package com.ixd3grp2.frontend;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// NOTE: Det kan være at extendere Application ikke er nødvendigt her, 
// afhængigt af hvordan denne klasse bruges i resten af applikationen.
// Gælder også ift. @Override, så det kan fjernes hvis ikke nødvendigt.
// Det samme gælder også for public void start(Stage stage) metoden,


public class infoAboutWishFEoriginal extends Application {
    @Override
     public void start(Stage stage) {
       
  //---------------------------- Bottom Bar ----------------------------
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
        String buttonStyle = "-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: #BLACK; "
                        + "-fx-font-size: 16px;-fx-font-family: 'Elms sans';" 
                        + "-fx-padding: 10px 20px; "
                        + "-fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);

        // Add buttons to the bottombar
        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);
    
       
        
       // Implementation for infoAboutWishFE goes here
 // ------------ Wishlist form (center) ------------
        VBox topContent = new VBox(15);
        topContent.setPadding(new Insets(20));
        topContent.setAlignment(Pos.TOP_LEFT);

        Label costLabel = new Label("Enter cost");
        TextField costField = new TextField();
        costField.setPromptText("0,00 Kr.");

        Label relevanceLabel = new Label("Why is it relevant?");
        TextArea relevanceArea = new TextArea();
        relevanceArea.setPromptText("Please explain why the product is relevant for you to buy...");
        relevanceArea.setWrapText(true);
        relevanceArea.setPrefRowCount(3);

        Label waitLabel = new Label("Enter waiting period");
        HBox waitInputs = new HBox(10);
        TextField daysField = new TextField();
        daysField.setPromptText("Days");
        TextField hoursField = new TextField();
        hoursField.setPromptText("Hours");
        waitInputs.getChildren().addAll(daysField, hoursField);

        Label dateLabel = new Label("Or enter date");
        DatePicker datePicker = new DatePicker();

        Button saveButton = new Button("Save wish");
        saveButton.setStyle(
            "-fx-background-color: #DDE5B6; -fx-border-color: #849a47; -fx-text-fill: black; " +
            "-fx-font-size: 16px; -fx-font-family: 'Elms sans'; " +
            "-fx-padding: 10px 20px; -fx-background-radius: 5px; -fx-border-radius: 5px;"
        );
        saveButton.setMaxWidth(Double.MAX_VALUE);

        topContent.getChildren().addAll(
            costLabel, costField,
            relevanceLabel, relevanceArea,
            waitLabel, waitInputs,
            dateLabel, datePicker,
            saveButton
        );


  //----------------- layout and scene -------------------- 
        // We instantiate a new Scene of size 300x250, with white background and and associated scene graph rooted in 'layout'
        
        
         // Use a BorderPane to position the bottombar at the bottom
        BorderPane layout = new BorderPane();
        //Fonten og størrelsen forbliver standard (JavaFX default: typisk "System" font, størrelse 12–13).
        layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';");// Light beige background
        Scene scene = new Scene(layout, 1197/3, 2256/3);//1197/3 width and 2256/3 height of an iPhone 16


        //layout.setCenter(centerContent);//// Main content in the center
        layout.setCenter(topContent); // Main content in the center
        layout.setBottom(bottombar); // Add the bottom bar to the bottom of the layout


        // We set the scene on the stage and display it
        stage.setTitle("Stage Title");
        stage.setScene(scene);
        stage.show();
    }
}