package com.ucs.rcm.reqobj;

public class ReqgetEquipmentinfo {
	private int id=0;
	private String systemName=null;//系统名称
	private String equipmentName=null;//资产名称
	private String subcomponent=null;//部件名称
	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getSubcomponent() {
		return subcomponent;
	}
	public void setSubcomponent(String subcomponent) {
		this.subcomponent = subcomponent;
	}
	
	
	
}
