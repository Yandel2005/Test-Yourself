package modes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import interfaces.GameMode;
import javafx.animation.PauseTransition;
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
	public static final int ID = 3;
	private String targetColor;
	private boolean challenge = false;
	private double challengeChance;
	
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
			user.setScore(user.getScore() + 100);
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
   @Override
   public void nextRound(GameScreen screen, GameSquare square, User user) {
	mechanics.SoundManager.playNextRoundSound();
		roundNumber++;
		challengeChance = random.nextDouble();

		if(roundNumber >= 2 && challengeChance < 0.30) {
			challenge = true;
			roundNumber = 0;

			screen.getGamePane().getChildren().clear();
			helper.startTypingChallenge(screen, user, this);
		} else {
			start(screen, square, user);
		}
	}
   
	private String pickRandomColor() {
		int index = (int)(Math.random() * GameSquare.COLORS.length);
				return GameSquare.COLORS[index];
	}

	public void startChallengeTimer(GameScreen screen) {
		challengeTimer = new PauseTransition(javafx.util.Duration.seconds(4));
		challengeTimer.setOnFinished(e -> {
			screen.endGame();
		});
		challengeTimer.play();
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

	
