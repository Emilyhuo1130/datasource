package com.ucs.rcm.responseObj;
//最终实体
public class Subcomponent {
	private int id;
	private String componentid;//所属类id
	private String equipmentName;//资产名称
	private String equipmentId;//资产编号
	private String systemName;//所属系统
	private String equipmentDescription;//部件详细描述
	private String level;//等级
	private String subcomponentId;//所属类
	private String upEquipment;//上一级设备
	private String subComponent;//部件
	
	
	public String getSubcomponentId() {
		return subcomponentId;
	}
	public void setSubcomponentId(String subcomponentId) {
		this.subcomponentId = subcomponentId;
	}
	public String getSubComponent() {
		return subComponent;
	}
	public void setSubComponent(String subComponent) {
		this.subComponent = subComponent;
	}
	public String getUpEquipment() {
		return upEquipment;
	}
	public void setUpEquipment(String upEquipment) {
		this.upEquipment = upEquipment;
	}
	public String getComponentid() {
		return componentid;
	}
	public void setComponentid(String componentid) {
		this.componentid = componentid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getEquipmentDescription() {
		return equipmentDescription;
	}
	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
	
}
