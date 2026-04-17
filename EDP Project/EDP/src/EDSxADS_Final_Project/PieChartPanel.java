package EDSxADS_Final_Project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PieChartPanel extends JPanel {

    private String[] labels;
    private double[] values;
    private Color[] colors;

    public PieChartPanel(String[] labels, double[] values, Color[] colors) {
        this.labels = labels;
        this.values = values;
        this.colors = colors;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double total = 0;
        for (double v : values) total += v;

        int startAngle = 0;

        int size = Math.min(getWidth(), getHeight()) - 50;

        for (int i = 0; i < values.length; i++) {
            int angle = (int) Math.round(values[i] / total * 360);

            g2.setColor(colors[i]);
            g2.fillArc(10, 10, size, size, startAngle, angle);

            startAngle += angle;
        }

        // Legend
        int y = 20;
        for (int i = 0; i < labels.length; i++) {
            g2.setColor(colors[i]);
            g2.fillRect(size + 20, y, 15, 15);

            g2.setColor(Color.black);
            g2.drawString(labels[i] + " (" + (int)values[i] + "%)", size + 40, y + 12);

            y += 25;
        }
    }
}
