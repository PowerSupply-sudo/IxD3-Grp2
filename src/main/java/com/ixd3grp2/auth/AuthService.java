package com.ixd3grp2.auth;

import com.ixd3grp2.db.User;
import com.ixd3grp2.db.UserDAO;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Optional;

/**
 * Authentication service handling login and user registration.
 * Uses bcrypt for secure password hashing.
 */
public class AuthService {
    private final UserDAO userDao;

    public AuthService() {
        this.userDao = new UserDAO();
    }

    /**
     * Verify login credentials against the database.
     * @return true if username exists and password matches the hash.
     */
    public boolean verifyLogin(String username, String plainPassword) {
        Optional<User> userOpt = userDao.findByUsername(username);
        if (userOpt.isEmpty()) {
            return false;
        }
        User user = userOpt.get();
        return BCrypt.checkpw(plainPassword, user.passwordHash);
    }

    /**
     * Register a new user with validation.
     * @param username the username (email) for the account
     * @param password the plaintext password
     * @param confirmPassword the confirmation password
     * @return RegistrationResult with success/error details
     */
    public RegistrationResult register(String username, String password, String confirmPassword) {
        // Validation: non-empty fields
        if (username == null || username.trim().isEmpty()) {
            return RegistrationResult.error("Username/Email cannot be empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            return RegistrationResult.error("Password cannot be empty.");
        }
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            return RegistrationResult.error("Confirm Password cannot be empty.");
        }

        // Validation: passwords match
        if (!password.equals(confirmPassword)) {
            return RegistrationResult.error("Passwords do not match.");
        }

        // Validation: password length (at least 6 chars for demo; adjust as needed)
        if (password.length() < 6) {
            return RegistrationResult.error("Password must be at least 6 characters.");
        }

        // Validation: username doesn't already exist
        if (userDao.usernameExists(username)) {
            return RegistrationResult.error("Username/Email already registered.");
        }

        // Hash password and insert
        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
        int userId = userDao.insertUser(username, passwordHash);

        if (userId > 0) {
            return RegistrationResult.success();
        } else {
            return RegistrationResult.error("Failed to create account. Please try again.");
        }
    }
}
