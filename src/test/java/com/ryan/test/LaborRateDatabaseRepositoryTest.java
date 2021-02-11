package com.ryan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ryan.data.LaborRateDatabaseRepository;
import com.ryan.data.LaborRateRepository;
import com.ryan.models.LaborRate;
import com.ryan.util.ConnectionFactory;
import com.ryan.util.Environment;

public class LaborRateDatabaseRepositoryTest {
	private static LaborRateRepository rateRepo;
	
	@BeforeClass
	public static void setup() {
		ConnectionFactory.setEnvironment(Environment.TEST);
		ConnectionFactory.restoreKnownGoodState();
		rateRepo = new LaborRateDatabaseRepository(ConnectionFactory.getConnection());
	}
	
	@Test
	public void shouldCreateLaborRate() {
		LaborRate rate = new LaborRate(0, "testrate4", BigDecimal.ONE);
		int insertId = rateRepo.createLaborRate(rate);
		assertEquals(4, insertId);
		LaborRate createdRate = rateRepo.getLaborRateById(insertId);
		assertEquals("testrate4", createdRate.getDescription());
		assertEquals(BigDecimal.ONE.doubleValue(), createdRate.getRatePerHour().doubleValue(), 0.0001);
	}
	
	@Test
	public void shouldGetLaborRateById() {
		LaborRate rate = rateRepo.getLaborRateById(1);
		assertEquals(1, rate.getLaborRateId());
		assertEquals("testrate1", rate.getDescription());
		assertEquals(BigDecimal.ONE.doubleValue(), rate.getRatePerHour().doubleValue(), 0.0001);
	}
	
	@Test
	public void shouldNotFindNonExistingLaborRate() {
		LaborRate rate = rateRepo.getLaborRateById(-1);
		assertEquals(0, rate.getLaborRateId());
		assertNull(rate.getDescription());
		assertNull(rate.getRatePerHour());
	}
	
	@Test
	public void shouldUpdateLaborRate() {
		LaborRate expected = new LaborRate(2, "updatedrate", BigDecimal.TEN);
		int affectedRows = rateRepo.updateLaborRate(expected);
		assertEquals(1, affectedRows);
		LaborRate actual = rateRepo.getLaborRateById(2);
		assertEquals(expected.getLaborRateId(), actual.getLaborRateId());
		assertEquals(expected.getDescription(), actual.getDescription());
		assertEquals(expected.getRatePerHour().doubleValue(), actual.getRatePerHour().doubleValue(), 0.0001);
	}
	
	@Test
	public void shouldNotUpdateNonExistingLaborRate() {
		LaborRate rate = new LaborRate(-1, "bogusrate", BigDecimal.ZERO);
		int affectedRows = rateRepo.updateLaborRate(rate);
		assertEquals(0, affectedRows);
	}
	
	@Test
	public void shouldDeleteLaborRate() {
		int affectedRows = rateRepo.deleteLaborRateById(3);
		assertEquals(1, affectedRows);
		LaborRate deletedRate = rateRepo.getLaborRateById(3);
		assertEquals(0, deletedRate.getLaborRateId());
		assertNull(deletedRate.getDescription());
		assertNull(deletedRate.getRatePerHour());
	}
	
	@Test
	public void shouldNotDeleteNonExistingLaborRate() {
		int affectedRows = rateRepo.deleteLaborRateById(-1);
		assertEquals(0, affectedRows);
	}

}
