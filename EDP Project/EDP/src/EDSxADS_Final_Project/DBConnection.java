package EDSxADS_Final_Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DBConnection {

    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "Recycling";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void initDatabase() {
        try (Connection conn = DriverManager.getConnection(BASE_URL, USER, PASS); Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.executeUpdate("USE " + DB_NAME);

            // Admins Table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Admins ("
                    + "Username VARCHAR(100) PRIMARY KEY, "
                    + "Password INT)");

            // RecycleBins Table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS RecycleBins ("
                    + "BinID INT AUTO_INCREMENT PRIMARY KEY, "
                    + "Location VARCHAR(100) NOT NULL, "
                    + "Status ENUM('Empty', 'Half-Full', 'Full') DEFAULT 'Empty')");

            // Students Table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Students ("
                    + "StudentNo INT PRIMARY KEY, "
                    + "FirstName VARCHAR(50), "
                    + "MiddleName VARCHAR(50), "
                    + "LastName VARCHAR(50), "
                    + "Department VARCHAR(100), "
                    + "Course VARCHAR(50), "
                    + "Section VARCHAR(20), "
                    + "Specialization VARCHAR(50), "
                    + "YearLevel VARCHAR(20))");

            // Transactions Table (Includes Foreign Keys for Students and Bins)
            String sqlTransactions = "CREATE TABLE IF NOT EXISTS Transactions ("
                    + "TransactionID INT AUTO_INCREMENT PRIMARY KEY, "
                    + "BinID INT, "
                    + "StudentNo INT NOT NULL, "
                    + "MaterialType VARCHAR(50) NOT NULL, "
                    + "Quantity INT NOT NULL, "
                    + "CollectionDate DATETIME DEFAULT CURRENT_TIMESTAMP, "
                    + "CONSTRAINT fk_student FOREIGN KEY (StudentNo) "
                    + "REFERENCES Students(StudentNo) ON DELETE CASCADE ON UPDATE CASCADE, "
                    + "CONSTRAINT fk_bin FOREIGN KEY (BinID) "
                    + "REFERENCES RecycleBins(BinID) ON DELETE SET NULL)";

            stmt.executeUpdate(sqlTransactions);

            // Initial Data
            seedInitialData(stmt);

        } catch (SQLException se) {
            se.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Initialization Error: " + se.getMessage());
        }
    }

    private static void seedInitialData(Statement stmt) throws SQLException {
        // Seed Students (One for each major department to populate Dashboard charts)
        if (!hasData(stmt, "Students")) {
            stmt.executeUpdate("INSERT INTO Students (StudentNo, FirstName, LastName, Department) VALUES "
                    + "(2024104677, 'Akila', 'Cruz', 'IT'), "
                    + "(2024104678, 'Ernesto', 'Agustin', 'Engineering'), "
                    + "(2024104679, 'Yza', 'Pascua', 'Business'), "
                    + "(2024104680, 'Ian', 'Gabriel', 'Education'), "
                    + "(2024104681, 'Kath', 'Salidaga', 'Medical'), "
                    + "(2024104682, 'Sherlock', 'Holmes', 'Law')");
        }

        // Bins
        if (!hasData(stmt, "RecycleBins")) {
            stmt.executeUpdate("INSERT INTO RecycleBins (Location, Status) VALUES "
                    + "('Engineering Building', 'Empty'), "
                    + "('Canteen', 'Empty'), "
                    + "('Pimentel', 'Empty'), "
                    + "('E-Library', 'Empty')");
        }

        // Default Admin
        if (!hasData(stmt, "Admins")) {
            stmt.executeUpdate("INSERT INTO Admins (Username, Password) VALUES ('admin', 12345)");
        }
    }

    private static boolean hasData(Statement stmt, String tableName) throws SQLException {
        try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName)) {
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    // Role-Based Connection 
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    BASE_URL + DB_NAME,
                    USER,
                    PASS
            );
        } catch (SQLException e) {
            System.err.println("Database Connection Error: " + e.getMessage());
            return null;
        }
    }
}
