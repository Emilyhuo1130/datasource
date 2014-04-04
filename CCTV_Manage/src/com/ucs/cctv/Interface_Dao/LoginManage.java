package com.ucs.cctv.Interface_Dao;

import com.ucs.cctv.Pojo.AdminInfo;


public interface LoginManage {
	
	public Boolean addAdmin(AdminInfo admin);
	
	public Boolean adminLoginVerify(String adminAccount,String adminPw);
	
	public Boolean updateAdminInfo(AdminInfo admin);
	
	public Boolean operatorLoginVerify(String operatorAccount,String operatorPw);

}
