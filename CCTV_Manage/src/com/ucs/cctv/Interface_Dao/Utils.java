package com.ucs.cctv.Interface_Dao;
import java.util.List;

import com.ucs.cctv.Trees.SectionCameraTrees;

public interface Utils {
	
	//显示所有区位
	public  List<String> showAllSection();
	
	//显示所有监控组
	public   List<String> showMonitorGroup();
	
	
	
	//显示区位摄像机树
	public SectionCameraTrees showSectionCameraTrees();
	
	
	//通过id查找区位摄像机树
	public SectionCameraTrees showSectionCameraTreesById(int id);
}
