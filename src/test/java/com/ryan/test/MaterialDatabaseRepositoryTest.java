package com.ryan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ryan.data.MaterialDatabaseRepository;
import com.ryan.data.MaterialRepository;
import com.ryan.models.Material;
import com.ryan.models.MaterialType;
import com.ryan.models.Unit;
import com.ryan.util.ConnectionFactory;
import com.ryan.util.Environment;

public class MaterialDatabaseRepositoryTest {
	private static MaterialRepository materialRepo;
	
	@BeforeClass
	public static void setup() {
		ConnectionFactory.setEnvironment(Environment.TEST);
		ConnectionFactory.restoreKnownGoodState();
		materialRepo = new MaterialDatabaseRepository(ConnectionFactory.getConnection());
	}
	
	@Test
	public void shouldCreateMaterial() {
		Material material = new Material(0, "testmaterial4", new MaterialType(2, null), new Unit(1, null), BigDecimal.ONE);
		int insertId = materialRepo.createMaterial(material);
		assertEquals(4, insertId);
		Material createdMaterial = materialRepo.getMaterialById(insertId);
		assertEquals("testmaterial4", createdMaterial.getMaterialName());
		assertEquals(new MaterialType(2, null), createdMaterial.getMaterialType());
		assertEquals(new Unit(1, null), createdMaterial.getUnit());
		assertEquals(BigDecimal.ONE.doubleValue(), createdMaterial.getUnitCost().doubleValue(), 0.0001);
	}
	
	@Test
	public void shouldGetMaterialById() {
		Material material = materialRepo.getMaterialById(1);
		assertEquals(1, material.getMaterialId());
		assertEquals("testmaterial1", material.getMaterialName());
		assertEquals(new MaterialType(1, null), material.getMaterialType());
		assertEquals(new Unit(1, null), material.getUnit());
		assertEquals(BigDecimal.ONE.doubleValue(), material.getUnitCost().doubleValue(), 0.0001);
	}
	
	@Test
	public void shouldNotGetNonExistingMaterial() {
		Material material = materialRepo.getMaterialById(-1);
		assertEquals(0, material.getMaterialId());
		assertNull(material.getMaterialName());
		assertNull(material.getMaterialType());
		assertNull(material.getUnit());
		assertNull(material.getUnitCost());
	}
	
	@Test
	public void shouldGetMaterialsByType() {
		List<Material> materials = materialRepo.getMaterialsByType(new MaterialType(1, null));
		assertEquals(2, materials.size());
		Material material = materialRepo.getMaterialById(1);
		assertEquals(material.getMaterialId(), materials.get(0).getMaterialId());
		assertEquals(material.getMaterialName(), materials.get(0).getMaterialName());
		assertEquals(material.getMaterialType(), materials.get(0).getMaterialType());
		assertEquals(material.getUnit(), materials.get(0).getUnit());
		assertEquals(material.getUnitCost().doubleValue(), materials.get(0).getUnitCost().doubleValue(), 0.0001);
	}
	
	@Test
	public void shouldUpdateMaterial() {
		Material expected = new Material(2, "updatedmaterial", new MaterialType(1, null), new Unit(2, null), BigDecimal.TEN);
		int affectedRows = materialRepo.updateMaterial(expected);
		assertEquals(1, affectedRows);
		Material actual = materialRepo.getMaterialById(2);
		assertEquals(expected.getMaterialId(), actual.getMaterialId());
		assertEquals(expected.getMaterialName(), actual.getMaterialName());
		assertEquals(expected.getMaterialType(), actual.getMaterialType());
		assertEquals(expected.getUnit(), actual.getUnit());
		assertEquals(expected.getUnitCost().doubleValue(), actual.getUnitCost().doubleValue(), 0.0001);
	}
	
	@Test
	public void shouldNotUpdateNonExistingMaterial() {
		Material bogusMaterial = new Material(-1, "phonyname", new MaterialType(3, null), new Unit (2, null), BigDecimal.ZERO);
		int affectedRows = materialRepo.updateMaterial(bogusMaterial);
		assertEquals(0, affectedRows);
	}
	
	@Test
	public void shouldDeleteMaterial() {
		int affectedRows = materialRepo.deleteMaterialById(3);
		assertEquals(1, affectedRows);
		Material deletedMaterial = materialRepo.getMaterialById(3);
		assertEquals(0, deletedMaterial.getMaterialId());
		assertNull(deletedMaterial.getMaterialName());
		assertNull(deletedMaterial.getMaterialType());
		assertNull(deletedMaterial.getUnit());
		assertNull(deletedMaterial.getUnitCost());
	}
	
	@Test
	public void shouldNotDeleteNonExistingMaterial() {
		int affectedRows = materialRepo.deleteMaterialById(-1);
		assertEquals(0, affectedRows);
	}
	
}
