package mechanics;

import interfaces.GameMode;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import screens.GameScreen;
import users.User;

public class MediumMode implements GameMode {
	public static final int ID = 2;
	private String targetColor;
	private boolean roundActive = false;

	@Override
	public int getGameModeId() {
		return ID;
	}
	
	@Override 
	public void start(GameScreen screen, GameSquare square, User user) {
		if (roundActive) return;
		roundActive = true;
		this.targetColor = GameSquare.COLORS[(int)(Math.random() * 4)];
		screen.setInstruction("CLICK THE " + targetColor + " SQUARE!");
		
		int targetIndex = (int) (Math.random() * 4);
		
		for (int i = 0; i < 4; i++) {
			GameSquare newSquare = new GameSquare(screen.getGamePane(), screen, user);
			newSquare.modifySquare(50,  1500, 300);
			
			if (i == targetIndex) {
				newSquare.spawnSpecificSquare(targetColor);
			} else {
				newSquare.spawnSpecificSquare(getUniqueColor(targetColor));
			}
		}
		
	}
	
	private String pickRandomColor() {
		int index = (int)(Math.random() * GameSquare.COLORS.length);
				return GameSquare.COLORS[index];
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
		if(!roundActive) return;
		
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
		if (!roundActive) return;
		square.stopTimer();
		
		user.setAccuracy(user.getAccuracy() - 20);
		nextRound(screen, square, user);
	}
	private void nextRound(GameScreen screen, GameSquare square, User user) {
		roundActive = false;
		screen.getGamePane().getChildren().clear();
		PauseTransition pause = new PauseTransition(Duration.millis(300));
        pause.setOnFinished(e -> start(screen, square, user));
        pause.play();
	}

}

