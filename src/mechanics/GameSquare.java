package mechanics;

import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import screens.GameOverScreen;
import screens.GameScreen;
import users.User;

import java.util.Random;

public class GameSquare {
	
	private Random random = new Random();
	private Pane gamePane;
	private GameScreen gameScreen;
	
	public GameSquare(Pane gamePane, GameScreen gameScreen) {
		this.gamePane = gamePane;
		this.gameScreen = gameScreen;
	}
	

	public void spawnRandomSquare(int sizeLimit, int timeLimit) {
		
		int size = random.nextInt(sizeLimit + 20);
		Rectangle square = new Rectangle(size, size);
		square.setX(random.nextInt(1000));
		square.setY(random.nextInt(400));
		square.setFill(Color.color(random.nextDouble(),
		random.nextDouble(), random.nextDouble()));
		
		square.setOnMouseClicked(e -> { gamePane.getChildren().remove(square);
		gameScreen.squareClicked();
		spawnNextSquare(500, sizeLimit, timeLimit);
		});
		
		gamePane.getChildren().add(square);
		
		PauseTransition despawn = new PauseTransition(Duration.millis(timeLimit));
		despawn.setOnFinished(e -> {
			if(gamePane.getChildren().contains(square)) {
				gameScreen.squareMissed();
				gamePane.getChildren().remove(square);
				gameScreen.squareMissed();
				spawnNextSquare(500, sizeLimit, timeLimit);
			}
		});
		despawn.play();
	}
	
	private void spawnNextSquare(int delayMillis, int sizeLimit, int timeLimit) {
	    PauseTransition pause = new PauseTransition(Duration.millis(delayMillis));
	    pause.setOnFinished(event -> spawnRandomSquare(sizeLimit, timeLimit));
	    pause.play();
	}

}