package com.ucs.tdc.pojo;

import java.util.List;

public class HistoryTaskList {
	private List<TaskParticulars> taskListInfo;//历史任务集合
	private Integer page; //总页数
	
	public List<TaskParticulars> getTaskListInfo() {
		return taskListInfo;
	}
	public void setTaskListInfo(List<TaskParticulars> taskListInfo) {
		this.taskListInfo = taskListInfo;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
}
