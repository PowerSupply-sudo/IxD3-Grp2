package com.ixd3grp2.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class addWishFE {

    // Denne metode bygger hele "Add Wish"-siden og viser den i samme Stage
    public static void showAddWishScene(Stage stage) {

        // Bottombar
        HBox bottombar = new HBox();
        bottombar.setSpacing(40);
        bottombar.setStyle("-fx-background-color: #b1d06aff; -fx-padding: 10px; -fx-min-height: 60px;");
        bottombar.setAlignment(Pos.CENTER);

        //Knapper i bottombar
        Button searchButton = new Button("Search");
        Button homeButton = new Button("Home");
        Button profileButton = new Button("Profile");

        // Ensartet styling til knapperne
        String buttonStyle = "-fx-border-color: #31672aff; -fx-text-fill: #31672aff;" // Color, border, text size, and padding.
                           + " -fx-font-size: 16px; -fx-padding: 10px 20px;"
                           + " -fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);

        // Tilføj knapperne til bottombar
        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);

        // Center content
        VBox content = new VBox(15);
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-padding: 15px;");

        // Input field to enter wish title
        TextField wishTitle = new TextField();
        wishTitle.setPromptText("Enter Wish title");

        // Buttons for adding to wishlist and creating new wishlist
        Button addToWishlist = new Button("Add to wishlist");
        Button newWishlist = new Button("New wishlist");

        // Box to hold the add and new wishlist buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(addToWishlist, newWishlist);

        // Continue button to proceed (can be used for saving or next steps)
        Button continueButton = new Button("Continue");
        continueButton.setStyle("-fx-padding: 10px 40px;");

        // Add all elements to the content VBox(center content)
        content.getChildren().addAll(wishTitle, buttonBox, continueButton);

        // Layout
        // layout with center content and bottombar at the bottom
        BorderPane layout = new BorderPane();
        layout.setCenter(content);
        layout.setBottom(bottombar);

        // new Scene with layout
        Scene scene = new Scene(layout, 1197/3, 2256/3, Color.WHITE);
        stage.setScene(scene);
        stage.show();

        // Navigation tilbage til homePageFE
        // 'Home'-button sends the user back to the home page
        homeButton.setOnAction(e -> new homePageFE().start(stage));
    }
}
