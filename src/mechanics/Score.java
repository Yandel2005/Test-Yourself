package mechanics;

import users.User;

public class Score {
	private int id;
	private int value;
	private User user;
	private int gameModeId;
	
	public Score(User user, int value, int gameModeId) {
		this.user = user;
		this.value = value;
		this.gameModeId = gameModeId;
	}
	
	public Score(int id, User user, int value, int gameModeId) {
		this.id = id;
		this.user = user;
		this.value = value;
		this.gameModeId = gameModeId; 
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}
	
	public int getGameModeId() {
		return this.gameModeId;
	}
	
	public void setGameModeId(int gameModeId) {
		this.gameModeId = gameModeId;
	}

}
