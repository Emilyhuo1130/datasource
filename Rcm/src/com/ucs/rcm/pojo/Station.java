package com.ucs.rcm.pojo;

/**
 * Station entity. @author MyEclipse Persistence Tools
 */

public class Station implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer groupno;
	private String lineNo;
	private String stationid;
	private String stationName;

	// Constructors

	/** default constructor */
	public Station() {
	}

	/** minimal constructor */
	public Station(String lineNo, String stationid, String stationName) {
		this.lineNo = lineNo;
		this.stationid = stationid;
		this.stationName = stationName;
	}

	/** full constructor */
	public Station(Integer groupno, String lineNo, String stationid,
			String stationName) {
		this.groupno = groupno;
		this.lineNo = lineNo;
		this.stationid = stationid;
		this.stationName = stationName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupno() {
		return this.groupno;
	}

	public void setGroupno(Integer groupno) {
		this.groupno = groupno;
	}

	public String getLineNo() {
		return this.lineNo;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getStationid() {
		return this.stationid;
	}

	public void setStationid(String stationid) {
		this.stationid = stationid;
	}

	public String getStationName() {
		return this.stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

}