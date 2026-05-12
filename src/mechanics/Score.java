package mechanics;

import users.User;

public class Score {
	private int value;
	private User user;
	private int gameModeId;
	private double reactionTime;
	
	public Score(User user, int value, int gameModeId, double reactionTime) {
		this.user = user;
		this.value = value;
		this.gameModeId = gameModeId;
		this.reactionTime = reactionTime;
	}


	public int getValue() {
		return this.value;
	}

	public User getUser() {
		return this.user;
	}
	
	public int getGameModeId() {
		return this.gameModeId;
	}

	public double getReactionTime() {
		return this.reactionTime;
	}

}
