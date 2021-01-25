package com.ryan.data;

import java.util.List;

import com.ryan.models.Role;

public interface RoleRepository {

	public List<Role> getAllRoles();
	
	public Role getRoleById(int roleId);
}
