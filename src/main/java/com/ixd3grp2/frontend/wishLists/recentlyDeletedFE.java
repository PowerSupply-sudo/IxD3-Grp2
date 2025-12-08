package com.ixd3grp2.frontend.wishLists; // Angiver hvilken pakke klassen tilhører

import com.ixd3grp2.frontend.BottomBarFactory; // Importerer fælles bottombar factory, så vi kan genbruge navigationen

// JavaFX imports til GUI-komponenter
import javafx.application.Application; // Gør klassen til en JavaFX applikation
import javafx.geometry.Insets;         // Bruges til margin/padding
import javafx.geometry.Pos;            // Bruges til alignment (CENTER, LEFT osv.)
import javafx.scene.Scene;             // Scene = container for hele UI'et
import javafx.scene.control.Button;    // Knap-komponent
import javafx.scene.control.Label;     // Tekst-label
import javafx.scene.control.ScrollPane;// Scrollbar-container
import javafx.scene.layout.*;          // Layouts som VBox, HBox, BorderPane, GridPane
import javafx.scene.text.Font;         // Font-styling
import javafx.stage.Stage;             // Stage = selve vinduet

public class recentlyDeletedFE extends Application { // Klassen extends Application, så den kan køres som JavaFX app

    @Override
    public void start(Stage stage) { // start-metoden viser scenen i det givne Stage (vindue)

        //---------------------------- Topbar ----------------------------
        Button backButton = new Button("\u2190"); // ← pil-knap
        backButton.setFont(Font.font("Elms sans", 16)); // Sætter font og størrelse
        backButton.setStyle( // Styling af tilbage-knappen
            "-fx-text-fill: black; -fx-background-color: #DDE5B6; -fx-border-color: #849a47; " +
            "-fx-padding: 6px 15px; -fx-background-radius: 5px; -fx-border-radius: 5px;"
        );
        backButton.setOnAction(e -> listOfWishesFE.showListOfWishesScene(stage, 0)); // Navigerer tilbage til ønskeliste-siden

        Label title = new Label("Recently Deleted"); // Titel i topbaren
        title.setStyle("-fx-font-family: 'Elms sans'; -fx-font-size: 18px; -fx-text-fill: #31672aff;");
        // 👉 Ryk titlen lidt til venstre (fx 20px)
        title.setTranslateX(-20);

        /**Button editButton = new Button("\u270E"); // ✎ blyant-knap
        editButton.setStyle( // Styling af edit-knappen
            "-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: black; " +
            "-fx-font-size: 20px; -fx-font-family: 'Elms sans'; " +
            "-fx-padding: 6px 12px; -fx-background-radius: 5px; -fx-border-radius: 5px;"
        );*/

        HBox topBar = new HBox(); // Horisontal container til topbaren
        topBar.setPadding(new Insets(10)); // Indre margin
        topBar.setAlignment(Pos.CENTER);   // Centrerer indholdet
        topBar.setSpacing(10);             // Afstand mellem elementer

        Region leftSpacer = new Region();  // Spacer til venstre
        Region rightSpacer = new Region(); // Spacer til højre
        HBox.setHgrow(leftSpacer, Priority.ALWAYS); // Spacer vokser til at fylde plads
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        // rækkefølge: back (venstre), spacer, title (midten), spacer, edit (højre)
        topBar.getChildren().addAll(backButton, leftSpacer, title, rightSpacer/**, editButton*/);

        //---------------------------- Items sektion ----------------------------
        VBox itemsSection = new VBox(10); // Vertikal container til items, spacing = 10px
        itemsSection.setAlignment(Pos.CENTER_LEFT); // Venstrejusteret indhold
        itemsSection.setPadding(new Insets(10));    // Indre margin på 10px

        Label itemsTitle = new Label("Items"); // Titel til items-sektionen
        itemsTitle.setFont(Font.font("Elms sans", 16)); // Font og størrelse
        itemsTitle.setStyle("-fx-text-fill: #31672aff;"); // Grøn tekstfarve

        // Loop til at tilføje 10 items
        for (int i = 0; i < 10; i++) {
            HBox itemBox = new HBox(10); // Horisontal container til hvert item
            itemBox.setAlignment(Pos.CENTER_LEFT); // Venstrejusteret
            itemBox.setStyle( // Styling af item-boksen
                "-fx-background-color: #849a47;" +
                "-fx-padding: 10px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
                //styling
            );
            itemBox.setMaxWidth(300); // Maks bredde for boksen

            Label itemLabel = new Label("Item name " + (i+1)); // Label med item-navn
            itemLabel.setFont(Font.font("Elms sans", 14));     // Font og størrelse
            itemLabel.setStyle("-fx-text-fill: black;");       // Sort tekst

            if (i % 2 == 0) { // Hver anden item får strikethrough (markeret som slettet)
                itemLabel.setStyle("-fx-text-fill: black; -fx-strikethrough: true;");
            }

            Button restoreButton = new Button("Restore"); // Restore-knap
            restoreButton.setStyle("-fx-background-color: #a98467; -fx-text-fill: white;"); // Styling

            Region spacer = new Region(); // Spacer til at skubbe restore-knappen til højre
            HBox.setHgrow(spacer, Priority.ALWAYS); // Spacer vokser til at fylde plads

            itemBox.getChildren().addAll(itemLabel, spacer, restoreButton); // Tilføjer label, spacer og knap til itemBox
            itemsSection.getChildren().add(itemBox); // Tilføjer itemBox til itemsSection
        }

        // ScrollPane til Items
        ScrollPane itemsScroll = new ScrollPane(itemsSection); // Scrollbar-container til items
        itemsScroll.setFitToWidth(true); // Indholdet fylder hele bredden
        itemsScroll.setPrefHeight(300);  // Højde for items scroll
        itemsScroll.setStyle("-fx-background: #F0EAD2;"); // Baggrundsfarve

        //---------------------------- Lists sektion ----------------------------
        GridPane listGrid = new GridPane(); // Grid til at placere list-bokse
        listGrid.setHgap(10);               // Vandret spacing
        listGrid.setVgap(10);               // Lodret spacing
        listGrid.setPadding(new Insets(10));// Indre margin

        Label listsTitle = new Label("Lists"); // Titel til lists-sektionen
        listsTitle.setFont(Font.font("Elms sans", 16)); // Font og størrelse
        listsTitle.setStyle("-fx-text-fill: #31672aff;"); // Grøn tekstfarve

        // Loop til at tilføje 12 lists
        for (int i = 0; i < 12; i++) {
            VBox listBox = new VBox(5); // Vertikal container til hver list
            listBox.setAlignment(Pos.CENTER); // Centreret indhold
            listBox.setStyle( // Styling af list-boksen
                "-fx-background-color: #849a47;" +
                "-fx-padding: 10px;" +
                "-fx-border-radius: 8px;" +
                "-fx-background-radius: 8px;"
            );
            listBox.setMinSize(100, 80); // Minimum størrelse

            Label listLabel = new Label("List name " + (i+1)); // Label med list-navn
            listLabel.setFont(Font.font("Elms sans", 12));     // Font og størrelse
            listLabel.setStyle("-fx-text-fill: black;");       // Sort tekst

            Button restoreButton = new Button("Restore"); // Restore-knap
            restoreButton.setStyle("-fx-background-color: #a98467; -fx-text-fill: white;"); // Styling

            listBox.getChildren().addAll(listLabel, restoreButton); // Tilføjer label og knap til listBox
            listGrid.add(listBox, i % 3, i / 3); // Tilføjer listBox til grid (3 kolonner)
        }

        // ScrollPane til Lists
        ScrollPane listsScroll = new ScrollPane(listGrid); // Scrollbar-container til lists
        listsScroll.setFitToWidth(true); // Indholdet fylder hele bredden
        listsScroll.setPrefHeight(300);  // Højde for lists scroll
        listsScroll.setStyle("-fx-background: #F0EAD2;"); // Baggrundsfarvee

        // ---------------- Layout ----------------
        VBox centerContent = new VBox(20, itemsTitle, itemsScroll, listsTitle, listsScroll); // Samler items og lists i en VBox
        centerContent.setPadding(new Insets(20)); // Indre margin

        BorderPane layout = new BorderPane(); // BorderPane til at placere top, center, bottom
        layout.setCenter(centerContent);      // Center = vores indhold
        // ✅ Brug BottomBarFactory som i alle andre FE.java filer
        layout.setBottom(BottomBarFactory.createBottomBar(stage)); // Bottombar nederst
        layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';"); // Styling af layout
        layout.setTop(topBar); // viser topbar øverst


        Scene scene = new Scene(layout, 1197/3, 2256/3); // Scene med iPhone 16 proportioner
        stage.setScene(scene); // Sætter scenen på stage
        stage.setTitle("Recently Deleted"); // Titel på vinduet
        stage.show(); // Viser vinduet
    }

    public static void showRecentlyDeletedScene(Stage stage) { // Statisk metode til at vise scenen fra andre klasser
        new recentlyDeletedFE().start(stage); // Opretter instans og kalder start
    }
}
