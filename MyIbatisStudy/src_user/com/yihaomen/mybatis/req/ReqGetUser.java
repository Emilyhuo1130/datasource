package com.yihaomen.mybatis.req;

import com.yihaomen.mybatis.model.Page_;
import com.yihaomen.mybatis.model.UserInfo;

public class ReqGetUser {
	private Page_ page;
	private UserInfo user;
	public Page_ getPage() {
		return page;
	}
	public void setPage(Page_ page) {
		this.page = page;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	
}
