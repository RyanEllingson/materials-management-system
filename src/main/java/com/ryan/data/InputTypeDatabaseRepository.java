package com.ryan.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ryan.models.InputType;

public class InputTypeDatabaseRepository implements InputTypeRepository {
	private Connection conn;
	
	public InputTypeDatabaseRepository(Connection conn) {
		this.conn = conn;
	}

	@Override
	public InputType getInputTypeById(int inputTypeId) {
		InputType inputType = new InputType();
		String sql = "select input_type_id, input_type from input_types where input_type_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, inputTypeId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				inputType.setMaterialTypeId(rs.getInt(1));
				inputType.setMaterialType(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inputType;
	}

	@Override
	public List<InputType> getAllInputTypes() {
		List<InputType> inputTypes = new ArrayList<>();
		String sql = "select input_type_id, input_type from input_types";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				inputTypes.add(new InputType(
					rs.getInt(1),
					rs.getString(2)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inputTypes;
	}

}
