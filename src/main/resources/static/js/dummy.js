
// instructors dummy object in real time, this will come from backend
const instructors = {
    1: 'John Doe',
    2: 'Jane Smith',
    3: 'Emily Johnson',
};

function searchInstructor() {
    const input = document.getElementById('instructorInput').value.toLowerCase();
    const results = Object.values(instructors).filter(name => 
        name.toLowerCase().includes(input)
    );

    const resultDropdown = document.getElementById('resultDropdown');
    if (results.length > 0) {
        let resultHTML = '';
        results.forEach(result => {
            resultHTML += `<a href="#" onclick="selectInstructor('${result}')">${result}</a>`;
        });
        resultDropdown.innerHTML = resultHTML;
        resultDropdown.style.display = 'block';
        document.getElementById('addButton').disabled = true;
    } else {
        resultDropdown.style.display = 'none';
        document.getElementById('addButton').disabled = false;
    }
}

function addInstructor() {
    const instructorName = document.getElementById('instructorInput').value;
    // Here you would add the instructor name to your object or database
    const newId = Object.keys(instructors).length + 1;
    instructors[newId] = instructorName;
    alert(`Added "${instructorName}" to instructors.`);

    // Clearing the input field after adding
    document.getElementById('instructorInput').value = '';
    document.getElementById('resultDropdown').style.display = 'none'; // Hide dropdown
    document.getElementById('addButton').disabled = true; // Disable the add button as there's no input
}

function selectInstructor(name) {
    document.getElementById('instructorInput').value = name;
    document.getElementById('resultDropdown').style.display = 'none';
    document.getElementById('addButton').disabled = false;
    // Prevent form submission or navigation
    event.preventDefault();
}

