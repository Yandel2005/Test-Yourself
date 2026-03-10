package users;

public class User {

private String username;
private String password;
private int highscore;
private double bestAccuracy;

public User(String username, String password) {
	this.username = username;
	this.password = password;
	this.highscore = 0;
	this.bestAccuracy = 0.0;
}

public void recordGame(int score, double accuracy) {
	if(score > highscore) {
		highscore = score;
	}
	if(accuracy > bestAccuracy) {
		bestAccuracy = accuracy;
	}
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

}