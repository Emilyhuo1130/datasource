package com.ucs.tdc.services;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.tdc.interFace.Project;
import com.ucs.tdc.pojo.ProjectInfo;
import com.ucs.tdc.pojo.Project_Statistics;
import com.ucs.tdc.pojo.Project_Statistics_details_List;
import com.ucs.tdc.pojo.TaskParticulars;

@Controller
public class ProjectController {
	static Logger log = Logger.getLogger(ProjectController.class);
	@Resource
	private Project interFace;
	Gson gson =new Gson();
	
	
	
	
	
	/***项目总页数*/
	@RequestMapping(value = "admin/do_projectpages")
	@ResponseBody
	public int do_addProject(@RequestParam int pageSize,String projectName,String starTime,String endTime) {
		return interFace.do_projectpages(pageSize,projectName, starTime, endTime);
	}
	
	
	/**管理员     项目管理  添加项目**/
	@RequestMapping(value = "admin/do_addProject")
	@ResponseBody
	public boolean do_addProject(@RequestParam String project_info) {
		ProjectInfo info=gson.fromJson(project_info, ProjectInfo.class);
		info.setCreateProjectDate(new Date());
		return interFace.do_addProject(info);
	}
	
	/**管理员 显示项目**/
	@RequestMapping(value = "admin/do_findProjects")
	@ResponseBody
	public List<ProjectInfo> do_findProjects(@RequestParam int pageSize, int page,
			String projectName,String starTime,String endTime) {
		return interFace.do_findProjects(pageSize,page,projectName,starTime,endTime);
	}
	
	/***根据id查找项目信息**/
	@RequestMapping(value = "admin/do_findProjectsByID")
	@ResponseBody
	public ProjectInfo do_findProjectsByID(@RequestParam int id) {
		ProjectInfo info=interFace.do_findProjectsByID(id);
		info.setCreateProjectDate(null);
		return info;
	}
	
	/***管理员  更新项目 信息**/
	@RequestMapping(value = "admin/do_updateProject")
	@ResponseBody
	public boolean do_updateProjects(@RequestParam String project_info) {
		ProjectInfo info=gson.fromJson(project_info, ProjectInfo.class);
		ProjectInfo old_info =interFace.do_findProjectsByID(info.getId());
		old_info.setProjectRemark(info.getProjectRemark());
		old_info.setProjectState(info.getProjectState());
		old_info.setProjectName(info.getProjectName());
		old_info.setProjectType(info.getProjectType());
		return interFace.do_updateProjectsByID(old_info);
	}
	
	
	
	
	
	
	                         /***管理员 项目统计***/
	/**返回   项目统计   页数***/
	@RequestMapping(value = "admin/do_Project_Statisticspages")
	@ResponseBody
	public int do_Project_Statisticspages(@RequestParam int pageSize, String projectName,
			String starTime,String endTime) {
		return interFace.do_Project_Statisticspages(pageSize,projectName,starTime,endTime);
	}
	
	/****返回  项目统计  按页查询***/
	@RequestMapping(value = "admin/do_search_Project_Statistics")
	@ResponseBody
	public List<Project_Statistics> do_Project_Statistics(@RequestParam int pageSize,int page,String projectName,
			String starTime,String endTime) {
		return interFace.do_Project_Statistics(pageSize,page,projectName,starTime,endTime);
	}
	
	
	
	/**项目统计  查看详情按钮***/
	@RequestMapping(value = "admin/do_Project_Statistics_details")
	@ResponseBody
	public Project_Statistics_details_List do_Project_Statistics_details(
			 int pageSize, int page, String projectName,
			 String starTime, String endTime,String userName) {
			return interFace.do_Project_Statistics_details(pageSize,page,projectName,starTime,endTime,userName);
	}
	
	/***项目统计  某个人在某个项目上的工时详情***/
	
	@RequestMapping(value = "admin/do_Project_one_details")
	@ResponseBody
	public List<TaskParticulars> do_Project_one_details(
			 int pageSize, int page, String projectName,String userName,
			 String starTime, String endTime) {
		return interFace.do_Project_one_details(pageSize,page,projectName,userName,starTime,endTime);
	}
	
}
