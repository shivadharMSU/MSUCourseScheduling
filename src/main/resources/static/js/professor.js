// JavaScript (detailsManagement.js)

let allProfessors = [];
let allProfessorTypes = [];
let currentProfessorDetails = {};
fetchAllProfessors();
fetchAllProfessorsTypes();

//suggestion box hiding

let isMouseOverProfSuggestionBox = false;
let isMouseOverProfTypeSuggestionBox = false;

// Professor Name Suggestion Box
let profInputField = document.getElementById('professorName');
let profSuggestionBox = document.getElementById('suggestionBox');

// Professor Type Suggestion Box
let profTypeInputField = document.getElementById('profType');
let profTypeSuggestionBox = document.getElementById('suggestionBoxProfType');

// Event listeners for mouse enter/leave for professor name suggestion box
profSuggestionBox.addEventListener('mouseenter', () => isMouseOverProfSuggestionBox = true);
profSuggestionBox.addEventListener('mouseleave', () => isMouseOverProfSuggestionBox = false);

// Event listeners for mouse enter/leave for professor type suggestion box
profTypeSuggestionBox.addEventListener('mouseenter', () => isMouseOverProfTypeSuggestionBox = true);
profTypeSuggestionBox.addEventListener('mouseleave', () => isMouseOverProfTypeSuggestionBox = false);

// Adjusting the blur event listener on the input field for professor name
profInputField.addEventListener('blur', function () {
    if (!isMouseOverProfSuggestionBox) {
        setTimeout(() => profSuggestionBox.style.display = 'none', 100); // Delay hiding to allow click event
    }
});

// Adjusting the blur event listener on the input field for professor type
profTypeInputField.addEventListener('blur', function () {
    if (!isMouseOverProfTypeSuggestionBox) {
        setTimeout(() => profTypeSuggestionBox.style.display = 'none', 100); // Delay hiding to allow click event
    }
});

// end of suggestion box hiding

document.getElementById('professorName').addEventListener('input', searchProfessor);
const profTypeSelect = document.getElementById('profType');

// Fetch all professors from the server
function fetchAllProfessors() {
    return fetch('http://localhost:8080/getProfessors')
        .then(response => response.json())
        .then(data => {
            allProfessors = data; // Store the data in the allProfessors variable
            console.log(allProfessors);
            displayProfessors();
        })
        .catch(error => {
            console.error('Error fetching professors:', error);
        });
}

function fetchAllProfessorsTypes() {
    return fetch('http://localhost:8080/getProfessorType')
        .then(response => response.json())
        .then(data => {
            allProfessorTypes = data; // Store the data in the allProfessorTypes variable
            console.log(allProfessorTypes);
        })
        .catch(error => {
            console.error('Error fetching professors:', error);
        });
}

// Dynamically add day buttons with Bootstrap classes
document.querySelectorAll('.day-button').forEach(button => {
    button.addEventListener('click', function () {
        // Toggle 'active' class to highlight the button with Bootstrap style
        this.classList.toggle('active');
        this.classList.toggle('btn-primary'); // Bootstrap active class
        // Add logic to handle the selection of day
    });
});

// Function to display suggestions
function searchProfessor() {
    const input = document.getElementById('professorName').value.toLowerCase();
    const filteredProfessors = allProfessors.filter(professor =>
        professor.name.toLowerCase().includes(input)
    );
    showSuggestionsforProf(filteredProfessors);
}

// Function to show suggestions in the suggestion box with Bootstrap classes
function showSuggestionsforProf(suggestions) {
    const suggestionBox = document.getElementById('suggestionBox');
    suggestionBox.innerHTML = '';
    suggestions.forEach(professor => {
        const div = document.createElement('div');
        div.classList.add('list-group-item', 'list-group-item-action'); // Bootstrap classes
        div.textContent = professor.name;
        div.onclick = function () {
            selectProfessor(professor);
        };
        suggestionBox.appendChild(div);
    });
    suggestionBox.style.display = suggestions.length > 0 ? 'block' : 'none';
}

function showSuggestionsforProfType() {
    const suggestionBoxProfType = document.getElementById('suggestionBoxProfType');
    suggestionBoxProfType.innerHTML = '';
    allProfessorTypes.forEach(professor => {
        const div = document.createElement('div');
        div.classList.add('list-group-item', 'list-group-item-action'); // Bootstrap classes
        div.textContent = professor.type;
        div.onclick = function () {
            selectProfessorType(professor);
        };
        suggestionBoxProfType.appendChild(div);
    });
    suggestionBoxProfType.style.display = allProfessorTypes.length > 0 ? 'block' : 'none';
}

function selectProfessorType(professor) {
    document.getElementById('profType').value = professor.type;
    document.getElementById('profTypeId').value = professor.id;
    document.getElementById('suggestionBoxProfType').style.display = 'none';
}

function selectProfessor(professor) {
    document.getElementById('professorName').value = professor.name;
    document.getElementById('courseLoad').value = professor.courseLoad;
    document.getElementById('profType').value = professor.professorTypeName;
    populateAvailabilities(professor.availabilities);
    document.getElementById('suggestionBox').style.display = 'none';
}

function populateAvailabilities(availabilities) {
    const timeSlotsDiv = document.getElementById('timeSlotsContainer');
    timeSlotsDiv.innerHTML = '';
    availabilities.forEach(availability => {
        const timeSlotRow = document.createElement('div');
        timeSlotRow.classList.add('row', 'mb-2'); // Bootstrap row with margin
        timeSlotRow.innerHTML = `
            <div class="col-3">
                <input type="text" class="form-control" placeholder="Day of the Week" value="${availability.dayOfWeek}" />
            </div>
            <div class="col-3">
                <input type="time" class="form-control" value="${availability.startTime}" />
            </div>
            <div class="col-3">
                <input type="time" class="form-control" value="${availability.endTime}" />
            </div>
            <div class="col-3">
                <button class="btn btn-danger" onclick="removeTimeSlot(this)">Remove</button>
            </div>
        `;
        timeSlotsDiv.appendChild(timeSlotRow);
    });
}



function displayProfessors() {
    const container = document.getElementById('professorsContainer');
    container.innerHTML = '';
    allProfessors.forEach(professor => {
        const row = document.createElement('div');
        row.classList.add('row', 'mb-2'); // Bootstrap classes for rows and margin
        const nameCol = document.createElement('div');
        nameCol.classList.add('col');
        nameCol.textContent = professor.name;
        row.appendChild(nameCol);

        const buttonCol = document.createElement('div');
        buttonCol.classList.add('col', 'text-end');
        row.appendChild(buttonCol);
        container.appendChild(row);
    });
}

function toggleDaySelection(dayId) {
    var element = document.getElementById('label' + dayId.charAt(0).toUpperCase() + dayId.slice(1));
    element.classList.toggle('active-day'); // You'll need to define this class in your CSS
}



// Function to dynamically add new time slots for professor availability
// JavaScript Function to add new time slots
function addAvailabilitySlot() {
    let timeSlotsDiv = document.getElementById('timeSlotsContainer');
    let index = timeSlotsDiv.children.length;

    let newTimeSlotDiv = document.createElement('div');
    newTimeSlotDiv.classList.add('row', 'align-items-center', 'mb-3');

    let days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
    let buttonsHtml = days.map(day => `<button class="btn btn-outline-primary" data-day="${day.toLowerCase()}">${day}</button>`).join('');


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

    newTimeSlotDiv.querySelectorAll('.btn-outline-primary').forEach(button => {
        button.addEventListener('click', function () {
            toggleDaySelection(this);
        });
    });
}


function removeTimeSlot(element) {
    element.closest('.row').remove();
}

function toggleDaySelection(button) {
    button.classList.toggle('active');
    button.classList.toggle('btn-primary'); // Bootstrap active class
}


function submitProfessorDetails(event) {
    event.preventDefault();  // Prevent page reload

    // Update the currentProfessorDetails object with the form data
    currentProfessorDetails = {
        name: document.getElementById('professorName').value,
        courseLoad: document.getElementById('courseLoad').value,
        profType: document.getElementById('profTypeId').value,
        availabilities: [] // This will be populated below
    };

    // Get all the availability slots
    const timeSlotRows = document.getElementById('timeSlotsContainer').querySelectorAll('.row');
    timeSlotRows.forEach(row => {
        const activeButton = row.querySelector('button.active');
        if (activeButton) {
            console.log(activeButton.textContent);
            const dayOfWeek = activeButton.textContent; // Assuming active button has the day
            const timeInputs = row.querySelectorAll('input[type="time"]');
            const startTime = timeInputs[0].value; // First time input is start time
            const endTime = timeInputs[1].value; // Second time input is end time
            currentProfessorDetails.availabilities.push({ dayOfWeek, startTime, endTime });
        }
    });

    const apiUrl = 'http://localhost:8080/submitProfessorDetails';

    // Perform the API call to submit professor details
    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(currentProfessorDetails), // Convert the currentProfessorDetails object to a JSON string
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            // Handle success response (e.g., displaying a success message)
            alert('Professor details submitted successfully!');
        })
        .catch((error) => {
            console.error('Error:', error);
            // Handle errors here (e.g., displaying an error message)
            alert('An error occurred while submitting professor details.');
        });

    console.log(currentProfessorDetails);
    clearFields();
}

function clearFields() {
    document.getElementById('professorName').value = '';
    document.getElementById('courseLoad').value = '';
    document.getElementById('profType').value = '';
    const timeSlotsContainer = document.getElementById('timeSlotsContainer');
    while (timeSlotsContainer.firstChild) {
        timeSlotsContainer.removeChild(timeSlotsContainer.firstChild);
    }
}

// Bind the event listener to the form submit event
document.getElementById('professorForm').addEventListener('submit', function (event) {
    event.preventDefault();
});