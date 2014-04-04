package com.ucs.cctv.Pojo;

import java.util.HashSet;
import java.util.Set;

public class SectionInfo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer sectionId;
	private String sectionName;
	private String remark;
	private Set<CameraInfo> cameraMember = new HashSet<CameraInfo>();
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public Set<CameraInfo> getCameraMember() {
		return cameraMember;
	}
	public void setCameraMember(Set<CameraInfo> cameraMembers1) {
		this.cameraMember = cameraMembers1;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
