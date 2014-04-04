package com.ucs.cctv.Interface_Dao;


import java.util.List;

import com.ucs.cctv.Pojo.CameraInfo;
import com.ucs.cctv.Pojo.MonitorGroup;
import com.ucs.cctv.Pojo.OperatorInfo;
import com.ucs.cctv.Response.OperatorResponse;


public interface OperatorManage{

	//查看所有操作员
	public  OperatorResponse  findAllOperators(String operatorName,String operatorAccount,String level,int pageSize, int page);
	
	//更新操作员信息
	public Boolean  updateOperatorInfo(OperatorInfo operator);

	//添加操作员
	public Boolean addOperator(OperatorInfo operator);
	
	//删除操作员
	public Boolean deleteOperator(int id);
	
	//按id查找操作员
	public OperatorInfo findOperatorbyId(int id);
	
	//查找操作员所监控的摄像机
	public List<CameraInfo> findCamerabyOperator(String operatorName);
	
	//根据名称查找监控组
	public MonitorGroup findMonitorbyName(String name);
	
}
