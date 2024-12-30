import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Approve extends JFrame implements ActionListener {
    private JTable table;
    private String name, email, phone, genderStr, pwdStr;
    private JButton approveButton, rejectButton, showUser;
    private JTextField namet, emailt, phonet;
    private JLabel t1, t2, t3, t4;

    public Approve() {
        setTitle("Approve New User");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setBounds(500, 150, 500, 500);
        // setLayout(null);
        getContentPane().setBackground(new Color(142, 211, 211));

     /*   String data[][]={ {"101","Amit","670000"},
                {"102","Jai","780000"},
                {"101","Sachin","700000"}};
        String column[]={"ID","NAME","SALARY"};

        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);
        add(sp);*/


        t1 = new JLabel("Approve or Reject User!");
        t1.setFont(new Font(" ", Font.BOLD, 25));
        t1.setBounds(100, 10, 350, 25);
        t1.setForeground(Color.black);
        add(t1);

        t2 = new JLabel("Name");
        t2.setFont(new Font(" ", Font.BOLD, 20));
        t2.setBounds(10, 65, 100, 25);
        t2.setForeground(Color.black);
        add(t2);

        t3 = new JLabel("Email Id");
        t3.setFont(new Font(" ", Font.BOLD, 20));
        t3.setBounds(10, 140, 100, 25);
        t3.setForeground(Color.black);
        add(t3);

        t4 = new JLabel("Mob. No.");
        t4.setFont(new Font(" ", Font.BOLD, 20));
        t4.setBounds(10, 200, 100, 25);
        t4.setForeground(Color.black);
        add(t4);

        namet = new JTextField();
        namet.setBounds(100, 50, 350, 50);
        namet.setFont(new Font(" ", Font.BOLD, 25));
        namet.setEditable(false);
        add(namet);

        emailt = new JTextField();
        emailt.setBounds(100, 120, 350, 50);
        emailt.setFont(new Font(" ", Font.BOLD, 25));
        emailt.setEditable(false);
        add(emailt);

        phonet = new JTextField();
        phonet.setBounds(100, 190, 350, 50);
        phonet.setFont(new Font(" ", Font.BOLD, 25));
        phonet.setEditable(false);
        add(phonet);

        showUser = new JButton("Is anyone yet to approve?");
        showUser.setFont(new Font(" ", Font.BOLD, 25));
        showUser.setBounds(45, 350, 400, 50);
        add(showUser);

        approveButton = new JButton("Approve");
        approveButton.setFont(new Font(" ", Font.BOLD, 25));
        approveButton.setBounds(255, 350, 200, 50);
        approveButton.setVisible(false);
        add(approveButton);

        rejectButton = new JButton("Reject");
        rejectButton.setBounds(45, 350, 200, 50);
        rejectButton.setFont(new Font(" ", Font.BOLD, 25));
        rejectButton.setVisible(false);
        add(rejectButton);


        setVisible(true);


        showUser.addActionListener(this);
        rejectButton.addActionListener(this);
        approveButton.addActionListener(this);


    }

    public void rejectMethod(){

        String stmt = "delete from urax where uemail=?; ";
        PreparedStatement pstmt = null;
        try {
            pstmt = ConnectionProvider.con.prepareStatement(stmt);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        try {
            pstmt.setString(1, email);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        int i = 0;
        try {
            i = pstmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        if (i > 0) {
            namet.setText("");
            phonet.setText("");
            emailt.setText("");
            approveButton.setVisible(false);
            rejectButton.setVisible(false);
            showUser.setVisible(true);


//                this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Something went Wrong.");
        }
        try {
            pstmt.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        ConnectionProvider.con = ConnectionProvider.getConnection();
        Approve ap = new Approve();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(showUser)) {

            if (namet.getText().isEmpty()) {
                String approveUser = "SELECT * FROM urax ";
                ResultSet rs1;

                Statement stmt = null;
                try {
                    stmt = ConnectionProvider.con.createStatement();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    rs1 = stmt.executeQuery(approveUser);
                    if (rs1.next()) {
                        showUser.setVisible(false);
                        approveButton.setVisible(true);
                        rejectButton.setVisible(true);

                        name = rs1.getString("uname");
                        email = rs1.getString("uemail");
                        phone = rs1.getString("uphno");
                        genderStr = rs1.getString("gender");
                        pwdStr = rs1.getString("upwd");
                        namet.setText(name);
                        emailt.setText(email);
                        phonet.setText(phone);
                    } else {
                        JOptionPane.showMessageDialog(null, "No Registration Requests!");


                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }

        }

        if (e.getSource().equals(rejectButton)) {
            rejectMethod();
//            JOptionPane.showMessageDialog(null, "User Rejected.");
        }

        if (e.getSource().equals(approveButton)) {
            try {

                String insertstmt = "INSERT INTO urdx (uemail, uname, gender,uphno, upwd) VALUES (?, ?, ?,?, ?)";
                PreparedStatement insertpstmt = ConnectionProvider.con.prepareStatement(insertstmt);

                // ab statemnet me valude ko dena  :


                insertpstmt.setString(1, email);
                insertpstmt.setString(2, name);
                insertpstmt.setString(3, genderStr);
                insertpstmt.setString(4, phone);
                insertpstmt.setString(5, pwdStr);


                int rowsInserted = 0;
                try {
                    rowsInserted = insertpstmt.executeUpdate();
                } catch (SQLException ex) {if (ex.getMessage().contains("Duplicate entry")){
                    JOptionPane.showMessageDialog(null, "This email id already registered for another user!!");
                }else {
                    throw new RuntimeException(ex);
                }
                }
                if (rowsInserted > 0) {
                    System.out.println("A new record was inserted successfully!");
                    JOptionPane.showMessageDialog(null, "User Added successfully!");
                    rejectMethod();

                    String stmt = "delete from urax where uemail=?; ";
                    PreparedStatement pstmt = null;
                    try {
                        pstmt = ConnectionProvider.con.prepareStatement(stmt);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        pstmt.setString(1, email);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    int i = 0;
                    try {
                        pstmt.close();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

              
                } else {
                    JOptionPane.showMessageDialog(null, "User couldn't be registered!");

                }
                try {
                    insertpstmt.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        }
    }
}








