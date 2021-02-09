package com.ryan.data;

import java.sql.Connection;
import java.util.List;

import com.ryan.models.Material;
import com.ryan.models.MaterialType;

public class MaterialDatabaseRepository implements MaterialRepository {
	private Connection conn;
	
	public MaterialDatabaseRepository(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int createMaterial(Material material) {
		int insertId = 0;
		
		return insertId;
	}

	@Override
	public Material getMaterialById(int materialId) {
		return null;
	}

	@Override
	public List<Material> getMaterialsByType(MaterialType materialType) {
		return null;
	}

	@Override
	public int updateMaterial(Material material) {
		return 0;
	}

	@Override
	public int deleteMaterialById(int materialId) {
		return 0;
	}

}
