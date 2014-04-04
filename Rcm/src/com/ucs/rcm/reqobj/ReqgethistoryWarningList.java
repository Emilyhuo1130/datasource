package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgethistoryWarningList {
	private Page page;//page
	private HistorywarningQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public HistorywarningQueryObj getQuery() {
		return query;
	}
	public void setQuery(HistorywarningQueryObj query) {
		this.query = query;
	}
	
}
