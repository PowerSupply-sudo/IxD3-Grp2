package com.ixd3grp2.frontend.userManagement;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class registerPageFE {

    private static final String REGULAR_FONT_PATH = "/com/ixd3grp2/frontend/resources/fonts/ElmsSans-Regular.ttf";

    public Scene getScene(Stage stage) {
 
        Font regularFont = loadCustomFont(REGULAR_FONT_PATH, 16); 
        Font titleFont = loadCustomFont(REGULAR_FONT_PATH, 36);
        
        if (regularFont == null) regularFont = Font.font("Arial", 16);
        if (titleFont == null) titleFont = Font.font("Arial", 36);

        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setMaxWidth(340); 
        contentBox.setPadding(new Insets(20));

        // --- TITLE ---
        Label lblTitle = new Label("sign up");
        lblTitle.setFont(titleFont);
        lblTitle.getStyleClass().add("label-text");
        VBox.setMargin(lblTitle, new Insets(0, 0, 10, 0));

        // --- INPUTS ---
        TextField emailInput = createStyledTextField("example@gmail.com", regularFont);
        
        Label lblSelectPass = new Label("select password");
        lblSelectPass.setFont(regularFont);
        lblSelectPass.getStyleClass().add("label-text");

        TextField passInput = createStyledTextField("password", regularFont); 
        
        Label lblConfirmPass = new Label("confirm password");
        lblConfirmPass.setFont(regularFont);
        lblConfirmPass.getStyleClass().add("label-text");
        
        TextField confirmPassInput = createStyledTextField("password", regularFont);

        // --- BUTTON ---
        Button btnContinue = new Button("continue  →");
        btnContinue.setFont(regularFont);
        btnContinue.getStyleClass().add("brown-button");
        btnContinue.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(btnContinue, new Insets(20, 0, 0, 0));
        
        btnContinue.setOnAction(e -> {
            loginPageFE login = new loginPageFE();
            stage.setScene(login.getScene(stage));
        });

        // --- BACK LINK ---
        Hyperlink backLink = new Hyperlink("back to login");
        backLink.getStyleClass().add("forgot-link");
        backLink.setOnAction(e -> {
            loginPageFE login = new loginPageFE();
            stage.setScene(login.getScene(stage));
        });

        contentBox.getChildren().addAll(
                lblTitle,
                emailInput,
                lblSelectPass,
                passInput,
                lblConfirmPass,
                confirmPassInput,
                btnContinue,
                backLink
        );

        StackPane root = new StackPane();
        root.getChildren().addAll(contentBox);
        
        Scene scene = new Scene(root, 1197.0/3, 2256.0/3);

        // IMPORTANT: Point to registerPage.css
        try {
            scene.getStylesheets().add(getClass().getResource("registerPage.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Warning: registerPage.css not found");
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
}