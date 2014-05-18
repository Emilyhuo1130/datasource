package com.ucs.tdc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucs.tdc.interFace.Admin;
import com.ucs.tdc.interFace.Task;
import com.ucs.tdc.pojo.HistoryTaskList;
import com.ucs.tdc.pojo.TaskParticulars;
import com.ucs.tdc.pojo.TaskParticularsInfoResponse;
import com.ucs.tdc.pojo.UserInfo;
import com.ucs.tdc.pojo.WeekTaskInfoResponse;
import com.ucs.tdc.pojo.Weektask;
/**任务管理**/
@Controller
public class TaskController {
	static Logger log = Logger.getLogger(AdminController.class);
	@Resource
	private Task interFace;
	
	@Resource
	private Admin admininterFace;
	Gson gson =new Gson();
	
	/***返回周计划总页数**/
	@RequestMapping(value = "user/do_geWeekTaskPageCount")
	@ResponseBody
	public Integer do_geWeekTaskPageCount(@RequestParam  int pageSize,int weekNo,String year,String userAccount) {
		return interFace.do_geWeekTaskPageCount(weekNo,year,userAccount,pageSize);
	}
	
	/***查看本周任务和显示计划任务**/
	@RequestMapping(value = "user/do_showThisWeekTask")
	@ResponseBody
	public List<Weektask> showThisWeekTask(@RequestParam int page, int pageSize,int weekNo,String year,String userAccount) {
		String userName=admininterFace.do_findUserName(userAccount);
		return interFace.showThisWeekTask(weekNo,year,userName,page,pageSize);
	}
	
	/***通过id查找周计划任务**/
	@RequestMapping(value = "user/do_findWeekTaskById")
	@ResponseBody
	public Weektask updateNextWeekTask(@RequestParam int id) {
		Weektask info =interFace.do_findNextWeekTaskById(id);
		info.setUpdateTime(null);
		return info;
	}
	/***修改周计划任务**/
	@RequestMapping(value = "user/do_updateNextWeekTask")
	@ResponseBody
	public Boolean updateNextWeekTask(@RequestParam String weekTaskInfo) {
		Weektask task=gson.fromJson(weekTaskInfo, Weektask.class);
		Weektask oldinfo =interFace.do_findNextWeekTaskById(task.getId());
		oldinfo.setProjectName(task.getProjectName());
		oldinfo.setWeekTask(task.getWeekTask());
		return interFace.do_updateNextWeekTask(oldinfo);
	}
	/***删除周计划任务***/
	@RequestMapping(value = "user/do_deleteWeekTask")
	@ResponseBody
	public Boolean do_deleteWeekTask(@RequestParam int id) {
		return interFace.do_deleteWeekTask(interFace.do_findNextWeekTaskById(id));
	}
	
	/***添加下周计划任务**/
	@RequestMapping(value = "user/do_addNextWeekTask")
	@ResponseBody
	public Boolean addNextWeekTask(@RequestParam String weekTaskInfo) {
		Weektask task=gson.fromJson(weekTaskInfo, Weektask.class);
		String userName=admininterFace.do_findUserName(task.getUserAccount());
		task.setUserAccount(userName);
		task.setUpdateTime(new Date());
		return interFace.do_addNextWeekTask(task);
	}
	
	
	/**添加任务***/
	@RequestMapping(value = "user/do_addTasks")
	@ResponseBody
	public boolean do_addTasks(@RequestParam String tasks_info) {
		List<TaskParticulars> list=gson.fromJson(tasks_info,  
                new TypeToken<List<TaskParticulars>>() {  
        }.getType()); 
		String workName=list.get(0).getUserName();
		String userName=admininterFace.do_findUserName(workName);
		for(TaskParticulars info:list){
			info.setUserName(userName);
		}
		return interFace.do_addTasks(list);
		
	}
	
	/***普通用户查看自己的历史任务**/
	@RequestMapping(value = "user/do_findHitoryTask")
	@ResponseBody
	public HistoryTaskList do_findHitoryTask(@RequestParam String startTime,String endTime,String userName,
			int page, int pageSize) {
		return interFace.do_findHitoryTask(startTime,endTime,userName,page,pageSize);
	}
	/**根据id来获取某个历史任务***/
	@RequestMapping(value = "user/do_findHitoryTaskById")
	@ResponseBody
	public TaskParticulars do_findHitoryTaskById(@RequestParam int id) {
		return interFace.do_findHitoryTaskById(id);
	}
	
	/***根据id来修改某个历史任务
	 * @throws ParseException **/
	@RequestMapping(value = "user/do_updateHitoryTask")
	@ResponseBody
	public boolean do_updateHitoryTaskById(@RequestParam String taskinfo) throws ParseException {
		TaskParticulars infonew=gson.fromJson(taskinfo, TaskParticulars.class);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(infonew.getFarmatDate());
		TaskParticulars infoold =interFace.do_findHitoryTaskByIdNoModify(infonew.getId());
		infoold.setFinishHouse(infonew.getFinishHouse());
		infoold.setProjectName(infonew.getProjectName());
		infoold.setTaskDetail(infonew.getTaskDetail());
		infoold.setCommitDare(date);
		return interFace.do_updateHitoryTask(infoold);
	}
	/***根据id来删除某个历史任务
	 * @throws ParseException **/
	@RequestMapping(value = "user/do_deleteHistoryMission")
	@ResponseBody
	public boolean do_deleteHistoryMission(@RequestParam int id) throws ParseException {
		return interFace.do_deleteHistoryMission(interFace.do_findHitoryTaskByIdNoModify(id));
	}
	
	/***普通用户要修改自己信息，得到自己的信息*/
	@RequestMapping(value = "user/do_getMyUserInfo")
	@ResponseBody
	public UserInfo do_getMyUserInfo(@RequestParam String userAccount) {
		return interFace.do_getMyUserInfo(userAccount);
	}
	
	/***普通用户 修改密码前的原密码验证**/
	@RequestMapping(value = "user/do_verifyPW")
	@ResponseBody
	public boolean do_verifyPW(@RequestParam String userAccount,String password) {
		return interFace.do_verifyPW(userAccount,password);
	}
	
	/***普通用户 修改个人信息**/
	@RequestMapping(value = "user/do_modifyMyInfo")
	@ResponseBody
	public boolean do_modifyMyInfo(@RequestParam String user_info) {
		UserInfo updateinfo=gson.fromJson(user_info, UserInfo.class);
		UserInfo oldinfo=interFace.do_getMyUserInfo(updateinfo.getUserAccount());
		if((updateinfo.getUserPw()!=null)&&(updateinfo.getUserPw().length()>0)){
			oldinfo.setUserPw(updateinfo.getUserPw());
		}
		if((updateinfo.getPhoneNumber()!=null)&&(updateinfo.getPhoneNumber().length()>0)){
			oldinfo.setPhoneNumber(updateinfo.getPhoneNumber());
		}
		if((updateinfo.getUserName()!=null)&&(updateinfo.getUserName().length()>0)){
			oldinfo.setUserName(updateinfo.getUserName());
		}
		return interFace.do_modifyMyInfo(oldinfo);
	}
	
	
	
	
	
	
	
	                /***管理员 任务管理   界面*/
	
	
	/***管理员 任务管理   显示 共几页**/
	@RequestMapping(value = "public/do_findTaskspages")
	@ResponseBody
	public int do_findTaskspage(@RequestParam int pageSize,
			 String projectName,
			 int fromWeek, int toWeek, String userName) {
		return interFace.do_findTaskspage(pageSize,projectName,fromWeek,toWeek,userName);
	}
	/***管理员 任务管理查询**/
	@RequestMapping(value = "public/do_findTasks")
	@ResponseBody
	public List<TaskParticulars> do_findTasks(@RequestParam String projectName,
			int fromWeek,int toWeek,String year, int pageSize,
			int page, String userName) {
		List<TaskParticulars> list=interFace.do_findTasks(projectName,
				fromWeek,toWeek,year,pageSize,page,userName);
		return list;
		
	}
	/***管理员查看某个用户 返回多有用户姓名**/ 
	@RequestMapping(value = "admin/do_select_AllUser")
	@ResponseBody
	public List<String> do_select_AllUser() {
		return interFace.do_select_AllUser();
	}
	
	
	/****显示每个人的周任务 按人查 like***/
	@RequestMapping(value = "admin/do_select_AllUserHistoryTasks")
	@ResponseBody
	public WeekTaskInfoResponse do_select_AllUserHistoryTasks(@RequestParam int startweekNo,int endweekNo,
			String userName,int page,int pageSize) {
		return interFace.do_select_AllUserHistoryTasks(startweekNo,endweekNo,userName,page,pageSize);
	}
	
	/***每人的历史日报 名字 时间查****/
	@RequestMapping(value = "admin/do_select_AllUserHistoryWeekTasks")
	@ResponseBody
	public TaskParticularsInfoResponse do_select_AllUserHistoryWeekTasks(@RequestParam 
			String userName,int page,int pageSize,String starTime ,String endTime) {
		return interFace.do_select_AllUserHistoryWeekTasks(userName,page, pageSize, starTime , endTime);
	}
}
