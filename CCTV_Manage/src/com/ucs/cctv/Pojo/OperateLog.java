package com.ucs.cctv.Pojo;

// Generated 2013-11-18 14:45:18 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

public class OperateLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer logId;
	
	private String operatorAccount;
	private Date operateTime;
	private String operateType;
	private String remark;
	private String operatorFormatDate;

	public String getOperatorFormatDate() {
		return operatorFormatDate;
	}

	public void setOperatorFormatDate(String operatorFormatDate) {
		this.operatorFormatDate = operatorFormatDate;
	}

	public OperateLog() {
	}

	public OperateLog(Date operateTime, String operateType, Integer logId) {
		this.operateTime = operateTime;
		this.operateType = operateType;
		this.logId = logId;
	}

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		if(operateTime==null){
			operateTime = new Date();
		}else{
		this.operateTime = operateTime;
		}
	}

	public String getOperateType() {
		return this.operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	
	public String getOperatorAccount() {
		return operatorAccount;
	}

	public void setOperatorAccount(String operatorAccount) {
		this.operatorAccount = operatorAccount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
