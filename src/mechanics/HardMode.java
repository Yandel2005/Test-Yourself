package mechanics;

import interfaces.GameMode;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import screens.GameScreen;
import users.User;

public class HardMode implements GameMode {
	
	public static final int ID = 3;
	private String targetColor;
	private boolean roundActive = false;
	private int roundsSinceLastTyping = 0;
	
	private String[] longWords = {
			"POLYMORPHISM", "ENCAPSULATION", "SYNCHRONIZED",
			"INHERITANCE", "ABSTRACTION", "INSTANTIATE",
			"PHENOMENON", "LABYRINTHINE", "QUINTESSENTIAL",
			"METAMORPHOSIS", "UNFATHOMABLE", "OSCILLATION"};
	
	
	@Override
	public int getGameModeId() {
		return ID;
	}
	
	@Override
	public void start(GameScreen screen, GameSquare square, User user) {
		if(this.roundActive) return;
		this.roundActive = true;
		screen.getGamePane().getChildren().clear();
		
		roundsSinceLastTyping++;
		
		if (roundsSinceLastTyping >= 2 && Math.random() > 0.5) {
			this.roundActive = false;
			String word = longWords[(int)(Math.random() * longWords.length)];
			screen.initiateTypingChallenge(word);
			roundsSinceLastTyping = 0;
        return;
		}
		screen.getGamePane().getChildren().clear();
		this.targetColor = GameSquare.COLORS[(int)(Math.random() * 4)];
		screen.setInstruction("QUICK! CLICK THE " + targetColor + " SQUARES!");
		
		int targetIndex = (int)(Math.random() * 4);
		for(int i = 0; i < 4; i++) {
			GameSquare newSquare = new GameSquare(screen.getGamePane(), screen, user);
			newSquare.modifySquare(35, 900, 150);
			
			if (i == targetIndex) {
				newSquare.spawnSpecificSquare(targetColor);
			} else {
			newSquare.spawnSpecificSquare(getUniqueColor(targetColor));
			}
		}
	}
	
	private void triggerTypingEvent(GameScreen screen, User user) {
        String word = longWords[(int) (Math.random() * longWords.length)];
        
        screen.initiateTypingChallenge(word);
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
        roundActive = false;
        square.stopTimer();
        
        if (square.getColorName().equals(this.targetColor)) {
            user.setScore(user.getScore() + 500); // More points for Hard Mode!
            user.setAccuracy(user.getAccuracy() + 2); // Harder to gain accuracy
        } else {
           user.setAccuracy(Math.max(0, user.getAccuracy() - 25));
         }	
       nextRound(screen, square, user);
     }
	
   @Override
   public void onSquareMiss(GameScreen screen, GameSquare square, User user) {
       if (!roundActive) return;
       roundActive = false;
       
       user.setAccuracy(Math.max(0, user.getAccuracy() - 25));
       nextRound(screen, square, user);
   }
   
   private void nextRound(GameScreen screen, GameSquare square, User user) {
       screen.getGamePane().getChildren().clear();
       
       PauseTransition pause = new PauseTransition(Duration.millis(150));
       pause.setOnFinished(e -> {
           this.roundActive = false; // Unlock round right before starting the next one
           start(screen, new GameSquare(screen.getGamePane(), screen, user), user);
       });
       pause.play();
   }
   
   private String pickRandomColor() {
       return GameSquare.COLORS[(int)(Math.random() * GameSquare.COLORS.length)];
   }
}

	
