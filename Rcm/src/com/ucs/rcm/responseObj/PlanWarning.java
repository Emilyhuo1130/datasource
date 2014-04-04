package com.ucs.rcm.responseObj;

public class PlanWarning {
	private String id;//序号
	private String equipmentId;//设备id
	private String equipmentname;//设备名称
	private String subsystemName;
	private String systemName;//所属系统
	private String lineNo;//线号
	private String stationName;//站台名称
	private String warningType;//警告类型 
	private String warningTypeLevel;//警告类型级别
	private String warninginfo;//警告内容
	private String warningDate;//警告日期
	private String statments;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentname() {
		return equipmentname;
	}
	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
	public String getWarningTypeLevel() {
		return warningTypeLevel;
	}
	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}
	public String getWarninginfo() {
		return warninginfo;
	}
	public void setWarninginfo(String warninginfo) {
		this.warninginfo = warninginfo;
	}
	public String getWarningDate() {
		return warningDate;
	}
	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
	}
	public String getStatments() {
		return statments;
	}
	public void setStatments(String statments) {
		this.statments = statments;
	}
	
	
}
