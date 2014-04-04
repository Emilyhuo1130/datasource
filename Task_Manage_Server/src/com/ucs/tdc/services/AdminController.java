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
		public void setInterFace(Admin interFace) {
			this.interFace = interFace;
		}
		
		static Boolean flag=false;
		
		/***普通用户登录**/
		@RequestMapping(value = "user_Log/login")
		@ResponseBody
		public boolean user_login(HttpServletRequest req,
				HttpServletResponse res) {
			
			String user_info=req.getParameter("user_info");
			Gson gson =new Gson();
			log.info("普通用户登录:"+user_info);
			UserInfo info=gson.fromJson(user_info, UserInfo.class);
			flag =interFace.verifyuser(info);
			if(flag){
				HttpSession session=req.getSession();
				session.setAttribute("userName", info.getUserAccount());
				session.setAttribute("userPassWord", info.getUserPw());
				
				System.out.println(session.getId());
				log.info("添加session");
			}
			return flag;
			
		}
		
		
		/**管理员登录***/
		@RequestMapping(value = "admin_Log/login")
		@ResponseBody
		public boolean admin_login(@RequestParam String admininfo) {
			log.info("管理员登录: "+admininfo);
			Gson gson =new Gson();
			AdminInfo info=gson.fromJson(admininfo, AdminInfo.class);
			flag=interFace.verifyAdmin(info);
			return flag;
			
		}
		
		/**管理员修改密码前验证自己密码**/
		@RequestMapping(value = "admin/do_verifyAdminPW")
		@ResponseBody
		public boolean do_verifyAdminPW(@RequestParam String adminName,String password) {
			log.info("管理员修改密码前验证自己密码    AdminName:"+adminName+"    password:"+password);
			flag =interFace.do_verifyAdminPW(adminName,password);
			return flag;
			
		}
		
		/**管理员修改自己密码**/
		@RequestMapping(value = "admin/do_modifyAdminInfo")
		@ResponseBody
		public boolean do_modifyAdminInfo(@RequestParam String adminName,String newPassword) {
			log.info("管理员修改自己密码    AdminName:"+adminName+"    password:"+newPassword);
			AdminInfo info =interFace.do_findAdminInfoByAccount(adminName);
			info.setUserPw(newPassword);
			flag =interFace.do_modifyAdminInfo(info);
			return flag;
			
		}
		/***通过账号查询到管理员的信息**/
		@RequestMapping(value = "admin/do_findAdminInfoByAccount")
		@ResponseBody
		public AdminInfo do_findAdminInfoByAccount(@RequestParam String adminName) {
			log.info("通过账号查询到管理员的信息    AdminName:"+adminName);
			AdminInfo info =interFace.do_findAdminInfoByAccount(adminName);
			return info;
			
		}
		
		/**返回查看的总页数***/
		@RequestMapping(value = "admin/do_userspages")
		@ResponseBody
		public int do_userspages(@RequestParam int pageSize,
				 String userName,String userAccount) {
			log.info("用户管理返回页数："+pageSize+userName+userAccount);
			int count=interFace.do_userspages(pageSize,userName,userAccount);
			return count;
			
		}
		/**管理员查看所有用户***/
		@RequestMapping(value = "admin/do_findAllUser")
		@ResponseBody
		public List<UserInfo> do_findAllUser(@RequestParam String userName,String userAccount,
				int pageSize,int page) {
			List<UserInfo> list=interFace.do_findAllUser(userName,userAccount,pageSize, page);
			Gson gson =new Gson();
			log.info("管理员查看所有用户:"+gson.toJson(list));
			return list;
			
		}
		/***管理员要修改某个人的信息  返回对应id的信息**/
		@RequestMapping(value = "admin/do_findAllUserbyId")
		@ResponseBody
		public UserInfo do_findAllUserbyId(@RequestParam int id) {
			log.info("管理员要修改某个人的信息  返回对应id的信息:"+id);
			UserInfo info=interFace.do_findAllUserbyId(id);
			Gson gson =new Gson();
			log.info("根据ID返回某人信息:"+gson.toJson(info));
			return info;
			
		}
		
		/**管理员添加用户***/
		@RequestMapping(value = "admin/do_addUser")
		@ResponseBody
		public boolean do_addUser(@RequestParam String user_info) {
			log.info("管理员添加用户："+user_info);
			Gson gson =new Gson();
			UserInfo info=gson.fromJson(user_info, UserInfo.class);
			flag=interFace.do_addUser(info);
			return flag;
			
		}
		
		/**管理员更新 普通用户***/
		@RequestMapping(value = "admin/do_updateUser")
		@ResponseBody
		public boolean do_updateUser(@RequestParam String user_info) {
			log.info("管理员更新 普通用户:"+user_info);
			Gson gson =new Gson();
			UserInfo info=gson.fromJson(user_info, UserInfo.class);
			UserInfo oldinfo=interFace.do_findAllUserbyId(info.getId());
			info.setUserPw(oldinfo.getUserPw());
			flag=interFace.do_updateUser(info);
			return flag;
		}
		/**管理员 重置普通用户密码***/
		@RequestMapping(value = "admin/do_restorePassword")
		@ResponseBody
		public boolean do_restorePassword(@RequestParam int id) {
			log.info("管理员 重置普通用户密码:"+id);
			UserInfo info=interFace.do_findAllUserbyId(id);
			info.setUserPw("123456");
			flag=interFace.do_updateUser(info);
			return flag;
		}
		
		/***管理员 删除普通用户**/
		@RequestMapping(value = "admin/do_deleteUser")
		@ResponseBody
		public boolean do_deleteUser(@RequestParam String user_info) {
			log.info("管理员 删除普通用户:"+user_info);
			Gson gson =new Gson();
			UserInfo info=gson.fromJson(user_info, UserInfo.class);
			flag=interFace.do_deleteUser(info);
			return flag;
		}
		
		/***查看用户名的姓名**/
		@RequestMapping(value = "public/do_findUserName")
		@ResponseBody
		public String do_findUserName(@RequestParam String accountName) {
			log.info("查看用户名的姓名:"+accountName);
			String userName=interFace.do_findUserName(accountName);
			return userName;
		}
		
}
