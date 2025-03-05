document.addEventListener("DOMContentLoaded", function () {
  const selectCourse = document.getElementById("selectCourse");
  const tenureSelect = document.getElementById("tenure");
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

  // Fetch tenure options
  fetch("http://localhost:8080/coursedetails/getSemesterTenure", {
    method: "POST",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error(`Error fetching tenure list: ${response.statusText}`);
      }
      return response.json();
    })
    .then((data) => {
      data.forEach((tenure) => {
        const option = document.createElement("option");
        option.value = tenure.tenureId;
        option.textContent = tenure.weeklyTenure;
        tenureSelect.appendChild(option);
      });
    })
    .catch((error) => console.error("Error fetching tenure list:", error));

  // Enable submit button when both fields are filled
  function checkFormValidity() {
    submitButton.disabled = !(selectCourse.value && tenureSelect.value);
  }

  selectCourse.addEventListener("change", checkFormValidity);
  tenureSelect.addEventListener("change", checkFormValidity);

  // Form submission handler
  const addNewCourseForm = document.getElementById("addNewCourseForm");
  addNewCourseForm.addEventListener("submit", function (event) {
    event.preventDefault();

    const courseId = selectCourse.value;
    const tenureId = tenureSelect.value;
      console.log(tenureId);
    if (!courseId || !tenureId) {
      alert("Please select a course and tenure.");
      return;
    }

    const data = {
      courseId: courseId,
      semId: semId,
      tenure: tenureId,
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
