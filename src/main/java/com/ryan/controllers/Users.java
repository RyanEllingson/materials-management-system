package com.ryan.controllers;

import com.ryan.data.UserRepository;
import com.ryan.data.UserDatabaseRepository;
import com.ryan.models.User;
import com.ryan.util.ConnectionFactory;
import com.ryan.util.HashGenerator;

public class Users {
	
	public static User register(String password, String firstName, String lastName, int roleId) {
//		UserRepository userRepo = new UserDatabaseRepository(ConnectionFactory.getConnection());
//		User user = new User();
//		String hashedPass = HashGenerator.generateHash(password);
//		user.setPassword(hashedPass);
//		user.setFirstName(firstName);
//		user.setLastName(lastName);
//		user.setRole(dao.getRoleById(roleId));
//		int insertId = dao.insertUser(user);
//		return dao.getUserById(insertId);
		return null;
	}
	
	public static User login(int userId, String password) {
//		Dao dao = DaoFactory.getDao();
//		User user = dao.getUserById(userId);
//		if (user.getUserId() != 0) {
//			String[] passArr = user.getPassword().split("/");
//			if (!HashGenerator.comparePasswords(passArr[0], password, passArr[1])) {
//				user = new User();
//			}
//		}
//		return user;
		return null;
	}

}
