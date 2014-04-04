package com.ucs.rcm.responseObj;

import java.util.List;
import com.ucs.rcm.pojo.Facilityindex;
public class ResChattelanalyseList{
	/**
	 * 
	 */
	private List<Facilityindex> ChattelanalyseList;
	private String totalCount;//总记录数
	private String totalPage;//总页数
	
public List<Facilityindex> getChattelanalyseList() {
		return ChattelanalyseList;
	}
	public void setChattelanalyseList(List<Facilityindex> chattelanalyseList) {
		ChattelanalyseList = chattelanalyseList;
	}
	/*	public List<Chattelanalysebo> getChattelanalyseList() {
		return ChattelanalyseList;
	}
	public void setChattelanalyseList(List<Chattelanalysebo> chattelanalyseList) {
		ChattelanalyseList = chattelanalyseList;
	}*/
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
