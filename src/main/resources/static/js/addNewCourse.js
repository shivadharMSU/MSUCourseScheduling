document.addEventListener("DOMContentLoaded", function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const semId = urlParams.get('semId');

    // Fetch courses not included in the semester
    fetch(`http://localhost:8080/getCourseDropDownNotIncludedForSemesterList/${semId}`, {
        method: 'GET',
    })
    .then(response => {
        // Check if the response is OK and if the content-type is JSON
        if (!response.ok) {
            throw new Error(`Error fetching course list: ${response.statusText}`);
        }
        const contentType = response.headers.get('content-type');
        if (contentType && contentType.includes('application/json')) {
            return response.json();
        } else {
            throw new Error('Response is not in JSON format');
        }
    })
    .then(data => {
        const selectSemester = document.getElementById("selectCourse");
        if (data && data.courseDropDownForSemester && Array.isArray(data.courseDropDownForSemester)) {
            data.courseDropDownForSemester.forEach(sem => {
                const option = document.createElement("option");
                option.value = sem.courseId;
                option.textContent = sem.courseName;
                selectSemester.appendChild(option);
            });
        } else {
            console.error('Unexpected data format:', data);
        }
    })
    .catch(error => console.error('Error fetching semester list:', error));

    // Form submission handler
    const copySemesterForm = document.getElementById("copySemesterForm");
    copySemesterForm.addEventListener("submit", function(event) {
        event.preventDefault();
        
        const courseId = document.getElementById("selectCourse").value;
        const tenure = document.getElementById("tenure").value;
         console.log(tenure);
        // Check if fields are filled properly
        if (!courseId || !tenure) {
            alert('Please select a course and enter tenure.');
            return;
        }

        const data = {
            courseId: courseId,
            semId: semId,
            tenure: tenure
        };

        // Post request to save course for the semester
        fetch('http://localhost:8080/saveCourseForSemester', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            if (data.responseMessage === "success") {
                window.location.href = `courseAndsectionList.html?semId=${semId}`;
            } else {
                alert("Copy Semester failed. Please try again.");
            }
        })
        .catch(error => console.error('Error copying semester:', error));
    });
});
