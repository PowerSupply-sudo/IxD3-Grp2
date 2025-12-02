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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


//importing icons
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.feather.Feather;

public class infoAboutWishFE extends Application {

    // Popup dimensions
    private static final double POPUP_WIDTH = 350;
    private static final double POPUP_HEIGHT = 550;
    
    // Screen dimensions (for testing)
    private static final double SCREEN_WIDTH = 1197.0 / 3;
    private static final double SCREEN_HEIGHT = 2256.0 / 3;

    /**
     * COMPONENT METHOD
     * Creates and returns the "Info about wish" popup as a component.
     * 
     * @param onClose Called when user wants to close the popup
     * @return The popup as a Pane
     */
    public static Pane createContent(Runnable onClose) {
        
        BorderPane popup = new BorderPane();
        
        popup.setPrefWidth(POPUP_WIDTH);
        popup.setPrefHeight(POPUP_HEIGHT);
        popup.setMaxWidth(POPUP_WIDTH);
        popup.setMaxHeight(POPUP_HEIGHT);
        
        popup.setStyle(
            "-fx-background-color: #ADC178;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #6C584C;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 8;"
        );
        
        // ============== TOP: Close Button ==============
        
        FontIcon closeIcon = new FontIcon(Feather.X);
        closeIcon.setIconSize(22);
        closeIcon.setIconColor(Color.web("#6C584C"));
        
        Button closeButton = new Button();
        closeButton.setGraphic(closeIcon);
        closeButton.setStyle(
            "-fx-background-color: transparent;" +
            "-fx-cursor: hand;" +
            "-fx-padding: 5;"
        );
        
        // When clicked, run the onClose callback
        closeButton.setOnAction(e -> onClose.run());
        
        // HBox to push the X button to the right
        HBox topBar = new HBox(closeButton);
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPadding(new Insets(10, 10, 0, 10));
        
        popup.setTop(topBar);
        
        // ============== CENTER: Form Content ==============
        
        VBox formContent = new VBox(12);
        formContent.setPadding(new Insets(5, 15, 15, 15));
        
        // ----- Enter Cost Section -----
        Label costLabel = new Label("Enter cost");
        costLabel.setStyle(
            "-fx-text-fill: #543E30;" +
            "-fx-font-size: 22px;"
        );
        
        // TEXT FIELD = WHITE (indicates user can type here)
        TextField costField = new TextField();
        costField.setPromptText("0,00 Kr.");
        costField.setPrefWidth(100);
        costField.setMaxWidth(100);
        costField.setStyle(
            "-fx-background-color: #FFFFFF;" +
            "-fx-text-fill: #543E30;" +
            "-fx-prompt-text-fill: #8B7355;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 8 12;" +
            "-fx-border-color: #6C584C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 8;"
        );
        
        formContent.getChildren().addAll(costLabel, costField);
        
        popup.setCenter(formContent);

        // ----- Why is it relevant? Section -----
        Label relevanceLabel = new Label("Why is it relevant?");
        relevanceLabel.setStyle(
            "-fx-text-fill: #543E30;" +
            "-fx-font-size: 22px;"
        );
        
        // TextArea for explanation (multi-line input) = WHITE (indicates user can type here)
        TextArea relevanceArea = new TextArea();
        relevanceArea.setPromptText("Please explain why the product is relevant for you to buy...");
        relevanceArea.setWrapText(true);
        relevanceArea.setPrefWidth(200);   // Wider
        relevanceArea.setPrefHeight(120);  // Taller
        relevanceArea.setStyle(
            "-fx-border-color: #6C584C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 8;" +
            "-fx-font-size: 12px;"
        );
        
        // Side buttons (stacked vertically) = BROWN (indicates clickable button)
        Button questionnaireBtn = new Button("Question\nnaire");
        questionnaireBtn.setStyle(
            "-fx-background-color: #A98467;" +
            "-fx-text-fill: #FFFFFF;" +
            "-fx-font-size: 12px;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 10 15;" +
            "-fx-cursor: hand;" +
            "-fx-border-color: #6C584C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 8;"
        );
        questionnaireBtn.setPrefWidth(95);  // Consistent width
        
        Button prosConsBtn = new Button("Pros &\nCons list");
        prosConsBtn.setStyle(
            "-fx-background-color: #A98467;" +
            "-fx-text-fill: #FFFFFF;" +
            "-fx-font-size: 12px;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 10 15;" +
            "-fx-cursor: hand;" +
            "-fx-border-color: #6C584C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 8;"
        );
        prosConsBtn.setPrefWidth(95);  // Consistent width
        
        // VBox to stack the two buttons vertically
        VBox sideButtons = new VBox(10);
        sideButtons.setAlignment(Pos.TOP_CENTER);
        sideButtons.getChildren().addAll(questionnaireBtn, prosConsBtn);
        
        // HBox to place TextArea and buttons side by side
        HBox relevanceRow = new HBox(12);
        relevanceRow.setAlignment(Pos.TOP_LEFT);
        relevanceRow.getChildren().addAll(relevanceArea, sideButtons);
        
        // Add spacing before this section
        VBox.setMargin(relevanceLabel, new Insets(10, 0, 0, 0));  // 10px top margin
        
        formContent.getChildren().addAll(relevanceLabel, relevanceRow);

        // ----- Enter Waiting Period Section -----
        Label waitLabel = new Label("Enter waiting period");
        waitLabel.setStyle(
            "-fx-text-fill: #543E30;" +
            "-fx-font-size: 22px;"
        );
        
        // Days field = WHITE (indicates user can type here)
        TextField daysField = new TextField();
        daysField.setPromptText("Days");
        daysField.setPrefWidth(70);
        daysField.setMaxWidth(70);
        daysField.setStyle(
            "-fx-background-color: #FFFFFF;" +
            "-fx-text-fill: #543E30;" +
            "-fx-prompt-text-fill: #8B7355;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #6C584C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 8;" +
            "-fx-padding: 8 12;"
        );
        
        // Dash between fields
        Label dashLabel = new Label("—");
        dashLabel.setStyle(
            "-fx-text-fill: #543E30;" +
            "-fx-font-size: 18px;" +
            "-fx-padding: 0 5;"
        );
        
        // Hours field = WHITE (indicates user can type here)
        TextField hoursField = new TextField();
        hoursField.setPromptText("Hours");
        hoursField.setPrefWidth(70);
        hoursField.setMaxWidth(70);
        hoursField.setStyle(
            "-fx-background-color: #FFFFFF;" +
            "-fx-text-fill: #543E30;" +
            "-fx-prompt-text-fill: #8B7355;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #6C584C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 8;" +
            "-fx-padding: 8 12;"
        );
        
        // HBox for Days — Hours
        HBox waitRow = new HBox(5);
        waitRow.setAlignment(Pos.CENTER_LEFT);
        waitRow.getChildren().addAll(daysField, dashLabel, hoursField);
        
        // "Or enter date" label
        Label orDateLabel = new Label("Or enter date");
        orDateLabel.setStyle(
            "-fx-text-fill: #6C584C;" +
            "-fx-font-size: 12px;"
        );
        
        // DatePicker (styled via CSS) = WHITE (indicates user can select here)
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date");
        datePicker.setPrefWidth(140);
        datePicker.setMaxWidth(140);
        datePicker.setShowWeekNumbers(false);  // Removes the week numbers (5, 6, 7, 8, etc.)
        
        // Add spacing
        VBox.setMargin(waitLabel, new Insets(15, 0, 0, 0));
        VBox.setMargin(orDateLabel, new Insets(5, 0, 0, 0));
        
        formContent.getChildren().addAll(waitLabel, waitRow, orDateLabel, datePicker);
        
        // ----- Save Wish Button (bottom right) = BROWN (indicates clickable button) -----
        Button saveButton = new Button("Save wish");
        saveButton.setStyle(
            "-fx-background-color: #A98467;" +
            "-fx-text-fill: #FFFFFF;" +
            "-fx-font-size: 14px;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #6C584C;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 8;" +
            "-fx-padding: 10 20;" +
            "-fx-cursor: hand;"
        );
        
        saveButton.setOnAction(e -> {
            System.out.println("Save wish clicked!");
            // TODO: Save the wish data
            onClose.run();  // Close the popup after saving
        });
        
        // HBox to push button to the right
        HBox saveRow = new HBox(saveButton);
        saveRow.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(saveRow, new Insets(15, 0, 0, 0));
        
        formContent.getChildren().add(saveRow);

        //don't add anything below this one or the error "unreachable code" will come up. note for myself
        return popup;
        
    }

    

    /**
     * TEST HARNESS
     * Lets us run and test the component in isolation.
     */
    @Override
    public void start(Stage stage) {
        
        // Create a fake "background" to simulate home page. i'm gonna explain to sandra tomorrow
        Pane fakeHomePage = new Pane();
        fakeHomePage.setStyle("-fx-background-color: #F0EAD2;");
        
        // StackPane lets us layer the popup on top
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(fakeHomePage);
        
        // Create our component
        Pane popup = createContent(() -> {
            System.out.println("Close button clicked!");
            // In real app: root.getChildren().remove(popup);
        });
        
        // Add popup on top
        root.getChildren().add(popup);
        
        // Show it
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Load CSS for proper TextArea styling. apparently this is always done after setting the scene and not before. the more you know. i kept getting errors
        scene.getStylesheets().add(
            getClass().getResource("infoAboutWish.css").toExternalForm()
        );
        
        stage.setTitle("Testing: Info About Wish Component");
        stage.setScene(scene);
        stage.show();
    }
//ksjdfbksjhfks
    public static void main(String[] args) {
        launch(args);
    }
}