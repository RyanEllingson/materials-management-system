package com.ryan.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.ryan.data.UnitDatabaseRepository;
import com.ryan.models.Unit;
import com.ryan.util.ConnectionFactory;

public class UnitDatabaseRepositoryTest {
	private static UnitDatabaseRepository unitRepo = new UnitDatabaseRepository(ConnectionFactory.getConnection());
	
	@Test
	public void shouldFindById() {
		Unit expected = new Unit(1, "Each");
		Unit actual = unitRepo.getUnitById(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotFindNonExistingUnit() {
		Unit expected = new Unit();
		Unit actual = unitRepo.getUnitById(-1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldFindAllUnits() {
		List<Unit> actual = unitRepo.getAllUnits();
		assertEquals(2, actual.size());
		Unit unit = new Unit(2, "Pounds");
		assertEquals(unit, actual.get(1));
	}

}
