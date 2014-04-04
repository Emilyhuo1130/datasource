package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetFormsOperateindexList {
	private Page page;//page
	private FormsOperateindexQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public FormsOperateindexQueryObj getQuery() {
		return query;
	}
	public void setQuery(FormsOperateindexQueryObj query) {
		this.query = query;
	}
	
}
