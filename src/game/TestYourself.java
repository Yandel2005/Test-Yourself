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
	private Button btnRestart;
	private Pane easyPane;
	private Random random = new Random();
	private int accuracy = 100;
	private Text accuracyText;
	private Timeline timeline;
	
	public TestYourself(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		gameScreen = new BorderPane();
	
		createButtons();
		styleButtons();
		createHeader();
		layoutComponents();
		createButtonListeners();
		showGame();
	}
	
	private void createButtons() {
		btnStart = new Button("START");
		btnSB = new Button("SCOREBOARD");
		btnExit = new Button("EXIT");
		btnRestart = new Button("RESTART");
		btnBack = new Button("BACK");
		btnCD = new Button("CHANGE DIFFICULTY");
		btnE = new Button("EASY");
		btnM = new Button("MEDIUM");
		btnH = new Button("HARD");
	}
	
	private void createButtonListeners() {
		btnStart.setOnAction(e -> showDifficultyScreen());
		btnBack.setOnAction(e -> showGame());
		btnExit.setOnAction(e -> System.exit(0));
		btnE.setOnAction(e -> showEasyMode());
		btnRestart.setOnAction(e -> showGame());
	}
	
	private void styleButtons() {
		Button[] buttons = { btnStart, btnSB, btnExit, btnCD, btnE, btnM, btnH };
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setFont(Font.font("Arial Black", 50));
			buttons[i].setStyle(
			"-fx-background-radius: 50;" + 
			"-fx-border-radius: 50;");
		}
		btnBack.setStyle("-fx-background-radius: 50;" + 
			"-fx-border-radius: 50;");
	}
	
	private void createHeader() {
		Text title = new Text("TEST YOURSELF");
		title.setFont(Font.font("Arial Black", 70));
		header = new HBox(title);
		header.setAlignment(Pos.CENTER);
		header.setStyle(
		"-fx-background-radius: 25;" + 
		"-fx-padding: 30;");
	}
	
	private void layoutComponents() {
		buttonDisplay = new VBox(btnStart, btnSB, btnExit);
		buttonDisplay.setAlignment(Pos.CENTER);
		buttonDisplay.setSpacing(100);
		gameScreen.setCenter(buttonDisplay);
		gameScreen.setTop(header);
	}

	private void createHUD() {
		accuracyText = new Text(accuracy + "%");
		accuracyText.setFont(Font.font("Arial Black", 30));
		accuracyText.setFill(Color.BLACK);
		HUD = new HBox(accuracyText);
		HUD.setAlignment(Pos.CENTER);
		HUD.setSpacing(50);
	}

	private void showEasyMode() {
		createHUD();
		easyPane = new Pane();
		spawnRandomSquare();
		gameScreen.setCenter(easyPane);
		gameScreen.setTop(HUD);
	}

	private void spawnRandomSquare() {
		
		int size = random.nextInt(100) + 25;
		Rectangle square = new Rectangle(size, size);
		square.setX(random.nextInt(1200));
		square.setY(random.nextInt(400));
		square.setFill(Color.color(random.nextDouble(),
		random.nextDouble(), random.nextDouble()));
		square.setOnMouseClicked(e -> { easyPane.getChildren().remove(square);
		accuracy += 5;
		if (accuracy > 100) accuracy = 100;
		accuracyText.setText(accuracy + "%");
		PauseTransition pause = new PauseTransition(Duration.millis(200));
		pause.setOnFinished(event -> spawnRandomSquare());
		pause.play();
		});
		
		easyPane.getChildren().add(square);
		
		PauseTransition despawn = new PauseTransition(Duration.millis(800));
		despawn.setOnFinished(e -> {
			if(easyPane.getChildren().contains(square)) {
				easyPane.getChildren().remove(square);
				accuracy -= 10;
				if(accuracy <= 0) {
				accuracy = 0;
				accuracyText.setText(accuracy + "%");
				showGameOver();
				} else {
					accuracyText.setText(accuracy + "%");
					spawnNextSquare(0);
			}}
		});
		despawn.play();
	}
	
	private void showGameOver() {
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

	private void spawnNextSquare(int delayMillis) {
	    PauseTransition pause = new PauseTransition(Duration.millis(delayMillis));
	    pause.setOnFinished(event -> spawnRandomSquare());
	    pause.play();
	}

	private void showGame() {
		gameScreen.setCenter(buttonDisplay);
		gameScreen.setTop(header);
		gameScreen.setBottom(null);
		Scene scene = new Scene(gameScreen);
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