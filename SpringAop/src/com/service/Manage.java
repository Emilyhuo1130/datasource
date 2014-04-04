package com.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.inter_face.ManageInterface;
import com.pojo.AdminInfo;
import com.pojo.AllUserInfo;
import com.pojo.UserInfo;

@Controller
@RequestMapping(value="/jsp/*")
public class Manage {
	@Resource
	private ManageInterface managedao;
	
	@RequestMapping(value="userMain")
	public ModelAndView userMain(HttpServletRequest req,HttpServletResponse res){
		UserInfo info=managedao.getuserMain();
		return new ModelAndView("userMain","user",info);
	}
	@RequestMapping(value="adminMain")
	public ModelAndView adminMain(HttpServletRequest req,HttpServletResponse res){
		AdminInfo info=managedao.getadminMain();
		return new ModelAndView("adminMain","admin",info);
	}
	
	
	@RequestMapping(value="getAllUserInfo")
	public ModelAndView getAllUserInfo(HttpServletRequest req,HttpServletResponse res){
		AllUserInfo info=managedao.getAllUserInfo();
		return new ModelAndView("jstl","allUserInfo",info);
	}
	
	
	@RequestMapping(value="getSelect")
	@ResponseBody
	public List<String> getSelect(HttpServletRequest req,HttpServletResponse res){
		List<String> list=managedao.getSelect();
		return list;
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="clearSession")
	@ResponseBody
	public boolean clearSession(HttpServletRequest req,HttpServletResponse res){
		System.out.println("clearSession");
		HttpSession session=req.getSession();
		session.invalidate();//清除所有session
		return true;
	}
	
}
