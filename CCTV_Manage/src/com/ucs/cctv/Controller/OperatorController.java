/**操作员管理*/
package com.ucs.cctv.Controller;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.ucs.cctv.Implement_Dao.UtilsImpl;
import com.ucs.cctv.Interface_Dao.LogInfoManage;
import com.ucs.cctv.Interface_Dao.MonitorGroupManage;
import com.ucs.cctv.Interface_Dao.OperatorManage;
import com.ucs.cctv.Pojo.MonitorGroup;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Pojo.OperatorInfo;
import com.ucs.cctv.Response.OperatorResponse;
import com.ucs.cctv.utils.ToolUtils;

@Controller
public class OperatorController{
	
	static Logger log = Logger.getLogger(OperatorController.class);
	HttpSession session;
	@Resource
	LogInfoManage logInfoManage;
	@Resource
	OperatorManage operatorDao;
	public void setOperatorDao(OperatorManage operatorDao) {
		this.operatorDao = operatorDao;
	}
	@Resource
	MonitorGroupManage monitorDao;
	
	public void setMonitorDao(MonitorGroupManage monitorDao) {
		this.monitorDao = monitorDao;
	}
	@Resource
	UtilsImpl utilsDao;

	public void setUtilsDao(UtilsImpl utilsDao) {
		this.utilsDao = utilsDao;
	}
	Gson gson = new Gson();
	
	
	//查询所有的操作员(已测***)
	@RequestMapping(value="jsp/manage/searchUser.do")
	@ResponseBody
	public OperatorResponse  find_AllOperators(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/findAllOperators.do");
		
		String operatorName=req.getParameter("operatorName");
		String operatorAccount=req.getParameter("operatorAccount");
		String level=req.getParameter("level");
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int page=Integer.parseInt(req.getParameter("page"));
		
	/*	String operatorName="";
		String operatorAccount="";
		String level="";
		int pageSize=5;
		int page=1;*/
		
		OperatorResponse operatorResponse = operatorDao.findAllOperators( operatorName, operatorAccount, level, pageSize,  page);
		for(OperatorInfo operatorInfo :	operatorResponse.getOperatorInfo()){
			operatorInfo.setMonitorGroups(null);
		}
		if(operatorResponse.getOperatorInfo().size()>0){
			operatorResponse.getOperatorInfo().get(0).getMonitorGroups();
		}
		log.info("查看所有操作员返回数据="+gson.toJson(operatorResponse));
		
		return operatorResponse;
	}
	
	
	//根据id查询操作员(已测***)	
	@RequestMapping(value="jsp/manage/searchUserById.do")
	@ResponseBody
	public Map<String,Object> find_OperatorById(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/searchUserById.do");
		int id=Integer.parseInt(req.getParameter("operatorId"));
		
		//test
		//int id=4;
		
		OperatorInfo operator = operatorDao.findOperatorbyId(id);
		map.put("findOperatorById", operator);
		log.info("根据id查看操作员返回数据="+operator);
		return map;
		}
		
	
	//返回所有的监控组名称(已测***)
	@RequestMapping(value="jsp/manage/getAllMonitorName.do")
	@ResponseBody
	public List<String>  get_AllMonitorName(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/getAllMonitorName.do");
		List<String> allMonitorName =utilsDao.showMonitorGroup();
		log.info("查看所有操作员返回数据="+gson.toJson(allMonitorName));
		return allMonitorName;
	}
	
	//*****************更新操作员  添加操作员(先判断操作员是否存在  如果存在就更新，不存在就添加)(已测***)********
	@RequestMapping(value="jsp/manage/updateUser.do")
	@ResponseBody
	public Map<String,Object> update_Operator(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		log.info("请求:jsp/manage/updateUser.do");
		//log.info("入参="+req.getParameter("operatorId"));
		
		OperatorInfo operatorInfo = new OperatorInfo();
		
		 String operatorId=req.getParameter("operatorId")==""?"0":req.getParameter("operatorId");
		 String operatorName=req.getParameter("operatorName");
		 String phoneNumber=req.getParameter("phoneNumber");
		 String operatorLevel=req.getParameter("operatorLevel");
		 String remark=req.getParameter("remark");
		 String operatorAccount=req.getParameter("operatorAccount");
		 String operatorPw=req.getParameter("operatorPw");
		 
		String monitorGroupNameStr= req.getParameter("monitorGroups");
		
		/*String operatorId="1";
		String monitorGroupNameStr="夜晚机房监控组#临时机房监控组";*/
		
		Set<MonitorGroup> monitorGroups = new HashSet<MonitorGroup>();
		String[]  monitorGroupNames;
		if("".equals(monitorGroupNameStr)){
			
			 monitorGroupNames = null;
			 
		}else{
			monitorGroupNames = monitorGroupNameStr.split("#");

			//解析监控组组数名["",""]
					log.info(Arrays.toString(monitorGroupNames));
					String name;
					for (int i = 0; i < monitorGroupNames.length; i++) {
						 name = monitorGroupNames[i];
						 MonitorGroup monitorGroup = new MonitorGroup();
						 monitorGroup = operatorDao.findMonitorbyName(name);
						 monitorGroups.add(monitorGroup);
						log.info("*******************************"+gson.toJson(name));
			
					}
			
		}
		
		
				
		operatorInfo.setOperatorId(Integer.parseInt(operatorId));
		operatorInfo.setOperatorName(operatorName);
		operatorInfo.setOperatorPw(operatorPw);
		operatorInfo.setOperatorAccount(operatorAccount);
		operatorInfo.setPhoneNumber(phoneNumber);
		operatorInfo.setOperatorLevel(operatorLevel);
		operatorInfo.setRemark(remark);
		operatorInfo.setMonitorGroups(monitorGroups);
		operatorInfo.setMonitorGroupNames(null);
		
		/*operatorInfo.setOperatorId(Integer.parseInt(operatorId));
		operatorInfo.setOperatorName("user10");
		operatorInfo.setOperatorPw("000");
		operatorInfo.setOperatorAccount("000");
		operatorInfo.setPhoneNumber("000");
		operatorInfo.setOperatorLevel("000");
		operatorInfo.setRemark("000");
		operatorInfo.setMonitorGroupNames(null);*/
		
		 
		log.info("入参="+operatorInfo);
		//首先判断操作员是否存在   根据id查找
		Boolean updateFlag=false;
		try {
			if(operatorId.isEmpty()||operatorId==null||"0".equals(operatorId)){
				log.info("可以添加此操作员");
				 updateFlag = operatorDao.addOperator(operatorInfo);
				 //记录日志
				 session = req.getSession();
					log.info("检验session中是否有内容**************"+session.getAttribute("userAccount").toString());
					OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "添加", session.getAttribute("userAccount").toString()+"添加了新的操作员", ToolUtils.formatDate(new Date()));
					logInfoManage.insertInfoToDataBase(operateLog);
			}else{
				log.info("可以更新此操作员");
				 updateFlag = operatorDao.updateOperatorInfo(operatorInfo);
				 //记录日志
				 session = req.getSession();
					log.info("检验session中是否有内容**************"+session.getAttribute("userAccount").toString());
					OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "更新", session.getAttribute("userAccount").toString()+"更新了id为"+operatorId+"的操作员信息", ToolUtils.formatDate(new Date()));
					logInfoManage.insertInfoToDataBase(operateLog);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("更新  添加 操作员信息 error");
		}
		map.put("updateOperator", updateFlag);
		log.info(map);
		return map;
				
	}
	
	//删除操作员(已测***)
	@RequestMapping(value="jsp/manage/deleteUser.do")
	@ResponseBody
	public Map<String,Object> delete_Operator(HttpServletRequest req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		int id=Integer.parseInt(req.getParameter("operatorId"));
		//int id=6;
		log.info("入参id="+id);
		map.put("deleteInfo", operatorDao.deleteOperator(id));
		//记录日志
		 session = req.getSession();
			log.info("检验session中是否有内容**************"+session.getAttribute("userAccount").toString());
			OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "删除", session.getAttribute("userAccount").toString()+"删除了id为"+id+"的操作员信息", ToolUtils.formatDate(new Date()));
			logInfoManage.insertInfoToDataBase(operateLog);
		return map;
	}
	
	/**添加用户时验证手机号和账户名**/
	@RequestMapping(value="jsp/manage/verifyAccountandPone.do")
	@ResponseBody
	public String verifyAccountandPone(HttpServletRequest req,HttpServletResponse res){
		//TODO
		//返回 1 存在想通账号名   、 返回 2存在相同手机号号  ，返回3 存在相同的账号名和手机号 ，返回4正常 代表开可以添加用户 
		String operatorAccount=req.getParameter("operatorAccount").trim();
		String phoneNumber=req.getParameter("phoneNumber").trim();
		System.out.println(operatorAccount+"---------"+phoneNumber);
		return "4";
	}
	
	
	/**更新用户时验证手机号是否被其他人使用**/
	@RequestMapping(value="jsp/manage/veriftyotherphoneNumber.do")
	@ResponseBody
	public boolean veriftyotherphoneNumber(HttpServletRequest req,HttpServletResponse res){
		//TODO
		//找个除本身id的对象检查其他对象的手机号
		//返回true表示这个手机号可以使用，返回false表示这个手机号已经被其他人使用
		String operatorId=req.getParameter("operatorId").trim();
		String phoneNumber=req.getParameter("phoneNumber");
		System.out.println("------"+operatorId+"---------"+phoneNumber);
		return true;
	}	
		
}
