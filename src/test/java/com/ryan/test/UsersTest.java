package com.ryan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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
		user = Users.register("password", "testy", "testerson", 1);
	}

	@Test
	public void shouldHaveRegisterdUser() {
		assertEquals("testy", user.getFirstName());
		assertEquals("testerson", user.getLastName());
		assertEquals("Admin", user.getRole().getRole());
	}
	
	@Test
	public void shouldLoginRegisteredUser() {
		User returnedUser = Users.login(user.getUserId(), "password");
		assertEquals("testy", returnedUser.getFirstName());
	}
	
	@Test
	public void shouldNotLoginUnregisteredUser() {
		User returnedUser = Users.login(0, "blabla");
		assertNull(returnedUser.getFirstName());
	}
	
	@AfterClass
	public static void teardown() {
		dao.deleteUserById(user.getUserId());
	}
}
