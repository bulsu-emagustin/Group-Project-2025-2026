package EDSxADS_Final_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Bins {

    public static int getBinIDFromLocation(String location) {
        
        return switch (location) {
            case "Engineering Building" -> 1;
            case "Canteen" -> 2;
            case "Pimentel" -> 3;
            case "E-Library" -> 4; 
            default -> -1;
        };
    }

    public static void updateBinStatus(int binID) {
        String countSql = "SELECT SUM(Quantity) FROM Transactions WHERE BinID = ?";
        String updateSql = "UPDATE RecycleBins SET Status = ? WHERE BinID = ?";

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return;

            int totalQuantity = 0;
            try (PreparedStatement pstmtCount = con.prepareStatement(countSql)) {
                pstmtCount.setInt(1, binID);
                try (ResultSet rs = pstmtCount.executeQuery()) {
                    if (rs.next()) {
                        totalQuantity = rs.getInt(1);
                    }
                }
            }

            // Status Logic
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
                pstmtUpdate.setInt(2, binID);
                pstmtUpdate.executeUpdate();
            }

            if ("Full".equals(newStatus)) {
                JOptionPane.showMessageDialog(null, "Warning: Bin ID " + binID + " is now FULL!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clearBin(int binID) {
        
        String resetBinStatus = "UPDATE RecycleBins SET Status = 'Empty' WHERE BinID =?";
        
        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return;
            
            con.setAutoCommit(false); // Start transaction

            try (PreparedStatement pst1 = con.prepareStatement(resetBinStatus)) {
                
                //Clear history
                pst1.setInt(1, binID);
                pst1.executeUpdate();

                con.commit(); // Save changes
                JOptionPane.showMessageDialog(null, "Bin ID " + binID + " has been emptied");
            } catch (SQLException ex) {
                con.rollback(); // Undo if error
                throw ex;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error clearing bin");
        }
    }

    public static String getBinStatusByLocation(String location) {
        int binID = getBinIDFromLocation(location);
        if (binID == -1) return "Invalid location.";

        String sql = "SELECT Status FROM RecycleBins WHERE BinID = ?";
        try (Connection con = DBConnection.getConnection(); 
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, binID);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return rs.getString("Status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Unknown";
    }
}