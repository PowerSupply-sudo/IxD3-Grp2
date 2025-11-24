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

public class addWishFE {// Klasse til at oprette "Add Wish"-siden i frontend

    // Denne metode bygger hele "Add Wish"-siden og viser den i samme Stage
    public static void showAddWishScene(Stage stage) {// Metode til at vise "Add Wish"-scenen i den givne stage

        // ---------------- Bottombar ----------------
        HBox bottombar = new HBox();// Opretter en horisontal boks til bottombaren
        bottombar.setSpacing(40);// Sætter mellemrum mellem elementerne i bottombaren
        bottombar.setStyle("-fx-background-color: #b1d06aff; -fx-padding: 10px; -fx-min-height: 60px;");// Sætter baggrundsfarve, polstring og minimumshøjde for bottombaren
        bottombar.setAlignment(Pos.CENTER);// Centrerer elementerne i bottombaren'

        //Knapper i bottombar
        Button searchButton = new Button("Search");// Opretter en søgeknap
        Button homeButton = new Button("Home");// Opretter en hjemme-knap
        Button profileButton = new Button("Profile");// Opretter en profil-knap

        // Ensartet styling til knapperne
        String buttonStyle = "-fx-border-color: #31672aff; -fx-text-fill: #31672aff;" // Color, border, text size, and padding.
                           + " -fx-font-size: 16px; -fx-padding: 10px 20px;"
                           + " -fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);// Anvender den definerede stil til søgeknappen
        homeButton.setStyle(buttonStyle);// Anvender den definerede stil til hjemme-knappen
        profileButton.setStyle(buttonStyle);// Anvender den definerede stil til profil-knappen

        // Tilføj knapperne til bottombar
        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);// Tilføjer knapperne til bottombaren

        //---------------- Center-boks med knapper ----------------
        // Input field to enter wish title
        TextField wishTitle = new TextField();// Opretter et tekstfelt til at indtaste ønsketitel
        wishTitle.setPromptText("Enter Wish title");// Sætter en prompttekst i tekstfeltet for at guide brugeren

        // Buttons for adding to wishlist and creating new wishlist
        Button addToWishlist = new Button("Add to wishlist");// Opretter en knap til at tilføje ønsket til en ønskeliste
        Button newWishlist = new Button("New wishlist");// Opretter en knap til at oprette en ny ønskeliste

        HBox wishListButtons = new HBox(10);// Opretter en horisontal boks til ønskelisteknapperne med 10px mellemrum
        wishListButtons.setAlignment(Pos.CENTER);// Centrerer ønskelisteknapperne i
        wishListButtons.getChildren().addAll(addToWishlist, newWishlist);// Tilføjer ønskelisteknapperne til boksen

        // Continue button to proceed (can be used for saving or next steps)
        Button continueButton = new Button("Continue");// Opretter en fortsætknap
        continueButton.setStyle("-fx-padding: 10px 40px;");// Tilføjer polstring til fortsætknappen for bedre udseende

        // Saml alt i én VBox-boks med kant og baggrund
        VBox wishBox = new VBox(15);// Opretter en vertikal boks til at holde alle elementerne med 15px mellemrum
        wishBox.setAlignment(Pos.CENTER);// Centrerer elementerne i boksen
        wishBox.setStyle(
            "-fx-padding: 25px;" +
            "-fx-border-color: #31672aff;" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 12px;" +
            "-fx-background-color: #f9fff0;" +
            "-fx-background-radius: 12px;"
        );// Sætter stil for boksen med polstring, kantfarve, kantbredde, kantens runding, baggrundsfarve og baggrundens runding

        wishBox.setMaxWidth(310);// Sætter maksimal bredde for boksen for at forhindre at den bliver for bred

        // Tilføj alle elementer til boksen
        wishBox.getChildren().addAll(wishTitle, wishListButtons, continueButton);// Tilføjer tekstfeltet, ønskelisteknapperne og fortsætknappen til boksen

        // ---------------- Layout ----------------
        // layout with center content and bottombar at the bottom
        BorderPane layout = new BorderPane();// Opretter et BorderPane-layout for at arrangere elementerne
        layout.setBottom(bottombar);// Sætter bottombaren i bunden af layoutet

        // Opretter en wrapper VBox til at centrere boksen
        VBox centerWrapper = new VBox();
        centerWrapper.setAlignment(Pos.CENTER);
        centerWrapper.getChildren().add(wishBox);

        // Sæt wrapperen som center i layoutet
        layout.setCenter(centerWrapper);


        // new Scene with layout
        Scene scene = new Scene(layout, 1197/3, 2256/3, Color.WHITE);// Opretter en ny scene med den givne bredde og højde, og sætter baggrundsfarven til hvid på iPhone-størrelse
        stage.setScene(scene); // Sætter scenen på den eksisterende stage(stage er selve vinduet der åbner når man kører programmet)
        stage.show();// Viser scenen på scenen(stage)

        // Navigation tilbage til homePageFE
        // 'Home'-button sends the user back to the home page
        homeButton.setOnAction(e -> new homePageFE().start(stage));// Når homeButton klikkes, kaldes start-metoden i homePageFE-klassen med den nuværende stage som argument
    }
}
