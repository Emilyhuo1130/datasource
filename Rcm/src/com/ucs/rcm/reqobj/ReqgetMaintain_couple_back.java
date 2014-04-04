package com.ucs.rcm.reqobj;

import com.ucs.rcm.business.bo.Page;

public class ReqgetMaintain_couple_back {
	private Page page;//page
	private MaintainQueryObj query;//req信息
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public MaintainQueryObj getQuery() {
		return query;
	}
	public void setQuery(MaintainQueryObj query) {
		this.query = query;
	}
	
}
