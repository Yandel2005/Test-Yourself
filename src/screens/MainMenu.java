package screens;

import gameoutput.GameData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import users.User;


public class MainMenu {
	
	private GameData db;
	private GameScreen screen;
	private ScoreboardScreen sbScreen;
	private BorderPane gameScreen;
	private HBox header;
	private VBox buttonDisplay;
	private Stage primaryStage;
	private Buttons buttons;
	private SelectionScreen selectionScreen;
	private User user;
	
	public MainMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.gameScreen = new BorderPane();
		this.buttons = new Buttons();
		this.buttons.createButtons();

		this.db = new GameData();
		this.user = db.getUserByUsername("Player1");
		if(user == null) {
			user = new User(0, "Player1");
			int generatedId = db.insertUser(user);
			user.setUserId(generatedId);
		}
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
		Scene scene = new Scene(gameScreen);
		gameScreen.setCenter(buttonDisplay);
		gameScreen.setTop(header);
		gameScreen.setBottom(null);
		scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
		primaryStage.setScene(scene);

		if (!primaryStage.isMaximized()) {
			primaryStage.setMaximized(true);
			primaryStage.show();

	    }
		
	}
	
	

}
