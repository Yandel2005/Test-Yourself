package screens;

import gameoutput.GameData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
    private GameScreen screen;
    private GameData db;
    private User user;

    public ScoreboardScreen(Stage primaryStage, MainMenu menu, User user, GameData db, Buttons buttons) {
        this.db = db;
        this.user = user;
        this.primaryStage = primaryStage;
        this.gameScreen = new BorderPane();
        this.buttons = buttons;
        this.menu = menu;
        this.gameScreen.getStyleClass().add("mainRoot");
        gameScreen.setPadding(new Insets(20));
        sbDisplay = new GridPane();
        sbDisplay.setAlignment(Pos.TOP_CENTER);
        sbDisplay.setHgap(30);
        sbDisplay.setVgap(15);
        sbDisplay.setGridLinesVisible(true);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33);

        sbDisplay.getColumnConstraints().addAll(col1, col2, col3);

        sbDisplay.setMaxWidth(Double.MAX_VALUE);
        sbDisplay.setMaxHeight(Double.MAX_VALUE);

        addHeaders();


        Button back = buttons.getBtnBack();
        back.setOnAction(e -> {
            menu.showGame();
        });
        gameScreen.setCenter(sbDisplay);
        gameScreen.setBottom(back);
        loadScores();



    }

    public BorderPane getLayout() {
        return this.gameScreen;
    }


    public void show() {
   primaryStage.getScene().setRoot(gameScreen);
    primaryStage.show();
}

private void loadScores() {
    if (db == null) return;

    List<User> topPlayers = db.getTopScores();
    for (int i = 0; i < topPlayers.size(); i++) {
        User player = topPlayers.get(i);
        int row = i + 1;

        Label nameLabel = new Label(player.getUsername());
        Label scoreLabel = new Label(String.valueOf(player.getScore()));
        Label modeLabel = new Label(player.getModeName());

        nameLabel.getStyleClass().add("stats");
        scoreLabel.getStyleClass().add("stats");
        modeLabel.getStyleClass().add("stats");

        sbDisplay.add(nameLabel, 0, row);
        sbDisplay.add(scoreLabel, 1, row);
        sbDisplay.add(modeLabel, 2, row);
        }
}

private void addHeaders() {
    Label nameHeader = new Label("USERNAME");
    Label scoreHeader = new Label("SCORE");
    Label diffHeader = new Label("DIFFICULTY");

    nameHeader.getStyleClass().add("sb-header");
    scoreHeader.getStyleClass().add("sb-header");
    diffHeader.getStyleClass().add("sb-header");

    sbDisplay.add(nameHeader, 0, 0);
    sbDisplay.add(scoreHeader, 1, 0);
    sbDisplay.add(diffHeader, 2, 0);
}
}