package com.ryan.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ryan.models.Unit;

public class UnitDatabaseRepository implements UnitRepository {
	private Connection conn;
	
	public UnitDatabaseRepository(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Unit getUnitById(int unitId) {
		Unit unit = new Unit();
		String sql = "select unit_id, unit from units where unit_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, unitId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				unit.setUnitId(rs.getInt(1));
				unit.setUnit(rs.getString(2));
			}
		} catch (SQLException e) {
			
		}
		return unit;
	}

	@Override
	public List<Unit> getAllUnits() {
		List<Unit> units = new ArrayList<>();
		String sql = "select unit_id, unit from units";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				units.add(new Unit(
					rs.getInt(1),
					rs.getString(2)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return units;
	}

}
