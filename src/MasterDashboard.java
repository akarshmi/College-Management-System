import javax.swing.*;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.table.DefaultTableModel;

import static javax.swing.SwingUtilities.paintComponent;

public class MasterDashboard extends JFrame implements ActionListener {
    JButton allStudent, newStudent, searchStudent, removeStudent, editStudent,logoutUser;
    JButton button1,button2,button3,button4,button5,button6;
    JFrame frame1 = new JFrame();
    ImageIcon icon =new ImageIcon("Assets/user.png");

    JLabel t1,t2,t3,t4,t5,t6,t7;

    MasterDashboard() {


        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JFrame frame = new JFrame("Master Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(237, 229, 154));

        frame.setPreferredSize(new Dimension(1030, 600));
        frame.setBounds(200,50,1300,600);



        allStudent = new JButton("All Student List");
        allStudent.setBounds(50, 120, 150, 30);
        frame.add(allStudent);

        searchStudent = new JButton("Search Student");
        searchStudent.setBounds(50, 170, 150, 30);
        frame.add(searchStudent);

        newStudent = new JButton("Add a Student");
        newStudent.setBounds(50, 220, 150, 30);
        frame.add(newStudent);

        removeStudent = new JButton("Remove a Student");
        removeStudent.setBounds(50, 270, 150, 30);
        frame.add(removeStudent);

        editStudent = new JButton("Edit a Student");
        editStudent.setBounds(50, 320, 150, 30);
        frame.add(editStudent);

        t1=new JLabel("Welcome Master Admin");
        t1.setBounds(300,50,500,35);
        t1.setFont(new Font("nothimng",Font.BOLD,40));
        frame.add(t1);

        t2 = new JLabel("Add User");
        t2.setBounds(465,260,130,50);
        t2.setFont(new Font("nothimng",Font.BOLD,25));
        frame.add(t2);

        t3 = new JLabel("Delete User");
        t3.setBounds(460,490,150,50);
        t3.setFont(new Font("nothimng",Font.BOLD,25));
        frame.add(t3);

        t4 = new JLabel("All Users");
        t4.setBounds(665,260,130,50);
        t4.setFont(new Font("nothimng",Font.BOLD,25));
        frame.add(t4);


        t5 = new JLabel("Search Users");
        t5.setBounds(650,490,150,50);
        t5.setFont(new Font("nothimng",Font.BOLD,25));
        frame.add(t5);

        t7 = new JLabel("Approve Users");
        t7.setBounds(830,260,170,50);
        t7.setFont(new Font("nothimng",Font.BOLD,20));
        frame.add(t7);

        t6 = new JLabel("Logout");
        t6.setBounds(860,490,150,50);
        t6.setFont(new Font("nothimng",Font.BOLD,25));
        frame.add(t6);

        // Replace these file paths with the paths to your images
        String imagePath1 = "Assets/add.png";
        String imagePath2 = "Assets/remove.png";
        String imagePath3 = "Assets/allUsers.png";
        String imagePath4 = "Assets/001.png";
        String imagePath6 = "Assets/icons8-logout-100.png";
        String imagePath5 = "Assets/accept.png";


        button1 = createImageButton(imagePath1);
        button2 = createImageButton(imagePath2);
        button3 = createImageButton(imagePath3);
        button4 = createImageButton(imagePath4);
        button5 = createImageButton(imagePath5);
        button6 = createImageButton(imagePath6);


        button1.setBounds(450, 120, 150, 150);
        button2.setBounds(450, 350, 150, 150);
        button3.setBounds(640, 120, 150, 150);
        button4.setBounds(640, 350, 150, 150);
        button5.setBounds(830, 120, 150, 150);
        button6.setBounds(830, 350, 150, 150);

        // Add ActionListener to each button
        button1.addActionListener(this);
     //   button1.addActionListener(createButtonActionListener("B1"));
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        allStudent.addActionListener(this);
        removeStudent.addActionListener(this);
        editStudent.addActionListener(this);
        newStudent.addActionListener(this);
        searchStudent.addActionListener(this);




        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(button6);
        frame.pack();

        frame.setIconImage(icon.getImage());

        frame.setVisible(true);


    }

    private static ActionListener createButtonActionListener(final String buttonName) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource().equals(buttonName)){

                }


            }
        };
    }

    private static JButton createImageButton(String imagePath) {
        JButton button = new JButton();
        try {
            // Load and set the image on the button
            Image image = ImageIO.read(new File(imagePath));
            button.setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(button1)){
           UserRegistrationForm urf = new UserRegistrationForm();
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
            this.dispose();
        }


        if (ae.getSource().equals(logoutUser)){
            UserLoginForm userLoginForm = new UserLoginForm();
            this.dispose();
        }
        //for new Student Addition
        if (ae.getSource().equals(newStudent)) {
            StudentRegistrationForm srf = new StudentRegistrationForm();
            this.dispose();
        }

        if (ae.getSource().equals(removeStudent)) {

            try {
                RemoveStudent removeStudent = new RemoveStudent();
                this.dispose();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        if(ae.getSource().equals(button4)){
            try {
                SearchUser searchUser = new SearchUser();
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
        if (ae.getSource().equals(button3)){
            Statement stmt = null;
            ResultSet rs1;
            try {
                stmt = ConnectionProvider.con.createStatement();
                rs1=stmt.executeQuery("SELECT * FROM urdx ;");

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

        if (ae.getSource().equals(button2)){
            try {
                RemoveUser removeUser = new RemoveUser();

            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        if (ae.getSource().equals(button6)){
            UserLoginForm urf = new UserLoginForm();
            System.exit(0);
            //this.dispose();
        }
        if (ae.getSource().equals(button5)){
        Approve ap = new Approve();
        }

    }

    public static void main(String[] args) {
        ConnectionProvider.con = ConnectionProvider.getConnection();
        MasterDashboard mdb = new MasterDashboard();
    }


}
