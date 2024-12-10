document.addEventListener("DOMContentLoaded", function () {
  const selectCourse = document.getElementById("selectCourse");
  const tenureInput = document.getElementById("tenure");
  const submitButton = document.querySelector("button[type='submit']");

  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const semId = urlParams.get("semId");

  // Fetch courses not included in the semester
  fetch(
    `http://localhost:8080/getCourseDropDownNotIncludedForSemesterList/${semId}`,
    {
      method: "GET",
    }
  )
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error fetching course list: ${response.statusText}`);
      }
      return response.json();
    })
    .then((data) => {
      if (
        data.courseDropDownForSemester &&
        Array.isArray(data.courseDropDownForSemester)
      ) {
        data.courseDropDownForSemester.forEach((course) => {
          const option = document.createElement("option");
          option.value = course.courseId;
          option.textContent = course.courseName;
          selectCourse.appendChild(option);
        });
      }
    })
    .catch((error) => console.error("Error fetching course list:", error));

  // Enable submit button when both fields are filled
  function checkFormValidity() {
    submitButton.disabled = !(selectCourse.value && tenureInput.value.trim());
  }

  selectCourse.addEventListener("change", checkFormValidity);
  tenureInput.addEventListener("input", checkFormValidity);

  // Form submission handler
  const addNewCourseForm = document.getElementById("addNewCourseForm");
  addNewCourseForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const courseId = selectCourse.value;
    const tenure = tenureInput.value.trim();

    if (!courseId || !tenure) {
      alert("Please select a course and enter the tenure.");
      return;
    }

    const data = {
      courseId: courseId,
      semId: semId,
      tenure: tenure,
    };

    fetch("http://localhost:8080/saveCourseForSemester", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => response.json())
      .then((data) => {
        if (data.responseMessage === "success") {
          window.location.href = `courseAndsectionList.html?semId=${semId}`;
        } else {
          alert("Save Course failed. Please try again.");
        }
      })
      .catch((error) => console.error("Error saving course:", error));
  });
});
