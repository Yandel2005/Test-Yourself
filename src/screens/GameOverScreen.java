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

public class GameOverScreen {
	
	private Stage primaryStage;
	private Buttons buttons;
	private BorderPane gameScreen;

	public GameOverScreen(Stage primaryStage, Buttons buttons) {
		
		this.primaryStage = primaryStage;
		this.buttons = buttons;
		this.gameScreen = new BorderPane();
		
		VBox gameOverLayout = new VBox(buttons.getBtnSB(), buttons.getBtnExit());
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
	
	public void show() {
	    Scene scene = new Scene(gameScreen, 1200, 1200);
	    primaryStage.setScene(scene);
	    primaryStage.setMaximized(true);
	    primaryStage.show();
	}
}
	


