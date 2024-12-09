document.addEventListener('DOMContentLoaded', function () {
    populateSemesters();
});

// Day mapping: numbers to day names
const dayMapping = {
    1: 'Monday',
    2: 'Tuesday',
    3: 'Wednesday',
    4: 'Thursday',
    5: 'Friday',
    6: 'Saturday',
    7: 'Sunday',
};

// Fetch and populate the semester dropdown
async function populateSemesters() {
    const semesterSelect = document.getElementById('semester-select');
    semesterSelect.innerHTML = '<option value="">Select</option>';

    try {
        const response = await fetch('http://localhost:8080/getSemesterDropDown');
        if (!response.ok) throw new Error('Failed to fetch semesters');
        const semesters = await response.json();

        // Populate the semester dropdown
        Object.entries(semesters).forEach(([key, value]) => {
            const option = document.createElement('option');
            option.value = key;
            option.textContent = value;
            semesterSelect.appendChild(option);
        });

        // Fetch classrooms when a semester is selected
        semesterSelect.addEventListener('change', function () {
            if (semesterSelect.value) {
                populateClassrooms(semesterSelect.value);
            }
        });
    } catch (error) {
        console.error('Error fetching semesters:', error);
    }
}

// Fetch and display classrooms for the selected semester
async function populateClassrooms(semesterId) {
    const reportContent = document.getElementById('report-content');
    const classroomSelect = document.getElementById('classroom-select');
    const searchInput = document.getElementById('search');
    classroomSelect.innerHTML = '<option value="">Select</option>'; // Reset classroom dropdown
    reportContent.innerHTML = ''; // Clear the table content

    try {
        const response = await fetch(`http://localhost:8080/classrooms/report/${semesterId}`);
        if (!response.ok) throw new Error('Failed to fetch classrooms');
        const { classrooms } = await response.json();

        console.log('Fetched Classrooms:', classrooms);

        // Populate the classroom dropdown
        classrooms.forEach(classroom => {
            const option = document.createElement('option');
            option.value = classroom.roomId;
            option.textContent = classroom.roomName;
            classroomSelect.appendChild(option);
        });

        // Display all classrooms initially
        displayClassrooms(classrooms);

        // Filter classrooms based on the search input
        searchInput.addEventListener('input', function () {
            const query = searchInput.value.toLowerCase();
            const filteredClassrooms = classrooms.filter(classroom =>
                classroom.roomName.toLowerCase().includes(query)
            );
            displayClassrooms(filteredClassrooms);
        });

        // Filter classrooms based on selected classroom
        classroomSelect.addEventListener('change', function () {
            const selectedClassroomId = classroomSelect.value;
            if (selectedClassroomId) {
                const filteredClassrooms = classrooms.filter(classroom =>
                    classroom.roomId.toString() === selectedClassroomId
                );
                displayClassrooms(filteredClassrooms);
            } else {
                displayClassrooms(classrooms); // Show all classrooms if "Select" is chosen
            }
        });
    } catch (error) {
        console.error('Error fetching classrooms:', error);
    }
}

// Display classrooms in the table
function displayClassrooms(classrooms) {
    const reportContent = document.getElementById('report-content');
    reportContent.innerHTML = ''; // Clear previous rows

    classrooms.forEach(classroom => {
        if (classroom.schedule && classroom.schedule.length > 0) {
            classroom.schedule.forEach(schedule => {
                const row = reportContent.insertRow();
                row.insertCell().textContent = classroom.roomName || 'N/A';
                row.insertCell().textContent = dayMapping[schedule.day] || 'N/A'; // Map day number to day name
                row.insertCell().textContent = `${schedule.startTime || 'N/A'} - ${schedule.endTime || 'N/A'}`; // Occupied times
                row.insertCell().textContent = schedule.professorName || 'N/A';
                row.insertCell().textContent = `${schedule.courseName || 'N/A'}_${schedule.sectionNo || 'N/A'}`;
            });
        } else {
            const row = reportContent.insertRow();
            row.insertCell().textContent = classroom.roomName || 'N/A';
            row.insertCell().textContent = 'N/A'; // Day
            row.insertCell().textContent = 'No schedule available'; // Occupied
            row.insertCell().textContent = 'N/A'; // Professor's Name
            row.insertCell().textContent = 'N/A'; // Course
        }
    });
}

// Export the classroom report to PDF
document.getElementById('export-pdf').addEventListener('click', function () {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    doc.setFontSize(18);
    doc.text('Classroom Report', 10, 10);

    doc.autoTable({
        head: [['Classroom Name', 'Day', 'Occupied', 'Professor Name', 'Course']],
        body: Array.from(document.querySelectorAll('#report-content tr')).map(row =>
            Array.from(row.cells).map(cell => cell.innerText)
        ),
        startY: 20,
    });

    doc.save('classroom-report.pdf');
});
