package com.ucs.cctv.Interface_Dao;

import java.util.List;

import com.ucs.cctv.Pojo.SectionInfo;
import com.ucs.cctv.Response.SectionResponse;

public interface SectionManage {
	
	//添加区位
	public Boolean addSection(SectionInfo section);
	
	//删除区位
	public Boolean deleteSection(int id);
	
	//修改区位信息	
	public Boolean updateSection(SectionInfo section);	
	
	//查看所有区位
	public SectionResponse findAllSections(String sectionName , int pageSize,int page);
	
	//按id查看区位信息
	public SectionInfo findSectionById(int id);
	
	//返回所有的区位信息名称
	public List<String> findAllSectionName() ;
	
	
	
}
