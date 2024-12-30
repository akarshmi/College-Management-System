import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;
import java.text.*;

public class StudentRegistrationForm extends JFrame implements ActionListener {

    private String namestr, fnamestr, mnamestr, genderstr, addressstr, phonestr, emailstr, regidstr;
    private JComboBox selector;
    private JLabel title, name, fname, mname, gender, address, phone, email, rid, decl, connectionWarning,userType, genderEmptyLabel, chkboxLabel, genderEmptyLabel1, dataEmptyLabel;
    private JTextField namet, fnamet, mnamet, phonet, emailt, ridt;
    private JTextArea addresst;
    private JRadioButton genderbtn1, genderbtn2;
    private JCheckBox cnfbox;
    static JFrame frame = new JFrame("Student Registration Form");
    private JButton submit, reset, back,home;
    private JDialog conFailedDialog, genderEmpty, chkbox, dataEmpty;

    public void resetAction() {
        namet.setText("");
        fnamet.setText("");
        mnamet.setText("");
        phonet.setText("");
        emailt.setText("");
        ridt.setText("");
        addresst.setText("");
        cnfbox.setSelected(false);
        genderbtn2.setSelected(false);
        genderbtn1.setSelected(false);
    }



    public static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();



        // Create column names
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = metaData.getColumnName(i);
        }

        // Create data for the table
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = resultSet.getObject(i);
            }
            tableModel.addRow(rowData);
        }
        return tableModel;
    }





    public StudentRegistrationForm() {


        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        setLayout(null);
        setVisible(true);
        setBounds(490, 50, 550, 650);
        setTitle("Student Registration Form");
        getContentPane().setBackground(new Color(193, 222, 88));


        //Adding Labels
        title = new JLabel("REGISTRATION FORM");
        title.setBounds(100, 5, 500, 50);
        title.setFont(new Font("Times Roman", Font.BOLD, 30));
        add(title);

        name = new JLabel("Name :");
        name.setBounds(10, 50, 100, 50);
        name.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(name);

        fname = new JLabel("Father's Name :");
        fname.setBounds(10, 100, 200, 50);
        fname.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(fname);

        mname = new JLabel("Mother's Name :");
        mname.setBounds(10, 150, 200, 50);
        mname.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(mname);

        gender = new JLabel("Gender");
        gender.setBounds(10, 200, 100, 50);
        gender.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(gender);

        address = new JLabel("Address :");
        address.setBounds(10, 250, 100, 50);
        address.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(address);

        phone = new JLabel("Phone No: ");
        phone.setBounds(10, 350, 130, 50);
        phone.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(phone);

        email = new JLabel("Email Id :");
        email.setBounds(10, 400, 100, 50);
        email.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(email);

        rid = new JLabel("Registration Id :");
        rid.setBounds(10, 450, 200, 50);
        rid.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(rid);




        /// adding TextBoxes

        namet = new JTextField();
        namet.setBounds(200, 60, 300, 32);
        namet.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(namet);

        fnamet = new JTextField();
        fnamet.setBounds(200, 110, 300, 32);
        fnamet.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(fnamet);

        mnamet = new JTextField();
        mnamet.setBounds(200, 160, 300, 32);
        mnamet.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(mnamet);
        addresst = new JTextArea(10, 10);
        addresst.setBounds(200, 260, 300, 95);
        addresst.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(addresst);

        phonet = new JTextField();
        phonet.setBounds(200, 360, 300, 32);
        phonet.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(phonet);

        emailt = new JTextField();
        emailt.setBounds(200, 410, 300, 32);
        emailt.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(emailt);

        ridt = new JTextField();
        ridt.setBounds(200, 460, 300, 32);
        ridt.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(ridt);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(genderbtn2);
        genderGroup.add(genderbtn1);
        genderbtn1 = new JRadioButton("MALE");
        genderbtn1.setBounds(200, 210, 100, 32);
        genderbtn2 = new JRadioButton("FEMALE");
        add(genderbtn1);
        genderbtn2.setBounds(300, 210, 100, 32);
        add(genderbtn2);

        cnfbox = new JCheckBox();
        cnfbox.setBounds(200, 500, 30, 30);
        add(cnfbox);


        decl = new JLabel("I herby declare that every data is accurate. ");
        decl.setBounds(230, 520, 300, 20);
        add(decl);

        submit = new JButton("Sumbit");
        submit.setBounds(230, 550, 80, 32);
        add(submit);

        reset = new JButton("Reset");
        reset.setBounds(320, 550, 80, 32);
        add(reset);

        back = new JButton("Cancel");
        back.setBounds(410, 550, 80, 32);
        add(back);


        //ADDING ACTIONLISTENERS
        genderbtn2.addActionListener(this);
        genderbtn1.addActionListener(this);
        submit.addActionListener(this);
        back.addActionListener(this);
        reset.addActionListener(this);


        String imagePath1 = "Assets/homepage2.png";
        home  =  createImageButton(imagePath1);
        home.setBounds(475,10,45,45);
        home.addActionListener(createButtonActionListener("Home"));
        add(home);
        home.addActionListener(this);


/*//dailogbox intilization
        conFailedDialog = new JDialog(frame,"Connection Failed!!",true);
        conFailedDialog.setLayout(new FlowLayout());
        conFailedDialog.setVisible(false);
        conFailedDialog.setSize(500,300);
        frame.add(conFailedDialog);
        connectionWarning = new JLabel("We could not connect the DataBase!!");
        conFailedDialog.add(connectionWarning);*/
        /// gender cnt be emty dialog
       /* genderEmpty = new JDialog(frame, "Data Missing!!", true);
        genderEmpty.setLayout(new FlowLayout());
        genderEmpty.setBounds(650, 100, 250, 100);
        genderEmptyLabel = new JLabel("Please Select ");
        genderEmptyLabel1 = new JLabel(" atleast one Gender!!");
        genderEmptyLabel.setFont(new Font("Time Roman New", Font.BOLD, 20));
        genderEmptyLabel1.setFont(new Font("Time Roman New", Font.BOLD, 20));
        genderEmpty.add(genderEmptyLabel);
        genderEmpty.add(genderEmptyLabel1);


        //////////////check boc cant be seleceted//////////////////
        chkbox = new JDialog(frame, "Check the Box!!", true);
        chkbox.setLayout(new FlowLayout());
        chkbox.setBounds(650, 100, 250, 100);
        chkboxLabel = new JLabel("Please check the Box!!");
        chkboxLabel.setFont(new Font("Time Roman New", Font.BOLD, 20));
        chkbox.add(chkboxLabel);
////DATA EMPTY CANT BE NULL
        dataEmpty = new JDialog(frame, "Data Missing!!", true);
        dataEmpty.setLayout(new FlowLayout());
        dataEmpty.setBounds(650, 100, 250, 100);
        dataEmptyLabel = new JLabel("Enter all details..!!");
        dataEmptyLabel.setFont(new Font("Time Roman New", Font.BOLD, 20));
        dataEmpty.add(dataEmptyLabel);*/
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

    private static ActionListener createButtonActionListener(final String buttonName) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

///code forr home menu

            }
        };
    }

    public void actionPerformed(ActionEvent e) {

        //Reset Button resetting details
        if (e.getSource().equals(reset)) {
            resetAction();
            JOptionPane.showMessageDialog(null, "Reset Successful!!");


        }

        //gender Action
        genderbtn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genderbtn2.setSelected(false);
            }
        });
        genderbtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               genderbtn1.setSelected(false);
            }
        });

//        JOptionPane.showMessageDialog(null, "Select atleast one Gender!");

        //sumbit button actionListener
        if (e.getSource().equals(submit)) {
            boolean isValid = UserRegistrationForm.isValidName(namet.getText());
            boolean isValidfather = UserRegistrationForm.isValidName(fnamet.getText());
            boolean isValidmother = UserRegistrationForm.isValidName(mnamet.getText());
            boolean isValidPhone = UserRegistrationForm.isValidPhoneNumber(phonet.getText());

            if (!isValidPhone) {
                JOptionPane.showMessageDialog(null, "Entered Phone No. is Invalid!");
            } else if (!UserLoginForm.isValidEmail(emailt.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid email format!");
            } else if (namet.getText().isEmpty() || emailt.getText().isEmpty() || phonet.getText().isEmpty() ||
                    fnamet.getText().isEmpty() || mnamet.getText().isEmpty() || ridt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill all the details");
            } else if (!isValidmother) {
                JOptionPane.showMessageDialog(null, "Mother's Name is Invalid!");
            } else if (!isValidfather) {
                JOptionPane.showMessageDialog(null, "Father's Name is Invalid!");
            } else if (!isValid) {
                JOptionPane.showMessageDialog(null, "Entered Name is Invalid!");
            } else {
                if (cnfbox.isSelected()) {
                    try {
                        if (ConnectionProvider.con.isClosed()) {
                            JOptionPane.showMessageDialog(null, "Connection Closed!");
                        } else {
                            String namestr = namet.getText();
                            String fnamestr = fnamet.getText();
                            String mnamestr = mnamet.getText();
                            String genderstr = "";
                            if (genderbtn1.isSelected()) {
                                genderstr = genderbtn1.getText();
                            } else if (genderbtn2.isSelected()) {
                                genderstr = genderbtn2.getText();
                            } else {
                                JOptionPane.showMessageDialog(null, "Select at least one Gender!");
                                return; // Exit the method if gender is not selected
                            }
                            String addressstr = addresst.getText();
                            String regidstr = ridt.getText();
                            String phonestr = phonet.getText();
                            String emailstr = emailt.getText();

                            String insertstmt = "INSERT INTO srdx (sname, fname, mname, gender, address, phno, emailid, rid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                            try (PreparedStatement insertpstmt = ConnectionProvider.con.prepareStatement(insertstmt)) {
                                insertpstmt.setString(1, namestr);
                                insertpstmt.setString(2, fnamestr);
                                insertpstmt.setString(3, mnamestr);
                                insertpstmt.setString(4, genderstr);
                                insertpstmt.setString(5, addressstr);
                                insertpstmt.setString(6, phonestr);
                                insertpstmt.setString(7, emailstr);
                                insertpstmt.setString(8, regidstr);

                                int rowsInserted = insertpstmt.executeUpdate();
                                if (rowsInserted > 0) {
                                    System.out.println("A new record was inserted successfully!");
                                    JOptionPane.showMessageDialog(null, "Inserted successfully!");
                                    resetAction();
                                }
                            }
                        }
                    } catch (SQLException ex) {
                        if (ex.getMessage().contains("PRIMARY"))
                            JOptionPane.showMessageDialog(null,"Registration ID Already Exist!");
                        ex.printStackTrace();
                        // Handle the exception appropriately (logging, displaying an error message, etc.)
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "CheckBox is not chosen!");
                }
            }
        }

/*
        if (e.getSource().equals(submit)) {

            boolean isValid = UserRegistrationForm.isValidName(namet.getText());
            boolean isValidfather = UserRegistrationForm.isValidName(fnamet.getText());
            boolean isValidmother = UserRegistrationForm.isValidName(mnamet.getText());
            boolean isValidMail = UserLoginForm.isValidEmail(emailt.getText());
            boolean isValidPhone = UserRegistrationForm.isValidPhoneNumber(phonet.getText());

            if (!UserLoginForm.isValidEmail(emailt.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid email format!");
            }

            else if (namet.getText().isEmpty() || emailt.getText().isEmpty() || phonet.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill all the details");
            }else if (!isValidmother){
                JOptionPane.showMessageDialog(null, "Mother's Name is Invalid!");
            }else if (!isValidfather){
                JOptionPane.showMessageDialog(null, "Father's Name is Invalid!");
            }
            else if (!isValid){
                JOptionPane.showMessageDialog(null, "Entered Name is Invalid!");
            }else if (!isValidPhone){
                JOptionPane.showMessageDialog(null, "Entered Phone No. is Invalid!");
            }else
            if (namet.getText().isEmpty() || emailt.getText().isEmpty() || phonet.getText().isEmpty() || fnamet.getText().isEmpty() || mnamet.getText().isEmpty() || ridt.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all the DataFields..!");
            } else {
                if (cnfbox.isSelected()) {
                    try {
                        if (ConnectionProvider.con.isClosed()) {
                            JOptionPane.showMessageDialog(null, "Connection Closed!");

                        } else {
                            namestr = namet.getText();
                            fnamestr = fnamet.getText();
                            mnamestr = mnamet.getText();
                            if (genderbtn1.isSelected()) {
                                genderstr = genderbtn1.getText();
                            } else if(genderbtn2.isSelected()) {
                                genderstr = genderbtn2.getText();
                            }else{
                                JOptionPane.showMessageDialog(null, "Select at least one Gender!");
                            }
                            addressstr = addresst.getText();
                            regidstr = ridt.getText();
                            phonestr = phonet.getText();
                            emailstr = emailt.getText();

                            // statement ko value pahuchana ('_^_')

//                            if (namestr != null && !namestr.isEmpty()) {
//                                String namesearch = "SELECT sname, fname, mname, gender, address, phno, emailid, rid FROM srdx WHERE sname = ?";
//                                PreparedStatement pstmt1 = null;
//                                ResultSet rs1;
//                                try {
//                                    pstmt1 = ConnectionProvider.con.prepareStatement(namesearch);
//                                    pstmt1.setString(1, namestr); // Set the parameter at index 1
//                                    rs1 = pstmt1.executeQuery();
//
//                                    // Process the retrieved data from the ResultSet
//                                    while (rs1.next()) {
//                                        String sName = rs1.getString("sname");
//                                        String fName = rs1.getString("fname");
//                                        String mName = rs1.getString("mname");
//                                        String gender = rs1.getString("gender");
//                                        String address = rs1.getString("address");
//                                        String phno = rs1.getString("phno");
//                                        String emailid = rs1.getString("emailid");
//                                        String rid = rs1.getString("rid");
//                                    }
//                                    // Close ResultSet and PreparedStatement after use
//                                    rs1.close();
//                                    pstmt1.close();
//                                } catch (SQLException aes) {
//                                    // Handle exceptions
//                                    aes.printStackTrace(); // Log the exception or take appropriate actions
//                                }
//                            }
                            String insertstmt = "INSERT INTO srdx (sname, fname, mname, gender, address, phno, emailid, rid) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement insertpstmt = ConnectionProvider.con.prepareStatement(insertstmt);

                            // ab statemnet me valude ko dena  :
                            insertpstmt.setString(1, namestr);
                            insertpstmt.setString(2, fnamestr);
                            insertpstmt.setString(3, mnamestr);
                            insertpstmt.setString(4, genderstr);
                            insertpstmt.setString(5, addressstr);
                            insertpstmt.setString(6, phonestr);
                            insertpstmt.setString(7, emailstr);
                            insertpstmt.setString(8, regidstr);

                            int rowsInserted = insertpstmt.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("A new record was inserted successfully!");
                                JOptionPane.showMessageDialog(null, "Inserted successfully!");

                                resetAction();
                            }
                            insertpstmt.close();
                        }
                    } catch (SQLException ex) {
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "CheckBox is not Choosen!");
                }
            }
        }
*/


        if (e.getSource().equals(back)){

            this.dispose();
        }
    }

    public static void main(String[] args) throws SQLException {
        StudentRegistrationForm srf = new StudentRegistrationForm();
        Connection con = ConnectionProvider.getConnection();
        if (con==null){
            srf.dispose();
        }
    }
}

