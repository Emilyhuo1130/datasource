package com.ucs.tdc.interFace;

import java.util.List;

import com.ucs.tdc.pojo.ProjectInfo;
import com.ucs.tdc.pojo.Project_Statistics;
import com.ucs.tdc.pojo.Project_Statistics_details_List;
import com.ucs.tdc.pojo.TaskParticulars;

public interface Project {

	Boolean do_addProject(ProjectInfo info);

	int do_projectpages(int pageSize,String projectName,String starTime,String endTime);

	List<ProjectInfo> do_findProjects(int pageSize, int page, String projectName, String starTime, String endTime);

	int do_Project_Statisticspages(int pageSize, String projectName, String starTime, String endTime);

	List<Project_Statistics> do_Project_Statistics(int pageSize,
			int page, String projectName, String starTime, String endTime);

	Project_Statistics_details_List do_Project_Statistics_details(
			int pageSize, int page, String projectName,
			 String starTime, String endTime, String userName);

	ProjectInfo do_findProjectsByID(int id);

	Boolean do_updateProjectsByID(ProjectInfo info);

	List<TaskParticulars> do_Project_one_details(int pageSize, int page,
			String projectName, String userName, String starTime, String endTime);
	
}
