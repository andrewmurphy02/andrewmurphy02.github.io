<html>
<body style="background-color:navajowhite;">
    <h3>Select a major to view all jobs for that major</h3>
    <form action="jdbc_view_jobs.php" method="post">
        <select id="major" name="major">
            <option value="CSCE">CSCE</option>
            <option value="BIOL">BIOL</option>
            <option value="MATH">MATH</option>
            <option value="ALL">ALL</option>
        </select>
        <br><br>
        <input type="submit" value="Submit">
    </form>
</body>
<a href="http://www.csce.uark.edu/~ajm057/Assignment_5/homepage.html"><button style="font-size: 24px; padding: 5px 15px;">HOME PAGE</button></a>
</html>

<?php
if (isset($_POST['major'])) 
{
    // replace ' ' with '\ ' in the strings so they are treated as single command line args
    $major = escapeshellarg($_POST['major']);

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_view_jobs ' . $major;

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    echo "<p>command: $command <p>"; 
    // run jdbc_view_students.exe
    system($escaped_command);           
}
?>