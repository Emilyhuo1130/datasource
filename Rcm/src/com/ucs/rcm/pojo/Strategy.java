package com.ucs.rcm.pojo;

/**
 * Strategy entity. @author MyEclipse Persistence Tools
 */

public class Strategy implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String componentid;
	private String fultDescription;
	private String faultCause;
	private String maintenancePolicy;

	// Constructors

	/** default constructor */
	public Strategy() {
	}

	/** full constructor */
	public Strategy(String componentid, String fultDescription,
			String faultCause, String maintenancePolicy) {
		this.componentid = componentid;
		this.fultDescription = fultDescription;
		this.faultCause = faultCause;
		this.maintenancePolicy = maintenancePolicy;
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

	public String getMaintenancePolicy() {
		return this.maintenancePolicy;
	}

	public void setMaintenancePolicy(String maintenancePolicy) {
		this.maintenancePolicy = maintenancePolicy;
	}

}