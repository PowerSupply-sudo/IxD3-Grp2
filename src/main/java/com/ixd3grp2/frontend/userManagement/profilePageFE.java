package com.ixd3grp2.frontend.userManagement;

// Imports for Navigation (assuming these are in the parent package)
import com.ixd3grp2.frontend.BottomBarFactory;
import com.ixd3grp2.frontend.homePageFE;

// JavaFX Imports
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*; // Imports BorderPane, VBox, HBox, TilePane, Priority, etc.
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

// Icon Imports
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.feather.Feather;

public class profilePageFE extends Application {

    // Colors extracted from your design
    private static final String BG_BEIGE = "#F0EAD2";
    private static final String PRIMARY_BROWN = "#6C584C"; // Dark outlines/text
    private static final String ACCENT_BROWN = "#A98467";  // Avatar fill / Button fill
    private static final String ITEM_GREEN = "#849a47";    // Card background

    @Override
    public void start(Stage stage) {
        
        // 1. Root Layout
        BorderPane layout = new BorderPane();
        layout.setStyle("-fx-background-color: " + BG_BEIGE + "; -fx-font-family: 'Elms sans';");

        // -----------------------------------------
        // 2. TOP SECTION (Avatar + Name)
        // -----------------------------------------
        VBox topSection = new VBox(10);
        topSection.setAlignment(Pos.CENTER);
        topSection.setPadding(new Insets(40, 0, 0, 0)); // Padding from top

        // -- Avatar Circle --
        StackPane avatarContainer = new StackPane();
        Circle avatarCircle = new Circle(65); // Size matched to image
        avatarCircle.setFill(Color.web(ACCENT_BROWN));
        avatarCircle.setStroke(Color.web(PRIMARY_BROWN));
        avatarCircle.setStrokeWidth(2);

        // User Icon inside Avatar
        FontIcon userIcon = new FontIcon(Feather.USER);
        userIcon.setIconSize(60);
        userIcon.setIconColor(Color.web(PRIMARY_BROWN));
        
        avatarContainer.getChildren().addAll(avatarCircle, userIcon);

        // -- Name + Edit Icon Row --
        HBox nameBox = new HBox(8); // Spacing between name and pencil
        nameBox.setAlignment(Pos.CENTER);
        
        Label nameLabel = new Label("name");
        nameLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: " + PRIMARY_BROWN + "; -fx-font-weight: bold;");
        
        Button editNameBtn = new Button();
        FontIcon editIcon = new FontIcon(Feather.EDIT_2); // The pencil icon
        editIcon.setIconSize(20);
        editIcon.setIconColor(Color.web(PRIMARY_BROWN));
        editNameBtn.setGraphic(editIcon);
        editNameBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand; -fx-padding: 0;");
        
        nameBox.getChildren().addAll(nameLabel, editNameBtn);

        // -- Divider Line --
        Line divider = new Line(0, 0, 300, 0); // Width 300
        divider.setStroke(Color.web(PRIMARY_BROWN));
        divider.setStrokeWidth(1.5);

        topSection.getChildren().addAll(avatarContainer, nameBox, divider);
        layout.setTop(topSection);


        // -----------------------------------------
        // 3. CENTER SECTION (Buy List Grid)
        // -----------------------------------------
        VBox centerContent = new VBox(15);
        centerContent.setPadding(new Insets(10, 20, 0, 20));

        // -- Header Row (Title + Settings Icon) --
        StackPane headerStack = new StackPane();
        
        // Title centered
        Label listTitle = new Label("buy list");
        listTitle.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: black;");
        
        // Settings Icon aligned Right
        Button settingsBtn = new Button();
        FontIcon settingsIcon = new FontIcon(Feather.SETTINGS);
        settingsIcon.setIconSize(24);
        settingsIcon.setIconColor(Color.web(PRIMARY_BROWN));
        settingsBtn.setGraphic(settingsIcon);
        settingsBtn.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
        settingsBtn.setAlignment(Pos.CENTER_RIGHT);
        
        headerStack.getChildren().addAll(listTitle, settingsBtn);
        StackPane.setAlignment(listTitle, Pos.CENTER);
        StackPane.setAlignment(settingsBtn, Pos.CENTER_RIGHT);


        // -- The Grid of Items (TilePane for deletion reflow) --
        TilePane itemContainer = new TilePane();
        itemContainer.setHgap(15);
        itemContainer.setVgap(15);
        itemContainer.setAlignment(Pos.TOP_CENTER);
        itemContainer.setPrefColumns(3); // Attempt to fit 3 columns
        itemContainer.setTileAlignment(Pos.CENTER);

        // Add dummy items (creating 9 items for demonstration)
        for (int i = 0; i < 9; i++) {
            // We pass 'itemContainer' so the card knows what parent to remove itself from
            VBox card = createItemCard("Item " + (i + 1), itemContainer);
            itemContainer.getChildren().add(card);
        }

        // ScrollPane for the grid
        ScrollPane scrollPane = new ScrollPane(itemContainer);
        scrollPane.setFitToWidth(true);
        // Hide scrollbars & make transparent
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        centerContent.getChildren().addAll(headerStack, scrollPane);
        
        layout.setCenter(centerContent);


        // -----------------------------------------
        // 4. BOTTOM SECTION (Navigation)
        // -----------------------------------------
        layout.setBottom(BottomBarFactory.createBottomBar(stage));


        // -----------------------------------------
        // SCENE SETUP
        // -----------------------------------------
        Scene scene = new Scene(layout, 1197.0 / 3, 2256.0 / 3);
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.show();
    }

    // -----------------------------------------
    // HELPER METHODS
    // -----------------------------------------

    // Creates the individual green cards
    private VBox createItemCard(String itemName, Pane parentContainer) {
        VBox card = new VBox();
        card.setPrefSize(100, 160); // Approximate size
        card.setMaxSize(100, 160);
        card.setAlignment(Pos.BOTTOM_CENTER);
        
        // Styling: Green bg, rounded corners, brown border
        card.setStyle(
            "-fx-background-color: " + ITEM_GREEN + ";" +
            "-fx-background-radius: 10;" +
            "-fx-border-color: " + PRIMARY_BROWN + ";" +
            "-fx-border-radius: 10;" +
            "-fx-border-width: 1.5;"
        );

        // -- The "X" Visual Placeholder (Top part of card) --
        StackPane placeholder = new StackPane();
        placeholder.setPrefHeight(100);
        VBox.setVgrow(placeholder, Priority.ALWAYS); // Fill available space
        
        Line line1 = new Line(0, 0, 90, 100);
        line1.setStroke(Color.web("white", 0.5)); // Semi-transparent white
        Line line2 = new Line(90, 0, 0, 100);
        line2.setStroke(Color.web("white", 0.5));
        
        Label nameLbl = new Label(itemName);
        nameLbl.setStyle("-fx-text-fill: " + PRIMARY_BROWN + "; -fx-font-size: 12px;");
        
        placeholder.getChildren().addAll(line1, line2, nameLbl);

        // -- The "Bye" Button (Bottom part) --
        Button byeBtn = new Button("bye");
        FontIcon trashIcon = new FontIcon(Feather.TRASH_2); // Trash icon
        trashIcon.setIconSize(12);
        trashIcon.setIconColor(Color.WHITE);
        
        byeBtn.setGraphic(trashIcon);
        byeBtn.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT); // Icon to right of text
        byeBtn.setGraphicTextGap(5);
        
        byeBtn.setStyle(
            "-fx-background-color: " + ACCENT_BROWN + ";" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 12px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 0 0 8 8;" + // Round only bottom corners
            "-fx-border-color: " + PRIMARY_BROWN + " transparent transparent transparent;" + // Top border only
            "-fx-pref-width: 98;" + // Slightly less than card width
            "-fx-cursor: hand;"
        );

        // --- DELETE FUNCTIONALITY ---
        byeBtn.setOnAction(e -> {
            parentContainer.getChildren().remove(card);
            System.out.println("Deleted " + itemName);
        });

        card.getChildren().addAll(placeholder, byeBtn);
        return card;
    }

    // Static helper to switch to this scene
    public static void showProfileScene(Stage stage) {
        new profilePageFE().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}