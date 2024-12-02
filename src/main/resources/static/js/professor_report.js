document.addEventListener('DOMContentLoaded', function () {
    populateSemesters();
});

// Fetch and populate semester dropdown
async function populateSemesters() {
    const semesterSelect = document.getElementById('semester-select');
    semesterSelect.innerHTML = '<option value="">Select</option>';

    try {
        const response = await fetch('http://localhost:8080/getSemesterDropDown');
        if (!response.ok) throw new Error('Network response was not ok');
        const semesters = await response.json();
        
        Object.entries(semesters).forEach(([key, value]) => {
            const option = document.createElement('option');
            option.value = key;
            option.textContent = value;
            semesterSelect.appendChild(option);
        });

        semesterSelect.addEventListener('change', function () {
            if (semesterSelect.value) {
                populateProfessors(semesterSelect.value);
            }
        });
    } catch (error) {
        console.error('Failed to fetch semesters:', error);
    }
}

// Fetch and populate professors based on selected semester
async function populateProfessors(semesterId) {
    const professorSelect = document.getElementById('professor-select');
    professorSelect.innerHTML = '<option value="">Select</option>';

    try {
        const response = await fetch(`http://localhost:8080/reports/getProfReport/${semesterId}`);
        if (!response.ok) throw new Error('Failed to fetch professors');
        const { professorReportSchedule } = await response.json();

        professorReportSchedule.forEach(professor => {
            if (professor.schedule && Object.values(professor.schedule).some(day => day && day.occupied)) {
                const option = document.createElement('option');
                option.value = professor.id;
                option.textContent = professor.name;
                professorSelect.appendChild(option);
            }
        });

        displayAllProfessors(professorReportSchedule);

        professorSelect.addEventListener('change', function () {
            if (professorSelect.value) {
                displayProfessorSchedule(professorSelect.value, professorReportSchedule);
            } else {
                displayAllProfessors(professorReportSchedule);
            }
        });
    } catch (error) {
        console.error('Failed to fetch professors:', error);
    }
}

// Display all professors' schedules for the selected semester
function displayAllProfessors(professors) {
    const reportContent = document.getElementById('report-content');
    reportContent.innerHTML = '';

    professors.forEach(professor => {
        if (professor.schedule) {
            Object.entries(professor.schedule).forEach(([day, schedule]) => {
                if (schedule && schedule.occupied) {
                    const row = reportContent.insertRow();
                    row.insertCell().textContent = professor.name;
                    row.insertCell().textContent = day;
                    row.insertCell().textContent = schedule.occupied;
                }
            });
        }
    });
}

// Display a single professor's schedule
function displayProfessorSchedule(professorId, professors) {
    const reportContent = document.getElementById('report-content');
    reportContent.innerHTML = '';

    const professor = professors.find(prof => prof.id == professorId);
    if (professor && professor.schedule) {
        Object.entries(professor.schedule).forEach(([day, schedule]) => {
            if (schedule && schedule.occupied) {
                const row = reportContent.insertRow();
                row.insertCell().textContent = professor.name;
                row.insertCell().textContent = day;
                row.insertCell().textContent = schedule.occupied;
            }
        });
    }
}

// Export to PDF
document.getElementById('export-pdf').addEventListener('click', function () {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    doc.setFontSize(18);
    doc.text("Professor's Report", 10, 10);

    doc.autoTable({
        head: [['Professor Name', 'Day', 'Occupied']],
        body: Array.from(document.querySelectorAll('#report-content tr')).map(row => 
            Array.from(row.cells).map(cell => cell.innerText)
        ),
        startY: 20
    });

    doc.save('professor-report.pdf');
});
