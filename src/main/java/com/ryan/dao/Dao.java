package com.ryan.dao;

import com.ryan.models.Role;
import com.ryan.models.User;

public interface Dao {

	public int insertUser(User user);
	
	public User getUserById(int userId);
	
	public Role getRoleById(int roleId);
	
	public void deleteUserById(int userId);
}
