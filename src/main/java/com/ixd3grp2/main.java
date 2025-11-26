package com.ixd3grp2;

public class main {
    public static void main (String[] args) {
        // Dispatcher: decide whether to run frontend (JavaFX) or backend tasks.
        if (args != null && args.length > 0) {
            String cmd = args[0].toLowerCase();
            switch (cmd) {
                case "frontend":
                    // Launch the JavaFX Application (welcome screen)
                    javafx.application.Application.launch(com.ixd3grp2.frontend.welcomeFE.class, args);
                    break;
                case "initdb":
                case "backend-init":
                    // Run database initialization
                    try {
                        com.ixd3grp2.DatabaseInitializer.initializeDatabase();
                    } catch (Exception e) {
                        System.err.println("Failed to initialize database:");
                        e.printStackTrace();
                        System.exit(1);
                    }
                    break;
                case "backend":
                    System.out.println("No general backend entrypoint defined. Use 'initdb' to initialize the database.");
                    break;
                case "help":
                case "--help":
                case "-h":
                default:
                    printUsage();
                    break;
            }
        } else {
            // Default behaviour: start frontend (welcome screen)
                javafx.application.Application.launch(com.ixd3grp2.frontend.welcomeFE.class, args);
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java -cp <your-classpath> com.ixd3grp2.main [command]\n" +
                "Commands:\n" +
                "  frontend       Launch the JavaFX frontend (default)\n" +
                "  initdb         Initialize the database from src/db/user-logins.sql\n" +
                "  backend        Placeholder for backend run tasks\n" +
                "  help           Show this help message\n");
    }
}