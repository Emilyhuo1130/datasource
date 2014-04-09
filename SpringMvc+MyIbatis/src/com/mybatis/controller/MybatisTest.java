package com.mybatis.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mybatis.inter.IUserInfoOperation;
import com.mybatis.mapper.Page_;
import com.mybatis.mapper.UserInfo;

@Controller
public class MybatisTest {
	@Resource
	private IUserInfoOperation face;
	Logger log=Logger.getLogger(MybatisTest.class);
	
	@RequestMapping(value="/getUserById")
	@ResponseBody
	public UserInfo getUserById(){
		return face.selectUserByID(4);
	}
	
	@RequestMapping(value="/updateUserInfo")
	@ResponseBody
	public UserInfo updateUserInfo(){
		UserInfo user = face.selectUserByID(4);
		user.setInfoid(90);
		face.updateUserInfo(user);
		return face.selectUserByID(4);
	}
	//分页
	@RequestMapping(value="/getAllusers")
	@ResponseBody
	public List<UserInfo> getAllusers(){
		int pageCount=1;//第一页
		int pageSize=2;//每页两条
		Page_ page=new Page_();
		page.setOffset((pageCount-1)*pageSize);
		page.setPageSize(pageSize);
		return face.getAllusers(page);
	}
}
