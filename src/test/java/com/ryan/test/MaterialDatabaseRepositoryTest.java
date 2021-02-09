package com.ryan.test;

import java.math.BigDecimal;

import org.junit.AfterClass;
import org.junit.BeforeClass;

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
	
	@AfterClass
	public static void teardown() {
		materialRepo.deleteMaterialById(createMaterialId);
		materialRepo.deleteMaterialById(readMaterialId);
		materialRepo.deleteMaterialById(updateMaterialId);
	}
	
}
