package com.ryan.data;

import java.util.List;

import com.ryan.models.Material;
import com.ryan.models.MaterialType;

public interface MaterialRepository {
	
	public int createMaterial(Material material);
	
	public Material getMaterialById(int materialId);
	
	public List<Material> getMaterialsByType(MaterialType materialType);
	
	public int updateMaterial(Material material);
	
	public int deleteMaterialById(int materialId);

}
