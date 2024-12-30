
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserDashboard extends JFrame implements ActionListener {

    JLabel title, label, l1, l2, l3;
    JFrame frame1 = new JFrame();
    JButton allStudent, newStudent, searchStudent, removeStudent, editStudent,logoutUser;

    public UserDashboard() throws HeadlessException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        setLayout(null);
        setBounds(450, 100, 600, 600);
        setVisible(true);
        setTitle("Welcome to Dashboard");
        getContentPane().setBackground(new Color(223, 255, 216));

        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
        //   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        title = new JLabel("<html><u><b>Welcome to Dashboard<b><u><html>");
        title.setBounds(130, 20, 350, 50);
        title.setFont(new Font("Ariel", Font.BOLD, 30));
        title.setForeground(Color.orange);
        add(title);

        allStudent = new JButton("All Student List");
        allStudent.setBounds(50, 100, 150, 30);
        add(allStudent);

        searchStudent = new JButton("Search Student");
        searchStudent.setBounds(50, 150, 150, 30);
        add(searchStudent);

        newStudent = new JButton("Add a Student");
        newStudent.setBounds(50, 200, 150, 30);
        add(newStudent);

        removeStudent = new JButton("Remove a Student");
        removeStudent.setBounds(50, 250, 150, 30);
        add(removeStudent);

        editStudent = new JButton("Edit a Student");
        editStudent.setBounds(50, 300, 150, 30);
        add(editStudent);

        logoutUser = new JButton("Logout");
        logoutUser.setBounds(50, 350, 150, 30);
        add(logoutUser);




        BufferedImage imageMale = null;
        BufferedImage imageFemale = null;
        try {
            imageMale = ImageIO.read(new File("Assets/00200.png")); // Replace with your image path
            imageFemale = ImageIO.read(new File("Assets/000000.png")); // Replace with your image path
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (imageMale != null && imageFemale != null) {
            if (UserLoginForm.userGender.equalsIgnoreCase("male")){
                label = new JLabel(new ImageIcon(imageMale));
                label.setBounds(250, 0, 300, 300); // Set bounds here
            }else if (UserLoginForm.userGender.equalsIgnoreCase("female")) {
                label = new JLabel(new ImageIcon(imageFemale));
                label.setBounds(250, 0, 300, 300); // Set bounds here
            }
        } else {
            label = new JLabel("Image not found");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBounds(200, 0, 300, 300); // Set bounds for error label
        }
        add(label);

        l1 = new JLabel("User Name");
        l1.setBounds(300, 220, 200, 50);
        l1.setFont(new Font("Ariel", Font.BOLD, 25));
        l1.setText(UserLoginForm.userName);
        add(l1);

        l2 = new JLabel("Login Time");
        l2.setBounds(300, 255, 200, 55);
        l2.setFont(new Font("Ariel", Font.BOLD, 15));
        add(l2);


        l3 = new JLabel();
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        l3.setText(formattedTime);
        l3.setFont(new Font("Ariel", Font.BOLD, 25));
        l3.setBounds(300, 300, 400, 50);
        add(l3);

        removeStudent.addActionListener(this);
        editStudent.addActionListener(this);
        allStudent.addActionListener(this);
        searchStudent.addActionListener(this);
        newStudent.addActionListener(this);
        logoutUser.addActionListener(this);

     }


    @Override
    public void actionPerformed(ActionEvent ae) {


        if (ae.getSource().equals(logoutUser)){
            UserLoginForm userLoginForm = new UserLoginForm();
            this.dispose();
        }
        //for new Student Addition
        if (ae.getSource().equals(newStudent)) {
            StudentRegistrationForm srf = new StudentRegistrationForm();
            //this.dispose();
        }

        if (ae.getSource().equals(removeStudent)) {

            try {
                RemoveStudent removeStudent = new RemoveStudent();
                //this.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        if (ae.getSource().equals(allStudent)) {
           // String allSearch = "SELECT sname, fname, mname, gender, address, phno, emailid, rid FROM srd ;";
            //String allSearch = "SELECT sname, fname, mname, gender, address, phno, emailid, rid FROM srd ";
            Statement stmt = null;
            ResultSet rs1;
            try {
                stmt = ConnectionProvider.con.createStatement();
                rs1=stmt.executeQuery("SELECT * FROM srdx ;");

               // rs1 = stmt.executeQuery(allSearch);
                DefaultTableModel model1 = new DefaultTableModel();
                // Retrieve metadata from ResultSet to set column names for the model
                int columnCount = rs1.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    model1.addColumn(rs1.getMetaData().getColumnName(i));
                }
                // Process the retrieved data from the ResultSet
                while (rs1.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        row[i - 1] = rs1.getObject(i);
                    }
                    model1.addRow(row);
                }
                JTable table = new JTable(model1);
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setPreferredSize(new Dimension(1200, 400));

                frame1.getContentPane().removeAll();
                frame1.getContentPane().add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the frame1
                frame1.pack();
                frame1.setLocationRelativeTo(null);
                frame1.setVisible(true);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

        if (ae.getSource().equals(searchStudent)){
            try {
                SearchStudent searchStudent = new SearchStudent();
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
          //  this.dispose();
        }

    }


    public static void main (String[]args){
        Connection con = ConnectionProvider.getConnection();
                try {
                    UserDashboard udb = new UserDashboard();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

}
