package mechanics;

import interfaces.GameMode;
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
		int currentScore = user.getScore();
		int currentAccuracy = user.getAccuracy();
		user.setScore(currentScore + 100);
		currentAccuracy = Math.min(100, currentAccuracy + 10);
		user.setAccuracy(currentAccuracy);
	}
	
	@Override
	public void onSquareMiss(GameScreen screen, GameSquare square, User user) {
		int currentAccuracy = user.getAccuracy();
		currentAccuracy = Math.min(100, currentAccuracy - 10);
		user.setAccuracy(currentAccuracy);
		square.spawnRandomSquare();
		
	}

}
