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
import com.ixd3grp2.auth.AuthService;
import com.ixd3grp2.auth.RegistrationResult;
import com.ixd3grp2.frontend.homePageFE;

public class loginPageFE extends Application {

    //created variables for font paths to keep it clean
    private static final String REGULAR_FONT_PATH = "/com/ixd3grp2/frontend/resources/fonts/ElmsSans-Regular.ttf";
    private static final String TITLE_FONT_PATH   = "/com/ixd3grp2/frontend/resources/fonts/FingerPaint-Regular.ttf";
    
    private AuthService authService;
    private Stage primaryStage;
    private Label messageLabel;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        this.authService = new AuthService();
 
        //load custom fonts (we might need to put these somewhere in a global stylesheet)
        Font smallFont   = loadCustomFont(REGULAR_FONT_PATH, 12); //i'm using smallFont for stuff like "forgot password" text
        Font regularFont = loadCustomFont(REGULAR_FONT_PATH, 16); 
        Font titleFont = loadCustomFont(TITLE_FONT_PATH, 45);
        
        //If these fonts stop existing for some weird reason we can just use these
        if (smallFont == null) smallFont = Font.font("Arial", 12);
        if (regularFont == null) regularFont = Font.font("Arial", 16);
        if (titleFont == null) titleFont = Font.font("Segoe Script", FontWeight.BOLD, 45);

        // Create header (welcome + title)
        VBox headerBox = new VBox(5);
        headerBox.setAlignment(Pos.TOP_CENTER);
        headerBox.setPadding(new Insets(30, 20, 20, 20));

        Label lblWelcome = new Label("welcome to");
        lblWelcome.setFont(regularFont);

        Label lblTitle = new Label("Avarice");
        lblTitle.setFont(titleFont);
        lblTitle.getStyleClass().add("label-title");

        headerBox.getChildren().addAll(lblWelcome, lblTitle);

        // Create tab pane for login and signup
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle("-fx-font-size: 14;");

        // Login tab
        Tab loginTab = createLoginTab(regularFont, smallFont);
        loginTab.setText("Log In");

        // Sign up tab
        Tab signupTab = createSignUpTab(regularFont, smallFont);
        signupTab.setText("Sign Up");

        tabPane.getTabs().addAll(loginTab, signupTab);

        // Message label for errors/success
        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: #c70039; -fx-font-size: 12;");
        messageLabel.setWrapText(true);

        // Main container
        VBox mainContainer = new VBox(10);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(10, 30, 20, 30));
        mainContainer.getChildren().addAll(headerBox, tabPane, messageLabel);

        StackPane root = new StackPane();
        root.getChildren().addAll(mainContainer);
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

    /**
     * Create the login tab with email/password fields and login button.
     */
    private Tab createLoginTab(Font regularFont, Font smallFont) {
        VBox loginBox = new VBox(15);
        loginBox.setAlignment(Pos.TOP_CENTER);
        loginBox.setPadding(new Insets(20));
        loginBox.setMaxWidth(320);

        Label lblLogin = new Label("log in");
        lblLogin.setFont(regularFont);

        TextField emailInput = createStyledTextField("username or email", regularFont);
        HBox passContainer = createArrowInputField("password", true, regularFont);
        PasswordField passField = (PasswordField) ((HBox) passContainer).getChildren().get(0);

        Hyperlink forgotPass = new Hyperlink("forgot password?");
        forgotPass.setFont(smallFont);
        forgotPass.getStyleClass().add("forgot-link");

        HBox forgotContainer = new HBox(forgotPass);
        forgotContainer.setAlignment(Pos.CENTER_RIGHT);

        Button loginButton = new Button("LOGIN");
        loginButton.setStyle("-fx-font-size: 14; -fx-padding: 8;");
        loginButton.setPrefWidth(100);

        loginButton.setOnAction(ae -> {
            String username = emailInput.getText().trim();
            String password = passField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                showMessage("Please enter username and password.", true);
                return;
            }

            boolean loginSuccess = authService.verifyLogin(username, password);
            if (loginSuccess) {
                showMessage("Login successful! Redirecting...", false);
                try {
                    Thread.sleep(500); // brief delay for UX
                    new homePageFE().start(primaryStage);
                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Failed to open home page.", true);
                }
            } else {
                showMessage("Invalid username or password.", true);
            }
        });

        loginBox.getChildren().addAll(
                lblLogin,
                emailInput,
                passContainer,
                forgotContainer,
                loginButton
        );

        Tab tab = new Tab();
        tab.setContent(loginBox);
        return tab;
    }

    /**
     * Create the sign up tab with email/password fields and register button.
     */
    private Tab createSignUpTab(Font regularFont, Font smallFont) {
        VBox signupBox = new VBox(15);
        signupBox.setAlignment(Pos.TOP_CENTER);
        signupBox.setPadding(new Insets(20));
        signupBox.setMaxWidth(320);

        Label lblSignup = new Label("sign up");
        lblSignup.setFont(regularFont);

        TextField emailInput = createStyledTextField("email or username", regularFont);
        HBox passContainer = createArrowInputField("password", true, regularFont);
        PasswordField passField = (PasswordField) ((HBox) passContainer).getChildren().get(0);

        HBox confirmPassContainer = createArrowInputField("confirm password", true, regularFont);
        PasswordField confirmPassField = (PasswordField) ((HBox) confirmPassContainer).getChildren().get(0);

        Button registerButton = new Button("SIGN UP");
        registerButton.setStyle("-fx-font-size: 14; -fx-padding: 8;");
        registerButton.setPrefWidth(100);

        registerButton.setOnAction(ae -> {
            String email = emailInput.getText().trim();
            String password = passField.getText();
            String confirmPassword = confirmPassField.getText();

            RegistrationResult result = authService.register(email, password, confirmPassword);

            if (result.success) {
                showMessage("Account created! Please log in.", false);
                // Clear fields
                emailInput.clear();
                passField.clear();
                confirmPassField.clear();
            } else {
                showMessage(result.message, true);
            }
        });

        signupBox.getChildren().addAll(
                lblSignup,
                emailInput,
                passContainer,
                confirmPassContainer,
                registerButton
        );

        Tab tab = new Tab();
        tab.setContent(signupBox);
        return tab;
    }

    /**
     * Display a message to the user.
     */
    private void showMessage(String message, boolean isError) {
        messageLabel.setText(message);
        if (isError) {
            messageLabel.setStyle("-fx-text-fill: #c70039; -fx-font-size: 12;");
        } else {
            messageLabel.setStyle("-fx-text-fill: #06a83a; -fx-font-size: 12;");
        }
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