function fetchData() {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  const semId = urlParams.get("semId");

  fetch("http://localhost:8080/getCourseAndSemesterList", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({ semId: semId }),
  })
    .then((response) => response.json())
    .then((data) => displayCourses(data))
    .catch((error) => console.error("Error:", error));
}

function displayCourses(courses) {
  const coursesList = document.getElementById("coursesList");

  courses.forEach((course) => {
    const courseDiv = document.createElement("div");
    courseDiv.classList.add("card");

    const courseHeader = document.createElement("div");
    courseHeader.classList.add("card-header", "p-0");
    const courseButton = document.createElement("button");
    courseButton.classList.add("btn", "btn-link", "collapsed", "course-name"); // Adding 'course-name' class here
    courseButton.setAttribute("type", "button");
    courseButton.setAttribute("data-toggle", "collapse");
    courseButton.setAttribute(
      "data-target",
      `#courseCollapse${course.courseId}`
    );
    courseButton.setAttribute("aria-expanded", "false");
    courseButton.setAttribute(
      "aria-controls",
      `courseCollapse${course.courseId}`
    );
    courseButton.textContent = course.courseName;
    courseHeader.appendChild(courseButton);
    courseDiv.appendChild(courseHeader);

    const sectionDiv = document.createElement("div");
    sectionDiv.classList.add("collapse");
    sectionDiv.setAttribute("id", `courseCollapse${course.courseId}`);
    sectionDiv.setAttribute(
      "aria-labelledby",
      `courseCollapse${course.courseId}`
    );
    sectionDiv.setAttribute("data-parent", "#coursesList");
    const sectionCardBody = document.createElement("div");
    sectionCardBody.classList.add("card-body");
    const sectionList = document.createElement("ul");
    sectionList.classList.add("list-group", "list-group-flush");
    course.sectionList.forEach((section) => {
      const sectionItem = document.createElement("li");
      sectionItem.classList.add("list-group-item");
      sectionItem.textContent = `Section ${section.sectionNo} - Professor: ${section.professorName} - Time: ${section.dayAndTime}`;
      sectionList.appendChild(sectionItem);
    });
    sectionCardBody.appendChild(sectionList);
    sectionDiv.appendChild(sectionCardBody);
    courseDiv.appendChild(sectionDiv);

    coursesList.appendChild(courseDiv);
  });
}

// Call fetchData function when the page is loaded
document.addEventListener("DOMContentLoaded", fetchData);
