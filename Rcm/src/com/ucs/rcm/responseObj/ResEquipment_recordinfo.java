package com.ucs.rcm.responseObj;

public class ResEquipment_recordinfo {
	private int id;
	private String systemName;
	private String subsystemName;
	private String lineNo;
	private String stationName;
	private String component;
	private String componentId;//
	private String locationId;//位置编码
	private String modelstandard;//型号规格
	private String outTime;//出产时间
	private String buyTime;//购买时间
	private String Production_house;//产家
	private String Production_coding;//产检编码
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
	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getModelstandard() {
		return modelstandard;
	}
	public void setModelstandard(String modelstandard) {
		this.modelstandard = modelstandard;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public String getProduction_house() {
		return Production_house;
	}
	public void setProduction_house(String production_house) {
		Production_house = production_house;
	}
	public String getProduction_coding() {
		return Production_coding;
	}
	public void setProduction_coding(String production_coding) {
		Production_coding = production_coding;
	}
	
	
	
}
