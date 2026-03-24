package screens;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mechanics.Buttons;
import users.User;

public class SelectionScreen {
	
	private BorderPane layout;
	private Buttons buttons;
	private Stage primaryStage;
	private HBox header;
	private User user;
	
	public SelectionScreen(Stage primaryStage, BorderPane mainMenuLayout, Buttons buttons, User user) {
		this.primaryStage = primaryStage;
		this.buttons = buttons;
		this.user = user;
		this.layout = new BorderPane();
		
		VBox difficultyLayout = new VBox(buttons.getBtnEasy(), buttons.getBtnMedium(), buttons.getBtnHard());
		
		buttons.getBtnEasy().setOnAction(e -> {
			int gameModeId = 1;
			GameScreen easyMode = new GameScreen(primaryStage, buttons, user, gameModeId);
			easyMode.showEasyMode();
			primaryStage.getScene().setRoot(easyMode.getLayout());
		});
		buttons.getBtnBack().setFont(Font.font("Arial Black", 20));
		buttons.getBtnBack().setOnAction(e -> {
			primaryStage.getScene().setRoot(mainMenuLayout);
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
		header = new HBox(title);
		header.setAlignment(Pos.CENTER);
	}
	
	public BorderPane getLayout() {
		return this.layout;
		
	}
}
