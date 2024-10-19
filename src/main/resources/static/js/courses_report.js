// Dummy JSON data with course details organized by semester
const data = {
    "semesters": [
        {"id": "fall-2024", "name": "Fall 2024"},
        {"id": "spring-2024", "name": "Spring 2024"},
        {"id": "summer-2024", "name": "Summer 2024"}
    ],
    "courses": {
        "fall-2024": [
            {
                "courseNumber": "CS101",
                "crn": "12345",
                "courseName": "Intro to Computer Science",
                "credits": 3,
                "computerRequired": "Yes"
            },
            {
                "courseNumber": "CS102",
                "crn": "12346",
                "courseName": "Data Structures",
                "credits": 4,
                "computerRequired": "Yes"
            }
        ],
        "spring-2024": [
            {
                "courseNumber": "CS201",
                "crn": "23456",
                "courseName": "Algorithms",
                "credits": 4,
                "computerRequired": "Yes"
            },
            {
                "courseNumber": "CS202",
                "crn": "23457",
                "courseName": "Operating Systems",
                "credits": 4,
                "computerRequired": "Yes"
            }
        ],
        "summer-2024": [
            {
                "courseNumber": "CS301",
                "crn": "34567",
                "courseName": "Advanced Java Programming",
                "credits": 3,
                "computerRequired": "Yes"
            },
            {
                "courseNumber": "CS302",
                "crn": "34568",
                "courseName": "Web Development",
                "credits": 3,
                "computerRequired": "Yes"
            }
        ]
    }
};

// Function to populate dropdowns
function populateDropdowns() {
    const semesterSelect = document.getElementById('semester-select');
    const searchInput = document.getElementById('search');

    // Populate Semester Dropdown
    data.semesters.forEach(semester => {
        const option = document.createElement('option');
        option.value = semester.id;
        option.textContent = semester.name;
        semesterSelect.appendChild(option);
    });

    // Add event listener to update the courses based on selected semester
    semesterSelect.addEventListener('change', function () {
        const selectedSemester = this.value;
        populateCourses(selectedSemester, searchInput.value);
    });

    // Add event listener for the search input
    searchInput.addEventListener('input', function () {
        const selectedSemester = semesterSelect.value;
        populateCourses(selectedSemester, this.value);
    });
}

// Function to populate course data based on selected semester and search query
function populateCourses(semesterId, searchQuery) {
    const reportContent = document.getElementById('report-content');
    reportContent.innerHTML = ''; // Clear previous content

    const courses = data.courses[semesterId];
    if (!courses) {
        console.log('No courses available for this semester.');
        return;
    }

    courses.forEach(course => {
        if (
            course.courseNumber.toLowerCase().includes(searchQuery.toLowerCase()) ||
            course.courseName.toLowerCase().includes(searchQuery.toLowerCase()) ||
            course.crn.toLowerCase().includes(searchQuery.toLowerCase())
        ) {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${course.courseNumber}</td>
                <td>${course.crn}</td>
                <td>${course.courseName}</td>
                <td>${course.credits}</td>
                <td>${course.computerRequired}</td>
            `;
            reportContent.appendChild(row);
        }
    });

    console.log('Courses displayed:', reportContent.children.length > 0 ? 'Yes' : 'No courses found');
}

// Call the function to populate the dropdowns when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', populateDropdowns);
