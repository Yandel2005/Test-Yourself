package screens;

import gameoutput.GameData;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import mechanics.GameSquare;
import mechanics.Score;
import users.User;

public class GameScreen {
	
	private boolean isGameOver = false;
	private int currentGameModeId;
	private HBox header;
	private BorderPane gameScreen;
	private Pane gamePane;
	private Stage stage;
	private Text accuracyText;
	private Text scoreText;
	private int accuracy;
	private Buttons buttons;
	private User user;
	
	public GameScreen(Stage stage, Buttons buttons, User user, int gameModeId) {
		this.stage = stage;
		this.buttons = buttons;
		this.user = user;
		this.currentGameModeId = gameModeId;
	
		accuracy = 100;
		
		gameScreen = new BorderPane();
		gamePane = new Pane();
		
		accuracyText = new Text("Accuracy: " + accuracy + "%");
		scoreText = new Text("Score: " + user.getScore());
		
		createHeader();
		
		gameScreen.setTop(header);
		gameScreen.setCenter(gamePane);
		
	}
	
	private void createHeader() {
		HBox titleBox = new HBox(50, accuracyText, scoreText);
		titleBox.setAlignment(Pos.CENTER);
		header = titleBox;
		header.getStyleClass().add("header-box");
	}
	
	 public void showEasyMode() {
		 gamePane.getChildren().clear();
		 GameSquare square = new GameSquare(gamePane, this);
		 square.spawnRandomSquare(200, 1200);
	
		}
	 
	 public void squareClicked() {
		accuracy += 10;
		if (accuracy > 100) {
			accuracy = 100;
		}
		accuracyText.setText("Accuracy: " + accuracy + "%");
		
		user.setScore(user.getScore() + 100);
		scoreText.setText("Score: " + user.getScore());
	 }
	 
	 public void squareMissed() {
		 if(isGameOver) return;
		 accuracy -= 10;
		 if (accuracy <= 0)
			 accuracy = 0;
		 	 accuracyText.setText("Accuracy: " + accuracy + "%");
		 
		 if(accuracy == 0) {
			 isGameOver = true;
			 GameOverScreen gameOver = new GameOverScreen(stage, buttons);
			 gameOver.show();
			 GameData db = new GameData();
			 Score finalScore = new Score(user, user.getScore(), currentGameModeId);
			 db.saveScore(finalScore);
		
		 }
	 }

	 
	 public BorderPane getLayout() {
		 return gameScreen;
	 }
	 
	 
}