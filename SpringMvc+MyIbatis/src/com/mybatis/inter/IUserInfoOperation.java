package com.mybatis.inter;

import java.util.List;

import com.mybatis.mapper.Page_;
import com.mybatis.mapper.UserInfo;



public interface IUserInfoOperation {
	//��������һ�������� selectUserByID ������ UserInfo.xml �������õ� select ��id ��Ӧ��<select id="selectUserByID"��
	public UserInfo selectUserByID(int id);
	public UserInfo selectUserByName(String name);
	public List<UserInfo> selectALLKileName(String name);
	public List<UserInfo> getAllusers(Page_ page);
	public void addUser(UserInfo user);
	public void updateUserInfo(UserInfo user);
	public void deleteUserinfo(int id);
	
}
