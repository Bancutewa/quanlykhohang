package inventory.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBCDbConnection {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String jdbcUrl = "jdbc:mysql://localhost:3306/inventory_management?useSSL=false";
        String user="root";
        String pass="bancutewa10304";

        try {
            System.out.println("Connecting to the database: "+jdbcUrl);
            Connection myConn=
                    DriverManager.getConnection(jdbcUrl,user,pass);
            System.out.println("Connection successful!");
        } catch (Exception exc) {
            System.out.println(exc);
        }
    }
}
