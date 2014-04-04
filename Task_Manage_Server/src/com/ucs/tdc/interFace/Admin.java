package com.ucs.tdc.interFace;

import java.util.List;

import com.ucs.tdc.pojo.AdminInfo;
import com.ucs.tdc.pojo.UserInfo;

public interface Admin {
	

	public Boolean verifyuser(UserInfo info);

	public Boolean verifyAdmin(AdminInfo info);

	public Boolean do_addUser(UserInfo info);

	public Boolean do_updateUser(UserInfo info);

	public Boolean do_deleteUser(UserInfo info);

	public List<UserInfo> do_findAllUser(String userName, String userAccount, int pageSize, int page);

	public int do_userspages(int pageSize, String userName, String userAccount);

	public UserInfo do_findAllUserbyId(int id);

	public String do_findUserName(String accountName);

	public Boolean do_verifyAdminPW(String adminName, String password);

	public AdminInfo do_findAdminInfoByAccount(String adminName);

	public Boolean do_modifyAdminInfo(AdminInfo info);
}
