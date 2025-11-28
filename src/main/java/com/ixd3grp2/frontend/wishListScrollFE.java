package com.ixd3grp2.frontend;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Stage;

// Lav vidre på scrollable wish list her, ved at tilføje lukkeknapper, så man kan slette ønskelister osv.
// Opdatering af wish list scroll scene: Indtil videre er der tilføjet en luk-knap (X) i øverste højre hjørne af boksen, 
// som navigerer tilbage til addWishFE scenen, når den klikkes.
// Nu er der tilføjet flere funktioner f.eks som at man kan slet-knapper for hver ønskeliste,
// men når man lukker scenen ned, så gemmes ændringerne ikke.

// NOTE: Det kan være at extendere Application ikke er nødvendigt her, 
// afhængigt af hvordan denne klasse bruges i resten af applikationen. 
// Gælder også ift. @Override, så det kan fjernes hvis ikke nødvendigt.
// Det samme gælder også for public void start(Stage stage) metoden,
// det kan være at der skal bruges public static void showWishListScrollScene(Stage stage) i stedet.

public class wishListScrollFE extends Application {// Klasse til at oprette "Wish List Scroll"-siden i frontend
    @Override
     public void start(Stage stage) {// Metode til at vise "Wish List Scroll"-scenen i den givne stage
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
        String buttonStyle = "-fx-background-color: #DDE5B6; -fx-border-color: #31672aff; -fx-text-fill: #BLACK; "
                        + "-fx-font-size: 16px;-fx-font-family: 'Elms sans';" 
                        + "-fx-padding: 10px 20px; "
                        + "-fx-background-radius: 5px; -fx-border-radius: 5px;";
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

        VBox wishlistContainer = new VBox(15);// Container for wishlist items with spacing
        wishlistContainer.setAlignment(Pos.TOP_CENTER);// Align items to the top center
        wishlistContainer.setStyle("-fx-padding: 20px;");// Padding around the wishlist items

        for (int i = 0; i < 10; i++) {// Example: Adding 10 wishlist items
            HBox item = new HBox(10);// Each wishlist item container
            item.setAlignment(Pos.CENTER_LEFT);// Align items to the left
            item.setStyle(// Styling for each wishlist item
                "-fx-background-color: #849a47;" +
                "-fx-padding: 10px;" +
                "-fx-border-color: #6c584c;" +
                "-fx-border-width: 2px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
            );
            item.setMaxWidth(280);// Set a max width for each wishlist item

            javafx.scene.control.Label name = new javafx.scene.control.Label("Wishlist name goes here...");// Wishlist name label
            name.setStyle("-fx-font-size: 12px; -fx-text-fill: black;");// Wishlist name label style

            javafx.scene.control.Label count = new javafx.scene.control.Label("4 🎁"); // Gift emoji with count
            count.setStyle("-fx-font-size: 14px; -fx-text-fill: #31672a;");// Gift emoji with count

            Button delete = new Button("X");// Delete button
            delete.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");// Delete button style

            delete.setOnAction(e -> wishlistContainer.getChildren().remove(item));// Delete functionality

            Region spacer = new Region();// Spacer to push count and delete button to the right
            HBox.setHgrow(spacer, Priority.ALWAYS);// Make spacer grow to take available space

            item.getChildren().addAll(name, spacer, count, delete);// Add elements to the item HBox
            wishlistContainer.getChildren().add(item);// Add item to the wishlist container

        }

        ScrollPane scrollPane = new ScrollPane(wishlistContainer);// ScrollPane to make the wishlist scrollable
        scrollPane.setFitToWidth(true);// Make the content fit the width of the ScrollPane
        scrollPane.setStyle("-fx-background: #849a47;");// Transparent background for ScrollPane

        // Close-knappen(X) i øverste højre hjørne af boksen
        Button closeButton = new Button("X");// Opretter en luk-knap
        closeButton.setStyle("-fx-font-weight: bold; -fx-text-fill: BLACK;");// Gør luk-knappen mere synlig med fed skrift og sort farve
        closeButton.setOnAction(e -> addWishFE.showAddWishScene(stage));// Når luk-knappen klikkes, navigeres tilbage til addWishFE
        
        HBox closeWrapper = new HBox();// Opretter en horisontal boks til at placere luk-knappen
        closeWrapper.setAlignment(Pos.TOP_RIGHT);// Justerer luk-knappen til højre i bok
        closeWrapper.setMaxWidth(Double.MAX_VALUE);// Gør boksen så bred som muligt for at sikre at knappen er i højre hjørne
        closeWrapper.getChildren().add(closeButton);// Tilføjer luk-knappen til boksen

    // ---------------- Bekræft-knap ----------------
        // (Bekræft-knappen skal implementeres her)

        Button confirmButton = new Button("Confirm");// Opretter en bekræft-knap
        confirmButton.setStyle(// Stil for bekræft-knappen
            "-fx-background-color: #6b4c2f;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 10px 30px;" +
            "-fx-background-radius: 8px;"
        );// Sætter stil for bekræft-knappen

    // ---------------- Layout ----------------
        //(layout er allerede oprettet som en BorderPane ovenfor)

        VBox centerWrapper = new VBox(20);// Wrapper for centering content with spacing
        centerWrapper.setAlignment(Pos.CENTER);// Center content horizontally and vertically
        centerWrapper.setStyle("-fx-padding: 20px;");// Padding around the content
        centerWrapper.getChildren().addAll(closeWrapper, scrollPane, confirmButton);// Add close button, scrollable wishlist and confirm button to the wrapper

        layout.setCenter(centerWrapper);// Set the center of the layout to the wrapper

        // ---------------- Scene ----------------

        layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';");// Light beige background
        Scene scene = new Scene(layout, 1197/3, 2256/3);//1197/3 width and 2256/3 height of an iPhone 16


        // We set the scene on the stage and display it
        stage.setTitle("Wish List Scroll");// Set the title of the stage
        stage.setScene(scene);// Set the scene on the stage
        stage.show();// Show the stage

         // Navigation (eksempel)
        homeButton.setOnAction(e -> new homePageFE().start(stage));
    }
    // Valgfrit: gør det nemt at skifte til denne scene fra andre klasser
    public static void showWishListScrollScene(Stage stage) {// Statisk metode til at vise "Wish List Scroll"-scenen i den givne stage
        new wishListScrollFE().start(stage);// Opretter en ny instans af wishListScrollFE og kalder start-metoden med den givne stage
    }    

}
