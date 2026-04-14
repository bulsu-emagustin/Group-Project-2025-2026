package EDSxADS_Final_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Bins {

    public static int getBinIDFromLocation(String location) {
        switch (location) {
            case "Engineering Building": return 1;
            case "Canteen": return 2;
            case "Pimentel": return 3;
            case "E-Library": return 4;
            default: return -1;
        }
    }

    public static void updateBinStatus(int Loc) {
        String countSql = "SELECT SUM(Quantity) FROM Transactions WHERE BinID = ?";
        String updateSql = "UPDATE RecycleBins SET Status = ? WHERE BinID = ?";

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return;

            int totalQuantity = 0;

            try (PreparedStatement pstmtCount = con.prepareStatement(countSql)) {
                pstmtCount.setInt(1, Loc);
                try (ResultSet rs = pstmtCount.executeQuery()) {
                    if (rs.next()) {
                        totalQuantity = rs.getInt(1);
                    }
                }
            }

            String newStatus;
            if (totalQuantity <= 50) {
                newStatus = "Empty";
            } else if (totalQuantity <= 300) {
                newStatus = "Half-Full";
            } else {
                newStatus = "Full";
            }

            try (PreparedStatement pstmtUpdate = con.prepareStatement(updateSql)) {
                pstmtUpdate.setString(1, newStatus);
                pstmtUpdate.setInt(2, Loc);
                pstmtUpdate.executeUpdate();
            }

            if ("Full".equals(newStatus)) {
                System.out.println("ALERT: Bin ID " + Loc + " is now FULL!");
                JOptionPane.showMessageDialog(null, "Bin ID " + Loc + " has reached capacity!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating bin status: ");
        }
    }

    public static void clearBin(String binID) {
        String sql = "UPDATE RecycleBins SET Status = 'Empty' WHERE BinID = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            if (con == null) return;

            pst.setString(1, binID);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Bin ID " + binID + " cleared successfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage());
        }
    }

    // GET BIN STATUS BY LOCATION
    public static String getBinStatusByLocation(String location) {

    int binID = getBinIDFromLocation(location);
    if (binID == -1) return "Invalid location.";

    String sql = "SELECT Status FROM RecycleBins WHERE BinID = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement pst = con.prepareStatement(sql)) {

        pst.setInt(1, binID);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            return rs.getString("Status");
        } else {
            return "No data found.";
        }

    } catch (SQLException e) {
        e.printStackTrace();
        return "Error retrieving data.";
    }
}
}