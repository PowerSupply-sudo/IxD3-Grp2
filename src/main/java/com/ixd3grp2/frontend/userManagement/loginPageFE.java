package com.ixd3grp2.frontend.userManagement;

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
        // Increased to 300 to push text below the logo
        Region spacer = new Region();
        spacer.setPrefHeight(300); 

        // 2. LABELS
        Label lblLogin = new Label("Log in");
        lblLogin.setFont(regularFont);
        lblLogin.getStyleClass().add("label-text");

        // 3. INPUTS
        TextField emailInput = createStyledTextField("example@gmail.com", regularFont);
        
        HBox passContainer = createArrowInputField("password", true, regularFont, () -> {
            new homePageFE().start(stage);
        });
        
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

        HBox signupContainer = createArrowInputField("example@gmail.com", false, regularFont, () -> {
            registerPageFE register = new registerPageFE();
            stage.setScene(register.getScene(stage));
        });

        contentBox.getChildren().addAll(
                spacer, 
                lblLogin, emailInput, passContainer, forgotContainer,
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

        Label arrow = new Label(">>"); 
        arrow.getStyleClass().add("arrow-icon");
        arrow.setOnMouseClicked(e -> { if (action != null) action.run(); });

        HBox container = new HBox(tf, arrow);
        container.setAlignment(Pos.CENTER_RIGHT);
        container.getStyleClass().add("input-box");
        return container;
    }
}