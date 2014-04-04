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
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.ucs.business.object.Project_Statistics;
import com.ucs.business.object.Project_Statistics_details_List;
import com.ucs.business.object.TaskParticulars;

public class GetStatisticsResponse {
	
	Gson  gson =  new Gson();
	RequestInfo resinfo = new RequestInfo();
	Properties properties = new Properties();
	Logger loger = Logger.getLogger(GetStatisticsResponse.class.getName());
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	InputStream inputStream = classLoader.getResourceAsStream("Requesturi.properties");
	
	//统计页面页数
	public Integer Statisticspages(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("projectName", req.getParameter("projectName")));
		String startTime=req.getParameter("startTime");
		String endTime=req.getParameter("endTime");
		loger.info(req.getParameter("projectName")+","+req.getParameter("pageSize")+","+req.getParameter("page")+","+req.getParameter("starTime")+","+req.getParameter("endTime")+",");
		String regexp = "\"";
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
		String url = properties.getProperty("uri")+"/admin/do_Project_Statisticspages.do";
		Integer result = Integer.parseInt(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		return result;
		}
		else{
			return null;
		}
	}	
		
	//项目统计
	public List<Project_Statistics> ProjectStatistics(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		List<Project_Statistics> result=null;
		params.add(new BasicNameValuePair("projectName",req.getParameter("projectName")));
		params.add(new BasicNameValuePair("pageSize",req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page",req.getParameter("page")));
		String startTime=req.getParameter("starTime");
		String endTime=req.getParameter("endTime");
		String regexp = "\"";
		try {
			if(req.getParameter("starTime").equals("")){
				startTime = req.getParameter("year")+"-01-01";
				
			}
			if(req.getParameter("endTime").equals("")){
				endTime = req.getParameter("year")+"-12-31";
			}
			if(req.getParameter("starTime").compareTo(req.getParameter("endTime"))<=0){
				params.add(new BasicNameValuePair("starTime", startTime));
				params.add(new BasicNameValuePair("endTime", endTime));
			try {
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String url = properties.getProperty("uri")+"/admin/do_search_Project_Statistics.do";
			result = gson.fromJson(resinfo.requestinfo(params,url), new TypeToken<List<Project_Statistics>>() {  }.getType());
			loger.info("request uri:"+url);
			loger.info("request parammter:"+params);
			//loger.info("response:"+result.size());
			
			}
			else{
				return  null;
			}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			loger.info("---有空值---"); 
		}
		return result;
	}	
		
	//项目详情
	public Project_Statistics_details_List Projectdetails(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("projectName",req.getParameter("projectName")));
		params.add(new BasicNameValuePair("userName",req.getParameter("userName")));
		params.add(new BasicNameValuePair("pageSize",req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page",req.getParameter("page")));
		String startTime=req.getParameter("starTime");
		String endTime=req.getParameter("endTime");
		String regexp = "\"";
		if(req.getParameter("starTime").equals("")){
			startTime = req.getParameter("year")+"-01-01";
			
		}
		if(req.getParameter("endTime").equals("")){
			endTime = req.getParameter("year")+"-12-31";
		}
		if(req.getParameter("starTime").compareTo(req.getParameter("endTime"))<=0){
			params.add(new BasicNameValuePair("starTime",startTime));
			params.add(new BasicNameValuePair("endTime", endTime));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_Project_Statistics_details.do";
		Project_Statistics_details_List result = gson.fromJson(resinfo.requestinfo(params,url),Project_Statistics_details_List.class);
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.getdetailsList().size());
		return result;
		}
		else{
			return null;
			}
	}
	
	//个人工时统计
	public List< TaskParticulars> Project_one_details(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("projectName",req.getParameter("projectName")));
		params.add(new BasicNameValuePair("pageSize",req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page",req.getParameter("page")));
		params.add(new BasicNameValuePair("userName",req.getParameter("userName")));
		String startTime=req.getParameter("startTime");
		String endTime=req.getParameter("endTime");
		loger.info("个人工时统计:startTime="+startTime+","+"endTime="+endTime);
		String regexp = "\"";
		if(req.getParameter("startTime").equals("")){
			startTime = req.getParameter("year")+"-01-01";
			
		}
		if(req.getParameter("endTime").equals("")){
			endTime = req.getParameter("year")+"-12-31";
		}
		if(req.getParameter("startTime").compareTo(req.getParameter("endTime"))<=0){
			params.add(new BasicNameValuePair("starTime", startTime));
			params.add(new BasicNameValuePair("endTime",endTime));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_Project_one_details.do";
		List< TaskParticulars> result = gson.fromJson(resinfo.requestinfo(params,url),new TypeToken<List<TaskParticulars>>() {  }.getType());
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.size());
		return result;
		}
		else{
			return null;
			}
	}

}
