package com.ucs.tdc.protocol;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;
import com.ucs.tdc.pojo.ProjectInfo;
import com.ucs.tdc.protocol.Adminprotocol.UTF8PostMethod;
import com.google.gson.GsonBuilder;
public class Projectprotocol {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static void main(String[] args) throws HttpException, IOException {
		// TODO Auto-generated method stub
		/***返回总页数***、
		 */
		//returnpages();
		
		/***管理员 添加项目**/
		//add_project();
		
		/***管理员 显示项目**/   
		//showAllprojects();
		
		/********************************项目统计界面*************************、
		 * 
		 */
		/**返回   项目统计   页数***/    
		//returnprojectpages();
		
		/****返回  项目统计 查询***/  
		//do_Project_Statistics();
		
		/**项目统计  查看详情按钮***/
		//do_Project_Statistics_details();
		
		/**更新项目***/
		//do_updateProjectsByID();
		/**多条件查询测试**/
		//do_find_Test_Projects();
		
	}

	private static void do_find_Test_Projects() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		
		String url = "http://localhost:8080/Task_Manage/admin/do_find_Test_Projects.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("starTime", "2013-01-01");
		method.setParameter("endTime", "2014-01-01");
		method.setParameter("projectName", "国客星空");
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();
	}

	private static void do_updateProjectsByID() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		ProjectInfo info=new ProjectInfo();
		info.setId(5);
		info.setProjectName("测试小项目");
		info.setProjectState("策划中");
		info.setProjectRemark("策划备注");
		String str=gson.toJson(info);
		String url = "http://localhost:8080/Task_Manage/admin/do_updateProject.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("project_info",str);
	
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();
	}

	private static void do_Project_Statistics_details() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		
		String url = "http://localhost:8080/Task_Manage_Server/admin/do_Project_Statistics_details.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("pageSize", "5");
		method.setParameter("page", "1");
		method.setParameter("projectName", "国客星空");
		method.setParameter("starTime", "2013-01-01 00:00:00");
		method.setParameter("endTime", "2014-01-01 00:00:00");
		method.setParameter("userName", "chenhao");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();
	}

	private static void do_Project_Statistics() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		
		String url = "http://localhost:8080/Task_Manage/admin/do_search_Project_Statistics.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("starTime", "2013-01-01");
		method.setParameter("endTime", "2014-01-01");
		method.setParameter("pageSize", "3");
		method.setParameter("projectName", "国客星空");
		method.setParameter("page", "1");
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();
	}

	private static void returnprojectpages() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		
		String url = "http://localhost:8080/Task_Manage/admin/do_Project_Statisticspages.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("projectName", "");
		method.setParameter("pageSize", "2");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();
	}

	private static void showAllprojects() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		HttpClient client = new HttpClient(); 
		
		String url = "http://localhost:8080/Task_Manage/admin/do_findProjects.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("pageSize", "3");
		method.setParameter("page", "1");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
		
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        String ss=method.getResponseBodyAsString();
        
        method.releaseConnection();
	}

	private static void add_project() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		ProjectInfo info=new ProjectInfo ();
		info.setProjectName("国客星空4期");
		info.setProjectState("研发中");
		info.setProjectRemark("备注");
		String str=gson.toJson(info);
		String url = "http://localhost:8080/Task_Manage/admin/do_addProject.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("project_info", str);
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
		String url = "http://localhost:8080/Task_Manage/admin/do_projectpages.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("pageSize", "2");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}

}
