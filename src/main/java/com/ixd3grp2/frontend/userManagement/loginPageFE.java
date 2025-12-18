package com.ixd3grp2.frontend.userManagement;

import java.sql.SQLException;

import com.ixd3grp2.DBConnection;
import com.ixd3grp2.frontend.homePageFE;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class loginPageFE {

    private static final String REGULAR_FONT_PATH = "/com/ixd3grp2/frontend/resources/fonts/ElmsSans-Regular.ttf";
    
    private DBConnection db;
    public loginPageFE(DBConnection db){
        this.db = db;
    }

    public Scene getScene(Stage stage) {

        Font regularFont = loadCustomFont(REGULAR_FONT_PATH, 18);
        Font smallFont = loadCustomFont(REGULAR_FONT_PATH, 12);
        if (regularFont == null) regularFont = Font.font("Arial", 18);
        if (smallFont == null) smallFont = Font.font("Arial", 12);

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setMaxWidth(320);
        contentBox.setPadding(new Insets(20));

        // 1. SPACER ADJUSTMENT
        Region spacer = new Region();
        spacer.setPrefHeight(233);

        // 3. INPUTS
        // Pass 'false' for email (standard text), 'true' for password
        TextField emailInput = createStyledTextField("example@gmail.com", regularFont, false);
        TextField passInput = createStyledTextField("password", regularFont, true);

        // 4. FORGOT PASSWORD
        Hyperlink forgotPass = new Hyperlink("forgot password?");
        forgotPass.setFont(smallFont);
        forgotPass.getStyleClass().add("forgot-link");
        HBox forgotContainer = new HBox(forgotPass);
        forgotContainer.setAlignment(Pos.CENTER_RIGHT);

        // 5. NEW LOGIN BUTTON
        Button btnLogin = new Button("Log in");
        btnLogin.setFont(regularFont);
        btnLogin.setMaxWidth(Double.MAX_VALUE/2); // Fill width
        btnLogin.getStyleClass().add("login-button");
        btnLogin.setOnAction(e -> {
            var email = emailInput.getText();
            var password = passInput.getText();
            try {
                if (db.login(email, password)){
                    new homePageFE().start(stage);
                } else {
                    System.out.println("user not real :unicorn:");
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        // 6. SECTION SPACER (Moves Sign Up down)
        Region sectionSpacer = new Region();
        sectionSpacer.setPrefHeight(30);

        // 7. SIGN UP SECTION
        Label lblOrSignup = new Label("or sign up");
        lblOrSignup.setFont(regularFont);
        lblOrSignup.getStyleClass().add("label-text");

        // FIX: Use setOnMouseClicked instead of setOnAction
        lblOrSignup.setOnMouseClicked(e -> {
            new homePageFE().start(stage);
        });

        // Sign up keeps the arrow style as requested
        HBox signupContainer = createArrowInputField("example@gmail.com", false, regularFont, () -> {
            registerPageFE register = new registerPageFE(db);
            stage.setScene(register.getScene(stage));
        });

        contentBox.getChildren().addAll(
                spacer,
                emailInput, 
                passInput, 
                forgotContainer,
                btnLogin,       // <--- Added Button
                sectionSpacer,  // <--- Added Spacer
                lblOrSignup, 
                signupContainer
        );

        StackPane root = new StackPane();
        root.getChildren().addAll(contentBox);

        Scene scene = new Scene(root, 1197.0/3, 2256.0/3);

        try {
            scene.getStylesheets().add(getClass().getResource("loginPage.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Warning: loginPage.css not found");
        }

        return scene;
    }

    // HELPERS
    private Font loadCustomFont(String path, double size) {
        try {
            if (getClass().getResourceAsStream(path) == null) return null;
            return Font.loadFont(getClass().getResourceAsStream(path), size);
        } catch (Exception e) { return null; }
    }

    // Updated to handle PasswordField creation
    private TextField createStyledTextField(String prompt, Font font, boolean isPassword) {
        TextField tf = isPassword ? new PasswordField() : new TextField();
        tf.setPromptText(prompt);
        if (font != null) tf.setFont(font);
        tf.getStyleClass().add("standard-text-field");
        return tf;
    }

    private HBox createArrowInputField(String prompt, boolean isPassword, Font font, Runnable action) {
        TextField tf = isPassword ? new PasswordField() : new TextField();
        tf.setPromptText(prompt);
        if (font != null) tf.setFont(font);
        tf.getStyleClass().add("inner-text-field");
        HBox.setHgrow(tf, Priority.ALWAYS);

        // Note: You would usually add a Button or Label with an arrow icon here
        // tailored to your previous logic, but kept simple here for the structure.
        HBox container = new HBox(tf);
        container.setAlignment(Pos.CENTER_RIGHT);
        container.getStyleClass().add("input-box");
        
        // Make the whole box clickable for the action if intended
        container.setOnMouseClicked(e -> action.run());
        
        return container;
    }
}
