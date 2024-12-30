import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class UserLogin {
    private JTextField textField2;
    private JTextField textField3;
    private JRadioButton maleRadioButton;
    private JTextField textField6;
    private JCheckBox checkBox1;
    private JButton submitButton1;
    private JButton searchButton;
    private JButton resetButton;
    private JProgressBar progressBar1;

    public UserLogin() {
        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==submitButton1){
                    Connection con = ConnectionProvider.getConnection();
                }
            }
        });
    }

    public static void main(String[] args) {
        UserLoginForm ul=new UserLoginForm();
    }
}
