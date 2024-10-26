document.addEventListener('DOMContentLoaded', function () {
    populateSemesters();
});

// Fetch and populate semester dropdown
async function populateSemesters() {
    const semesterSelect = document.getElementById('semester-select');
    
    // Add "Select" option as the default
    semesterSelect.innerHTML = '<option value="">Select</option>';

    try {
        const response = await fetch('http://localhost:8080/getSemesterDropDown');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const semesters = await response.json();
        
        // Log the fetched semesters
        console.log('Fetched Semesters:', semesters);
        
        Object.entries(semesters).forEach(([key, value]) => {
            const option = document.createElement('option');
            option.value = key;
            option.textContent = value;
            semesterSelect.appendChild(option);
        });

        // Only trigger professor loading when a semester is selected
        semesterSelect.addEventListener('change', function () {
            if (semesterSelect.value) {
                console.log('Selected Semester:', semesterSelect.value); // Log selected semester
                populateProfessors(semesterSelect.value); // Load professors when semester is selected
            }
        });

    } catch (error) {
        console.error('Failed to fetch semesters:', error);
    }
}

// Fetch and populate professors based on selected semester
async function populateProfessors(semesterId) {
    const professorSelect = document.getElementById('professor-select');
    professorSelect.innerHTML = '<option value="">Select</option>'; // Clear previous options

    try {
        const response = await fetch(`http://localhost:8080/reports/getProfReport/${semesterId}`);
        if (!response.ok) {
            throw new Error('Failed to fetch professors');
        }
        const { professorReportSchedule } = await response.json();

        // Log the fetched professor data
        console.log('Fetched Professors for Semester:', semesterId, professorReportSchedule);

        // Populate professor dropdown
        professorReportSchedule.forEach(professor => {
            const option = document.createElement('option');
            option.value = professor.id;
            option.textContent = professor.name;
            professorSelect.appendChild(option);
        });

        // Display all professors' schedules for the selected semester
        displayAllProfessors(professorReportSchedule);

        // Add event listener for filtering based on professor selection
        professorSelect.addEventListener('change', function () {
            const selectedProfessorId = professorSelect.value;
            console.log('Selected Professor ID:', selectedProfessorId); // Log selected professor ID
            if (selectedProfessorId) {
                displayProfessorSchedule(selectedProfessorId, professorReportSchedule);
            } else {
                displayAllProfessors(professorReportSchedule); // If no professor is selected, show all
            }
        });

    } catch (error) {
        console.error('Failed to fetch professors:', error);
    }
}

// Function to display all professors' schedules for the selected semester
function displayAllProfessors(professors) {
    const reportContent = document.getElementById('report-content');
    reportContent.innerHTML = ''; // Clear previous content

    console.log('Displaying all professors', professors); // Log professors being displayed

    professors.forEach(professor => {
        if (professor.schedule && Object.keys(professor.schedule).length > 0) {
            // Iterate over the days and display the schedule
            Object.entries(professor.schedule).forEach(([day, { occupied }]) => {
                const row = reportContent.insertRow();
                row.insertCell().textContent = professor.name;
                row.insertCell().textContent = day;
                row.insertCell().textContent = occupied || 'N/A'; // Only display "Occupied"
            });
        } else {
            // Display a fallback if no schedule is available
            const row = reportContent.insertRow();
            row.insertCell().textContent = professor.name;
            row.insertCell().textContent = "N/A"; // No day
            row.insertCell().textContent = "No schedule available";
        }
    });
}

// Function to display a single professor's schedule
function displayProfessorSchedule(professorId, professors) {
    const reportContent = document.getElementById('report-content');
    reportContent.innerHTML = ''; // Clear previous content

    const professor = professors.find(prof => prof.id === professorId);
    
    if (professor && professor.schedule) {
        console.log('Displaying schedule for professor', professor); // Log professor being displayed

        Object.entries(professor.schedule).forEach(([day, { occupied }]) => {
            const row = reportContent.insertRow();
            row.insertCell().textContent = professor.name;
            row.insertCell().textContent = day;
            row.insertCell().textContent = occupied || 'N/A';
        });
    } else {
        console.log('No matching professor found for ID or no schedule available:', professorId);
        reportContent.innerHTML = '<tr><td colspan="3">No data available for this professor.</td></tr>';
    }
}
