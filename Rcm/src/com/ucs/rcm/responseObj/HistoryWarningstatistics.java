package com.ucs.rcm.responseObj;

public class HistoryWarningstatistics {
	private String systemName;
	private String subSystemName;
	private String lineNo;
	private String stationName;
	private String component;
	private int count;
	
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
