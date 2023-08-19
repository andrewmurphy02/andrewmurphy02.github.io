import java.sql.*;

/*
jdbc_insert_item.java    // java program that is called by php that just does the insert; calls jdbc_db.java to connect and do the actual insert
jdbc_db.java // class (no main program) that has useful methods
*/

public class jdbc_insert_job {
   // The main program that inserts a job
   public static void main(String[] args) throws SQLException {
      String Username = "ajm057"; // Change to your own username
      String mysqlPassword = "de2ki3Ah"; // Change to your own mysql Password

      // Connect to the database
      jdbc_db myDB = new jdbc_db();
      myDB.connect(Username, mysqlPassword);
      myDB.initDatabase();

      StringBuilder builder = new StringBuilder();
      String query1 = "SELECT * from JOBS";
      builder.append("<br> Table JOBS before:" + myDB.query(query1) + "<br>");

      String job_id;
      String company_name;
      String job_title;
      String salary;
      String desired_major;

      // Read command line arguments
      // args[0] is the first parameter
      job_id = args[0];
      company_name = args[1];
      job_title = args[2];
      salary = args[3];
      desired_major = args[4];
      
      // Insert the new job
      String input = "'"/* + next_id + "','"*/ + job_id + "','" + company_name + "','" + job_title + "','" + salary + "','" + desired_major + "'";
      myDB.insert("JOBS", input); // Insert new job

      // For debugging purposes: Show the database after the insert
      builder.append("<br><br><br> Table JOBS after:" + myDB.query(query1));
      System.out.println(builder.toString());

      myDB.disConnect();
   }
}