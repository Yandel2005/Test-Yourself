package gameoutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mechanics.Score;
import users.User;

public class GameData {
	
	private Connection connection;
	
	public GameData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "poop123");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public int insertUser(User user) {
		String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1,  user.getUsername());
			 stmt.setString(2, "defaultPassword");
			  stmt.executeUpdate();
			  ResultSet rs = stmt.getGeneratedKeys();
			  if(rs.next()) {
				  return rs.getInt(1);
			  }
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public User getUserByUsername(String username) {
		String sql = "SELECT user_id, username, password FROM Users WHERE username = ?";
		 try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			 stmt.setString(1, username);
			 ResultSet rs = stmt.executeQuery();
			 if(rs.next()) {
				  return new User(rs.getInt("user_id"), rs.getString("username"));
			 }
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return null;
	}
	
	public void saveScore(Score score) {
		String sql = "INSERT INTO Scores (score_value, user_id, game_mode_id) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1,  score.getValue());
			stmt.setInt(2, score.getUser().getUserId());
			stmt.setInt(3, score.getGameModeId());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
