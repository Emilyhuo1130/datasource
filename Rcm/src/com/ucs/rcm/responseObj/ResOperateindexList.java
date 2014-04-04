package com.ucs.rcm.responseObj;

import java.util.List;
public class ResOperateindexList {
	private String totalCount;//总记录数
	private String totalPage;//总页数
	private List<ResOperateindex> operateindexList;
	
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
	public List<ResOperateindex> getOperateindexList() {
		return operateindexList;
	}
	public void setOperateindexList(List<ResOperateindex> operateindexList) {
		this.operateindexList = operateindexList;
	}
	
	
	
}
