package interfaces;

import mechanics.GameSquare;
import screens.GameScreen;
import users.User;

public interface GameMode {
	
	void start(GameScreen screen, GameSquare square, User user);
	
	void onSquareClick(GameScreen screen, GameSquare square, User user);
	
	void onSquareMiss(GameScreen screen, GameSquare square, User user);
	
	int getGameModeId();
	
	default String getInstructionText() {
		return "";
	}
	
}


