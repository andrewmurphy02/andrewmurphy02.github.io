<html>
<body style="background-color:navajowhite;">
<h3>Enter information about a job to add to the database:</h3>
<form action="jdbc_insert_job.php" method="post">
    Job ID: <input type="text" name="job_id"><br>
    Company Name: <input type="text" name="company_name"><br>
    Job Title: <input type="text" name="job_title"><br>
    Salary: <input type="text" name="salary"><br>
    Desired Major: <input type="text" name="desired_major"><br>
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
    $job_id = escapeshellarg($_POST['job_id']);
    $company_name = escapeshellarg($_POST['company_name']);
    $job_title = escapeshellarg($_POST['job_title']);
    $salary = escapeshellarg($_POST['salary']);
    $desired_major = escapeshellarg($_POST['desired_major']);
    

    $command = 'java -cp .:mysql-connector-java-5.1.40-bin.jar jdbc_insert_job ' . $job_id . ' ' . $company_name . ' ' . $job_title . ' ' . $salary . ' ' . $desired_major;

    // remove dangerous characters from command to protect web server
    $escaped_command = escapeshellcmd($command);
    echo "<p>command: $command <p>"; 
    // run jdbc_insert_item.exe
    system($escaped_command);           
}
?>
