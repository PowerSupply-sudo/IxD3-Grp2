package com.ixd3grp2.frontend;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.ixd3grp2.frontend.wishLists.wishListsFE;


public class homePageFE extends Application{
    @Override
    public void start(Stage stage) {     
    // We create the text component
        //Here is the Headline "324"
        Text headline = new Text("324");
        headline.setFont(new Font("Elms sans", 48));// Large font size for headline
        headline.setFill(Color.web("#31672aff")); // Dark green color

        // Here is the subtext "DKK saved"
        Text subtext = new Text("Days You Æ");
        subtext.setFont(new Font("Elms sans", 24));// Smaller font size for subtext
        subtext.setFill(Color.web("#31672aff")); // Dark green color

        // Create a VBox for headline and subtext
        VBox textContent = new VBox();
        textContent.setAlignment(Pos.CENTER); // Center text
        textContent.setSpacing(10); // Space between headline and subtext
        textContent.setStyle("-fx-padding: -500px 0 0 0;"); // Add top padding to move content up
        textContent.getChildren().addAll(headline, subtext); // Add headline and subtext to VBox

       // Create the "Add list +" button
        Button addWishButton = new Button("Add Wish +");
        addWishButton.setStyle("-fx-background-color: #b1d06aff; -fx-text-fill: #31672aff; -fx-border-color: #31672aff; -fx-font-size: 16px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;"); // Styling for the button
        
        // Gør knappen klikbar — åbn addWishFE som et modulært vindue
       addWishButton.setOnAction(e -> addWishFE.showAddWishScene(stage));

        // We create the root containing all the above
        //Group root = new Group();
        //root.getChildren().addAll(headline, subtext);

        // Create a separate VBox for the button
        VBox buttonContent = new VBox();
        buttonContent.setAlignment(Pos.CENTER); // Center button
        buttonContent.setSpacing(40); // Space between elements
        buttonContent.getChildren().add(addWishButton); // Add button to VBox

        /** Combining textContent and buttonContent into one VBox*/
        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER); // Center content
        centerContent.setSpacing(10); // Space between headline and subtext
        centerContent.setStyle("-fx-padding: 200px 0 0 0;");// Add top padding to push content up
        centerContent.getChildren().addAll(textContent, buttonContent); // Add headline, subtext, and button to VBox
        

        //Create the bottombar
        HBox bottombar = new HBox();
        bottombar.setSpacing(40);// Space between buttons
        bottombar.setStyle("-fx-background-color: #b1d06aff; -fx-padding: 10px; -fx-min-height: 60px;"); // Ligth greenbackground med fixed height
        //bottombar.setLayoutY(2256/3 - 50); // Position at the bottom of the scene
        bottombar.setAlignment(Pos.CENTER);// Center buttons horizontally and vertically


        // Create buttons for the bottom bar
        Button searchButton = new Button("Search");
        Button homeButton = new Button("Home");
        Button profileButton = new Button("Profile");
    

        String buttonStyle = "-fx-border-color: #31672aff; -fx-text-fill: #31672aff; "
                        + "-fx-font-size: 16px; -fx-padding: 10px 20px; "
                        + "-fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);
        

        //---------------------------- Wish Lists Button ----------------
       // Wish Lists-knap
        Button wishListsButton = new Button("Wish Lists");
        wishListsButton.setStyle("-fx-background-color: #b1d06aff; -fx-text-fill: #31672aff; "
            + "-fx-border-color: #31672aff; -fx-font-size: 16px; -fx-padding: 10px 20px; "
            + "-fx-border-radius: 5px; -fx-background-radius: 5px;");
        wishListsButton.setOnAction(e -> wishListsFE.showWishListsScene(stage));

        // VBox til knappen (placeret lige over bottom bar)
        VBox wishListsBox = new VBox();
        wishListsBox.setAlignment(Pos.CENTER);
        wishListsBox.setPadding(new Insets(0, 0, 40, 0)); // 40px afstand til bottom bar
        wishListsBox.getChildren().add(wishListsButton);

        // Layout
        BorderPane layout = new BorderPane();
        layout.setCenter(centerContent);   // dit hovedindhold
        layout.setBottom(new VBox(wishListsBox, bottombar)); // knap + bottom bar




        /**
        button = new button();
        searchButton.setStyle("-fx-border-color: #31672aff;");// Green background
        homeButton.setStyle("-fx-border-color: #31672aff;"); // Green background
        profileButton.setStyle("-fx-border-color: #31672aff;"); // Green background */
        // Add buttons to the bottombar
        
        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);
    
    // Use the existing BorderPane to position the bottombar at the bottom
    // (do not redeclare `layout` here — it was declared above)
       

        // We instantiate a new Scene of size 300x250, with white background and and associated scene graph rooted in 'layout'
        Scene scene = new Scene(layout, 1197/3, 2256/3, Color.WHITE);

        
        // We set the scene on the stage and display it
        stage.setTitle("Stage Title");
        stage.setScene(scene);
        stage.show();
    }
    
    
    /**VBox content = new VBox(12);
    content.setPadding(new Insets(12));
    content.setAlignment(Pos.CENTER);

    TextField wishTitle = new TextField();
    wishTitle.setPromptText("Enter Wish title");

    HBox buttonRow = new HBox(10);
    buttonRow.setAlignment(Pos.CENTER);
    Button addToWishlist = new Button("Add to wishlist");
    Button newWishlist = new Button("New wishlist");
    buttonRow.getChildren().addAll(addToWishlist, newWishlist);

    Button continueButton = new Button("Continue");
    continueButton.setOnAction(ev -> {
       dialog.close();
    });
    continueButton.setStyle("-fx-padding: 8px 36px;");

    content.getChildren().addAll(wishTitle, buttonRow, continueButton);

    dialog.getDialogPane().setContent(content);
    dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
    dialog.showAndWait();
    */
    
    public static void main(String[] args) {
        /* Launch the Application class in which is in */
        launch(args);

        // The above is equivalent to calling application.launch(SimpleStage.class, args);
    }
}
