package EDSxADS_Final_Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class DonutChartPanel extends JPanel {

    private double percentage;
    private Color color;
    private String label;

    public DonutChartPanel(double percentage, Color color, String label) {
        this.percentage = percentage;
        this.color = color;
        this.label = label;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight()) - 20;
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        // Background circle
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillArc(x, y, size, size, 0, 360);

        // Foreground (progress)
        g2.setColor(color);
        int safePercent = (int) Math.min(percentage, 100);
        int angle = (int) (360 * safePercent / 100);
        g2.fillArc(x, y, size, size, 90, -angle);

        // Inner circle (hole)
        int holeSize = size / 2;
        int hx = (getWidth() - holeSize) / 2;
        int hy = (getHeight() - holeSize) / 2;

        g2.setColor(getParent().getBackground()); // matches panel bg
        g2.fillOval(hx, hy, holeSize, holeSize);

        // Percentage text
        String text = (int) percentage + "%";
        g2.setColor(Color.black);
        g2.setFont(new Font("Arial", Font.BOLD, 18));

        FontMetrics fm = g2.getFontMetrics();
        int tx = getWidth() / 2 - fm.stringWidth(text) / 2;
        int ty = getHeight() / 2 + fm.getAscent() / 2;

        g2.drawString(text, tx, ty);

        // Label (below)
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        fm = g2.getFontMetrics();
        int lx = getWidth() / 2 - fm.stringWidth(label) / 2;
        g2.drawString(label, lx, getHeight() - 10);
    }
}
