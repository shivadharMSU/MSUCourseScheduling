// sectionReport.js

let globalReportData = []; // Global variable to store report data

document.addEventListener('DOMContentLoaded', () => {
    // Populate semester dropdown on page load
    fetch('http://localhost:8080/getSemesterDropDown')
        .then(response => response.json())
        .then(data => {
            const semesterDropdown = document.getElementById('semesterDropdown');
            Object.entries(data).forEach(([id, name]) => {
                const option = document.createElement('option');
                option.value = id;
                option.textContent = name;
                semesterDropdown.appendChild(option);
            });
        })
        .catch(error => console.error('Error fetching semesters:', error));

    // Event listener for semester dropdown change
    document.getElementById('semesterDropdown').addEventListener('change', function () {
        const semesterId = this.value;
        if (semesterId) {
            fetchReportData(semesterId);
        } else {
            clearTable();
        }
    });

    // Event listeners for dropdown changes
    document.getElementById('courseDropdown').addEventListener('change', filterTable);
    document.getElementById('professorDropdown').addEventListener('change', filterTable);
    document.getElementById('classRoomDropdown').addEventListener('change', filterTable);
});

function fetchReportData(semesterId) {
    fetch(`http://localhost:8080/reports/getSectionReport/${semesterId}`)
        .then(response => response.json())
        .then(data => {
            globalReportData = data.sectionReportDchedule; // Store data in global variable
            populateReportTable(globalReportData);
            populateDropdowns(globalReportData);
        })
        .catch(error => console.error('Error fetching report data:', error));
}

function populateReportTable(reportData) {
    const tableBody = document.getElementById('reportTableBody');
    tableBody.innerHTML = '';

    reportData.forEach(row => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${row.courseName}</td>
            <td>${row.sectionNo}</td>
            <td>${row.prodessorName}</td>
            <td>${row.time}</td>
            <td>${row.classRoom}</td>
        `;
        tableBody.appendChild(tr);
    });
}

function populateDropdowns(reportData) {
    const courseSet = new Set();
    const professorSet = new Set();
    const classroomSet = new Set();

    reportData.forEach(row => {
        courseSet.add(row.courseName);
        professorSet.add(row.prodessorName);
        classroomSet.add(row.classRoom);
    });

    populateDropdown('courseDropdown', courseSet);
    populateDropdown('professorDropdown', professorSet);
    populateDropdown('classRoomDropdown', classroomSet);
}

function populateDropdown(dropdownId, dataSet) {
    const dropdown = document.getElementById(dropdownId);
    dropdown.innerHTML = '<option value="">Select</option>';
    dataSet.forEach(item => {
        const option = document.createElement('option');
        option.value = item;
        option.textContent = item;
        dropdown.appendChild(option);
    });
}

function clearTable() {
    document.getElementById('reportTableBody').innerHTML = '<tr><td colspan="5" class="text-center">Select a semester to view the report</td></tr>';
}

// Function to filter and update table based on dropdown selections
function filterTable() {
    const selectedCourse = document.getElementById('courseDropdown').value;
    const selectedProfessor = document.getElementById('professorDropdown').value;
    const selectedClassRoom = document.getElementById('classRoomDropdown').value;

    const filteredData = globalReportData.filter(row => {
        const matchesCourse = selectedCourse === '' || row.courseName === selectedCourse;
        const matchesProfessor = selectedProfessor === '' || row.prodessorName === selectedProfessor;
        const matchesClassRoom = selectedClassRoom === '' || row.classRoom === selectedClassRoom;
        return matchesCourse && matchesProfessor && matchesClassRoom;
    });

    populateReportTable(filteredData); // Update table with filtered data
}


// Existing code...

function generatePDF() {
    const { jsPDF } = window.jspdf;
    const doc = new jsPDF();

    // Select the table and sanitize it
    const table = document.getElementById('reportTable');
    const purifiedTable = DOMPurify.sanitize(table.outerHTML); // Sanitize HTML

    // Use html2canvas to render the table as an image
    html2canvas(table, { scale: 2 }).then(canvas => {
        const imgData = canvas.toDataURL('image/png'); // Convert canvas to image data
        const imgWidth = 180; // Width of the image in the PDF
        const imgHeight = (canvas.height * imgWidth) / canvas.width; // Scale height proportionally

        // Add image to PDF and save it
        doc.addImage(imgData, 'PNG', 10, 10, imgWidth, imgHeight);
        doc.save('Section_Report.pdf');
    }).catch(error => console.error('Error generating PDF:', error));
}

