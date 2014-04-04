package com.ucs.connect.date;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucs.business.object.ProjectInfo;

public class GetProjectResponse {
	
	Gson  gson =  new Gson();
	RequestInfo resinfo = new RequestInfo();
	Properties properties = new Properties();
	Logger loger = Logger.getLogger(GetProjectResponse.class.getName());
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	InputStream inputStream = classLoader.getResourceAsStream("Requesturi.properties");
	
	//项目页数
	public Integer projectPages(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("projectName", req.getParameter("projectName")));
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		String startTime="";
		String endTime="";
		String regexp = "\"";
		if(req.getParameter("starTime").equals("")){
			startTime = req.getParameter("year")+"-01-01";
			
		}
		if(req.getParameter("endTime").equals("")){
			endTime = req.getParameter("year")+"-12-31";
		}
		if(req.getParameter("starTime").compareTo(req.getParameter("endTime"))<=0){
			params.add(new BasicNameValuePair("starTime", startTime.replaceAll(regexp,"" )));
			params.add(new BasicNameValuePair("endTime", endTime.replaceAll(regexp,"" )));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_projectpages.do";
		Integer result = Integer.parseInt(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		return result;
		}else{
			return null;
		}
	}
		
	//添加项目
	public boolean addProject(HttpServletRequest req) {
		ProjectInfo project = new ProjectInfo();
		System.out.println(req.getParameter("projectName")+","+req.getParameter("projectState")+","+req.getParameter("projectRemark")+","+req.getParameter("projectType"));
		project.setprojectName(req.getParameter("projectName"));
		project.setprojectState(req.getParameter("projectState"));
		project.setprojectRemark(req.getParameter("projectRemark"));
		project.setprojectType(req.getParameter("projectType"));
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("project_info", gson.toJson(project)));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_addProject.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		return result;
	}	
		
	//显示项目
	public List<ProjectInfo> findProjects(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page", req.getParameter("page")));
		params.add(new BasicNameValuePair("projectName", req.getParameter("projectName")));
		String startTime=req.getParameter("startTime");
		String endTime=req.getParameter("endTime");
		String regexp = "\"";
		loger.info(req.getParameter("page")+","+req.getParameter("pageSize")+","+req.getParameter("projectName")+","+req.getParameter("startTime")+","+req.getParameter("endTime"));
		System.out.println("date="+req.getParameter("startTime"));
		if(req.getParameter("startTime").equals("")){
			startTime = req.getParameter("year")+"-01-01";
			
		}
		if(req.getParameter("endTime").equals("")){
			endTime = req.getParameter("year")+"-12-31";
		}
		if(req.getParameter("startTime").compareTo(req.getParameter("endTime"))<=0){
			params.add(new BasicNameValuePair("startTime", startTime));
			params.add(new BasicNameValuePair("endTime", endTime));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_findProjects.do";
		List<ProjectInfo> result = gson.fromJson(resinfo.requestinfo(params,url), new TypeToken<List<ProjectInfo>>() {  }.getType());
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.size());
		return result;
		}else{
			return null;
		}
	}
	
	//根据id查看项目
	public ProjectInfo ProjectsByID(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", req.getParameter("id")));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_findProjectsByID.do";
		ProjectInfo result = gson.fromJson(resinfo.requestinfo(params,url),ProjectInfo.class);
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.getprojectName());
		return result;
	}
	
	//更新项目信息
	public Boolean updateProject(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		ProjectInfo project = new ProjectInfo();
		loger.info("入参="+req.getParameter("id")+","+req.getParameter("projectName")+","+req.getParameter("projectRemark")+","+req.getParameter("projectState")+","+req.getParameter("projectType"));
		project.setid(Integer.parseInt(req.getParameter("id")));
		project.setprojectName(req.getParameter("projectName"));
		project.setprojectRemark(req.getParameter("projectRemark"));
		project.setprojectState(req.getParameter("projectState"));
		project.setprojectType((req.getParameter("projectType")));
		params.add(new BasicNameValuePair("project_info", gson.toJson(project)));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_updateProject.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		return result;
	}

}
