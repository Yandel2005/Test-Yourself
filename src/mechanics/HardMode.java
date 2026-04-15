package mechanics;

import java.util.Random;

import mechanics.typeHelper;
import interfaces.GameMode;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import screens.GameScreen;
import users.User;


public class HardMode implements GameMode {

	typeHelper helper = new typeHelper();
	Random random = new Random();
	private int roundNumber = 0;
	private String chosenWord;
	public static final int ID = 3;
	private String targetColor;
	private boolean challenge = false;
	
	@Override
	public int getGameModeId() {
		return ID;
	}
	
	@Override
	public void start(GameScreen screen, GameSquare square, User user) {
		
		this.targetColor = GameSquare.COLORS[(int)(Math.random() * 4)];
		screen.setInstruction("CLICK THE " + targetColor + " SQUARE!");
		
		int targetIndex = (int) (Math.random() * 4);
		
		for (int i = 0; i < 4; i++) {
			GameSquare newSquare = new GameSquare(screen.getGamePane(), screen, user);
			newSquare.modifySquare(50,  700, 100);
			
			if (i == targetIndex) {
				newSquare.spawnSpecificSquare(targetColor);
			} else {
				newSquare.spawnSpecificSquare(getUniqueColor(targetColor));
			}
		}
		}
	
	
	private String getUniqueColor(String target) {
		String decoy = pickRandomColor();
		while (decoy.equals(target)) {
			decoy = pickRandomColor();
		}

		return decoy;
	}	
	
   @Override
    public void onSquareClick(GameScreen screen, GameSquare square, User user) {
		
		if (square.getColorName().equals(this.targetColor)) {
			user.setScore(user.getScore() + 200);
			user.setAccuracy(user.getAccuracy() + 5);
		} else {
			user.setAccuracy(Math.max(0, user.getAccuracy() - 20));
		}	
		nextRound(screen, square, user);
     }
	
   @Override
   public void onSquareMiss(GameScreen screen, GameSquare square, User user) {
		square.stopTimer();
		user.setAccuracy(user.getAccuracy() - 20);
		nextRound(screen, square, user);
   }
   
   private void nextRound(GameScreen screen, GameSquare square, User user) {
	   roundNumber++;
		screen.getLayout().getChildren().clear();
        
        int randomNumber = random.nextInt(4) + 1;
        if (roundNumber == randomNumber) {
        	challenge = true;
        	helper.startTypingChallenge(screen, square, user);
			PauseTransition pause = new PauseTransition(Duration.millis(5000));
			pause.setOnFinished(e -> start(screen, square, user));
			pause.play();
        	return;

        }

	}
   
	private String pickRandomColor() {
		int index = (int)(Math.random() * GameSquare.COLORS.length);
				return GameSquare.COLORS[index];
	}
}

	
