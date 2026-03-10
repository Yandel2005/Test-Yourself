package app;

import game.TestYourself;
import javafx.application.Application;
import javafx.stage.Stage;

public class PlayTestYourself extends Application{
	
	@Override
	public void start(Stage primaryStage) {
		new TestYourself(primaryStage);
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
