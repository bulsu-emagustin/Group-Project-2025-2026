package EDSxADS_Final_Project;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JPasswordField;

public class RecyclingCollectionMonitoringSystem {

    //Main Method
    public static void main(String[] args) {
        new UniversityRecycleZone();
    }
}

//Main Frame
class UniversityRecycleZone extends JFrame {

    //Variables
    JFrame MainF;
    JPanel header, UnivImageP, leftPanel, UserImageP, MainP, ColumnChart;
    JDialog Login, Contribution, History;
    JLabel UnivIcon, UserIcon, UniversityL, AdminL, LoginL, UsernameL, PasswordL, ContributionL, SIDL, MTypeL, QuantityL, StudentCL, LocationL;
    JButton AdminButton, AddContriButton, ViewContriButton, ClearButton1, ClearButton2, EnterButton, CancelButton, SearchButton;
    JPasswordField Passwordfield;
    JTextField Usernamefield, IDfield, Quantityfield;
    JComboBox<String> MTypeBox, LTypeBox;
    JTable Table;
    JScrollPane TableS;
    ImageIcon MainBack, UnivL, UserIconL, ScaledUnivIcon, ScaledUserIcon;
    Image Logo, Logo2;

    public UniversityRecycleZone() {
        DBConnection.initDatabase();

        //Containers
        MainF = new JFrame("University Recycle Zone");
        header = new JPanel();
        leftPanel = new JPanel();
        UnivImageP = new JPanel();
        UserImageP = new JPanel();
        Login = new JDialog(this, "Admin Login", true);
        Contribution = new JDialog(this, "Contribution Form", true);
        History = new JDialog(this, "User History", true);
        String[] Mtype = {"Plastic", "Glass", "Paper", "Metal", "E-Waste"};
        String[] LType = {"Engineering Building", "Canteen", "E-Library", "Pimentel"};
        MainP = new JPanel();

        //Column Chart
        ColumnChart = new WeeklyColumnChart();

        //Background Image
        MainBack = new ImageIcon("Main_Background.jpg");
        JLabel Mbackground = new JLabel(MainBack);
        Mbackground.setLayout(null);
        MainF.setContentPane(Mbackground);

        //Layout Config
        MainF.setLayout(new BorderLayout());

        //Header Config
        header.setBackground(new Color(34, 139, 34));
        header.setPreferredSize(new Dimension(1500, 90));
        header.setLayout(null);
        UnivImageP.setBounds(10, 10, 80, 70);
        UnivImageP.setLayout(null);
        UnivL = new ImageIcon("URZLogo.png");
        Logo = UnivL.getImage().getScaledInstance(80, 70, Image.SCALE_SMOOTH);
        ScaledUnivIcon = new ImageIcon(Logo);
        UnivIcon = new JLabel(ScaledUnivIcon);
        UnivIcon.setBounds(0, 0, 80, 70);
        UniversityL = new JLabel("University Recycle Zone");
        UniversityL.setBounds(61, 20, 500, 50);
        UniversityL.setFont(new Font("Arial Black", Font.BOLD, 30));

        //Login button
        AdminL = new JLabel("ADMIN");
        AdminL.setBounds(1240, 25, 120, 40);
        AdminL.setFont(new Font("Bodoni MT", Font.BOLD, 30));
        AdminButton = new JButton("Login");
        AdminButton.setForeground(Color.red);
        AdminButton.setFont(new Font("Arial Black", Font.BOLD, 20));
        AdminButton.setBounds(1350, 20, 120, 50);
        new LoginFunction();

        //Left panel Config
        leftPanel.setBackground(new Color(50, 205, 50));
        leftPanel.setPreferredSize(new Dimension(400, 800));
        leftPanel.setLayout(null);
        UserImageP.setBounds(50, 50, 300, 250);
        UserImageP.setLayout(null);
        UserIconL = new ImageIcon("UserIcon.png");
        Logo2 = UserIconL.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
        ScaledUserIcon = new ImageIcon(Logo2);
        UserIcon = new JLabel(ScaledUserIcon);
        UserIcon.setBounds(0, 0, 300, 250);
        AddContriButton = new JButton("Add Contribution");
        AddContriButton.setBackground(Color.LIGHT_GRAY);
        AddContriButton.setFont(new Font("Arial", Font.BOLD, 30));
        AddContriButton.setBounds(50, 390, 300, 100);
        ViewContriButton = new JButton("View Contribution");
        ViewContriButton.setBackground(Color.LIGHT_GRAY);
        ViewContriButton.setFont(new Font("Arial", Font.BOLD, 30));
        ViewContriButton.setBounds(50, 530, 300, 100);

        //Add Contribution Dialog
        AddContriButton.addActionListener(e -> {
            Contribution.setSize(500, 400);
            Contribution.setLocationRelativeTo(this);
            Contribution.setLayout(null);
            ContributionL = new JLabel("CONTRIBUTION");
            ContributionL.setBounds(130, 10, 300, 50);
            ContributionL.setFont(new Font("Arial Black", Font.BOLD, 25));

            SIDL = new JLabel("School ID: ");
            SIDL.setBounds(63, 40, 70, 80);
            IDfield = new JTextField(100);
            IDfield.setBounds(130, 65, 300, 30);
            MTypeL = new JLabel("Material Type: ");
            MTypeL.setBounds(40, 93, 100, 80);
            MTypeBox = new JComboBox<>(Mtype);
            MTypeBox.setBounds(130, 120, 300, 30);
            LocationL = new JLabel("Location: ");
            LocationL.setBounds(68, 180, 100, 30);
            LTypeBox = new JComboBox<>(LType);
            LTypeBox.setBounds(130, 180, 300, 30);
            QuantityL = new JLabel("Quantity: ");
            QuantityL.setBounds(70, 210, 70, 80);
            Quantityfield = new JTextField(50);
            Quantityfield.setBounds(130, 235, 300, 30);
            EnterButton = new JButton("Enter");
            EnterButton.setBounds(90, 285, 130, 50);
            EnterButton.setFont(new Font("Arial", Font.BOLD, 25));
            CancelButton = new JButton("Cancel");
            CancelButton.setBounds(270, 285, 130, 50);
            CancelButton.setFont(new Font("Arial", Font.BOLD, 25));

            Contribution.add(SIDL);
            Contribution.add(ContributionL);
            Contribution.add(IDfield);
            Contribution.add(MTypeL);
            Contribution.add(MTypeBox);
            Contribution.add(LocationL);
            Contribution.add(LTypeBox);
            Contribution.add(QuantityL);
            Contribution.add(Quantityfield);
            Contribution.add(EnterButton);
            Contribution.add(CancelButton);

            // Handler for addition
            EnterButton.addActionListener(ev -> {
                try {
                    // Check if fields are empty
                    if (IDfield.getText().trim().isEmpty() || Quantityfield.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(Contribution, "Please fill all fields!", "Input Error", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // Validate ID format
                    long id;
                    try {
                        id = Long.parseLong(IDfield.getText().trim());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Contribution, "Invalid School ID! Please enter numbers only.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Validate Quantity format
                    long qty;
                    try {
                        qty = Long.parseLong(Quantityfield.getText().trim());
                        if (qty <= 0) {
                            JOptionPane.showMessageDialog(Contribution, "Quantity must be a positive number!", "Input Error", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(Contribution, "Invalid Quantity! Please enter a whole number (integers only).", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try (Connection con = DBConnection.getConnection()) {
                        // Check if the Student is registered
                        String checkSql = "SELECT StudentNo FROM Students WHERE StudentNo = ?";
                        PreparedStatement checkPst = con.prepareStatement(checkSql);
                        checkPst.setLong(1, id);
                        ResultSet rs = checkPst.executeQuery();

                        if (!rs.next()) {
                            JOptionPane.showMessageDialog(Contribution,
                                    "Access Denied: Student ID " + id + " is not registered.\n"
                                    + "Please contact an Admin for registration.",
                                    "Unregistered Student", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Get BinID based on selected location
                        int binID = Bins.getBinIDFromLocation(LTypeBox.getSelectedItem().toString());
                        if (binID == -1) {
                            JOptionPane.showMessageDialog(Contribution, "Invalid Location selected!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // INSERT TRANSACTION
                        String sql = "INSERT INTO Transactions (StudentNo, MaterialType, Quantity) VALUES (?, ?, ?)";
                        PreparedStatement pst = con.prepareStatement(sql);

                        pst.setLong(1, id);
                        pst.setString(2, MTypeBox.getSelectedItem().toString());
                        pst.setLong(3, qty);

                        int rows = pst.executeUpdate();
                        if (rows > 0) {
                            JOptionPane.showMessageDialog(Contribution, "Contribution successfully saved");
                            IDfield.setText("");
                            Quantityfield.setText("");
                            Contribution.dispose();
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Contribution, "Database Error: " + ex.getMessage());
                }
            });

            CancelButton.addActionListener(ev -> {
                IDfield.setText("");
                Quantityfield.setText("");
                Contribution.dispose();
            });

            Contribution.setVisible(true);
        });

        // View Contribution
        ViewContriButton.addActionListener(e -> {
            History.setSize(850, 450);
            History.setLocationRelativeTo(this);
            History.setLayout(null);
            JLabel SIDL_History = new JLabel("School ID: ");
            SIDL_History.setBounds(460, 13, 100, 30);
            JTextField searchField = new JTextField(50);
            searchField.setBounds(522, 13, 200, 30);
            SearchButton = new JButton("Search");
            SearchButton.setBounds(720, 13, 100, 30);

            String[] columns = {"Transaction ID", "Student No", "Material", "Quantity", "Department", "Date"};
            DefaultTableModel model = new DefaultTableModel(columns, 0);

            // Using class variables
            Table = new JTable(model);
            Table.getTableHeader().setReorderingAllowed(false);
            TableS = new JScrollPane(Table);
            TableS.setBounds(10, 60, 810, 354);

            SearchButton.addActionListener(searchEv -> {
                String studentIDStr = searchField.getText().trim();
                if (studentIDStr.isEmpty()) {
                    JOptionPane.showMessageDialog(History, "Please enter a Student Number.");
                    return;
                }

                try (Connection con = DBConnection.getConnection()) {
                    String query = "SELECT t.TransactionID, t.StudentNo, s.Department, t.MaterialType, t.Quantity, t.CollectionDate "
                            + "FROM Transactions t "
                            + "INNER JOIN Students s ON t.StudentNo = s.StudentNo "
                            + "WHERE t.StudentNo = ?";

                    PreparedStatement pst = con.prepareStatement(query);
                    pst.setString(1, studentIDStr);
                    ResultSet rs = pst.executeQuery();

                    model.setRowCount(0);

                    boolean found = false;
                    while (rs.next()) {
                        found = true;
                        // Using getLong to prevent overflow crash
                        model.addRow(new Object[]{
                            rs.getLong("TransactionID"),
                            rs.getLong("StudentNo"),
                            rs.getString("MaterialType"),
                            rs.getLong("Quantity"),
                            rs.getString("Department"),
                            rs.getTimestamp("CollectionDate")
                        });
                    }

                    if (!found) {
                        JOptionPane.showMessageDialog(History, "No transactions found for Student: " + studentIDStr);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(History, "Error: " + ex.getMessage());
                }
            });

            History.getContentPane().removeAll();
            History.add(TableS);
            History.add(SIDL_History);
            History.add(searchField);
            History.add(SearchButton);
            History.revalidate();
            History.repaint();
            History.setVisible(true);
        });

        //Main Panel
        MainP.setBackground(new Color(240, 255, 255));
        MainP.setPreferredSize(new Dimension(1, 1));
        MainP.setLayout(null);
        StudentCL = new JLabel("Students Weekly");
        StudentCL.setFont(new Font("Arial Black", Font.BOLD, 60));
        StudentCL.setBounds(270, 20, 700, 80);
        ContributionL = new JLabel("Contribution");
        ContributionL.setFont(new Font("Arial Black", Font.BOLD, 60));
        ContributionL.setBounds(350, 100, 700, 80);
        ColumnChart.setBounds(30, 200, 1030, 460);

        //Adding Components
        MainF.add(header, BorderLayout.NORTH);
        header.add(UnivImageP);
        UnivImageP.add(UnivIcon);
        header.add(UniversityL);
        header.add(AdminL);
        header.add(AdminButton);
        MainF.add(leftPanel, BorderLayout.WEST);
        leftPanel.add(UserImageP);
        UserImageP.add(UserIcon);
        leftPanel.add(AddContriButton);
        leftPanel.add(ViewContriButton);
        MainF.add(MainP, BorderLayout.CENTER);
        MainP.add(StudentCL);
        MainP.add(ContributionL);
        MainP.add(ColumnChart);

        MainF.setSize(1500, 800);
        MainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainF.setLocationRelativeTo(null);
        MainF.setVisible(true);
    }

    // Login Jpanel
    class LoginFunction extends JFrame {

        public LoginFunction() {
            AdminButton.addActionListener(e -> {
                Login.setSize(550, 270);
                Login.setLocationRelativeTo(null);
                Login.setLayout(null);
                LoginL = new JLabel("LOGIN");
                LoginL.setFont(new Font("Arial Black", Font.BOLD, 25));
                LoginL.setBounds(220, 10, 150, 40);
                Login.add(LoginL);

                UsernameL = new JLabel("Username:");
                UsernameL.setBounds(50, 60, 100, 25);
                Login.add(UsernameL);
                Usernamefield = new JTextField();
                Usernamefield.setBounds(115, 60, 297, 30);
                Login.add(Usernamefield);

                ClearButton1 = new JButton("Clear");
                ClearButton1.setBounds(410, 60, 80, 30);
                Login.add(ClearButton1);
                
                ClearButton2 = new JButton("Clear");
                ClearButton2.setBounds(410, 110, 80, 30);
                Login.add(ClearButton2);

                PasswordL = new JLabel("Password:");
                PasswordL.setBounds(50, 110, 100, 25);
                Login.add(PasswordL);

                Passwordfield = new JPasswordField();
                Passwordfield.setBounds(115, 110, 297, 30);
                Login.add(Passwordfield);

                EnterButton = new JButton("Enter");
                EnterButton.setBounds(115, 165, 125, 50);

                EnterButton.addActionListener(ev -> {
                    String user = Usernamefield.getText().trim();
                    String pass = new String(Passwordfield.getPassword());

                    try (Connection con = DBConnection.getConnection()) {
                        String sql = "SELECT * FROM Admins WHERE Username = ? AND Password = ?";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, user);
                        pst.setString(2, pass);
                        ResultSet rs = pst.executeQuery();

                        if (rs.next()) {
                            JOptionPane.showMessageDialog(Login, "Welcome Admin!");
                            new AdminFrame();
                            Login.dispose();
                            MainF.dispose();
                        } else {
                            JOptionPane.showMessageDialog(Login, "Invalid User/Pass", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                // Clears Userfield only
                ClearButton1.addActionListener(ev -> {
                    Usernamefield.setText("");
                });

                // Clears Passwordfield only
                ClearButton2.addActionListener(ev -> {
                    Passwordfield.setText("");
                });

                Login.add(EnterButton);
                CancelButton = new JButton("Cancel");
                CancelButton.setBounds(285, 165, 125, 50);
                Login.add(CancelButton);
                CancelButton.addActionListener(ev -> Login.dispose());

                Login.setVisible(true);
            });
        }
    }
}
