package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * LineHealth entity. @author MyEclipse Persistence Tools
 */

public class LineHealth implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lineNo;
	private String saveTime;
	private Float value;

	// Constructors

	/** default constructor */
	public LineHealth() {
	}

	/** minimal constructor */
	public LineHealth(String lineNo, Float value) {
		this.lineNo = lineNo;
		this.value = value;
	}

	/** full constructor */
	public LineHealth(String lineNo, String saveTime, Float value) {
		this.lineNo = lineNo;
		this.saveTime = saveTime;
		this.value = value;
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

	public String getSaveTime() {
		return this.saveTime;
	}

	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}

	public Float getValue() {
		return this.value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

}