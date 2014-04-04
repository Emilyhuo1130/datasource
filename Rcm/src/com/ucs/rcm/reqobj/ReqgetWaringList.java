package com.ucs.rcm.reqobj;
/**ajax请求收到的数据*/
import com.ucs.rcm.business.bo.Page;

public class ReqgetWaringList {

	private Page page;//page
	private WarningQueryObj query;//req信息

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public WarningQueryObj getQuery() {
		return query;
	}

	public void setQuery(WarningQueryObj query) {
		this.query = query;
	}

}
