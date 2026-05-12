package users;

public class User {

	private int userId;
	private String username;
	private String password;
	private int highscore;
	private int score = 0;
	private int accuracy = 100;
	private String modeName;
	private double totalReactionTime = 0;
	private int clicks = 0;

	public User(int userId, String username, String password) {
		this.username = username;
		this.userId = userId;
		this.password = password;
	}

	public User(String username) {
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
	}

	public String getUsername() {
		return username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void updateHighScore() {
		if (this.score > this.highscore) {
			this.highscore = this.score;
		}
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		if (accuracy > 100) this.accuracy = 100;
		else if (accuracy < 0) this.accuracy = 0;
		else this.accuracy = accuracy;
	}


	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public String getModeName() {
		return modeName;
	}


	public void addReactionTime(double time) {
		this.totalReactionTime += time;
		this.clicks++;
	}

	public double getAverageReactionTime() {
		if (clicks == 0) {
			return 0;
		} else {
			return totalReactionTime / clicks;
		}
	}

}