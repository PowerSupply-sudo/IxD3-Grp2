package com.ixd3grp2.frontend.wishLists;

import com.ixd3grp2.frontend.BottomBarFactory;
import com.ixd3grp2.frontend.homePageFE;
import com.ixd3grp2.frontend.createWishListFE;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;

public class wishListsFE extends Application {

    @Override
    public void start(Stage stage) {

        // ---------------- Grid med ønskelister ----------------
        GridPane grid = new GridPane();// Layout for wish lists
        grid.setPadding(new Insets(20));// Margin omkring grid
        grid.setHgap(20);// Afstand mellem kolonner
        grid.setVgap(20);// Afstand mellem rækker
        grid.setAlignment(Pos.CENTER);// Centrerer grid i vinduet

        int columns = 3;// Antal kolonner i grid
        int totalWishLists = 22;// Samlet antal ønskelister

        for (int i = 0; i < totalWishLists; i++) {// Opretter knapper for hver ønskeliste
            Button wishListsButton = new Button("Wish List " + (i + 1));// Knap for ønskeliste
            wishListsButton.setMinSize(100, 80);// Standard størrelse for knap
            wishListsButton.setMaxSize(100, 80);// Maksimal størrelse for knap
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
            wishListsButton.setOnAction(e -> listOfWishesFE.showListOfWishesScene(stage, wishListsIndex));// Åbner detaljeret visning af ønskeliste

            grid.add(wishListsButton, i % columns, i / columns);// Tilføjer knap til grid på korrekt position
        }
        // ---------------- ScrollPane for grid ----------------
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);   // gør at grid fylder hele bredden
        scrollPane.setFitToHeight(false);  // gør at grid fylder hele højden
        scrollPane.setPannable(true);     // tillader at man kan scrolle med musen
        scrollPane.setStyle("-fx-background: #f0ead2; -fx-background-color: #f0ead2;"); // gør baggrunden gennemsigtig

        // Kun lodret scrollbar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);   // ❌ ingen vandret scrollbar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // ✅ lodret scrollbar når nødvendigt

        scrollPane.setStyle("-fx-background: #F0EAD2; -fx-background-color: #F0EAD2;");


        // ---------------- Topbar med back, title og add ----------------
        Button backButton = new Button("\u2190"); // ←
        backButton.setOnAction(e -> new homePageFE().start(stage)); // tilbage til forsiden
        backButton.setFont(Font.font("Elms sans", 16));
        backButton.setStyle("-fx-text-fill: black; -fx-background-color: #DDE5B6; -fx-border-color: #849a47; "
                            + "-fx-padding: 6px 15px; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        Label title = new Label("My wish lists");
        title.setStyle("-fx-font-family: 'Elms sans'; -fx-font-size: 18px; -fx-text-fill: #31672aff;");

        //skal måske være en plus ikon i stedet for tekst
        //eller ikke have nogen overhovedet addbutton
        Button addButton = new Button("+"); // højre hjørne (tilføj ny ønskeliste)
        addButton.setOnAction(e -> createWishListFE.showCreateWishListScene(stage)); // gå til opret ønskeliste side
        addButton.setFont(Font.font("Elms sans", 16));
        addButton.setStyle("-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: black; "
                        + "-fx-font-size: 20px; -fx-font-family: 'Elms sans'; "
                        + "-fx-padding: 6px 12px; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(10);

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        // rækkefølge: back (venstre), spacer, title (midten), spacer, add (højre)
        topBar.getChildren().addAll(backButton, leftSpacer, title, rightSpacer, addButton);



        // ---------------- Layout og Scene ----------------
        BorderPane layout = new BorderPane();// Hovedlayout for scenen
       // layout.setCenter(grid);// Sætter grid i midten af layout
        layout.setCenter(scrollPane);
        layout.setBottom(BottomBarFactory.createBottomBar(stage));// Sætter bottombar i bunden af layout
        layout.setTop(topBar);// Sætter topbar i toppen af layout
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

        // navigation
        addButton.setOnAction(e -> createWishListFE.showCreateWishListScene(stage));


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

