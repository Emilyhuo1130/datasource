package com;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class TwoLoginTest {
	@RequestMapping(value = "/admin/login")
	public void adminlogin(HttpServletRequest req) {
		Map<String,String> map=new HashMap<String, String>();
		String name=req.getParameter("adminName");
		String pw=req.getParameter("password");
		map.put("adminName", name);
		HttpSession session=req.getSession();
		session.setAttribute("adminName", name);
		session.setAttribute("adminPW", pw);
		

	}
	@RequestMapping(value = "/user/login")
	public void userlogin(HttpServletRequest req) {
		System.out.println("------------------------------------");
		String name=req.getParameter("userName");
		String pw=req.getParameter("password");
		System.out.println(name+pw);
		HttpSession session=req.getSession();
		session.setAttribute("userName", name);
		session.setAttribute("userPW", pw);
		System.out.println("---------------------------------------");

	}
	
	
	@RequestMapping(value = "/user/public/add")
	public void user_public_add(HttpServletRequest req) {

		System.out.println("user_public_add");

	}
	@RequestMapping(value = "/admin/public/add")
	public void admin_public_add(HttpServletRequest req) {

		System.out.println("admin_public_add");

	}
}
