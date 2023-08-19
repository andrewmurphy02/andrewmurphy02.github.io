<html>
<body style="background-color:navajowhite;">
<h3>Enter information about a application to add to the database:</h3>
<form action="jdbc_insert_application.php" method="post">
    Student ID: <input type="text" name="student_id"><br>
    Job ID: <input type="text" name="job_id"><br>
    <input name="submit" type="submit" >
</form>
<br><br>
</body>
<a href="http://www.csce.uark.edu/~ajm057/Assignment_5/homepage.html"><button style="font-size: 24px; padding: 5px 15px;">HOME PAGE</button></a>
</html>

<?php
if (isset($_POST['submit'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $student_id = escapeshellarg($_POST['student_id']);
    $job_id = escapeshellarg($_POST['job_id']);
    
    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_insert_application ' . $student_id . ' ' . $job_id;

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    echo "<p>command: $command <p>"; 
    // run jdbc_insert_item.exe
    system($escaped_command);           
}
?>


