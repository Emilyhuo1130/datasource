/**摄像机管理*/
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
import com.ucs.cctv.Interface_Dao.CameraManage;
import com.ucs.cctv.Interface_Dao.LogInfoManage;
import com.ucs.cctv.Pojo.CameraInfo;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Response.CameraResponse;
import com.ucs.cctv.utils.ToolUtils;


@Controller
public class CameraController {
	static Logger log = Logger.getLogger(CameraController.class);
	HttpSession session;
	@Resource
	LogInfoManage logInfoManage;
	@Resource
	CameraManage cameraDao;
	public void setCameraDao(CameraManage cameraDao) {
		this.cameraDao = cameraDao;
	}
	Gson gson = new Gson();
	
	//查看所有摄像机----------询条件查 (已测***)
	@RequestMapping(value="jsp/manage/searchCameras.do")
	@ResponseBody
	public CameraResponse find_AllCameras(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/searchCameras.do");
		String cameraName=req.getParameter("cameraName");
		String cameraModel=req.getParameter("cameraModel"); 
		String section=req.getParameter("section");
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int page=Integer.parseInt(req.getParameter("page"));
	/*	String cameraName="";
		String cameraModel=""; 
		String section="";
		int pageSize=5;
		int page=1;*/
		CameraResponse cameraResponse = cameraDao.findAllCameras(cameraName,cameraModel,section,pageSize,page);
		for(CameraInfo cameraInfo : cameraResponse.getCameraInfo()){
			cameraInfo.setMonitorGroups(null);
		}
		log.info("查询所有的摄像机返回数据="+gson.toJson(cameraResponse.getCameraInfo()));
		return cameraResponse;
		}
	
	//按id查看摄像机(已测***)		
	@RequestMapping(value="jsp/manage/searchCameraById.do")
	@ResponseBody
	public Map<String,Object> find_CameraBId(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/searchCameraById.do");
		int id=Integer.parseInt(req.getParameter("cameraId"));
		//int id=1;
		CameraInfo camera = cameraDao.findCamerabyId(id);
		map.put("CameraInfo", camera);
		return map;
		}
			
	
	//更新  添加 摄像机信息(已测***)
	@RequestMapping(value="jsp/manage/updateCameraInfo.do")
	@ResponseBody
	public Map<String,Object> update_CameraInfo(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/updateCameraInfo.do");	
		//验证此摄像机是否存在   添加的时候检查ip是否被占用
		
		String cameraId = req.getParameter("cameraId")==null?"":req.getParameter("cameraId");
		
		//String cameraId="2";
		
		CameraInfo cameraInfo = new CameraInfo();
		cameraInfo.setCameraId(Integer.parseInt(cameraId.isEmpty()?"0":cameraId));
		cameraInfo.setCameraIp(req.getParameter("cameraIP"));
		cameraInfo.setCameraMac(req.getParameter("cameraMac"));
		cameraInfo.setCameraModel(req.getParameter("cameraModel"));
		cameraInfo.setCameraName(req.getParameter("cameraName"));
		cameraInfo.setSection(req.getParameter("section"));
		cameraInfo.setCameraType(req.getParameter("cameraType"));
		if(req.getParameter("capacity")!=null&&req.getParameter("capacity").length()>0){
			cameraInfo.setCapacity(Integer.parseInt(req.getParameter("capacity")));
		}
		cameraInfo.setMonitorGroups(null);
		System.out.println(req.getParameter("cameraType")+"-----------");
		/*cameraInfo.setCameraId(1);
		cameraInfo.setCameraIp("192.169.0.000");
		cameraInfo.setCameraMac("59:59:59");
		cameraInfo.setCameraModel("00-ss");
		cameraInfo.setCameraName("cameraName000");
		cameraInfo.setCameraType("枪机000");
		cameraInfo.setCapacity(33);
		cameraInfo.setSection("仓库000");*/
		
		Boolean updateFlag=false;
		try {
			String  id =cameraId==null?"":cameraId;
			
			if(id.isEmpty()||id==null||"0".equals(id)){
				log.info("可以添加这台摄像机");
				 updateFlag = cameraDao.addCamera(cameraInfo);
				//记录日志
					 session = req.getSession();
					log.info("检验session中是否有内容**************"+session.getAttribute("userAccount").toString());
					OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "添加", session.getAttribute("userAccount").toString()+"添加了一台摄像机", ToolUtils.formatDate(new Date()));
					logInfoManage.insertInfoToDataBase(operateLog);
			}else{
				log.info("可以更新这台摄像机");
				updateFlag = cameraDao.updateCameraInfo(cameraInfo);
				//记录日志
				 session = req.getSession();
				log.info("检验session中是否有内容**************"+session.getAttribute("userAccount").toString());
				OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "更新", session.getAttribute("userAccount").toString()+"修改了id为"+cameraId+"的摄像机", ToolUtils.formatDate(new Date()));
				logInfoManage.insertInfoToDataBase(operateLog);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("更新  添加 摄像机信息错误");
		}
		map.put("updateCamera", updateFlag);
		
		
		return map;
		}
	
	//删除 摄像机信息(已测***)
	@RequestMapping(value="jsp/manage/deleteCamera.do")
	@ResponseBody
	public Map<String,Object> delete_CameraInfo(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/deleteCamera.do");	
		String cameraId = req.getParameter("cameraId");
		//String cameraId = "2";
				log.info("返回删除是否成功="+map);
				boolean deleteBackInfo = cameraDao.deleteCamera(Integer.parseInt(cameraId));
				map.put("deleteCameraInfo", deleteBackInfo);
		//记录日志
				 session = req.getSession();
				OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "删除", session.getAttribute("userAccount").toString()+"删除了cameraId为"+cameraId+"的摄像机", ToolUtils.formatDate(new Date()));
				logInfoManage.insertInfoToDataBase(operateLog);
		return map;
		
		}
			
	//***********************验证ip是否存在(已测***)******************************************
	@RequestMapping(value="jsp/manage/validateCameraIp.do")
	@ResponseBody
	public boolean validateIp(HttpServletRequest req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		String ip = req.getParameter("cameraIp");
		//String ip = "192.168.1.1220";
		boolean validateInfo = cameraDao.verifyIP(ip);
		//返回true是存在
		if(validateInfo){
			log.info("*************该ip已经存在****************");
		}else{
			log.info("*************该ip不存在****************");
		}
		return validateInfo;
		
	}
	
	/***验证更新某个摄像机的时候这个IP是否在其他摄像机中使用**/
	@RequestMapping(value="jsp/manage/validateotherCameraIp.do")
	@ResponseBody
	public boolean validateotherCameraIp(HttpServletRequest req,HttpServletResponse res){
		//TODO
		//返回true表示这个ip 可以使用，返回false表示这个IP已经在除这个摄像机外的机器配置过
		res.setHeader("Access-Control-Allow-Origin","*");
		String cameraId=req.getParameter("cameraId").trim();
		String ip = req.getParameter("cameraIp").trim();
		System.out.println(cameraId+"-----------"+ip);
		return true;
		
	}
	
	}
