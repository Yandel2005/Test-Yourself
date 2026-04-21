package game;

import java.util.Random;

import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import mechanics.GameSquare;

public class TestYourself {
	
	private BorderPane gameScreen;
	private VBox buttonDisplay;
	private HBox HUD;
	private HBox header;
	private Stage primaryStage;
	private Button btnStart;
	private Button btnExit;
	private Button btnBack;
	private Button btnSB;
	private Button btnCD;
	private Button btnE;
	private Button btnM;
	private Button btnH;
	private Timeline timeline;
	
	public TestYourself(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		gameScreen = new BorderPane();
	
		createButtons();
		createHeader();
		layoutComponents();
		createButtonListeners();
		showGame();
	}
	
	private void createButtons() {
		btnStart = new Button("START");
		btnSB = new Button("SCOREBOARD");
		btnExit = new Button("EXIT");
		btnBack = new Button("BACK");
		btnE = new Button("EASY");
		btnM = new Button("MEDIUM");
		btnH = new Button("HARD");
	}
	
	private void createButtonListeners() {
		btnStart.setOnAction(e -> showDifficultyScreen());
		btnBack.setOnAction(e -> showGame());
		btnExit.setOnAction(e -> System.exit(0));
	}

	private void createHeader() {
		Text title = new Text("TEST YOURSELF");
		title.getStyleClass().add("header-title");
		header = new HBox(title);
		header.setAlignment(Pos.TOP_CENTER);
	}
	
	private void layoutComponents() {
		buttonDisplay = new VBox(btnStart, btnSB, btnExit);
		buttonDisplay.setAlignment(Pos.CENTER);
		buttonDisplay.setSpacing(100);
		gameScreen.setCenter(buttonDisplay);
		gameScreen.setTop(header);
	}
	
	public void showGameOver() {
		VBox gameOverLayout = new VBox(btnSB, btnExit);
		gameOverLayout.setAlignment(Pos.CENTER);
		gameOverLayout.setSpacing(50);
		Text gameOverText = new Text("GAME OVER");
		gameOverText.setFont(Font.font("Arial Black", 80));
		gameOverText.setFill(Color.RED);
		HBox topBox =  new HBox(gameOverText);
		topBox.setAlignment(Pos.CENTER);
		gameScreen.setCenter(gameOverLayout);
		gameScreen.setTop(topBox);
	}


	private void showGame() {
		gameScreen.setCenter(buttonDisplay);
		gameScreen.setTop(header);
		gameScreen.setBottom(null);
		Scene scene = new Scene(gameScreen);
		scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
		
	}
	
	private void showDifficultyScreen() {
		VBox difficultyLayout = new VBox(btnE, btnM, btnH);
		difficultyLayout.setAlignment(Pos.CENTER);
		difficultyLayout.setSpacing(50);
		btnBack.setFont(Font.font("Arial Black", 20));
		gameScreen.setCenter(difficultyLayout);
		gameScreen.setBottom(btnBack);	
	}

}