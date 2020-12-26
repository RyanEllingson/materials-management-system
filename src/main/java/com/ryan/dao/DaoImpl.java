package com.ryan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ryan.models.Role;
import com.ryan.models.User;

class DaoImpl implements Dao {
	private Connection conn;
	
	DaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insertUser(User user) {
		int insertId = 0;
		String sql = "insert into users (user_id, password, first_name, last_name, role_id) values (?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, user.getUserId());
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
		String sql = "select user_id, password, first_name, last_name, role_id from users where user_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setUserId(rs.getInt(1));
				user.setPassword(rs.getString(2));
				user.setFirstName(rs.getString(3));
				user.setLastName(rs.getString(4));
				user.setRole(getRoleById(rs.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Role getRoleById(int roleId) {
		Role role = new Role();
		String sql = "select role_id, role from roles where role_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, roleId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				role.setRoleId(rs.getInt(1));
				role.setRole(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return role;
	}

}
