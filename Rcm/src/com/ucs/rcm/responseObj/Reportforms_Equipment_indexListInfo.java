package com.ucs.rcm.responseObj;

public class Reportforms_Equipment_indexListInfo {
	private int id;
	private String systemName;
	private String subsystemName;
	private String lineNo;
	private String stationName;
	private String component;
	private String locationId;//位置编码
	private String componentDescription;
	private String healthLevel;
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
	public String getSubsystemName() {
		return subsystemName;
	}
	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
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
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getComponentDescription() {
		return componentDescription;
	}
	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}
	public String getHealthLevel() {
		return healthLevel;
	}
	public void setHealthLevel(String healthLevel) {
		this.healthLevel = healthLevel;
	}
	
}
