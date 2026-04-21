package modes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import interfaces.GameMode;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import mechanics.GameSquare;
import mechanics.TypeHelper;
import screens.GameScreen;
import users.User;

public class HardMode implements GameMode {

	private List<GameSquare> activeSquares = new ArrayList<>();
	private PauseTransition challengeTimer;
	TypeHelper helper = new TypeHelper();
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
		screen.getGamePane().getChildren().clear();

		this.targetColor = GameSquare.COLORS[(int)(Math.random() * 4)];
		screen.setInstruction("CLICK THE " + targetColor + " SQUARE!");
		
		int targetIndex = (int) (Math.random() * 4);

		for (int i = 0; i < 4; i++) {
           GameSquare newSquare = new GameSquare(screen.getGamePane(), screen, user);
            activeSquares.add(newSquare);
			newSquare.modifySquare(50,  1100, 100);
			
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
			square.stopTimer();
			clearCurrentRound();
			screen.getGamePane().getChildren().clear();
			user.setScore(user.getScore() + 200);
			user.setAccuracy(user.getAccuracy() + 10);
			nextRound(screen, square, user);
		} else {
            onSquareMiss(screen, square, user);
        }
     }
	
   @Override
   public void onSquareMiss(GameScreen screen, GameSquare square, User user) {
		square.stopTimer();
		user.setAccuracy(user.getAccuracy() - 20);
	   nextRound(screen, square, user);
   }
   
   private void nextRound(GameScreen screen, GameSquare square, User user) {
		roundNumber++;

        int randomNumber = random.nextInt(3) + 1;
		int randomNumber2 = random.nextInt(3) + 1;
        if (randomNumber == randomNumber2 && roundNumber > 1) {
			challenge = true;
			screen.getGamePane().getChildren().clear();
			helper.startTypingChallenge(screen, square, user, this);
			challengeTimer = new PauseTransition(Duration.millis(5000));challengeTimer.setOnFinished(e -> start(screen, square, user));
			challengeTimer.play();
		} else {
			start(screen, square, user);
        }
	}
   
	private String pickRandomColor() {
		int index = (int)(Math.random() * GameSquare.COLORS.length);
				return GameSquare.COLORS[index];
	}

	public void stopChallengeTimer() {
		if (challengeTimer != null) {
			challengeTimer.stop();
		}
	}

	private void clearCurrentRound() {
		for (int i = 0; i < activeSquares.size(); i++) {
			GameSquare currentSquare = activeSquares.get(i);
			currentSquare.stopTimer();
		}
		activeSquares.clear();
	}
}

	
