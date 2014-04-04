package com.ucs.tdc.protocol;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;
import com.ucs.tdc.pojo.TaskParticulars;
import com.ucs.tdc.pojo.UserInfo;
import com.ucs.tdc.pojo.Weektask;
import com.ucs.tdc.protocol.Adminprotocol.UTF8PostMethod;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
public class Taskprotorcol {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static void main(String[] args) throws HttpException, IOException {
		// TODO Auto-generated method stub
		/***普通用户 添加任务**/
		//user_addTask();
		
		/***普通用户 修改密码前的原密码验证**/ 
		//vartify_pw();
		
		/***普通用户 修改密码**/
		//modifyPW();
		
		
		
		/*******************任务管理界面**************************/
		/***返回总页数**/
		//returnpages();
		
		/***管理员 任务管理查询**/
		//findAlltasks();
		
		
		/****显示人员*/
		//selectaiiuser();
		
		/**查看周计划任务**/
		//showThisWeekTask();
		
		//updateNextWeekTask();
		
		/***添加下周计划任务**/
		//addNextWeekTask();
		
		//do_findHitoryTask();
		
		//GsonBuilder();
		/**根据id来获取某个历史任务***/
		//do_findHitoryTaskById();
		/***根据id来删除某个历史任务
		 * @throws ParseException **/
		//do_deleteHistoryMission();
		
		/***修改周计划任务**/
		
		/****显示每个人的周任务 按人查 like***/
		do_select_AllUserHistoryTasks();
		/***每人的历史日报 名字 时间查****/
		//do_select_AllUserHistoryWeekTasks();
		
	}

	
	private static void do_select_AllUserHistoryWeekTasks() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage_Server/admin/do_select_AllUserHistoryWeekTasks.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("userName", "陈浩");
		method.setParameter("starTime", "2013-01-01");
		method.setParameter("endTime", "2014-01-01");
		method.setParameter("page", "1");
		method.setParameter("pageSize", "10");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}


	private static void do_select_AllUserHistoryTasks() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage_Server/admin/do_select_AllUserHistoryTasks.do?";
		PostMethod method = new UTF8PostMethod(url);
	//	method.setParameter("userName", "陈");
		method.setParameter("startweekNo", "44");
		method.setParameter("endweekNo", "44");
		method.setParameter("page", "1");
		method.setParameter("pageSize", "10");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}


	private static void do_deleteHistoryMission() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage_Server/user/do_deleteHistoryMission.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("id", "1");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}


	private static void do_findHitoryTaskById() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage/user/do_findHitoryTaskById.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("id", "1");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        String ss=method.getResponseBodyAsString();
        TaskParticulars info=gson.fromJson(ss, TaskParticulars.class);
        method.releaseConnection();  
	}


/**GsonBuilder 日期格式化***/
	private static void GsonBuilder() {
		// TODO Auto-generated method stub
		//Gson gson2=new Gson();
		Gson gson2=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		List<Person> list=new ArrayList<Person>();
		
		Person p=new Person();
		p.setName("lisi");
		p.setAge(22);
		p.setBirthday(new Date());
		list.add(p);
		System.out.println(gson2.toJson(list));
		String strinfo=gson2.toJson(list);
		
		List<Person> listinfo=gson2.fromJson(strinfo,
				new TypeToken<List<Person>>() {
		}.getType());
		System.out.println(listinfo.get(0).getBirthday());
	}



	private static void do_findHitoryTask() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage_Server/user/do_findHitoryTask.do?";
		PostMethod method = new UTF8PostMethod(url);
	
		method.setParameter("startTime", "2013-01-01");
		method.setParameter("endTime", "2014-01-01");
		method.setParameter("pageSize", "2");
		method.setParameter("userName", "陈浩");
		method.setParameter("page", "1");
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}



	private static void addNextWeekTask() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage_Server/user/do_addNextWeekTask.do?";
		PostMethod method = new UTF8PostMethod(url);
		Weektask info=new Weektask();
		info.setUserAccount("chenhao");
		info.setWeekNo(42);
		info.setWeekTask("周计划任务");
		String ss=gson.toJson(info);
		method.setParameter("weekTaskInfo", ss);
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}



	private static void updateNextWeekTask() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage_Server/user/do_updateNextWeekTask.do?";
		PostMethod method = new UTF8PostMethod(url);
		Weektask info=new Weektask();
		info.setId(1);
		info.setProjectName("00000000");
		info.setWeekTask("周计划任务000000000000000");
		String ss=gson.toJson(info);
		method.setParameter("weekTaskInfo", ss);
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}



	private static void showThisWeekTask() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage_Server/user/do_showThisWeekTask.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("weekNo", "44");
		method.setParameter("year", "2014");
		method.setParameter("userAccount", "chenhao");
		method.setParameter("page", "1");
		method.setParameter("pageSize", "10");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
       // gson.fromJson(method.getResponseBodyAsString(), Weektask.class);
        method.releaseConnection();  
	}



	private static void selectaiiuser() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage/admin/do_select_AllUser.do?";
		PostMethod method = new UTF8PostMethod(url);
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}



	private static void findAlltasks() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://192.168.2.101:8080/Task_Manage/admin/do_findTasks.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("fromWeek", "0");
		method.setParameter("toWeek", "88");
		method.setParameter("pageSize", "2");
		method.setParameter("projectName", "");
		method.setParameter("year", "2013-00-00 00:00:00");
		method.setParameter("page", "1");
	
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}



	private static void returnpages() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage/admin/do_findTaskspages.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("fromWeek", "0");
		method.setParameter("toWeek", "88");
		method.setParameter("pageSize", "2");
		method.setParameter("projectName", "");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}

	private static void modifyPW() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage/user/do_modifyPW.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("userName", "陈浩");
		method.setParameter("password", "123567");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}

	private static void vartify_pw() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage/user/do_verifyPW.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("userName", "陈浩");
		method.setParameter("password", "12356");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}

	private static void user_addTask() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		TaskParticulars task=new TaskParticulars();
		List<TaskParticulars> info=new ArrayList<TaskParticulars>();
		task.setProjectName("国客星空二期");
		task.setFinishHouse((float) 2.4);
		task.setUserName("chenhao");
		task.setTaskDetail("工作详情");
		//task.setCommitDare(new Timestamp(l));
	//	task.setWeekNumber(43);
		info.add(task);
		String str=gson.toJson(info);
		System.out.println(str);
		String url = "http://localhost:8080/Task_Manage/user/do_addTasks.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("tasks_info", str);
		
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}

}
