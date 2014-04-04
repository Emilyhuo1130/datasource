package com.ucs.rcm.responseObj;

import java.util.List;

public class AllTendency {
	private List<TendencyInfo> infoList;
	private String name;
	
	
	public List<TendencyInfo> getInfoList() {
		return infoList;
	}
	public void setInfoList(List<TendencyInfo> infoList) {
		this.infoList = infoList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
