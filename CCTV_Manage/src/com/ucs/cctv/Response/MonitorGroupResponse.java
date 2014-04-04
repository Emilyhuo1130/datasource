package com.ucs.cctv.Response;

import java.util.List;

import com.ucs.cctv.Trees.SectionCameraTrees;

public class MonitorGroupResponse {
	String groupId;
	private String groupName;
	private String remark;
	private List<SectionCameraTrees> sectionCameraTrees;
	
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<SectionCameraTrees> getSectionCameraTrees() {
		return sectionCameraTrees;
	}
	public void setSectionCameraTrees(List<SectionCameraTrees> sectionCameraTrees) {
		this.sectionCameraTrees = sectionCameraTrees;
	}
}
