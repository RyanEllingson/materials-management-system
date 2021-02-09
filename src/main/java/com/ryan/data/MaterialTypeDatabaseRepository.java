package com.ryan.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ryan.models.MaterialType;

public class MaterialTypeDatabaseRepository implements MaterialTypeRepository {
	private Connection conn;
	
	public MaterialTypeDatabaseRepository(Connection conn) {
		this.conn = conn;
	}

	@Override
	public MaterialType getMaterialTypeById(int materialTypeId) {
		MaterialType materialType = new MaterialType();
		String sql = "select material_type_id, material_type from material_types where material_type_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, materialTypeId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				materialType.setMaterialTypeId(rs.getInt(1));
				materialType.setMaterialType(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return materialType;
	}

	@Override
	public List<MaterialType> getAllMaterialTypes() {
		List<MaterialType> materialTypes = new ArrayList<>();
		String sql = "select material_type_id, material_type from material_types";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				materialTypes.add(new MaterialType(
					rs.getInt(1),
					rs.getString(2)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return materialTypes;
	}

}
