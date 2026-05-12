package screens;

import gameoutput.GameData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import mechanics.Buttons;
import users.User;


import java.util.List;

public class ScoreboardScreen {


    private GridPane sbDisplay;
    private Buttons buttons;
    private MainMenu menu;
    private Stage primaryStage;
    private BorderPane gameScreen;
    private GameData db;
    private User user;
    private Region spacerLeft;
    private Region spacerRight;
    private String time;

    public ScoreboardScreen(Stage primaryStage, MainMenu menu, User user, GameData db, Buttons buttons) {
        this.db = db;
        this.user = user;
        this.primaryStage = primaryStage;
        this.gameScreen = new BorderPane();
        this.buttons = buttons;
        this.menu = menu;
        this.gameScreen.getStyleClass().add("mainRoot");

        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("EASY", "MEDIUM", "HARD");
        difficultyBox.setValue("EASY");

        difficultyBox.setOnAction(e -> { String selected = difficultyBox.getValue();
            int id = getModeId(selected);
        loadScores(id);
    });
        gameScreen.setPadding(new Insets(20, 20, 0, 0));
        sbDisplay = new GridPane();
        sbDisplay.setAlignment(Pos.TOP_CENTER);
        sbDisplay.setHgap(30);
        sbDisplay.setVgap(15);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);

        sbDisplay.getColumnConstraints().addAll(col1, col2, col3, col4);

        sbDisplay.setMaxWidth(Double.MAX_VALUE);
        sbDisplay.setMaxHeight(Double.MAX_VALUE);

        addHeaders();


        Button back = buttons.getBtnBack();
        spacerLeft = new Region();
        spacerRight = new Region();
        HBox container = new HBox();
        HBox.setHgrow(spacerLeft, Priority.ALWAYS);
        HBox.setHgrow(spacerRight, Priority.ALWAYS);
        container.getChildren().addAll(back, spacerLeft, difficultyBox, spacerRight);
        container.setAlignment(Pos.CENTER);
        back.setOnAction(e -> {
            menu.showGame();

        });
        gameScreen.setCenter(sbDisplay);
        gameScreen.setBottom(container);
        BorderPane.setAlignment(sbDisplay, Pos.CENTER);
        BorderPane.setMargin(sbDisplay, new Insets(20));
        loadScores(getModeId("EASY"));
        sbDisplay.setGridLinesVisible(true);


    }

    private int getModeId(String mode) {
        switch (mode) {
            case "EASY": return 1;
            case "MEDIUM": return 2;
            case "HARD": return 3;
            default: return 1;
        }
    }

    public BorderPane getLayout() {
        return this.gameScreen;
    }

    public void show() {
        primaryStage.getScene().setRoot(gameScreen);
    primaryStage.show();
}

private void loadScores(int gameModeId) {
    if (db == null) return;
    sbDisplay.getChildren().clear();
    sbDisplay.setGridLinesVisible(false);
    addHeaders();

    List<User> topPlayers = db.getTopScores(gameModeId);
    for (int i = 0; i < topPlayers.size(); i++) {

        User player = topPlayers.get(i);
        int row = i + 1;

        Label nameLabel = new Label(player.getUsername());
        Label scoreLabel = new Label(String.valueOf(player.getScore()));
        Label modeLabel = new Label(player.getModeName());

        if(player.getAverageReactionTime() > 0) {
            time = String.format("%.0f ms", player.getAverageReactionTime());
        } else {
            time = "N/A";
        }
        Label reactionLabel = new Label(time);
        nameLabel.getStyleClass().add("stats");
        scoreLabel.getStyleClass().add("stats");
        modeLabel.getStyleClass().add("stats");
        reactionLabel.getStyleClass().add("stats");

        sbDisplay.add(nameLabel, 0, row);
        sbDisplay.add(scoreLabel, 1, row);
        sbDisplay.add(modeLabel, 2, row);
        sbDisplay.add(reactionLabel, 3, row);
        }
    sbDisplay.setGridLinesVisible(true);

}

private void addHeaders() {
    Label nameHeader = new Label("USERNAME");
    Label scoreHeader = new Label("SCORE");
    Label diffHeader = new Label("DIFFICULTY");
    Label reactionHeader = new Label("REACTION TIME");
    Label title = new Label("SCOREBOARD");

    nameHeader.getStyleClass().add("sb-header");
    scoreHeader.getStyleClass().add("sb-header");
    diffHeader.getStyleClass().add("sb-header");
    reactionHeader.getStyleClass().add("sb-header");

    sbDisplay.add(nameHeader, 0, 0);
    sbDisplay.add(scoreHeader, 1, 0);
    sbDisplay.add(diffHeader, 2, 0);
    sbDisplay.add(reactionHeader, 3, 0);
}
}