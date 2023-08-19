import java.sql.*;

/*
jdbc_insert_item.java    // java program that is called by php that just does the insert; calls jdbc_db.java to connect and do the actual insert
jdbc_db.java // class (no main program) that has useful methods
*/

public class jdbc_insert_application {
   // The main program that inserts an application
   public static void main(String[] args) throws SQLException {
      String Username = "ajm057"; // Change to your own username
      String mysqlPassword = "de2ki3Ah"; // Change to your own mysql Password

      // Connect to the database
      jdbc_db myDB = new jdbc_db();
      myDB.connect(Username, mysqlPassword);
      myDB.initDatabase();

      StringBuilder builder = new StringBuilder();
      String query1 = "SELECT * from APPLICATIONS";
      builder.append("<br> Table APPLICATIONS before:" + myDB.query(query1) + "<br>");

      String student_id;
      String job_id;

      // Read command line arguments
      // args[0] is the first parameter
      student_id = args[0];
      job_id = args[1];
      
      // Insert the new application
      String input = "'"/* + next_id + "','"*/ + student_id + "','" + job_id + "'";
      myDB.insert("APPLICATIONS", input); // Insert new application
     
      // For debugging purposes: Show the database after the insert
      builder.append("<br><br><br> Table APPLICATIONS after:" + myDB.query(query1));
      System.out.println(builder.toString());

      myDB.disConnect();
   }
}
