package com.ucs.rcm.pojo;

/**
 * Faultinfo entity. @author MyEclipse Persistence Tools
 */

public class Faultinfo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String componentid;
	private String equipmentname;
	private String equipmentid;
	private String systemname;
	private String equipmentDescription;
	private String faulttype;
	private String fultDescription;
	private String faultCause;
	private String influence;
	private String maintenancePolicy;
	private String subcomponent;

	// Constructors

	/** default constructor */
	public Faultinfo() {
	}
	public Faultinfo(String componentid, String subcomponent) {
		super();
		this.componentid = componentid;
		this.subcomponent = subcomponent;
	}
	
	public Faultinfo(Integer id, String equipmentid, String subcomponent) {
		super();
		this.id = id;
		this.equipmentid = equipmentid;
		this.subcomponent = subcomponent;
	}
	/** minimal constructor */
	public Faultinfo(String componentid, String equipmentname,
			String equipmentid, String systemname, String equipmentDescription,
			String faulttype, String fultDescription, String faultCause,
			String influence, String maintenancePolicy) {
		this.componentid = componentid;
		this.equipmentname = equipmentname;
		this.equipmentid = equipmentid;
		this.systemname = systemname;
		this.equipmentDescription = equipmentDescription;
		this.faulttype = faulttype;
		this.fultDescription = fultDescription;
		this.faultCause = faultCause;
		this.influence = influence;
		this.maintenancePolicy = maintenancePolicy;
	}

	/** full constructor */
	public Faultinfo(String componentid, String equipmentname,
			String equipmentid, String systemname, String equipmentDescription,
			String faulttype, String fultDescription, String faultCause,
			String influence, String maintenancePolicy, String subcomponent) {
		this.componentid = componentid;
		this.equipmentname = equipmentname;
		this.equipmentid = equipmentid;
		this.systemname = systemname;
		this.equipmentDescription = equipmentDescription;
		this.faulttype = faulttype;
		this.fultDescription = fultDescription;
		this.faultCause = faultCause;
		this.influence = influence;
		this.maintenancePolicy = maintenancePolicy;
		this.subcomponent = subcomponent;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComponentid() {
		return this.componentid;
	}

	public void setComponentid(String componentid) {
		this.componentid = componentid;
	}

	public String getEquipmentname() {
		return this.equipmentname;
	}

	public void setEquipmentname(String equipmentname) {
		this.equipmentname = equipmentname;
	}

	public String getEquipmentid() {
		return this.equipmentid;
	}

	public void setEquipmentid(String equipmentid) {
		this.equipmentid = equipmentid;
	}

	public String getSystemname() {
		return this.systemname;
	}

	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}

	public String getEquipmentDescription() {
		return this.equipmentDescription;
	}

	public void setEquipmentDescription(String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public String getFaulttype() {
		return this.faulttype;
	}

	
	public Faultinfo(String faultCause,Integer id) {
		super();
		this.id = id;
		this.faultCause = faultCause;
	}

	public void setFaulttype(String faulttype) {
		this.faulttype = faulttype;
	}

	public String getFultDescription() {
		return this.fultDescription;
	}

	public void setFultDescription(String fultDescription) {
		this.fultDescription = fultDescription;
	}

	public String getFaultCause() {
		return this.faultCause;
	}

	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}

	public String getInfluence() {
		return this.influence;
	}

	public void setInfluence(String influence) {
		this.influence = influence;
	}

	public String getMaintenancePolicy() {
		return this.maintenancePolicy;
	}

	public void setMaintenancePolicy(String maintenancePolicy) {
		this.maintenancePolicy = maintenancePolicy;
	}

	public String getSubcomponent() {
		return this.subcomponent;
	}

	public void setSubcomponent(String subcomponent) {
		this.subcomponent = subcomponent;
	}

}