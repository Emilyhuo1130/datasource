package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * Historywarning entity. @author MyEclipse Persistence Tools
 */

public class Historywarning implements java.io.Serializable {

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
	private String warningType;
	private String warninginfo;
	private String warningTypeLevel;
	private String warningstatments;
	private String warningDate;

	// Constructors

	/** default constructor */
	public Historywarning() {
	}

	/** full constructor */
	public Historywarning(String systemName, String subsystemName,
			String lineNo, String stationName, String component,
			String warningType, String warninginfo, String warningTypeLevel,
			String warningstatments, String warningDate) {
		this.systemName = systemName;
		this.subsystemName = subsystemName;
		this.lineNo = lineNo;
		this.stationName = stationName;
		this.component = component;
		this.warningType = warningType;
		this.warninginfo = warninginfo;
		this.warningTypeLevel = warningTypeLevel;
		this.warningstatments = warningstatments;
		this.warningDate = warningDate;
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

	public String getWarningType() {
		return this.warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getWarninginfo() {
		return this.warninginfo;
	}

	public void setWarninginfo(String warninginfo) {
		this.warninginfo = warninginfo;
	}

	public String getWarningTypeLevel() {
		return this.warningTypeLevel;
	}

	public void setWarningTypeLevel(String warningTypeLevel) {
		this.warningTypeLevel = warningTypeLevel;
	}

	public String getWarningstatments() {
		return this.warningstatments;
	}

	public void setWarningstatments(String warningstatments) {
		this.warningstatments = warningstatments;
	}

	public String getWarningDate() {
		return this.warningDate;
	}

	public void setWarningDate(String warningDate) {
		this.warningDate = warningDate;
	}

}