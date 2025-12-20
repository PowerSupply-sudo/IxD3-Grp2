-- Create the application database and users table (safe for first-run initialization)
CREATE DATABASE IF NOT EXISTS `wishlistapp` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `wishlistapp`;

CREATE TABLE IF NOT EXISTS `users` (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO users (
    username,
    password
) VALUES

    ('Nicklas', '1234'),
    ('Sarah', '4321'),
    ('Jens', 'nej'),
    ('test', '1234');

    COMMIT;