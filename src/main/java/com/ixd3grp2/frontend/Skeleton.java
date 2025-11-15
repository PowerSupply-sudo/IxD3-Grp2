package com.ixd3grp2.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Skeleton extends Application{
    @Override
    public void start(Stage stage) {
        /* Here is where the scene graph is defined */
        // We create the rectangle shape
Rectangle square = new Rectangle(200,50,60,60);
square.setFill(Color.GREEN);
// We create the circle shape
Circle circle = new Circle(150,150,30);
circle.setFill(Color.ORANGE);
// We create a group containing the shapes above
Group graphics = new Group();
graphics.getChildren().addAll(square, circle);
// We create the text component
Text text = new Text(20, 120, "Text");
text.setFont(new Font(20));
// We create the root containing all the above
Group root = new Group();
root.getChildren().addAll(text, graphics);
// We instantiate a new Scene of size 300x250, with white
// background and and associated scene graph rooted in 'root'
Scene scene = new Scene(root, 1197/3, 2256/3, Color.WHITE);

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
