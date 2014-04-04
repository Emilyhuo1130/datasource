package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetChattelanalyseList {
	private Page page;
	private ChattelanalyseQueryObj query;
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public ChattelanalyseQueryObj getQuery() {
		return query;
	}
	public void setQuery(ChattelanalyseQueryObj query) {
		this.query = query;
	}
	
}
