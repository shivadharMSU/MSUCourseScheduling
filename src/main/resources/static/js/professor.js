let allProfessors = [];
let allProfessorTypes = [];
let allCoursesDetails = []; // Store all courses fetched from the server
let selectedCourses = []; // Store selected course objects
let currentProfessorDetails = {};

document.addEventListener("DOMContentLoaded", function () {
  fetchAllProfessors();
  fetchAllProfessorsTypes();
  fetchFullCoursesDetails();
});

//suggestion box hiding

// Helper function to handle mouse enter/leave events for suggestion boxes
function setupSuggestionBoxHiding(inputFieldId, suggestionBoxId) {
  let isMouseOverSuggestionBox = false;
  const inputField = document.getElementById(inputFieldId);
  const suggestionBox = document.getElementById(suggestionBoxId);

  // Event listeners for mouse enter/leave
  suggestionBox.addEventListener(
    "mouseenter",
    () => (isMouseOverSuggestionBox = true)
  );
  suggestionBox.addEventListener(
    "mouseleave",
    () => (isMouseOverSuggestionBox = false)
  );

  // Blur event listener on input field to hide suggestion box
  inputField.addEventListener("blur", function () {
    if (!isMouseOverSuggestionBox) {
      setTimeout(() => (suggestionBox.style.display = "none"), 100); // Delay to allow click events
    }
  });
}

// Call the function for Professor Name, Professor Type, and Course Input suggestion boxes
setupSuggestionBoxHiding("professorName", "suggestionBox");
setupSuggestionBoxHiding("profType", "suggestionBoxProfType");
setupSuggestionBoxHiding("courseInput", "courseSuggestionBox"); // Added for courses

// end of suggestion box hiding

document
  .getElementById("professorName")
  .addEventListener("input", searchProfessor);
document
  .getElementById("courseInput")
  .addEventListener("input", searchFullCoursesDetails);
const profTypeSelect = document.getElementById("profType");

// Fetch all professors from the server
function fetchAllProfessors() {
  return fetch("http://localhost:8080/getProfessors")
    .then((response) => response.json())
    .then((data) => {
      allProfessors = data; // Store the data in the allProfessors variable
      displayProfessors();
    })
    .catch((error) => {
      console.error("Error fetching professors:", error);
    });
}

function fetchAllProfessorsTypes() {
  return fetch("http://localhost:8080/getProfessorType")
    .then((response) => response.json())
    .then((data) => {
      allProfessorTypes = data; // Store the data in the allProfessorTypes variable
    })
    .catch((error) => {
      console.error("Error fetching professors:", error);
    });
}

// Fetch all courses from the server
function fetchFullCoursesDetails() {
  return fetch("http://localhost:8080/coursedetails/fullCoursesDetails")
    .then((response) => response.json())
    .then((data) => {
      allCoursesDetails = data; // Store all course data
    })
    .catch((error) => {
      console.error("Error fetching courses:", error);
    });
}

function displayProfessors() {
  const container = document.getElementById("professorsContainer");

  // Sort professors by profStatus: active (true) first, inactive (false) last
  const sortedProfessors = allProfessors.sort(
    (a, b) => b.profStatus - a.profStatus
  );

  container.innerHTML = sortedProfessors
    .map(
      (professor, index) => `
        <div class="professor-item ${
          !professor.profStatus ? "inactive-professor" : ""
        }" data-index="${index}">
            <div class="professor-name" onclick="toggleDetails(${index})">
                <span class="arrow-icon">&#9660;</span>
                ${professor.name}
            </div>
            <div class="professor-details" id="details-${index}" style="display: none;">
                <div>Name: ${professor.name}</div>
                <div>Course Load: ${professor.courseLoad}</div>
                <div>Professor Type: ${professor.professorTypeName}</div>
                <div>Courses: <ol>${professor.professorMappings
                  .map((mapping) => `<li>${mapping.courseName}</li>`)
                  .join("")}</ol></div>
                <div>Status: ${
                  professor.profStatus ? "Active" : "Inactive"
                }</div>
                <div>Availabilities: ${professor.availabilities
                  .map(
                    (availability) => `
                        <div>Day: ${availability.dayOfWeek}, Start Time: ${availability.startTime}, End Time: ${availability.endTime}</div>
                    `
                  )
                  .join("")}</div>
                <div class="professor-controls">
                    <button type="button" class="btn btn-danger" onclick="editDetails(${index})">Edit</button>
                    <button type="button" class="btn btn-danger" onclick="deleteProfessor(${
                      professor.professorId
                    })">Toggle</button>
                </div>
            </div>
        </div>
    `
    )
    .join("");
}

// Function to search and filter courses based on input
function searchFullCoursesDetails() {
  const input = document.getElementById("courseInput").value.toLowerCase();
  const filteredCourses = allCoursesDetails.filter((course) =>
    course.courseDetails.courseName.toLowerCase().includes(input)
  );
  showCourseSuggestions(filteredCourses);
}

// Function to display suggestions
function searchProfessor() {
  const input = document.getElementById("professorName").value.toLowerCase();
  const filteredProfessors = allProfessors.filter((professor) =>
    professor.name.toLowerCase().includes(input)
  );
  showSuggestionsforProf(filteredProfessors);
}

// Function to show suggestions in the suggestion box with Bootstrap classes
function showSuggestionsforProf(suggestions) {
  const suggestionBox = document.getElementById("suggestionBox");
  suggestionBox.innerHTML = "";
  suggestions.forEach((professor) => {
    const div = document.createElement("div");
    div.classList.add("list-group-item", "list-group-item-action"); // Bootstrap classes
    div.textContent = professor.name;
    div.onclick = function () {
      selectProfessor(professor);
    };
    suggestionBox.appendChild(div);
  });
  suggestionBox.style.display = suggestions.length > 0 ? "block" : "none";
}

function showSuggestionsforProfType() {
  const suggestionBoxProfType = document.getElementById(
    "suggestionBoxProfType"
  );
  suggestionBoxProfType.innerHTML = "";
  allProfessorTypes.forEach((professor) => {
    const div = document.createElement("div");
    div.classList.add("list-group-item", "list-group-item-action"); // Bootstrap classes
    div.textContent = professor.type;
    div.onclick = function () {
      selectProfessorType(professor);
    };
    suggestionBoxProfType.appendChild(div);
  });
  suggestionBoxProfType.style.display =
    allProfessorTypes.length > 0 ? "block" : "none";
}

// Show course suggestions in the suggestion box
function showCourseSuggestions(suggestions) {
  const suggestionBox = document.getElementById("courseSuggestionBox");
  suggestionBox.innerHTML = ""; // Clear previous suggestions

  suggestions.forEach((course) => {
    const div = document.createElement("div");
    div.classList.add("list-group-item", "list-group-item-action"); // Bootstrap styling
    div.textContent = course.courseDetails.courseName;
    div.onclick = function () {
      selectCourse(course); // Select the course on click
    };
    suggestionBox.appendChild(div);
  });
  suggestionBox.style.display = suggestions.length > 0 ? "block" : "none";
}

function selectCourse(course) {
  // Ensure the course is not already selected
  if (!selectedCourses.some((c) => c.courseDetails.courseId === course.courseDetails.courseId)) {
    selectedCourses.push(course); // Add course to the selected list
    updateSelectedCourses(); // Update the UI with the new selection
  }

  // Clear the input and hide the suggestion box
  document.getElementById("courseInput").value = "";
  document.getElementById("courseSuggestionBox").style.display = "none";
}

// Update selected courses
function updateSelectedCourses() {
  const container = document.getElementById("selectedCoursesContainer");

  // Check if the container exists in the DOM
  if (!container) {
    console.error("Error: 'selectedCoursesContainer' element not found.");
    return;
  }

  // Clear previous selections
  container.innerHTML = "";

  // Generate HTML for the selected courses
  selectedCourses.forEach((course, index) => {
    const courseDiv = document.createElement("div");
    courseDiv.classList.add("badge", "badge-primary", "mr-2", "p-2", "d-inline-flex", "align-items-center");
    courseDiv.innerHTML = `
      ${course.courseDetails.courseName}
      <button type="button" class="ml-2 btn btn-sm btn-danger" onclick="removeCourse(${index})">X</button>
    `;
    container.appendChild(courseDiv);
  });

  // Update the hidden input with selected course IDs
  document.getElementById("selectedCoursesIds").value = selectedCourses
    .map((c) => c.courseDetails.courseId)
    .join(",");
}

// Remove a course
function removeCourse(index) {
  selectedCourses.splice(index, 1); // Remove course from the list
  updateSelectedCourses(); // Update the UI
}

function selectProfessorType(professor) {
  document.getElementById("profType").value = professor.type;
  document.getElementById("profTypeId").value = professor.id;
  document.getElementById("suggestionBoxProfType").style.display = "none";
}

function selectProfessor(professor) {
  var matchedType = allProfessorTypes.find(
    (type) => type.type === professor.professorTypeName
  );
  document.getElementById("profId").value = professor.professorId;
  document.getElementById("professorName").value = professor.name;
  document.getElementById("courseLoad").value = professor.courseLoad;
  document.getElementById("profType").value = professor.professorTypeName;
  if (matchedType) {
    document.getElementById("profTypeId").value = matchedType.id;
  } else {
    console.warn("No matching professor type found.");
  }
  if (professor.profStatus) {
    document.getElementById("profStatus").checked = true; // Turn on toggle
  } else {
    document.getElementById("profStatus").checked = false; // Turn off toggle
  }
  populateAvailabilities(professor.availabilities);
  document.getElementById("suggestionBox").style.display = "none";

  // Populate selected courses
  selectedCourses = professor.professorMappings
    .map((mapping) => {
      return allCoursesDetails.find(
        (course) => course.courseDetails.courseId === mapping.courseId
      );
    })
    .filter((course) => course !== undefined);
  updateSelectedCoursesDisplay();
}

function updateSelectedCoursesDisplay() {
  const container = document.getElementById("selectedCoursesContainer");
  container.innerHTML = ""; // Clear previous selections

  selectedCourses.forEach((course, index) => {
    const courseDiv = document.createElement("div");
    courseDiv.classList.add(
      "badge",
      "badge-primary",
      "p-2",
      "mr-2",
      "d-inline-flex",
      "align-items-center"
    );

    courseDiv.innerHTML = `
      ${course.courseDetails.courseName}
      <button type="button" class="ml-2 btn btn-sm btn-danger" onclick="removeCourse(${index})">X</button>
    `;

    container.appendChild(courseDiv);
  });
}

function populateAvailabilities(availabilities) {
  const timeSlotsDiv = document.getElementById("timeSlotsContainer");
  timeSlotsDiv.innerHTML = ""; // Clear existing slots
  let index = 0; // Initialize index for unique IDs

  availabilities.forEach((availability) => {
    let days = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    let buttonsHtml = days
      .map(
        (day) =>
          `<button class="btn btn-outline-primary ${
            availability.dayOfWeek.toLowerCase() === day.toLowerCase()
              ? "active"
              : ""
          }" data-day="${day.toLowerCase()}">${day}</button>`
      )
      .join("");

    const timeSlotDiv = document.createElement("div");
    timeSlotDiv.classList.add("row", "align-items-center", "mb-3");
    timeSlotDiv.innerHTML = `
            <div class="btn-group col-auto" role="group" aria-label="Day Selection">
                ${buttonsHtml}
            </div>
            <div class="col">
                <input type="time" class="form-control" value="${availability.startTime}" />
            </div>
            <div class="col">
                <input type="time" class="form-control" value="${availability.endTime}" />
            </div>
            <div class="col-auto">
                <button class="btn btn-danger" type="button" onclick="removeTimeSlot(this)">Remove</button>
            </div>
        `;

    timeSlotsDiv.appendChild(timeSlotDiv);

    index++; // Increment index for next slot
    // Re-attach event listeners for day selection toggle if needed
    timeSlotDiv.querySelectorAll(".btn-outline-primary").forEach((button) => {
      button.addEventListener("click", function () {
        this.classList.toggle("active");
        this.classList.toggle("btn-primary");
      });
    });
  });
}

function toggleDaySelection(dayId) {
  var element = document.getElementById(
    "label" + dayId.charAt(0).toUpperCase() + dayId.slice(1)
  );
  element.classList.toggle("active-day"); // You'll need to define this class in your CSS
}

// Function to dynamically add new time slots for professor availability
// JavaScript Function to add new time slots
function addAvailabilitySlot() {
  let timeSlotsDiv = document.getElementById("timeSlotsContainer");
  let index = timeSlotsDiv.children.length;

  let newTimeSlotDiv = document.createElement("div");
  newTimeSlotDiv.classList.add("row", "align-items-center", "mb-3");

  let days = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
  let buttonsHtml = days
    .map(
      (day) =>
        `<button class="btn btn-outline-primary" data-day="${day.toLowerCase()}">${day}</button>`
    )
    .join("");

  newTimeSlotDiv.innerHTML = `
        <div class="btn-group col-auto" role="group" aria-label="Day Selection">
            ${buttonsHtml}
        </div>
        <div class="col">
            <input type="time" class="form-control" id="startTime${index}" />
        </div>
        <div class="col">
            <input type="time" class="form-control" id="endTime${index}" />
        </div>
        <div class="col-auto">
            <button class="btn btn-outline-danger" type="button" onclick="removeTimeSlot(this)">Remove</button>
        </div>
    `;

  timeSlotsDiv.appendChild(newTimeSlotDiv);

  newTimeSlotDiv.querySelectorAll(".btn-outline-primary").forEach((button) => {
    button.addEventListener("click", function () {
      toggleDaySelection(this);
    });
  });
}

function removeTimeSlot(element) {
  element.closest(".row").remove();
}

function toggleDaySelection(button) {
  button.classList.toggle("active");
  button.classList.toggle("btn-primary"); // Bootstrap active class
}

function submitProfessorDetails(event) {
  event.preventDefault(); // Prevent page reload

  // Update the currentProfessorDetails object with the form data
  currentProfessorDetails = {
    professorId: document.getElementById("profId").value,
    name: document.getElementById("professorName").value,
    courseLoad: document.getElementById("courseLoad").value,
    profTypeId: document.getElementById("profTypeId").value,
    profCourses: selectedCourses.map((course) => course.courseDetails.courseId),
    profStatus: document.getElementById("profStatus").checked,
    availabilities: [], // This will be populated below
  };

  // Get all the availability slots
  const timeSlotRows = document
    .getElementById("timeSlotsContainer")
    .querySelectorAll(".row");

  const profcourses = document.getElementById("");

  // Iterate over each row to extract availability data
  timeSlotRows.forEach((row) => {
    const activeButtons = row.querySelectorAll("button.active");
    const timeInputs = row.querySelectorAll('input[type="time"]');

    if (timeInputs.length === 2) {
      // Check if there are exactly two time inputs
      const startTime = timeInputs[0].value;
      const endTime = timeInputs[1].value;

      activeButtons.forEach((activeButton) => {
        const dayOfWeek = activeButton.textContent; // Get the text of each active button

        currentProfessorDetails.availabilities.push({
          dayOfWeek: dayOfWeek.trim(),
          startTime,
          endTime,
        });
      });
    } else {
      console.warn("Row missing time inputs:", row);
    }
  });
  console.log("currentProfessorDetails   ", currentProfessorDetails);
  const apiUrl = "http://localhost:8080/saveProfessor";

  // Perform the API call to submit professor details
  fetch(apiUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(currentProfessorDetails), // Convert the currentProfessorDetails object to a JSON string
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json(); // Parse response as JSON if status is ok
    })
    .then((data) => {
      // If data is a string, it means the response was plain text
      if (typeof data === "string") {
        console.log("Success:", data);
        alert(data); // Alert the plain text success message
        window.location.reload();
      } else {
        console.log("Success:", data.message);
        alert(data.message); // Alert the success message from the JSON response
        window.location.reload();
      }
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("An error occurred while submitting professor details.");
    });
  clearFields();
}

function clearFields() {
  document.getElementById("profId").value = "";
  document.getElementById("professorName").value = "";
  document.getElementById("courseLoad").value = "";
  document.getElementById("profType").value = "";
  document.getElementById("profTypeId").value = "";
  document.getElementById("selectedCoursesContainer").innerHTML = "";
  document.getElementById("selectedCoursesIds").value = "";
  selectedCourses = [];
  const timeSlotsContainer = document.getElementById("timeSlotsContainer");
  while (timeSlotsContainer.firstChild) {
    timeSlotsContainer.removeChild(timeSlotsContainer.firstChild);
  }
}

// Function to toggle the display of details
function toggleDetails(index) {
  // Get all the details and header elements
  const allDetails = document.querySelectorAll(".professor-details");
  const allHeaders = document.querySelectorAll(".professor-item");

  // Get the clicked elements
  const detailsDiv = document.getElementById(`details-${index}`);
  const headerDiv = allHeaders[index];

  // Check if the clicked section is already expanded
  const isExpanded = headerDiv.classList.contains("expanded");

  // Close all sections
  allDetails.forEach((element) => {
    element.style.display = "none";
  });
  allHeaders.forEach((element) => {
    element.classList.remove("expanded");
  });

  // Toggle the clicked section
  if (!isExpanded) {
    detailsDiv.style.display = "block";
    headerDiv.classList.add("expanded");
  }
  // updateArrowIcons();
}

// Function to make details editable
function editDetails(index) {
  // Retrieve the professor item and make its details editable
  const professor = allProfessors[index];
  selectProfessor(professor);
  window.scrollTo(0, 0);
}

// Function to delete a professor
function deleteProfessor(professorId) {
  if (confirm("Are you sure you want to change this professor status?")) {
    const apiUrl = `http://localhost:8080/delete/${professorId}`; // DELETE API URL with professorId

    // Perform the DELETE request
    fetch(apiUrl, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.text(); // Parse response as plain text
      })
      .then((data) => {
        alert(data); // Show success message
        window.location.reload(); // Reload the page or update the DOM if needed
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("An error occurred while deleting the professor.");
      });
  }
}

// Bind the event listener to the form submit event
document
  .getElementById("professorForm")
  .addEventListener("submit", function (event) {
    event.preventDefault();
  });
