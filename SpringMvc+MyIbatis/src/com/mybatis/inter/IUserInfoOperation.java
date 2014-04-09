package com.mybatis.inter;

import java.util.List;

import com.mybatis.mapper.Page_;
import com.mybatis.mapper.UserInfo;



public interface IUserInfoOperation {
	//这里面有一个方法名 selectUserByID 必须与 UserInfo.xml 里面配置的 select 的id 对应（<select id="selectUserByID"）
	public UserInfo selectUserByID(int id);
	public UserInfo selectUserByName(String name);
	public List<UserInfo> selectALLKileName(String name);
	public List<UserInfo> getAllusers(Page_ page);
	public void addUser(UserInfo user);
	public void updateUserInfo(UserInfo user);
	public void deleteUserinfo(int id);
	
}
