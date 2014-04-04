package com.ucs.tdc.protocol;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

import com.google.gson.Gson;
import com.ucs.tdc.protocol.Adminprotocol.UTF8PostMethod;

public class Utilsprotocol {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public static void main(String[] args) throws HttpException, IOException {
		// TODO Auto-generated method stub
		/***显示项目 select下拉菜单**/
		show_select();
		
		/***显示第几周到几周下拉菜单**/  
		//showweeks();
		
		/***显示第几周**/           
		//showthisweek();
		
	}

	private static void showthisweek() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://192.168.2.102:8080/Task_Manage/public/show_thisweek.do?";
		PostMethod method = new UTF8PostMethod(url);
		method.setParameter("info", "1111");
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();
	}

	private static void showweeks() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage/public/select_weeks.do?";
		PostMethod method = new UTF8PostMethod(url);
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();
	}

	private static void show_select() throws HttpException, IOException {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		HttpClient client = new HttpClient(); 
		String url = "http://localhost:8080/Task_Manage/public/select_project.do?";
		PostMethod method = new UTF8PostMethod(url);
		client.executeMethod(method);
		System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        method.releaseConnection();
	}

}
