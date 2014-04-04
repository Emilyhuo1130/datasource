package com.ucs.connect.date;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucs.business.object.AdminInfo;
import com.ucs.business.object.UserInfo;

public class GetUserResponse {
	
	Gson  gson =  new Gson();
	RequestInfo resinfo = new RequestInfo();
	Properties properties = new Properties();
	Logger loger = Logger.getLogger(GetUserResponse.class.getName());
	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	InputStream inputStream = classLoader.getResourceAsStream("Requesturi.properties");
	
	//验证管理员登录
	public Boolean getadmin(HttpServletRequest req) {	
		AdminInfo admin = new AdminInfo();
		admin.setName(req.getParameter("userAccount"));
		admin.setPassword(req.getParameter("userPw"));
		String info = gson.toJson(admin);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("admininfo", info));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin_Log/login.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		if(result){
			HttpSession session=req.getSession();
			session.setAttribute("adminAccount", req.getParameter("userAccount"));
			session.setAttribute("adminPassWord", req.getParameter("userPw"));
		}
		return result;
	}
	
	//验证用户登录信息
	public Boolean getuser(HttpServletRequest req) {
		UserInfo user = new UserInfo();
		user.setuserAccount(req.getParameter("userAccount"));
		user.setPassword(req.getParameter("userPw"));
		String info = gson.toJson(user);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_info", info));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/user_Log/login.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		if(result){
			HttpSession session=req.getSession();
			session.setAttribute("userAccount", req.getParameter("userAccount"));
			session.setAttribute("userPassWord", req.getParameter("userPw"));
			loger.info(req.getParameter("userAccount")+"-----------"+req.getParameter("userPw"));
		}
		return result;
	}
	
	//获取页面信息
	public Integer getpage(HttpServletRequest req) {
		loger.info("-----------------获取页面信息------------------");
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("userName", req.getParameter("userName")));
		params.add(new BasicNameValuePair("userAccount", req.getParameter("userAccount")));
		loger.info("-----0----request:");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_userspages.do";
		Integer result = Integer.parseInt(resinfo.requestinfo(params,url));
		loger.info("-----1----request uri:"+url);
		loger.info("------2--------request parammter:"+params);
		loger.info("-------3--------------response:"+result.toString());
		return result;
	}
	
	//查看用户
	public List<UserInfo> queryAllUser(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("userName", req.getParameter("userName")));
		params.add(new BasicNameValuePair("userAccount", req.getParameter("userAccount")));
		params.add(new BasicNameValuePair("pageSize", req.getParameter("pageSize")));
		params.add(new BasicNameValuePair("page", req.getParameter("page")));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_findAllUser.do";
		List<UserInfo> result = gson.fromJson(resinfo.requestinfo(params,url), new TypeToken<List<UserInfo>>() {  }.getType());
		loger.info("------------request uri:"+url);
		loger.info("----------------request parammter:"+params);
		if(result==null){
			loger.error("response is null");
		}
		else{
			loger.info("response:"+result.size());
			}
		return result;
	}
	
	//管理员跳转到修改用户
	public UserInfo findUserbyId(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id", req.getParameter("id")));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_findAllUserbyId.do";
		UserInfo result = gson.fromJson(resinfo.requestinfo(params,url),UserInfo.class);
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.getName());
		return result;
	}
		
	//管理员添加用户
	public Boolean adduser(HttpServletRequest req) {
		UserInfo user = new UserInfo();
		user.setuserAccount(req.getParameter("userAccount"));
		user.setPassword(req.getParameter("userPw"));
		user.setphoneNumber(req.getParameter("phoneNumber"));
		user.setName(req.getParameter("userName"));
		String info = gson.toJson(user);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_info", info));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_addUser.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		return result;
	}
	
	//管理员修改用户
	public Boolean updateuser(HttpServletRequest req) {
		UserInfo user = new UserInfo();
		System.out.println("userAccount="+req.getParameter("userAccount"));
		user.setid(Integer.parseInt(req.getParameter("id")));
		user.setuserAccount(req.getParameter("userAccount"));
		user.setPassword(req.getParameter("userPw"));
		user.setphoneNumber(req.getParameter("phoneNumber"));
		user.setName(req.getParameter("userName"));
		String info = gson.toJson(user);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_info", info));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_updateUser.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		return result;
	}
	
	//管理员删除用户
	public Boolean deleteuser(HttpServletRequest req) {
		UserInfo user = new UserInfo();
		user.setid(Integer.parseInt(req.getParameter("id")));
		String info = gson.toJson(user);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("user_info", info));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_deleteUser.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		return result;
	}	
	
	//管理员修改个人信息
	public Boolean modifyAdminInfo(HttpServletRequest req) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("adminName", req.getParameter("adminName")));
		params.add(new BasicNameValuePair("password", req.getParameter("oldPw")));
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = properties.getProperty("uri")+"/admin/do_verifyAdminPW.do";
		Boolean result = Boolean.parseBoolean(resinfo.requestinfo(params,url));
		loger.info("request uri:"+url);
		loger.info("request parammter:"+params);
		loger.info("response:"+result.toString());
		if(result==true){
			System.out.println(req.getParameter("newPw")+","+(req.getParameter("rePassword")));
			if(req.getParameter("newPw").equals(req.getParameter("rePassword"))){
			params.add(new BasicNameValuePair("newPassword", req.getParameter("newPw")));
			url = properties.getProperty("uri")+"/admin/do_modifyAdminInfo.do";
			System.out.println("/admin/do_modifyAdminInfo.do---------------");
			Boolean res = Boolean.parseBoolean(resinfo.requestinfo(params,url));
			loger.info("request uri:"+url);
			loger.info("request parammter:"+params);
			loger.info("response:"+result.toString());
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
}
