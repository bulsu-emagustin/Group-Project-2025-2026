package EDSxADS_Final_Project;

// UI and Layout Imports
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

// Swing Component Imports
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

// Event Handling Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Database and SQL Imports
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTextArea;

public class AdminFrame extends JFrame {

    // Variables
    JFrame AdminF;
    JPanel mainheader, header, UnivImageP, customMenuBar, mainPanel, panel1, panel2, panel3, RecordP, subheader1, RecordPleft, studentPleft, subheader2, dashboardP, studentP, plasticP, glassP, paperP, metalP, EWasteP, DepartmentDP;
    JTextField IDfield, FNamefield, MNamefield, LNamefield, Secfield;
    JLabel UnivIcon, UniversityL, AdminL, TitleL, UnivIDL, DepartmentL1, DepartmentL2, MTypeL, DashT, RegisterL, StudentID, StudentFN, StudentMN, StudentLN, StudentC, StudentS, StudentSP, StudentSY, StudentT, RecycleBinL, LocationL, StatusL, DelRL, UpdateL, SaveL;
    JTextArea StatusTA;
    JDialog RegisterD, BinsD;
    ImageIcon UnivL, ScaledUnivIcon;
    Image Logo;
    JButton AdminButton, RecordButton, RegisterButton, DashboardButton, StudentButton, BinButton, SearchButton, AddButton, CancelButton, EmptyBinButton, DeleteButton, UpdateButton, SaveButton;
    JButton StudentUpdateButton, StudentSaveButton, StudentDeleteButton;
    JLabel StudentUpdateL, StudentSaveL, StudentDelRL;
    JButton StudentSearchButton;
    String[][] Values = {};
    String[] Atributes = {"Transaction ID", "Bin ID", "Student ID", "Department", "Material Type", "Quantity", "Date"};
    String[] StudentAtributes = {"Student ID", "First Name", "Middle Name", "Last Name", "Course", "Section", "Specialization", "Year Level"};
    JTable Table, StudentTable;
    JScrollPane TableS, StudentTableS;
    JTextField StudentIDfield;
    JLabel StudentIDL;
    JComboBox<String> StudentDTypeBox;
    ImageIcon AMainBack1, AMainBack2, AMainBack3;
    JComboBox<String> DTypeBox, MTypeBox, LocationBox, CTypeBox, STypeBox, SYTypeBox;

    boolean isEditing = false;

    public AdminFrame() {
        // Containers & Arrays
        AdminF = new JFrame("University Recycle Zone");
        mainheader = new JPanel();
        header = new JPanel();
        UnivImageP = new JPanel();
        mainPanel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        customMenuBar = new JPanel();
        RecordP = new JPanel();
        subheader1 = new JPanel();
        RecordPleft = new JPanel();
        studentP = new JPanel();
        studentPleft = new JPanel();
        subheader2 = new JPanel();
        RegisterD = new JDialog();
        BinsD = new JDialog();

        // Selections for JComboBox
        String[] DType = {"All Departments", "IT", "Engineering", "Business", "Education", "Medical", "Law"};

        String[] MType = {"All Materials", "Plastic", "Glass", "Paper", "Metal", "E-Waste"};
        String[] LType = {"Engineering Building", "Canteen", "E-Library", "Pimentel"};
        String[] CType = {"BSIT", "BSCS", "BSIS", "BSCE", "BSEE", "BSME", "BSECE", "BSBA", "BSA", "BSMA", "BEEd", "BSEd", "BSN", "BSMT", "BSPSY", "BSCrim", "BPA", "LM"};
        String[] SType = {"Web and Mobile Development", "Cybersecurity", "Game Development", "Civil Engineering", "Mechanical Engineering", "Electrical Engineering", "Entrepreneurship",
            "Financial Management", "Business Analytics", "Special Education", "Math Education", "Science Education", "Medical Technology", "Nursing Fields", "Clinical Psychology",
            "Criminology", "Political Governance", "Forensic Science"};
        String[] SYType = {"1st Year", "2nd Year", "3rd Year", "4th Year"};

        // Dashboard Panels
        dashboardP = new JPanel();
        plasticP = new JPanel();
        glassP = new JPanel();
        paperP = new JPanel();
        metalP = new JPanel();
        EWasteP = new JPanel();
        DepartmentDP = new JPanel();

        // Set the values
        AdminStatsLoader loader = new AdminStatsLoader();

        // Fetch the data using the named  class (Kinukuha nito ang mga values gamit ang Class)
        loader.updateDepartmentCounts();
        loader.updateMaterialCounts();

        int total = loader.getPlastic() + loader.getGlass() + loader.getPaper()
                + loader.getMetal() + loader.getEWaste();

        //Donut Chart Panels
        DonutChartPanel plasticChart = new DonutChartPanel(loader.getPlastic(), Color.GREEN, "Plastic");
        DonutChartPanel glassChart = new DonutChartPanel(loader.getGlass(), Color.CYAN, "Glass");
        DonutChartPanel paperChart = new DonutChartPanel(loader.getPaper(), Color.YELLOW, "Paper");
        DonutChartPanel metalChart = new DonutChartPanel(loader.getMetal(), Color.GRAY, "Metal");
        DonutChartPanel ewasteChart = new DonutChartPanel(loader.getEWaste(), Color.RED, "E-Waste");

        // Backgrounds (Para into sa mga Panels sa CardLayout)
        AMainBack1 = new ImageIcon("Admin_Background.jpg");
        JLabel AMbackground1 = new JLabel(AMainBack1);
        AMbackground1.setLayout(null);
        AMbackground1.setBounds(0, 0, 1500, 625);

        AMainBack2 = new ImageIcon("Admin_Background.jpg");
        JLabel AMbackground2 = new JLabel(AMainBack2);
        AMbackground2.setLayout(null);
        AMbackground2.setBounds(0, 0, 1500, 625);

        AMainBack3 = new ImageIcon("Admin_Background.jpg");
        JLabel AMbackground3 = new JLabel(AMainBack3);
        AMbackground3.setLayout(null);
        AMbackground3.setBounds(0, 0, 1500, 625);

        // Layouts
        AdminF.setLayout(new BorderLayout());
        mainheader.setLayout(new BorderLayout());
        customMenuBar.setLayout(new FlowLayout(FlowLayout.LEADING));
        panel1.setLayout(null);
        panel2.setLayout(null);
        RecordP.setLayout(new BorderLayout());
        subheader1.setLayout(null);
        subheader2.setLayout(null);
        dashboardP.setLayout(null);
        RecordPleft.setLayout(null);
        studentPleft.setLayout(null);

        // Header Config
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
        AdminL = new JLabel("ADMIN");
        AdminL.setBounds(1240, 25, 120, 40);
        AdminL.setFont(new Font("Bodoni MT", Font.BOLD, 30));
        AdminButton = new JButton("Logout");
        AdminButton.setForeground(Color.red);
        AdminButton.setFont(new Font("Arial Black", Font.BOLD, 20));
        AdminButton.setBounds(1350, 20, 120, 50);

        // CardLayout Setup
        CardLayout cl = new CardLayout();
        mainPanel = new JPanel(cl);

        // Menu Bar
        customMenuBar.setBackground(Color.LIGHT_GRAY);
        customMenuBar.setPreferredSize(new Dimension(1500, 35));
        RecordButton = new JButton("Records");
        RegisterButton = new JButton("Register");
        DashboardButton = new JButton("Dashboard");
        StudentButton = new JButton("Students");
        BinButton = new JButton("Recycle Bin");

        // Panel 1 (Records)
        TitleL = new JLabel("Recycling Collection Monitoring System");
        TitleL.setFont(new Font("Bell MT", Font.BOLD, 50));
        TitleL.setBounds(310, 0, 1000, 100);
        RecordP.setBounds(150, 100, 1180, 700);
        subheader1.setBackground(Color.white);
        subheader1.setPreferredSize(new Dimension(50, 60));
        RecordPleft.setBackground(Color.LIGHT_GRAY);
        RecordPleft.setPreferredSize(new Dimension(150, 100));

        DepartmentL1 = new JLabel("Department: ");
        DepartmentL1.setBounds(30, 10, 100, 30);
        DTypeBox = new JComboBox<>(DType);
        DTypeBox.setBounds(105, 10, 170, 30);

        MTypeL = new JLabel("Material Type: ");
        MTypeL.setBounds(310, 10, 200, 30);
        MTypeBox = new JComboBox<>(MType);
        MTypeBox.setBounds(395, 10, 100, 30);

        UnivIDL = new JLabel("ID: ");
        UnivIDL.setBounds(860, 10, 30, 30);
        IDfield = new JTextField(50);
        IDfield.setBounds(880, 10, 200, 30);

        SearchButton = new JButton("Search");
        SearchButton.setBounds(1080, 10, 90, 30);

        DefaultTableModel model = new DefaultTableModel(Atributes, 0);
        Table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return isEditing;
            }
        };
        Table.getTableHeader().setReorderingAllowed(false);
        TableS = new JScrollPane(Table);

        UpdateL = new JLabel("Update Record");
        UpdateL.setBounds(33, 0, 100, 30);
        UpdateButton = new JButton("Update");
        UpdateButton.setBounds(25, 30, 100, 30);
        SaveL = new JLabel("Save Record");
        SaveL.setBounds(38, 70, 100, 30);
        SaveButton = new JButton("Save");
        SaveButton.setBounds(25, 100, 100, 30);
        DelRL = new JLabel("Delete Record");
        DelRL.setBounds(33, 145, 100, 30);
        DeleteButton = new JButton("Delete");
        DeleteButton.setBackground(Color.red);
        DeleteButton.setBounds(25, 175, 100, 30);

        // Panel 2 (Dashboard)
        DashT = new JLabel("Dashboard");
        DashT.setFont(new Font("Bell MT", Font.BOLD, 50));
        DashT.setBounds(50, 0, 1000, 100);
        dashboardP.setBackground(Color.white);
        dashboardP.setBounds(50, 100, 1385, 500);

        // Dashboard Components
        plasticChart.setBounds(10, 35, 250, 200);
        glassChart.setBounds(270, 35, 250, 200);
        paperChart.setBounds(530, 35, 250, 200);
        metalChart.setBounds(140, 270, 250, 200);
        ewasteChart.setBounds(400, 270, 250, 200);
        DepartmentDP.setBounds(800, 10, 570, 480);

        // Adding Components to the Dashboard Panel
        dashboardP.add(plasticChart);
        dashboardP.add(glassChart);
        dashboardP.add(paperChart);
        dashboardP.add(metalChart);
        dashboardP.add(ewasteChart);
        dashboardP.add(DepartmentDP);

        // Panel 3 (Student)
        StudentT = new JLabel("Student Records");
        StudentT.setFont(new Font("Bell MT", Font.BOLD, 50));
        StudentT.setBounds(565, 0, 1000, 100);
        studentP.setLayout(new BorderLayout());
        studentP.setBounds(150, 100, 1180, 700);
        subheader2.setBackground(Color.white);
        subheader2.setPreferredSize(new Dimension(1180, 60));
        studentPleft.setBackground(Color.LIGHT_GRAY);
        studentPleft.setPreferredSize(new Dimension(150, 100));

        DepartmentL2 = new JLabel("Department: ");
        DepartmentL2.setBounds(30, 10, 100, 30);
        StudentDTypeBox = new JComboBox<>(DType);
        StudentDTypeBox.setBounds(105, 10, 170, 30);

        StudentIDL = new JLabel("ID: ");
        StudentIDL.setBounds(860, 10, 30, 30);
        StudentIDfield = new JTextField(50);
        StudentIDfield.setBounds(880, 10, 200, 30);

        StudentSearchButton = new JButton("Search");
        StudentSearchButton.setBounds(1080, 10, 90, 30);

        DefaultTableModel model2 = new DefaultTableModel(StudentAtributes, 0);
        StudentTable = new JTable(model2) {
            public boolean isCellEditable(int row, int column) {
                return isEditing;
            }
        };
        StudentTable.getTableHeader().setReorderingAllowed(false);
        StudentTableS = new JScrollPane(StudentTable);

        StudentUpdateL = new JLabel("Update Record");
        StudentUpdateL.setBounds(33, 0, 100, 30);
        StudentUpdateButton = new JButton("Update");
        StudentUpdateButton.setBounds(25, 30, 100, 30);
        StudentSaveL = new JLabel("Save Record");
        StudentSaveL.setBounds(38, 70, 100, 30);
        StudentSaveButton = new JButton("Save");
        StudentSaveButton.setBounds(25, 100, 100, 30);
        StudentDelRL = new JLabel("Delete Student");
        StudentDelRL.setBounds(32, 145, 100, 30);
        StudentDeleteButton = new JButton("Delete");
        StudentDeleteButton.setBackground(Color.red);
        StudentDeleteButton.setBounds(25, 175, 100, 30);

        // CardLayout Navigation (Para sa mga buttons)
        RecordButton.addActionListener(new PanelSwitcher(mainPanel, cl, "panel1"));
        DashboardButton.addActionListener(new PanelSwitcher(mainPanel, cl, "panel2"));
        StudentButton.addActionListener(new PanelSwitcher(mainPanel, cl, "panel3"));

        // Load initial table data automatically
        FilterRecords initialRecordFilter = new FilterRecords(IDfield, DTypeBox, MTypeBox, Table);
        FilterStudents initialStudentFilter = new FilterStudents(StudentIDfield, StudentDTypeBox, StudentTable);
        initialRecordFilter.actionPerformed(null);
        initialStudentFilter.actionPerformed(null);

        // Logout
        AdminButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Logout?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                AdminF.dispose();
                new UniversityRecycleZone();
            }
        });

        // Search Action
        SearchButton.addActionListener(initialRecordFilter);

        // Student Search Action
        StudentSearchButton.addActionListener(initialStudentFilter);

        // Register Dialog Trigger
        RegisterButton.addActionListener(e -> {
            RegisterD = new JDialog(AdminF, "Register Student", true);
            RegisterD.setSize(500, 620);
            RegisterD.setLocationRelativeTo(null);
            RegisterD.setLayout(null);

            RegisterL = new JLabel("Register Student");
            RegisterL.setFont(new Font("Arial", Font.BOLD, 20));
            RegisterL.setBounds(165, 10, 300, 50);

            StudentID = new JLabel("Student ID: ");
            StudentID.setBounds(72, 70, 100, 30);

            IDfield = new JTextField(50);
            IDfield.setBounds(140, 70, 280, 30);

            StudentFN = new JLabel("First Name: ");
            StudentFN.setBounds(70, 125, 100, 30);

            FNamefield = new JTextField(50);
            FNamefield.setBounds(140, 125, 280, 30);

            StudentMN = new JLabel("Middle Name: ");
            StudentMN.setBounds(58, 180, 100, 30);

            MNamefield = new JTextField(50);
            MNamefield.setBounds(140, 180, 280, 30);

            StudentLN = new JLabel("Last Name: ");
            StudentLN.setBounds(72, 235, 100, 30);

            LNamefield = new JTextField(50);
            LNamefield.setBounds(140, 235, 280, 30);

            StudentC = new JLabel("Course: ");
            StudentC.setBounds(89, 290, 100, 30);

            CTypeBox = new JComboBox<>(CType);
            CTypeBox.setBounds(140, 290, 280, 30);

            StudentS = new JLabel("Section: ");
            StudentS.setBounds(87, 345, 100, 30);

            Secfield = new JTextField(50);
            Secfield.setBounds(140, 345, 280, 30);

            StudentSP = new JLabel("Specialization: ");
            StudentSP.setBounds(51, 400, 200, 30);

            STypeBox = new JComboBox<>(SType);
            STypeBox.setBounds(140, 400, 280, 30);

            StudentSY = new JLabel("Year Level: ");
            StudentSY.setBounds(61, 455, 100, 30);

            SYTypeBox = new JComboBox<>(SYType);
            SYTypeBox.setBounds(140, 455, 280, 30);

            //Buttons
            RegisterButton = new JButton("Register");
            RegisterButton.setBounds(100, 515, 130, 50);

            //Register Student
            RegisterStudent Registration = new RegisterStudent();

            RegisterButton.addActionListener(ex -> {
                //check if fields are empty
                if (IDfield.getText().isBlank() || FNamefield.getText().isBlank()
                        || LNamefield.getText().isBlank() || Secfield.getText().isBlank()) {

                    JOptionPane.showMessageDialog(RegisterD, "All fields (except Middle Name) are required!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Registration.studentRegistration();
                }
            });

            CancelButton = new JButton("Cancel");
            CancelButton.setBounds(260, 515, 130, 50);
            CancelButton.addActionListener(ev -> RegisterD.dispose());

            //Adding Components
            RegisterD.add(RegisterL);
            RegisterD.add(StudentID);
            RegisterD.add(IDfield);
            RegisterD.add(StudentFN);
            RegisterD.add(FNamefield);
            RegisterD.add(StudentMN);
            RegisterD.add(MNamefield);
            RegisterD.add(StudentLN);
            RegisterD.add(LNamefield);
            RegisterD.add(StudentC);
            RegisterD.add(CTypeBox);
            RegisterD.add(StudentS);
            RegisterD.add(Secfield);
            RegisterD.add(StudentSP);
            RegisterD.add(STypeBox);
            RegisterD.add(StudentSY);
            RegisterD.add(SYTypeBox);
            RegisterD.add(RegisterButton);
            RegisterD.add(CancelButton);
            RegisterD.setVisible(true);
        });

        //Bins Dialog Trigger
        BinButton.addActionListener(e -> {
            BinsD = new JDialog(AdminF, "Recycle Status", true);
            BinsD.setSize(500, 280);
            BinsD.setLocationRelativeTo(null);
            BinsD.setLayout(null);

            RecycleBinL = new JLabel("Recycle Bin Status");
            RecycleBinL.setFont(new Font("Arial", Font.BOLD, 20));
            RecycleBinL.setBounds(155, 10, 300, 50);
            LocationL = new JLabel("Location: ");
            LocationL.setBounds(50, 70, 60, 30);
            LocationBox = new JComboBox<>(LType);
            LocationBox.setBounds(105, 70, 330, 30);
            StatusL = new JLabel("Status: ");
            StatusL.setBounds(61, 120, 50, 30);
            StatusTA = new JTextArea();
            StatusTA.setBounds(105, 123, 330, 30);
            EmptyBinButton = new JButton("Empty Bin");
            EmptyBinButton.setBounds(80, 180, 150, 50);

            Bins bins = new Bins();
            String empty = StatusTA.toString();

            LocationBox.addActionListener(ev -> {
                String location = LocationBox.getSelectedItem().toString();
                StatusTA.setText(Bins.getBinStatusByLocation(location));
            });

            EmptyBinButton.addActionListener(ev -> bins.clearBin(empty));

            CancelButton = new JButton("Cancel");
            CancelButton.setBounds(250, 180, 150, 50);
            CancelButton.addActionListener(ev -> BinsD.dispose());

            //Adding Components
            BinsD.add(RecycleBinL);
            BinsD.add(LocationL);
            BinsD.add(LocationBox);
            BinsD.add(StatusL);
            BinsD.add(StatusTA);
            BinsD.add(EmptyBinButton);
            BinsD.add(CancelButton);
            BinsD.setVisible(true);
        });

        // Update Button Logic
        UpdateButton.addActionListener(e -> {
            int selectedRow = Table.getSelectedRow();

            if (!isEditing) {
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Select row to update");
                    return;
                }

                isEditing = true;

                // Enable editing
                Table.setDefaultEditor(Object.class, new javax.swing.DefaultCellEditor(new JTextField()));

                JOptionPane.showMessageDialog(null, "You can now edit the selected row.\nPress Save to save changes.");
            }
        });

        // Save Button Logic
        SaveButton.addActionListener(e -> {
            if (!isEditing) {
                JOptionPane.showMessageDialog(null, "Press Update first to enable editing.");
                return;
            }

            if (Table.isEditing()) {
                Table.getCellEditor().stopCellEditing();
            }

            int selectedRow = Table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "No row selected.");
                return;
            }

            try (Connection con = DBConnection.getConnection()) {
                DefaultTableModel tableModel = (DefaultTableModel) Table.getModel();

                int transactionId = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                String department = tableModel.getValueAt(selectedRow, 3).toString();
                String material = tableModel.getValueAt(selectedRow, 4).toString();
                int quantity = Integer.parseInt(tableModel.getValueAt(selectedRow, 5).toString());

                String sql = "UPDATE Transactions t "
                        + "INNER JOIN Students s ON t.StudentNo = s.StudentNo "
                        + "SET s.Department=?, t.MaterialType=?, t.Quantity=? "
                        + "WHERE t.TransactionID=?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, department);
                pst.setString(2, material);
                pst.setInt(3, quantity);
                pst.setInt(4, transactionId);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Record saved!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Connection Error");
            }

            isEditing = false;
            Table.setDefaultEditor(Object.class, null);
        });

        // Delete Button Logic
        DeleteButton.addActionListener(e -> {
            int[] selectedRows = Table.getSelectedRows();

            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(null, "Select row(s) to delete");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete selected record(s)?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try (Connection con = DBConnection.getConnection()) {

                DefaultTableModel tableModel = (DefaultTableModel) Table.getModel();

                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int row = selectedRows[i];

                    int transactionId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

                    String sql = "DELETE FROM Transactions WHERE TransactionID = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, transactionId);
                    pst.executeUpdate();

                    tableModel.removeRow(row);
                }

                JOptionPane.showMessageDialog(null, "Record(s) deleted!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Connection Error");
            }
        });

        // Update button for Student Records
        StudentUpdateButton.addActionListener(e -> {
            int selectedRow = StudentTable.getSelectedRow();

            if (!isEditing) {
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Select row to update");
                    return;
                }

                isEditing = true;

                // Enable editing
                StudentTable.setDefaultEditor(Object.class, new javax.swing.DefaultCellEditor(new JTextField()));

                JOptionPane.showMessageDialog(null, "You can now edit the selected row.\nPress Save to save changes.");
            }
        });

        // Student Save Button Logic
        StudentSaveButton.addActionListener(e -> {
            if (!isEditing) {
                JOptionPane.showMessageDialog(null, "Press Update first to enable editing.");
                return;
            }

            if (StudentTable.isEditing()) {
                StudentTable.getCellEditor().stopCellEditing();
            }

            int selectedRow = StudentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "No row selected.");
                return;
            }

            try (Connection con = DBConnection.getConnection()) {
                DefaultTableModel tableModel = (DefaultTableModel) StudentTable.getModel();

                int studentNo = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
                String firstName = tableModel.getValueAt(selectedRow, 1).toString();
                String middleName = tableModel.getValueAt(selectedRow, 2).toString();
                String lastName = tableModel.getValueAt(selectedRow, 3).toString();
                String course = tableModel.getValueAt(selectedRow, 4).toString();
                String section = tableModel.getValueAt(selectedRow, 5).toString();
                String specialization = tableModel.getValueAt(selectedRow, 6).toString();
                String YearLevel = tableModel.getValueAt(selectedRow, 7).toString();

                String sql = "UPDATE Students SET FirstName=?, MiddleName=?, LastName=?, Course=?, Section=?, Specialization=?, YearLevel=? WHERE StudentNo=?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, firstName);
                pst.setString(2, middleName);
                pst.setString(3, lastName);
                pst.setString(4, course);
                pst.setString(5, section);
                pst.setString(6, specialization);
                pst.setString(7, YearLevel);
                pst.setInt(8, studentNo);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Student record saved!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

            isEditing = false;
            StudentTable.setDefaultEditor(Object.class, null);
        });

        // Student Delete Button Logic
        StudentDeleteButton.addActionListener(e -> {
            int[] selectedRows = StudentTable.getSelectedRows();

            if (selectedRows.length == 0) {
                JOptionPane.showMessageDialog(null, "Select row(s) to delete");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to delete selected student(s)?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try (Connection con = DBConnection.getConnection()) {
                DefaultTableModel tableModel = (DefaultTableModel) StudentTable.getModel();

                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    int row = selectedRows[i];
                    int studentNo = Integer.parseInt(tableModel.getValueAt(row, 0).toString());

                    String sql = "DELETE FROM Students WHERE StudentNo = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setInt(1, studentNo);
                    pst.executeUpdate();

                    tableModel.removeRow(row);
                }

                JOptionPane.showMessageDialog(null, "Student(s) deleted!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Connection Error");
            }
        });

        // Dashboard (Pie Chart)
        //instantiate variables
        String[] depts = {"IT", "Engineering", "Business", "Education", "Medical", "Law"};
        double[] vals = loader.getCountsArray();
        Color[] cols = {Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK};

        //creation of the chart
        PieChartPanel pieChart = new PieChartPanel(depts, vals, cols);
        DepartmentDP.setLayout(new BorderLayout());
        DepartmentDP.add(pieChart, BorderLayout.CENTER);

        // Custom Header and Custom Menu Bar Assembly
        AdminF.add(mainheader, BorderLayout.NORTH);
        mainheader.add(header, BorderLayout.NORTH);
        mainheader.add(customMenuBar, BorderLayout.CENTER);
        customMenuBar.add(RecordButton);
        customMenuBar.add(RegisterButton);
        customMenuBar.add(DashboardButton);
        customMenuBar.add(StudentButton);
        customMenuBar.add(BinButton);

        //Main Panel
        AdminF.add(mainPanel, BorderLayout.CENTER);

        //Panel 1 Components
        mainPanel.add(panel1, "panel1");
        panel1.add(AMbackground1);
        AMbackground1.add(TitleL);
        AMbackground1.add(RecordP);
        RecordP.add(subheader1, BorderLayout.NORTH);
        subheader1.add(DepartmentL1);
        subheader1.add(DTypeBox);
        subheader1.add(MTypeL);
        subheader1.add(MTypeBox);
        subheader1.add(UnivIDL);
        subheader1.add(IDfield);
        subheader1.add(SearchButton);
        RecordP.add(TableS, BorderLayout.CENTER);
        RecordP.add(RecordPleft, BorderLayout.EAST);
        RecordPleft.add(SaveL);
        RecordPleft.add(SaveButton);
        RecordPleft.add(UpdateL);
        RecordPleft.add(UpdateButton);
        RecordPleft.add(DelRL);
        RecordPleft.add(DeleteButton);

        //Panel 2 Components
        mainPanel.add(panel2, "panel2");
        panel2.add(AMbackground2);
        AMbackground2.add(DashT);
        AMbackground2.add(dashboardP);

        //Panel 3 Components
        mainPanel.add(panel3, "panel3");
        panel3.setLayout(new BorderLayout());
        panel3.add(AMbackground3);
        AMbackground3.add(StudentT);
        AMbackground3.add(studentP);
        studentP.add(subheader2, BorderLayout.NORTH);
        subheader2.add(DepartmentL2);
        subheader2.add(StudentDTypeBox);
        subheader2.add(StudentIDL);
        subheader2.add(StudentIDfield);
        subheader2.add(StudentSearchButton);
        studentP.add(StudentTableS, BorderLayout.CENTER);
        studentP.add(studentPleft, BorderLayout.EAST);
        studentPleft.add(StudentSaveL);
        studentPleft.add(StudentSaveButton);
        studentPleft.add(StudentUpdateL);
        studentPleft.add(StudentUpdateButton);
        studentPleft.add(StudentDelRL);
        studentPleft.add(StudentDeleteButton);

        //Adding Header Components in the Admin Frame
        header.add(UnivImageP);
        UnivImageP.add(UnivIcon);
        header.add(UniversityL);
        header.add(AdminL);
        header.add(AdminButton);

        // Admin Frame Settings
        AdminF.setSize(1500, 800);
        AdminF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AdminF.setLocationRelativeTo(null);
        AdminF.setVisible(true);
    }

    // Named class
    class PanelSwitcher implements ActionListener {

        private JPanel mainPanel;
        private CardLayout cl;
        private String target;

        public PanelSwitcher(JPanel mp, CardLayout c, String t) {
            this.mainPanel = mp;
            this.cl = c;
            this.target = t;
        }

        public void actionPerformed(ActionEvent e) {
            cl.show(mainPanel, target);
        }
    }

    class FilterRecords implements ActionListener {

        private JTextField idF;
        private JComboBox<String> dBox;
        private JComboBox<String> mBox;
        private JTable table;

        public FilterRecords(JTextField i, JComboBox<String> d, JComboBox<String> m, JTable t) {
            this.idF = i;
            this.dBox = d;
            this.mBox = m;
            this.table = t;
        }

        public void actionPerformed(ActionEvent e) {
            String id = idF.getText().trim();
            String dept = dBox.getSelectedItem().toString();
            String mat = mBox.getSelectedItem().toString();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            // Convert "All" selections to SQL wildcard (%)
            String deptSearch = dept.equals("All Departments") ? "%" : dept;
            String matSearch = mat.equals("All Materials") ? "%" : mat;

            try (Connection con = DBConnection.getConnection()) {

                String sql = "SELECT t.TransactionID, t.BinID, s.StudentNo, s.Department, t.MaterialType, t.Quantity, t.CollectionDate "
                        + "FROM Transactions t "
                        + "INNER JOIN Students s ON t.StudentNo = s.StudentNo "
                        + "WHERE (t.StudentNo = ? OR ? = '') "
                        + "AND s.Department LIKE ? "
                        + "AND t.MaterialType LIKE ? "
                        + "ORDER BY t.TransactionID ASC";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, id);
                pst.setString(3, deptSearch);
                pst.setString(4, matSearch);

                ResultSet rs = pst.executeQuery();
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("TransactionID"),
                        rs.getObject("BinID"),
                        rs.getInt("StudentNo"),
                        rs.getString("Department"),
                        rs.getString("MaterialType"),
                        rs.getInt("Quantity"),
                        rs.getTimestamp("CollectionDate")
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, null + "CONNECTION ERROR");
            }
        }
    }

    class FilterStudents implements ActionListener {

        private JTextField idF;
        private JComboBox<String> dBox;
        private JTable table;

        public FilterStudents(JTextField i, JComboBox<String> d, JTable t) {
            this.idF = i;
            this.dBox = d;
            this.table = t;
        }

        public void actionPerformed(ActionEvent e) {
            String id = idF.getText().trim();
            String dept = dBox.getSelectedItem().toString();
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            String deptSearch = dept.equals("All Departments") ? "%" : dept;

            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT StudentNo, FirstName, MiddleName, LastName, Course, Section, Specialization, YearLevel "
                        + "FROM Students "
                        + "WHERE (StudentNo = ? OR ? = '') "
                        + "AND Department LIKE ?";

                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, id);
                pst.setString(2, id);
                pst.setString(3, deptSearch);

                ResultSet rs = pst.executeQuery();
                model.setRowCount(0);
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("StudentNo"),
                        rs.getString("FirstName"),
                        rs.getString("MiddleName"),
                        rs.getString("LastName"),
                        rs.getString("Course"),
                        rs.getString("Section"),
                        rs.getString("Specialization"),
                        rs.getString("YearLevel")
                    });
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Connection Error");
            }
        }
    }

    class AdminStatsLoader {

        private int itCount = 0;
        private int engCount = 0;
        private int busCount = 0;
        private int eduCount = 0;
        private int medCount = 0;
        private int lawCount = 0;

        private int plasticTot, glassTot, paperTot, metalTot, eWasteTot;

        //method for fetching Material count
        public void updateMaterialCounts() {

            plasticTot = glassTot = paperTot = metalTot = eWasteTot = 0;
            String sql = "SELECT MaterialType, "
                    + "(SUM(Quantity) * 100.0 / (SELECT SUM(Quantity) FROM Transactions)) AS Total "
                    + "FROM Transactions GROUP BY MaterialType";

            try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    String mat = rs.getString("MaterialType");
                    double total = rs.getDouble("Total");

                    switch (mat) {
                        case "Plastic":
                            plasticTot = (int) Math.round(total);
                            break;
                        case "Glass":
                            glassTot = (int) Math.round(total);
                            break;
                        case "Paper":
                            paperTot = (int) Math.round(total);
                            break;
                        case "Metal":
                            metalTot = (int) Math.round(total);
                            break;
                        case "E-Waste":
                            eWasteTot = (int) Math.round(total);
                            break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //method for fetching Deparment counts
        public void updateDepartmentCounts() {

            itCount = engCount = busCount = eduCount = medCount = lawCount = 0;

            String sql = "SELECT Department, COUNT(*) AS Total FROM Students GROUP BY Department";

            try (Connection con = DBConnection.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet rs = pst.executeQuery()) {

                while (rs.next()) {
                    String dept = rs.getString("Department");
                    int total = rs.getInt("Total");

                    //
                    switch (dept) {
                        case "IT":
                            itCount = total;
                            break;
                        case "Engineering":
                            engCount = total;
                            break;
                        case "Business":
                            busCount = total;
                            break;
                        case "Education":
                            eduCount = total;
                            break;
                        case "Medical":
                            medCount = total;
                            break;
                        case "Law":
                            lawCount = total;
                            break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // return the val to an array
        public double[] getCountsArray() {
            return new double[]{itCount, engCount, busCount, eduCount, medCount, lawCount};
        }

        //return material val to the charts
        public int getPlastic() {
            return plasticTot;
        }

        public int getGlass() {
            return glassTot;
        }

        public int getPaper() {
            return paperTot;
        }

        public int getMetal() {
            return metalTot;
        }

        public int getEWaste() {
            return eWasteTot;
        }
    }

    class RegisterStudent {

        private String StudID;
        private String StudFname;
        private String StudMname;
        private String StudLname;
        private String Course;
        private String Department;
        private String Section;
        private String StudSpec;
        private String YearLevel;

        private String getDepartmentByCourse(String course) {
            switch (course) {
                case "BSIT":
                case "BSCS":
                case "BSIS":
                    return "IT";
                case "BSCE":
                case "BSEE":
                case "BSME":
                case "BSECE":
                    return "Engineering";
                case "BSBA":
                case "BSA":
                case "BSMA":
                    return "Business";
                case "BEEd":
                case "BSEd":
                    return "Education";
                case "BSN":
                case "BSPSY":
                    return "Medical";
                case "BSMT":
                case "BSCrim":
                case "BPA":
                case "LM":
                    return "Law";
                default:
                    return "Unknown";
            }
        }

        public void studentRegistration() {
            try (Connection con = DBConnection.getConnection()) {

                StudID = IDfield.getText();
                StudFname = FNamefield.getText();
                StudMname = MNamefield.getText();
                StudLname = LNamefield.getText();
                Course = CTypeBox.getSelectedItem().toString();
                Department = getDepartmentByCourse(Course);
                Section = Secfield.getText();
                StudSpec = STypeBox.getSelectedItem().toString();
                YearLevel = SYTypeBox.getSelectedItem().toString();

                String sql = "INSERT INTO Students (StudentNo, FirstName, MiddleName, LastName, Department, Course, Section, Specialization, YearLevel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, StudID);
                pst.setString(2, StudFname);
                pst.setString(3, StudMname);
                pst.setString(4, StudLname);
                pst.setString(5, Department);
                pst.setString(6, Course);
                pst.setString(7, Section);
                pst.setString(8, StudSpec);
                pst.setString(9, YearLevel);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Student record saved!");
                RegisterD.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Connection Error");
            }
        }

    }
}
