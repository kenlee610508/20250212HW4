package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/food"; 
    private static final String USER = "root"; // 
    private static final String PASSWORD = "@Ken121092538"; // 
    private static Connection connection = null;

    
    public static Connection getConnection() {
        if (connection == null) { 
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); 
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ 資料庫連線成功！");
            } catch (ClassNotFoundException e) {
                System.err.println("❌ 錯誤：找不到 MySQL JDBC 驅動");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("❌ 錯誤：無法連線至 MySQL，請檢查連線設定");
                e.printStackTrace();
            }
        }
        return connection;
    }
}

