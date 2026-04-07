package screens;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import users.User;

public class GameOverScreen {
	
	private Stage primaryStage;
	private Buttons buttons;
	private BorderPane gameScreen;
	private User user;
	private MainMenu mainMenu;

	public GameOverScreen(Stage primaryStage,  MainMenu mainMenu, Buttons buttons) {
		this.mainMenu = mainMenu;
		this.primaryStage = primaryStage;
		this.buttons = buttons;
		this.gameScreen = new BorderPane();
		
		buttons.getBtnPA().setOnAction(e -> {
		mainMenu.showGame();
		});
		
		
		
		VBox gameOverLayout = new VBox(buttons.getBtnSB(), buttons.getBtnExit(), buttons.getBtnPA());
		gameOverLayout.setAlignment(Pos.CENTER);
		gameOverLayout.setSpacing(50);
		
		Text gameOverText = new Text("GAME OVER");
		gameOverText.getStyleClass().add("header-title");
		HBox topBox =  new HBox(gameOverText);
		
		topBox.setAlignment(Pos.CENTER);
		gameScreen.setCenter(gameOverLayout);
		gameScreen.setTop(topBox);
	}
	
	public void show() {
	    Scene scene = new Scene(gameScreen, primaryStage.getWidth(), primaryStage.getHeight());
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public BorderPane getLayout() {
		return this.gameScreen;
	}
}
	


