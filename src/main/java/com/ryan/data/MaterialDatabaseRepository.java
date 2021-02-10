package com.ryan.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ryan.models.Material;
import com.ryan.models.MaterialType;
import com.ryan.models.Unit;

public class MaterialDatabaseRepository implements MaterialRepository {
	private Connection conn;
	
	public MaterialDatabaseRepository(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int createMaterial(Material material) {
		int insertId = 0;
		String sql = "insert into materials (material_name, material_type_id, unit_id, unit_cost) values (?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, material.getMaterialName());
			ps.setInt(2, material.getMaterialType().getMaterialTypeId());
			ps.setInt(3, material.getUnit().getUnitId());
			ps.setBigDecimal(4, material.getUnitCost());
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				insertId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return insertId;
	}

	@Override
	public Material getMaterialById(int materialId) {
		Material material = new Material();
		String sql = "select material_id, material_name, material_type_id, unit_id, unit_cost from materials where material_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, materialId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				material.setMaterialId(rs.getInt(1));
				material.setMaterialName(rs.getString(2));
				material.setMaterialType(new MaterialType(rs.getInt(3), null));
				material.setUnit(new Unit(rs.getInt(4), null));
				material.setUnitCost(rs.getBigDecimal(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return material;
	}

	@Override
	public List<Material> getMaterialsByType(MaterialType materialType) {
		List<Material> materials = new ArrayList<>();
		String sql = "select material_id, material_name, material_type_id, unit_id, unit_cost from materials where material_type_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, materialType.getMaterialTypeId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				materials.add(new Material(
					rs.getInt(1),
					rs.getString(2),
					new MaterialType(rs.getInt(3), null),
					new Unit(rs.getInt(4), null),
					rs.getBigDecimal(5)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return materials;
	}

	@Override
	public int updateMaterial(Material material) {
		int affectedRows = 0;
		String sql = "update materials set material_name = ?, material_type_id = ?, unit_id = ?, unit_cost = ? where material_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, material.getMaterialName());
			ps.setInt(2, material.getMaterialType().getMaterialTypeId());
			ps.setInt(3, material.getUnit().getUnitId());
			ps.setBigDecimal(4, material.getUnitCost());
			ps.setInt(5, material.getMaterialId());
			affectedRows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public int deleteMaterialById(int materialId) {
		int affectedRows = 0;
		String sql = "delete from materials where material_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, materialId);
			affectedRows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

}
