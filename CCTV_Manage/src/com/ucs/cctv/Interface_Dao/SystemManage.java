package com.ucs.cctv.Interface_Dao;



import com.ucs.cctv.Pojo.SystemConfigInfo;

public interface SystemManage {
	
	//返回系统参数设置参数
	public SystemConfigInfo getSystemConfigInfo();
		
	//更新系统配置参数信息
	public boolean updateSystemConfigInfo(SystemConfigInfo systemConfigInfo) ;
	
	
	
	
	
	
}
