package com.ucs.cctv.Pojo;

import java.util.HashSet;
import java.util.Set;

public class CameraInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer cameraId;
	private String cameraName;
	private String cameraModel;
	private String cameraIp;
	private String cameraMac;
	private Integer capacity;
	private String section;
	private String cameraType;
	private Set<MonitorGroup> monitorGroups = new HashSet<MonitorGroup>(); 

	public CameraInfo() {
	}

	public CameraInfo(String cameraName, String cameraModel, String cameraIp,
			String cameraMac, Integer capacity, String section,
			String cameraType) {
		this.cameraName = cameraName;
		this.cameraModel = cameraModel;
		this.cameraIp = cameraIp;
		this.cameraMac = cameraMac;
		this.capacity = capacity;
		this.section = section;
		this.cameraType = cameraType;
	}
	
	public String getCameraName() {
		return this.cameraName;
	}

	public void setCameraName(String cameraName) {
		this.cameraName = cameraName;
	}

	public String getCameraModel() {
		return this.cameraModel;
	}

	public void setCameraModel(String cameraModel) {
		this.cameraModel = cameraModel;
	}

	public String getCameraIp() {
		return this.cameraIp;
	}

	public void setCameraIp(String cameraIp) {
		this.cameraIp = cameraIp;
	}

	public String getCameraMac() {
		return this.cameraMac;
	}

	public void setCameraMac(String cameraMac) {
		this.cameraMac = cameraMac;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getCameraType() {
		return this.cameraType;
	}

	public void setCameraType(String cameraType) {
		this.cameraType = cameraType;
	}

	public Set<MonitorGroup> getMonitorGroups() {
		return monitorGroups;
	}

	public void setMonitorGroups(Set<MonitorGroup> monitorGroups) {
		this.monitorGroups = monitorGroups;
	}

	public Integer getCameraId() {
		return cameraId;
	}

	public void setCameraId(Integer cameraId) {
		this.cameraId = cameraId;
	}

}
