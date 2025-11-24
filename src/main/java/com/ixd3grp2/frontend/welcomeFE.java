package com.ixd3grp2.frontend;

import com.ixd3grp2.frontend.userManagement.loginPageFE;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.File;

public class welcomeFE extends Application {

    // Global dimensions (approx 400x750)
    public static final double WIDTH = 1197.0 / 3;
    public static final double HEIGHT = 2256.0 / 3;

    @Override
    public void start(Stage stage) {
        
        // 1. Check Login Status first
        if (isUserLoggedIn()) {
            // If logged in, go to Home
            // Note: Ensure homePageFE has the method .show(stage) or .start(stage) depending on your file
            new homePageFE().start(stage); 
        } else {
            // 2. Not logged in: Load Login Page
            loginPageFE login = new loginPageFE();
            Scene loginScene = login.getScene(stage);
            
            // 3. Set the scene onto the stage
            stage.setScene(loginScene);
            
            // 4. Show the stage
            stage.show();
            
            // 5. NOW we can size to scene (because the scene is set)
            // This removes the extra whitespace at the bottom
            stage.sizeToScene();
            stage.setResizable(false);
        }
    }

    private boolean isUserLoggedIn() {
        String env = System.getenv("WISHLISTAPP_LOGGEDIN");
        if (env != null && env.equalsIgnoreCase("true")) return true;
        File marker = new File("session.logged");
        return marker.exists();
    }
}