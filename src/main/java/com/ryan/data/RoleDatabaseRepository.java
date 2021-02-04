package com.ryan.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ryan.models.Role;

public class RoleDatabaseRepository implements RoleRepository {
	private Connection conn;
	
	public RoleDatabaseRepository(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> roles = new ArrayList<>();
		String sql = "select role_id, role from roles";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				roles.add(new Role(
					rs.getInt(1),
					rs.getString(2)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;
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
