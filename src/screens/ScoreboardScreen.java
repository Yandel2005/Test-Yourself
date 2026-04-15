package screens;

import gameoutput.GameData;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import users.User;
import screens.MainMenu;

import java.util.List;

public class ScoreboardScreen {

    private Buttons buttons;
    private MainMenu menu;
    private Stage primaryStage;
    private BorderPane gameScreen;
    private GameScreen screen;
    private GridPane gridLayout;
    private GameData db;
    private User user;
    private Text scores;

    public ScoreboardScreen(Stage primaryStage, MainMenu menu, User user, GameData db, Buttons buttons) {
        this.db = db;
        this.user = user;
        this.primaryStage = primaryStage;
        this.gridLayout = new GridPane();
        this.gameScreen = new BorderPane();
        this.buttons = buttons;
        this.menu = menu;

        Button back = buttons.getBtnBack();
        back.setOnAction(e -> {
            menu.showGame();
        });
        gameScreen.setCenter(gridLayout);
        gameScreen.setBottom(back);
        loadScores();



    }

    public BorderPane getLayout() {
        return this.gameScreen;
    }


    public void show() {
    Scene scene = new Scene(gameScreen, primaryStage.getWidth(), primaryStage.getHeight());
    primaryStage.setScene(scene);
    primaryStage.show();

}

private void loadScores() {
        List<User> scores = db.getTopScores();
        gridLayout.getChildren().clear();
        for (int i = 0; i < scores.size(); i++) {
            User player = scores.get(i);
            String name = player.getUsername();
            int score = player.getScore();
            String mode_name = player.getModeName();
            gridLayout.add(new Label(name), 0, i);
            gridLayout.add(new Label(String.valueOf(score)), 1, i);
            gridLayout.add(new Label(mode_name), 2, i);
        }
}
}