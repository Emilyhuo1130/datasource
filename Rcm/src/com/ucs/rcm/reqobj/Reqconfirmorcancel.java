package com.ucs.rcm.reqobj;

public class Reqconfirmorcancel {
	private String warningId;
	private String message; //开始操作记录
	private String time;//开始操作的时间
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
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	

	
}
