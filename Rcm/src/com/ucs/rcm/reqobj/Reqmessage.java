package com.ucs.rcm.reqobj;

public class Reqmessage {
	private String warningId;
	private String starMessage; //开始操作记录
	private String endMessage;//结束操作记录
	private String starTime;//开始操作的时间
	private String endTime;//结束操作的时间
	private String fromuser;//来自哪个操作用户
	private String ok;
	
	
	
	
	public String getOk() {
		return ok;
	}
	public void setOk(String ok) {
		this.ok = ok;
	}
	public String getWarningId() {
		return warningId;
	}
	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}
	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	public String getStarMessage() {
		return starMessage;
	}
	public void setStarMessage(String starMessage) {
		this.starMessage = starMessage;
	}
	public String getEndMessage() {
		return endMessage;
	}
	public void setEndMessage(String endMessage) {
		this.endMessage = endMessage;
	}
	public String getStarTime() {
		return starTime;
	}
	public void setStarTime(String starTime) {
		this.starTime = starTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
