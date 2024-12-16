let allCourses = [];
let allProfessors = [];
let selectedProfessors = [];
let currentCourseDetails = {};

// Helper function to handle suggestion box hiding on blur
function setupSuggestionBoxHiding(inputFieldId, suggestionBoxId) {
  let isMouseOverSuggestionBox = false;
  const inputField = document.getElementById(inputFieldId);
  const suggestionBox = document.getElementById(suggestionBoxId);

  suggestionBox.addEventListener(
    "mouseenter",
    () => (isMouseOverSuggestionBox = true)
  );
  suggestionBox.addEventListener(
    "mouseleave",
    () => (isMouseOverSuggestionBox = false)
  );

  inputField.addEventListener("blur", function () {
    if (!isMouseOverSuggestionBox) {
      setTimeout(() => (suggestionBox.style.display = "none"), 100);
    }
  });
}

// Set up suggestion box hiding
setupSuggestionBoxHiding("courseInput", "courseSuggestionBox");
setupSuggestionBoxHiding("professorInput", "professorSuggestionBox");

// Add input listeners
document.getElementById("courseInput").addEventListener("input", searchCourse);
document
  .getElementById("professorInput")
  .addEventListener("input", searchProfessor);

async function fetchAllProfessors() {
  try {
    const response = await fetch("http://localhost:8080/getProfessors");
    allProfessors = await response.json();
    console.log("Professors fetched:", allProfessors); // Debugging
  } catch (error) {
    console.error("Error fetching professors:", error);
  }
}

async function fetchAllCourses() {
  try {
    const response = await fetch(
      "http://localhost:8080/coursedetails/fullCoursesDetails"
    );
    allCourses = await response.json();
    console.log("Courses fetched:", allCourses); // Debugging
    displayCourses(); // Ensure display logic happens after data is fetched
  } catch (error) {
    console.error("Error fetching courses:", error);
  }
}

document.addEventListener("DOMContentLoaded", async () => {
  await fetchAllProfessors(); // Ensure professors are fetched before display
  await fetchAllCourses(); // Ensure courses are fetched and displayed
});

// Display courses
function displayCourses() {
  const container = document.getElementById("coursesContainer");

  container.innerHTML = allCourses
    .map(
      (course, index) => `
      <div class="course-item" data-index="${index}">
        <div class="course-name" onclick="toggleCourseDetails(${index})">
          <span class="arrow-icon">&#9660;</span> 
          ${course.courseDetails.courseName} (CSIT ${
        course.courseDetails.courseNumber
      })
        </div>
        <div class="course-details" id="course-details-${index}" style="display: none;">
          <div>Credits: ${course.courseDetails.credits}</div>
          <div>Computer Required: ${
            course.courseDetails.computerRequired ? "Yes" : "No"
          }</div>
          <div>Assigned Professors:
            <ul>
              ${
                course.professorMappings && course.professorMappings.length > 0
                  ? course.professorMappings
                      .map(
                        (prof) =>
                          `<li>${getProfessorNameById(prof.professorId)}</li>`
                      )
                      .join("")
                  : "<li>No professors assigned</li>"
              }
            </ul>
          </div>
          <div class="course-controls">
            <button class="btn btn-primary" onclick="editCourse(${index})">Edit</button>
            <button class="btn btn-danger" onclick="deleteCourse(${
              course.courseDetails.courseId
            })">Delete</button>
          </div>
        </div>
      </div>`
    )
    .join("");
}

function toggleCourseDetails(index) {
  // Get all course details and headers
  const allDetails = document.querySelectorAll(".course-details");
  const allHeaders = document.querySelectorAll(".course-item");

  // Get the specific course's details and header for the clicked course
  const detailsDiv = document.getElementById(`course-details-${index}`);
  const headerDiv = allHeaders[index];

  // Check if the clicked course section is already expanded
  const isExpanded = headerDiv.classList.contains("expanded");

  // Close all open course details and reset arrow icons
  allDetails.forEach((details) => (details.style.display = "none"));
  allHeaders.forEach((header) => header.classList.remove("expanded"));

  // Toggle the clicked course section
  if (!isExpanded) {
    detailsDiv.style.display = "block";
    headerDiv.classList.add("expanded");
  }

  // Update arrow icon rotation based on expanded state
  updateArrowIcons();
}

function updateArrowIcons() {
  const allHeaders = document.querySelectorAll(".course-item");
  allHeaders.forEach((header) => {
    const arrowIcon = header.querySelector(".arrow-icon");
    if (header.classList.contains("expanded")) {
      arrowIcon.style.transform = "rotate(180deg)";
    } else {
      arrowIcon.style.transform = "rotate(0deg)";
    }
  });
}

function editCourse(index) {
  // Retrieve the course item and make its details editable
  const course = allCourses[index];
  selectCourse(course); // Use the selectCourse logic to populate the form
  window.scrollTo(0, 0); // Scroll to the top of the page for easy editing
}

// Get professor name by ID
function getProfessorNameById(professorId) {
  const professor = allProfessors.find((p) => p.professorId === professorId);
  return professor ? professor.name : "Unknown";
}

// Search for courses
function searchCourse() {
  const input = document.getElementById("courseInput").value.toLowerCase();
  const suggestions = allCourses.filter((course) =>
    course.courseDetails.courseName.toLowerCase().includes(input)
  );
  showCourseSuggestions(suggestions);
}

// Show course suggestions
function showCourseSuggestions(suggestions) {
  const suggestionBox = document.getElementById("courseSuggestionBox");
  suggestionBox.innerHTML = "";

  suggestions.forEach((course) => {
    const div = document.createElement("div");
    div.classList.add("list-group-item", "list-group-item-action");
    div.textContent = course.courseDetails.courseName;
    div.onclick = () => selectCourse(course);
    suggestionBox.appendChild(div);
  });

  suggestionBox.style.display = suggestions.length ? "block" : "none";
}

// Select a course
function selectCourse(course) {
  document.getElementById("courseInput").value =
    course.courseDetails.courseName;
  document.getElementById("courseId").value = course.courseDetails.courseId;
  document.getElementById("courseNumber").value =
    course.courseDetails.courseNumber;
  document.getElementById("credits").value = course.courseDetails.credits;
  document.getElementById("computerRequired").checked =
    course.courseDetails.computerRequired;
  //   document.getElementById("tenure").value = course.semesterMappings
  //     .map((m) => m.tenure)
  //     .join(", ");
  document.getElementById("courseSuggestionBox").style.display = "none";

  // populate selected courses
  selectedProfessors = allProfessors.filter((prof) =>
    course.professorMappings.some((p) => p.professorId === prof.professorId)
  );
  updateSelectedProfessors();
}

// Search for professors
function searchProfessor() {
  const input = document.getElementById("professorInput").value.toLowerCase();
  const suggestions = allProfessors.filter((prof) =>
    prof.name.toLowerCase().includes(input)
  );
  showProfessorSuggestions(suggestions);
}

// Show professor suggestions
function showProfessorSuggestions(suggestions) {
  const suggestionBox = document.getElementById("professorSuggestionBox");
  suggestionBox.innerHTML = "";

  suggestions.forEach((prof) => {
    const div = document.createElement("div");
    div.classList.add("list-group-item", "list-group-item-action");
    div.textContent = prof.name;
    div.onclick = () => selectProfessor(prof);
    suggestionBox.appendChild(div);
  });

  suggestionBox.style.display = suggestions.length ? "block" : "none";
}

// Select a professor
function selectProfessor(professor) {
  // Ensure the professor is not already selected
  if (
    !selectedProfessors.some((p) => p.professorId === professor.professorId)
  ) {
    selectedProfessors.push(professor);
    updateSelectedProfessors(); // Update the UI with the new selection
  }

  // Clear the input and hide the suggestion box
  document.getElementById("professorInput").value = "";
  document.getElementById("professorSuggestionBox").style.display = "none";
}

function updateSelectedProfessors() {
  const container = document.getElementById("selectedProfessorsContainer");

  if (!container) {
    console.error("Error: 'selectedProfessorsContainer' element not found.");
    return;
  }

  // Clear previous selections
  container.innerHTML = "";

  // Generate badges for each selected professor
  selectedProfessors.forEach((prof, index) => {
    const badge = document.createElement("div");
    badge.classList.add(
      "badge",
      "badge-primary",
      "mr-2",
      "p-2",
      "d-inline-flex",
      "align-items-center"
    );
    badge.innerHTML = `
            ${prof.name}
            <button type="button" class="ml-2 btn btn-sm btn-danger" onclick="removeProfessor(${index})">X</button>
        `;
    container.appendChild(badge);
  });

  // Update the hidden input with selected professor IDs
  document.getElementById("selectedProfessorIds").value = selectedProfessors
    .map((p) => p.professorId)
    .join(",");
}

function removeProfessor(index) {
  selectedProfessors.splice(index, 1); // Remove the professor from the list
  updateSelectedProfessors(); // Update the UI
}

// Submit course details
function submitCourseDetails(event) {
  event.preventDefault();

  const courseId = document.getElementById("courseId").value.trim();
  const courseName = document.getElementById("courseInput").value.trim();
  const courseNumber = document.getElementById("courseNumber").value.trim();
  const credits = document.getElementById("credits").value.trim();
  const computerRequired = document.getElementById("computerRequired").checked;

  if (!courseName || !courseNumber) {
    alert("Course name and course number cannot be empty!");
    return;
  }

  // Validate course number for duplicates
  // Validate course number for duplicates
  for (let i = 0; i < allCourses.length; i++) {
    const currentCourse = allCourses[i];
    if (
      currentCourse.courseDetails.courseNumber === courseNumber && // Check if the number matches
      currentCourse.courseDetails.id !== courseId // Allow if the ID matches (update case)
    ) {
      alert("Course number already exists. Please use a different number.");
      return; // Exit immediately
    }
  }

  const courseData = {
    courseId: courseId || null,
    courseName,
    courseNumber,
    credits: parseFloat(credits),
    computerRequired,
    professors: selectedProfessors.map((p) => p.professorId),
  };

  console.log("Submitting course details:", courseData);

  fetch("http://localhost:8080/coursedetails/savecourse", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(courseData),
  })
    .then((response) => {
      if (!response.ok) {
        return response.text().then((errorText) => {
          throw new Error(
            errorText || `HTTP error! Status: ${response.status}`
          );
        });
      }
      return response.json();
    })
    .then((data) => {
      alert(data.message || "Course saved successfully.");
      fetchAllCourses(); // Refresh the course list
      clearFields(); // Clear fields after saving
    })
    .catch((error) => {
      console.error("Error saving course:", error);
      alert("Error saving course: " + error.message);
    });
}

function clearFields() {
  // Clear course-related fields
  document.getElementById("courseId").value = "";
  document.getElementById("courseInput").value = "";
  document.getElementById("courseNumber").value = "";
  document.getElementById("credits").value = "";
  document.getElementById("computerRequired").checked = false;
  document.getElementById("selectedProfessorsContainer").innerHTML = "";
  document.getElementById("selectedProfessorIds").value = "";
  selectedProfessors = [];
  // document.getElementById("tenure").value = "";
  document.getElementById("professorSuggestionBox").style.display = "none";
  window.scrollTo(0, 0);
}
