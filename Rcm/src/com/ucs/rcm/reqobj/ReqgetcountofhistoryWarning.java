package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetcountofhistoryWarning {
	private Page page;//page
	private CountofHistorywarningQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public CountofHistorywarningQueryObj getQuery() {
		return query;
	}
	public void setQuery(CountofHistorywarningQueryObj query) {
		this.query = query;
	}
	
}
