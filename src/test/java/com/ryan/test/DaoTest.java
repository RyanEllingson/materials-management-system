package com.ryan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ryan.dao.Dao;
import com.ryan.dao.DaoFactory;
import com.ryan.models.User;

public class DaoTest {
	private static Dao dao;
	
	@BeforeClass
	public static void setup() {
		dao = DaoFactory.getDao();
	}
	
	@Test
	public void shouldGetRoles() {
		assertEquals("Admin", dao.getRoleById(1).getRole());
		assertEquals("Planner", dao.getRoleById(2).getRole());
		assertEquals("Standard", dao.getRoleById(3).getRole());
	}
	
	@Test
	public void shouldInsertRetrieveAndDeleteUser() {
		User testUser = new User(0, "email", "password", "testy", "testerson", dao.getRoleById(1));
		int insertId = dao.insertUser(testUser);
		User result = dao.getUserById(insertId);
		assertEquals("password", result.getPassword());
		assertEquals("testy", result.getFirstName());
		assertEquals("testerson", result.getLastName());
		dao.deleteUserById(insertId);
		assertNull(dao.getUserById(insertId).getPassword());
	}
}
