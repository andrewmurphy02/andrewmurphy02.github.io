import java.sql.*;

public class jdbc_view_jobs {
    // The main program that finds jobs based on major
    public static void main(String[] args) throws SQLException {
        String Username = "ajm057"; // Change to your own username
        String mysqlPassword = "de2ki3Ah"; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

        // Get dropdown selection
        String selectedOption = null;
        if (args.length > 0) {
            selectedOption = args[0];
        } else {
            System.out.println("No option selected");
            System.exit(1);
        }

        String query = "";
        // Switch statement that queries based on major selection
        switch (selectedOption) {
            case "CSCE":
                query = "SELECT * FROM JOBS WHERE DESIRED_MAJOR ='CSCE'";
                break;
            case "BIOL":
                query = "SELECT * FROM JOBS WHERE DESIRED_MAJOR='BIOL'";
                break;
            case "MATH":
                query = "SELECT * FROM JOBS WHERE DESIRED_MAJOR='MATH'";
                break;
            case "ALL":
                query = "SELECT * FROM JOBS";
                break;
            default:
                System.out.println("Invalid option");
                System.exit(1);
        }

        StringBuilder builder = new StringBuilder();
        builder.append("<br><br><br> Table JOBS results:" + myDB.query(query));
        System.out.println(builder.toString());

        myDB.disConnect();
    }
}
