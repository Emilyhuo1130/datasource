package com.ucs.rcm.pojo;

/**
 * Hisdata201308 entity. @author MyEclipse Persistence Tools
 */

public class Hisdata201308 implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String pointcode;
	private Integer savetime;
	private Boolean flag;
	private Short status;
	private Double value;
	private Integer opertime;
	private String componentId;

	// Constructors

	/** default constructor */
	public Hisdata201308() {
	}

	/** minimal constructor */
	public Hisdata201308(Integer savetime, Boolean flag, Double value) {
		this.savetime = savetime;
		this.flag = flag;
		this.value = value;
	}

	/** full constructor */
	public Hisdata201308(String pointcode, Integer savetime, Boolean flag,
			Short status, Double value, Integer opertime, String componentId) {
		this.pointcode = pointcode;
		this.savetime = savetime;
		this.flag = flag;
		this.status = status;
		this.value = value;
		this.opertime = opertime;
		this.componentId = componentId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPointcode() {
		return this.pointcode;
	}

	public void setPointcode(String pointcode) {
		this.pointcode = pointcode;
	}

	public Integer getSavetime() {
		return this.savetime;
	}

	public void setSavetime(Integer savetime) {
		this.savetime = savetime;
	}

	public Boolean getFlag() {
		return this.flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Integer getOpertime() {
		return this.opertime;
	}

	public void setOpertime(Integer opertime) {
		this.opertime = opertime;
	}

	public String getComponentId() {
		return this.componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

}