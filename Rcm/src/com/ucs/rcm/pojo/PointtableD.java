package com.ucs.rcm.pojo;

/**
 * PointtableD entity. @author MyEclipse Persistence Tools
 */

public class PointtableD implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String pointCode;
	private String pointDesc;
	private String groupCode;
	private String deviceCode;
	private String appSysId;

	// Constructors

	/** default constructor */
	public PointtableD() {
	}

	/** full constructor */
	public PointtableD(String pointCode, String pointDesc, String groupCode,
			String deviceCode, String appSysId) {
		this.pointCode = pointCode;
		this.pointDesc = pointDesc;
		this.groupCode = groupCode;
		this.deviceCode = deviceCode;
		this.appSysId = appSysId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPointCode() {
		return this.pointCode;
	}

	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}

	public String getPointDesc() {
		return this.pointDesc;
	}

	public void setPointDesc(String pointDesc) {
		this.pointDesc = pointDesc;
	}

	public String getGroupCode() {
		return this.groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getDeviceCode() {
		return this.deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getAppSysId() {
		return this.appSysId;
	}

	public void setAppSysId(String appSysId) {
		this.appSysId = appSysId;
	}

}