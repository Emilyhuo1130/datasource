package com;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bo.form.UserForm;


@Controller
public class LoginController {
	
	//get方法 Http请求
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest req) {

		System.out.println("testget");
		System.out.println(req.getParameter("name"));
		
		return new ModelAndView("index", "name", "gao");

	}

	//pos 请求 带 REQUEST 和RESPONSE
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView  testM(HttpServletRequest req,
			HttpServletResponse res) {
		
		System.out.println("testpost");
	
	UserForm uf =  new UserForm();
	uf.setName(req.getParameter("name"));
	uf.setPassword(req.getParameter("password"));
		return new ModelAndView("index-tojump", "form", uf);
		
		
	}
	
	
	//带参数的 REQUEST和RESPONSE
	@RequestMapping(value = "/login1")
	public ModelAndView  login3(@RequestParam String names,HttpServletRequest req,
			HttpServletResponse res) {
		
		System.out.println("testpost");
		System.out.println(req.getParameter("names"));
		System.out.println(req.getParameter("name"));
		return new ModelAndView("index", "name", req.getParameter("name"));
		
		
	}
	
	//get post 传递对象到下一页
	@RequestMapping(value = "/logins")
	public ModelAndView login3(HttpServletRequest request) {

		System.out.println("testget");
		System.out.println(request.getParameter("name"));
		return new ModelAndView("index", "name", "gao");

	}
	
	// 带参 返回JSON对象
	@RequestMapping(value="/json")
	@ResponseBody
	public PointList validataUser(@RequestParam String userName) throws UnsupportedEncodingException{
		PointList infoList=new PointList();
		/*Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("code", userName);*/
		System.out.println(userName);
		String str=URLDecoder.decode(userName,"utf-8");
		System.out.println(str);
		List<PointInfo> list=new ArrayList<PointInfo>();
		PointInfo info=new PointInfo();
		info.setUsername(userName);
		info.setAge(33);
		list.add(info);
		infoList.setListInfo(list);
		return infoList;
	}
	
	//带参 返回JSON对象
	@RequestMapping(value="/json1")
	@ResponseBody
	public Map<String,Object> validataUser1(){
		Map<String,Object> map = new HashMap<String,Object>();
		UserForm uf =  new UserForm();
		
		uf.setName("gao");
		uf.setPassword("11111");
		map.put("user", uf);
		return map;
	}
	
	
	@RequestMapping(value="/testform")
	@ResponseBody
	public boolean testform(HttpServletRequest req ,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		String username = req.getParameter("username");
		String passwd = req.getParameter("userpassword");
		if(username!=null&&passwd!=null){
			System.out.println(username+"    "+passwd);
		}else{
			System.out.println("----------------");
		}
		return true;
	}
	
	
	
	/***
	 * ip mac测试
	 * **/

	@RequestMapping(value="/testmac")
	@ResponseBody
	public String testmac(HttpServletRequest request ,HttpServletResponse res){
		 String ip = request.getHeader("x-forwarded-for");   
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	            ip = request.getHeader("Proxy-Client-IP");   
	        }   
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	            ip = request.getHeader("WL-Proxy-Client-IP");   
	        }   
	        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
	            ip = request.getRemoteAddr();   
	        }   
	        return ip; 
	}
	
	/**
	 * 下载测试
	 * @throws IOException 
	 * 
	 * **/
	@RequestMapping(value="/download")
	@ResponseBody
	//window.open("../download.do");
	public void download(HttpServletRequest request ,HttpServletResponse response) throws IOException{
			request.setCharacterEncoding("UTF-8");  
	        BufferedInputStream bis = null;  
	        BufferedOutputStream bos = null;  
	  
	        String downLoadPath = "D:/WOTBox/config.json";  
	  
	        long fileLength = new File(downLoadPath).length();  
	        response.setContentType("text/html;charset=UTF-8");  
	        response.setContentType("application/octet-stream; charset=UTF-8");
	        response.setHeader("Content-disposition", "attachment; filename="   + new String("config.json".getBytes("utf-8"), "ISO8859-1"));  
	        response.setHeader("Content-Length", String.valueOf(fileLength));  
	  
	        bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
	        bos = new BufferedOutputStream(response.getOutputStream());  
	        byte[] buff = new byte[2048];  
	        int bytesRead;  
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	            bos.write(buff, 0, bytesRead);  
	        }  
	        bis.close();  
	        bos.close();  
	}
	
	
	
	
}