package com.inter_face;

import java.util.List;

import com.pojo.AdminInfo;
import com.pojo.AllUserInfo;
import com.pojo.UserInfo;

public interface ManageInterface {
	

	public UserInfo getuserMain();

	public AdminInfo getadminMain();

	public AllUserInfo getAllUserInfo();

	public List<String> getSelect();

	
}
