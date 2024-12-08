let allClassrooms = [];
let currentClassroomDetails = {};

document.addEventListener("DOMContentLoaded", function () {
    fetchAllClassrooms();
});

// Fetch all classrooms from the server
function fetchAllClassrooms() {
    return fetch("http://localhost:8080/getClassrooms")
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
                    ${classroom.room_name}
                </div>
                <div class="classroom-details" id="details-${index}" style="display: none;">
                    <div>Classroom Name: ${classroom.room_name}</div>
                    <div class="classroom-controls">
                        <button type="button" class="btn btn-danger" onclick="editDetails(${index})">Edit</button>
                        <button type="button" class="btn btn-danger" onclick="deleteClassroom(${classroom.room_id})">Delete</button>
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
        classroom.room_name.toLowerCase().includes(input)
    );
    showClassroomSuggestions(filteredClassrooms);
}

// Show classroom suggestions in the suggestion box
function showClassroomSuggestions(suggestions) {
    const suggestionBox = document.getElementById("suggestionBox");
    suggestionBox.innerHTML = ""; // Clear previous suggestions

    suggestions.forEach((classroom) => {
        const div = document.createElement("div");
        div.classList.add("list-group-item", "list-group-item-action"); // Bootstrap styling
        div.textContent = classroom.room_name;
        div.onclick = function () {
            selectClassroom(classroom); // Select the classroom on click
        };
        suggestionBox.appendChild(div);
    });
    suggestionBox.style.display = suggestions.length > 0 ? "block" : "none";
}

// Select a classroom
function selectClassroom(classroom) {
    document.getElementById("classroomId").value = classroom.room_id;
    document.getElementById("classroomName").value = classroom.room_name;
    document.getElementById("suggestionBox").style.display = "none";
}

// Submit classroom details
function submitClassroomDetails(event) {
    event.preventDefault();

    currentClassroomDetails = {
        room_id: document.getElementById("classroomId").value,
        room_name: document.getElementById("classroomName").value,
    };

    const apiUrl = "http://localhost:8080/saveClassroom";
    fetch(apiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(currentClassroomDetails),
    })
        .then((response) => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then((data) => {
            alert(data.message);
            window.location.reload();
        })
        .catch((error) => {
            console.error("Error:", error);
            alert("An error occurred while submitting classroom details.");
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
    document.getElementById("classroomId").value = classroom.room_id;
    document.getElementById("classroomName").value = classroom.room_name;
}

// Delete a classroom
function deleteClassroom(classroomId) {
    if (confirm("Are you sure you want to delete this classroom?")) {
        const apiUrl = `http://localhost:8080/deleteClassroom/${classroomId}`;
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
                return response.text();
            })
            .then((data) => {
                alert(data);
                window.location.reload();
            })
            .catch((error) => {
                console.error("Error:", error);
                alert("An error occurred while deleting the classroom.");
            });
    }
}

// Toggle classroom details display
function toggleDetails(index) {
    const detailsDiv = document.getElementById(`details-${index}`);
    detailsDiv.style.display =
        detailsDiv.style.display === "none" ? "block" : "none";
}
