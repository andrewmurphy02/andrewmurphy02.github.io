<html>
<body style="background-color:navajowhite;">
    <h3>Select an option to view all applications for the selected option</h3>
    <form action="jdbc_view_applications.php" method="post"> 
        <select id="method" name="method">
            <option value="CSCE">CSCE</option>
            <option value="BIOL">BIOL</option>
            <option value="MATH">MATH</option>
            <option value="STUDENT">STUDENTID</option>
            <option value="JOB">JOBID</option>
            <option value="ALL">ALL</option>
        </select>
        <input type="text" id="idValue" name="idValue" placeholder="Enter ID Value" style="display:none;">
        <br><br>
        <input type="submit" value="Submit">
    </form>
    <script>
        // Add event listener to the dropdown
        document.getElementById("method").addEventListener("change", function() {
            // Get whatever is selected
            var selectedOption = this.value;
            // Receive input
            var idValueInput = document.getElementById("idValue");
            // If student option or job option is selected display the id text box else hide it
            if (selectedOption === "STUDENT" || selectedOption === "JOB") {
                idValueInput.style.display = "block";
            } else {
                idValueInput.style.display = "none";
            }
        });
    </script>
</body>
<a href="http://www.csce.uark.edu/~ajm057/Assignment_5/homepage.html"><button style="font-size: 24px; padding: 5px 15px;">HOME PAGE</button></a>
</html>

<?php
if (isset($_POST['method'])) 
{
    $method = $_POST['method'];
    // Is ID value set to something else set it to blank
    $idValue = isset($_POST['idValue']) ? $_POST['idValue'] : '';

    // Replace ' ' with '\ ' in the strings so they are treated as single command line args
    $method = escapeshellarg($method);
    $idValue = escapeshellarg($idValue);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_view_applications ' . $method . ' ' . $idValue;

    // Remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    echo "<p>command: $command <p>"; 
    // Run jdbc_view_students.exe
    system($escaped_command);           
}
?>