package com.ixd3grp2.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



// NOTE: Det kan være at extendere Application ikke er nødvendigt her, 
// afhængigt af hvordan denne klasse bruges i resten af applikationen. 
// Gælder også ift. @Override, så det kan fjernes hvis ikke nødvendigt.
// Det samme gælder også for public void start(Stage stage) metoden,
// det kan være at der skal bruges public static void showAddWishScene(Stage stage) i stedet.


public class addWishFE {// Klasse til at oprette "Add Wish"-siden i frontend

    // Denne metode bygger hele "Add Wish"-siden og viser den i samme Stage
    public static void showAddWishScene(Stage stage) {// Metode til at vise "Add Wish"-scenen i den givne stage

        //---------------- Center-boks med knapper ----------------
        // Input field to enter wish title
        TextField wishTitle = new TextField();// Opretter et tekstfelt til at indtaste ønsketitel.
        wishTitle.setPromptText("Enter Wish title");// Sætter en prompttekst i tekstfeltet for at guide brugeren

        // Buttons for adding to wishlist and creating new wishlist
        Button addToWishlist = new Button("Add to wishlist");// Opretter en knap til at tilføje ønsket til en ønskeliste
        addToWishlist.setStyle(
            "-fx-background-color: #a98467;" +
            "-fx-text-fill: black;" +
            "-fx-font-family: 'Elms sans';" +
            "-fx-border-color: #6c584c;" +
            "-fx-font-size: 12px;" +
            "-fx-padding: 8px 20px;" +
            "-fx-background-radius: 5px;"
        );// Sætter stil for knappen med baggrundsfarve, tekstfarve, skrifttype, kantfarve, skriftstørrelse, polstring og runding af kanter

        Button newWishlist = new Button("New wishlist");// Opretter en knap til at oprette en ny ønskeliste
        newWishlist.setStyle(
            "-fx-background-color: #a98467;" +
            "-fx-text-fill: black;" +
            "-fx-font-family: 'Elms sans';" +
            "-fx-border-color: #6c584c;" +
            "-fx-font-size: 12px;" +
            "-fx-padding: 8px 20px;" +
            "-fx-background-radius: 5px;"
        );// Sætter stil for knappen med baggrundsfarve, tekstfarve, skrifttype, kantfarve, skriftstørrelse, polstring og runding af kanter

        // Navigationshandlinger for knapperne
        addToWishlist.setOnAction(e -> wishListScrollFE.showWishListScrollScene(stage));

        // Når man klikker på "New wishlist", åbnes popup-boksen i et nyt vindue
        newWishlist.setOnAction(e -> createWishListFE.showCreateWishListScene(stage));


        HBox wishListButtons = new HBox(10);// Opretter en horisontal boks til ønskelisteknapperne med 10px mellemrum
        wishListButtons.setAlignment(Pos.CENTER);// Centrerer ønskelisteknapperne 
        wishListButtons.getChildren().addAll(addToWishlist, newWishlist);// Tilføjer ønskelisteknapperne til boksen
        wishListButtons.setStyle(
            "-fx-font-family: 'Elms sans';" +
            "-fx-font-size: 14px;"+
            "-fx-text-fill: black;"
        );// Sætter stil for ønskelisteknapperne med skrifttype og skriftstørrelse

        
    // ---------------- Ønskeboks ----------------
        // Saml alt i én VBox-boks med kant og baggrund
        VBox wishBox = new VBox(15);// Opretter en vertikal boks til at holde alle elementerne med 15px mellemrum
        wishBox.setAlignment(Pos.CENTER);// Centrerer elementerne i boksen
        wishBox.setStyle(
            "-fx-padding: 25px;" +
            "-fx-border-color: black;" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 12px;" +
            "-fx-background-color: #849a46;" +
            "-fx-background-radius: 12px;"
        );// Sætter stil for boksen med polstring, kantfarve, kantbredde, kantens runding, baggrundsfarve og baggrundens runding

        wishBox.setMaxWidth(310);// Sætter maksimal bredde for boksen for at forhindre at den bliver for bred

        // Exit-knappen(X) i øverste højre hjørne af boksen
        Button closeButton = new Button("X");// Opretter en luk-knap
        closeButton.setStyle("-fx-font-weight: bold; -fx-text-fill: BLACK;");// Gør luk-knappen mere synlig med fed skrift og sort farve
        closeButton.setOnAction(e -> new homePageFE().start(stage));// Når luk-knappen klikkes, navigeres tilbage til homePageFE

        HBox closeWrapper = new HBox();// Opretter en horisontal boks til at placere luk-knappen
        closeWrapper.setAlignment(Pos.TOP_RIGHT);// Justerer luk-knappen til højre i bok
        closeWrapper.setMaxWidth(Double.MAX_VALUE);// Gør boksen så bred som muligt for at sikre at knappen er i højre hjørne
        closeWrapper.getChildren().add(closeButton);// Tilføjer luk-knappen til boksen

        // Tilføj alle elementer til boksen - med X-knap øverst til højre,  
        // som den første ting i boksen, da den er i en separat HBox,   
        // og dermed placeres øverst.
        wishBox.getChildren().addAll(closeWrapper, wishTitle, wishListButtons);// Tilføjer luk-knappen, tekstfeltet, ønskelisteknapperne og fortsætknappen til boksen

        // ---------------- Layout ----------------
        // layout with center content and bottombar at the bottom
        BorderPane layout = new BorderPane();
        //layout.setTop(topBar);// Sets the top bar, kun hvis det er noget vil vil have deroppe i topbar
        //layout.setCenter(centerContent);// Sets the center content, kun hvis det er noget vil vil have deroppe i topbar
        layout.setBottom(BottomBarFactory.createBottomBar(stage));


        // Opretter en wrapper VBox til at centrere boksen
        VBox centerWrapper = new VBox();
        centerWrapper.setAlignment(Pos.CENTER);
        centerWrapper.getChildren().add(wishBox);

        // Sæt wrapperen som center i layoutet
        layout.setCenter(centerWrapper);

        layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';");// Light beige background
        Scene scene = new Scene(layout, 1197/3, 2256/3);//1197/3 width and 2256/3 height of an iPhone 16
        stage.setScene(scene); // Sætter scenen på den eksisterende stage(stage er selve vinduet der åbner når man kører programmet)
        stage.setTitle("Add Wish");// Sætter titlen på vinduet(stage)
        stage.show();// Viser scenen på scenen(stage)

    }
}
