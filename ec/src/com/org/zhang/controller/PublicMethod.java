package com.org.zhang.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.zhang.service.face.PublicMethodFace;
import com.org.zhang.service.face.UserControllerFace;

@Controller
public class PublicMethod {
	Logger log=Logger.getLogger(PublicMethod.class);
	@Resource
	private PublicMethodFace service;
	@RequestMapping(value="/public_CommitBug@PublicMethod")
	@ResponseBody
	public boolean CommitBug(HttpServletRequest req,HttpServletRequest res){
		log.info(req.getParameter("opinion"));
		return service.CommitBug(req.getParameter("opinion"));
	}
}
