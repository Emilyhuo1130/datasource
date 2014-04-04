package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * Operatestatistics entity. @author MyEclipse Persistence Tools
 */

public class Operatestatistics implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String lineNo;
	private String safetyDays;
	private String offstreamDays;
	private String noPlanoffstreamDays;
	private String month;
	private String updateDay;

	// Constructors

	/** default constructor */
	public Operatestatistics() {
	}

	/** full constructor */
	public Operatestatistics(String lineNo, String safetyDays,
			String offstreamDays, String noPlanoffstreamDays, String month,
			String updateDay) {
		this.lineNo = lineNo;
		this.safetyDays = safetyDays;
		this.offstreamDays = offstreamDays;
		this.noPlanoffstreamDays = noPlanoffstreamDays;
		this.month = month;
		this.updateDay = updateDay;
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

	public String getSafetyDays() {
		return this.safetyDays;
	}

	public void setSafetyDays(String safetyDays) {
		this.safetyDays = safetyDays;
	}

	public String getOffstreamDays() {
		return this.offstreamDays;
	}

	public void setOffstreamDays(String offstreamDays) {
		this.offstreamDays = offstreamDays;
	}

	public String getNoPlanoffstreamDays() {
		return this.noPlanoffstreamDays;
	}

	public void setNoPlanoffstreamDays(String noPlanoffstreamDays) {
		this.noPlanoffstreamDays = noPlanoffstreamDays;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getUpdateDay() {
		return this.updateDay;
	}

	public void setUpdateDay(String updateDay) {
		this.updateDay = updateDay;
	}

}