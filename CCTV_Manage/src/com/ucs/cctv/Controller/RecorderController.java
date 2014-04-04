/**录像机管理*/
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
import com.ucs.cctv.Implement_Dao.RecorderManageImpl;
import com.ucs.cctv.Interface_Dao.LogInfoManage;
import com.ucs.cctv.Interface_Dao.RecorderManage;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Pojo.RecorderInfo;
import com.ucs.cctv.Response.RecorderResponse;
import com.ucs.cctv.utils.ToolUtils;

@Controller
public class RecorderController {
	static Logger log = Logger.getLogger(RecorderController.class);
	HttpSession session;
	@Resource
	LogInfoManage logInfoManage;
	@Resource
	RecorderManage recorderDao;
	public void setRecorderDao(RecorderManage recorderDao) {
		this.recorderDao = recorderDao;
	}
	Gson gson = new Gson();
	
	//查看所有录像机(已测***)
	@RequestMapping(value="jsp/manage/searchRecorders.do")
	@ResponseBody
	public RecorderResponse find_AllRecorders(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/searchRecorders.do");
		
		String recorderName=req.getParameter("recorderName");
		String recorderModel=req.getParameter("recorderModel"); 
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int page=Integer.parseInt(req.getParameter("page"));
		
		/*String recorderName="";
		String recorderModel=""; 
		int pageSize=5;
		int page=1;*/
		RecorderResponse recorderResponse = recorderDao.findAllRecorders(recorderName,recorderModel,pageSize,page);
		
		log.info("查询所有的录像机返回数据="+gson.toJson(recorderResponse));
		return recorderResponse;
		}
		
	//根据ip查询某台录像机(已测***)
	@RequestMapping(value="jsp/manage/searchRecorderById.do")
	@ResponseBody
	public Map<String,Object> find_RecorderById(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/searchRecorderById.do");
		int id=Integer.parseInt(req.getParameter("recorderId"));
		//int id=1;
		RecorderInfo recorder = recorderDao.findRecorderbyId(id);
		log.info("根据id查询摄像机返回数据="+gson.toJson(recorder));
		map.put("recorderInfo", recorder);
		return map;
		}
		
	//更新 添加录像机的信息(已测***)
	@RequestMapping(value="jsp/manage/updateRecorderInfo.do")
	@ResponseBody
	public Map<String,Object> update_RecorderInfo(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/updateRecorderInfo.do");
		RecorderInfo recorderInfo = new RecorderInfo();
		
	   recorderInfo.setRecorderId(Integer.parseInt(req.getParameter("recorderId").isEmpty()?"0":req.getParameter("recorderId")));
		recorderInfo.setRecorderIp(req.getParameter("recorderIp"));
		recorderInfo.setRecorderMac(req.getParameter("recorderMac"));
		recorderInfo.setRecorderModel(req.getParameter("recorderModel"));
		recorderInfo.setRecorderName(req.getParameter("recorderName"));
		recorderInfo.setRecorderType(req.getParameter("recorderType"));
		if(req.getParameter("capacity")!=null&&req.getParameter("capacity").length()>0){
			recorderInfo.setCapacity(Integer.parseInt(req.getParameter("capacity")));
		}
	/*	
		recorderInfo.setRecorderId(0);
		recorderInfo.setRecorderIp("192.168.111.3");
		recorderInfo.setRecorderMac("23:23:4:33:OO");
		recorderInfo.setRecorderModel("rt-8");
		recorderInfo.setRecorderName("re111");
		recorderInfo.setRecorderType("uorng");
		recorderInfo.setCapacity(34);*/
		
		log.info("入参="+gson.toJson(recorderInfo));
		Boolean updateFlag=false;
		try {
			String  id = recorderInfo.getRecorderId().toString();
			log.info("*****id=***"+id);
			log.info("该录像机是否存在="+id);
			if(id.isEmpty()||id==null||"0".equals(id)){
				log.info("可以添加这台录像机");
				 updateFlag = recorderDao.addRecorder(recorderInfo);
				 //记录日志
				 session = req.getSession();
					OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "添加", session.getAttribute("userAccount").toString()+"添加了一台录像机", ToolUtils.formatDate(new Date()));
					logInfoManage.insertInfoToDataBase(operateLog);
			}else{
				log.info("可以更新这台录像机");
				 updateFlag = recorderDao.updateRecorderInfo(recorderInfo);
				 //记录日志
				 session = req.getSession();
					OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "更新", session.getAttribute("userAccount").toString()+"更新了一台录像机", ToolUtils.formatDate(new Date()));
					logInfoManage.insertInfoToDataBase(operateLog);
				
			}
		} catch (Exception e) {
			log.info("更新  添加 录像机信息 error");
		}
		map.put("recorder", updateFlag);
		log.info("添加或更新操作员返回数据="+gson.toJson(map));
		return map;
		
		
		}
	
	
	//删除 录像机信息(已测******)
	@RequestMapping(value="jsp/manage/deleteRecorder.do")
	@ResponseBody
	public Map<String,Object> delete_CameraInfo(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/deleteRecorder.do");	
		String recorderId = req.getParameter("recorderId");
		//String recorderId = "7";
		boolean deleteBackInfo = recorderDao.deleteRecorder(Integer.parseInt(recorderId));
		map.put("deleteRecorderInfo", deleteBackInfo);
		log.info("返回删除是否成功="+map);
		//记录日志
		 session = req.getSession();
			OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "删除", session.getAttribute("userAccount").toString()+"删除了id为"+recorderId+"的录像机", ToolUtils.formatDate(new Date()));
			logInfoManage.insertInfoToDataBase(operateLog);
		return map;
		
		}
	//***********************验证ip是否存在(已测)******************************************
		@RequestMapping(value="jsp/manage/validateRecorderIp.do")
		@ResponseBody
		public boolean validateIp(HttpServletRequest req,HttpServletResponse res){
			res.setHeader("Access-Control-Allow-Origin","*");
			String ip = req.getParameter("recorderIp");
			//String ip = "192.168.1.441";
		
			boolean validateInfo = recorderDao.verifyIP(ip);
			if(validateInfo){
				log.info("*************该ip不存在****************");
			}else{
				log.info("*************该ip已经存在****************");
			}
			return validateInfo;
			
		}
		
		/**验证更新某个录像机时这个ip是否在其他录像机使用***/
		@RequestMapping(value="jsp/manage/validateotherRecorderIp.do")
		@ResponseBody
		public boolean validateotherRecorderIp(HttpServletRequest req,HttpServletResponse res){
			//TODO
			//返回true表示可以更新，返回false表示这个IP已经在除这个录像机以外的录像机中配置过
			res.setHeader("Access-Control-Allow-Origin","*");
			String recorderId=req.getParameter("recorderId");
			String ip = req.getParameter("recorderIp");
			System.out.println(recorderId+"----------"+ip);
			return true;
			
		}
		
	}
