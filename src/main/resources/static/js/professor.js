let allProfessors = [];
let allProfessorTypes = [];
let currentProfessorDetails = {};
fetchAllProfessors();
fetchAllProfessorsTypes();

//suggestion box hiding

let isMouseOverProfSuggestionBox = false;
let isMouseOverProfTypeSuggestionBox = false;

// Professor Name Suggestion Box
let profInputField = document.getElementById("professorName");
let profSuggestionBox = document.getElementById("suggestionBox");

// Professor Type Suggestion Box
let profTypeInputField = document.getElementById("profType");
let profTypeSuggestionBox = document.getElementById("suggestionBoxProfType");

// Event listeners for mouse enter/leave for professor name suggestion box
profSuggestionBox.addEventListener(
  "mouseenter",
  () => (isMouseOverProfSuggestionBox = true)
);
profSuggestionBox.addEventListener(
  "mouseleave",
  () => (isMouseOverProfSuggestionBox = false)
);

// Event listeners for mouse enter/leave for professor type suggestion box
profTypeSuggestionBox.addEventListener(
  "mouseenter",
  () => (isMouseOverProfTypeSuggestionBox = true)
);
profTypeSuggestionBox.addEventListener(
  "mouseleave",
  () => (isMouseOverProfTypeSuggestionBox = false)
);

// Adjusting the blur event listener on the input field for professor name
profInputField.addEventListener("blur", function () {
  if (!isMouseOverProfSuggestionBox) {
    setTimeout(() => (profSuggestionBox.style.display = "none"), 100); // Delay hiding to allow click event
  }
});

// Adjusting the blur event listener on the input field for professor type
profTypeInputField.addEventListener("blur", function () {
  if (!isMouseOverProfTypeSuggestionBox) {
    setTimeout(() => (profTypeSuggestionBox.style.display = "none"), 100); // Delay hiding to allow click event
  }
});

// end of suggestion box hiding

document
  .getElementById("professorName")
  .addEventListener("input", searchProfessor);
const profTypeSelect = document.getElementById("profType");

// Fetch all professors from the server
function fetchAllProfessors() {
  return fetch("http://localhost:8080/getProfessors")
    .then((response) => response.json())
    .then((data) => {
      allProfessors = data; // Store the data in the allProfessors variable
      console.log(allProfessors);
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
      console.log(allProfessorTypes);
    })
    .catch((error) => {
      console.error("Error fetching professors:", error);
    });
}

// Dynamically add day buttons with Bootstrap classes
document.querySelectorAll(".day-button").forEach((button) => {
  button.addEventListener("click", function () {
    // Toggle 'active' class to highlight the button with Bootstrap style
    this.classList.toggle("active");
    this.classList.toggle("btn-primary"); // Bootstrap active class
    // Add logic to handle the selection of day
  });
});

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
    profStatus: document.getElementById("profStatus").checked,
    availabilities: [], // This will be populated below
  };

  // Get all the availability slots
  const timeSlotRows = document
    .getElementById("timeSlotsContainer")
    .querySelectorAll(".row");
  console.log(timeSlotRows);

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

  console.log(currentProfessorDetails);
  clearFields();
}

function clearFields() {
  document.getElementById("profId").value = "";
  document.getElementById("professorName").value = "";
  document.getElementById("courseLoad").value = "";
  document.getElementById("profType").value = "";
  document.getElementById("profTypeId").value = "";
  const timeSlotsContainer = document.getElementById("timeSlotsContainer");
  while (timeSlotsContainer.firstChild) {
    timeSlotsContainer.removeChild(timeSlotsContainer.firstChild);
  }
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
                <!-- Display professor details here -->
                <div>Name: ${professor.name}</div>
                <div>Course Load: ${professor.courseLoad}</div>
                <div>Professor Type: ${professor.professorTypeName}</div>
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
    console.log(professorId);
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
