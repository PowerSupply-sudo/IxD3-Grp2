package com.ixd3grp2.frontend.wishLists;

import java.util.Arrays;
import java.util.List;
import com.ixd3grp2.frontend.homePageFE;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class listOfWishesFE {

    // ✅ Statisk metode til at vise en ønskeliste
    public static void showListOfWishesScene(Stage stage, int listIndex) {

        // ---------------- Topbar med back, title og edit ----------------
        Button backButton = new Button("\u2190"); // venstre hjørne
        backButton.setOnAction(e -> wishListsFE.showWishListsScene(stage));
        backButton.setFont(Font.font("Elms sans", 16));
        backButton.setStyle("-fx-text-fill: black; -fx-background-color: #DDE5B6; -fx-border-color: #849a47; "
                            + "-fx-padding: 6px 15px; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        Label title = new Label("Wish List #" + (listIndex + 1)); // midten
        title.setStyle("-fx-font-family: 'Elms sans'; -fx-font-size: 18px; -fx-text-fill: #31672aff;");

        Button editButton = new Button("\u270E"); // højre hjørne
        editButton.setStyle("-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: black; "
                            + "-fx-font-size: 20px; -fx-font-family: 'Elms sans'; "
                            + "-fx-padding: 6px 12px; -fx-background-radius: 5px; -fx-border-radius: 5px;");

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(10);

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, javafx.scene.layout.Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, javafx.scene.layout.Priority.ALWAYS);

        // rækkefølge: back (venstre), spacer, title (midten), spacer, edit (højre)
        topBar.getChildren().addAll(backButton, leftSpacer, title, rightSpacer, editButton);

        // ---------------- CenterContent med grid ----------------
        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setSpacing(20);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));

        // 👉 Demo-data med backend-klassen listOfWishesBoxes
        List<listOfWishesBoxes> demoWishes = Arrays.asList(
            new listOfWishesBoxes("Noise-cancelling headphones", "30d 14h"),
            new listOfWishesBoxes("Minimalist backpack", "25d 6h"),
            new listOfWishesBoxes("Ergonomic chair", "12d 3h"),
            new listOfWishesBoxes("Water bottle", "40d 20h"),
            new listOfWishesBoxes("Desk lamp", "18d 5h"),
            new listOfWishesBoxes("Running shoes", "10d 2h")
        );

        int columns = 3;
        for (int i = 0; i < demoWishes.size(); i++) {
            listOfWishesBoxes item = demoWishes.get(i);

            VBox itemBox = new VBox(10);
            itemBox.setAlignment(Pos.CENTER);
            itemBox.setStyle("-fx-background-color: #849a47;-fx-background-radius: 8px;");
            itemBox.setPadding(new Insets(10));
            itemBox.setPrefSize(100, 100);

            Label timeLabel = new Label(item.getTimeLeft()); // placeholder tid
            timeLabel.setFont(Font.font("Elms sans", 14));
            timeLabel.setStyle("-fx-text-fill: black;");

            Label itemName = new Label(item.getName()); // navn på item
            itemName.setFont(Font.font("Elms sans", 10));
            itemName.setStyle("-fx-text-fill: black;");

            Button deleteButton = new Button("Bye \uD83D\uDDD1"); // knap til at slette item
            deleteButton.setFont(Font.font("Elms sans", 12));
            deleteButton.setStyle(
                "-fx-background-color: #a98467; " +
                "-fx-text-fill: white; " +
                "-fx-border-color: black; " +
                "-fx-background-radius: 8px; " +
                "-fx-border-radius: 8px;"
            );

            itemBox.getChildren().addAll(timeLabel, itemName, deleteButton);

            int row = i / columns;
            int col = i % columns;
            grid.add(itemBox, col, row);
        }

        centerContent.getChildren().add(grid);

        // ---------------- Bottombar ----------------
        HBox bottombar = new HBox();
        bottombar.setSpacing(40);
        bottombar.setStyle("-fx-background-color: #6c584c; -fx-padding: 10px; -fx-min-height: 60px;");
        bottombar.setAlignment(Pos.CENTER);

        Button searchButton = new Button("Search");
        Button homeButton = new Button("Home");
        Button profileButton = new Button("Profile");

        String buttonStyle = "-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: black; "
                        + "-fx-font-size: 16px;-fx-font-family: 'Elms sans';" 
                        + "-fx-padding: 10px 20px; "
                        + "-fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);

        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);

        // ---------------- Layout ----------------
        BorderPane layout = new BorderPane();
        layout.setTop(topBar);        // topbar med back, title og edit
        layout.setCenter(centerContent);
        layout.setBottom(bottombar);

        layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';");
        Scene scene = new Scene(layout, 1197/3, 2256/3);

        stage.setTitle("List of wishes");
        stage.setScene(scene);
        stage.show();

        // Navigation til Home
        homeButton.setOnAction(e -> new homePageFE().start(stage));

        // ---------------- Pop-up logik til editButton ----------------
        editButton.setOnAction(e -> {
            Stage popup = new Stage();
            popup.setTitle("Rediger ønskeliste");

            VBox popupLayout = new VBox(15);
            popupLayout.setPadding(new Insets(20));
            popupLayout.setAlignment(Pos.CENTER);

            Label editLabel = new Label("Rediger navn på ønskelisten:");
            TextField nameField = new TextField(title.getText());

            Button saveButton = new Button("Gem ændringer"); // gemmer nyt navn
            Button deleteButton = new Button("Slet ønskeliste"); // sletter hele listen

            saveButton.setStyle("-fx-background-color: #31672aff; -fx-text-fill: white;");
            deleteButton.setStyle("-fx-background-color: #a98467; -fx-text-fill: white;");

            saveButton.setOnAction(ev -> {
                title.setText(nameField.getText()); // Opdaterer navnet i UI
                popup.close();
            });

            deleteButton.setOnAction(ev -> {
                popup.close();
                wishListsFE.showWishListsScene(stage); // Tilbage til oversigten
                System.out.println("Ønskeliste #" + (listIndex + 1) + " slettet.");
            });

            // HBox med knapperne ved siden af hinanden
            HBox buttonBar = new HBox(15);
            buttonBar.setAlignment(Pos.CENTER);
            buttonBar.getChildren().addAll(saveButton, deleteButton);

            popupLayout.getChildren().addAll(editLabel, nameField, buttonBar);

            Scene popupScene = new Scene(popupLayout, 300, 200);
            popup.setScene(popupScene);
            popup.show();
        });
    }
}
