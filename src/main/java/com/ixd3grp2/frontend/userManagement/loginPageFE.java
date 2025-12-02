package com.ixd3grp2.frontend.userManagement;

import com.ixd3grp2.auth.AuthService;
import com.ixd3grp2.frontend.homePageFE;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.File;

public class loginPageFE {

    private static final String REGULAR_FONT_PATH = "/com/ixd3grp2/frontend/resources/fonts/ElmsSans-Regular.ttf";
    private AuthService authService;

    public Scene getScene(Stage stage) {
        authService = new AuthService();
 
        Font regularFont = loadCustomFont(REGULAR_FONT_PATH, 18);
        Font smallFont = loadCustomFont(REGULAR_FONT_PATH, 12);
        if (regularFont == null) regularFont = Font.font("Arial", 18);
        if (smallFont == null) smallFont = Font.font("Arial", 12);

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setMaxWidth(320);
        contentBox.setPadding(new Insets(20)); 

        // 1. SPACER ADJUSTMENT
        // Increased to 300 to push text below the logo
        Region spacer = new Region();
        spacer.setPrefHeight(233); 

        // 2. LABELS
        Label lblLogin = new Label("Log in");
        lblLogin.setFont(regularFont);
        lblLogin.getStyleClass().add("label-text");

        // 3. INPUTS
        TextField emailInput = createStyledTextField("example@gmail.com", regularFont);
        
        PasswordField passInput = new PasswordField();
        passInput.setPromptText("password");
        passInput.setFont(regularFont);
        passInput.getStyleClass().add("inner-text-field");
        
        Label errorMessage = new Label();
        errorMessage.setStyle("-fx-text-fill: #ff0000; -fx-font-size: 12;");
        errorMessage.setVisible(false);
        
        HBox passContainer = new HBox();
        passContainer.setAlignment(Pos.CENTER_RIGHT);
        passContainer.getStyleClass().add("input-box");
        
        Label arrow = new Label(">>"); 
        arrow.getStyleClass().add("arrow-icon");
        arrow.setStyle("-fx-cursor: hand;");
        arrow.setOnMouseClicked(e -> {
            String email = emailInput.getText().trim();
            String password = passInput.getText();
            
            // Validate inputs
            if (email.isEmpty()) {
                errorMessage.setText("Email cannot be empty");
                errorMessage.setVisible(true);
                return;
            }
            if (password.isEmpty()) {
                errorMessage.setText("Password cannot be empty");
                errorMessage.setVisible(true);
                return;
            }
            
            // Verify credentials
            if (authService.verifyLogin(email, password)) {
                errorMessage.setVisible(false);
                // Create session marker
                try {
                    new File("session.logged").createNewFile();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                new homePageFE().start(stage);
            } else {
                errorMessage.setText("Invalid email or password");
                errorMessage.setVisible(true);
            }
        });
        
        HBox.setHgrow(passInput, Priority.ALWAYS);
        passContainer.getChildren().addAll(passInput, arrow);
        
        // 4. FORGOT PASSWORD
        Hyperlink forgotPass = new Hyperlink("forgot password?");
        forgotPass.setFont(smallFont);
        forgotPass.getStyleClass().add("forgot-link");
        HBox forgotContainer = new HBox(forgotPass);
        forgotContainer.setAlignment(Pos.CENTER_RIGHT);

        // 5. SIGN UP SECTION
        Label lblOrSignup = new Label("or sign up");
        lblOrSignup.setFont(regularFont);
        lblOrSignup.getStyleClass().add("label-text");
        lblOrSignup.setPadding(new Insets(10, 0, 0, 0));

        TextField signupEmailInput = createStyledTextField("example@gmail.com", regularFont);
        HBox signupContainer = new HBox(signupEmailInput);
        signupContainer.setAlignment(Pos.CENTER_RIGHT);
        signupContainer.getStyleClass().add("input-box");
        
        Label signupArrow = new Label(">>"); 
        signupArrow.getStyleClass().add("arrow-icon");
        signupArrow.setStyle("-fx-cursor: hand;");
        signupArrow.setOnMouseClicked(e -> {
            registerPageFE register = new registerPageFE();
            stage.setScene(register.getScene(stage));
        });
        
        HBox.setHgrow(signupEmailInput, Priority.ALWAYS);
        signupContainer.getChildren().add(signupArrow);

        contentBox.getChildren().addAll(
                spacer, 
                lblLogin, emailInput, passContainer, errorMessage, forgotContainer,
                lblOrSignup, signupContainer
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

    // --- HELPERS ---
    private Font loadCustomFont(String path, double size) {
        try {
            if (getClass().getResourceAsStream(path) == null) return null;
            return Font.loadFont(getClass().getResourceAsStream(path), size);
        } catch (Exception e) { return null; }
    }

    private TextField createStyledTextField(String prompt, Font font) {
        TextField tf = new TextField();
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

        HBox container = new HBox(tf);
        container.setAlignment(Pos.CENTER_RIGHT);
        container.getStyleClass().add("input-box");
        return container;
    }
}