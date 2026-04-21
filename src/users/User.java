package users;

public class User {

	private int userId;
	private String username;
	private String password;
	private int highscore;
	private int score = 0;
	private int accuracy = 100;
	private double bestAccuracy;
	private String modeName;

	public User(int userId, String username, String password) {
		this.username = username;
		this.userId = userId;
		this.password = password;
		this.score = score;
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

	public int getHighscore() {
		return highscore;
	}

	public String getUsername() {
		return username;
	}

	public double getBestAccuracy() {
		return bestAccuracy;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setHighScore(int score) {
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

	public void resetScore() {
		this.score = 0;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public String getModeName() {
		return modeName;
	}

	public String getPassword() {
		return password;
	}

}