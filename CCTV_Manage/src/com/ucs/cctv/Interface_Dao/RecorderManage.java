package com.ucs.cctv.Interface_Dao;

import com.ucs.cctv.Pojo.RecorderInfo;
import com.ucs.cctv.Response.RecorderResponse;

public interface RecorderManage {
	
	//查看所有录像机
	public  RecorderResponse findAllRecorders(String recorderName,String recorderType,int pageSize, int page);
		
	//更新录像机信息
	public Boolean  updateRecorderInfo(RecorderInfo recorder);

	//添加录像机
	public Boolean addRecorder(RecorderInfo recorder);
		
	//按id查找录像机
	public RecorderInfo findRecorderbyId(int id);
	
	//验证ip
		public Boolean verifyIP(String ip);

    //删除录像机
		public boolean deleteRecorder(int id);

		
}
