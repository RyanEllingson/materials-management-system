package com.ryan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ryan.data.UserDatabaseRepository;
import com.ryan.data.UserRepository;
import com.ryan.models.Role;
import com.ryan.models.User;
import com.ryan.util.ConnectionFactory;
import com.ryan.util.Environment;

public class UserDatabaseRepositoryTest {
	private static UserRepository userRepo;
	private static int createUserId;
	private static int readUserId;
	private static int updateUserId;
	private static int deleteUserId;
	
	@BeforeClass
	public static void setup() {
		ConnectionFactory.setEnvironment(Environment.TEST);
		userRepo = new UserDatabaseRepository(ConnectionFactory.getConnection());
		User readUser = new User(0, "testemail1", "password1", "firstname1", "lastname1", new Role(1, "Admin"));
		readUserId = userRepo.insertUser(readUser);
		User updateUser = new User(0, "testemail2", "password2", "firstname2", "lastname2", new Role(1, "Admin"));
		updateUserId = userRepo.insertUser(updateUser);
		User deleteUser = new User(0, "testemail3", "password3", "firstname3", "lastname3", new Role(1, "Admin"));
		deleteUserId = userRepo.insertUser(deleteUser);
	}
	
	@Test
	public void shouldInsertUser() {
		User user = new User(0, "testemail4", "password4", "firstname4", "lastname4", new Role(2, "Planner"));
		createUserId = userRepo.insertUser(user);
		assertNotEquals(0, createUserId);
		User createdUser = userRepo.getUserById(createUserId);
		assertEquals("testemail4", createdUser.getEmail());
		assertEquals("password4", createdUser.getPassword());
		assertEquals("firstname4", createdUser.getFirstName());
		assertEquals("lastname4", createdUser.getLastName());
		assertEquals(new Role(2, null), createdUser.getRole());
	}
	
	@Test
	public void shouldGetUserById() {
		User user = userRepo.getUserById(readUserId);
		assertEquals(readUserId, user.getUserId());
		assertEquals("testemail1", user.getEmail());
		assertEquals("password1", user.getPassword());
		assertEquals("firstname1", user.getFirstName());
		assertEquals("lastname1", user.getLastName());
		assertEquals(1, user.getRole().getRoleId());
	}
	
	@Test
	public void shouldNotFindNonExistingUserId() {
		User user = userRepo.getUserById(-1);
		assertEquals(0, user.getUserId());
		assertNull(user.getEmail());
		assertNull(user.getPassword());
		assertNull(user.getFirstName());
		assertNull(user.getLastName());
		assertNull(user.getRole());
	}
	
	@Test
	public void shouldGetUserByEmail() {
		User user = userRepo.getUserByEmail("testemail1");
		assertEquals(readUserId, user.getUserId());
		assertEquals("testemail1", user.getEmail());
		assertEquals("password1", user.getPassword());
		assertEquals("firstname1", user.getFirstName());
		assertEquals("lastname1", user.getLastName());
		assertEquals(1, user.getRole().getRoleId());
	}
	
	@Test
	public void shouldNotFindNonExistingEmail() {
		User user = userRepo.getUserByEmail("nonexistingemail");
		assertEquals(0, user.getUserId());
		assertNull(user.getEmail());
		assertNull(user.getPassword());
		assertNull(user.getFirstName());
		assertNull(user.getLastName());
		assertNull(user.getRole());
	}
	
	@Test
	public void shouldUpdateUser() {
		User user = new User(updateUserId, "testemail2", "betterpassword", "betterfirstname", "betterlastname", new Role(3, "Standard"));
		int affectedRows = userRepo.updateUser(user);
		assertEquals(1, affectedRows);
		User updatedUser = userRepo.getUserById(updateUserId);
		assertEquals(updateUserId, updatedUser.getUserId());
		assertEquals("testemail2", updatedUser.getEmail());
		assertEquals("betterpassword", updatedUser.getPassword());
		assertEquals("betterfirstname", updatedUser.getFirstName());
		assertEquals("betterlastname", updatedUser.getLastName());
		assertEquals(3, updatedUser.getRole().getRoleId());
	}
	
	@Test
	public void shouldNotUpdateNonExistingUser() {
		User user = new User(-1, "email", "password", "firstname", "lastname", new Role(1, "Admin"));
		int affectedRows = userRepo.updateUser(user);
		assertEquals(0, affectedRows);
	}
	
	@Test
	public void shouldDeleteUser() {
		int affectedRows = userRepo.deleteUserById(deleteUserId);
		assertEquals(1, affectedRows);
		User deletedUser = userRepo.getUserById(deleteUserId);
		assertEquals(0, deletedUser.getUserId());
		assertNull(deletedUser.getEmail());
		assertNull(deletedUser.getPassword());
		assertNull(deletedUser.getFirstName());
		assertNull(deletedUser.getLastName());
		assertNull(deletedUser.getRole());
	}
	
	@Test
	public void shouldNotDeleteNonExistingUser() {
		int affectedRows = userRepo.deleteUserById(-1);
		assertEquals(0, affectedRows);
	}
	
	@AfterClass
	public static void teardown() {
		userRepo.deleteUserById(createUserId);
		userRepo.deleteUserById(readUserId);
		userRepo.deleteUserById(updateUserId);
	}
}
