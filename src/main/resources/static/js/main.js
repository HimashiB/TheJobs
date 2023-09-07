// Function to filter table rows based on search input
function filterTable() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.querySelector("table");
    tr = table.getElementsByTagName("tr");

    for (i = 1; i < tr.length; i++) {
        var matchFound = false;
        for (var j = 0; j < 5; j++) {
            td = tr[i].getElementsByTagName("td")[j];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    matchFound = true;
                    break; // Exit the inner loop if a match is found
                }
            }
        }
        if (matchFound) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }
    }
}

// Function to generate a sample report
function generateReport() {
    const reportText = 'This is a sample report.\nGenerated on ' + new Date().toLocaleString();
    alert(reportText);
}

// Add event listeners for the search input and report generation button
document.getElementById("searchInput").addEventListener("keyup", filterTable);
