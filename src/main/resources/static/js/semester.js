document.addEventListener("DOMContentLoaded", function() {
  fetch('http://localhost:8080/getSemesterDetails')
  .then(response => response.json())
  .then(data => {
      renderSemesterDetails(data);
  })
  .catch(error => console.error('Error fetching semester details:', error));
});

function renderSemesterDetails(data) {
  const semesterDetailsContainer = document.getElementById('semester-details');

  data.forEach(year => {
      const table = document.createElement('table');
      table.classList.add('table', 'table-borderless', 'mb-4');

      const row = table.insertRow();
      const yearCell = row.insertCell();
      yearCell.classList.add('text-center', 'align-middle', 'w-25');
      yearCell.innerHTML = `<h3>${year.year}</h3>`;

      year.semesterList.forEach(semester => {
          const semesterCell = row.insertCell();
          semesterCell.classList.add('text-center', 'align-middle', 'w-25');
          
          if (semester === null) {
              // Display "+" sign in the color #C8102E
              semesterCell.innerHTML = '<p style="color: #C8102E;">+</p>';
          } else {
              // Append semName and year as query parameters
              semesterCell.innerHTML = `
                  <a 
                      style="color: #C8102E;" 
                      href="courseAndsectionList.html?semId=${semester.semId}&semName=${encodeURIComponent(semester.semName)}&year=${encodeURIComponent(year.year)}"
                  >
                      ${semester.semName}
                  </a>`;
          }
      });

      semesterDetailsContainer.appendChild(table);
  });
}
