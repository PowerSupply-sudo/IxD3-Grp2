// Java
package com.ixd3grp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;
    private Connection conn;

    private DBConnection() throws SQLException {
        String url = System.getenv("DB_URL");
        if (url == null || url.isBlank()) {
            String host = System.getenv().getOrDefault("DB_HOST", "localhost");
            String port = System.getenv().getOrDefault("DB_PORT", "3306");
            String db = System.getenv().getOrDefault("DB_NAME", "wishlistapp");
            url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&protocol=TCP", host, port, db);
        }

        String user = System.getenv().getOrDefault("DB_USER", "wishlistuser");
        String pass = System.getenv().getOrDefault("DB_PASS", "wishlistpass");

        this.conn = DriverManager.getConnection(url, user, pass);
    }

    public static synchronized DBConnection getInstance() throws SQLException {
        if (instance == null || instance.conn == null || instance.conn.isClosed()) {
            instance = new DBConnection();
        }
        return instance;
    }

    public void createUser (String username, String password) throws SQLException{
        var statement = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
        statement.setString(1, username);
        statement.setString(2, password);
        statement.execute();
        conn.commit();
    }
    
    public boolean login (String username, String password) throws SQLException{
        var statement = conn.prepareStatement("SELECT password FROM users WHERE username = ?");
        statement.setString(1, username);
        statement.executeQuery();
        var result = statement.executeQuery();
        result.next();
        var dbpassword = result.getString(1);
        return password.equals(dbpassword);
    }

    public void deleteUser (String username) throws SQLException{
        var statement = conn.prepareStatement("DELETE FROM users WHERE username = ?");
        statement.setString(1, username);
        statement.execute();
        conn.commit();
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
        instance = null;
    }
}
