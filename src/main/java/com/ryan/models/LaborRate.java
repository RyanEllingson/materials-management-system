package com.ryan.models;

import java.math.BigDecimal;

public class LaborRate {
	private int laborRateId;
	private String description;
	private BigDecimal ratePerHour;
	
	public LaborRate() {
		super();
	}

	public LaborRate(int laborRateId, String description, BigDecimal ratePerHour) {
		super();
		this.laborRateId = laborRateId;
		this.description = description;
		this.ratePerHour = ratePerHour;
	}

	public int getLaborRateId() {
		return laborRateId;
	}

	public void setLaborRateId(int laborRateId) {
		this.laborRateId = laborRateId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getRatePerHour() {
		return ratePerHour;
	}

	public void setRatePerHour(BigDecimal ratePerHour) {
		this.ratePerHour = ratePerHour;
	}

	@Override
	public String toString() {
		return "LaborRate [laborRateId=" + laborRateId + ", description=" + description + ", ratePerHour=" + ratePerHour
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + laborRateId;
		result = prime * result + ((ratePerHour == null) ? 0 : ratePerHour.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LaborRate other = (LaborRate) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (laborRateId != other.laborRateId)
			return false;
		if (ratePerHour == null) {
			if (other.ratePerHour != null)
				return false;
		} else if (!ratePerHour.equals(other.ratePerHour))
			return false;
		return true;
	}
	

}
