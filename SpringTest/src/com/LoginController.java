package com;

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
	
	
}