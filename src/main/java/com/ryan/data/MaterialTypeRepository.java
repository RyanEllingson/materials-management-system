package com.ryan.data;

import java.util.List;

import com.ryan.models.MaterialType;

public interface MaterialTypeRepository {
	
	public MaterialType getMaterialTypeById(int materialTypeId);
	
	public List<MaterialType> getAllMaterialTypes();

}
