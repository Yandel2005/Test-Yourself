 package gameoutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import mechanics.Score;
import users.User;

public class GameData {

	private Connection connection;
	private static final String URL = "jdbc:mysql://localhost:3306/mydb";
	private static final String USER = "root";
	private static final String PASS = "poop123";

	public GameData() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveScore(Score score) {
		String sql = "INSERT INTO Scores (score_value, user_id, game_mode_id, reaction_time) VALUES (?,?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, score.getValue());
			stmt.setInt(2, score.getUser().getUserId());
			stmt.setInt(3, score.getGameModeId());
			stmt.setDouble(4, score.getReactionTime());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public List<User> getTopScores(int gameModeId) {
		List<User> topUsers = new ArrayList<>();

		if (this.connection == null) {
			System.out.println("Connection Failed.");
			return topUsers;
		}

		String sql = "SELECT u.username, s.score_value, g.mode_name, s.reaction_time " + "FROM Scores s " + "JOIN Users u ON s.user_id = u.user_id " + "JOIN game_modes g ON s.game_mode_id = g.game_mode_id " + "WHERE g.game_mode_id = ? " + "ORDER BY s.score_value DESC " + "LIMIT 15";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setInt(1, gameModeId);
			 try (ResultSet rs = stmt.executeQuery()) {
				 while (rs.next()) {
					 User u = new User(rs.getString("username"));
					 u.setScore(rs.getInt("score_value"));
					 u.setModeName(rs.getString("mode_name"));
					 u.addReactionTime(rs.getDouble("reaction_time"));
					 topUsers.add(u);
				 }
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topUsers;
	}

	public Boolean registerUser(String username, String password) {
		if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
		return false;
		}

		String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, username.trim());
			stmt.setString(2, password);
			int affectedRows = stmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username already exists");
					return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public User checkLogin(String username, String password) {
		String sql = "SELECT * from Users Where username = ? AND password = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					User user = new User(rs.getString("username"));
					user.setUserId(rs.getInt("user_id"));
					return user;
				}
			}
		} catch (SQLException e) {
			System.out.println("THIS USER EXISTSS.");
			e.printStackTrace();
		}
		return null;
	}

	public boolean userExists(String username) {
		String sql = "SELECT 1 FROM Users WHERE username = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}}

