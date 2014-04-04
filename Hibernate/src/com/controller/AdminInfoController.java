package com.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.AdminInfoDAO;
import com.pojo.AdminInfo;

public class AdminInfoController {
	@RequestMapping(value="/getAlladminInfo")
	@ResponseBody
	public List<AdminInfo> getAlladminInfo(@RequestParam String setting){
		AdminInfoDAO dao=new AdminInfoDAO();
		List<AdminInfo> list=dao.findAll(1,3);
		return  list;
		
	}
}
