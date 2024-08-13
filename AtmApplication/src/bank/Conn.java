package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    // Define instance variables
    Connection c;
    Statement s;

    // Constructor to initialize database connection
    public Conn() {
        try {
            // Initialize connection and statement
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "");
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
}
