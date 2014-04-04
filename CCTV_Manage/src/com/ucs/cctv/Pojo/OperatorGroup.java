package com.ucs.cctv.Pojo;

import java.util.List;

public class OperatorGroup {

	private Integer operatorId;
	private String operatorName;
	private String phoneNumber;
	private String operatorLevel;
	private String remark;
	private String operatorAccount;
	private String operatorPw;
	private List<String> operatorNames;
	
	public List<String> getOperatorNames() {
		return operatorNames;
	}
	public void setOperatorNames(List<String> operatorNames) {
		this.operatorNames = operatorNames;
	}
	public Integer getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getOperatorLevel() {
		return operatorLevel;
	}
	public void setOperatorLevel(String operatorLevel) {
		this.operatorLevel = operatorLevel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperatorAccount() {
		return operatorAccount;
	}
	public void setOperatorAccount(String operatorAccount) {
		this.operatorAccount = operatorAccount;
	}
	public String getOperatorPw() {
		return operatorPw;
	}
	public void setOperatorPw(String operatorPw) {
		this.operatorPw = operatorPw;
	}
}

