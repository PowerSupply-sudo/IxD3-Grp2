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

        // ---------------- Popup-boks (centreret indhold) ----------------
        VBox popupBox = new VBox(15);//A VBox stacks elements vertically (top to bottom)
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
        HBox closeWrapper = new HBox();//HBox arranges them horizontally (left to right).
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
        layout.setBottom(BottomBarFactory.createBottomBar(stage));
        layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';");// Light beige background
        
        Scene scene = new Scene(layout, 1197/3, 2256/3);//1197/3 width and 2256/3 height of an iPhone 16
        stage.setScene(scene);
        stage.setTitle("Create Wishlist");
        stage.show();

        // Navigation
        closeButton.setOnAction(e -> addWishFE.showAddWishScene(stage)); // tilbage til Add Wish
    }
}
