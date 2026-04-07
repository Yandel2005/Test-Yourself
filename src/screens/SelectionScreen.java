package screens;

import gameoutput.GameData;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import mechanics.EasyMode;
import mechanics.HardMode;
import mechanics.MediumMode;
import users.User;

public class SelectionScreen {
	
	private GameData db;
	private BorderPane layout;
	private Buttons buttons;
	private Stage primaryStage;
	private HBox header;
	private User user;
	private MainMenu mainMenu;
	
	public SelectionScreen(Stage primaryStage, MainMenu mainMenu, Buttons buttons, User user, GameData db) {
		this.primaryStage = primaryStage;
		this.buttons = buttons;
		this.user = user;
		this.layout = new BorderPane();
		this.db = db;
		this.mainMenu = mainMenu;
		
		VBox difficultyLayout = new VBox(buttons.getBtnEasy(), buttons.getBtnMedium(), buttons.getBtnHard());
		
		buttons.getBtnEasy().setOnAction(e -> launchGame(EasyMode.ID));
		buttons.getBtnMedium().setOnAction(e -> launchGame(MediumMode.ID));
		buttons.getBtnHard().setOnAction(e -> launchGame(HardMode.ID));
		buttons.getBtnBack().setOnAction(e -> {
			this.mainMenu.showGame();
		});
		
		createHeader();
		difficultyLayout.setAlignment(Pos.CENTER);
		difficultyLayout.setSpacing(50);
		layout.setTop(header);
		layout.setCenter(difficultyLayout);
		layout.setBottom(buttons.getBtnBack());
		
	}
		
	private void createHeader() {
		Text title = new Text("CHOOSE YOUR DIFFICULTY.");
		title.getStyleClass().add("header-title");
		header = new HBox(title);
		header.setAlignment(Pos.CENTER);
	}
	
	public BorderPane getLayout() {
		return this.layout;
		
	}
	
	private void launchGame(int modeId) {
		GameScreen game = new GameScreen(primaryStage, this.mainMenu, buttons, user, modeId, this.db);
		primaryStage.getScene().setRoot(game.getLayout());
		game.startGame();
	}
}
