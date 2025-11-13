package frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {

        StackPane MainScreen = new StackPane();
        Rectangle square = new Rectangle(20,20,20,20);
        square.setFill(Color.GREEN);
        MainScreen.getChildren().add(square);

        Scene scene = new Scene(MainScreen, 600, 900);

        stage.setScene(scene);
        stage.setTitle("TEST TIELE");
        stage.show();
    }
}
