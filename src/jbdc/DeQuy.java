import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DeQuy {
    public static Connection getConnection() throws Exception{
        Connection c = null;
        try {
            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            String url ="jdbc:sqlserver://DESKTOP-FPTEPJA:1433;databaseName=JDBC;trustServerCertificate=true";
            String username ="sa";
            String password = "123456789";
            c = DriverManager.getConnection(url,username,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return c;
    }
    public static void closeConnection(Connection c){
        try {
            if (c!=null){
                c.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void  prinInfo(Connection c) {
        try {
            if (c!=null){
                DatabaseMetaData mtdt = c.getMetaData();
                System.out.println(mtdt.getDatabaseProductName());
                System.out.println(mtdt.getDatabaseProductVersion());
            }
        }catch (SQLException e){
            
        }
    }
}
