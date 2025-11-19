package com.ixd3grp2.frontend;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
//import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class homePageFE extends Application{
    @Override
    public void start(Stage stage) {     
        // We create the text component

        Text headline = new Text("324");
        headline.setFont(new Font("Elms sans", 48));// Large font size for headline
        headline.setFill(Color.web("#31672aff")); // Dark green color

        Text subtext = new Text("DKK saved");
        subtext.setFont(new Font("Elms sans", 24));// Smaller font size for subtext
        subtext.setFill(Color.web("#31672aff")); // Dark green color

        // We create the root containing all the above
        //Group root = new Group();
        //root.getChildren().addAll(headline, subtext);

        //Create a VBox to center the headline and subtext vertically and horizontally
        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER); // Center content
        centerContent.setSpacing(10); // Space between headline and subtext
        centerContent.getChildren().addAll(headline, subtext); // Add headline and subtext to VBox

        //Create the bottombar
        HBox bottombar = new HBox();
        bottombar.setSpacing(50);// Space between buttons
        bottombar.setStyle("-fx-background-color: #b1d06aff; -fx-padding: 10px;"); // Ligth greenbackground
        //bottombar.setLayoutY(2256/3 - 50); // Position at the bottom of the scene
        bottombar.setAlignment(Pos.CENTER);// Center buttons horizontally and vertically


        // Create buttons for the bottom bar
        Button searchButton = new Button("Search");
        Button homeButton = new Button("Home");
        Button profileButton = new Button("Profile");

        // Add buttons to the bottom bar, and the bottons placed in the correct order
        searchButton.setStyle("-fx-border-color: #31672aff;");// Green background
        homeButton.setStyle("-fx-border-color: #31672aff;"); // Green background
        profileButton.setStyle("-fx-border-color: #31672aff;"); // Green background

        // Add buttons to the bottombar
        bottombar.getChildren().addAll(searchButton, homeButton, profileButton);
    
        // Use a BorderPane to position the bottombar at the bottom
        BorderPane layout = new BorderPane();
        layout.setCenter(centerContent);//// Main content in the center
        layout.setBottom(bottombar); // Add the bottom bar to the bottom of the layout
       

        // We instantiate a new Scene of size 300x250, with white background and and associated scene graph rooted in 'layout'
        Scene scene = new Scene(layout, 1197/3, 2256/3, Color.WHITE);

        
        // We set the scene on the stage and display it
        stage.setTitle("Stage Title");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        /* Launch the Application class in which is in */
        launch(args);

        /*the above is equivalent to calling application.launch(SimpleStage.class, args); */
    }
}
