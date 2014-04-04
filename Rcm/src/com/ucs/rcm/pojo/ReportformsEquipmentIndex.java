package com.ucs.rcm.pojo;

/**
 * ReportformsEquipmentIndex entity. @author MyEclipse Persistence Tools
 */

public class ReportformsEquipmentIndex implements java.io.Serializable {

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
	private String locationId;
	private String componentDescription;
	private String healthLevel;

	// Constructors

	/** default constructor */
	public ReportformsEquipmentIndex() {
	}

	/** full constructor */
	public ReportformsEquipmentIndex(String systemName, String subsystemName,
			String lineNo, String stationName, String component,
			String locationId, String componentDescription, String healthLevel) {
		this.systemName = systemName;
		this.subsystemName = subsystemName;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.component = component;
		this.locationId = locationId;
		this.componentDescription = componentDescription;
		this.healthLevel = healthLevel;
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

	public String getLocationId() {
		return this.locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getComponentDescription() {
		return this.componentDescription;
	}

	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	public String getHealthLevel() {
		return this.healthLevel;
	}

	public void setHealthLevel(String healthLevel) {
		this.healthLevel = healthLevel;
	}

}