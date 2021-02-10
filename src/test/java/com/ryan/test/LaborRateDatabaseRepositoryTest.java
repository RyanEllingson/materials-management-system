package com.ryan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ryan.data.LaborRateDatabaseRepository;
import com.ryan.models.LaborRate;
import com.ryan.util.ConnectionFactory;

public class LaborRateDatabaseRepositoryTest {
	private static LaborRateDatabaseRepository rateRepo = new LaborRateDatabaseRepository(ConnectionFactory.getConnection());
	private static int createRateId;
	private static int readRateId;
	private static int updateRateId;
	private static int deleteRateId;
	
	@BeforeClass
	public static void setup() {
		LaborRate readRate = new LaborRate(0, "testrate1", BigDecimal.ONE);
		readRateId = rateRepo.createLaborRate(readRate);
		LaborRate updateRate = new LaborRate(0, "testrate2", BigDecimal.ONE);
		updateRateId = rateRepo.createLaborRate(updateRate);
		LaborRate deleteRate = new LaborRate(0, "testrate3", BigDecimal.ONE);
		deleteRateId = rateRepo.createLaborRate(deleteRate);
	}
	
	@Test
	public void shouldCreateLaborRate() {
		LaborRate rate = new LaborRate(0, "testrate4", BigDecimal.ONE);
		createRateId = rateRepo.createLaborRate(rate);
		assertNotEquals(0, createRateId);
		LaborRate createdRate = rateRepo.getLaborRateById(createRateId);
		assertEquals("testrate4", createdRate.getDescription());
		assertEquals(BigDecimal.ONE, createdRate.getRatePerHour());
	}
	
	@Test
	public void shouldGetLaborRateById() {
		LaborRate rate = rateRepo.getLaborRateById(readRateId);
		assertEquals(readRateId, rate.getLaborRateId());
		assertEquals("testrate1", rate.getDescription());
		assertEquals(BigDecimal.ONE, rate.getRatePerHour());
	}
	
	@Test
	public void shouldNotFindNonExistingLaborRate() {
		LaborRate rate = rateRepo.getLaborRateById(-1);
		assertEquals(0, rate.getLaborRateId());
		assertEquals(null, rate.getDescription());
		assertEquals(null, rate.getRatePerHour());
	}
	
	@Test
	public void shouldUpdateLaborRate() {
		LaborRate expected = new LaborRate(updateRateId, "updatedrate", BigDecimal.TEN);
		int affectedRows = rateRepo.updateLaborRate(expected);
		assertEquals(1, affectedRows);
		LaborRate actual = rateRepo.getLaborRateById(updateRateId);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotUpdateNonExistingLaborRate() {
		LaborRate rate = new LaborRate(-1, "bogusrate", BigDecimal.ZERO);
		int affectedRows = rateRepo.updateLaborRate(rate);
		assertEquals(0, affectedRows);
	}
	
	@Test
	public void shouldDeleteLaborRate() {
		int affectedRows = rateRepo.deleteLaborRateById(deleteRateId);
		assertEquals(1, affectedRows);
		LaborRate deletedRate = rateRepo.getLaborRateById(deleteRateId);
		assertEquals(0, deletedRate.getLaborRateId());
		assertNull(deletedRate.getDescription());
		assertNull(deletedRate.getRatePerHour());
	}
	
	@Test
	public void shouldNotDeleteNonExistingLaborRate() {
		int affectedRows = rateRepo.deleteLaborRateById(-1);
		assertEquals(0, affectedRows);
	}
	
	@AfterClass
	public static void teardown() {
		rateRepo.deleteLaborRateById(createRateId);
		rateRepo.deleteLaborRateById(readRateId);
		rateRepo.deleteLaborRateById(updateRateId);
	}

}
