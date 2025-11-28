package com.ixd3grp2.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class createWishListFE {

    public static void showCreateWishListScene(Stage stage) {

        // ---------------- Bottombar ----------------
        //Create the bottombar
        HBox bottombar = new HBox();
        bottombar.setSpacing(40);// Space between buttons
        bottombar.setStyle("-fx-background-color: #6c584c; -fx-padding: 10px; -fx-min-height: 60px;"); // Ligth greenbackground med fixed height
        //bottombar.setLayoutY(2256/3 - 50); // Position at the bottom of the scene
        bottombar.setAlignment(Pos.CENTER);// Center buttons horizontally and vertically


        // Create buttons for the bottom bar
        Button searchButton = new Button("Search");
        Button homeButton = new Button("Home");
        Button profileButton = new Button("Profile");

        // Add buttons to the bottom bar, and the bottons placed in the correct order, and the size of the buttons
        String buttonStyle = "-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: #BLACK; "
                        + "-fx-font-size: 16px;-fx-font-family: 'Elms sans';" 
                        + "-fx-padding: 10px 20px; "
                        + "-fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);

        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);

        // ---------------- Popup-boks (centreret indhold) ----------------
        VBox popupBox = new VBox(15);
        popupBox.setAlignment(Pos.CENTER);
        popupBox.setStyle(
            "-fx-padding: 20px;" +
            "-fx-background-color: #dde5b6;" +     // grøn baggrund
            "-fx-border-color: #6c584c;" +        // brun kant
            "-fx-border-width: 3px;" +
            "-fx-border-radius: 10px;" +
            "-fx-background-radius: 10px;"
        );
        popupBox.setMaxWidth(300);

        StackPane inputStack = new StackPane(); // StackPane til at overlappe tekstfelt og knap

        TextField wishlistName = new TextField(); // Tekstfelt til ønskelistenavn
        wishlistName.setPromptText("Enter wishlist name here..."); // Prompt-tekst for at guide brugeren
        wishlistName.setMaxWidth(200); // begræns bredden for bedre centrering

        Button closeButton = new Button("X"); // Luk-knap
        closeButton.setStyle("-fx-font-weight: bold; -fx-text-fill: BLACK;"); // Stil for at gøre den mere synlig

        // HBox til at placere knappen i højre hjørne
        HBox closeWrapper = new HBox();
        closeWrapper.setAlignment(Pos.CENTER_RIGHT);
        closeWrapper.getChildren().add(closeButton);

        inputStack.getChildren().addAll(wishlistName, closeWrapper);

        Button createButton = new Button("Create wishlist");
        createButton.setStyle(
            "-fx-background-color: #a98467;" +
            "-fx-text-fill: black;" +
            "-fx-font-family: 'Elms sans';" +
            "-fx-border-color: #6c584c;" +
            "-fx-font-size: 14px;" +
            "-fx-padding: 8px 20px;" +
            "-fx-background-radius: 5px;"
        );

       // Skift til ønskeliste-scroll-siden når knappen trykkes
        createButton.setOnAction(e -> wishListScrollFE.showWishListScrollScene(stage));


        popupBox.getChildren().addAll(inputStack, createButton);

        // ---------------- Wrapper til centreret placering ----------------
        VBox centerWrapper = new VBox();
        centerWrapper.setAlignment(Pos.CENTER);
        centerWrapper.getChildren().add(popupBox);

        // ---------------- Layout ----------------
        BorderPane layout = new BorderPane();
        layout.setCenter(centerWrapper);
        layout.setBottom(bottombar);

        layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';");// Light beige background
        Scene scene = new Scene(layout, 1197/3, 2256/3);//1197/3 width and 2256/3 height of an iPhone 16
        stage.setScene(scene);
        stage.setTitle("Create Wishlist");
        stage.show();

        // Navigation
        homeButton.setOnAction(e -> new homePageFE().start(stage));
        closeButton.setOnAction(e -> addWishFE.showAddWishScene(stage)); // tilbage til Add Wish
    }
}
