package mechanics;

import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import screens.GameScreen;
import users.User;

import java.util.Random;

public class GameSquare {
	
	private PauseTransition despawn;
	private String currentColorName;
	public static final String[] COLORS = {"RED", "BLUE", "GREEN", "YELLOW"};
	private Random random = new Random();
	private Pane gamePane;
	private GameScreen gameScreen;
	private int sizeLimit;
	private int timeLimit;
	private int delay;
	public User user;
	
	public GameSquare(Pane gamePane, GameScreen gameScreen, User user) {
		this.gamePane = gamePane;
		this.gameScreen = gameScreen;
		this.user = user;
	}
	
	public void modifySquare(int sizeLimit, int timeLimit, int delay) {
		this.sizeLimit = sizeLimit;
		this.timeLimit = timeLimit;
		this.delay = delay;
	}

	public void spawnRandomSquare() {
		int size = random.nextInt(sizeLimit) + 20;
		
		Rectangle square = new Rectangle(size, size);
		
		double currentWidth = gamePane.getWidth();
	    double currentHeight = gamePane.getHeight();
	    if (currentWidth <= 0) currentWidth = 800;
	    if (currentHeight <= 0) currentHeight = 600;
		int maxX = (int) (gamePane.getWidth() - size);
	    int maxY = (int) (gamePane.getHeight() - size);
	    if (maxX > 0) {
	        square.setX(random.nextInt(maxX));
	    } else {
	        square.setX(random.nextInt(400));
	    }
	    if (maxY > 0) {
	        square.setY(random.nextInt(maxY));
	    } else {
	        square.setY(random.nextInt(400)); 
	    }

		square.setFill(Color.color(random.nextDouble(),
		random.nextDouble(), random.nextDouble()));
		
		PauseTransition despawn = new PauseTransition(Duration.millis(timeLimit));
		
		square.setOnMouseClicked(e -> { 
		
		gamePane.getChildren().remove(square);
		gameScreen.squareClicked(this);
		spawnNextSquare();
		});
		
		gamePane.getChildren().add(square);
		
		despawn.setOnFinished(e -> {
			if(gamePane.getChildren().contains(square)) {
				gamePane.getChildren().remove(square);
				gameScreen.squareMissed(this);
				
			}
		});
		despawn.play();
	}
	
	private void spawnNextSquare() {
	    PauseTransition pause = new PauseTransition(Duration.millis(delay));
	    pause.setOnFinished(event -> spawnRandomSquare());
	    pause.play();
	}

	public void spawnSpecificSquare(String colorName) {
		this.currentColorName = colorName;
		
		int size = random.nextInt(sizeLimit) + 20;
		Rectangle square = new Rectangle(size, size);

		switch (colorName) {
        case "RED":    square.setFill(Color.RED); break;
        case "BLUE":   square.setFill(Color.BLUE); break;
        case "GREEN":  square.setFill(Color.GREEN); break;
        case "YELLOW": square.setFill(Color.YELLOW); break;
        default:       square.setFill(Color.BLACK); break;
    }
		
		double currentWidth = gamePane.getWidth();
	    double currentHeight = gamePane.getHeight();
	    if (currentWidth <= 0) currentWidth = 800;
	    if (currentHeight <= 0) currentHeight = 600;
		int maxX = (int)(gamePane.getWidth() - size);
		int maxY = (int)(gamePane.getHeight() - size);
				if (maxX > 0) {
					square.setX(random.nextInt(maxX));
				} else {
					square.setX(random.nextInt(400));
				}
		if (maxY > 0) {
			square.setY(random.nextInt(maxY));
		} else {
			square.setY(random.nextInt(400));
		}
		
		despawn = new PauseTransition(Duration.millis(timeLimit));
		
	    square.setOnMouseClicked(e -> {
	    	despawn.stop();
	        gamePane.getChildren().remove(square);
	        gameScreen.squareClicked(this);
	    });
	    despawn.setOnFinished(e -> {
	        if (gamePane.getChildren().contains(square)) {
	            gamePane.getChildren().remove(square);
	            gameScreen.squareMissed(this);
	        }
	    });
	    gamePane.getChildren().add(square);
	    despawn.play();
	
	}

	public String getColorName() {
		return this.currentColorName;
	}
	
	public void stopTimer() {
		if (despawn!= null) {
			despawn.stop();
		}
	}
	}