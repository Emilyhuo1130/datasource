package com.ucs.rcm.reqobj;

public class ReqsearchEquipmentInfo {
	private int id=0;
	private String stationName=null;
	private String systemName=null;
	private String component=null;
	private String subComponent=null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	public String getComponent() {
		return component;
	}
	public void setComponent(String component) {
		this.component = component;
	}
	public String getSubComponent() {
		return subComponent;
	}
	public void setSubComponent(String subComponent) {
		this.subComponent = subComponent;
	}
	
}
