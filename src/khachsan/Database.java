package khachsan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DATABASE_NAME = "HotelManager";
    private static final String URL = "jdbc:sqlserver://LAPTOP-16NKG8C1\\SQLEXPRESS:1433;databaseName=" + DATABASE_NAME
            + ";encrypt=false";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "123";
    private static Database instance = null;
    private Connection connection = null;

    private Database() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}