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
    }

    private boolean isUserLoggedIn() {
        String env = System.getenv("WISHLISTAPP_LOGGEDIN");
        if (env != null && env.equalsIgnoreCase("true")) return true;
        File marker = new File("session.logged");
        return marker.exists();
    }
}