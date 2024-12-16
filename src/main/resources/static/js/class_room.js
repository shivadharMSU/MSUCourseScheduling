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
    .map((classroom, index) => {
      // Split classroom name and capacity
      const nameAndCapacity = classroom.roomName.match(/^(.*)\s\((\d+)\)$/); // Regex to extract name and capacity
      const classroomName = nameAndCapacity
        ? nameAndCapacity[1]
        : classroom.roomName;
      const classCapacity = nameAndCapacity ? nameAndCapacity[2] : "N/A";

      return `
        <div class="classroom-item" data-index="${index}">
            <div class="classroom-name" onclick="toggleDetails(${index})">
                <span class="arrow-icon" id="arrow-${index}">&#9654;</span>
                ${classroomName} (${classCapacity})
            </div>
            <div class="classroom-details" id="details-${index}" style="display: none;">
                <div><strong>Classroom Id:</strong> ${classroom.id}</div>
                <div><strong>Classroom Name:</strong> ${classroomName}</div>
                <div><strong>Capacity:</strong> ${classCapacity}</div>
                <div class="classroom-controls">
                    <button type="button" class="btn btn-danger" onclick="editDetails(${index})">Edit</button>
                </div>
            </div>
        </div>
      `;
    })
    .join("");
}

function toggleDetails(index) {
  const detailsElement = document.getElementById(`details-${index}`);
  const arrowIcon = document.getElementById(`arrow-${index}`);

  if (detailsElement.style.display === "none") {
    detailsElement.style.display = "block"; // Show details
    arrowIcon.innerHTML = "&#9660;"; // Downward-pointing arrow
  } else {
    detailsElement.style.display = "none"; // Hide details
    arrowIcon.innerHTML = "&#9654;"; // Right-pointing arrow
  }
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
  // Extract the classroom name and capacity from the concatenated roomName
  const nameAndCapacity = classroom.roomName.match(/^(.*)\s\((\d+)\)$/); // Regex to extract name and capacity

  const classroomName = nameAndCapacity
    ? nameAndCapacity[1]
    : classroom.roomName; // Extract name
  const classCapacity = nameAndCapacity ? nameAndCapacity[2] : ""; // Extract capacity

  // Populate the form fields
  document.getElementById("classroomId").value = classroom.id;
  document.getElementById("classroomName").value = classroomName;
  document.getElementById("classroomCapacity").value = classCapacity;

  // Hide the suggestion box
  document.getElementById("suggestionBox").style.display = "none";
}

function submitClassroomDetails(event) {
  event.preventDefault();

  const classroomId = document.getElementById("classroomId").value.trim();
  const classroomName = document.getElementById("classroomName").value.trim();
  const classCapacity = document
    .getElementById("classroomCapacity")
    .value.trim();

  if (!classroomName) {
    alert("Classroom name cannot be empty!");
    return;
  }

  // Concatenate classroom name and capacity
  const concatenatedRoomName = `${classroomName} (${classCapacity})`;

  // Check if the concatenated room name already exists
  const isDuplicate = allClassrooms.some(
    (classroom) =>
      classroom.roomName.toLowerCase() === concatenatedRoomName.toLowerCase() &&
      (classroomId === "" || parseInt(classroomId, 10) !== classroom.id) // Ignore the current classroom in edit mode
  );

  if (isDuplicate) {
    alert(
      "Classroom name with this capacity already exists. Please use a different name or capacity."
    );
    return;
  }

  // Prepare the classroom details with the concatenated name
  const currentClassroomDetails = {
    id: classroomId === "" ? null : parseInt(classroomId, 10), // Convert to null for new classrooms
    roomName: concatenatedRoomName, // Use the concatenated name
  };

  // Your logic to send currentClassroomDetails to the server goes here

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
  document.getElementById("classroomCapacity").value = "";
}

// Edit classroom details
function editDetails(index) {
  const classroom = allClassrooms[index];

  // Extract the classroom name and capacity from the concatenated roomName
  const nameAndCapacity = classroom.roomName.match(/^(.*)\s\((\d+)\)$/); // Regex to extract name and capacity

  const classroomName = nameAndCapacity
    ? nameAndCapacity[1]
    : classroom.roomName; // Extract name
  const classCapacity = nameAndCapacity ? nameAndCapacity[2] : ""; // Extract capacity

  // Populate the fields in the form
  document.getElementById("classroomId").value = classroom.id;
  document.getElementById("classroomName").value = classroomName;
  document.getElementById("classroomCapacity").value = classCapacity;

  // Scroll to the top of the page
  window.scrollTo(0, 0);
}

// Toggle classroom details display
function toggleDetails(index) {
  const detailsDiv = document.getElementById(`details-${index}`);
  detailsDiv.style.display =
    detailsDiv.style.display === "none" ? "block" : "none";
}
