package com.ixd3grp2.frontend.loginPage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class loginPageFE extends Application {

    //created variables for font paths to keep it clean
    private static final String REGULAR_FONT_PATH = "/com/ixd3grp2/frontend/resources/fonts/ElmsSans-Regular.ttf";
    private static final String TITLE_FONT_PATH   = "/com/ixd3grp2/frontend/resources/fonts/FingerPaint-Regular.ttf";

    @Override
    public void start(Stage stage) {
 
        //load custom fonts (we might need to put these somewhere in a global stylesheet)
        Font smallFont   = loadCustomFont(REGULAR_FONT_PATH, 12); //i'm using smallFont for stuff like "forgot password" text
        Font regularFont = loadCustomFont(REGULAR_FONT_PATH, 16); 
        Font titleFont = loadCustomFont(TITLE_FONT_PATH, 45);
        
        //If these fonts stop existing for some weird reason we can just use these
        if (smallFont == null) smallFont = Font.font("Arial", 12);
        if (regularFont == null) regularFont = Font.font("Arial", 16);
        if (titleFont == null) titleFont = Font.font("Segoe Script", FontWeight.BOLD, 45);


        VBox contentBox = new VBox(15);
        contentBox.setAlignment(Pos.TOP_CENTER);
        contentBox.setMaxWidth(320);
        contentBox.setPadding(new Insets(50, 20, 20, 20));


        Label lblWelcome = new Label("welcome to");
        lblWelcome.setFont(regularFont);

        Label lblTitle = new Label("Avarice");
        lblTitle.setFont(titleFont);
        lblTitle.getStyleClass().add("label-title");

        // (login and sign up swction but there is no functionality yet)
        Label lblLogin = new Label("log in");
        lblLogin.setFont(regularFont);

        //DON'T DELETE THIS PLEASE:
        //lblLogin.setMaxWidth(Double.MAX_VALUE); MAX_VALUE streches it to full width, aligning it to the left. this combines with the line below
        //lblLogin.setAlignment(Pos.CENTER_LEFT); example of what i would need to add if i wanted to align it left. i do not want that now tho
        
        TextField emailInput = createStyledTextField("example@gmail.com", regularFont);
        HBox passContainer = createArrowInputField("password", true, regularFont);
        

        Hyperlink forgotPass = new Hyperlink("forgot password?");
        forgotPass.setFont(smallFont);
        forgotPass.getStyleClass().add("forgot-link");
        
        HBox forgotContainer = new HBox(forgotPass);
        forgotContainer.setAlignment(Pos.CENTER_RIGHT);


        Label lblOrSignup = new Label("or sign up");
        lblOrSignup.setFont(regularFont);
        lblOrSignup.setPadding(new Insets(10, 0, 0, 0));

        HBox signupContainer = createArrowInputField("example@gmail.com", false, regularFont);


        contentBox.getChildren().addAll(
                lblWelcome, lblTitle,
                lblLogin, emailInput, passContainer, forgotContainer,
                lblOrSignup, signupContainer,
                new Label("") 
        );

        StackPane root = new StackPane();
        
        root.getChildren().addAll(contentBox);
        Scene scene = new Scene(root, 1197/3, 2256/3);


        //get the loginpage css file and if not found print a warning
        try {
            scene.getStylesheets().add(getClass().getResource("loginPage.css").toExternalForm());
        } catch (Exception e) {
            System.out.println("Warning: you probably moved the loginpage.css file lol");
            e.printStackTrace();
        }

        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
    //iIMPORTANT NOTE ABOUT THE BELOW HELPERS
    //VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV

    //this following code below written by an LLM. i will check it out tomorrow

    // --- HELPERS ---

    private Font loadCustomFont(String path, double size) {
        try {
            if (getClass().getResourceAsStream(path) == null) {
                System.out.println("ERROR: Could not find font at: " + path);
                return null;
            }
            return Font.loadFont(getClass().getResourceAsStream(path), size);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private TextField createStyledTextField(String prompt, Font font) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        if (font != null) tf.setFont(font);
        
        // Add the CSS class defined in .standard-text-field
        tf.getStyleClass().add("standard-text-field");
        
        return tf;
    }

    private HBox createArrowInputField(String prompt, boolean isPassword, Font font) {
        // The input inside
        TextField tf;
        if (isPassword) {
            tf = new PasswordField();
        } else {
            tf = new TextField();
        }
        tf.setPromptText(prompt);
        if (font != null) tf.setFont(font);
        
        // This is the TRANSPARENT internal text field
        tf.getStyleClass().add("inner-text-field");
        
        HBox.setHgrow(tf, Priority.ALWAYS);

        Label arrow = new Label("➔");
        arrow.getStyleClass().add("arrow-icon");

        HBox container = new HBox(tf, arrow);
        container.setAlignment(Pos.CENTER_RIGHT);
        
        // This container creates the Border look
        container.getStyleClass().add("input-box");
        
        return container;
    }

    public static void main(String[] args) {
        launch();
    }
}