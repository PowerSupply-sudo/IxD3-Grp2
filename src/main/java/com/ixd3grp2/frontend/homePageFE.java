package com.ixd3grp2.frontend;
import com.ixd3grp2.frontend.userManagement.profilePageFE;


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
//---------------------------- Bottom Bar ----------------------------
        //Create the bottombar
        HBox bottombar = new HBox();
        bottombar.setSpacing(40);// Space between buttons
        bottombar.setStyle("-fx-background-color: #6c584c; -fx-padding: 10px; -fx-min-height: 60px;"); // Dark beige background
        //bottombar.setLayoutY(2256/3 - 50); // Position at the bottom of the scene
        bottombar.setAlignment(Pos.CENTER);// Center buttons horizontally and vertically


        // Create buttons for the bottom bar
        Button searchButton = new Button("\uD83D\uDD0D");
        Button homeButton = new Button("\uD83C\uDFE0");
        Button profileButton = new Button("\uD83D\uDC64");
    

        String buttonStyle = "-fx-background-color: #DDE5B6; -fx-border-color: #31672aff; -fx-text-fill: #BLACK; "
                        + "-fx-font-size: 16px;-fx-font-family: 'Elms sans';" 
                        + "-fx-padding: 10px 20px; "
                        + "-fx-background-radius: 5px; -fx-border-radius: 5px;"
                        + "-fx-cursor: hand;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);
        profileButton.setOnAction(e -> profilePageFE.showProfileScene(stage));

        //---------------------------- Add Buttons to Bottom Bar ----------------------------
        // Add buttons to the bottom bar        
        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);
        
//---------------------------- Center Content ----------------------------
    //--------------------Creating tekst components and content -----------------------

        //------------days saved section------------
        //Here is the Headline "324"
        Text headline = new Text("324");
        headline.setFont(new Font("Elms sans", 40));// Large font size for headline
        headline.setFill(Color.web("#31672aff")); // Dark green color

         // Here is the subtext "¤ saved"
        Text subtext = new Text("$ Saved");
        subtext.setFont(new Font("Elms sans", 20));// Smaller font size for subtext
        subtext.setFill(Color.web("#31672aff")); // Dark green color

        //------------money saved section------------
         //Here is the Headline "30"
        Text headline2 = new Text("30");
        headline2.setFont(new Font("Elms sans", 40));// Large font size for headline
        headline2.setFill(Color.web("#31672aff")); // Dark green color

        // Here is the subtext "days without purchasing"
        Text subtext2 = new Text("Days Without Purchasing");
        subtext2.setFont(new Font("Elms sans", 20));// Smaller font size for subtext
        subtext2.setFill(Color.web("#31672aff")); // Dark green color


        // Første sektion (headline + subtext)
        VBox section1 = new VBox();
        section1.setAlignment(Pos.CENTER);
        section1.setSpacing(1); // afstand mellem headline og subtext
        section1.getChildren().addAll(headline, subtext);

        // Anden sektion (headline2 + subtext2)
        VBox section2 = new VBox();
        section2.setAlignment(Pos.CENTER);
        section2.setSpacing(1); // afstand mellem headline2 og subtext2
        section2.getChildren().addAll(headline2, subtext2);

        // Saml begge sektioner i en overordnet VBox
        VBox textContent = new VBox();
        textContent.setAlignment(Pos.CENTER);
        textContent.setSpacing(30); // afstand mellem section1 og section2
        textContent.setStyle("-fx-padding: -500px 0 0 0;");
        textContent.getChildren().addAll(section1, section2);
                
        //---------------------------- Add Wish Button ----------------------------
       // Create the "Add list +" button
        Button addWishButton = new Button("Add Wish +");
        addWishButton.setStyle("-fx-background-color: #a98467; -fx-text-fill: BLACK;" 
                                +"-fx-border-color: BLACK;"
                                +"-fx-font-size: 16px;-fx-font-family: 'Elms sans'; "
                                +"-fx-padding: 10px 20px; -fx-border-radius: 5px;"
                                +"-fx-background-radius: 5px;"
                                +"-fx-cursor: hand;"); // Styling for the button
        
        // Gør knappen klikbar — åbn addWishFE som et modulært vindue
        addWishButton.setOnAction(e -> addWishFE.showAddWishScene(stage));

        // Create a separate VBox for the button
        VBox buttonContent = new VBox();
        buttonContent.setAlignment(Pos.CENTER); // Center button
        buttonContent.setSpacing(40); // Space between elements
        buttonContent.getChildren().add(addWishButton); // Add button to VBox

    //---------------------------- Combine Text and Button Content ----------------------------
        /** Combining textContent and buttonContent into one VBox*/
        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER); // Center content
        centerContent.setSpacing(10); // Space between headline and subtext
        centerContent.setStyle("-fx-padding: 200px 0 0 0;");// Add top padding to push content up
        centerContent.getChildren().addAll(textContent, buttonContent); // Add headline, subtext, and button to VBox
        


        //---------------------------- Wish Lists Button ----------------
       // Wish Lists-knap
        Button wishListsButton = new Button("Wish Lists");
        wishListsButton.setStyle("-fx-background-color: #DDE5B6; -fx-text-fill: BLACK; "
            + "-fx-border-color: #31672aff; -fx-font-size: 16px; -fx-font-family: 'Elms sans'; -fx-padding: 10px 20px; "
            + "-fx-border-radius: 5px; -fx-background-radius: 5px;"
            + "-fx-cursor: hand;");
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

    
             
    //----------------- layout and scene --------------------
        // We instantiate a new Scene of size 300x250, with white background and and associated scene graph rooted in 'layout'
        layout.setStyle("-fx-background-color: #F0EAD2;-fx-font-family: 'Elms sans';");// Light beige background
        Scene scene = new Scene(layout, 1197/3, 2256/3);//1197/3 width and 2256/3 height of an iPhone 16

        // We set the title of the Stage (the window) and set the Scene to be displayed, then we show the Stage
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();

    }
    
    //---------------------------- Main Method ----------------------------
    // NOTE: The main method is only needed for the IDE with limited JavaFX support. Not needed for running from the command line.
    public static void main(String[] args) { // the main method is the entry point of the application
        /* Launch the Application class in which is in */
        launch(args);

        // The above is equivalent to calling application.launch(SimpleStage.class, args);
    }
}
