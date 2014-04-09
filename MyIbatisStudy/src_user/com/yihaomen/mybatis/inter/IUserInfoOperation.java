package com.yihaomen.mybatis.inter;

import java.util.List;

import com.yihaomen.mybatis.model.Article;
import com.yihaomen.mybatis.model.Page_;
import com.yihaomen.mybatis.model.UserInfo;
import com.yihaomen.mybatis.req.ReqGetUser;

public interface IUserInfoOperation {
	//这里面有一个方法名 selectUserByID 必须与 UserInfo.xml 里面配置的 select 的id 对应（<select id="selectUserByID"）
	public UserInfo selectUserByID(int id);
	public UserInfo selectUserByName(String name);
	public List<UserInfo> selectALLKileName(String name);
	public List<UserInfo> getAllusers(Page_ page);
	public List<UserInfo> getAllUserPertame(ReqGetUser requser);
	public void addUser(UserInfo user);
	public void updateUserInfo(UserInfo user);
	public void deleteUserinfo(int id);
	public List<Article> getUserArticles(int id);
	public List<UserInfo> getForEachTest(List<Integer> ids);
}
