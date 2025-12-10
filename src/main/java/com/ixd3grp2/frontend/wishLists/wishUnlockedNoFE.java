package com.ixd3grp2.frontend.wishLists;

import com.ixd3grp2.frontend.BottomBarFactory;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// NOTE: Det kan være at extendere Application ikke er nødvendigt her, 
// afhængigt af hvordan denne klasse bruges i resten af applikationen.
// Gælder også ift. @Override, så det kan fjernes hvis ikke nødvendigt.
// Det samme gælder også for public void start(Stage stage) metoden,

public class wishUnlockedNoFE extends Application {

    @Override
    public void start(Stage stage) {

        //---------------------------- Top Content ----------------------------
        // Øverste del af siden med billede, tid, navn og beskrivelse
        VBox topContent = new VBox(15); // Vertikal layout med spacing
        topContent.setAlignment(Pos.CENTER); // Centrerer indholdet
        topContent.setPadding(new Insets(20)); // Margin omkring indholdet

        // 🔹 Placeholder for billede (stor krydsboks)
        Label imagePlaceholder = new Label("\u2612"); // ☒ (U+2612)
        imagePlaceholder.setPrefSize(120, 120); // hele boksen
        imagePlaceholder.setAlignment(Pos.CENTER); // centrerer krydset
        imagePlaceholder.setFont(Font.font("Elms sans", 90)); // gør krydset stort
        imagePlaceholder.setStyle(
            "-fx-border-color: #849a47; " +
            "-fx-border-width: 2px; " +
            "-fx-background-color: #dde5b6;"+
            "-fx-text-fill: #849a47;"
        );


        // 🔹 Tid tilbage (fx 30d 14h ⓘ)
        Label timeLabel = new Label("30d 14h \u24D8"); // ⓘ (U+24D8)
        timeLabel.setFont(Font.font("Elms sans", 14));
        timeLabel.setStyle("-fx-text-fill: #31672aff;");

        // 🔹 Navn på ønsket
        Label itemName = new Label("Noise-cancelling headphones"); // Eksempelnavn
        itemName.setFont(Font.font("Elms sans", 16));
        itemName.setStyle("-fx-text-fill: black;");

        // 🔹 Beskrivelse ("Why do you want it?")
        Label reasonLabel = new Label("Why do you want it?");
        reasonLabel.setFont(Font.font("Elms sans", 14));
        reasonLabel.setStyle("-fx-text-fill: #31672aff;");

        // 🔹 Tekstfelt til beskrivelse
        TextArea reasonText = new TextArea("?????????????????????????????\n?????????????????????????????");
        reasonText.setWrapText(true); // Automatisk linjeskift
        reasonText.setPrefRowCount(3); // Højde på tekstfelt
        reasonText.setStyle("-fx-background-color: #dde5b6; -fx-border-color: #849a47;");

        // 🔹 Info-tekst nederst
        Label infoText = new Label("Check back in 30d 14h to see your wishlisted item");
        infoText.setFont(Font.font("Elms sans", 12));
        infoText.setStyle("-fx-text-fill: #6c584c;");

        // Tilføjer alle elementer til topContent
        topContent.getChildren().addAll(imagePlaceholder, timeLabel, itemName, reasonLabel, reasonText, infoText);

        //---------------------------- Action Buttons ----------------------------
        // Knapperne "Bye" og "Buy" nederst på siden
        HBox actionButtons = new HBox(30); // Horisontalt layout med spacing
        actionButtons.setAlignment(Pos.CENTER); // Centrerer knapperne
        actionButtons.setPadding(new Insets(10)); // Margin omkring knapperne

        Button byeButton = new Button("Bye"); // Knap til at fjerne ønsket
        Button buyButton = new Button("Buy"); // Knap til at købe ønsket

        // Styling for begge knapper
        String actionStyle = "-fx-background-color: #a98467; -fx-text-fill: white; -fx-font-size: 14px; "
                           + "-fx-font-family: 'Elms sans'; -fx-padding: 8px 20px; "
                           + "-fx-background-radius: 8px; -fx-border-radius: 8px;";
        byeButton.setStyle(actionStyle);
        buyButton.setStyle(actionStyle);

        // Tilføjer knapperne til actionButtons
        //actionButtons.getChildren().addAll(byeButton, buyButton);
        //buyButton.setOnAction(e -> {listOfWishesFE.showListOfWishesScene(stage, 0); // du kan sende rigtigt listIndex
        //});
        
        //byeButton.setOnAction(e->{listOfWishesFE.showListOfWishesScene(stage, 0);});
        //skal gå videre til en betalings side, men er ikke et krav. og siden er heller ikke i Figma'en
           

        // ---------------- Topbar med tilbageknap, titel og redigeringsknap ----------------
        Button backButton = new Button("\u2190"); // ← pil
        backButton.setFont(Font.font("Elms sans", 16));
        backButton.setStyle("-fx-text-fill: black; -fx-background-color: #DDE5B6; -fx-border-color: #849a47; "
                            + "-fx-padding: 6px 15px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        backButton.setOnAction(e -> listOfWishesFE.showListOfWishesScene(stage, 0)); // du kan sende rigtigt listIndex

        Label title = new Label("Wish View");
        title.setStyle("-fx-font-family: 'Elms sans'; -fx-font-size: 18px; -fx-text-fill: #31672aff;");

        Button editButton = new Button("\u270E"); // ✎ blyant
        editButton.setStyle("-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: black; "
                            + "-fx-font-size: 20px; -fx-font-family: 'Elms sans'; "
                            + "-fx-padding: 6px 12px; -fx-background-radius: 5px; -fx-border-radius: 5px;");
        
        // Popup logik direkte i knappen
        editButton.setOnAction(e -> {
            Stage editStage = new Stage();
            editStage.setTitle("Edit wish");

            Label editLabel = new Label("Edit wish name:");
            TextField nameField = new TextField(itemName.getText()); // bruger det nuværende navn

            Button saveButton = new Button("Safe changes");
            Button deleteButton = new Button("Delete wish");

            saveButton.setStyle("-fx-background-color: #31672aff; -fx-text-fill: white;");
            deleteButton.setStyle("-fx-background-color: #a98467; -fx-text-fill: white;");

            saveButton.setOnAction(ev -> {
                itemName.setText(nameField.getText()); // opdaterer navnet i hovedvinduet
                editStage.close();                     // lukker popup
            });

            deleteButton.setOnAction(ev -> {
                listOfWishesFE.showListOfWishesScene(stage, 0); // tilbage til oversigten
                editStage.close();
                System.out.println("Wish deleted.");
            });

            HBox buttonBar = new HBox(15);
            buttonBar.setAlignment(Pos.CENTER);
            buttonBar.getChildren().addAll(saveButton, deleteButton);

            VBox editLayout = new VBox(15);
            editLayout.setPadding(new Insets(20));
            editLayout.setAlignment(Pos.CENTER);
            editLayout.getChildren().addAll(editLabel, nameField, buttonBar);

            Scene editScene = new Scene(editLayout, 300, 200);
            editStage.setScene(editScene);
            editStage.show();

        });


        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(10);

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        // rækkefølge: back (venstre), spacer, title (midten), spacer, edit (højre)
        topBar.getChildren().addAll(backButton, leftSpacer, title, rightSpacer, editButton);

        
        //---------------------------- Layout og Scene ----------------------------
        // Samler topContent og actionButtons i centerContent
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(Pos.CENTER);
        centerContent.getChildren().addAll(topContent, actionButtons);

        // Bruger BorderPane til at placere center og bottom
        BorderPane layout = new BorderPane();
        layout.setTop(topBar);
        layout.setCenter(centerContent);
        layout.setBottom(BottomBarFactory.createBottomBar(stage)); // Bruger BottomBarFactory

        //Fonten og størrelsen forbliver standard (JavaFX default: typisk "System" font, størrelse 12–13).
        layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';");// Light beige background
        Scene scene = new Scene(layout, 1197/3, 2256/3);//1197/3 width and 2256/3 height of an iPhone 16

        
        // We set the scene on the stage and display it
        stage.setTitle("Wish unlocked no"); // Titel på vinduet
        stage.setScene(scene);// Sætter scenen på scenen
        stage.show();// Viser scenen

    }
    public static void main(String[] args) {
        launch(args);
    }
}
