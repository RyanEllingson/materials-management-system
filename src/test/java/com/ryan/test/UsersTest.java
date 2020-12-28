package com.ryan.test;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ryan.controllers.Users;
import com.ryan.dao.Dao;
import com.ryan.dao.DaoFactory;
import com.ryan.models.User;

public class UsersTest {
	private static Dao dao;
	private static User user;
	
	@BeforeClass
	public static void setup() {
		dao = DaoFactory.getDao();
	}

	@Test
	public void shouldRegisterUser() {
		user = Users.register("password", "testy", "testerson", 1);
		System.out.println(user.getPassword());
		assertEquals("testy", user.getFirstName());
		assertEquals("testerson", user.getLastName());
		assertEquals("Admin", user.getRole().getRole());
	}
	
	@AfterClass
	public static void teardown() {
		dao.deleteUserById(user.getUserId());
	}
}
