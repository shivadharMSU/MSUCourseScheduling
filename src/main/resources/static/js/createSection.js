document.addEventListener("DOMContentLoaded", function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    // Fetch room data and populate dropdown
    fetchData();
    fetchRooms();
    fetchProfessors();
     
        
    document.getElementById("professor").innerHTML = '<option value="" selected>Select</option>';

    // Set default option for room dropdown
    document.getElementById("roomId").innerHTML = '<option value="" selected>Select</option>';

        
    const addTimeSlotButton = document.getElementById("onAvailable");
    addTimeSlotButton.addEventListener("click", function() {
        addTimeSlot();
    });

    // Function to add a new time slot
    function addTimeSlot() {
        const timeSlotsContainer = document.getElementById("timeSlotsContainer");
        const index = timeSlotsContainer.children.length; // Get the number of existing time slots
        const days = ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

        // Create elements for the new time slot
        const newTimeSlotDiv = document.createElement('div');
        newTimeSlotDiv.classList.add('row', 'align-items-center', 'mb-3');
        newTimeSlotDiv.innerHTML = `
            <div class="btn-group col-auto" role="group" aria-label="Day Selection">
                ${days.map(day => `<button class="btn btn-outline-primary" data-day="${day.toLowerCase()}">${day}</button>`).join('')}
            </div>
            <div class="col">
                <input type="time" class="form-control" id="startTime${index}" />
            </div>
            <div class="col">
                <input type="time" class="form-control" id="endTime${index}" />
            </div>
            <div class="col-auto">
                <button class="btn btn-outline-danger remove-time-slot" type="button">Remove</button>
            </div>
        `;

        // Append the new time slot to the container
        timeSlotsContainer.appendChild(newTimeSlotDiv);

        // Add event listeners to toggle day selection for the new time slot
        newTimeSlotDiv.querySelectorAll('.btn-outline-primary').forEach(button => {
            button.addEventListener('click', function () {
                toggleDaySelection(this);
            });
        });

        // Add event listener to the remove button of the new time slot
        const removeButton = newTimeSlotDiv.querySelector('.remove-time-slot');
        removeButton.addEventListener('click', function() {
            removeTimeSlot(newTimeSlotDiv);
        });

        document.querySelectorAll('[id^="endTime"]').forEach(endTimeInput => {
            endTimeInput.addEventListener("change", function() {
                fetchRooms();
                fetchSuggestion();
            });
        });

    
        
    }


    function getTimeSlots() {
        const timeSlots = [];
        document.querySelectorAll('#timeSlotsContainer .row').forEach(timeSlot => {
            const dayButtons = timeSlot.querySelectorAll('.btn-outline-primary.active');
            const startTimeInput = timeSlot.querySelector('[id^="startTime"]');
            const endTimeInput = timeSlot.querySelector('[id^="endTime"]');
            if (dayButtons.length > 0 && startTimeInput && endTimeInput) {
                const days = Array.from(dayButtons).map(button => button.dataset.day);
                const startTime = startTimeInput.value;
                const endTime = endTimeInput.value;
                timeSlots.push({ days, startTime, endTime });
            }
        });
        return timeSlots;
    }

    // Function to remove a time slot
    function removeTimeSlot(timeSlotDiv) {
        timeSlotDiv.remove();
    }

    // Function to toggle day selection
    function toggleDaySelection(button) {
        button.classList.toggle('active');
        button.classList.toggle('btn-primary'); // Bootstrap active class
    }


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
            // "startTime": document.getElementById("startHour").value+':'+document.getElementById("startMinute").value+' '+document.getElementById("startAmPm").value,
            // "endTime": document.getElementById("endTime").value,
            "crossSectionId": document.getElementById("crossSectionId").value
        };

        const timeSlots = [];

    // Iterate through added time slots and collect data
    document.querySelectorAll('#timeSlotsContainer .row').forEach(timeSlot => {
        const dayButtons = timeSlot.querySelectorAll('.btn-outline-primary.active');
        const startTimeInput = timeSlot.querySelector('[id^="startTime"]');
        const endTimeInput = timeSlot.querySelector('[id^="endTime"]');
        if (dayButtons.length > 0 && startTimeInput && endTimeInput) {
            const days = Array.from(dayButtons).map(button => button.dataset.day);
            const startTime = startTimeInput.value;
            const endTime = endTimeInput.value;
            timeSlots.push({ days, startTime, endTime });
        }
    });

    // Add time slots to formData
    formData.timeSlots = timeSlots;

    console.log(formData);

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
               
                throw new Error(response.text);
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Failed to save section"+error);
        });
    });

    // Function to fetch room data and populate dropdown
    function fetchRooms() {
        const timeSlots = getTimeSlots();
        console.log(timeSlots);
        fetch("http://localhost:8080/getClassListForDropDown", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                "professorId": 9876,
                "semId": 3,
                "courseId": 67890,
                "timeSlots": timeSlots // Pass time slots array to the API
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
            roomDropdown.innerHTML = ""; // Clear existing options
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
                "courseId": 1,
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
       // const professorId = this.value;
        // Call suggestion API and display suggestion
        fetchSuggestion();
    });

    document.getElementById("roomId").addEventListener("change", function() {
       
        
        // Call suggestion API and display suggestion
        fetchSuggestion();
    });

    // Function to fetch suggestion for the selected professor
    function fetchSuggestion() {
        const timeSlots = getTimeSlots();
        fetch("http://localhost:8080/getSuggestion", {
            
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
				"createNew":true,
                "courseId": urlParams.get('courseId'),
                 "semId": urlParams.get('semId'),
                "professorId": document.getElementById("professor").value,
                "roomId": document.getElementById("roomId").value,
                "timeSlots": timeSlots
                
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
            const suggestionBox = document.getElementById("suggestionBox");
            suggestionBox.innerHTML = ""; // Clear previous content
        
            const conflictDTO = data.conflictDTO;
        
            // Check if there are any conflicts
            if (conflictDTO) {
                for (const conflictType in conflictDTO) {
                    const conflictMessages = conflictDTO[conflictType];
                    if (conflictMessages && conflictMessages.length > 0) {
                        // Create heading for each conflict type
                        const heading = document.createElement("h3");
                        heading.textContent = `${conflictType} Conflict`;
                        suggestionBox.appendChild(heading);
        
                        // Create list to display conflict messages
                        const list = document.createElement("ul");
                        // Check if conflictMessages is a string
                        if (typeof conflictMessages === 'string') {
                            const listItem = document.createElement("li");
                            listItem.textContent = conflictMessages;
                            list.appendChild(listItem);
                        } else {
                            // If it's not a string, assume it's an array and iterate over it
                            conflictMessages.forEach(message => {
                                const listItem = document.createElement("li");
                                listItem.textContent = message;
                                list.appendChild(listItem);
                            });
                        }
                        suggestionBox.appendChild(list);
                    }
                }
            } else {
                // If no conflicts, display a message indicating so
                suggestionBox.textContent = "No conflicts found.";
            }
        
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
