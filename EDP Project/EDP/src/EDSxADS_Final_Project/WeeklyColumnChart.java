package EDSxADS_Final_Project;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
        
public class WeeklyColumnChart extends JPanel {
    
    // Changed to long to handle values over 2.1 billion
    private long[] values = {0, 0, 0, 0, 0, 0, 0}; 
    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public WeeklyColumnChart() {
        setBackground(Color.WHITE);
        fetchDbData(); // Load real data on startup
    }

    public final void fetchDbData() {
        // Reset values
        for (int i = 0; i < values.length; i++) values[i] = 0;

        try (Connection con = DBConnection.getConnection()) {
            // This query calculates total quantity per day for the CURRENT week
            String query = "SELECT WEEKDAY(CollectionDate) as DayIndex, SUM(Quantity) as Total " +
                           "FROM Transactions " +
                           "WHERE YEARWEEK(CollectionDate, 1) = YEARWEEK(CURDATE(), 1) " +
                           "GROUP BY DayIndex";
            
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                int index = rs.getInt("DayIndex");
                if (index >= 0 && index < 7) {
                    // Changed to getLong to prevent SQLDataException
                    values[index] = rs.getLong("Total");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        repaint(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        
        int barWidth = width / values.length;

        // Changed max to long to match the values array
        long max = 0;
        for (long v : values) {
            if (v > max) max = v;
        }
        
        // Avoid division by zero if there is no data yet
        if (max <= 0) max = 1; 
        
        for (int i = 0; i < values.length; i++) {
            // Calculated using double to maintain precision before casting to pixel height
            int barHeight = (int) (((double) values[i] / max) * (height - 50));

            int x = i * barWidth + 10;
            int y = height - barHeight - 30;

            // Bar Color
            g.setColor(new Color(34, 139, 34));
            g.fillRect(x, y, barWidth - 20, barHeight);

            // Labels
            g.setColor(Color.BLACK);
            g.drawString(days[i], x + 10, height - 10);
            
            // Displays the large number as a String above the bar
            g.drawString(String.valueOf(values[i]), x + 10, y - 5);
        }
    }
}