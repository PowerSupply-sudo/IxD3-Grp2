// Java
package com.ixd3grp2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class DatabaseInitializer {
    public static void initializeDatabase() throws SQLException, IOException {
        String sql = new String(Files.readAllBytes(Paths.get("src/db/user-logins.sql")));

        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Database initialized successfully");
        }
    }
}
