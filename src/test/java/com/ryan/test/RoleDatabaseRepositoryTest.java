package com.ryan.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ryan.data.RoleDatabaseRepository;
import com.ryan.data.RoleRepository;
import com.ryan.models.Role;
import com.ryan.util.ConnectionFactory;
import com.ryan.util.Environment;

public class RoleDatabaseRepositoryTest {
	private static RoleRepository roleRepo;
	
	@BeforeClass
	public static void setup() {
		ConnectionFactory.setEnvironment(Environment.TEST);
		roleRepo = new RoleDatabaseRepository(ConnectionFactory.getConnection());
	}

	@Test
	public void shouldGetRoleById() {
		Role expected = new Role(1, "Admin");
		Role actual = roleRepo.getRoleById(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotFindNonExistingRole() {
		Role expected = new Role();
		Role actual = roleRepo.getRoleById(-1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldGetAllRoles() {
		List<Role> roles = roleRepo.getAllRoles();
		assertEquals(3, roles.size());
		assertEquals(2, roles.get(1).getRoleId());
		assertEquals("Planner", roles.get(1).getRole());
	}
}
