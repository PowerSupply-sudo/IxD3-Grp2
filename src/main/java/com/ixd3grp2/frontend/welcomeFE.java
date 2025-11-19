package com.ixd3grp2.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class welcomeFE extends Application {

    private static final double WIDTH = 1197;
    private static final double HEIGHT = 2256;

    @Override
    public void start(Stage stage) {
        // Build a minimal welcome screen (placeholder) and lock the window size.
        StackPane MainScreen = new StackPane();
        Rectangle square = new Rectangle(20, 20, 20, 20);
        square.setFill(Color.GREEN);
        MainScreen.getChildren().add(square);

        Scene scene = new Scene(MainScreen, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.setTitle("Welcome");

        // Lock stage to the chosen size
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setMaxWidth(WIDTH);
        stage.setMaxHeight(HEIGHT);
        stage.setResizable(false);

        // Decide where to navigate next depending on login state
        if (isUserLoggedIn()) {
            // If logged in, hand the same stage to the home page UI
            try {
                new homePageFE().start(stage);
                return;
            } catch (Exception e) {
                System.err.println("Failed to open home page:");
                e.printStackTrace();
            }
        } else {
            // Not logged in: try to forward to loginFE if available; otherwise show placeholder
            try {
                Class<?> loginClass = Class.forName("com.ixd3grp2.frontend.loginFE");
                Object loginInstance = loginClass.getDeclaredConstructor().newInstance();
                // Attempt to call start(Stage) on the loginFE instance
                loginClass.getMethod("start", Stage.class).invoke(loginInstance, stage);
                return;
            } catch (ClassNotFoundException cnfe) {
                // loginFE not yet implemented — show a temporary placeholder with a button
                StackPane placeholder = new StackPane();
                Text info = new Text("Login screen is under development.\nClick to simulate login.");
                Button simulate = new Button("Simulate login -> Home");
                simulate.setOnAction(ae -> {
                    // Create a simple marker file so subsequent launches route to homePageFE
                    try {
                        createSessionMarker();
                    } catch (IOException ioException) {
                        // ignore
                    }
                    try {
                        new homePageFE().start(stage);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                placeholder.getChildren().addAll(info, simulate);
                Scene phScene = new Scene(placeholder, WIDTH, HEIGHT);
                stage.setScene(phScene);
                stage.show();
                return;
            } catch (Exception e) {
                System.err.println("Failed to launch loginFE via reflection:");
                e.printStackTrace();
            }
        }

        // If all routing attempts fail, just show the default welcome UI
        stage.show();
    }

    private boolean isUserLoggedIn() {
        // Priority: environment variable WISHLISTAPP_LOGGEDIN=true
        String env = System.getenv("WISHLISTAPP_LOGGEDIN");
        if (env != null && env.equalsIgnoreCase("true")) return true;

        // Otherwise check for a lightweight session marker file in the working directory
        File marker = new File("session.logged");
        return marker.exists();
    }

    private void createSessionMarker() throws IOException {
        File marker = new File("session.logged");
        if (!marker.exists()) marker.createNewFile();
    }
}