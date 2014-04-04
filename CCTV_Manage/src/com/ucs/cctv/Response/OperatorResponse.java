package com.ucs.cctv.Response;

import java.util.List;

import com.ucs.cctv.Pojo.OperatorInfo;

public class OperatorResponse {
	private List<OperatorInfo> operatorInfo;
	private int pages;
	
	public List<OperatorInfo> getOperatorInfo() {
		return operatorInfo;
	}
	public void setOperatorInfo(List<OperatorInfo> operatorInfo) {
		this.operatorInfo = operatorInfo;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

}
