document.addEventListener("DOMContentLoaded", function() {
    // Fetch room data and populate dropdown
    fetchData();
    fetchRooms();
    fetchProfessors();
        populateTimeDropdowns();
        
       
        
          // Event listener for start hour dropdown change
    document.getElementById("startHour").addEventListener("change", function() {
        const startHour = parseInt(this.value);
        const startMinuteDropdown = document.getElementById("startMinute");
        // Enable minute dropdown if hour selected
        startMinuteDropdown.disabled = isNaN(startHour);
    });

    // Event listener for end hour dropdown change
    document.getElementById("endHour").addEventListener("change", function() {
        const endHour = parseInt(this.value);
        const endMinuteDropdown = document.getElementById("endMinute");
        // Enable minute dropdown if hour selected
        endMinuteDropdown.disabled = isNaN(endHour);
    });

    // Event listener for start minute dropdown change
    document.getElementById("startMinute").addEventListener("change", function() {
        const startMinute = parseInt(this.value);
        const startAmPmDropdown = document.getElementById("startAmPm");
        // Enable AM/PM dropdown if minute selected
        startAmPmDropdown.disabled = isNaN(startMinute);
    });

    // Event listener for end minute dropdown change
    document.getElementById("endMinute").addEventListener("change", function() {
        const endMinute = parseInt(this.value);
        const endAmPmDropdown = document.getElementById("endAmPm");
        // Enable AM/PM dropdown if minute selected
        endAmPmDropdown.disabled = isNaN(endMinute);
    });

    // Event listener for end AM/PM dropdown change
    document.getElementById("endAmPm").addEventListener("change", function() {
        const endAmPm = this.value;
        // Call suggestion API when AM/PM selected
        if (endAmPm) {
            const startTime = document.getElementById("endHour").value + ":" + document.getElementById("endMinute").value + " " + endAmPm;
            fetchSuggestion(startTime);
        }
    });

    // Event listener for form submission
    document.getElementById("sectionForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent default form submission
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        // Construct JSON data from form inputs
        const formData = {
            
            "courseSemesterMappingId": urlParams.get('courseSemesterMappingId'),
            "courseId": urlParams.get('courseId'),
            // "sectionId": 54321,
            "professorId": document.getElementById("professor").value,
            "roomId": document.getElementById("roomId").value,
            "capacity": parseInt(document.getElementById("capacity").value),
            "maxCapacity": parseInt(document.getElementById("maxCapacity").value),
            "sectionNo": document.getElementById("sectionNo").value,
            "weekId": 1,
            "startTime": document.getElementById("startHour").value+':'+document.getElementById("startMinute").value+' '+document.getElementById("startAmPm").value,
            // "endTime": document.getElementById("endTime").value,
            "crossSectionId": document.getElementById("crossSectionId").value
        };

        // Send data to the server using fetch API
        fetch("http://localhost:8080/saveSctionSchedule", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                alert("Section saved successfully!");
                // Reset form after successful submission
                document.getElementById("sectionForm").reset();
            } else {
                throw new Error("Failed to save section");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Failed to save section");
        });
    });

    // Function to fetch room data and populate dropdown
    function fetchRooms() {
        fetch("http://localhost:8080/getClassListForDropDown", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "professorId": 9876,
                "semId": 3,
                "startTime": "10:00 AM",
                "endTime": "12:00 PM",
                "courseId": 67890
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Failed to fetch rooms");
            }
        })
        .then(data => {
            // Populate dropdown options with fetched data
            const roomDropdown = document.getElementById("roomId");
            data.forEach(room => {
                const option = document.createElement("option");
                option.value = room.id;
                option.textContent = room.roomName;
                roomDropdown.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Failed to fetch rooms");
        });
    }
    
    function fetchProfessors() {
        fetch("http://localhost:8080/getProfessorListForDropDown", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "courseId": 12345,
                "semId": 2,
                "weekDay": 3,
                "startTime": "09:00 AM",
                "endTime": "11:00 AM"
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Failed to fetch professors");
            }
        })
        .then(data => {
            // Populate dropdown options with fetched data
            const professorDropdown = document.getElementById("professor");
            data.forEach(professor => {
                const option = document.createElement("option");
                option.value = professor.proffessorId;
                option.textContent = professor.professorName;
                professorDropdown.appendChild(option);
            });
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Failed to fetch professors");
        });
    }
    
    function populateTimeDropdowns() {
        const hours = Array.from({ length: 12 }, (_, i) => i + 1);
        const minutes = Array.from({ length: 60 }, (_, i) => i);

        // Populate hours dropdowns
        ["startHour", "endHour"].forEach(id => {
            const dropdown = document.getElementById(id);
            hours.forEach(hour => {
                const option = new Option(hour.toString().padStart(2, "0"), hour.toString().padStart(2, "0"));
                dropdown.add(option);
            });
        });

        // Populate minutes dropdowns
        ["startMinute", "endMinute"].forEach(id => {
            const dropdown = document.getElementById(id);
            minutes.forEach(minute => {
                const option = new Option(minute.toString().padStart(2, "0"), minute.toString().padStart(2, "0"));
                dropdown.add(option);
            });
        });
    }
    
    
      document.getElementById("professor").addEventListener("change", function() {
        const professorId = this.value;
        // Call suggestion API and display suggestion
        fetchSuggestion(professorId);
    });

    // Function to fetch suggestion for the selected professor
    function fetchSuggestion(professorId) {
        fetch("http://localhost:8080/getSuggestion", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
				"createNew":true,
                "professorId": professorId
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Failed to fetch suggestion");
            }
        })
        .then(data => {
            // Display suggestion in the suggestion box
            const suggestionBox = document.getElementById("suggestionBox");
            suggestionBox.innerText = data.suggestion;
            // Show the suggestion box
            document.querySelector(".card").style.display = "block";
        })
        .catch(error => {
            console.error("Error:", error);
            // Hide suggestion box on error
            document.querySelector(".card").style.display = "none";
        });
    }
    function fetchData() {
            const queryString = window.location.search;
            const urlParams = new URLSearchParams(queryString);
            const courseId = urlParams.get('courseId');
            const courseSemesterMappingId = urlParams.get('courseSemesterMappingId');
            const courseName = urlParams.get('courseName');
            console.log(courseId);
             console.log(courseSemesterMappingId);
              console.log(courseName);
        }
    
});
