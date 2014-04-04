package com.ucs.rcm.pojo;

/**
 * Equipmenttree entity. @author MyEclipse Persistence Tools
 */

public class Equipmenttree implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String component;
	private String equipment;
	private String systemname;

	// Constructors

	/** default constructor */
	public Equipmenttree() {
	}

	public Equipmenttree(String systemname, String equipment) {
		super();
		this.systemname = systemname;
		this.equipment = equipment;
	}

	/** full constructor */
	public Equipmenttree(String component, String equipment, String systemname) {
		this.component = component;
		this.equipment = equipment;
		this.systemname = systemname;
	}

	// Property accessors

	public Equipmenttree(Integer id, String component) {
		super();
		this.id = id;
		this.component = component;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getEquipment() {
		return this.equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getSystemname() {
		return this.systemname;
	}

	public void setSystemname(String systemname) {
		this.systemname = systemname;
	}

}