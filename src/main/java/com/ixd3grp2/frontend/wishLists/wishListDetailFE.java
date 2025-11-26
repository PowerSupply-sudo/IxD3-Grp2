/**package com.ixd3grp2.frontend;// Korrekt sti til pakken

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;*/

/**public class wishListDetailFE {// Klasse til detaljeret visning af ønskeliste
    public static void showWishListDetailScene(Stage stage, int listIndex) {// Statisk metode til at vise detaljeret ønskeliste scene
        
        // ---------------- Bottombar ----------------
        HBox bottombar = new HBox(40);
        bottombar.setStyle("-fx-background-color: #b1d06aff; -fx-padding: 10px; -fx-min-height: 60px;");
        bottombar.setAlignment(Pos.CENTER);

        Button searchButton = new Button("Search");
        Button homeButton = new Button("Home");
        Button profileButton = new Button("Profile");

        String buttonStyle = "-fx-border-color: #31672aff; -fx-text-fill: #31672aff; "
                        + "-fx-font-size: 16px; -fx-padding: 10px 20px; "
                        + "-fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);

        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);

        // Navigation
        homeButton.setOnAction(e -> homePageFE.showHomePageScene(stage));
        searchButton.setOnAction(e -> {});
        profileButton.setOnAction(e -> {//});

        
        
        VBox layout = new VBox();// Vertikalt layout for scenen
        layout.setAlignment(Pos.CENTER);// Centrerer indholdet
        layout.setSpacing(20);// Afstand mellem elementer i layout

        Label title = new Label("Wish List #" + (listIndex + 1));// Titel for ønskelisten
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");// CSS styling for titel

        Button backButton = new Button("← Back");// Knap til at gå tilbage til ønskeliste oversigten
        backButton.setStyle("-fx-background-color: #31672aff; -fx-text-fill: white; -fx-padding: 8px 20px;");// CSS styling for knap
        backButton.setOnAction(e -> wishListsFE.showWishListsScene(stage));// Håndterer klik på tilbage knap

        layout.getChildren().addAll(title, backButton);// Tilføjer titel og knap til layout

        Scene scene = new Scene(layout, 1197/3, 2256/3, Color.WHITE);// Opretter scene med hvid baggrund
        stage.setScene(scene);// Sætter scenen på scenen
        stage.show();// Viser scenen
    }
}*/


package com.ixd3grp2.frontend.wishLists;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class wishListDetailFE {
    public static void showWishListDetailScene(Stage stage, int listIndex) {
        // ---------------- Center-indhold ----------------
        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setSpacing(20);

        Label title = new Label("Wish List #" + (listIndex + 1));
        Button backButton = new Button("← Back");
        backButton.setOnAction(e -> wishListsFE.showWishListsScene(stage));

        centerContent.getChildren().addAll(title, backButton);

        // ---------------- Bottombar ----------------
        HBox bottombar = new HBox();
        bottombar.setSpacing(40); // Space between buttons
        bottombar.setStyle("-fx-background-color: #b1d06aff; -fx-padding: 10px; -fx-min-height: 60px;");
        bottombar.setAlignment(Pos.CENTER); // Center buttons horizontally and vertically

        // Create buttons for the bottom bar
        Button searchButton = new Button("Search");
        Button homeButton = new Button("Home");
        Button profileButton = new Button("Profile");

        // Styling for buttons
        String buttonStyle = "-fx-border-color: #31672aff; -fx-text-fill: #31672aff; "
                           + "-fx-font-size: 16px; -fx-padding: 10px 20px; "
                           + "-fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);

        // Add buttons to the bottombar
        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);

        // ---------------- Layout ----------------
        BorderPane layout = new BorderPane();
        layout.setCenter(centerContent); // Main content in the center
        layout.setBottom(bottombar);     // Bottom bar at the bottom

        // ---------------- Scene ----------------
        Scene scene = new Scene(layout, 1197/3, 2256/3, Color.WHITE);
        stage.setTitle("Wish List Detail");
        stage.setScene(scene);
        stage.show();

        // ---------------- Navigation ----------------
        //homeButton.setOnAction(e -> new homePageFE().start(stage));
        // searchButton.setOnAction(e -> new searchFE().start(stage)); // hvis du laver en search-side
        // profileButton.setOnAction(e -> new profileFE().start(stage)); // hvis du laver en profile-side
    }
}



