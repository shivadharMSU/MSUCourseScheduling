document.addEventListener("DOMContentLoaded", function () {
  var yearSelect = document.getElementById("year");
  var semesterSelect = document.getElementById("semester");
  var submitButton = document.querySelector("button[type='submit']");

  var currentYear = new Date().getFullYear();

  // Populating years from current year to next 5 years
  for (var i = 0; i < 5; i++) {
    var option = document.createElement("option");
    option.value = currentYear + i;
    option.textContent = currentYear + i;
    yearSelect.appendChild(option);
  }

  function validateForm() {
    var yearSelected = yearSelect.value !== "";
    var semesterSelected = semesterSelect.value !== "";
    submitButton.disabled = !(yearSelected && semesterSelected);
  }

  // Validate form on dropdown changes
  yearSelect.addEventListener("change", validateForm);
  semesterSelect.addEventListener("change", validateForm);

  var form = document.getElementById("semesterForm");

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    var year = yearSelect.value;
    var semester = semesterSelect.value;

    var data = {
      year: parseInt(year),
      semNameId: parseInt(semester),
    };

    fetch("http://localhost:8080/createSemester", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Success:", data);
        if (data.responseMessage === "success") {
          window.location.href = "semester.html"; // Redirect to semester.html if success
        } else {
          alert("Failure: Request was unsuccessful!"); // Show alert if response is failure
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("Error: Request failed!"); // Show alert if there's an error
      });
  });
});
