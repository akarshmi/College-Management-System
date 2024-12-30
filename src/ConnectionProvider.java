import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    static Connection con = null;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //agar nahi kaam kare to naye computer par karo
            //password me samsya hoti hai kisi dusre computer par karo
            //on administration Munna Bhaiyya
            String url = "jdbc:mysql://localhost:3306/cmdb";
            String user = "root";
            String password = "";
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "JDBC Driver not found!!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage());
        }
        return con;
    }
}
