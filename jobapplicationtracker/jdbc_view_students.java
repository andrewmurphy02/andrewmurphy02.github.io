import java.sql.*;

public class jdbc_view_students {
    // The main program that views a student
    public static void main(String[] args) throws SQLException {
        String Username = "ajm057"; // Change to your own username
        String mysqlPassword = "de2ki3Ah"; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

        // Get dropdown choice
        String selectedOption = null;
        if (args.length > 0) {
            selectedOption = args[0]; 
        } else {
            System.out.println("No option selected");
            System.exit(1);
        }

        String query = "";
        // Switch statement that queries based on major selected
        switch (selectedOption) {
            case "CSCE":
                query = "SELECT * FROM STUDENTS WHERE MAJOR ='CSCE'";
                break;
            case "BIOL":
                query = "SELECT * FROM STUDENTS WHERE MAJOR='BIOL'";
                break;
            case "MATH":
                query = "SELECT * FROM STUDENTS WHERE MAJOR='MATH'";
                break;
            case "ALL":
                query = "SELECT * FROM STUDENTS";
                break;
            default:
                System.out.println("Invalid option");
                System.exit(1);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<br><br><br> Table STUDENTS results:" + myDB.query(query));
        System.out.println(builder.toString());
        

        myDB.disConnect();
    }
}
    

