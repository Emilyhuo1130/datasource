package com.ucs.rcm.pojo;

import java.lang.String;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String warningId;
	private String info;
	private String infodate;
	private String fromuser;
	private String statments;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(String info, String infodate) {
		this.info = info;
		this.infodate = infodate;
	}

	/** full constructor */
	public Message(String warningId, String info, String infodate,
			String fromuser, String statments) {
		this.warningId = warningId;
		this.info = info;
		this.infodate = infodate;
		this.fromuser = fromuser;
		this.statments = statments;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWarningId() {
		return this.warningId;
	}

	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfodate() {
		return this.infodate;
	}

	public void setInfodate(String infodate) {
		this.infodate = infodate;
	}

	public String getFromuser() {
		return this.fromuser;
	}

	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}

	public String getStatments() {
		return this.statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}

}