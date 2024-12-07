document.addEventListener("DOMContentLoaded", function () {
    const selectSemester = document.getElementById("selectSemester");
    const submitButton = document.querySelector("button[type='submit']");

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
        data.previousSemList.forEach(sem => {
            const option = document.createElement("option");
            option.value = sem.semId;
            option.textContent = sem.semName;
            selectSemester.appendChild(option);
        });

        // Enable button when a semester is selected
        selectSemester.addEventListener("change", function () {
            submitButton.disabled = selectSemester.value === "";
        });
    })
    .catch(error => console.error('Error fetching semester list:', error));

    const copySemesterForm = document.getElementById("copySemesterForm");

    copySemesterForm.addEventListener("submit", function (event) {
        event.preventDefault();

        const oldSemId = selectSemester.value;
        const newSemId = semId;

        const copyData = {
            oldSemId: oldSemId,
            newSemId: newSemId
        };

        fetch('http://localhost:8080/copySemester', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(copyData)
        })
        .then(response => response.json())
        .then(data => {
            if (data.responseMessage === "success") {
                window.location.href = `courseAndsectionList.html?semId=${newSemId}`;
            } else {
                alert("Copy Semester failed. Please try again.");
            }
        })
        .catch(error => console.error('Error copying semester:', error));
    });
});
