package com.ixd3grp2.frontend;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane; // Added
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.TextAlignment;
import javafx.scene.paint.Color; // Added

// Import Ikonli
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.feather.Feather;

import java.util.HashMap;
import java.util.Map;

public class questionnaireFE {

    private static final Map<Integer, Integer> currentScores = new HashMap<>();
    private static Label scoreLabel;
    private static Label recommendationLabel;
    private static HBox actionButtonsBox;

    public static Pane createContent(Runnable onBack) {
        currentScores.clear();

        // Root container (The green card)
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #718355; -fx-background-radius: 15;"); 
        root.setMaxWidth(320);
        root.setMaxHeight(500);
        root.setPadding(new Insets(15, 0, 20, 0));

        // --- HEADER (Updated with X icon) ---
        
        // 1. The Title
        Label title = new Label("Questionnaire for\nwish");
        title.setStyle("-fx-font-family: 'Finger Paint', cursive; -fx-font-size: 20px; -fx-text-fill: #2b2b2b; -fx-font-weight: bold;");
        title.setTextAlignment(TextAlignment.CENTER);

        // 2. The Close (X) Button
        Button closeBtn = new Button();
        FontIcon xIcon = new FontIcon(Feather.X);
        xIcon.setIconSize(24);
        xIcon.setIconColor(Color.web("#2b2b2b")); // Dark text color
        closeBtn.setGraphic(xIcon);
        closeBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0 0 0 15;"); // Padding left
        closeBtn.setOnAction(e -> onBack.run());

        // 3. StackPane to layer Title center and X left
        StackPane headerStack = new StackPane();
        headerStack.getChildren().addAll(title, closeBtn);
        StackPane.setAlignment(title, Pos.CENTER);
        StackPane.setAlignment(closeBtn, Pos.CENTER_LEFT);

        // 4. Decorative line
        Line line = new Line(0, 0, 200, 0);
        line.setStroke(Color.web("#3E4E28"));
        line.setStrokeWidth(2);

        VBox headerBox = new VBox(10, headerStack, line);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(0, 0, 10, 0));

        // --- SCROLLABLE CONTENT ---
        VBox questionsContainer = new VBox(25);
        questionsContainer.setPadding(new Insets(10, 20, 40, 20));

        // Add Questions
        addQuestions(questionsContainer);

        // --- RESULTS SECTION ---
        VBox resultsBox = createResultsBox(onBack);
        questionsContainer.getChildren().add(resultsBox);

        // --- SCROLL PANE CONFIGURATION ---
        ScrollPane scroll = new ScrollPane(questionsContainer);
        scroll.setFitToWidth(true);
        
        // ENABLE SCROLLBAR
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); 
        
        // Add a specific CSS class so we can style ONLY this scrollbar
        scroll.getStyleClass().add("questionnaire-scroll");
        
        // Transparent background for the scrollpane itself
        scroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        root.getChildren().addAll(headerBox, scroll);

        // Wrapper to center the card on screen
        VBox wrapper = new VBox(root);
        wrapper.setAlignment(Pos.CENTER);
        wrapper.setStyle("-fx-background-color: rgba(0,0,0,0.4);"); 
        
        // Click outside to close
        wrapper.setOnMouseClicked(e -> {
            // Only close if clicking the dark background, not the green card
            if (e.getTarget() == wrapper) onBack.run(); 
        });

        return wrapper;
    }

    // (Helper methods extracted to keep code clean)
    
    private static void addQuestions(VBox container) {
        container.getChildren().add(createQuestionBlock(1, "Is this purchase relevant to a goal or problem you currently have?", new String[]{"Yes", "Maybe", "No"}, new int[]{1, 0, 0}));
        container.getChildren().add(createQuestionBlock(2, "Why do you want it? (choose best fit)", new String[]{"Genuine want", "Trend", "Unsure"}, new int[]{1, 0, 0}));
        container.getChildren().add(createQuestionBlock(3, "Is this item for you or a gift?", new String[]{"For me", "Gift", "Both"}, new int[]{1, 1, 1}));
        container.getChildren().add(createQuestionBlock(4, "Do you truly need this now?", new String[]{"Yes", "No, but nice", "No"}, new int[]{1, 0, 0}));
        container.getChildren().add(createQuestionBlock(5, "Can you afford it right now without disrupting your budget?", new String[]{"Yes", "Barely", "No"}, new int[]{1, 0, 0}));
        container.getChildren().add(createQuestionBlock(6, "Does buying it make sense compared to alternatives?", new String[]{"Yes", "Maybe", "No"}, new int[]{1, 0, 0}));
        container.getChildren().add(createQuestionBlock(7, "Do you already own something similar?", new String[]{"Yes", "Somewhat", "No"}, new int[]{0, 0, 1}));
        container.getChildren().add(createQuestionBlock(8, "Do you have room to store/use this item?", new String[]{"Yes", "Maybe", "No"}, new int[]{1, 0, 0}));
    }

    private static VBox createResultsBox(Runnable onBack) {
        VBox resultsBox = new VBox(8);
        resultsBox.setAlignment(Pos.CENTER);
        resultsBox.setPadding(new Insets(20, 0, 0, 0));
        
        Label resultTitle = new Label("Your Score");
        resultTitle.setStyle("-fx-font-weight: bold; -fx-text-fill: #3E4E28;");

        scoreLabel = new Label("Answer more questions to get a score.");
        scoreLabel.setWrapText(true);
        scoreLabel.setTextAlignment(TextAlignment.CENTER);
        scoreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12px;");

        recommendationLabel = new Label("");
        recommendationLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 13px;");

        actionButtonsBox = new HBox(15);
        actionButtonsBox.setAlignment(Pos.CENTER);
        
        Button btnBuy = createResultButton("Buy");
        Button btnMaybe = createResultButton("Maybe");
        Button btnBye = createResultButton("Bye");
        
        btnBuy.setOnAction(e -> onBack.run());
        btnMaybe.setOnAction(e -> onBack.run());
        btnBye.setOnAction(e -> onBack.run());

        actionButtonsBox.getChildren().addAll(btnBuy, btnMaybe, btnBye);
        actionButtonsBox.setVisible(false);

        resultsBox.getChildren().addAll(resultTitle, scoreLabel, recommendationLabel, actionButtonsBox);
        return resultsBox;
    }

    private static VBox createQuestionBlock(int qIndex, String text, String[] options, int[] values) {
        VBox block = new VBox(8);
        Label qLabel = new Label("Q" + qIndex + ": " + text);
        qLabel.setWrapText(true);
        qLabel.setStyle("-fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;");

        HBox optionsBox = new HBox(10);
        optionsBox.setAlignment(Pos.CENTER_LEFT);

        Button[] buttons = new Button[options.length];
        for (int i = 0; i < options.length; i++) {
            final int value = values[i];
            Button btn = new Button(options[i]);
            btn.getStyleClass().add("question-button");
            
            btn.setOnAction(e -> {
                for (Button b : buttons) {
                    b.getStyleClass().remove("question-button-selected");
                    b.getStyleClass().add("question-button");
                }
                btn.getStyleClass().add("question-button-selected");
                currentScores.put(qIndex, value);
                updateScore();
            });
            buttons[i] = btn;
            optionsBox.getChildren().add(btn);
        }
        block.getChildren().addAll(qLabel, optionsBox);
        return block;
    }

    private static void updateScore() {
        int totalQuestions = 8;
        int answered = currentScores.size();
        
        if (answered < 4) {
            scoreLabel.setText("Keep answering... (" + answered + "/" + totalQuestions + ")");
            actionButtonsBox.setVisible(false);
            return;
        }

        int score = currentScores.values().stream().mapToInt(Integer::intValue).sum();
        String rec;
        if (score >= 6) rec = "Purchase is likely reasonable.";
        else if (score >= 4) rec = "Consider waiting.";
        else rec = "Not a good idea right now.";

        scoreLabel.setText("You gave " + score + " 'best' answers.");
        recommendationLabel.setText(rec);
        actionButtonsBox.setVisible(true);
    }

    private static Button createResultButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: #A98467; -fx-text-fill: white; -fx-background-radius: 15; -fx-min-width: 60; -fx-border-color: #543E30; -fx-border-radius: 15;");
        return btn;
    }
}