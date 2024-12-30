import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRegistrationForm extends JFrame implements ActionListener {
    private JLabel title, name, gender, address, phone, email, decl, chkboxLabel, genderEmptyLabel,userType,genderEmptyLabel1, password;

    private String namestr, genderstr, Addressstr, phonestr, emailstr, regidstr, pwdstr,UserType;
    private JTextField namet, phonet, emailt;
    private JRadioButton genderbtn1, genderbtn2;
    JPasswordField passwordField;
    private JComboBox selector;
    private JCheckBox cnfbox;
    JFrame urf = new JFrame("User's Registration Form");
    private JButton register, reset, login,home;
    public void resetAction() {
        namet.setText("");
        phonet.setText("");
        emailt.setText("");
        cnfbox.setSelected(false);
        genderbtn2.setSelected(false);
        genderbtn1.setSelected(false);
        passwordField.setText("");
    }


    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression for a valid 10-digit phone number
        String phoneRegex = "^\\d{10}$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(phoneRegex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(phoneNumber);

        // Check if the phone number matches the pattern
        return matcher.matches();
    }
    public static boolean isPasswordValid(String password) {
        // Check if the password has at least 8 characters
        return password.length() >= 8;
    }

    boolean isValid,isValidPhone,isValidPwd;
    public static boolean isValidName(String name) {
        String nameRegex = "^[A-Za-z]+(?:[\\s'-][A-Za-z]+)*$";
        Pattern pattern = Pattern.compile(nameRegex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public UserRegistrationForm() {


        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setLayout(null);
        setVisible(true);
        setTitle("User Registration Form");
        getContentPane().setBackground(new Color(255, 241, 0));
        urf.setBackground(Color.cyan);
        setBounds(490, 200, 550, 450);


        title = new JLabel("User Registration");
        title.setBounds(100, 5, 500, 50);
        title.setFont(new Font("Times Roman", Font.BOLD, 30));
        add(title);

        name = new JLabel("Name :");
        name.setBounds(10, 100, 100, 50);
        name.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(name);

        gender = new JLabel("Gender");
        gender.setBounds(10, 150, 100, 50);
        gender.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(gender);


        phone = new JLabel("Phone No: ");
        phone.setBounds(10, 200, 130, 50);
        phone.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(phone);

        email = new JLabel("Email Id :");
        email.setBounds(10, 250, 100, 50);
        email.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(email);

        password = new JLabel("Password");
        password.setBounds(10, 300, 100, 50);
        password.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(password);


        //////////////////////////////////
        userType = new JLabel("User Type :");
        userType.setBounds(10,50,130,50);
        userType.setFont(new Font("Times Roman",Font.BOLD,20));
        add(userType);

        String selectorStr[]={"User"};
        selector = new JComboBox<>(selectorStr);
        selector.setBounds(200,60,150,32);
        add(selector);

//////////////////////////////////////////////////////////


        namet = new JTextField();
        namet.setBounds(200, 110, 300, 32);
        namet.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(namet);

        phonet = new JTextField();
        phonet.setBounds(200, 210, 300, 32);
        phonet.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(phonet);

        emailt = new JTextField();
        emailt.setBounds(200, 260, 300, 32);
        emailt.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(emailt);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 310, 300, 32);
        passwordField.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(passwordField);


        genderbtn1 = new JRadioButton("MALE");
        genderbtn1.setBounds(200, 160, 100, 32);

        genderbtn2 = new JRadioButton("FEMALE");
        add(genderbtn1);

        genderbtn2.setBounds(300, 160, 100, 32);
        add(genderbtn2);


        cnfbox = new JCheckBox();
        cnfbox.setBounds(200, 340, 30, 30);
        add(cnfbox);


        decl = new JLabel("I herby declare that every data is accurate. ");
        decl.setBounds(230, 350, 300, 20);
        add(decl);

        register = new JButton("Register");
        register.setBounds(220, 370, 90, 32);
        add(register);

        reset = new JButton("Reset");
        reset.setBounds(320, 370, 80, 32);
        add(reset);

        login = new JButton("Login");
        login.setBounds(410, 370, 80, 32);
        add(login);

        register.addActionListener(this);
        reset.addActionListener(this);
        login.addActionListener(this);
        genderbtn1.addActionListener(this);
        genderbtn2.addActionListener(this);

        String imagePath1 = "Assets/homepage2.png";
        home  =  createImageButton(imagePath1);
        home.setBounds(475,10,45,45);
        home.addActionListener(createButtonActionListener("Home"));
        add(home);
        home.addActionListener(this);

/*
        genderEmpty = new JDialog(frame, "Data Missing!!", true);
        genderEmpty.setLayout(new FlowLayout());
        genderEmpty.setBounds(650,100,250, 100);
        genderEmptyLabel = new JLabel("Please Select ");
        genderEmptyLabel1= new JLabel(" atleast one Gender!!");
        genderEmptyLabel.setFont(new Font("Time Roman New",Font.BOLD,20));
        genderEmptyLabel1.setFont(new Font("Time Roman New",Font.BOLD,20));
        genderEmpty.add(genderEmptyLabel);
        genderEmpty.add(genderEmptyLabel1);

        dataEmpty = new JDialog(frame, "Data Missing!!", true);
        dataEmpty.setLayout(new FlowLayout());
        dataEmpty.setBounds(650,100,250, 250);
        dataEmptyLabel = new JLabel("Enter all details..!!");
        dataEmptyLabel.setFont(new Font("Time Roman New",Font.BOLD,20));
        dataEmpty.add(dataEmptyLabel);

        conFailedDialog = new JDialog(frame, "Connection Failed!!", true);
        conFailedDialog.setLayout(new FlowLayout());
        conFailedDialog.setBounds(650,100,250, 250);
        dataEmptyLabel.setFont(new Font("Time Roman New",Font.BOLD,20));
        connectionWarning = new JLabel("We could not connect to the Database!!");
        conFailedDialog.add(connectionWarning);

        chkbox = new JDialog(frame, "Check the Box!!", true);
        chkbox.setLayout(new FlowLayout());
        dataEmptyLabel.setFont(new Font("Time Roman New",Font.BOLD,20));
        chkbox.setBounds(650,100,250, 250);
        chkboxLabel = new JLabel("Please check the Box!!");
        chkbox.add(chkboxLabel);*/

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




    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(login)){
         UserLoginForm urf = new UserLoginForm();
         this.dispose();
        }

        if (e.getSource().equals(reset)){
            resetAction();
            JOptionPane.showMessageDialog(null,"Reset Successful!!");
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


        if (e.getSource().equals(register)) {
            isValid = isValidName(namet.getText());
            isValidPhone = isValidPhoneNumber(phonet.getText());
            isValidPwd = isPasswordValid(passwordField.getText());

            if (!UserLoginForm.isValidEmail(emailt.getText())) {
                JOptionPane.showMessageDialog(null, "Invalid email format!");
            }

            else if (namet.getText().isEmpty() || emailt.getText().isEmpty() || phonet.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Fill all the details");
            }
            else if (!isValid){
                JOptionPane.showMessageDialog(null, "Entered Name is Invalid!");
            }else if (!isValidPhone){
                JOptionPane.showMessageDialog(null, "Entered Phone No. is Invalid!");
            }
            else if (!isValidPwd){
                JOptionPane.showMessageDialog(null, "Password must contain at least 8 characters!");
            }
            else {
                if (cnfbox.isSelected()) {
                    try {
                        if (ConnectionProvider.con.isClosed()) {
                        } else {
                            namestr = namet.getText();
                            if (genderbtn1.isSelected()) {
                                genderstr = genderbtn1.getText();
                            } else if (genderbtn2.isSelected()) {
                                genderstr = genderbtn2.getText();
                            } else {
                                JOptionPane.showMessageDialog(null, "Select at least one Gender!");
                            }
                            phonestr = phonet.getText();
                            emailstr = emailt.getText().toLowerCase();
                            pwdstr = passwordField.getText();
                            UserType = (String) selector.getSelectedItem();

                        }
                        if(UserType.equalsIgnoreCase("Administrator")){

                            String insertstmt = "INSERT INTO mrdx (uemail, uname, uphno, upwd) VALUES (?, ?, ?, ?)";
                            PreparedStatement insertpstmt = ConnectionProvider.con.prepareStatement(insertstmt);

                            // ab statemnet me valude ko dena  :

                            insertpstmt.setString(1, emailstr);
                            insertpstmt.setString(2, namestr);
                            insertpstmt.setString(3, phonestr);
                            insertpstmt.setString(4, pwdstr);

                            int rowsInserted = insertpstmt.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("A new record was inserted successfully!");
                                JOptionPane.showMessageDialog(null, "Admin Added successfully!");

                                resetAction();
                            }else {
                                JOptionPane.showMessageDialog(null, "Admin couldn't be registered!");

                            }
                            insertpstmt.close();

                        } else if (UserType.equalsIgnoreCase("User")) {


                            String insertstmt = "INSERT INTO urax (uemail, uname, gender,uphno, upwd) VALUES (?, ?, ?,?, ?)";
                            PreparedStatement insertpstmt = ConnectionProvider.con.prepareStatement(insertstmt);

                            // ab statemnet me valude ko dena  :

                            insertpstmt.setString(1, emailstr);
                            insertpstmt.setString(2, namestr);
                            insertpstmt.setString(3,genderstr);
                            insertpstmt.setString(4, phonestr);
                            insertpstmt.setString(5, pwdstr);

                            int rowsInserted = insertpstmt.executeUpdate();
                            if (rowsInserted > 0) {
                                //System.out.println("A new record was inserted successfully!");
                                JOptionPane.showMessageDialog(null, "Registered Successfully!");
                                JOptionPane.showMessageDialog(null, "Waiting for Approval!");
                                resetAction();
                            }else {
                                JOptionPane.showMessageDialog(null, "User couldn't be registered!");

                            }
                            insertpstmt.close();
                        }
                    } catch (SQLException ex) {
                        if (ex.getMessage().contains("Duplicate entry")){
                            JOptionPane.showMessageDialog(null, "This email id already registered for another user!!");
                        }else {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    System.out.println("Check the Box first.");
                    JOptionPane.showMessageDialog(null, "Please Check the BOX!");
                }
            }
        }
    }

    public static void main(String[] args) {
        UserRegistrationForm urf = new UserRegistrationForm();
        Connection con = ConnectionProvider.getConnection();
      /*  if (con == null) {
            urf.dispose();
        }*/
    }
}
