package com.ucs.cctv.Pojo;
import java.util.HashSet;
import java.util.Set;

/**
 * OperatorInfo generated by hbm2java
 */
public class OperatorInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer operatorId;
	private String operatorName;
	private String phoneNumber;
	private String operatorLevel;
	private String remark;
	private String operatorAccount;
	private String operatorPw;
	private Set<MonitorGroup> monitorGroups = new HashSet<MonitorGroup>();
	private Set<String> monitorGroupNames = new HashSet<String>();

	
	
	public Set<String> getMonitorGroupNames() {
		return monitorGroupNames;
	}

	public void setMonitorGroupNames(Set<String> monitorGroupNames) {
		this.monitorGroupNames = monitorGroupNames;
	}

	public OperatorInfo() {
	}

	public OperatorInfo(String operatorAccount) {
		this.operatorAccount = operatorAccount;
	}

	public OperatorInfo(String operatorName, String phoneNumber,
			String operatorLevel, String remark, String operatorAccount) {
		this.operatorName = operatorName;
		this.phoneNumber = phoneNumber;
		this.operatorLevel = operatorLevel;
		this.remark = remark;
		this.operatorAccount = operatorAccount;
	}

	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOperatorLevel() {
		return this.operatorLevel;
	}

	public void setOperatorLevel(String operatorLevel) {
		this.operatorLevel = operatorLevel;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperatorAccount() {
		return this.operatorAccount;
	}

	public void setOperatorAccount(String operatorAccount) {
		this.operatorAccount = operatorAccount;
	}

	public Set<MonitorGroup> getMonitorGroups() {
		return monitorGroups;
	}

	public void setMonitorGroups(Set<MonitorGroup> monitorGroups1) {
		this.monitorGroups = monitorGroups1;
	}

	public Integer getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Integer operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorPw() {
		return operatorPw;
	}

	public void setOperatorPw(String operatorPw) {
		this.operatorPw = operatorPw;
	}

}
