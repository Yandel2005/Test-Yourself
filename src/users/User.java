package users;

public class User {

private int userId;
private String username;
private String password;
private int highscore;
private int score = 0;
public double accuracy = 100;
private double bestAccuracy;

public User(int userId, String username) {
	this.username = username;
	this.userId = userId;
	this.highscore = 0;
	this.score = 0;
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

private void setHighScore(int score) {
	this.score = score;
	if(score > highscore) {
		highscore = score;
	}
}

private double getAccuracy() {
	return accuracy;
}

private void setAccuracy(double accuracy) {
	this.accuracy = accuracy;
}

private void setUsername(String username) {
	this.username = username;
}

public void resetScore() {
	this.score = 0;
}
}