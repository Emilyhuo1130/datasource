package com.ucs.rcm.pojo;

/**
 * Equipmenttreeinfo entity. @author MyEclipse Persistence Tools
 */

public class Equipmenttreeinfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lineNo;
	private String stationName;
	private String systemname;
	private String subsystemname;
	private String component;
	
	private String subcomponent;
	public Equipmenttreeinfo(Integer id, String subcomponent) {
		super();
		this.id = id;
		this.subcomponent = subcomponent;
	}

	public Equipmenttreeinfo( String component,String subsystemname) {
		super();
		this.subsystemname = subsystemname;
		this.component = component;
	}

	public Equipmenttreeinfo(String subsystemname, String component,
			String subcomponentid) {
		super();
		this.subsystemname = systemname;
		this.component = component;
		this.subcomponentid = subcomponentid;
	}

	private String subcomponentid;
	private String maintenanceId;
	private String level;

	// Constructors

	/** default constructor */
	public Equipmenttreeinfo() {
	}



	/** minimal constructor */
	public Equipmenttreeinfo(String stationName, String systemname,
			String subsystemname, String component, String subcomponent,
			String subcomponentid, String level) {
		this.stationName = stationName;
		this.systemname = systemname;
		this.subsystemname = subsystemname;
		this.component = component;
		this.subcomponent = subcomponent;
		this.subcomponentid = subcomponentid;
		this.level = level;
	}

	/** full constructor */
	public Equipmenttreeinfo(String lineNo, String stationName,
			String systemname, String subsystemname, String component,
			String subcomponent, String subcomponentid, String maintenanceId,
			String level) {
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.systemname = systemname;
		this.subsystemname = subsystemname;
		this.component = component;
		this.subcomponent = subcomponent;
		this.subcomponentid = subcomponentid;
		this.maintenanceId = maintenanceId;
		this.level = level;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getSystemname() {
		return this.systemname;
	}

	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}

	public String getSubsystemname() {
		return this.subsystemname;
	}

	public void setSubsystemname(String subsystemname) {
		this.subsystemname = subsystemname;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getSubcomponent() {
		return this.subcomponent;
	}

	public void setSubcomponent(String subcomponent) {
		this.subcomponent = subcomponent;
	}

	public String getSubcomponentid() {
		return this.subcomponentid;
	}

	public void setSubcomponentid(String subcomponentid) {
		this.subcomponentid = subcomponentid;
	}

	public String getMaintenanceId() {
		return this.maintenanceId;
	}

	public void setMaintenanceId(String maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

}