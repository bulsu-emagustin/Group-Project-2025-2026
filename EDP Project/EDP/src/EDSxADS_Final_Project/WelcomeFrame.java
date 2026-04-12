package EDSxADS_Final_Project;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

class WelcomeFrame extends JFrame {

    JLabel Welcome, To, University, Zone;
    Timer time;

    public WelcomeFrame() {

        //Frame settings
        this.setTitle("University Recycle Zone");
        this.setSize(1500, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //Background image
        ImageIcon icon = new ImageIcon("Background.png");
        JLabel background = new JLabel(icon);
        background.setLayout(null);

        //Text Modifications
        Welcome = new JLabel("Welcome");
        Welcome.setBounds(490, 70, 600, 100);
        Welcome.setFont(new Font("Arial Black", Font.BOLD, 100));
        Welcome.setForeground(Color.black);
        To = new JLabel("to");
        To.setBounds(700, 210, 600, 100);
        To.setFont(new Font("Arial Black", Font.BOLD, 80));
        To.setForeground(Color.black);
        University = new JLabel("University Recycle");
        University.setBounds(45, 330, 1500, 180);
        University.setFont(new Font("Arial Black", Font.BOLD, 130));
        University.setForeground(Color.green);
        Zone = new JLabel("Zone");
        Zone.setBounds(560, 520, 700, 180);
        Zone.setFont(new Font("Arial Black", Font.BOLD, 130));
        Zone.setForeground(Color.green);        
        
        //Adding Text
        background.add(Welcome);
        background.add(To);
        background.add(University);
        background.add(Zone);        


        this.setContentPane(background);
        this.setVisible(true);
        
        //Timer 
        time = new Timer(4500, e -> {
            dispose(); 
            new UniversityRecycleZone(); 
        });
        time.setRepeats(false);
        time.start();        
    }
}
