package com.ryan.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ryan.models.LaborRate;

public class LaborRateDatabaseRepository implements LaborRateRepository {
	private Connection conn;
	
	public LaborRateDatabaseRepository(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int createLaborRate(LaborRate laborRate) {
		int insertId = 0;
		String sql = "insert into labor_rates (description, rate_per_hour) values (?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, laborRate.getDescription());
			ps.setBigDecimal(2, laborRate.getRatePerHour());
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
	public LaborRate getLaborRateById(int laborRateId) {
		LaborRate laborRate = new LaborRate();
		String sql = "select labor_rate_id, description, rate_per_hour from labor_rates where labor_rate_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, laborRateId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				laborRate.setLaborRateId(rs.getInt(1));
				laborRate.setDescription(rs.getString(2));
				laborRate.setRatePerHour(rs.getBigDecimal(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return laborRate;
	}

	@Override
	public List<LaborRate> getAllLaborRates() {
		List<LaborRate> laborRates = new ArrayList<>();
		String sql = "select labor_rate_id, description, rate_per_hour from labor_rates";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				laborRates.add(new LaborRate(
					rs.getInt(1),
					rs.getString(2),
					rs.getBigDecimal(3)
				));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return laborRates;
	}

	@Override
	public int updateLaborRate(LaborRate laborRate) {
		int affectedRows = 0;
		String sql = "update labor_rates set description = ?, rate_per_hour = ? where labor_rate_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, laborRate.getDescription());
			ps.setBigDecimal(2, laborRate.getRatePerHour());
			ps.setInt(3, laborRate.getLaborRateId());
			affectedRows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

	@Override
	public int deleteLaborRateById(int laborRateId) {
		int affectedRows = 0;
		String sql = "delete from labor_rates where labor_rate_id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, laborRateId);
			affectedRows = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectedRows;
	}

}
