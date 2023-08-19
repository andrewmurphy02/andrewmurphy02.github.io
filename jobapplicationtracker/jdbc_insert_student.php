<html>
<body style="background-color:navajowhite;">
<h3>Enter information about a student to add to the database:</h3>
<form action="jdbc_insert_student.php" method="post">
    Student ID: <input type="text" name="student_id"><br>
    Student Name: <input type="text" name="student_name"><br>
    Major: <input type="text" name="major"><br>
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
    $student_name = escapeshellarg($_POST['student_name']);
    $major = escapeshellarg($_POST['major']);
    

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_insert_student ' . $student_id . ' ' . $student_name . ' ' . $major;

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    echo "<p>command: $command <p>"; 
    // run jdbc_insert_item.exe
    system($escaped_command);           
}
?>


