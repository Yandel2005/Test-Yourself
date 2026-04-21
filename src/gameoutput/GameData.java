 package gameoutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public int insertUser(User user) {
		String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setString(1, user.getUsername());
			stmt.setString(2, "defaultPassword");
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public User getUserByUsername(String username) {
		if (connection == null) return null;

		String query = "SELECT * FROM users WHERE username = ?";

		try (PreparedStatement stmt = connection.prepareStatement(query)) {
			stmt.setString(1, username);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {

					User user = new User(rs.getString("username"));
					user.setUserId(rs.getInt("user_id"));
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void saveScore(Score score, int gameModeId) {
		String sql = "INSERT INTO Scores (score_value, user_id, game_mode_id) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, score.getValue());
			stmt.setInt(2, score.getUser().getUserId());
			stmt.setInt(3, score.getGameModeId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public List<User> getTopScores() {
		List<User> topUsers = new ArrayList<>();

		if (this.connection == null) {
			System.out.println("Connection Failed.");
			return topUsers;
		}

		String sql = "SELECT u.username, s.score_value, g.mode_name " +
				"FROM Scores s " + "JOIN Users u ON s.user_id = u.user_id " + "JOIN game_modes g ON s.game_mode_id = g.game_mode_id " + "ORDER BY s.score_value DESC " + "LIMIT 15";

		try (PreparedStatement stmt = connection.prepareStatement(sql);
		     ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				User u = new User(rs.getString("username"));
				u.setScore(rs.getInt("score_value"));
				u.setModeName(rs.getString("mode_name"));
				topUsers.add(u);
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
			e.printStackTrace();
		}
		return null;
	}
}
	