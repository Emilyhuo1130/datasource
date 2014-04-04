package com.ucs.rcm.pojo;

import java.lang.String;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Facilityindex entity. @author MyEclipse Persistence Tools
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Facilityindex implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String componentId;
	private String equipmentname;
	private String systemName;
	private String subsystemname;
	private String lineNo;
	private String stationName;
	private String state;
	private String woringCount;
	private String updatetime;
	private String healthIndex;

	// Constructors

	/** default constructor */
	public Facilityindex() {
	}

	/** minimal constructor */
	public Facilityindex(String componentId, String equipmentname,
			String systemName, String lineNo, String state, String woringCount,
			String updatetime, String healthIndex) {
		this.componentId = componentId;
		this.equipmentname = equipmentname;
		this.systemName = systemName;
		this.lineNo = lineNo;
		this.state = state;
		this.woringCount = woringCount;
		this.updatetime = updatetime;
		this.healthIndex = healthIndex;
	}

	/** full constructor */
	public Facilityindex(String componentId, String equipmentname,
			String systemName, String subsystemname, String lineNo,
			String stationName, String state, String woringCount,
			String updatetime, String healthIndex) {
		this.componentId = componentId;
		this.equipmentname = equipmentname;
		this.systemName = systemName;
		this.subsystemname = subsystemname;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.state = state;
		this.woringCount = woringCount;
		this.updatetime = updatetime;
		this.healthIndex = healthIndex;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComponentId() {
		return this.componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getEquipmentname() {
		return this.equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getSubsystemname() {
		return this.subsystemname;
	}

	public void setSubsystemname(String subsystemname) {
		this.subsystemname = subsystemname;
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

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getWoringCount() {
		return this.woringCount;
	}

	public void setWoringCount(String woringCount) {
		this.woringCount = woringCount;
	}

	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getHealthIndex() {
		return this.healthIndex;
	}

	public void setHealthIndex(String healthIndex) {
		this.healthIndex = healthIndex;
	}

}