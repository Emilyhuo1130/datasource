package com.ucs.tdc.services;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.ucs.tdc.interFace.Admin;
import com.ucs.tdc.pojo.AdminInfo;
import com.ucs.tdc.pojo.UserInfo;

@Controller
public class AdminController {
	static Logger log = Logger.getLogger(AdminController.class);
		@Resource
		private Admin interFace;
		Gson gson =new Gson();
		
		/***普通用户登录**/
		@RequestMapping(value = "user_Log/login")
		@ResponseBody
		public boolean user_login(HttpServletRequest req,
				HttpServletResponse res) {
			UserInfo info=gson.fromJson(req.getParameter("user_info"), UserInfo.class);
			if(interFace.verifyuser(info)){
				HttpSession session=req.getSession();
				session.setAttribute("userName", info.getUserAccount());
				session.setAttribute("userPassWord", info.getUserPw());
				return true;
			}else{
				return false;
			}
		}
		
		
		/**管理员登录***/
		@RequestMapping(value = "admin_Log/login")
		@ResponseBody
		public boolean admin_login(@RequestParam String admininfo) {
			return interFace.verifyAdmin(gson.fromJson(admininfo, AdminInfo.class));
		}
		
		/**管理员修改密码前验证自己密码**/
		@RequestMapping(value = "admin/do_verifyAdminPW")
		@ResponseBody
		public boolean do_verifyAdminPW(@RequestParam String adminName,String password) {
			return interFace.do_verifyAdminPW(adminName,password);
		}
		
		/**管理员修改自己密码**/
		@RequestMapping(value = "admin/do_modifyAdminInfo")
		@ResponseBody
		public boolean do_modifyAdminInfo(@RequestParam String adminName,String newPassword) {
			AdminInfo info =interFace.do_findAdminInfoByAccount(adminName);
			info.setUserPw(newPassword);
			return interFace.do_modifyAdminInfo(info);
		}
		/***通过账号查询到管理员的信息**/
		@RequestMapping(value = "admin/do_findAdminInfoByAccount")
		@ResponseBody
		public AdminInfo do_findAdminInfoByAccount(@RequestParam String adminName) {
			return interFace.do_findAdminInfoByAccount(adminName);
		}
		
		/**返回查看的总页数***/
		@RequestMapping(value = "admin/do_userspages")
		@ResponseBody
		public int do_userspages(@RequestParam int pageSize,
				 String userName,String userAccount) {
			return interFace.do_userspages(pageSize,userName,userAccount);
		}
		/**管理员查看所有用户***/
		@RequestMapping(value = "admin/do_findAllUser")
		@ResponseBody
		public List<UserInfo> do_findAllUser(@RequestParam String userName,String userAccount,
				int pageSize,int page) {
			return interFace.do_findAllUser(userName,userAccount,pageSize, page);
			
		}
		/***管理员要修改某个人的信息  返回对应id的信息**/
		@RequestMapping(value = "admin/do_findAllUserbyId")
		@ResponseBody
		public UserInfo do_findAllUserbyId(@RequestParam int id) {
			return interFace.do_findAllUserbyId(id);
		}
		
		/**管理员添加用户***/
		@RequestMapping(value = "admin/do_addUser")
		@ResponseBody
		public boolean do_addUser(@RequestParam String user_info) {
			return interFace.do_addUser(gson.fromJson(user_info, UserInfo.class));
		}
		
		/**管理员更新 普通用户***/
		@RequestMapping(value = "admin/do_updateUser")
		@ResponseBody
		public boolean do_updateUser(@RequestParam String user_info) {
			UserInfo info=gson.fromJson(user_info, UserInfo.class);
			UserInfo oldinfo=interFace.do_findAllUserbyId(info.getId());
			info.setUserPw(oldinfo.getUserPw());
			return interFace.do_updateUser(info);
		}
		/**管理员 重置普通用户密码***/
		@RequestMapping(value = "admin/do_restorePassword")
		@ResponseBody
		public boolean do_restorePassword(@RequestParam int id) {
			UserInfo info=interFace.do_findAllUserbyId(id);
			info.setUserPw("123456");
			return  interFace.do_updateUser(info);
		}
		
		/***管理员 删除普通用户**/
		@RequestMapping(value = "admin/do_deleteUser")
		@ResponseBody
		public boolean do_deleteUser(@RequestParam String user_info) {
			return  interFace.do_deleteUser(gson.fromJson(user_info, UserInfo.class));
		}
		
		/***查看用户名的姓名**/
		@RequestMapping(value = "public/do_findUserName")
		@ResponseBody
		public String do_findUserName(@RequestParam String accountName) {
			return interFace.do_findUserName(accountName);
		}
		
}
