let allCourses = [];
let allProfessors = [];
let currentCourseDetails = {};

fetchAllCourses();
fetchAllProfessors();

// Fetch all courses from the server
function fetchAllCourses() {
  return fetch("http://localhost:8080/coursedetails/fullCoursesDetails")
    .then((response) => response.json())
    .then((data) => {
      allCourses = data;
      displayCourses();
    })
    .catch((error) => {
      console.error("Error fetching courses:", error);
    });
}

// Fetch all professors from the server
function fetchAllProfessors() {
  return fetch("http://localhost:8080/getProfessors")
    .then((response) => response.json())
    .then((data) => {
      allProfessors = data;
    })
    .catch((error) => {
      console.error("Error fetching professors:", error);
    });
}

// Display all courses with their details
function displayCourses() {
  const container = document.getElementById("coursesContainer");
  container.innerHTML = allCourses
    .map(
      (course, index) => `
        <div class="course-item" data-index="${index}">
            <div class="course-name">${course.courseName} (${course.courseCode})</div>
            <div>Professor: ${course.professor ? course.professor.name : 'Not Assigned'}</div>
            <div>Credits: ${course.credits}</div>
            <div>Computer Required: ${course.computerRequired ? 'Yes' : 'No'}</div>
            <div class="course-controls">
                <button type="button" class="btn btn-primary" onclick="editCourse(${index})">Edit</button>
                <button type="button" class="btn btn-danger" onclick="deleteCourse(${course.courseId})">Delete</button>
            </div>
        </div>
    `
    )
    .join("");
}

// Search and display professor suggestions
function searchProfessor() {
  const input = document.getElementById("professor").value.toLowerCase();
  const filteredProfessors = allProfessors.filter((professor) =>
    professor.name.toLowerCase().includes(input)
  );
  showProfessorSuggestions(filteredProfessors);
}

// Show professor suggestions in a dropdown
function showProfessorSuggestions(suggestions) {
  const suggestionBox = document.getElementById("suggestionBox");
  suggestionBox.innerHTML = "";
  suggestions.forEach((professor) => {
    const div = document.createElement("div");
    div.classList.add("list-group-item", "list-group-item-action");
    div.textContent = professor.name;
    div.onclick = function () {
      selectProfessor(professor);
    };
    suggestionBox.appendChild(div);
  });
  suggestionBox.style.display = suggestions.length > 0 ? "block" : "none";
}

// Select a professor from the suggestion box
function selectProfessor(professor) {
  document.getElementById("professor").value = professor.name;
  document.getElementById("professorId").value = professor.professorId;
  document.getElementById("suggestionBox").style.display = "none";
}

// Submit course details (save or update)
function submitCourseDetails(event) {
  event.preventDefault();

  currentCourseDetails = {
    courseId: document.getElementById("courseId").value,
    courseName: document.getElementById("courseName").value,
    courseCode: document.getElementById("courseCode").value,
    professorId: document.getElementById("professorId").value,
  };

  const apiUrl = "http://localhost:8080/saveCourse";

  fetch(apiUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(currentCourseDetails),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      return response.json();
    })
    .then((data) => {
      alert(data.message);
      fetchAllCourses(); // Reload the course list
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("An error occurred while submitting course details.");
    });

  clearFields();
}

// Edit a course by pre-filling the form
function editCourse(index) {
  const course = allCourses[index];
  document.getElementById("courseId").value = course.courseId;
  document.getElementById("courseName").value = course.courseName;
  document.getElementById("courseCode").value = course.courseCode;
  if (course.professor) {
    document.getElementById("professor").value = course.professor.name;
    document.getElementById("professorId").value = course.professor.professorId;
  }
}

// Clear form fields
function clearFields() {
  document.getElementById("courseId").value = "";
  document.getElementById("courseName").value = "";
  document.getElementById("courseCode").value = "";
  document.getElementById("professor").value = "";
  document.getElementById("professorId").value = "";
}

// Delete a course
function deleteCourse(courseId) {
  if (confirm("Are you sure you want to delete this course?")) {
    const apiUrl = `http://localhost:8080/deleteCourse/${courseId}`;

    fetch(apiUrl, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.text();
      })
      .then((data) => {
        alert(data);
        fetchAllCourses(); // Reload the course list
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("An error occurred while deleting the course.");
      });
  }
}
