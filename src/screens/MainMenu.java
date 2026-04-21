package screens;

import gameoutput.GameData;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import users.User;

public class MainMenu {

	private GameData db;
	private ScoreboardScreen sbScreen;
	private BorderPane gameScreen;
	private HBox header;
	private VBox buttonDisplay;
	private Stage primaryStage;
	private Buttons buttons;
	private SelectionScreen selectionScreen;
	private User user;
	
	public MainMenu(Stage primaryStage, User user, GameData db) {
		this.primaryStage = primaryStage;
		this.gameScreen = new BorderPane();
		this.buttons = new Buttons();
		this.buttons.createButtons();
		this.db = db;
		this.user = user;

		this.selectionScreen = new SelectionScreen(primaryStage, this, buttons, this.user, db);
		this.sbScreen = new ScoreboardScreen(primaryStage, this, this.user, this.db, this.buttons);
		
		createHeader();
		layoutComponents();
		showGame();
	}

	public void showScoreBoard() {
		sbScreen.show();


	}

	private void createHeader() {
		Text title = new Text("TEST YOURSELF");
		title.getStyleClass().add("header-title");
		header = new HBox(title);
		header.setAlignment(Pos.CENTER);
	}
	
	private void layoutComponents() {
		buttonDisplay = new VBox(buttons.getBtnStart(), buttons.getBtnSB(), buttons.getBtnExit());
		buttonDisplay.setAlignment(Pos.CENTER);
		buttonDisplay.setSpacing(100);
		gameScreen.setCenter(buttonDisplay);
		gameScreen.setTop(header);
		buttons.getBtnSB().setOnAction(event -> primaryStage.getScene().setRoot(sbScreen.getLayout()));
		buttons.getBtnStart().setOnAction(e -> { primaryStage.getScene().setRoot(selectionScreen.getLayout());
	});
		
		}
	
	public void showGame() {

		user.setScore(0);
		user.setAccuracy(100);
		buttonDisplay.getChildren().clear();
		Button startBtn = buttons.getBtnStart();
		Button sbBtn = buttons.getBtnSB();
		Button exitBtn = buttons.getBtnExit();
		buttonDisplay.getChildren().addAll(startBtn, sbBtn, exitBtn);

		gameScreen.getStyleClass().add("mainRoot");
		gameScreen.setCenter(buttonDisplay);
		gameScreen.setTop(header);
		gameScreen.setBottom(null);


		String css = getClass().getResource("/styles.css").toExternalForm();
			primaryStage.getScene().getStylesheets().add(css);
			primaryStage.getScene().setRoot(gameScreen);

	}

	public BorderPane getLayout() {
		return this.gameScreen;
	}

}
