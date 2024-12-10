document.addEventListener("DOMContentLoaded", function () {
  const reportLinks = document.querySelectorAll(".report-list li a");

  reportLinks.forEach((link) => {
    link.addEventListener("click", function (e) {
      e.preventDefault();

      const reportName = this.textContent.trim();
      loadReport(reportName);
    });
  });

  function loadReport(reportName) {
    const container = document.querySelector(".container");
    const reportContent = document.createElement("div");
    reportContent.innerHTML = `<h3>${reportName} Report</h3><p>Content for ${reportName} report goes here...</p>`;
    container.innerHTML = ""; // Clear current content
    container.appendChild(reportContent); // Load new content
  }
});
