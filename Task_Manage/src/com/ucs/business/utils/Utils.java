package com.ucs.business.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucs.connect.date.GetUserResponse;
import com.ucs.connect.date.RequestInfo;

public class Utils {

	RequestInfo resinfo = new RequestInfo();
	Logger loger = Logger.getLogger(GetUserResponse.class.getName());
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	InputStream inputStream = classLoader.getResourceAsStream("Requesturi.properties");
	Gson  gson =  new Gson();
	public List<String> select_project() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/public/select_project.do";
		List<String> result = gson.fromJson(resinfo.requestinfo(params,url), new TypeToken<List<String>>() {  }.getType());
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		return result;
	}
	public List<String> select_weeks() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/public/select_weeks.do";
		return gson.fromJson(resinfo.requestinfo(params,url), new TypeToken<List<String>>() {  }.getType());
	}
	public String show_thisweek() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/public/show_thisweek.do";
		String result = resinfo.requestinfo(params,url);
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		return result;
	}
	public String show_userName() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_select_AllUser.do";
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		String result = resinfo.requestinfo(params,url);
		loger.info("response:"+result.toString());
		return result;
	}
}
