package app;


import javafx.application.Application;
import javafx.stage.Stage;
import screens.MainMenu;

public class PlayTestYourself extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		new MainMenu(primaryStage);
		
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
