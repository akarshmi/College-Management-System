import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveStudent extends JFrame implements FocusListener, ActionListener {
    private JLabel removeJlabel,l1,l2;
    private JButton remove,back;
    private JTextField nameRemove,ridRemove;
    private String removeStr;

    public RemoveStudent()throws HeadlessException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        setLayout(null);
        setBounds(450, 100, 600, 600);
        setVisible(true);
        setTitle("Delete an Student!");
        getContentPane().setBackground(new Color(196, 223, 170));
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        removeJlabel = new JLabel("Student Delete Menu");
        removeJlabel.setBounds(130,30,350,50);
        removeJlabel.setFont(new Font("Ariel", Font.BOLD, 30));
        removeJlabel.setForeground(Color.red);
        add(removeJlabel);

        l1 = new JLabel("Enter Registration ID :");
        l1.setBounds(50,200,350,50);
        l1.setFont(new Font("Ariel", Font.BOLD, 25));

        add(l1);

        l2 = new JLabel("Enter Name :");
        l2.setBounds(50,260,250,50);
        l2.setFont(new Font("Ariel", Font.BOLD, 25));
        add(l2);

        ridRemove = new JTextField();
        ridRemove.setFont(new Font("Nothing",Font.ITALIC,22));
        ridRemove.setBounds(330,210,200,45);
        add(ridRemove);

        nameRemove = new JTextField();
        nameRemove.setFont(new Font("Nothing",Font.ITALIC,22));
        nameRemove.setBounds(330,260,200,45);
        add(nameRemove);


        remove = new JButton("Search & Delete");
        remove.setFont(new Font("Nothing",Font.ITALIC,22));
        remove.setBounds(330,350,200,45);
        add(remove);

        back = new JButton("Back to Home");
        back.setFont(new Font("Nothing",Font.ITALIC,22));
        back.setBounds(100,350,200,45);
        add(back);

        ridRemove.addFocusListener(this);
        back.addActionListener(this);
        remove.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {


        if(ae.getSource().equals(remove)){
            boolean isValid = UserRegistrationForm.isValidName(nameRemove.getText());

            if (!isValid){
                if (nameRemove.getText().equalsIgnoreCase("Optional")){
                    nameRemove.setText("");
                    //JOptionPane.showMessageDialog(null, "Invalid Name Entered!");
                }
            }

            if (!ridRemove.getText().isEmpty()){

               try {
                   String stmt = "delete from srdx where rid=?; ";
                   String rnumber = ridRemove.getText();
                   PreparedStatement pstmt = ConnectionProvider.con.prepareStatement(stmt);
                   pstmt.setString(1,rnumber);
                   int i =pstmt.executeUpdate();
                   if (i>0){
                       JOptionPane.showMessageDialog(null, "Student deleted.");
                       this.dispose();


                   }else {
                       JOptionPane.showMessageDialog(null, "Student couldn't found.");
                   }
                   pstmt.close();

               } catch (SQLException ex) {
                   throw new RuntimeException(ex);
               }
           }else {
               JOptionPane.showMessageDialog(null, "Registration ID is Mandatory.");

           }
        }

        if (ae.getSource().equals(back)){
           /* try {
                UserDashboard userDashboard = new UserDashboard();
            } catch (UnsupportedLookAndFeelException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }*/

            this.dispose();

        }

    }

    @Override
    public void focusGained(FocusEvent fe) {
        if (nameRemove.getText().isEmpty()){
            nameRemove.setText("Optional");
        }

    }

    @Override
    public void focusLost(FocusEvent fe) {

        if (nameRemove.getText().isEmpty()||nameRemove.getText().equals("Optional")) {
            nameRemove.setText("");
        }
    }



/*    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        RemoveStudent removeStudent = new RemoveStudent();
        ConnectionProvider.con= ConnectionProvider.getConnection();
    }*/

}
