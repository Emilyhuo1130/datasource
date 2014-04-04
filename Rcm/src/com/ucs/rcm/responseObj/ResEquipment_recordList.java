package com.ucs.rcm.responseObj;

import java.util.List;

public class ResEquipment_recordList {
	
	private String totalCount;//总记录数
	private String totalPage;//总页数
	private List<ResEquipment_recordinfo> resEquipment_recordList;
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public String getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(String totalPage) {
		this.totalPage = totalPage;
	}
	public List<ResEquipment_recordinfo> getResEquipment_recordList() {
		return resEquipment_recordList;
	}
	public void setResEquipment_recordList(
			List<ResEquipment_recordinfo> resEquipment_recordList) {
		this.resEquipment_recordList = resEquipment_recordList;
	}
	
	
}
