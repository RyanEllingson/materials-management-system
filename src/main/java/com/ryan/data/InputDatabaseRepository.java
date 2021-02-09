package com.ryan.data;

import java.sql.Connection;
import java.util.List;

import com.ryan.models.Input;
import com.ryan.models.InputType;

public class InputDatabaseRepository implements InputRepository {
	private Connection conn;
	
	public InputDatabaseRepository(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int createMaterial(Input material) {
		int insertId = 0;
		
		return insertId;
	}

	@Override
	public Input getMaterialById(int materialId) {
		return null;
	}

	@Override
	public List<Input> getMaterialsByType(InputType materialType) {
		return null;
	}

	@Override
	public int updateMaterial(Input material) {
		return 0;
	}

	@Override
	public int deleteMaterialById(int materialId) {
		return 0;
	}

}
