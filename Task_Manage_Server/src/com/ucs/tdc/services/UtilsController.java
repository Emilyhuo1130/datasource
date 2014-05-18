package com.ucs.tdc.services;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ucs.tdc.interFace.UtilsInterface;
import com.ucs.tdc.pojo.UserInfo;

@Controller
public class UtilsController {
	static Logger log = Logger.getLogger(UtilsController.class);
	@Resource
	private UtilsInterface interFace;
	Gson gson=new Gson();
	
	/***显示项目 select下拉菜单**/
	@RequestMapping(value = "public/select_project")
	@ResponseBody
	public List<String> select_project(){
		return interFace.select_project();
	}
	
	/***显示第几周到第几周下拉菜单**/
	@RequestMapping(value = "public/select_weeks")
	@ResponseBody
	public List<Integer> select_weeks(){
		return interFace.select_weeks();
	}
	
	/***显示第几周**/
	@RequestMapping(value = "public/show_thisweek")
	@ResponseBody
	public int show_thisweek(){
		return Integer.parseInt(interFace.show_thisweek());
	}
	
}
