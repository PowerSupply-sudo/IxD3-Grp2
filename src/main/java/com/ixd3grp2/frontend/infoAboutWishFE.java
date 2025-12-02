package com.ixd3grp2.frontend;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label; 
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//this part imports things like Hbox and a rectangle shape. a shape can't center itself which is why Hbox is imported. the shapes need to be children of Hboxes
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.stage.Stage;


//importing icons
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.feather.Feather;

public class infoAboutWishFE extends Application {
    @Override
     public void start(Stage stage) {
//------------bottombar-----------------
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
        String buttonStyle = "-fx-background-color: #DDE5B6; -fx-border-color: #31672aff; -fx-text-fill: BLACK; "
                        + "-fx-font-size: 16px;-fx-font-family: 'Elms sans';" 
                        + "-fx-padding: 10px 20px; "
                        + "-fx-background-radius: 5px; -fx-border-radius: 5px;";
        searchButton.setStyle(buttonStyle);
        homeButton.setStyle(buttonStyle);
        profileButton.setStyle(buttonStyle);


        // Add buttons to the bottombar
        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);
    
        // Use a BorderPane to position the bottombar at the bottom
        BorderPane layout = new BorderPane();
        //layout.setCenter(centerContent);//// Main content in the center
        layout.setBottom(bottombar); // Add the bottom bar to the bottom of the layout
       
        // Implementation for infoAboutWishFE goes here  

    //----------------- layout and scene --------------------
        // We instantiate a new Scene of size 300x250, with white background and and associated scene graph rooted in 'layout'
         layout.setStyle("-fx-background-color: #F0EAD2; -fx-font-family: 'Elms sans';");// Light beige background
        Scene scene = new Scene(layout, 1197/3, 2256/3);//1197/3 width and 2256/3 height of an iPhone 16
     
        
        // We set the scene on the stage and display it
        stage.setTitle("Info About Wish");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}