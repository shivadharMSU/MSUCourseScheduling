// Dummy JSON data with classroom names and schedules, organized by semester
const data = {
    "semesters": [
        {"id": "fall-2024", "name": "Fall 2024"},
        {"id": "spring-2024", "name": "Spring 2024"},
        {"id": "summer-2024", "name": "Summer 2024"}
    ],
    "classrooms": {
        "fall-2024": [
            {
                "id": "classroom-101",
                "name": "Classroom 101",
                "schedule": {
                    "Mon": {
                        "occupied": "Java - 10:00AM - 1:00PM",
                        "unoccupied": "8:00AM - 10:00AM, 1:00PM - 2:00PM"
                    },
                    "Tues": {
                        "occupied": "Python - 4:00PM - 6:00PM",
                        "unoccupied": "10:00AM - 12:00PM, 3:00PM - 5:00PM"
                    },
                    "Wed": {
                        "occupied": "Database - 11:00AM - 2:00PM, Web Security - 1:00PM - 5:00PM",
                        "unoccupied": ""
                    },
                    "Thurs": {
                        "occupied": "Java - 10:00AM - 1:00PM",
                        "unoccupied": ""
                    },
                    "Fri": {
                        "occupied": "",
                        "unoccupied": ""
                    }
                }
            },
            {
                "id": "classroom-102",
                "name": "Classroom 102",
                "schedule": {
                    "Mon": {
                        "occupied": "Python - 8:00AM - 11:00AM",
                        "unoccupied": ""
                    },
                    "Tues": {
                        "occupied": "Database - 4:00PM - 7:00PM",
                        "unoccupied": ""
                    },
                    "Wed": {
                        "occupied": "",
                        "unoccupied": ""
                    },
                    "Thurs": {
                        "occupied": "",
                        "unoccupied": ""
                    },
                    "Fri": {
                        "occupied": "",
                        "unoccupied": ""
                    }
                }
            }
        ],
        "spring-2024": [
            {
                "id": "classroom-201",
                "name": "Classroom 201",
                "schedule": {
                    "Mon": {
                        "occupied": "Algorithms - 9:00AM - 12:00PM",
                        "unoccupied": "12:00PM - 1:00PM"
                    },
                    "Tues": {
                        "occupied": "Data Structures - 2:00PM - 4:00PM",
                        "unoccupied": ""
                    },
                    "Wed": {
                        "occupied": "Operating Systems - 11:00AM - 2:00PM",
                        "unoccupied": ""
                    },
                    "Thurs": {
                        "occupied": "Networking - 10:00AM - 1:00PM",
                        "unoccupied": ""
                    },
                    "Fri": {
                        "occupied": "",
                        "unoccupied": ""
                    }
                }
            },
            {
                "id": "classroom-202",
                "name": "Classroom 202",
                "schedule": {
                    "Mon": {
                        "occupied": "Software Engineering - 10:00AM - 12:00PM",
                        "unoccupied": ""
                    },
                    "Tues": {
                        "occupied": "Artificial Intelligence - 1:00PM - 3:00PM",
                        "unoccupied": "3:00PM - 5:00PM"
                    },
                    "Wed": {
                        "occupied": "Machine Learning - 10:00AM - 12:00PM",
                        "unoccupied": ""
                    },
                    "Thurs": {
                        "occupied": "Deep Learning - 11:00AM - 2:00PM",
                        "unoccupied": ""
                    },
                    "Fri": {
                        "occupied": "",
                        "unoccupied": ""
                    }
                }
            }
        ],
        "summer-2024": [
            {
                "id": "classroom-301",
                "name": "Classroom 301",
                "schedule": {
                    "Mon": {
                        "occupied": "Advanced Java - 9:00AM - 12:00PM",
                        "unoccupied": ""
                    },
                    "Tues": {
                        "occupied": "Python - 1:00PM - 3:00PM",
                        "unoccupied": ""
                    },
                    "Wed": {
                        "occupied": "Database Systems - 10:00AM - 1:00PM",
                        "unoccupied": ""
                    },
                    "Thurs": {
                        "occupied": "",
                        "unoccupied": ""
                    },
                    "Fri": {
                        "occupied": "",
                        "unoccupied": ""
                    }
                }
            },
            {
                "id": "classroom-302",
                "name": "Classroom 302",
                "schedule": {
                    "Mon": {
                        "occupied": "Data Mining - 8:00AM - 11:00AM",
                        "unoccupied": ""
                    },
                    "Tues": {
                        "occupied": "Big Data - 2:00PM - 4:00PM",
                        "unoccupied": ""
                    },
                    "Wed": {
                        "occupied": "Cloud Computing - 9:00AM - 12:00PM",
                        "unoccupied": ""
                    },
                    "Thurs": {
                        "occupied": "Cybersecurity - 10:00AM - 12:00PM",
                        "unoccupied": ""
                    },
                    "Fri": {
                        "occupied": "",
                        "unoccupied": ""
                    }
                }
            }
        ]
    }
};

// Function to populate dropdowns
function populateDropdowns() {
    const semesterSelect = document.getElementById('semester-select');
    const classroomSelect = document.getElementById('classroom-select');
    const searchInput = document.getElementById('search');

    // Populate Semester Dropdown
    data.semesters.forEach(semester => {
        const option = document.createElement('option');
        option.value = semester.id;
        option.textContent = semester.name;
        semesterSelect.appendChild(option);
    });

    // Add event listener to update the classroom dropdown based on selected semester
    semesterSelect.addEventListener('change', function () {
        const selectedSemester = this.value;
        populateClassrooms(selectedSemester, searchInput.value);
    });

    // Add event listener for the search input
    searchInput.addEventListener('input', function () {
        const selectedSemester = semesterSelect.value;
        populateClassrooms(selectedSemester, this.value);
    });
}

// Function to populate classroom dropdown based on selected semester and search query
function populateClassrooms(semesterId, searchQuery) {
    const classroomSelect = document.getElementById('classroom-select');
    classroomSelect.innerHTML = '<option value="">Select</option>'; // Clear previous options

    const classrooms = data.classrooms[semesterId];
    if (!classrooms) {
        console.log('No classrooms available for this semester.');
        return;
    }

    classrooms.forEach(classroom => {
        if (classroom.name.toLowerCase().includes(searchQuery.toLowerCase())) {
            const option = document.createElement('option');
            option.value = classroom.id;
            option.textContent = classroom.name;
            classroomSelect.appendChild(option);
        }
    });

    console.log('Classrooms found:', classroomSelect.length > 1 ? 'Yes' : 'No classrooms found');

    // If there's only one classroom found and no option selected yet, auto-select that classroom
    if (classroomSelect.length === 2) {
        classroomSelect.selectedIndex = 1;
        displayClassroomSchedule(semesterId, classroomSelect.value);
    }

    // Add event listener to display schedule when a classroom is selected
    classroomSelect.addEventListener('change', function () {
        displayClassroomSchedule(semesterId, this.value);
    });
}

// Function to display classroom's schedule for the selected semester
function displayClassroomSchedule(semesterId, classroomId) {
    const classrooms = data.classrooms[semesterId];
    const classroom = classrooms.find(room => room.id === classroomId);
    const reportContent = document.getElementById('report-content');
    reportContent.innerHTML = ''; // Clear previous content

    if (classroom) {
        console.log('Selected Classroom:', classroom.name);
        let firstRow = true; // For handling rowspan in the first column
        for (const [day, schedule] of Object.entries(classroom.schedule)) {
            const row = document.createElement('tr');
            
            // Classroom name only appears in the first row and spans all rows for this classroom
            if (firstRow) {
                const classroomCell = document.createElement('td');
                classroomCell.textContent = classroom.name;
                classroomCell.rowSpan = Object.keys(classroom.schedule).length;
                row.appendChild(classroomCell);
                firstRow = false;
            }
            
            const dayCell = document.createElement('td');
            dayCell.textContent = day;
            row.appendChild(dayCell);

            const occupiedCell = document.createElement('td');
            occupiedCell.textContent = schedule.occupied || 'N/A';
            row.appendChild(occupiedCell);

            const unoccupiedCell = document.createElement('td');
            unoccupiedCell.textContent = schedule.unoccupied || 'N/A';
            row.appendChild(unoccupiedCell);

            reportContent.appendChild(row);
        }
    } else {
        console.log('No matching classroom found for ID:', classroomId);
        reportContent.innerHTML = '<tr><td colspan="4">No data available for this classroom.</td></tr>';
    }
}

// Call the function to populate the dropdowns when the DOM is fully loaded
document.addEventListener('DOMContentLoaded', populateDropdowns);
