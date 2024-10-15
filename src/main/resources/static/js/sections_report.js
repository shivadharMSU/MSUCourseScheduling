// Dummy JSON data with section details organized by semester and course
const data = {
    "semesters": [
        {"id": "fall-2024", "name": "Fall 2024"},
        {"id": "spring-2024", "name": "Spring 2024"},
        {"id": "summer-2024", "name": "Summer 2024"}
    ],
    "sections": {
        "fall-2024": [
            {
                "courseName": "Java Programming",
                "sectionNo": "01",
                "capacity": 50,
                "maxCapacity": 100,
                "professor": "Dr. John Smith",
                "day": "Wed",
                "startTime": "10:00 AM",
                "endTime": "12:00 PM"
            },
            {
                "courseName": "Data Structures",
                "sectionNo": "02",
                "capacity": 40,
                "maxCapacity": 80,
                "professor": "Dr. Emily Johnson",
                "day": "Tue",
                "startTime": "1:00 PM",
                "endTime": "3:00 PM"
            }
        ],
        "spring-2024": [
            {
                "courseName": "Algorithms",
                "sectionNo": "01",
                "capacity": 60,
                "maxCapacity": 100,
                "professor": "Dr. Michael Williams",
                "day": "Mon",
                "startTime": "9:00 AM",
                "endTime": "11:00 AM"
            },
            {
                "courseName": "Operating Systems",
                "sectionNo": "01",
                "capacity": 50,
                "maxCapacity": 90,
                "professor": "Dr. Sarah Brown",
                "day": "Thu",
                "startTime": "10:00 AM",
                "endTime": "12:00 PM"
            }
        ],
        "summer-2024": [
            {
                "courseName": "Advanced Java",
                "sectionNo": "01",
                "capacity": 30,
                "maxCapacity": 50,
                "professor": "Dr. David Jones",
                "day": "Fri",
                "startTime": "1:00 PM",
                "endTime": "3:00 PM"
            },
            {
                "courseName": "Web Development",
                "sectionNo": "01",
                "capacity": 25,
                "maxCapacity": 40,
                "professor": "Dr. Susan Clark",
                "day": "Tue",
                "startTime": "10:00 AM",
                "endTime": "12:00 PM"
            }
        ]
    }
};

// Function to populate dropdowns
function populateDropdowns() {
    const semesterSelect = document.getElementById('semester-select');
    const courseSelect = document.getElementById('course-select');
    const daySelect = document.getElementById('day-select');
    const searchInput = document.getElementById('search');

    // Populate Semester Dropdown
    data.semesters.forEach(semester => {
        const option = document.createElement('option');
        option.value = semester.id;
        option.textContent = semester.name;
        semesterSelect.appendChild(option);
    });

    // Add event listener to update the sections based on selected semester
    semesterSelect.addEventListener('change', function () {
        const selectedSemester = this.value;
        populateCourses(selectedSemester);
        populateSections(selectedSemester, courseSelect.value, daySelect.value, searchInput.value);
    });

    // Add event listener to update the sections based on selected course
    courseSelect.addEventListener('change', function () {
        const selectedSemester = semesterSelect.value;
        populateSections(selectedSemester, this.value, daySelect.value, searchInput.value);
    });

    // Add event listener to update the sections based on selected day
    daySelect.addEventListener('change', function () {
        const selectedSemester = semesterSelect.value;
        populateSections(selectedSemester, courseSelect.value, this.value, searchInput.value);
    });

    // Add event listener for the search input
    searchInput.addEventListener('input', function () {
        const selectedSemester = semesterSelect.value;
        populateSections(selectedSemester, courseSelect.value, daySelect.value, this.value);
    });
}

// Function to populate courses dropdown based on selected semester
function populateCourses(semesterId) {
    const courseSelect = document.getElementById('course-select');
    courseSelect.innerHTML = '<option value="">Select</option>'; // Clear previous options

    const sections = data.sections[semesterId];
    if (!sections) return;

    const uniqueCourses = [...new Set(sections.map(section => section.courseName))];
    uniqueCourses.forEach(courseName => {
        const option = document.createElement('option');
        option.value = courseName;
        option.textContent = courseName;
        courseSelect.appendChild(option);
    });
}

// Function to populate sections based on selected semester, course, day, and search query
function populateSections(semesterId, courseName, day, searchQuery) {
    const reportContent = document.getElementById('report-content');
    reportContent.innerHTML = ''; // Clear previous content

    const sections = data.sections[semesterId];
    if (!sections) {
        console.log('No sections available for this semester.');
        return;
    }

    sections.forEach(section => {
        if (
            (courseName === '' || section.courseName === courseName) &&
            (day === '' || section.day.toLowerCase() === day.toLowerCase()) &&
            (section.courseName.toLowerCase().includes(searchQuery.toLowerCase()) ||
            section.professor.toLowerCase().includes(searchQuery.toLowerCase()) ||
            section.sectionNo.toLowerCase().includes(searchQuery.toLowerCase()) ||
            section.startTime.toLowerCase().includes(searchQuery.toLowerCase()) ||
            section.endTime.toLowerCase().includes(searchQuery.toLowerCase()))
        ) {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${section.courseName}</td>
                <td>${section.sectionNo}</td>
                <td>${section.capacity}</td>
                <td>${section.maxCapacity}</td>
                <td>${section.professor}</td>
                <td>${section.day}</td>
                <td>${section.startTime}</td>
                <td>${section.endTime}</td>
            `;
            reportContent.appendChild(row);
        }
    });

    console.log('Sections displayed:', reportContent.children.length > 0 ? 'Yes' : 'No sections found');
}

// Call the function to populate the dropdowns when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', populateDropdowns);
