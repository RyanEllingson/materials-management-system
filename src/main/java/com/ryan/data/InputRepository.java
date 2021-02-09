package com.ryan.data;

import java.util.List;

import com.ryan.models.Input;
import com.ryan.models.InputType;

public interface InputRepository {
	
	public int createMaterial(Input material);
	
	public Input getMaterialById(int materialId);
	
	public List<Input> getMaterialsByType(InputType materialType);
	
	public int updateMaterial(Input material);
	
	public int deleteMaterialById(int materialId);

}
