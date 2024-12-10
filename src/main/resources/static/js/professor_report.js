document.addEventListener("DOMContentLoaded", function () {
  populateSemesters();
});

// Fetch and populate semester dropdown
async function populateSemesters() {
  const semesterSelect = document.getElementById("semester-select");
  semesterSelect.innerHTML = '<option value="">Select</option>';

  try {
    const response = await fetch("http://localhost:8080/getSemesterDropDown");
    if (!response.ok) throw new Error("Network response was not ok");
    const semesters = await response.json();

    Object.entries(semesters).forEach(([key, value]) => {
      const option = document.createElement("option");
      option.value = key;
      option.textContent = value;
      semesterSelect.appendChild(option);
    });

    semesterSelect.addEventListener("change", function () {
      if (semesterSelect.value) {
        populateProfessors(semesterSelect.value);
      }
    });
  } catch (error) {
    console.error("Failed to fetch semesters:", error);
  }
}

// Fetch and populate professors based on selected semester
async function populateProfessors(semesterId) {
  const professorSelect = document.getElementById("professor-select");
  professorSelect.innerHTML = '<option value="">Select</option>';

  try {
    const response = await fetch(
      `http://localhost:8080/reports/getProfReport/${semesterId}`
    );
    if (!response.ok) throw new Error("Failed to fetch professors");
    const { professorReportSchedule } = await response.json();

    professorReportSchedule.forEach((professor) => {
      if (
        professor.schedule &&
        Object.values(professor.schedule).some((day) => day && day.occupied)
      ) {
        const option = document.createElement("option");
        option.value = professor.id;
        option.textContent = professor.name;
        professorSelect.appendChild(option);
      }
    });

    displayAllProfessors(professorReportSchedule);

    professorSelect.addEventListener("change", function () {
      if (professorSelect.value) {
        displayProfessorSchedule(
          professorSelect.value,
          professorReportSchedule
        );
      } else {
        displayAllProfessors(professorReportSchedule);
      }
    });
  } catch (error) {
    console.error("Failed to fetch professors:", error);
  }
}

// Display all professors' schedules for the selected semester
function displayAllProfessors(professors) {
  const reportContent = document.getElementById("report-content");
  reportContent.innerHTML = ""; // Clear previous rows

  professors.forEach((professor) => {
    if (professor.schedule && Object.keys(professor.schedule).length > 0) {
      let firstRow = true; // Track the first row for rowspan
      const scheduleEntries = Object.entries(professor.schedule);

      scheduleEntries.forEach(([day, schedule]) => {
        if (schedule && schedule.occupied) {
          const row = reportContent.insertRow();

          // Only add the professor's name in the first row and span across their schedule rows
          if (firstRow) {
            const nameCell = document.createElement("td");
            nameCell.textContent = professor.name;
            nameCell.rowSpan = scheduleEntries.length; // Span across all rows for this professor
            row.appendChild(nameCell);
            firstRow = false;
          }

          // Add day and occupied columns
          const dayCell = document.createElement("td");
          dayCell.textContent = day || "N/A";
          row.appendChild(dayCell);

          const occupiedCell = document.createElement("td");
          occupiedCell.textContent = schedule.occupied || "N/A";
          row.appendChild(occupiedCell);
        }
      });
    } else {
      const row = reportContent.insertRow();
      const nameCell = document.createElement("td");
      nameCell.textContent = professor.name;
      nameCell.colSpan = 3;
      row.appendChild(nameCell);
    }
  });
}

// Display a single professor's schedule
function displayProfessorSchedule(professorId, professors) {
  const reportContent = document.getElementById("report-content");
  reportContent.innerHTML = ""; // Clear previous rows

  const professor = professors.find((prof) => prof.id == professorId);
  if (professor && professor.schedule) {
    let firstRow = true;
    const scheduleEntries = Object.entries(professor.schedule);

    scheduleEntries.forEach(([day, schedule]) => {
      if (schedule && schedule.occupied) {
        const row = reportContent.insertRow();

        // Add professor's name only in the first row
        if (firstRow) {
          const nameCell = document.createElement("td");
          nameCell.textContent = professor.name;
          nameCell.rowSpan = scheduleEntries.length; // Span across all rows
          row.appendChild(nameCell);
          firstRow = false;
        }

        // Add day and occupied columns
        const dayCell = document.createElement("td");
        dayCell.textContent = day || "N/A";
        row.appendChild(dayCell);

        const occupiedCell = document.createElement("td");
        occupiedCell.textContent = schedule.occupied || "N/A";
        row.appendChild(occupiedCell);
      }
    });
  } else {
    reportContent.innerHTML =
      '<tr><td colspan="3">No data available for this professor.</td></tr>';
  }
}

// Export to PDF
document.getElementById("export-pdf").addEventListener("click", function () {
  const { jsPDF } = window.jspdf;
  const doc = new jsPDF({ orientation: "landscape" }); // Ensure landscape orientation

  doc.setFontSize(18);
  doc.text("Professor's Report", 10, 10);

  // Capture table content
  const tableData = [];
  const table = document.querySelector("#report-content");
  const rows = table.querySelectorAll("tr");

  rows.forEach((row) => {
    const columns = row.querySelectorAll("td");
    const rowData = [];
    columns.forEach((column) => {
      rowData.push(column.textContent);
    });
    tableData.push(rowData);
  });

  // Define the table header
  const headers = [["Professor Name", "Day", "Occupied"]];

  // Add autoTable to the PDF
  doc.autoTable({
    head: headers,
    body: tableData,
    startY: 20,
    theme: "grid",
    headStyles: {
      fillColor: [200, 16, 46], // RGB for #C8102E
      textColor: [255, 255, 255], // White text
    },
    styles: {
      fontSize: 10,
      overflow: "linebreak",
    },
    columnStyles: {
      0: { cellWidth: 80 }, // Adjusted column width for Professor Name
      1: { cellWidth: 40 }, // Adjusted column width for Day
      2: { cellWidth: 140 }, // Adjusted column width for Occupied
    },
  });

  // Save the PDF
  doc.save("professor-report-landscape.pdf");
});
