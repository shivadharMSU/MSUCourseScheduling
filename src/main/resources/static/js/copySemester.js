document.addEventListener("DOMContentLoaded", function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const semId = urlParams.get('semId');
    const data = {
        currentSemId: semId,
    };
    fetch('http://localhost:8080/getPreviousSemList', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
    .then(response => response.json())
    .then(data => {
        const selectSemester = document.getElementById("selectSemester");
        data.previousSemList.forEach(sem => {
            const option = document.createElement("option");
            option.value = sem.semId;
            option.textContent = sem.semName;
            selectSemester.appendChild(option);
        });
    })
    .catch(error => console.error('Error fetching semester list:', error));

    const copySemesterForm = document.getElementById("copySemesterForm");
    copySemesterForm.addEventListener("submit", function(event) {
        event.preventDefault();
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        const semId = urlParams.get('semId');
        const oldSemId =document.getElementById("selectSemester").value;
        const newSemId = semId;
        const data = {
            oldSemId: oldSemId,
            newSemId: newSemId
        };
        fetch('http://localhost:8080/copySemester', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
        .then(response => response.json())
        .then(data => {
            if (data.responseMessage === "success") {
                window.location.href = `courseAndsectionList.html?semId=${oldSemId}`;
            } else {
                alert("Copy Semester failed. Please try again.");
            }
        })
        .catch(error => console.error('Error copying semester:', error));
    });
});
