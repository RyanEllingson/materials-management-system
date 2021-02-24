package com.ryan.models;

import java.math.BigDecimal;

public class Material {
	private int materialId;
	private String materialName;
	private MaterialType materialType;
	private Unit unit;
	private BigDecimal unitCost;
	private BigDecimal inventory;
	
	public Material() {
		super();
	}

	public Material(int materialId, String materialName, MaterialType materialType, Unit unit, BigDecimal unitCost, BigDecimal inventory) {
		super();
		this.materialId = materialId;
		this.materialName = materialName;
		this.materialType = materialType;
		this.unit = unit;
		this.unitCost = unitCost;
		this.inventory = inventory;
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

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}
	
	public BigDecimal getInventory() {
		return inventory;
	}
	
	public void setInventory(BigDecimal inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "Material [materialId=" + materialId + ", materialName=" + materialName + ", materialType="
				+ materialType + ", unit=" + unit + ", unitCost=" + unitCost + "]";
	}

}
