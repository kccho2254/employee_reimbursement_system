package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.models.Reimb;
import com.revature.models.User;
import com.revature.utilities.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	Connection conn = null;
	PreparedStatement stmt = null;
	User user = new User(0, 0, null, null, null, null, null);
	public static Logger log = Logger.getLogger(UserDAOImpl.class);

	@Override
	public boolean addEmployee(User user) {

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO ers_schema.ers_users (username, \"password\", user_first_name, user_last_name, email, user_role_id) VALUES(?, ?, ?, ?, ?, ?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			stmt.setInt(6, user.getRoleId());
			if (stmt.executeUpdate() != 0) {
				log.info("User Signed up as: " + user.getUsername() + " with password " + user.getPassword());
				return true;
			} else {
				return false;
			}

		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return false;
			
		} finally {
			closeResources();
		}
	}

	@Override
	public User getEmpByUsername(String username) {
		User user = null;

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM ers_schema.ers_users WHERE username = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, username);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("user_first_name"));
				user.setLastName(rs.getString("user_last_name"));
				user.setEmail(rs.getString("email"));
				user.setRoleId(rs.getInt("user_role_id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return user;
	}
	
	@Override
	public User getEmpByEmail(String email) {
		User user = null;

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM ers_schema.ers_users WHERE email = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, email);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User();

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("user_first_name"));
				user.setLastName(rs.getString("user_last_name"));
				user.setEmail(rs.getString("email"));
				user.setRoleId(rs.getInt("user_role_id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return user;
	}
	
	@Override
	public User login(String email, String password) {
		// short for get employee by email and password
		// validate existence of employee in the db
		User user = null;

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM ers_schema.ers_users WHERE email = ? AND password = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, email);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				user = new User();

				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("user_first_name"));
				user.setLastName(rs.getString("user_last_name"));
				user.setEmail(rs.getString("email"));
				user.setRoleId(rs.getInt("user_role_id"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();

		try {
			conn = ConnectionUtil.getConnection(); // Get our database connection from the manager
			String sql = "SELECT * FROM ers_schema.ers_users"; // Our SQL query
			stmt = conn.prepareStatement(sql); // Creates the prepared statement from the query

			ResultSet rs = stmt.executeQuery(); // Queries the database

			// So long as the ResultSet actually contains results...
			while (rs.next()) {
				// result
				User user = new User();
				// Each variable in our User object maps to a column in a row from our results.
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setFirstName(rs.getString("user_first_name"));
				user.setLastName(rs.getString("user_last_name"));
				user.setEmail(rs.getString("email"));
				user.setRoleId(rs.getInt("user_role_id"));

				users.add(user);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// We need to make sure our statements and connections are closed,
			// or else we could wind up with a memory leak
			closeResources();
		}

		// return the list of User objects populated by the DB.
		return users;
	}


	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
}
