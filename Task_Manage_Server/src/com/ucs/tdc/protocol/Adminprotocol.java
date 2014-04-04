package com.ucs.tdc.protocol;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ucs.tdc.pojo.AdminInfo;
import com.ucs.tdc.pojo.UserInfo;

public class Adminprotocol {
	static Logger log = Logger.getLogger(Adminprotocol.class);
	private static String URL="http://localhost:8080";
	/**
	 * @param args
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static void main(String[] args) throws HttpException, IOException {
		
		// TODO Auto-generated method stub
		/***普通用户登录验证**/
		virtify_user();
		
		/**管理员登录**/
		//virtify_admin();
		
		/***返回总页数**/
		//returnpages();
		
		/**管理员查看所有用户***/
		//findAllUser();
		
		/**管理员添加用户***/
		//addsuer();
		
		/**管理员更新 普通用户***/
		//update_user();
		
		/***管理员 删除普通用户**/
		//delete_user();
		
		/****根据用户名查询姓名*/
		//do_findUserName();
		
			
	}
	private static void do_findUserName() throws HttpException, IOException {
		// TODO Auto-generated method stub
		HttpClient client = new HttpClient(); 
		String url = URL+"/Task_Manage/public/do_findUserName.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("accountName", "chenhao");
	
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}
	private static void delete_user() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		UserInfo info=new UserInfo();
		info.setUserName("陈浩");
		info.setUserAccount("chenhao");
		info.setUserPw("123456");
		info.setPhoneNumber("15216758579");
		info.setId(9);
		String str=gson.toJson(info);
		System.out.println(str);
		String url = URL+"/Task_Manage/admin/do_deleteUser.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("user_info", str);
		
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}
	private static void update_user() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		UserInfo info=new UserInfo();
		info.setUserName("张");
		info.setUserAccount("zhangyinhao");
		//info.setUserPw("666666");
		info.setPhoneNumber("77777");
		info.setId(8);
		String str=gson.toJson(info);
		System.out.println(str);
		String url = URL+"/Task_Manage/admin/do_updateUser.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("user_info", str);
		
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}
	private static void addsuer() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		UserInfo info=new UserInfo();
		info.setUserName("张");
		info.setUserAccount("zhangyinhao");
		info.setUserPw("123456");
		info.setPhoneNumber("");
		String str=gson.toJson(info);
		System.out.println(str);
		String url = URL+"/Task_Manage/admin/do_addUser.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("user_info", str);
		
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
		
	}
	private static void findAllUser() throws HttpException, IOException {
		// TODO Auto-generated method stub
		HttpClient client = new HttpClient(); 
		String url = URL+"/Task_Manage/admin/do_userspages.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("pageSize", "3");
		method.addParameter("userName", "");
		method.addParameter("userAccount", "");
		
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
		String url = URL+"/Task_Manage/admin/do_findAllUser.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("pageSize", "3");
		method.addParameter("userName", "");
		method.addParameter("userAccount", "");
		method.addParameter("page", "1");
		
		
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}
	private static void virtify_admin() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		AdminInfo info=new AdminInfo();
		info.setUserName("admin");
		info.setUserPw("123456");
		String str=gson.toJson(info);
		System.out.println(str);
		String url = URL+"/Task_Manage/admin_Log/login.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("admininfo", str);
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}
	private static void virtify_user() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		UserInfo info=new UserInfo();
		info.setUserAccount("zhangyinhao");
		info.setUserPw("123456");
		String str=gson.toJson(info);
		System.out.println(str);
		String url = URL+"/Task_Manage_Server/user_Log/login.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("user_info", str);
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();  
	}
	/***重写编码设置**/
	public static class UTF8PostMethod extends PostMethod{
        public UTF8PostMethod(String url){
            super(url);
        }
        @Override
        public String getRequestCharSet() {
            //return super.getRequestCharSet();
            return "UTF-8";
        }
    }

}
