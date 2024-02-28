
let allProfessors = [];

function fetchAllProfessors() {
    return fetch('http://localhost:8080/getProfessors')
        .then(response => response.json())
        .then(data => {
            allProfessors = data; // Store the data in the allProfessors variable
            displayProfessors();
        })
        .catch(error => {
            console.error('Error fetching professors:', error);
        });
}

fetchAllProfessors().then(() => {
    // This will run after the fetchAllProfessors promise resolves
    console.log(allProfessors); // Should now have the fetched data
});

document.querySelectorAll('.day-button').forEach(button => {
    button.addEventListener('click', function() {
        // Toggle 'active' class to highlight the button
        this.classList.toggle('active');
        // Add logic to handle the selection of day
    });
});


// Function to display suggestions
function searchProfessor() {
    var input = document.getElementById('professorName').value.toLowerCase();
    var filteredProfessors = allProfessors.filter(professor =>
        professor.name.toLowerCase().includes(input)
    );
    showSuggestions(filteredProfessors);
}

// Function to show suggestions in the suggestion box
function showSuggestions(suggestions) {
    var suggestionBox = document.getElementById('suggestionBox');
    suggestionBox.innerHTML = '';
    suggestions.forEach(professor => {
        var div = document.createElement('div');
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
    var timeSlotsDiv = document.getElementById('timeSlots');
    timeSlotsDiv.innerHTML = '';
    availabilities.forEach(availability => {
        var newTimeSlotDiv = document.createElement('div');
        newTimeSlotDiv.innerHTML = `
            <input type="text" placeholder="Day of the Week" value="${availability.dayOfWeek}" />
            <input type="time" value="${availability.startTime}" />
            <input type="time" value="${availability.endTime}" />
            <button onclick="removeTimeSlot(this)">Remove</button>
        `;
        timeSlotsDiv.appendChild(newTimeSlotDiv);
    });
}

document.getElementById('professorName').addEventListener('input', searchProfessor);

function displayProfessors() {
    const container = document.getElementById('professorsContainer');
    container.innerHTML = ''; // Clear previous content
    allProfessors.forEach(professor => {
        const box = document.createElement('div');
        box.classList.add('professor-box');

        const nameElement = document.createElement('span');
        nameElement.classList.add('professor-name');
        nameElement.textContent = professor.name;
        box.appendChild(nameElement);

        // Container for buttons
        const buttonsContainer = document.createElement('div');
        buttonsContainer.classList.add('buttons-container');

        // Create Update button
        const updateButton = document.createElement('button');
        updateButton.textContent = 'Update';
        // TODO: Add event listeners for update functionality

        // Create Delete button
        const deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        // TODO: Add event listeners for delete functionality

        buttonsContainer.appendChild(updateButton);
        buttonsContainer.appendChild(deleteButton);

        box.appendChild(buttonsContainer);

        container.appendChild(box);
    });
}


// Function to dynamically add new time slots for professor availability
function addAvailabilitySlot() {
    var timeSlotsDiv = document.getElementById('timeSlots');
    var newTimeSlotDiv = document.createElement('div');
    newTimeSlotDiv.innerHTML = `
        <input type="text" placeholder="Day of the Week" />
        <input type="time" placeholder="Start Time" />
        <input type="time" placeholder="End Time" />
        <button onclick="removeTimeSlot(this)">Remove</button>
    `;
    timeSlotsDiv.appendChild(newTimeSlotDiv);
}

// Function to remove a time slot
function removeTimeSlot(button) {
    // Remove the parent div of the button
    button.parentElement.remove();
}

// Function to handle form submission for professor details
function submitProfessorDetails() {
    var professorName = document.getElementById('professorName').value;
    var courseLoad = document.getElementById('courseLoad').value;
    var profType = document.getElementById('profType').value;
    var timeSlots = document.querySelectorAll('#timeSlots div');
    var availability = Array.from(timeSlots).map(slot => {
        return {
            dayOfWeek: slot.querySelector('input[type="text"]').value,
            startTime: slot.querySelector('input[type="time"]:nth-child(2)').value,
            endTime: slot.querySelector('input[type="time"]:nth-child(3)').value
        };
    });

    var professorDetails = {
        name: professorName,
        courseLoad: courseLoad,
        type: profType,
        availability: availability
    };

}

document.getElementById('professorName').addEventListener('input', searchProfessor);
