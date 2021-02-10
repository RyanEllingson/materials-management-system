package com.ryan.data;

import java.util.List;

import com.ryan.models.LaborRate;

public interface LaborRateRepository {
	
	public int createLaborRate(LaborRate laborRate);
	
	public LaborRate getLaborRateById(int laborRateId);
	
	public List<LaborRate> getAllLaborRates();
	
	public int updateLaborRate(LaborRate laborRate);
	
	public int deleteLaborRateById(int laborRateId);

}
