import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class JTableExample {
    public static void main(String[] args) {
        // Existing JFrame
        JFrame frame = new JFrame("JTable Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Creating data for the table
        Object[][] data = {
                {"John", 25, "Male"},
                {"Emily", 30, "Female"},
                {"Tom", 22, "Male"},
                {"Alice", 28, "Female"}
        };

        // Column names
        String[] columnNames = {"Name", "Age", "Gender"};

        // Creating a DefaultTableModel to hold the table data
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Creating a JTable using the DefaultTableModel
        JTable table = new JTable(model);

        // Adding the JTable to a JScrollPane (optional but recommended for scrollable tables)
        JScrollPane scrollPane = new JScrollPane(table);

        // Adding the JScrollPane (with JTable) to the existing JFrame
        frame.add(scrollPane);

        // Displaying the JFrame
        frame.setVisible(true);
    }
}
