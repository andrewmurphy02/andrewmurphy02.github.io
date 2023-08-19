import java.sql.*;

public class jdbc_view_applications {
    // The main program that views applications
    public static void main(String[] args) throws SQLException {
        String Username = "ajm057"; // Change to your own username
        String mysqlPassword = "de2ki3Ah"; // Change to your own mysql Password

        // Connect to the database
        jdbc_db myDB = new jdbc_db();
        myDB.connect(Username, mysqlPassword);
        myDB.initDatabase();

        // Sets the selected option in dropdown to args[0] and if nothing is selected
        // say error
        String selectedOption = null;
        if (args.length > 0) {
            selectedOption = args[0];
        } else {
            System.out.println("No option selected");
            System.exit(1);
        }

        String idValue;
        idValue = args[1];
        // Switch statment to call certain queries based on selected/inputted data
        String query = "";
        switch (selectedOption) {
            case "CSCE":
                query = "SELECT S.STUDENT_NAME, J.COMPANY_NAME, J.SALARY, S.MAJOR FROM APPLICATIONS A JOIN STUDENTS S ON A.STUDENT_ID = S.STUDENT_ID JOIN JOBS J ON A.JOB_ID = J.JOB_ID WHERE S.MAJOR = 'CSCE';";
                break;
            case "BIOL":
                query = "SELECT S.STUDENT_NAME, J.COMPANY_NAME, J.SALARY, S.MAJOR FROM APPLICATIONS A JOIN STUDENTS S ON A.STUDENT_ID = S.STUDENT_ID JOIN JOBS J ON A.JOB_ID = J.JOB_ID WHERE S.MAJOR = 'BIOL';";
                break;
            case "MATH":
                query = "SELECT S.STUDENT_NAME, J.COMPANY_NAME, J.SALARY, S.MAJOR FROM APPLICATIONS A JOIN STUDENTS S ON A.STUDENT_ID = S.STUDENT_ID JOIN JOBS J ON A.JOB_ID = J.JOB_ID WHERE S.MAJOR = 'MATH';";
                break;
            case "STUDENT":
                // If a dropdown was selected and an id was inputted
                if (args.length > 1) {
                    query = "SELECT S.STUDENT_NAME, J.COMPANY_NAME, J.SALARY, S.MAJOR FROM APPLICATIONS A JOIN STUDENTS S ON A.STUDENT_ID = S.STUDENT_ID JOIN JOBS J ON A.JOB_ID = J.JOB_ID WHERE S.STUDENT_ID = "
                            + idValue;
                } else {
                    System.out.println("No student ID provided");
                    System.exit(1);
                }
                break;
            case "JOB":
                // If a dropdown was selected and an id was inputted
                if (args.length > 1) {
                    query = "SELECT S.STUDENT_NAME, J.COMPANY_NAME, J.SALARY, S.MAJOR FROM APPLICATIONS A JOIN STUDENTS S ON A.STUDENT_ID = S.STUDENT_ID JOIN JOBS J ON A.JOB_ID = J.JOB_ID WHERE J.JOB_ID = "
                            + idValue;
                } else {
                    System.out.println("No job ID provided");
                    System.exit(1);
                }
                break;
            case "ALL":
                query = "SELECT S.STUDENT_NAME, J.COMPANY_NAME, J.SALARY, S.MAJOR FROM APPLICATIONS A JOIN STUDENTS S ON A.STUDENT_ID = S.STUDENT_ID JOIN JOBS J ON A.JOB_ID = J.JOB_ID;";
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
