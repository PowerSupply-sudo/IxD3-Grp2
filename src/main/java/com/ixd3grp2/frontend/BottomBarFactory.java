package com.ixd3grp2.frontend;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;              // ✅ vigtigt import

public class BottomBarFactory {

    public static HBox createBottomBar(Stage stage) { // ✅ signatur med Stage
        HBox bottombar = new HBox();
        bottombar.setSpacing(40);
        bottombar.setStyle("-fx-background-color: #6c584c; -fx-padding: 10px; -fx-min-height: 60px;");
        bottombar.setAlignment(Pos.CENTER);

        Button searchButton = new Button("\uD83D\uDD0D");
        Button homeButton = new Button("\uD83C\uDFE0");
        Button profileButton = new Button("\uD83D\uDC64");

        String buttonStyle = "-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: black; "
                           + "-fx-font-size: 16px;-fx-font-family: 'Elms sans';"
                           + "-fx-padding: 10px 20px; "
                           + "-fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);

        // ✅ Navigation indbygget
        homeButton.setOnAction(e -> new homePageFE().start(stage));
        //profileButton.setOnAction(e -> new profilePageFE().start(stage));// Manlger Profile page fra Jens   

        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);
        return bottombar;
    }
}
