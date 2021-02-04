package com.ryan.models;

public class Material {
	private int materialId;
	private String materialName;
	private MaterialType materialType;
	
	public Material() {
		super();
	}

	public Material(int materialId, String materialName, MaterialType materialType) {
		super();
		this.materialId = materialId;
		this.materialName = materialName;
		this.materialType = materialType;
	}

	public int getMaterialId() {
		return materialId;
	}

	public void setMaterialId(int materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public MaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(MaterialType materialType) {
		this.materialType = materialType;
	}

	@Override
	public String toString() {
		return "Material [materialId=" + materialId + ", materialName=" + materialName + ", materialType="
				+ materialType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + materialId;
		result = prime * result + ((materialName == null) ? 0 : materialName.hashCode());
		result = prime * result + ((materialType == null) ? 0 : materialType.hashCode());
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
		Material other = (Material) obj;
		if (materialId != other.materialId)
			return false;
		if (materialName == null) {
			if (other.materialName != null)
				return false;
		} else if (!materialName.equals(other.materialName))
			return false;
		if (materialType == null) {
			if (other.materialType != null)
				return false;
		} else if (!materialType.equals(other.materialType))
			return false;
		return true;
	}
	
	

}
