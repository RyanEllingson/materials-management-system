package com.ryan.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ryan.models.Role;
import com.ryan.models.User;

public class UserDatabaseRepository implements UserRepository {
	private Connection conn;
	
	public UserDatabaseRepository(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int insertUser(User user) {
		int insertId = 0;
		String sql = "insert into users (email, password, first_name, last_name, role_id) values (?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setInt(5, user.getRole().getRoleId());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				insertId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertId;
	}

	@Override
	public User getUserById(int userId) {
		User user = new User();
		String sql = "select user_id, email, password, first_name, last_name, role_id from users where user_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setEmail(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setRole(new Role());
				user.getRole().setRoleId(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = new User();
		String sql = "select user_id, email, password, first_name, last_name, role_id from users where email = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setEmail(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setRole(new Role());
				user.getRole().setRoleId(rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int updateUser(User user) {
		int affectedRows = 0;
		String sql = "update users set password = ?, first_name = ?, last_name = ?, role_id = ? where user_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setInt(4, user.getRole().getRoleId());
			ps.setInt(5, user.getUserId());
			affectedRows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public int deleteUserById(int userId) {
		int affectedRows = 0;
		String sql = "delete from users where user_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			affectedRows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

}
