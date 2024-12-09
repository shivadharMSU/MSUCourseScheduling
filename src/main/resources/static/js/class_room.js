let allClassrooms = [];
let currentClassroomDetails = {};

document.addEventListener("DOMContentLoaded", function () {
  fetchAllClassrooms();
});

// Fetch all classrooms from the server
function fetchAllClassrooms() {
  return fetch("http://localhost:8080/classrooms/allclassrooms")
    .then((response) => response.json())
    .then((data) => {
      allClassrooms = data;
      displayClassrooms();
    })
    .catch((error) => {
      console.error("Error fetching classrooms:", error);
    });
}

// Display classrooms in the UI
function displayClassrooms() {
  const container = document.getElementById("classroomContainer");
  container.innerHTML = allClassrooms
    .map(
      (classroom, index) => `
            <div class="classroom-item" data-index="${index}">
                <div class="classroom-name" onclick="toggleDetails(${index})">
                    <span class="arrow-icon">&#9660;</span>
                    ${classroom.roomName}
                </div>
                <div class="classroom-details" id="details-${index}" style="display: none;">
                <div>Classroom Id: ${classroom.id}</div>
                    <div>Classroom Name: ${classroom.roomName}</div>
                    <div class="classroom-controls">
                        <button type="button" class="btn btn-danger" onclick="editDetails(${index})">Edit</button>
                    </div>
                </div>
            </div>
        `
    )
    .join("");
}

// Search and filter classrooms based on input
function searchClassrooms() {
  const input = document.getElementById("classroomName").value.toLowerCase();
  const filteredClassrooms = allClassrooms.filter((classroom) =>
    classroom.roomName.toLowerCase().includes(input)
  );
  showClassroomSuggestions(filteredClassrooms);
}

// Show classroom suggestions in the suggestion box
function showClassroomSuggestions(suggestions) {
  const suggestionBox = document.getElementById("suggestionBox");
  suggestionBox.innerHTML = ""; // Clear previous suggestions

  suggestions.forEach((classroom) => {
    const div = document.createElement("div");
    div.classList.add("list-group-item", "list-group-item-action");
    div.textContent = classroom.roomName;
    div.onclick = function () {
      selectClassroom(classroom);
    };
    suggestionBox.appendChild(div);
  });
  suggestionBox.style.display = suggestions.length > 0 ? "block" : "none";
}

// Select a classroom
function selectClassroom(classroom) {
  document.getElementById("classroomId").value = classroom.id;
  document.getElementById("classroomName").value = classroom.roomName;
  document.getElementById("suggestionBox").style.display = "none";
}

function submitClassroomDetails(event) {
  event.preventDefault();

  const classroomId = document.getElementById("classroomId").value.trim();
  const classroomName = document.getElementById("classroomName").value.trim();

  if (!classroomName) {
    alert("Classroom name cannot be empty!");
    return;
  }

  // Check if the classroom name already exists
  const isDuplicate = allClassrooms.some(
    (classroom) =>
      classroom.roomName.toLowerCase() === classroomName.toLowerCase() &&
      (classroomId === "" || parseInt(classroomId, 10) !== classroom.id) // Ignore the current classroom in edit mode
  );

  if (isDuplicate) {
    alert("Classroom name already exists. Please use a different name.");
    return;
  }

  const currentClassroomDetails = {
    id: classroomId === "" ? null : parseInt(classroomId, 10), // Convert to null for new classrooms
    roomName: classroomName,
  };

  const apiUrl = "http://localhost:8080/classrooms/saveClassroom";

  fetch(apiUrl, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(currentClassroomDetails),
  })
    .then((response) => {
      if (!response.ok) {
        return response.text().then((errorText) => {
          throw new Error(
            errorText || `HTTP error! Status: ${response.status}`
          );
        });
      }
      return response.text(); // Expect plain text response
    })
    .then((data) => {
      alert(data); // Show success message
      window.location.reload(); // Reload the page to reflect changes
    })
    .catch((error) => {
      console.error("Error:", error);
      alert(`An error occurred: ${error.message}`);
    });

  clearFields();
}

// Clear form fields
function clearFields() {
  document.getElementById("classroomId").value = "";
  document.getElementById("classroomName").value = "";
}

// Edit classroom details
function editDetails(index) {
  const classroom = allClassrooms[index];
  document.getElementById("classroomId").value = classroom.id;
  document.getElementById("classroomName").value = classroom.roomName;
  window.scrollTo(0, 0);
}

// Toggle classroom details display
function toggleDetails(index) {
  const detailsDiv = document.getElementById(`details-${index}`);
  detailsDiv.style.display =
    detailsDiv.style.display === "none" ? "block" : "none";
}
