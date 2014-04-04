 package com.ucs.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.business.object.HistoryTaskList;
import com.ucs.business.object.TaskParticulars;
import com.ucs.business.object.TaskParticularsInfoResponse;
import com.ucs.business.object.WeekTask;
import com.ucs.business.object.WeekTaskInfoResponse;
import com.ucs.business.utils.Utils;
import com.ucs.connect.date.*;

@Controller
public class LoginController {
	//记录日志
	Logger loger = Logger.getLogger(LoginController.class.getName());
	Gson gson = new Gson();
	/*登录界面
	 *
	 */
	//用户登录
	
	@RequestMapping(value="jsp/user_Log/login.do")
	@ResponseBody
	public  Map<String,Object> userlogin(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetUserResponse getinfo = new GetUserResponse();
		System.out.println(req.getParameter("userAccount"));
		map.put("user",getinfo.getuser(req));
		return map;
	}
	
	//管理员登录
	@RequestMapping(value="jsp/admin_Log/login.do")
	@ResponseBody
	public Map<String,Object> adminlogin(HttpServletRequest req,HttpServletResponse res) {
		Map<String,Object> map = new HashMap<String,Object>();
		GetUserResponse getinfo = new GetUserResponse();
		map.put("admin", getinfo.getadmin(req));
		return map;
	}
	
	/*用户界面
	 *
	 */
	//用户添加任务
	@RequestMapping(value="jsp/user/do_addTasks.do")
	@ResponseBody
	public Map<String,Object> addTasks(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("task", getinfo.addTasks(req,req.getParameter("userAccount")));
		return map;
	}
	
	
	
	//用户添加下周计划
	@RequestMapping(value="jsp/user/do_addPlans.do")
	@ResponseBody
	public Map<String,Object> addPlans(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		String userAccount = req.getParameter("userAccount");
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("task", getinfo.addPlans(req,userAccount));
		return map;
	}
	

	//用户修改下周计划
	@RequestMapping(value="jsp/user/do_updatePlans.do")
	@ResponseBody
	public Map<String,Object> updatePlans(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("task", getinfo.updateWeekTask(req));
		return map;
	}
	
	//用户查看本周任务
	@RequestMapping(value="jsp/user/do_findPlans.do")
	@ResponseBody
	public Map<String,Object> findPlans(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("task", getinfo.findPlans(req));
		return map;
	}
	
	//删除下周计划
	@RequestMapping(value="jsp/user/do_deleteNextPlan.do")
	@ResponseBody
	public Boolean deleteNextPlan(HttpServletRequest req) {
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("delInfo", getinfo.deleteWeekTask(req));
		return false;
	} 
	
	//返回查询下周计划任务的总页数------------------------------------------------!
	@RequestMapping(value="jsp/user/do_geWeekTaskPageCount.do")
	@ResponseBody
	public Map<String,Object> getNextPlanTotalPages(HttpServletRequest req , HttpServletResponse res){
		Map<String ,Object> map = new HashMap<String, Object>()	;
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("totalPage", getinfo.getNextPlanTotalPage(req));
		return map;
	} 
	
	//用户查历史任务
	@RequestMapping(value="jsp/*/do_findHistoryTask.do")
	@ResponseBody
	public Map<String,Object> findHistoryPlans(HttpServletRequest req,HttpServletResponse res){
		
			Map<String,Object> map = new HashMap<String,Object>();
			try {
				GetTaskResponse getinfo = new GetTaskResponse();
			HistoryTaskList info = getinfo.findHistoryTask(req,getinfo.findUserName(req));
			loger.info("----------------------HistoryTaskList info="+gson.toJson(info));
			map.put("pages",info.getPage()==null?0:info.getPage() );
			map.put("tasklist",info.getTaskListInfo() );
			
		} catch (Exception e) {
			loger.info("---空值---");
		}
			return map;
	}
	//根据id来获取本周任务----------------------------------------!
	@RequestMapping(value="jsp/*/do_getCurrentWeekMissionById.do")
	@ResponseBody
	public Map<String,Object> do_getCurrentWeekMissionById(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("taskInfo",getinfo.do_getCurrentWeekMissionById(req) );
		return map;
	}
	//根据id来获取用户的单条历史任务
		@RequestMapping(value="jsp/*/do_getHistoryMissionById.do")
		@ResponseBody
		public Map<String,Object> do_getHistoryMissionById(HttpServletRequest req,HttpServletResponse res){
			Map<String,Object> map = new HashMap<String,Object>();
			GetTaskResponse getinfo = new GetTaskResponse();
			map.put("oneInfo",getinfo.do_getHistoryMissionById(req) );
			return map;
		}
	
	//普通用户修改修改本周任务  修改按钮操作-------------------------------------!
	@RequestMapping(value="jsp/*/do_updateCurrentWeekMission.do")
	@ResponseBody
	public Map<String,Object> updateCurrentWeekTask(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("backInfo",getinfo.updateCurrentWeekTask(req) );
		return map;
	}
	//普通用户修改历史任务  修改按钮操作
	@RequestMapping(value="jsp/*/do_updateHistoryMission.do")
	@ResponseBody
	public Map<String,Object> updateHistoryMission(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("backInfo",getinfo.updateHistoryTask(req) );
		return map;
	}
	
	//普通用户删除历史任务 删除按钮操作
	@RequestMapping(value="jsp/*/do_deleteHistoryMission.do")
	@ResponseBody
	public Map<String,Object> do_deleteHistoryMission(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("flag",getinfo.do_deleteHistoryMission(req) );
		return map;
	}
	@RequestMapping(value="jsp/user/do_getMyUserInfo.do")
	@ResponseBody
	public Map<String,Object> getMyUserInfo(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("userinfo", getinfo.getMyUserInfo(req));
		return map;
	}
	//用户修改个人信息
	@RequestMapping(value="jsp/user/do_modifyInfo.do")
	@ResponseBody
	public Map<String,Object> verifyPw(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetTaskResponse getinfo = new GetTaskResponse();
		map.put("userinfo", getinfo.verifyPW(req));
		return map;
	}
	/*管理员界面
	 *-------------------------------------------------------------------------------------------
	 */
	
	//管理员查看用户
	@RequestMapping(value="jsp/admin/do_findUser.do")
	@ResponseBody
	public Map<String,Object> findUser(HttpServletRequest req,HttpServletResponse res){
		System.out.println("-----管理员查看用户----");
		Map<String,Object> map = new HashMap<String,Object>();
		GetUserResponse getinfo = new GetUserResponse();
		map.put("userinfo", getinfo.queryAllUser(req));
		return map;
	}
	
	//管理员查看用户页数
	@RequestMapping(value="jsp/admin/do_getadminpages.do")
	@ResponseBody
	public Map<String,Object> findUserPage(HttpServletRequest req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		loger.info("-----管理员查看用户页数----");
		Map<String,Object> map = new HashMap<String,Object>();
		GetUserResponse getinfo = new GetUserResponse();
		map.put("userpage", getinfo.getpage(req));
		return map;
	}
	
	//管理员添加用户
	@RequestMapping(value="jsp/admin/do_addUser.do")
	@ResponseBody
	public Map<String,Object> addUser(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetUserResponse getinfo = new GetUserResponse();
		map.put("user", getinfo.adduser(req));
		return map;
	}
	
	//跳转到修改用户页面
	@RequestMapping(value="jsp/admin/do_toUpdateUser.do")
	@ResponseBody
	public Map<String,Object> toaddUser(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetUserResponse getinfo = new GetUserResponse();
		map.put("user", getinfo.findUserbyId(req));
		return map;
	}
	
	//管理员修改用户
	@RequestMapping(value="jsp/admin/do_updateUser.do")
	@ResponseBody
	public Map<String,Object> updateUser(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetUserResponse getinfo = new GetUserResponse();
		map.put("user", getinfo.updateuser(req));
		return map;
	}
		
	//管理员删除用户
	@RequestMapping(value="jsp/admin/do_deleteUser.do")
	@ResponseBody
	public Map<String,Object> deleteUser(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetUserResponse getinfo = new GetUserResponse();
		map.put("user", getinfo.deleteuser(req));
		return map;
	}
	
	/*项目管理
	 * 
	 */
	/****显示每个人的本周任务 按人查 like***/
	@RequestMapping(value = "jsp/admin/do_select_AllUserHistoryTasks")
	@ResponseBody
	public WeekTaskInfoResponse do_select_AllUserHistoryTasks(HttpServletRequest req,HttpServletResponse res) {
	//入参	 weekNo, userName, page, pageSize
		loger.info("----req="+req.getParameter("startweekNo")+","+req.getParameter("endweekNo"));
		GetTaskResponse Task =new GetTaskResponse();
		WeekTaskInfoResponse list=Task.do_select_AllUserHistoryTasks(req);
		return list;
		
	}
	/***每人的历史日报 名字 时间查****/
	@RequestMapping(value = "jsp/admin/do_select_AllUserHistoryWeekTasks")
	@ResponseBody
	public TaskParticularsInfoResponse do_select_AllUserHistoryWeekTasks(HttpServletRequest req,HttpServletResponse res) {
		//入参 userName, page, pageSize, starTime , endTime
		GetTaskResponse Task =new GetTaskResponse();
		TaskParticularsInfoResponse list=Task.do_select_AllUserHistoryWeekTasks(req);
		return list;
		
	}
	//添加项目
	@RequestMapping(value="jsp/admin/do_addProject.do")
	@ResponseBody
	public Map<String,Object> addProject(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetProjectResponse getinfo = new GetProjectResponse();
		map.put("project", getinfo.addProject(req));
		return map;
	}
	
	//查询项目
	@RequestMapping(value="jsp/admin/do_findProjects.do")
	@ResponseBody
	public Map<String,Object> findProjects(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetProjectResponse getinfo = new GetProjectResponse();
		map.put("project", getinfo.findProjects(req));
		return map;
	}
	
	//查询项目页数
	@RequestMapping(value="jsp/admin/do_findProjectsPages.do")
	@ResponseBody
	public Map<String,Object> findProjectsPages(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetProjectResponse getinfo = new GetProjectResponse();
		map.put("pages", getinfo.projectPages(req));
		return map;
	}
	
	//根据id跳转到修改项目信息
	@RequestMapping(value="jsp/admin/do_findProjectsByID.do")
	@ResponseBody
	public Map<String,Object> findProjectsByID(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetProjectResponse getinfo = new GetProjectResponse();
		map.put("project", getinfo.ProjectsByID(req));
		return map;
	}	
	
	//修改项目信息
	@RequestMapping(value="jsp/admin/do_updateProject.do")
	@ResponseBody
	public Map<String,Object> updateProject(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetProjectResponse getinfo = new GetProjectResponse();
		map.put("project", getinfo.updateProject(req));
		return map;
	}	
	/*项目统计
	 * 
	 */
	//统计查询
	@RequestMapping(value="jsp/admin/do_Project_Statistics.do")
	@ResponseBody
	public Map<String,Object> findStatisticsProjects(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetStatisticsResponse getinfo = new GetStatisticsResponse();
		map.put("statistics", getinfo.ProjectStatistics(req));
		return map;
	}
	
	//统计总页数
	@RequestMapping(value="jsp/admin/do_Project_Statisticspages.do")
	@ResponseBody
	public Map<String,Object> Projectpages(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetStatisticsResponse getinfo = new GetStatisticsResponse();
		map.put("statistics", getinfo.Statisticspages(req));
		return map;
	}
	
	
	//项目明细
	@RequestMapping(value="jsp/admin/do_Project_details_List.do")
	@ResponseBody
	public Map<String,Object>Projectdetails(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetStatisticsResponse getinfo = new GetStatisticsResponse();
		map.put("statistics", getinfo.Projectdetails(req));
		return map;
	}
	
	//个人工时统计
	@RequestMapping(value="jsp/admin/do_Project_one_details.do")
	@ResponseBody
	public Map<String,Object>Project_one_details(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetStatisticsResponse getinfo = new GetStatisticsResponse();
		map.put("statistics", getinfo.Project_one_details(req));
		return map;
	}
	
	//管理员修改个人信息
	@RequestMapping(value="jsp/admin/do_modifyAdminInfo.do")
	@ResponseBody
	public Map<String,Object> modifyAdminInfo(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		GetUserResponse getinfo = new GetUserResponse();
		map.put("userinfo", getinfo.modifyAdminInfo(req));
		return map;
	}
	
	
	/*Utils
	 * 
	 */
	//项目 select下拉菜单
	@RequestMapping(value="jsp/*/public/select_project.do")
	@ResponseBody
	public Map<String,Object>select_project(){
		Map<String,Object> map = new HashMap<String,Object>();
		Utils utils = new Utils();
		map.put("utils", utils.select_project());
		return map;
	}
		
	//第几周到几周下拉菜单
	@RequestMapping(value="jsp/*/select_weeks.do")
	@ResponseBody
	public Map<String,Object>select_weeks(){
		Map<String,Object> map = new HashMap<String,Object>();
		Utils utils = new Utils();
		map.put("utils", utils.select_weeks());
		return map;
	}
	
	//显示第几周
	@RequestMapping(value="jsp/*/public/show_thisweek.do")
	@ResponseBody
	public Map<String,Object>show_thisweek(){
		Map<String,Object> map = new HashMap<String,Object>();
		Utils utils = new Utils();
		map.put("utils", utils.show_thisweek());
		return map;
	}
	//显示用户名
	@RequestMapping(value="jsp/*/show_userName.do")
	@ResponseBody
	public Map<String,Object>show_userName(){
		Map<String,Object> map = new HashMap<String,Object>();
		Utils utils = new Utils();
		map.put("utils", utils.show_userName());
		return map;
	}
	
}