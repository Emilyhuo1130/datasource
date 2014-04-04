package com.ucs.business.object;

import java.util.List;

public class TaskParticularsInfoResponse {
	private List<TaskParticulars> taskParticularsList;
	private int totalPage;
	public List<TaskParticulars> getTaskParticularsList() {
		return taskParticularsList;
	}
	public void setTaskParticularsList(List<TaskParticulars> taskParticularsList) {
		this.taskParticularsList = taskParticularsList;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
