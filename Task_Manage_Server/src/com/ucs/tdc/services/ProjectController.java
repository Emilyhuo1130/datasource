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
	public void setInterFace(Project interFace) {
		this.interFace = interFace;
	}
	static Boolean flag=false;
	
	
	
	
	/***项目总页数*/
	@RequestMapping(value = "admin/do_projectpages")
	@ResponseBody
	public int do_addProject(@RequestParam int pageSize,String projectName,String starTime,String endTime) {
		int count=interFace.do_projectpages(pageSize,projectName, starTime, endTime);
		return count;
	}
	
	
	/**管理员     项目管理  添加项目**/
	@RequestMapping(value = "admin/do_addProject")
	@ResponseBody
	public boolean do_addProject(@RequestParam String project_info) {
		log.info("管理员     项目管理  添加项目:"+project_info);
		Gson gson =new Gson();
		ProjectInfo info=gson.fromJson(project_info, ProjectInfo.class);
		info.setCreateProjectDate(new Date());
		flag=interFace.do_addProject(info);
		return flag;
	}
	
	/**管理员 显示项目**/
	@RequestMapping(value = "admin/do_findProjects")
	@ResponseBody
	public List<ProjectInfo> do_findProjects(@RequestParam int pageSize, int page,
			String projectName,String starTime,String endTime) {
		log.info("管理员 显示项目:"+pageSize+"  "+page);
		List<ProjectInfo> list=interFace.do_findProjects(pageSize,page,projectName,starTime,endTime);
		Gson gson =new Gson();
		log.info("显示项目   :"+gson.toJson(list));
		return list;
	}
	
	/***根据id查找项目信息**/
	@RequestMapping(value = "admin/do_findProjectsByID")
	@ResponseBody
	public ProjectInfo do_findProjectsByID(@RequestParam int id) {
		log.info("根据id查找项目信息:"+id+"  "); 
		ProjectInfo info=interFace.do_findProjectsByID(id);
		Gson gson =new Gson();
		log.info("根据ID返回项目信息:"+gson.toJson(info));
		info.setCreateProjectDate(null);
		return info;
	}
	
	/***管理员  更新项目 信息**/
	@RequestMapping(value = "admin/do_updateProject")
	@ResponseBody
	public boolean do_updateProjects(@RequestParam String project_info) {
		log.info("管理员  更新项目信息:"+project_info+"  ");
		Gson gson =new Gson();
		ProjectInfo info=gson.fromJson(project_info, ProjectInfo.class);
		ProjectInfo old_info =interFace.do_findProjectsByID(info.getId());
		old_info.setProjectRemark(info.getProjectRemark());
		old_info.setProjectState(info.getProjectState());
		old_info.setProjectName(info.getProjectName());
		old_info.setProjectType(info.getProjectType());
		flag =interFace.do_updateProjectsByID(old_info);
		log.info("管理员  更新项目信息是否成功:"+flag+"  ");
		return flag;
	}
	
	
	
	
	
	
	                         /***管理员 项目统计***/
	/**返回   项目统计   页数***/
	@RequestMapping(value = "admin/do_Project_Statisticspages")
	@ResponseBody
	public int do_Project_Statisticspages(@RequestParam int pageSize, String projectName,
			String starTime,String endTime) {
		int count =interFace.do_Project_Statisticspages(pageSize,projectName,starTime,endTime);
		return count;
	}
	
	/****返回  项目统计  按页查询***/
	@RequestMapping(value = "admin/do_search_Project_Statistics")
	@ResponseBody
	public List<Project_Statistics> do_Project_Statistics(@RequestParam int pageSize,int page,String projectName,
			String starTime,String endTime) {
		log.info("返回  项目统计  按页查询:");
		List<Project_Statistics> list =interFace.do_Project_Statistics(pageSize,page,projectName,starTime,endTime);
		Gson gson=new Gson();
		log.info("项目统计："+gson.toJson(list));
		return list;
	}
	
	
	
	/**项目统计  查看详情按钮***/
	@RequestMapping(value = "admin/do_Project_Statistics_details")
	@ResponseBody
	public Project_Statistics_details_List do_Project_Statistics_details(
			 int pageSize, int page, String projectName,
			 String starTime, String endTime,String userName) {
		log.info("项目统计  查看详情按钮");
		Project_Statistics_details_List list =
			interFace.do_Project_Statistics_details(pageSize,page,projectName,starTime,endTime,userName);
		Gson gson=new Gson();
		log.info("返回项目统计  查看详情按钮:"+gson.toJson(list));
		return list;
	}
	
	/***项目统计  某个人在某个项目上的工时详情***/
	
	@RequestMapping(value = "admin/do_Project_one_details")
	@ResponseBody
	public List<TaskParticulars> do_Project_one_details(
			 int pageSize, int page, String projectName,String userName,
			 String starTime, String endTime) {
		log.info("项目统计  某个人在某个项目上的工时详情");
		List<TaskParticulars> list =interFace.do_Project_one_details(pageSize,page,projectName,userName,starTime,endTime);
		Gson gson=new Gson();
		log.info("返回项目统计  查看详情按钮:"+gson.toJson(list));
		return list;
	}
	
}
