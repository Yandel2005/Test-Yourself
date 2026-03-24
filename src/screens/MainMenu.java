package screens;

import java.net.URL;

import gameoutput.GameData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import users.User;


public class MainMenu {
	
	
	private BorderPane gameScreen;
	private HBox header;
	private VBox buttonDisplay;
	private Stage primaryStage;
	private Buttons buttons;
	private SelectionScreen selectionScreen;
	
	public MainMenu(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.gameScreen = new BorderPane();
		this.buttons = new Buttons();
		this.buttons.createButtons();
	
		GameData db = new GameData();
		User user = db.getUserByUsername("Player1");
		
		if(user == null) {
			user = new User(0, "Player1");
			int generatedId = db.insertUser(user);
			user.setUserId(generatedId);
		}
		this.selectionScreen = new SelectionScreen(primaryStage, gameScreen, buttons, user);
		
		createHeader();
		layoutComponents();
		showGame();
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
		
		buttons.getBtnStart().setOnAction(e -> { primaryStage.getScene().setRoot(selectionScreen.getLayout());
	});
		
		}
	
	public void showGame() {
		gameScreen.setCenter(buttonDisplay);
		gameScreen.setTop(header);
		gameScreen.setBottom(null);
		
		Scene scene = new Scene(gameScreen);
	
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.show();
		
	}
	
	

}
