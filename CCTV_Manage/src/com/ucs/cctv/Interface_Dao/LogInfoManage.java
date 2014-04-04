package com.ucs.cctv.Interface_Dao;

import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Response.LogResponse;


public interface LogInfoManage {
	
	//添加日志记录
	public boolean addLogInfo(OperateLog log);
	
	//查看日志记录
	public LogResponse findAllLogInfo(String operatorName,String operatorAccount,String startTime,
			String endTime,String operateType,int pageSize, int page);
	//记录日志信息
	public  boolean insertInfoToDataBase(OperateLog operateLog);
	//根据账号查询用户名
	public String getOperatorNameByAccount(final String operatorAccount);
}
