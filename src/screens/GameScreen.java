package screens;

import gameoutput.GameData;
import interfaces.GameMode;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import mechanics.EasyMode;
import mechanics.GameSquare;
import mechanics.HardMode;
import mechanics.MediumMode;
import mechanics.Score;
import users.User;

public class GameScreen {
	
	private Boolean isGameOver;
	private Label wordToType;
	private TextField inputField;
	private String currentTargetWord;
	private GameData db;
	private GameMode gameMode;
	private int gameModeId;
	private HBox header;
	private BorderPane gameScreen;
	private Pane gamePane;
	private Stage stage;
	private Text instructionText;
	private Text accuracyText;
	private Text scoreText;
	private Buttons buttons;
	private User user;
	private VBox typingContainer;
	private BorderPane mainMenuLayout;
	private MainMenu mainMenu;
	
	
	public GameScreen(Stage stage, MainMenu mainMenu, Buttons buttons, User user, int gameModeId, GameData db) {
		this.stage = stage;
		this.buttons = buttons;
		this.user = user;
		this.gameModeId = gameModeId;
		this.db = db;
		this.mainMenu = mainMenu;
		
		if (gameModeId == 1) {
			gameMode = new EasyMode();
		} else if (gameModeId == 2) {
			gameMode = new MediumMode();
		} else if (gameModeId == 3) {
			gameMode = new HardMode();
		}
		
		gameScreen = new BorderPane();
		gamePane = new Pane();
		isGameOver = false;
		instructionText = new Text("");
		instructionText.getStyleClass().add("instructions");
		accuracyText = new Text("Accuracy: 100%");
		accuracyText.getStyleClass().add("accuracy");
		scoreText = new Text("Score: " + user.getScore());
		scoreText.getStyleClass().add("score");
		createHeader();
		
		gameScreen.setTop(header);
		gameScreen.setCenter(gamePane);
		
		
		
	}
	
	public void startGame() {
		gamePane.getChildren().clear();
		GameSquare square = new GameSquare(gamePane, this, this.user);
		if (gamePane.getWidth() <= 0) {
			gamePane.widthProperty().addListener((obs, oldVal, newVal) -> {
				if (newVal.doubleValue() > 0 && gamePane.getChildren().isEmpty()) {
					gameMode.start(this, square, this.user);
				}
			});
		} else {
		gameMode.start(this, square, this.user);
	}
}
	private void createHeader() {
		HBox titleBox = new HBox(50, accuracyText, instructionText, scoreText);
		titleBox.setAlignment(Pos.CENTER);
		header = titleBox;
		header.getStyleClass().add("header-box");
	}
	 
	 public void squareMissed(GameSquare square) {
		 if(isGameOver) return;
		gameMode.onSquareMiss(this, square, this.user);
		accuracyText.setText("Accuracy: " + user.getAccuracy() + "%");
		if (user.getAccuracy() <= 0) {
			endGame();
		}
		 }
	 
	 public void squareClicked(GameSquare square) {
		 gameMode.onSquareClick(this, square, this.user);
		 scoreText.setText("Score: " + user.getScore());
		 accuracyText.setText("Accuracy: " + user.getAccuracy() + "%");
	 }
	 
	 public BorderPane getLayout() {
		 return gameScreen;
	 }
	 
	 public void endGame() {

		 if(isGameOver) return;
		 isGameOver = true;
		 gamePane.getChildren().clear();
		 user.updateHighScore();
		 Score finalScore = new Score(this.user, this.user.getScore(), this.gameMode.getGameModeId());
		 if (db != null) {
		        db.saveScore(finalScore, finalScore.getGameModeId());
		    } else {
		        System.out.println("Database connection missing!");
		    }
		 GameOverScreen gameOver = new GameOverScreen(stage, this.mainMenu, buttons);
		    gameOver.show();
		 }
	

	public Pane getGamePane() {
		return this.gamePane;
	}
	
	public void setInstruction(String message) {
		if (instructionText != null) {
            instructionText.setText(message);
	}
	 
}
}