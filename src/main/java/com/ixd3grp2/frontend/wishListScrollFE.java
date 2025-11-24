package com.ixd3grp2.frontend;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// lav vidre på scrollable wish list her, ved at tilføje lukkeknapper, så man kan slette ønskelister osv.

// NOTE: Det kan være at extendere Application ikke er nødvendigt her, 
// afhængigt af hvordan denne klasse bruges i resten af applikationen. 
// Gælder også ift. @Override, så det kan fjernes hvis ikke nødvendigt.
// Det samme gælder også for public void start(Stage stage) metoden,
// det kan være at der skal bruges public static void showWishListScrollScene(Stage stage) i stedet.

public class wishListScrollFE extends Application {
    @Override
     public void start(Stage stage) {
    // ---------------- Bottombar ----------------

        //Create the bottombar
        HBox bottombar = new HBox();
        bottombar.setSpacing(40);// Space between buttons
        bottombar.setStyle("-fx-background-color: #b1d06aff; -fx-padding: 10px; -fx-min-height: 60px;"); // Ligth greenbackground med fixed height
        bottombar.setAlignment(Pos.CENTER);// Center buttons horizontally and vertically

        // Create buttons for the bottom bar
        Button searchButton = new Button("Search");
        Button homeButton = new Button("Home");
        Button profileButton = new Button("Profile");

        // Add buttons to the bottom bar, and the bottons placed in the correct order, and the size of the buttons
        String buttonStyle = "-fx-border-color: #31672aff; -fx-text-fill: #31672aff;"
                           + " -fx-font-size: 16px; -fx-padding: 10px 20px;"
                           + " -fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle); 
        profileButton.setStyle(buttonStyle); 

        // Add buttons to the bottombar
        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);
    
    // ---------------- Layout ----------------   

        // Use a BorderPane to position the bottombar at the bottom
        BorderPane layout = new BorderPane();
        //layout.setCenter(centerContent);//// Main content in the center
        layout.setBottom(bottombar); // Add the bottom bar to the bottom of the layout
       
    // ---------------- Scrollbart ønskeliste-indhold ----------------
        // (Indholdet til den scrollbare ønskeliste skal implementeres her)

        VBox wishlistContainer = new VBox(15);
        wishlistContainer.setAlignment(Pos.TOP_CENTER);
        wishlistContainer.setStyle("-fx-padding: 20px;");

        for (int i = 0; i < 10; i++) {
            HBox item = new HBox(10);
            item.setAlignment(Pos.CENTER_LEFT);
            item.setStyle(
                "-fx-background-color: #ffffff;" +
                "-fx-padding: 10px;" +
                "-fx-border-color: #6b4c2f;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
            );
            item.setMaxWidth(280);

            javafx.scene.control.Label name = new javafx.scene.control.Label("Wishlist name goes here...");
            name.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");

            javafx.scene.control.Label count = new javafx.scene.control.Label("4 🎁");
            count.setStyle("-fx-font-size: 14px; -fx-text-fill: #31672a;");

            Button delete = new Button("X");
            delete.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);

            item.getChildren().addAll(name, spacer, count, delete);
            wishlistContainer.getChildren().add(item);
        }

        ScrollPane scrollPane = new ScrollPane(wishlistContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent;");
            
    // ---------------- Bekræft-knap ----------------
        // (Bekræft-knappen skal implementeres her)

        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle(
            "-fx-background-color: #6b4c2f;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10px 30px;" +
            "-fx-background-radius: 8px;"
        );

    // ---------------- Layout ----------------
        //(layout er allerede oprettet som en BorderPane ovenfor)

        VBox centerWrapper = new VBox(20);
        centerWrapper.setAlignment(Pos.CENTER);
        centerWrapper.setStyle("-fx-padding: 20px;");
        centerWrapper.getChildren().addAll(scrollPane, confirmButton);

        layout.setCenter(centerWrapper);

        // ---------------- Scene ----------------

        // We instantiate a new Scene of size 300x250, with white background and and associated scene graph rooted in 'layout'
        Scene scene = new Scene(layout, 1197/3, 2256/3, Color.WHITE);

        // We set the scene on the stage and display it
        stage.setTitle("Stage Title");
        stage.setScene(scene);
        stage.show();

         // Navigation (eksempel)
        homeButton.setOnAction(e -> new homePageFE().start(stage));
    }
    // Valgfrit: gør det nemt at skifte til denne scene fra andre klasser
    public static void showWishListScrollScene(Stage stage) {
        new wishListScrollFE().start(stage);
    }    

}
