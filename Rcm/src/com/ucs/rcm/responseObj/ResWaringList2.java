package com.ucs.rcm.responseObj;
/**警告返回信息*/
import java.util.List;

import com.ucs.rcm.business.bo.WarningBo;
import com.ucs.rcm.pojo.Warning;

public class ResWaringList2 {

	private List<Warning> warningList;//所有的警告信息
	private Integer totalCount;//总记录数
	private Integer totalPage;//总页数
	public List<Warning> getWarningList() {
		return warningList;
	}
	public void setWarningList(List<Warning> warningList) {
		this.warningList = warningList;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	
	

}
