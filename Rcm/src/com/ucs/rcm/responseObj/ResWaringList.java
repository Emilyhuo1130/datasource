package com.ucs.rcm.responseObj;
/**警告返回信息*/
import java.util.List;

import com.ucs.rcm.business.bo.WarningBo;
import com.ucs.rcm.pojo.Warning;

public class ResWaringList {

	private List<Warning> warningList;//所有的警告信息
	private String totalCount;//总记录数
	private String totalPage;//总页数
	public List<Warning> getWarningList() {
		return warningList;
	}
	public void setWarningList(List<Warning> warningList) {
		this.warningList = warningList;
	}
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

	

}
