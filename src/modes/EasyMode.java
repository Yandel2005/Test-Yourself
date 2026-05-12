package modes;

import interfaces.GameMode;
import mechanics.GameSquare;
import screens.GameScreen;
import users.User;

public class EasyMode implements GameMode {
	public static final int ID = 1;

	
	@Override
	public int getGameModeId() {
		return ID;
	}
	
	@Override 
	public void start(GameScreen screen, GameSquare square, User user) {
		square.modifySquare(100, 800, 300);
		square.spawnRandomSquare();
	}
	
	@Override
	public void onSquareClick(GameScreen screen, GameSquare square, User user) {
		user.setScore(user.getScore() + 100);
		user.setAccuracy(Math.min(100, user.getAccuracy() + 10));
		nextRound(screen, square, user);
	}
	
	@Override
	public void onSquareMiss(GameScreen screen, GameSquare square, User user) {
		user.setAccuracy(Math.max(0, user.getAccuracy() - 10));

		nextRound(screen, square, user);
		
	}

	public void nextRound(GameScreen screen, GameSquare square, User user) {
		mechanics.SoundManager.playNextRoundSound();
	}
}
