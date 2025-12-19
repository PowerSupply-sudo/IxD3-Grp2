package com.ixd3grp2.frontend.wishLists;

import java.util.Arrays;
import java.util.List;

import com.ixd3grp2.frontend.BottomBarFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class listOfWishesFE {

    // Static method for displaying wishlists
    public static void showListOfWishesScene(Stage stage, int listIndex) {

        Button backButton = new Button("\u2190"); 
        backButton.setOnAction(e -> wishListsFE.showWishListsScene(stage));
        backButton.setFont(Font.font("Elms sans", 16));
        backButton.setStyle(
            "-fx-text-fill: black; -fx-background-color: #DDE5B6; -fx-border-color: #849a47; " +
            "-fx-padding: 6px 15px; -fx-background-radius: 5px; -fx-border-radius: 5px;"
        );

        Label title = new Label("Wish List #" + (listIndex + 1));
        title.setStyle("-fx-font-family: 'Elms sans'; -fx-font-size: 18px; -fx-text-fill: #31672aff;");


        Button editButton = new Button("\u270E");
        editButton.setStyle(
            "-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: black; " +
            "-fx-font-size: 20px; -fx-font-family: 'Elms sans'; " +
            "-fx-padding: 6px 12px; -fx-background-radius: 5px; -fx-border-radius: 5px;"
        );

        editButton.setOnAction(e -> {
            Stage editStage = new Stage();
            editStage.setTitle("Edit wish list");

            Label editLabel = new Label("Edit wish list name:");
            TextField nameField = new TextField(title.getText());

            Button saveButton = new Button("Save changes");
            Button deleteButton = new Button("Delete wish list");

            saveButton.setStyle("-fx-background-color: #31672aff; -fx-text-fill: white;");
            deleteButton.setStyle("-fx-background-color: #a98467; -fx-text-fill: white;");

            saveButton.setOnAction(ev -> {
                title.setText(nameField.getText());
                editStage.close();
            });

            deleteButton.setOnAction(ev -> {
                wishListsFE.showWishListsScene(stage);
                editStage.close();
                System.out.println("Wish list #" + (listIndex + 1) + " deleted.");
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

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, javafx.scene.layout.Priority.ALWAYS);

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(10);
        topBar.getChildren().addAll(backButton, leftSpacer, title, rightSpacer, editButton);

        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setSpacing(20);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));

        centerContent.getChildren().add(grid);

        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setFitToWidth(true);   // gør at grid fylder hele bredden
        scrollPane.setFitToHeight(false);  // gør at grid fylder hele højden
        scrollPane.setPannable(true);     // tillader at man kan scrolle med musen
        scrollPane.setStyle("-fx-background: #f0ead2; -fx-background-color: #f0ead2;"); // Makes the background transparent

        // Kun lodret scrollbar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        scrollPane.setStyle("-fx-background: #F0EAD2; -fx-background-color: #F0EAD2;");

        // Demo-data med backend-klassen listOfWishesBoxes
        // (demo-data kan slettes hvis vi for vores database til at virke når vi tilføjer ny ønsker,
        // for så burde der ikke være brug for dem, 7,9,13 knock on wood, bank, bank, bank)
        List<listOfWishesBoxes> demoWishes = Arrays.asList(
            new listOfWishesBoxes("Noise-cancelling headphones", "30d 14h"),
            new listOfWishesBoxes("Minimalist backpack", "25d 6h"),
            new listOfWishesBoxes("Ergonomic chair", "12d 3h"),
            new listOfWishesBoxes("Water bottle", "40d 20h"),
            new listOfWishesBoxes("Desk lamp", "18d 5h"),
            new listOfWishesBoxes("Running shoes", "0d 0h"),
            new listOfWishesBoxes("Smartwatch", "5d 12h"),
            new listOfWishesBoxes("E-reader", "2d 8h"),
            new listOfWishesBoxes("Wireless charger", "15d 4h"),
            new listOfWishesBoxes("Yoga mat", "0d 0h"),
            new listOfWishesBoxes("Bluetooth speaker", "22d 10h"),
            new listOfWishesBoxes("Sunglasses", "8d 16h"),
            new listOfWishesBoxes("Travel mug", "14d 2h"),
            new listOfWishesBoxes("Fitness tracker", "0d 0h"),
            new listOfWishesBoxes("Portable hard drive", "28d 18h"),
            new listOfWishesBoxes("Desk organizer", "11d 7h"),
            new listOfWishesBoxes("Laptop stand", "19d 9h"),
            new listOfWishesBoxes("Noise-cancelling headphones", "30d 14h"),
            new listOfWishesBoxes("Minimalist backpack", "25d 6h")

        );

        int columns = 3;
        for (int i = 0; i < demoWishes.size(); i++) {
            listOfWishesBoxes item = demoWishes.get(i);

            VBox itemBox = new VBox(10);
            itemBox.setAlignment(Pos.CENTER);
            itemBox.setStyle("-fx-background-color: #849a47; -fx-background-radius: 8px;");
            itemBox.setPadding(new Insets(10));
            itemBox.setMinSize(100, 100);
            itemBox.setMaxSize(100, 100);

            Label timeLabel = new Label(item.getTimeLeft());
            timeLabel.setFont(Font.font("Elms sans", 14));
            timeLabel.setStyle("-fx-text-fill: black;");

            Label itemName = new Label(item.getName());
            itemName.setFont(Font.font("Elms sans", 10));
            itemName.setStyle("-fx-text-fill: black;");

            // Kun vis deleteButton hvis tiden er udløbet
            if (isExpired(item.getTimeLeft())) {
                Button deleteButton = new Button("Bye \uD83D\uDDD1");
                deleteButton.setFont(Font.font("Elms sans", 12));
                deleteButton.setStyle(
                    "-fx-background-color: #a98467; " +
                    "-fx-text-fill: white; " +
                    "-fx-border-color: black; " +
                    "-fx-background-radius: 8px; " +
                    "-fx-border-radius: 8px;"
                );
                itemBox.getChildren().addAll(timeLabel, itemName, deleteButton);
                deleteButton.setOnAction(e -> {
                grid.getChildren().remove(itemBox);
                System.out.println("Wish '" + item.getName() + "' deleted.");
                 });
            } else {
                itemBox.getChildren().addAll(timeLabel, itemName);
            }
                
        

            itemBox.setOnMouseClicked(e -> new wishUnlockedYesFE().start(stage));

            itemBox.setOnMouseClicked(e -> {
                if (isExpired(item.getTimeLeft())) {
                    new wishUnlockedYesFE().start(stage);
                } else {
                    new wishUnlockedNoFE().start(stage);
                }
            });

            int row = i / columns;
            int col = i % columns;
            grid.add(itemBox, col, row);
        }

       BorderPane layout = new BorderPane();
       layout.setCenter(scrollPane);
       layout.setTop(topBar);
       layout.setBottom(BottomBarFactory.createBottomBar(stage));
       layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';");

        Scene scene = new Scene(layout, 1197 / 3, 2256 / 3);// Højden og bredden på en iPhone 16
        stage.setTitle("List of wishes");// Titel på vinduet
        stage.setScene(scene);// Sætter scenen på scenen
        stage.show();// Viser scenen
    }

    // Demo: tjekker om tiden er udløbet
    private static boolean isExpired(String timeLeft) {
        try {
            // Split fx "30d 14h"
            String[] parts = timeLeft.split(" ");
            int days = Integer.parseInt(parts[0].replace("d", ""));
            int hours = Integer.parseInt(parts[1].replace("h", ""));
            return days <= 0 && hours <= 0;
        } catch (Exception e) {
            return false; // fejl i formatet
        }
    }
}
