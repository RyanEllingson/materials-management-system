package com.ryan.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ryan.data.MaterialTypeDatabaseRepository;
import com.ryan.data.MaterialTypeRepository;
import com.ryan.models.MaterialType;
import com.ryan.util.ConnectionFactory;
import com.ryan.util.Environment;

public class MaterialTypeDatabaseRepositoryTest {
	private static MaterialTypeRepository materialTypeRepo;
	
	@BeforeClass
	public static void setup() {
		ConnectionFactory.setEnvironment(Environment.TEST);
		materialTypeRepo = new MaterialTypeDatabaseRepository(ConnectionFactory.getConnection());
	}
	
	@Test
	public void shouldGetMaterialTypeById() {
		MaterialType expected = new MaterialType(1, "Raw Material");
		MaterialType actual = materialTypeRepo.getMaterialTypeById(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldNotGetNonExistingMaterialType() {
		MaterialType expected = new MaterialType();
		MaterialType actual = materialTypeRepo.getMaterialTypeById(-1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void shouldGetAllMaterialTypes() {
		List<MaterialType> materialTypes = materialTypeRepo.getAllMaterialTypes();
		assertEquals(3, materialTypes.size());
		MaterialType materialType = new MaterialType(2, "Intermediate");
		assertEquals(materialType, materialTypes.get(1));
	}

}
