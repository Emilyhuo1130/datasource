package com.ucs.cctv.Interface_Dao;

import com.ucs.cctv.Pojo.CameraInfo;
import com.ucs.cctv.Response.CameraResponse;

public interface CameraManage {
	
	//查看所有摄像机
    public  CameraResponse findAllCameras(String cameraName,String cameraType, String section , int pageSize, int page);
	
    //更新摄像机
	public Boolean  updateCameraInfo(CameraInfo camera);

	//添加摄像机
	public Boolean addCamera(CameraInfo camera);
	
	//按id查找摄像机
	public CameraInfo findCamerabyId(int id);
	
	//按摄像机名查找摄像机
	public CameraInfo findCamerabyName(String cameraName);
	
	//验证ip
	public Boolean verifyIP(String ip);

	public boolean deleteCamera(int id);
	
}
