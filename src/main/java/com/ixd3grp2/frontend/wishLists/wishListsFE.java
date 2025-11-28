package com.ixd3grp2.frontend.wishLists;

import com.ixd3grp2.frontend.homePageFE;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class wishListsFE extends Application {

    @Override
    public void start(Stage stage) {
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


        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);

        // ---------------- Grid med ønskelister ----------------
        GridPane grid = new GridPane();// Layout for wish lists
        grid.setPadding(new Insets(20));// Margin omkring grid
        grid.setHgap(20);// Afstand mellem kolonner
        grid.setVgap(20);// Afstand mellem rækker
        grid.setAlignment(Pos.CENTER);// Centrerer grid i vinduet

        int columns = 3;// Antal kolonner i grid
        int totalWishLists = 12;// Samlet antal ønskelister

        for (int i = 0; i < totalWishLists; i++) {// Opretter knapper for hver ønskeliste
            Button wishListsButton = new Button("Wish List " + (i + 1));// Knap for ønskeliste
            wishListsButton.setPrefSize(100, 80);// Standard størrelse for knap
            wishListsButton.setStyle(// CSS styling for knap
                "-fx-background-color: #dde5b6;" +
                "-fx-border-color: #849a47;" +
                "-fx-border-width: 2px;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-radius: 10px;"
            );

            // Håndterer klik på ønskeliste knap
            int wishListsIndex = i;// Gemmer indeks for brug i lambda udtryk
            wishListsButton.setOnAction(e -> wishListDetailFE.showWishListDetailScene(stage, wishListsIndex));// Åbner detaljeret visning af ønskeliste

            grid.add(wishListsButton, i % columns, i / columns);// Tilføjer knap til grid på korrekt position
        }

        // ---------------- Layout og Scene ----------------
        BorderPane layout = new BorderPane();// Hovedlayout for scenen
        layout.setCenter(grid);// Sætter grid i midten af layout
        layout.setBottom(bottombar);// Sætter bottombar i bunden af layout

        // ---------------- Scene ----------------

        layout.setStyle(
            "-fx-background-color: #F0EAD2;" +// Light beige background
            "-fx-font-family: 'Elms sans';" +
            "-fx-font-size: 16px;"//general font og størrelse
        );
        Scene scene = new Scene(layout, 1197/3, 2256/3);//1197/3 width and 2256/3 height of an iPhone 16
        stage.setTitle("Wish Lists");// Sætter vinduets titel
        stage.setScene(scene);// Sætter scenen på scenen
        stage.show();

        // Navigation
        homeButton.setOnAction(e -> new homePageFE().start(stage));// Går til forsiden ved klik på home knap
    }

    // Valgfrit: gør det nemt at kalde denne side
    //public static void showWishListsScene(Stage stage) {// Statisk metode til at vise ønskeliste scenen
       // new wishListsFE().start(stage);// Starter ønskeliste scenen
    //}

    public static void showWishListsScene(Stage stage) {
    new wishListsFE().start(stage);
    }


    // Main metode til at starte applikationen, altså kører den denne klasse direkte
    public static void main(String[] args) {
        launch(args);
    }

}

