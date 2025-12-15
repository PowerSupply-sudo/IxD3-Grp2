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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import org.kordamp.ikonli.feather.Feather;
import org.kordamp.ikonli.javafx.FontIcon;

public class infoAboutWishFE extends Application {

    // Popup dimensions
    private static final double POPUP_WIDTH = 350;
    private static final double POPUP_HEIGHT = 550;
    
    // Screen dimensions (for testing)
    private static final double SCREEN_WIDTH = 1197.0 / 3;
    private static final double SCREEN_HEIGHT = 2256.0 / 3;

    public static Pane createContent(Runnable onClose) {
        
        // 1. Create the Root StackPane (Layers: Content -> Overlay)
        StackPane rootStack = new StackPane();
        rootStack.setPrefSize(POPUP_WIDTH, POPUP_HEIGHT);
        rootStack.setMaxSize(POPUP_WIDTH, POPUP_HEIGHT);

        // 2. The Main "Info About Wish" Content (Your original BorderPane)
        BorderPane mainContent = new BorderPane();
        mainContent.setStyle(
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
        closeButton.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 5;");
        closeButton.setOnAction(e -> {
            // Get the stage from the button itself
            Stage currentStage = (Stage) closeButton.getScene().getWindow();
            new homePageFE().start(currentStage);
            onClose.run();
        });
        
        HBox topBar = new HBox(closeButton);
        topBar.setAlignment(Pos.CENTER_RIGHT);
        topBar.setPadding(new Insets(10, 10, 0, 10));
        mainContent.setTop(topBar);
        
        // ============== CENTER: Form Content ==============
        VBox formContent = new VBox(12);
        formContent.setPadding(new Insets(5, 15, 15, 15));
        
        // Cost
        Label costLabel = new Label("Enter cost");
        costLabel.setStyle("-fx-text-fill: #543E30; -fx-font-size: 22px;");
        TextField costField = new TextField();
        costField.setPromptText("0,00 Kr.");
        costField.setMaxWidth(100);
        costField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #543E30; -fx-background-radius: 8; -fx-padding: 8 12; -fx-border-color: #6C584C; -fx-border-radius: 8;");
        formContent.getChildren().addAll(costLabel, costField);
        
        mainContent.setCenter(formContent);

        // Relevance
        Label relevanceLabel = new Label("Why is it relevant?");
        relevanceLabel.setStyle("-fx-text-fill: #543E30; -fx-font-size: 22px;");
        
        TextArea relevanceArea = new TextArea();
        relevanceArea.setPromptText("Please explain why the product is relevant for you to buy...");
        relevanceArea.setWrapText(true);
        relevanceArea.setPrefSize(200, 120);
        relevanceArea.setStyle("-fx-border-color: #6C584C; -fx-border-radius: 8; -fx-font-size: 12px;");
        
        // Buttons
        Button questionnaireBtn = new Button("Question\nnaire");
        questionnaireBtn.setPrefWidth(95);
        questionnaireBtn.setStyle("-fx-background-color: #A98467; -fx-text-fill: #FFFFFF; -fx-font-size: 12px; -fx-background-radius: 8; -fx-border-color: #6C584C; -fx-border-radius: 8; -fx-cursor: hand;");
        
        Button prosConsBtn = new Button("Pros &\nCons list");
        prosConsBtn.setPrefWidth(95);
        prosConsBtn.setStyle("-fx-background-color: #A98467; -fx-text-fill: #FFFFFF; -fx-font-size: 12px; -fx-background-radius: 8; -fx-border-color: #6C584C; -fx-border-radius: 8; -fx-cursor: hand;");
        
        VBox sideButtons = new VBox(10, questionnaireBtn, prosConsBtn);
        sideButtons.setAlignment(Pos.TOP_CENTER);
        
        HBox relevanceRow = new HBox(12, relevanceArea, sideButtons);
        relevanceRow.setAlignment(Pos.TOP_LEFT);
        
        VBox.setMargin(relevanceLabel, new Insets(10, 0, 0, 0));
        formContent.getChildren().addAll(relevanceLabel, relevanceRow);

        // Waiting Period
        Label waitLabel = new Label("Enter waiting period");
        waitLabel.setStyle("-fx-text-fill: #543E30; -fx-font-size: 22px;");
        
        TextField daysField = new TextField();
        daysField.setPromptText("Days");
        daysField.setMaxWidth(70);
        daysField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #543E30; -fx-background-radius: 8; -fx-border-color: #6C584C; -fx-border-radius: 8; -fx-padding: 8 12;");
        
        Label dashLabel = new Label("—");
        dashLabel.setStyle("-fx-text-fill: #543E30; -fx-font-size: 18px; -fx-padding: 0 5;");
        
        TextField hoursField = new TextField();
        hoursField.setPromptText("Hours");
        hoursField.setMaxWidth(70);
        hoursField.setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #543E30; -fx-background-radius: 8; -fx-border-color: #6C584C; -fx-border-radius: 8; -fx-padding: 8 12;");
        
        HBox waitRow = new HBox(5, daysField, dashLabel, hoursField);
        waitRow.setAlignment(Pos.CENTER_LEFT);
        
        Label orDateLabel = new Label("Or enter date");
        orDateLabel.setStyle("-fx-text-fill: #6C584C; -fx-font-size: 12px;");
        
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date");
        datePicker.setMaxWidth(140);
        datePicker.setShowWeekNumbers(false);
        
        VBox.setMargin(waitLabel, new Insets(15, 0, 0, 0));
        VBox.setMargin(orDateLabel, new Insets(5, 0, 0, 0));
        formContent.getChildren().addAll(waitLabel, waitRow, orDateLabel, datePicker);
        
        // Save Button
        Button saveButton = new Button("Save wish");
        saveButton.setStyle("-fx-background-color: #A98467; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-background-radius: 8; -fx-border-color: #6C584C; -fx-border-radius: 8; -fx-padding: 10 20; -fx-cursor: hand;");
        saveButton.setOnAction(e -> {
            // Get the stage from the button itself
            Stage currentStage = (Stage) saveButton.getScene().getWindow();
            new homePageFE().start(currentStage);
            
            onClose.run();
        });
        
        HBox saveRow = new HBox(saveButton);
        saveRow.setAlignment(Pos.CENTER_RIGHT);
        VBox.setMargin(saveRow, new Insets(15, 0, 0, 0));
        formContent.getChildren().add(saveRow);

        // 3. Create the Overlay Layer (Initially hidden)
        StackPane overlayLayer = new StackPane();
        overlayLayer.setVisible(false);
        
        // 4. Button Logic to Show Questionnaire
        questionnaireBtn.setOnAction(e -> {
            // Create the questionnaire pane
            Pane qPane = questionnaireFE.createContent(() -> {
                // This runnable runs when "Back" or "Done" is clicked in questionnaire
                overlayLayer.setVisible(false);
                overlayLayer.getChildren().clear(); // Clean up
            });
            overlayLayer.getChildren().add(qPane);
            overlayLayer.setVisible(true);
        });

        // 5. Assemble Root
        rootStack.getChildren().addAll(mainContent, overlayLayer);

        return rootStack;
    }

@Override
    public void start(Stage stage) {
        // 1. Create the Background Layout (Changed from Pane to BorderPane)
        BorderPane backgroundLayout = new BorderPane();
        backgroundLayout.setStyle("-fx-background-color: #F0EAD2;");
        
        // 2. Add the Bottom Bar to the background
        backgroundLayout.setBottom(BottomBarFactory.createBottomBar(stage));

        // 3. Create the Root StackPane (Layers: Background -> Popup)
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        
        // Add the background layout first (so it's at the back)
        root.getChildren().add(backgroundLayout);
        
        // 4. Create and add the Popup
        Pane popup = createContent(() -> System.out.println("Close button clicked!"));
        root.getChildren().add(popup);
        
        // 5. Scene setup
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        // Note: Make sure your CSS file exists, or comment this line out if it crashes
        scene.getStylesheets().add(getClass().getResource("infoAboutWish.css").toExternalForm());
        
        stage.setTitle("Info About Wish + Questionnaire");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}