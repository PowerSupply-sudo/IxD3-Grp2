package com.ixd3grp2.db;

import java.sql.*;
import java.util.Optional;

/**
 * Data Access Object for User entities.
 * Handles all database operations for users.
 */
public class UserDAO {
    private final String url;
    private final String user;
    private final String pass;

    public UserDAO() {
        String host = System.getenv().getOrDefault("DB_HOST", "localhost");
        String port = System.getenv().getOrDefault("DB_PORT", "3306");
        String db = System.getenv().getOrDefault("DB_NAME", "wishlistapp");
        this.user = System.getenv().getOrDefault("DB_USER", "wishlistuser");
        this.pass = System.getenv().getOrDefault("DB_PASS", "wishlistpass");
        this.url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false&serverTimezone=UTC", host, port, db);
    }

    /**
     * Get a database connection using environment-based credentials.
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    /**
     * Find a user by username.
     */
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT id, username, password, created_at FROM users WHERE username = ?";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.id = rs.getInt("id");
                    u.username = rs.getString("username");
                    u.passwordHash = rs.getString("password");
                    u.createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                    return Optional.of(u);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    /**
     * Check if a username already exists in the database.
     */
    public boolean usernameExists(String username) {
        return findByUsername(username).isPresent();
    }

    /**
     * Insert a new user with a hashed password.
     * Returns the generated user ID on success, -1 on failure.
     */
    public int insertUser(String username, String passwordHash) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
