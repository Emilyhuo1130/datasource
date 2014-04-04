/*登录管理*/
package com.ucs.cctv.Controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.ucs.cctv.Interface_Dao.LogInfoManage;
import com.ucs.cctv.Interface_Dao.LoginManage;
import com.ucs.cctv.Pojo.AdminInfo;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.utils.ToolUtils;

@Controller
public class LoginController{
	static Logger log = Logger.getLogger(LoginController.class);
	@Resource
	LoginManage loginDao;
	public void setLoginDao(LoginManage loginDao) {
		this.loginDao = loginDao;
	}
	@Resource
	LogInfoManage logInfoManage;
	public void setLogInfoManage(LogInfoManage logInfoManage) {
		this.logInfoManage = logInfoManage;
	}
	Gson gson = new Gson();
	HttpSession session;

	//管理员登录验证(已测)
	@RequestMapping(value="jsp/manage/admin_login.do")
	@ResponseBody
	private  Map<String,Object>  admin_Verify(HttpServletRequest req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		Map<String,Object> map = new HashMap<String,Object>();
		
		log.info("请求地址:jsp/manage/admin_login.do");
		
		
		String operatorAccount = req.getParameter("operatorAccount")==null?"---null---":req.getParameter("operatorAccount");
		String operatorPw = req.getParameter("operatorPw");
	
		log.info("***************"+operatorAccount+","+operatorPw);
		
		boolean loginInfo = loginDao.adminLoginVerify(operatorAccount,operatorPw);
		if(loginInfo){
			 session = req.getSession();
			session.setAttribute("userAccount", req.getParameter("operatorAccount"));
			
			//记录日志
			OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "登录", session.getAttribute("userAccount").toString()+"登录了系统", ToolUtils.formatDate(new Date()));
			logInfoManage.insertInfoToDataBase(operateLog);
			
			log.info("session="+session.getAttribute("userName"));
		}
		map.put("adminlogin", loginInfo);
		log.info(map);
		return map;
	}
	
	//操作员登录验证(已测)
	@RequestMapping(value="jsp/manage/operator_login.do")
	@ResponseBody
	private  Map<String,Object>  operator_Verify(HttpServletRequest req){
		Map<String,Object> map = new HashMap<String,Object>();
		log.info("请求地址:jsp/manage/operator_login.do");
		
		String operatorAccount = req.getParameter("operatorAccount");
		String operatorPw = req.getParameter("operatorPw");
		
	/*	String operatorAccount ="chenhao";
		String operatorPw = "123";*/
		
		boolean loginInfo = loginDao.operatorLoginVerify(operatorAccount,operatorPw);
		if(loginInfo){
			
			session.setAttribute("userAccountNormal", req.getParameter("operatorAccount"));
			
			//记录日志
			log.info(session.getAttribute("userAccount").toString());
			log.info( new Date());
			log.info(ToolUtils.formatDate(new Date()));
			
			OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "登录", session.getAttribute("userAccount").toString()+"登录了系统", ToolUtils.formatDate(new Date()));
			logInfoManage.insertInfoToDataBase(operateLog);
			
			log.info("session="+session.getAttribute("userAccount"));
		}
		map.put("operatorlogin",loginInfo);
		log.info(map);
		return map;
	}
	
	//修改操作员信息(已测)
	@RequestMapping(value="jsp/manage/updateOperatorInfo.do")
	@ResponseBody
	private  Map<String,Object>  updateOperatorInfo(HttpServletRequest req){
		Map<String,Object> map = new HashMap<String,Object>();
		log.info("请求地址:jsp/manage/operator_login.do");
		AdminInfo adminInfo = new AdminInfo();
        String operatorAccount=req.getParameter("operatorAccount");
	    String password=req.getParameter("password");
	    
	    adminInfo.setAdminAccount(operatorAccount);
	    adminInfo.setAdminPw(password);
		map.put("updateOperator", loginDao.updateAdminInfo(adminInfo));
		//记录日志
		
		OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "修改", session.getAttribute("userAccount").toString()+"修改了信息", ToolUtils.formatDate(new Date()));
		logInfoManage.insertInfoToDataBase(operateLog);
		log.info(map);
		return map;
	}
	
	//修改当前登录的管理员密码
	@RequestMapping(value="jsp/manage/updateAdminInfo.do")
	@ResponseBody
	private  boolean updateAdminInfo(HttpServletRequest req){
		//TODO 返回true 修改成功 先验证原来密码是否正确  ，正确则修改密码 返回true， 不正确则返回false
		String adminAccount=req.getParameter("adminAccount");
		String adminoldPw=req.getParameter("adminPw");
		String adminNewPw=req.getParameter("adminNewPw");
		System.out.println(adminAccount+"----"+adminoldPw+"----"+adminNewPw);
		return true;
		
	}
	
	
	
}
