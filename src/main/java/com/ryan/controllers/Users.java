package com.ryan.controllers;

import com.ryan.dao.Dao;
import com.ryan.dao.DaoFactory;
import com.ryan.models.User;
import com.ryan.util.HashGenerator;

public class Users {
	
	public static User register(String password, String firstName, String lastName, int roleId) {
		Dao dao = DaoFactory.getDao();
		User user = new User();
		String hashedPass = HashGenerator.generateHash(password);
		user.setPassword(hashedPass);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setRole(dao.getRoleById(roleId));
		int insertId = dao.insertUser(user);
		return dao.getUserById(insertId);
	}

}
