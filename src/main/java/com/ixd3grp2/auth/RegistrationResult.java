package com.ixd3grp2.auth;

/**
 * Result object for registration operations.
 * Contains success flag and optional error message.
 */
public class RegistrationResult {
    public final boolean success;
    public final String message;

    public RegistrationResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public static RegistrationResult success() {
        return new RegistrationResult(true, "Registration successful!");
    }

    public static RegistrationResult error(String message) {
        return new RegistrationResult(false, message);
    }
}
