package com.ucs.rcm.reqobj;

public class RequpdateFaultInfo_user2ById {
	private int id;
	private String opinion;//审定意见
	private String ok;
	private String faultCause;
	private String influence;
	private String mainTenancePolicy;
	private String operator;
	
	
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getFaultCause() {
		return faultCause;
	}
	public void setFaultCause(String faultCause) {
		this.faultCause = faultCause;
	}
	public String getInfluence() {
		return influence;
	}
	public void setInfluence(String influence) {
		this.influence = influence;
	}
	public String getMainTenancePolicy() {
		return mainTenancePolicy;
	}
	public void setMainTenancePolicy(String mainTenancePolicy) {
		this.mainTenancePolicy = mainTenancePolicy;
	}
	public String getOk() {
		return ok;
	}
	public void setOk(String ok) {
		this.ok = ok;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
}
