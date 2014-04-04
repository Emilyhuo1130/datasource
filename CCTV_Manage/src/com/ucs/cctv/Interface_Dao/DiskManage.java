package com.ucs.cctv.Interface_Dao;


import com.ucs.cctv.Response.DiskSizeResponse;

public interface DiskManage {
	
	public void readDiskInfo();
	//读取properties的全部信息返回Response对象
	public DiskSizeResponse getDiskInfo();

	public boolean updateDiskInfo(DiskSizeResponse diskSizeResponse) ;
	
	
	
}
