import javax.swing.*;
import java.awt.*;

public class ApprovalUser extends JFrame {

    private JTable userTable;
    private JButton approveButton;
    private JButton denyButton;
    public ApprovalUser() throws HeadlessException {

        setTitle("User Request Approval");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        userTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(10, 10, 400, 150);
        add(scrollPane);

        // Initialize buttons
        approveButton = new JButton("Approve");
        denyButton = new JButton("Deny");

        approveButton.setBounds(10, 180, 100, 30);
        denyButton.setBounds(120, 180, 100, 30);

        add(approveButton);
        add(denyButton);
    }
}
