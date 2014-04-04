package com.ucs.rcm.responseObj;

public class HistoryWarning {
	private int id;
	private String systemName;
	private String subSystemName;
	private String lineNo;
	private String stationName;
	private String component;
	private String warninginfo;
	private String warningTypeLevel;
	private String warningstatments;
	private String warningTime;
	private String warningType;
	
	public String getWarningType() {
		return warningType;
	}
	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}
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
	public String getSubSystemName() {
		return subSystemName;
	}
	public void setSubSystemName(String subSystemName) {
		this.subSystemName = subSystemName;
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
	public String getWarninginfo() {
		return warninginfo;
	}
	public void setWarninginfo(String warninginfo) {
		this.warninginfo = warninginfo;
	}
	public String getWarningTypeLevel() {
		return warningTypeLevel;
	}
	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}
	public String getWarningstatments() {
		return warningstatments;
	}
	public void setWarningstatments(String warningstatments) {
		this.warningstatments = warningstatments;
	}
	public String getWarningTime() {
		return warningTime;
	}
	public void setWarningTime(String warningTime) {
		this.warningTime = warningTime;
	}
	
}
