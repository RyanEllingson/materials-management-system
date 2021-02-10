package com.ryan.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ryan.data.MaterialDatabaseRepository;
import com.ryan.models.Material;
import com.ryan.models.MaterialType;
import com.ryan.models.Unit;
import com.ryan.util.ConnectionFactory;

public class MaterialDatabaseRepositoryTest {
	private static MaterialDatabaseRepository materialRepo = new MaterialDatabaseRepository(ConnectionFactory.getConnection());
	private static int createMaterialId;
	private static int readMaterialId;
	private static int updateMaterialId;
	private static int deleteMaterialId;
	
	@BeforeClass
	public static void setup() {
		Material readMaterial = new Material(0, "testmaterial1", new MaterialType(1, null), new Unit(1, null), BigDecimal.ONE);
		readMaterialId = materialRepo.createMaterial(readMaterial);
		Material updateMaterial = new Material(0, "testmaterial2", new MaterialType(1, null), new Unit(1, null), BigDecimal.ONE);
		updateMaterialId = materialRepo.createMaterial(updateMaterial);
		Material deleteMaterial = new Material(0, "testmaterial3", new MaterialType(1, null), new Unit(1, null), BigDecimal.ONE);
		deleteMaterialId = materialRepo.createMaterial(deleteMaterial);
	}
	
	@Test
	public void shouldCreateMaterial() {
		Material material = new Material(0, "testmaterial4", new MaterialType(2, null), new Unit(1, null), BigDecimal.ONE);
		createMaterialId = materialRepo.createMaterial(material);
		assertNotEquals(0, createMaterialId);
		Material createdMaterial = materialRepo.getMaterialById(createMaterialId);
		assertEquals("testmaterial4", createdMaterial.getMaterialName());
		assertEquals(new MaterialType(2, null), createdMaterial.getMaterialType());
		assertEquals(new Unit(1, null), createdMaterial.getUnit());
		assertEquals(BigDecimal.ONE, createdMaterial.getUnitCost());
	}
	
	@Test
	public void shouldGetMaterialById() {
		Material material = materialRepo.getMaterialById(readMaterialId);
		assertEquals(readMaterialId, material.getMaterialId());
		assertEquals("testmaterial1", material.getMaterialName());
		assertEquals(new MaterialType(1, null), material.getMaterialType());
		assertEquals(new Unit(1, null), material.getUnit());
		assertEquals(BigDecimal.ONE, material.getUnitCost());
	}
	
	@Test
	public void shouldNotGetNonExistingMaterial() {
		Material material = materialRepo.getMaterialById(-1);
		assertEquals(0, material.getMaterialId());
		assertEquals(null, material.getMaterialName());
		assertEquals(null, material.getMaterialType());
		assertEquals(null, material.getUnit());
		assertEquals(null, material.getUnitCost());
	}
	
	@Test
	public void shouldGetMaterialsByType() {
		List<Material> materials = materialRepo.getMaterialsByType(new MaterialType(1, null));
		assertTrue(materials.size() > 1);
		Material material = materialRepo.getMaterialById(readMaterialId);
		assertTrue(materials.contains(material));
	}
	
	@Test
	public void shouldUpdateMaterial() {
		Material expected = new Material(updateMaterialId, "updatedmaterial", new MaterialType(1, null), new Unit(2, null), BigDecimal.TEN);
		int affectedRows = materialRepo.updateMaterial(expected);
		assertEquals(1, affectedRows);
		Material actual = materialRepo.getMaterialById(updateMaterialId);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotUpdateNonExistingMaterial() {
		Material bogusMaterial = new Material(-1, "phonyname", new MaterialType(3, null), new Unit (2, null), BigDecimal.ZERO);
		int affectedRows = materialRepo.updateMaterial(bogusMaterial);
		assertEquals(0, affectedRows);
	}
	
	@Test
	public void shouldDeleteMaterial() {
		int affectedRows = materialRepo.deleteMaterialById(deleteMaterialId);
		assertEquals(1, affectedRows);
		Material deletedMaterial = materialRepo.getMaterialById(deleteMaterialId);
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
	
	@AfterClass
	public static void teardown() {
		materialRepo.deleteMaterialById(createMaterialId);
		materialRepo.deleteMaterialById(readMaterialId);
		materialRepo.deleteMaterialById(updateMaterialId);
	}
	
}
