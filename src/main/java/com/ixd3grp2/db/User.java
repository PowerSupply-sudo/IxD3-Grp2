package com.ixd3grp2.db;

import java.time.LocalDateTime;

/**
 * User model representing a user account.
 */
public class User {
    public int id;
    public String username;
    public String passwordHash;
    public LocalDateTime createdAt;

    public User() {}

    public User(int id, String username, String passwordHash, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
