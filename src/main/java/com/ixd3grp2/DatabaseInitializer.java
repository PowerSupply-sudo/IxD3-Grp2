package com.ixd3grp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class DatabaseInitializer {
    private static final String HOST = System.getenv().getOrDefault("DB_HOST", "localhost");
    private static final String PORT = System.getenv().getOrDefault("DB_PORT", "3306");
    private static final String DB = System.getenv().getOrDefault("DB_NAME", "wishlistapp");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "wishlistuser");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASS", "wishlistpass");
    private static final String URL = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC", HOST, PORT, DB);

    public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() throws SQLException, IOException {
        // Read the SQL file
        String sql = new String(Files.readAllBytes(Paths.get("src/db/user-logins.sql")));
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Database initialized successfully");
        }
    }
}
