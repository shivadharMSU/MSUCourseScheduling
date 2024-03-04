// JavaScript (detailsManagement.js)

let allProfessors = [];
let currentProfessorDetails = {};
fetchAllProfessors();




document.getElementById('professorName').addEventListener('input', searchProfessor);

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
    showSuggestions(filteredProfessors);
}

// Function to show suggestions in the suggestion box with Bootstrap classes
function showSuggestions(suggestions) {
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
    let index = timeSlotsDiv.children.length + 1;

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

// Corrected removeTimeSlot function (unchanged, correctly targets '.row')
function removeTimeSlot(element) {
    element.closest('.row').remove();
}

function toggleDaySelection(button) {
    button.classList.toggle('active');
    button.classList.toggle('btn-primary'); // Bootstrap active class
}





// Function to handle form submission for professor details
function submitProfessorDetails() {
    // Update the currentProfessorDetails object with the form data
    currentProfessorDetails = {
        name: document.getElementById('professorName').value,
        courseLoad: document.getElementById('courseLoad').value,
        profType: document.getElementById('profType').value,
        availabilities: [] // This will be populated below
    };

    // Get all the availability slots
    const timeSlotRows = document.getElementById('timeSlotsContainer').querySelectorAll('.row');
    timeSlotRows.forEach(row => {
        const dayOfWeek = row.querySelector('button.active').textContent; // Assuming active button has the day
        const startTime = row.querySelector('input[type="time"]:first-child').value;
        const endTime = row.querySelector('input[type="time"]:last-child').value;
        currentProfessorDetails.availabilities.push({ dayOfWeek, startTime, endTime });
    });

    // TODO: Send currentProfessorDetails to the backend
    console.log(currentProfessorDetails);
}

// Bind the event listener to the form submit event
document.getElementById('professorForm').addEventListener('submit', function (event) {
    event.preventDefault();
    submitProfessorDetails();
});


