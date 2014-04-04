package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * ReportformsEquipmentRecord entity. @author MyEclipse Persistence Tools
 */

public class ReportformsEquipmentRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String systemName;
	private String subsystemName;
	private String lineNo;
	private String stationName;
	private String component;
	private String componentId;
	private String locationId;
	private String modelstandard;
	private String outTime;
	private String buyTime;
	private String productionHouse;
	private String productionCoding;

	// Constructors

	/** default constructor */
	public ReportformsEquipmentRecord() {
	}

	/** minimal constructor */
	public ReportformsEquipmentRecord(String outTime, String buyTime) {
		this.outTime = outTime;
		this.buyTime = buyTime;
	}

	/** full constructor */
	public ReportformsEquipmentRecord(String systemName, String subsystemName,
			String lineNo, String stationName, String component,
			String componentId, String locationId, String modelstandard,
			String outTime, String buyTime, String productionHouse,
			String productionCoding) {
		this.systemName = systemName;
		this.subsystemName = subsystemName;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.component = component;
		this.componentId = componentId;
		this.locationId = locationId;
		this.modelstandard = modelstandard;
		this.outTime = outTime;
		this.buyTime = buyTime;
		this.productionHouse = productionHouse;
		this.productionCoding = productionCoding;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSubsystemName() {
		return this.subsystemName;
	}

	public void setSubsystemName(String subsystemName) {
		this.subsystemName = subsystemName;
	}

	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getComponentId() {
		return this.componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getModelstandard() {
		return this.modelstandard;
	}

	public void setModelstandard(String modelstandard) {
		this.modelstandard = modelstandard;
	}

	public String getOutTime() {
		return this.outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getBuyTime() {
		return this.buyTime;
	}

	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}

	public String getProductionHouse() {
		return this.productionHouse;
	}

	public void setProductionHouse(String productionHouse) {
		this.productionHouse = productionHouse;
	}

	public String getProductionCoding() {
		return this.productionCoding;
	}

	public void setProductionCoding(String productionCoding) {
		this.productionCoding = productionCoding;
	}

}