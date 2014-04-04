package com.ucs.rcm.pojo;

/**
 * Influence entity. @author MyEclipse Persistence Tools
 */

public class Influence implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String componentid;
	private String influenceInfo;

	// Constructors

	/** default constructor */
	public Influence() {
	}

	/** full constructor */
	public Influence(String componentid, String influenceInfo) {
		this.componentid = componentid;
		this.influenceInfo = influenceInfo;
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

	public String getInfluenceInfo() {
		return this.influenceInfo;
	}

	public void setInfluenceInfo(String influenceInfo) {
		this.influenceInfo = influenceInfo;
	}

}