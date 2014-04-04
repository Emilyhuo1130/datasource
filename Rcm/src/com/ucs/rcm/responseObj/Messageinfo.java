package com.ucs.rcm.responseObj;

public class Messageinfo {
	private String warningId;
	private String message;
	private String infodate;
	private String fromuser;
	
	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
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
	public String getInfodate() {
		return infodate;
	}
	public void setInfodate(String infodate) {
		this.infodate = infodate;
	}
	
}
