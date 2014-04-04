package com.ucs.rcm.reqobj;

public class PlanInformQueryObj {
	private String systemName;
	private String subSystemName;
	private String lineNO;
	private String stationName;
	private String equipmentName;
	private String warningTypeLevel;//小修 中修 大修
	private String starTime;
	private String endTime;
	
	public String getSubSystemName() {
		return subSystemName;
	}
	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getLineNO() {
		return lineNO;
	}
	public void setLineNO(String lineNO) {
		this.lineNO = lineNO;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getWarningTypeLevel() {
		return warningTypeLevel;
	}
	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
