package com.ryan.data;

import java.util.List;

import com.ryan.models.Unit;

public interface UnitRepository {
	
	public Unit getUnitById(int unitId);
	
	public List<Unit> getAllUnits();

}
