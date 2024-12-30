import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLoginForm extends JFrame implements ActionListener {

    static String  userName,userGender;
    private JLabel title, email, decl, password, userType;
    private JButton login, signup, reset, back, home;
    private JTextField emailt;
    private JPasswordField pwd;
    private String UserType, mailStr, pwdStr,userPwd;
    private JComboBox selector;
    private JCheckBox cnfbox;
    JFrame frame = new JFrame("User Login");

    public UserLoginForm() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setLayout(null);

        setBounds(490, 200, 550, 400);
        getContentPane().setBackground(new Color(158, 210, 198));


        setTitle("User Login");
        setVisible(true);



        title = new JLabel("User Login");
        title.setBounds(190, 5, 500, 50);
        title.setFont(new Font("Times Roman", Font.BOLD, 30));
        add(title);
        userType = new JLabel("User Type :");
        userType.setBounds(30, 60, 130, 50);
        userType.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(userType);

        String selectorStr[] = {"Administrator", "User", "Student"};
        selector = new JComboBox<>(selectorStr);
        selector.setBounds(200, 75, 150, 35);
        add(selector);


        email = new JLabel("Email ID :");
        email.setBounds(30, 120, 100, 50);
        email.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(email);


        emailt = new JTextField();
        emailt.setForeground(new Color(33, 156, 144));

        emailt.setBounds(200, 130, 280, 32);
        emailt.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(emailt);


        password = new JLabel("Password :");
        password.setBounds(30, 160, 160, 50);
        password.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(password);

        pwd = new JPasswordField();
        pwd.setBounds(200, 170, 280, 32);
        pwd.setForeground(new Color(134, 10, 53));
        pwd.setFont(new Font("Times Roman", Font.BOLD, 20));
        add(pwd);

        login = new JButton("Login Now");
        login.setForeground(Color.magenta);
        login.setBackground(Color.pink);
        login.setBounds(200, 220, 137, 35);
        login.setFont(new Font("Times Roman", Font.BOLD, 15));
        add(login);

        signup = new JButton("SignUp Now");
        signup.setForeground(Color.BLUE);
        signup.setBackground(Color.pink);
        signup.setBounds(345, 220, 137, 35);
        signup.setFont(new Font("Times Roman", Font.BOLD, 15));
        add(signup);


        //back.setFont(new Font("Times Roman",Font.BOLD,18));
        //add(back);


        String imagePath1 = "Assets/homepage2.png";
        home = createImageButton(imagePath1);
        home.setBounds(485, 15, 50, 50);
        home.addActionListener(createButtonActionListener("Home"));
        add(home);
        home.addActionListener(this);


        signup.addActionListener(this);
        login.addActionListener(this);






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

            }
        };
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(signup)) {
            UserRegistrationForm urf = new UserRegistrationForm();
            this.dispose();
        }

        if (e.getSource().equals(login)) {
            boolean isValidPwd = UserRegistrationForm.isPasswordValid(pwd.getText());


            UserType = (String) selector.getSelectedItem();
            mailStr = emailt.getText();
            pwdStr = pwd.getText();

            ///

            if (!isValidEmail(mailStr)) {
                JOptionPane.showMessageDialog(null, "Invalid email format!");
            } else if (mailStr.isEmpty() || pwdStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill the Login Credentials!");
            }
            else if (!isValidPwd){
                JOptionPane.showMessageDialog(null, "Password must contain at least 8 characters!");
            }
            else {

                if (UserType.equalsIgnoreCase("Administrator")) {

                    String sql = "SELECT * FROM mrdx WHERE uemail = ? AND upwd = ?";
                    try (PreparedStatement statement = ConnectionProvider.con.prepareStatement(sql)) {
                        statement.setString(1, mailStr);
                        statement.setString(2, pwdStr);

                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {

                            if (emailt.getText().equals(mailStr) && pwd.getText().equals(pwdStr)){
                                MasterDashboard mds = new MasterDashboard();
                                this.dispose();
                            }else{
                                JOptionPane.showMessageDialog(null,"User Password Mismatch!!");
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"No Admin Found as Input Data!!");
                        }

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }




                /*if (UserType.equalsIgnoreCase("administrator")) {
                    String sql = "SELECT * FROM mrd WHERE uemail = ? AND upwd = ?";
                    try (PreparedStatement statement = ConnectionProvider.con.prepareStatement(sql)) {
                        statement.setString(1, mailStr);
                        statement.setString(2, pwdStr);

                        ResultSet resultSet = statement.executeQuery();


                        if (resultSet.next()) {

                            userName = resultSet.getString("uname");
                            if (emailt.getText().equals(mailStr) && pwd.getText().equals(pwdStr)) ;{
                                MasterDashboard mds = new MasterDashboard();
                                this.dispose();
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"No User Found as Input Data!!");
                        }

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }*/

                } else if (UserType.equalsIgnoreCase("User")) {

                    String sql = "SELECT * FROM urdx WHERE uemail = ? AND upwd = ?";
                    try (PreparedStatement statement = ConnectionProvider.con.prepareStatement(sql)) {
                        statement.setString(1, mailStr);
                        statement.setString(2, pwdStr);

                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            userName = resultSet.getString("uname");
                            userGender = resultSet.getString("gender");

                            if (emailt.getText().equals(mailStr) && pwd.getText().equals(pwdStr)) {
                                UserDashboard uds = new UserDashboard();
                                this.dispose();
                            }
                        }else {
                            JOptionPane.showMessageDialog(null,"No User Found as Input Data!!");
                        }

                    } catch (SQLException | UnsupportedLookAndFeelException | ClassNotFoundException |
                             InstantiationException | IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "For Student no Login Methdos are created yet!!");
                    JOptionPane.showMessageDialog(null, "Stay Alert ... Coming Soon!!!");

                }
            }


        /*

            String query="select * from users;";
            try {
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(query);
                while (rs.next()) {
                    String uName=rs.getString("Name");
                    String psw=rs.getString("password");
                    if (t1.getText().equals(uName) && t2.getText().equals(psw)) {
                        f1.dispose();
                        AdminInterface ai = new AdminInterface();
                        ai.adminCommand();
                        break;
                    }
                }
                con.close();
                stmt.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }



        */

        }
    }

    public static void main(String[] args) {
        UserLoginForm u = new UserLoginForm();

        ConnectionProvider.con = ConnectionProvider.getConnection();
    }

    public static boolean isValidEmail(String email) {
        // Regular expression for a valid email address
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Create a Matcher object
        Matcher matcher = pattern.matcher(email);

        // Check if the email matches the pattern
        return matcher.matches();
    }
}
