package com.ixd3grp2.frontend;

import com.ixd3grp2.frontend.userManagement.loginPageFE;
import javafx.application.Application;
import javafx.scene.Scene;
<<<<<<< HEAD
<<<<<<< HEAD
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
=======
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
>>>>>>> 10b8adf (added registerPageFE, made sure loginPageFE's sign in button routes you to homePageFE, sign up button reroutes you to registerPageFE, visually overhauled both pages to match high fidelity figma design. welcomeFE has been changed.)
=======
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
>>>>>>> de9b928 (har merge'et pom.xml to fix conflikts)
import javafx.stage.Stage;
import java.io.File;

public class welcomeFE extends Application {

    // Global dimensions (approx 400x750)
    public static final double WIDTH = 1197.0 / 3;
    public static final double HEIGHT = 2256.0 / 3;

    @Override
    public void start(Stage stage) {
<<<<<<< HEAD
        
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
=======
        // 1. Configure the Stage (Window)
        stage.setTitle("Avarice");
        stage.setMinWidth(WIDTH);
        stage.setMinHeight(HEIGHT);
        stage.setMaxWidth(WIDTH);
        stage.setMaxHeight(HEIGHT);
        stage.setResizable(false);

        // 2. Check Login Status
        if (isUserLoggedIn()) {
            // If logged in, go to Home
            new homePageFE().start(stage);
        } else {
            // Not logged in: Show Login Page
            // We ask loginPageFE to give us its scene
            loginPageFE login = new loginPageFE();
            Scene loginScene = login.getScene(stage);
            
            stage.setScene(loginScene);
        }
        
        stage.show();
>>>>>>> 10b8adf (added registerPageFE, made sure loginPageFE's sign in button routes you to homePageFE, sign up button reroutes you to registerPageFE, visually overhauled both pages to match high fidelity figma design. welcomeFE has been changed.)
    }

    private boolean isUserLoggedIn() {
        String env = System.getenv("WISHLISTAPP_LOGGEDIN");
        if (env != null && env.equalsIgnoreCase("true")) return true;
        File marker = new File("session.logged");
        return marker.exists();
    }
}