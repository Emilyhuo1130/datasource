package com.ucs.cctv.Pojo;

public class RecorderInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer recorderId;
	private String recorderName;
	private String recorderModel;
	private String recorderIp;
	private String recorderMac;
	private Integer capacity;
	private String recorderType;

	public RecorderInfo() {
	}

	public RecorderInfo(String recorderName, String recorderModel,
			String recorderIp, String recorderMac, Integer capacity,
			String recorderType) {
		this.recorderName = recorderName;
		this.recorderModel = recorderModel;
		this.recorderIp = recorderIp;
		this.recorderMac = recorderMac;
		this.capacity = capacity;
		this.recorderType = recorderType;
	}

	public String getRecorderName() {
		return this.recorderName;
	}

	public void setRecorderName(String recorderName) {
		this.recorderName = recorderName;
	}

	public String getRecorderModel() {
		return this.recorderModel;
	}

	public void setRecorderModel(String recorderModel) {
		this.recorderModel = recorderModel;
	}

	public String getRecorderIp() {
		return this.recorderIp;
	}

	public void setRecorderIp(String recorderIp) {
		this.recorderIp = recorderIp;
	}

	public String getRecorderMac() {
		return this.recorderMac;
	}

	public void setRecorderMac(String recorderMac) {
		this.recorderMac = recorderMac;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getRecorderType() {
		return this.recorderType;
	}

	public void setRecorderType(String recorderType) {
		this.recorderType = recorderType;
	}

	public Integer getRecorderId() {
		return recorderId;
	}

	public void setRecorderId(Integer recorderId) {
		this.recorderId = recorderId;
	}

}
