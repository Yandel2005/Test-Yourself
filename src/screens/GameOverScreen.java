package screens;

import gameoutput.GameData;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import users.User;

public class GameOverScreen {

	private VBox gameOverLayout;
	private GameData db;
	private ScoreboardScreen sb;
	private Stage primaryStage;
	private Buttons buttons;
	private BorderPane gameScreen;
	private User user;
	private MainMenu mainMenu;


	public GameOverScreen(Stage primaryStage,  MainMenu mainMenu, Buttons buttons, User user, GameData db) {
		this.mainMenu = mainMenu;
		this.primaryStage = primaryStage;
		this.buttons = buttons;
		this.db = db;
		this.user = user;

		this.gameScreen = new BorderPane();
		this.gameOverLayout = new VBox(20);
		this.gameOverLayout.setAlignment(Pos.CENTER);
		this.gameScreen.setCenter(gameOverLayout);

		this.sb = new ScoreboardScreen(primaryStage, this.mainMenu, this.user, this.db, this.buttons);
	}

	public void show() {
		gameOverLayout.getChildren().clear();
		gameOverLayout.getStyleClass().add("mainRoot");

		Button btnPA = buttons.getBtnPA();
		Button btnSB = buttons.getBtnSB();
		Button btnExit = buttons.getBtnExit();

		btnPA.setOnAction(e -> {
			user.setScore(0);
			user.setAccuracy(100);
			mainMenu.showGame();
		});

		btnSB.setOnAction(e -> sb.show());
		btnExit.setOnAction(e -> System.exit(0));

		Text gameOverText = new Text("GAME OVER!");
		gameOverText.getStyleClass().add("label");
		Text scoreDisplay = new Text("YOUR SCORE: " + user.getScore());
		scoreDisplay.getStyleClass().add("label");

		gameOverLayout.getChildren().addAll(gameOverText, scoreDisplay, btnPA, btnSB, btnExit);

		Scene currentScene = primaryStage.getScene();

		if (currentScene == null) {
			currentScene = new Scene(gameScreen);
			primaryStage.setScene(currentScene);
		} else {
			currentScene.setRoot(gameScreen);
		}
		String css = getClass().getResource("/styles.css").toExternalForm();
		if (!currentScene.getStylesheets().contains(css)) {
			currentScene.getStylesheets().add(css);
		}

			Platform.runLater(() -> {
		});
	}
	
	public BorderPane getLayout() {
		return this.gameScreen;
	}
}
	


