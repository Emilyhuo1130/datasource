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
	public void setInterFace(Task interFace) {
		this.interFace = interFace;
	}
	@Resource
	private Admin admininterFace;
	public void setInterFace(Admin interFace) {
		this.admininterFace = interFace;
	}
	
	static Boolean flag=false;
	/***返回周计划总页数**/
	@RequestMapping(value = "user/do_geWeekTaskPageCount")
	@ResponseBody
	public Integer do_geWeekTaskPageCount(@RequestParam  int pageSize,int weekNo,String year,String userAccount) {
		log.info("返回周计划总页数 :"+weekNo+"    "+year);
		Integer num =interFace.do_geWeekTaskPageCount(weekNo,year,userAccount,pageSize);
		log.info(num);
		return num;
	}
	
	/***查看本周任务和显示计划任务**/
	@RequestMapping(value = "user/do_showThisWeekTask")
	@ResponseBody
	public List<Weektask> showThisWeekTask(@RequestParam int page, int pageSize,int weekNo,String year,String userAccount) {
		log.info("查看本周任务 :"+weekNo+"    "+year);
		String userName=admininterFace.do_findUserName(userAccount);
		List<Weektask> list =interFace.showThisWeekTask(weekNo,year,userName,page,pageSize);
		 Gson gson =new Gson();
		 log.info(gson.toJson(list));
		return list;
	}
	
	/***通过id查找周计划任务**/
	@RequestMapping(value = "user/do_findWeekTaskById")
	@ResponseBody
	public Weektask updateNextWeekTask(@RequestParam int id) {
		log.info("通过id查找周计划任务 :"+id);
		Weektask info =interFace.do_findNextWeekTaskById(id);
		info.setUpdateTime(null);
		return info;
	}
	/***修改周计划任务**/
	@RequestMapping(value = "user/do_updateNextWeekTask")
	@ResponseBody
	public Boolean updateNextWeekTask(@RequestParam String weekTaskInfo) {
		Gson gson =new Gson();
		log.info("修改下周计划任务 :"+weekTaskInfo);
		Weektask task=gson.fromJson(weekTaskInfo, Weektask.class);
		Weektask oldinfo =interFace.do_findNextWeekTaskById(task.getId());
		
		oldinfo.setProjectName(task.getProjectName());
		oldinfo.setWeekTask(task.getWeekTask());
		log.info(gson.toJson(oldinfo));
		flag =interFace.do_updateNextWeekTask(oldinfo);
		return flag;
	}
	/***删除周计划任务***/
	@RequestMapping(value = "user/do_deleteWeekTask")
	@ResponseBody
	public Boolean do_deleteWeekTask(@RequestParam int id) {
		log.info("删除周计划任务"+id);	
		Weektask info =interFace.do_findNextWeekTaskById(id);
		flag =interFace.do_deleteWeekTask(info);
		return flag;
	}
	
	/***添加下周计划任务**/
	@RequestMapping(value = "user/do_addNextWeekTask")
	@ResponseBody
	public Boolean addNextWeekTask(@RequestParam String weekTaskInfo) {
		log.info("添加下周计划任务 :"+weekTaskInfo);
		Gson gson =new Gson();
		Weektask task=gson.fromJson(weekTaskInfo, Weektask.class);
		String userName=admininterFace.do_findUserName(task.getUserAccount());
		task.setUserAccount(userName);
		task.setUpdateTime(new Date());
		flag =interFace.do_addNextWeekTask(task);
		return flag;
	}
	
	
	/**添加任务***/
	@RequestMapping(value = "user/do_addTasks")
	@ResponseBody
	public boolean do_addTasks(@RequestParam String tasks_info) {
		log.info("添加任务:"+tasks_info);
		 Gson gson =new Gson();
		List<TaskParticulars> list=gson.fromJson(tasks_info,  
                new TypeToken<List<TaskParticulars>>() {  
        }.getType()); 
		String workName=list.get(0).getUserName();
		String userName=admininterFace.do_findUserName(workName);
		for(TaskParticulars info:list){
			info.setUserName(userName);
		}
		flag =interFace.do_addTasks(list);
		
		log.info("添加任务是否成功返回状态："+flag);
		return flag;
		
	}
	
	/***普通用户查看自己的历史任务**/
	@RequestMapping(value = "user/do_findHitoryTask")
	@ResponseBody
	public HistoryTaskList do_findHitoryTask(@RequestParam String startTime,String endTime,String userName,
			int page, int pageSize) {
		log.info("*普通用户查看历史任务");
		HistoryTaskList list =interFace.do_findHitoryTask(startTime,endTime,userName,page,pageSize);
		Gson gson =new Gson();
		log.info(gson.toJson(list));
		return list;
		
	}
	/**根据id来获取某个历史任务***/
	@RequestMapping(value = "user/do_findHitoryTaskById")
	@ResponseBody
	public TaskParticulars do_findHitoryTaskById(@RequestParam int id) {
		log.info("*根据id来获取某个历史任务");
		TaskParticulars info =interFace.do_findHitoryTaskById(id);
		Gson gson =new Gson();
		log.info(gson.toJson(info)); 
		return info;
	}
	
	/***根据id来修改某个历史任务
	 * @throws ParseException **/
	@RequestMapping(value = "user/do_updateHitoryTask")
	@ResponseBody
	public boolean do_updateHitoryTaskById(@RequestParam String taskinfo) throws ParseException {
		log.info("*根据id来修改某个历史任务"+taskinfo);
		Gson gson =new Gson();
		TaskParticulars infonew=gson.fromJson(taskinfo, TaskParticulars.class);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date date=sdf.parse(infonew.getFarmatDate());
		TaskParticulars infoold =interFace.do_findHitoryTaskByIdNoModify(infonew.getId());
		infoold.setFinishHouse(infonew.getFinishHouse());
		infoold.setProjectName(infonew.getProjectName());
		infoold.setTaskDetail(infonew.getTaskDetail());
		infoold.setCommitDare(date);
		log.info(gson.toJson(infoold));
		 flag =interFace.do_updateHitoryTask(infoold);
		return flag;
	}
	/***根据id来删除某个历史任务
	 * @throws ParseException **/
	@RequestMapping(value = "user/do_deleteHistoryMission")
	@ResponseBody
	public boolean do_deleteHistoryMission(@RequestParam int id) throws ParseException {
		TaskParticulars infoold =interFace.do_findHitoryTaskByIdNoModify(id);
		Gson gson=new Gson();
		log.info(gson.toJson(infoold));
		 flag =interFace.do_deleteHistoryMission(infoold);
		return flag;
	}
	
	/***普通用户要修改自己信息，得到自己的信息*/
	@RequestMapping(value = "user/do_getMyUserInfo")
	@ResponseBody
	public UserInfo do_getMyUserInfo(@RequestParam String userAccount) {
		log.info("普通用户要修改自己信息，得到自己的信息"+userAccount);
		UserInfo info =interFace.do_getMyUserInfo(userAccount);
		return info;
		
	}
	
	/***普通用户 修改密码前的原密码验证**/
	@RequestMapping(value = "user/do_verifyPW")
	@ResponseBody
	public boolean do_verifyPW(@RequestParam String userAccount,String password) {
		log.info("普通用户 修改密码前的原密码验证 userName:"+userAccount+"    password:"+password);
		flag =interFace.do_verifyPW(userAccount,password);
		return flag;
		
	}
	
	/***普通用户 修改个人信息**/
	@RequestMapping(value = "user/do_modifyMyInfo")
	@ResponseBody
	public boolean do_modifyMyInfo(@RequestParam String user_info) {
		Gson gson=new Gson();
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
		flag =interFace.do_modifyMyInfo(oldinfo);
		return flag;
		
	}
	
	
	
	
	
	
	
	                /***管理员 任务管理   界面*/
	
	
	/***管理员 任务管理   显示 共几页**/
	@RequestMapping(value = "public/do_findTaskspages")
	@ResponseBody
	public int do_findTaskspage(@RequestParam int pageSize,
			 String projectName,
			 int fromWeek, int toWeek, String userName) {
		log.info("管理员 任务管理   显示 共几页");
		int count=interFace.do_findTaskspage(pageSize,projectName,fromWeek,toWeek,userName);
		return count;
		
	}
	/***管理员 任务管理查询**/
	@RequestMapping(value = "public/do_findTasks")
	@ResponseBody
	public List<TaskParticulars> do_findTasks(@RequestParam String projectName,
			int fromWeek,int toWeek,String year, int pageSize,
			int page, String userName) {
		log.info("/***管理员 任务管理查询**/");
		List<TaskParticulars> list=interFace.do_findTasks(projectName,
				fromWeek,toWeek,year,pageSize,page,userName);
		Gson gson=new Gson();
		log.info("管理员 任务管理查询:"+gson.toJson(list));
		return list;
		
	}
	/***管理员查看某个用户 返回多有用户姓名**/ 
	@RequestMapping(value = "admin/do_select_AllUser")
	@ResponseBody
	public List<String> do_select_AllUser() {
		log.info("/***管理员查看某个用户 返回多有用户姓名**/");
		List<String> list=interFace.do_select_AllUser();
		Gson gson=new Gson();
		log.info("管理员 任务管理查询:"+gson.toJson(list));
		return list;
		
	}
	
	
	/****显示每个人的周任务 按人查 like***/
	@RequestMapping(value = "admin/do_select_AllUserHistoryTasks")
	@ResponseBody
	public WeekTaskInfoResponse do_select_AllUserHistoryTasks(@RequestParam int startweekNo,int endweekNo,
			String userName,int page,int pageSize) {
		log.info("/***显示每个人的周任务 **/");
		WeekTaskInfoResponse list=interFace.do_select_AllUserHistoryTasks(startweekNo,endweekNo,userName,page,pageSize);
		Gson gson=new Gson();
		log.info("显示每个人的周任务:"+gson.toJson(list));
		return list;
		
	}
	
	/***每人的历史日报 名字 时间查****/
	@RequestMapping(value = "admin/do_select_AllUserHistoryWeekTasks")
	@ResponseBody
	public TaskParticularsInfoResponse do_select_AllUserHistoryWeekTasks(@RequestParam 
			String userName,int page,int pageSize,String starTime ,String endTime) {
		log.info("/***每人的历史日报**/");
		TaskParticularsInfoResponse list=interFace.do_select_AllUserHistoryWeekTasks(userName,page, pageSize, starTime , endTime);
		Gson gson=new Gson();
		log.info("每人的历史日报:"+gson.toJson(list));
		return list;
		
	}
}
