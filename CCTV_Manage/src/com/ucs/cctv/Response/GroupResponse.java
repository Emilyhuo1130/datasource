package com.ucs.cctv.Response;

import java.util.List;

import com.ucs.cctv.Pojo.MonitorGroup;

public class GroupResponse {
	List<MonitorGroup> monitorGroup;
    int pages;
	public List<MonitorGroup> getMonitorGroup() {
		return monitorGroup;
	}
	public void setMonitorGroup(List<MonitorGroup> monitorGroup) {
		this.monitorGroup = monitorGroup;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

}
