import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ConnectionProvider.con=ConnectionProvider.getConnection();
        if(ConnectionProvider.con.isClosed()){
            System.err.println("Error");
        }else {
            System.err.println("Sahi hai");
           UserLoginForm urf = new UserLoginForm();

}
        }
}
