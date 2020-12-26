package com.ryan.dao;

import com.ryan.models.Role;
import com.ryan.models.User;

interface Dao {

	public int insertUser(User user);
	
	public User getUserById(int userId);
	
	public Role getRoleById(int roleId);
}
