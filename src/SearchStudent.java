import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchStudent extends JFrame implements FocusListener, ActionListener {

    JFrame frame = new JFrame();
    private JLabel searchJlabel, l1, l2;
    private JButton search, back;
    private JTextField nameSearch, ridSearch;
    private String searchStr, namestr, regidstr;

    public SearchStudent() throws HeadlessException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        setLayout(null);
        setBounds(450, 100, 600, 600);
        setVisible(true);
        setTitle("Search Student!");
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        searchJlabel = new JLabel("Student Search Menu");
        searchJlabel.setBounds(130, 30, 350, 50);
        searchJlabel.setFont(new Font("Ariel", Font.BOLD, 30));
        searchJlabel.setForeground(Color.red);
        add(searchJlabel);

        l1 = new JLabel("Enter Registration ID :");
        l1.setBounds(50, 200, 350, 50);
        l1.setFont(new Font("Ariel", Font.BOLD, 25));

        add(l1);

        l2 = new JLabel("Enter Name :");
        l2.setBounds(50, 260, 250, 50);
        l2.setFont(new Font("Ariel", Font.BOLD, 25));
        add(l2);

        ridSearch = new JTextField();
        ridSearch.setFont(new Font("Nothing", Font.ITALIC, 22));
        ridSearch.setBounds(330, 210, 200, 45);
        add(ridSearch);

        nameSearch = new JTextField();
        nameSearch.setFont(new Font("Nothing", Font.ITALIC, 22));
        nameSearch.setBounds(330, 260, 200, 45);
        add(nameSearch);


        search = new JButton("Search & Show");
        search.setFont(new Font("Nothing", Font.ITALIC, 22));
        search.setBounds(330, 350, 200, 45);
        add(search);

        back = new JButton("Back to Home");
        back.setFont(new Font("Nothing", Font.ITALIC, 22));
        back.setBounds(100, 350, 200, 45);
        add(back);

        ridSearch.addFocusListener(this);
        nameSearch.addFocusListener(this);
        back.addActionListener(this);
        search.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource().equals(search)) {

            namestr = nameSearch.getText();
            regidstr = ridSearch.getText();
            try {
                if (namestr != null && !namestr.isEmpty()) {
                    String namesearch = "SELECT * FROM srdx WHERE sname = ?";
                    PreparedStatement pstmt1 = null;
                    ResultSet rs1;
                    try {
                        pstmt1 = ConnectionProvider.con.prepareStatement(namesearch);
                        pstmt1.setString(1, namestr);
                        rs1 = pstmt1.executeQuery();

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
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the frame
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                } else if (regidstr != null && !regidstr.isEmpty()) {
                    String ridsearch = "SELECT * FROM srdx WHERE rid = ?";
                    PreparedStatement pstmt2 = null;
                    ResultSet rs2;
                    pstmt2 = ConnectionProvider.con.prepareStatement(ridsearch);
                    pstmt2.setString(1, regidstr);
                    rs2 = pstmt2.executeQuery();
                    DefaultTableModel model1 = new DefaultTableModel();

                    // Retrieve metadata from ResultSet to set column names for the model
                    int columnCount = rs2.getMetaData().getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        model1.addColumn(rs2.getMetaData().getColumnName(i));
                    }

                        // Process the retrieved data from the ResultSet
                        while (rs2.next()) {

                            Object[] row = new Object[columnCount];
                            for (int i = 1; i <= columnCount; i++) {
                                row[i - 1] = rs2.getObject(i);
                            }
                            model1.addRow(row);
                        }
                        JTable table = new JTable(model1);
                        JScrollPane scrollPane = new JScrollPane(table);
                        scrollPane.setPreferredSize(new Dimension(1200, 400));
                        frame.getContentPane().removeAll();
                        frame.getContentPane().add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the frame
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);


                } else {
                    JOptionPane.showMessageDialog(null, "Field couldn't be Empty!");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    if (ae.getSource().equals(back)){
//        try {
//            UserDashboard userDashboard = new UserDashboard();
//        } catch (UnsupportedLookAndFeelException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        this.dispose();
    }

    }



    @Override
    public void focusGained(FocusEvent fe) {
        if (nameSearch.getText().isEmpty()) {

        }
    }

    public void focusLost(FocusEvent fe) {
        if (nameSearch.getText().isEmpty() || nameSearch.getText().equals("Optional")) {
            nameSearch.setText("");
        }
    }

 public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
     SearchStudent searchStudent = new SearchStudent();
     ConnectionProvider.con = ConnectionProvider.getConnection();

 }
}


