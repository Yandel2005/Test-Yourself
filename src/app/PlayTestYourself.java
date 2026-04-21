package app;


import gameoutput.GameData;
import javafx.application.Application;
import javafx.stage.Stage;
import screens.LoginScreen;
import screens.MainMenu;

public class PlayTestYourself extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		GameData db = new GameData();
		new LoginScreen(primaryStage, db);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
