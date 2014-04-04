package com.ucs.cctv.Interface_Dao;

import com.ucs.cctv.Pojo.MonitorGroup;
import com.ucs.cctv.Response.GroupResponse;

public interface MonitorGroupManage {
	
	//查看所有监控组
	public GroupResponse findAllMonitorGroup(String monitorName,int pageSize, int page);
	
	//添加监控组
	public Boolean addMonitorGroup(MonitorGroup monitorgroup);
	
	//更新监控组
	public Boolean updateMonitorGroup(MonitorGroup monitorgroup);
	
	//删除监控组
	public boolean deleteMonitorGroup(int id);	
	
	//按ID查找监控组
	public MonitorGroup findMonitorbyId(int id);
	
}
