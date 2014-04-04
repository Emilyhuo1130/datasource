package com.ucs.connect.date;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucs.business.object.HistoryTaskList;
import com.ucs.business.object.TaskParticulars;
import com.ucs.business.object.TaskParticularsInfoResponse;
import com.ucs.business.object.UserInfo;
import com.ucs.business.object.WeekTask;
import com.ucs.business.object.WeekTaskInfoResponse;

public class GetTaskResponse {
	
	Gson  gson =  new Gson();
	RequestInfo resinfo = new RequestInfo();
	Properties properties = new Properties();
	Logger loger = Logger.getLogger(GetTaskResponse.class.getName());
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	InputStream inputStream = classLoader.getResourceAsStream("Requesturi.properties"); 
	
	//根据账户名查用户姓名
	public String findUserName(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("accountName", req.getParameter("userAccount")));
		loger.info("根据账户名查用户姓名,入参="+req.getParameter("userAccount"));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/public/do_findUserName.do";
		String result =resinfo.requestinfo(params,url);
		String regexp = "\"";
		result = result.replaceAll(regexp, "");
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result);
		return result;
	}
	
	//用户添加任务
	public String addTasks(HttpServletRequest req,String userAccount) {
		TaskParticulars task = new TaskParticulars();
		loger.info("用户名入参="+userAccount);
		task.setuserName(userAccount);
		task.setprojectName(req.getParameter("projectName"));
		task.setfinishHouse(Float.parseFloat(req.getParameter("taskTime")));
		task.settaskDetail(req.getParameter("taskDetail"));
		List <TaskParticulars> info = new ArrayList <TaskParticulars>();
		info.add(task);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tasks_info",  gson.toJson(info)));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_addTasks.do";
		String result =resinfo.requestinfo(params,url);
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result);
		return result;
	}
	
	//用户查询历史任务
	public HistoryTaskList findHistoryTask(HttpServletRequest req,String userName) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userName",userName));
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page", req.getParameter("page")));
		/*params.add(new BasicNameValuePair("startTime", req.getParameter("startTime")));
		params.add(new BasicNameValuePair("endTime", req.getParameter("endTime" )));*/
		String startTime="";
		String endTime="";
		String regexp = "\"";
		if(req.getParameter("startTime").equals("")){
			startTime = req.getParameter("year")+"-01-01";
			
		}
		if(req.getParameter("endTime").equals("")){
			endTime = req.getParameter("year")+"-12-31";
		}
		
		if(req.getParameter("startTime").compareTo(req.getParameter("endTime"))<=0){
			params.add(new BasicNameValuePair("startTime", req.getParameter("startTime")));
			params.add(new BasicNameValuePair("endTime", req.getParameter("endTime" )));
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String url = properties.getProperty("uri")+"/user/do_findHitoryTask.do";
			HistoryTaskList result =gson.fromJson(resinfo.requestinfo(params,url),HistoryTaskList.class);
			result.getPage();
			loger.info("request uri:"+url);
			loger.info("request parammter:"+params);
			loger.info("response:"+result.getPage());
			loger.info("response:"+result.getTaskListInfo().size());
			return result;		
		}
		else{
			return null;
		}
		
	}
	
	
	//根据id来获取本周任务-------------------------------------------------!
	public WeekTask do_getCurrentWeekMissionById(HttpServletRequest req) {
		// TODO Auto-generated method stub
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_findWeekTaskById.do";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id",req.getParameter("id")));
		WeekTask info=gson.fromJson(resinfo.requestinfo(params,url),WeekTask.class);
		loger.info("入参id="+req.getParameter("id"));
		loger.info("根据id来获取本周任务"+gson.toJson(info));
		return info;
	}
	//根据id来获取用户的单条历史任务
	public TaskParticulars do_getHistoryMissionById(HttpServletRequest req) {
		// TODO Auto-generated method stub
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_findHitoryTaskById.do";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id",req.getParameter("id")));
		TaskParticulars info=gson.fromJson(resinfo.requestinfo(params,url),TaskParticulars.class);
		loger.info("根据id来获取用户的单条历史任务"+gson.toJson(info));
		return info;
	}
	//获得下周计划的总页数
	public Integer getNextPlanTotalPage(HttpServletRequest req){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_geWeekTaskPageCount.do";
		loger.info("**************"+req.getParameter("pageSize")+","+req.getParameter("WeekNumber")+req.getParameter("year")+","+req.getParameter("userAccount"));
		params.add(new BasicNameValuePair("pageSize",req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("weekNo",req.getParameter("WeekNumber")));
		params.add(new BasicNameValuePair("year",req.getParameter("year")));
		params.add(new BasicNameValuePair("userAccount",req.getParameter("userAccount")));
		int totalPages=Integer.parseInt(resinfo.requestinfo(params,url));
		loger.info("totalPages="+totalPages);
		return totalPages;
	} 
	//用户更新本周任务-------------------------------------------------------!
	public Boolean updateCurrentWeekTask(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		WeekTask  weekTask  = new WeekTask();
		weekTask.setId(Integer.parseInt(req.getParameter("id")));
		weekTask.setProjectName(req.getParameter("projectName"));
		weekTask.setUpdateTime(null);
		weekTask.setUserAccount(req.getParameter("userAccount"));
		weekTask.setWeekNo(req.getParameter("weekNum"));
		weekTask.setWeekTask(req.getParameter("weekTask"));
		params.add(new BasicNameValuePair("weekTaskInfo",gson.toJson(weekTask)));
			
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			String url = properties.getProperty("uri")+"/user/do_updateNextWeekTask.do";
			Boolean result =gson.fromJson(resinfo.requestinfo(params,url),Boolean.class);
			
			loger.info("request uri:"+url);
			loger.info("request parammter:"+params);
			return result;		
		
	}
	//用户更新历史任务
	public Boolean updateHistoryTask(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		TaskParticulars  taskParticulars  = new TaskParticulars();
		taskParticulars.setid(Integer.parseInt(req.getParameter("id")));
		taskParticulars.setuserName(req.getParameter("userName"));
		taskParticulars.setfinishHouse(Float.parseFloat(req.getParameter("finishHouse")));
		taskParticulars.setprojectName(req.getParameter("projectName"));
		taskParticulars.setFarmatDate(req.getParameter("farmatDate"));
		taskParticulars.setcommitDare(null);
		taskParticulars.settaskDetail(req.getParameter("taskDetail"));
		params.add(new BasicNameValuePair("taskinfo",gson.toJson(taskParticulars)));
			
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			String url = properties.getProperty("uri")+"/user/do_updateHitoryTask.do";
			Boolean result =gson.fromJson(resinfo.requestinfo(params,url),Boolean.class);
			
			loger.info("request uri:"+url);
			loger.info("request parammter:"+params);
			return result;		
		
	}
	/**删除历史任务**/
	public Boolean do_deleteHistoryMission(HttpServletRequest req) {
		// TODO Auto-generated method stub
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_deleteHistoryMission.do";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id",req.getParameter("id")));
		loger.info("传给数据库的id="+req.getParameter("id"));
		String flag=resinfo.requestinfo(params,url);
		boolean b=Boolean.parseBoolean(flag);
		return b;
	}
	
	//用户添加下周计划
	public Boolean addPlans(HttpServletRequest req,String userAccount) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		WeekTask plan = new WeekTask();
		
		plan.setWeekTask(req.getParameter("planDetails"));
		loger.info("plan--------------="+req.getParameter("planDetails"));
		plan.setProjectName(req.getParameter("projectName"));
		plan.setUpdateTime(null);
		plan.setUserAccount(userAccount);
		plan.setWeekNo(req.getParameter("WeekNumber"));
		params.add(new BasicNameValuePair("weekTaskInfo",  gson.toJson(plan)));
		loger.info("gosn="+gson.toJson(plan));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_addNextWeekTask.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result);
		return result;	
	}
	//用户修删除周计划
	public Boolean deleteWeekTask(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id",req.getParameter("id")));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_deleteWeekTask.do";
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("response:"+result);
		return result;	
	}
	//用户修改下周计划
	public Boolean updateWeekTask(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		WeekTask plan = new WeekTask();
		plan.setId(Integer.parseInt(req.getParameter("id")));
		plan.setWeekNo(req.getParameter("WeekNumber"));
		plan.setWeekTask(req.getParameter("weekTask"));
		params.add(new BasicNameValuePair("weekTaskInfo",  gson.toJson(plan)));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_updateNextWeekTask.do";
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("response:"+result);
		return result;	
	}
	
	//用户查看下周计划
	public String lookNextWeekTaskPlan(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		/*WeekTask plan = new WeekTask();
		plan.setId(Integer.parseInt(req.getParameter("id")));
		plan.setWeekNo(req.getParameter("WeekNo"));
		plan.setWeekTask(req.getParameter("weekTask"));*/
		params.add(new BasicNameValuePair("WeekNumber",  req.getParameter("WeekNo")));
		params.add(new BasicNameValuePair("year",  req.getParameter("year")));
		params.add(new BasicNameValuePair("userAccount",  req.getParameter("userAccount")));
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page", req.getParameter("page")));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/showThisWeekTask.do";
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		String result = resinfo.requestinfo(params,url);
		loger.info("response:"+result);
		return result;	
	}
	
	//用户查看本周任务
	public List<WeekTask>  findPlans(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("year", req.getParameter("year")));
		params.add(new BasicNameValuePair("userAccount", req.getParameter("userAccount")));
		params.add(new BasicNameValuePair("weekNo", req.getParameter("WeekNumber")));
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page", req.getParameter("page")));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_showThisWeekTask.do";
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		Gson gson =new Gson();
		List<WeekTask> listWeekTask=gson.fromJson(resinfo.requestinfo(params,url),new TypeToken<List<WeekTask>>(){}.getType()); 
		try {
			loger.info("response:"+listWeekTask.get(0).getProjectName());
		} catch (Exception e) {
			loger.info("------------------null-------------");
		}
		return listWeekTask;	
	}
	
	//跳转到个人信息
	public UserInfo getMyUserInfo(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userAccount", req.getParameter("userAccount")));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_getMyUserInfo.do";
		UserInfo result = gson.fromJson(resinfo.requestinfo(params,url),UserInfo.class);
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.getName());
	    return result;
		}
	
	//修改个人信息
	public Boolean verifyPW(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userAccount", req.getParameter("userAccount")));
		params.add(new BasicNameValuePair("password", req.getParameter("oldPW")));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user/do_verifyPW.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString()); 
		loger.info("result="+result+","+(result==true)+"");
		if(result==true){
			loger.info(req.getParameter("newPW")+","+req.getParameter("rePassword"));
			if(req.getParameter("newPW").equals(req.getParameter("rePassword"))){
			UserInfo user = new UserInfo();
			user.setid(Integer.parseInt(req.getParameter("id")));
			user.setName(req.getParameter("userName"));
			user.setPassword(req.getParameter("newPW"));
			user.setuserAccount(req.getParameter("userAccount"));
			user.setphoneNumber(req.getParameter("phoneNumber"));
			params.add(new BasicNameValuePair("user_info",  gson.toJson(user)));
			url = properties.getProperty("uri")+"/user/do_modifyMyInfo.do";
			Boolean res = Boolean.parseBoolean(resinfo.requestinfo(params,url));
			loger.info("修改密码返回="+res);
			return res;		
			}else{
				String info="error";
				return false;
				}
			}
			else{
	           return false;
	           }
		}
	/****显示每个人的本周任务 按人查 like***/
	public WeekTaskInfoResponse do_select_AllUserHistoryTasks(HttpServletRequest req) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		
		params.add(new BasicNameValuePair("startweekNo", req.getParameter("startweekNo")));
		params.add(new BasicNameValuePair("endweekNo", req.getParameter("endweekNo")));
		params.add(new BasicNameValuePair("userName", req.getParameter("userName")));
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page", req.getParameter("page")));
		loger.info("--------显示每个人的本周任务 按人查-----------userName="+req.getParameter("userName")+"startweekNo="+req.getParameter("endweekNo"));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_select_AllUserHistoryTasks.do";
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		Gson gson =new Gson();
		WeekTaskInfoResponse listWeekTask=gson.fromJson(resinfo.requestinfo(params,url),WeekTaskInfoResponse.class); 
		try {
			
		} catch (Exception e) {
			loger.info("------------------null-------------");
		}
		return listWeekTask;
	}
	/***每人的历史日报 名字 时间查****/
	public TaskParticularsInfoResponse do_select_AllUserHistoryWeekTasks(
			HttpServletRequest req) {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("starTime", req.getParameter("starTime")));
		params.add(new BasicNameValuePair("endTime", req.getParameter("endTime")));
		params.add(new BasicNameValuePair("userName", req.getParameter("userName")));
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page", req.getParameter("page")));
		loger.info("-------每人的历史日报 名字 时间查------------userName="+req.getParameter("userName"));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_select_AllUserHistoryWeekTasks.do";
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		Gson gson =new Gson();
		TaskParticularsInfoResponse TaskParticulars=gson.fromJson(resinfo.requestinfo(params,url),TaskParticularsInfoResponse.class); 
		try {
			
		} catch (Exception e) {
			loger.info("------------------null-------------");
		}
		return TaskParticulars;
	}

	
	
	
		
}
		
