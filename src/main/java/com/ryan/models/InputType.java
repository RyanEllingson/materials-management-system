package com.ryan.models;

public class InputType {
	private int materialTypeId;
	private String materialType;
	
	public InputType() {
		super();
	}

	public InputType(int materialTypeId, String materialType) {
		super();
		this.materialTypeId = materialTypeId;
		this.materialType = materialType;
	}

	public int getMaterialTypeId() {
		return materialTypeId;
	}

	public void setMaterialTypeId(int materialTypeId) {
		this.materialTypeId = materialTypeId;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	@Override
	public String toString() {
		return "MaterialType [materialTypeId=" + materialTypeId + ", materialType=" + materialType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((materialType == null) ? 0 : materialType.hashCode());
		result = prime * result + materialTypeId;
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
		InputType other = (InputType) obj;
		if (materialType == null) {
			if (other.materialType != null)
				return false;
		} else if (!materialType.equals(other.materialType))
			return false;
		if (materialTypeId != other.materialTypeId)
			return false;
		return true;
	}
	
	

}
