package com.ucs.cctv.Controller;

import java.util.Date;

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
import com.ucs.cctv.Interface_Dao.SystemManage;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Pojo.SystemConfigInfo;
import com.ucs.cctv.utils.ToolUtils;

@Controller
public class SystemPrameterConfiguration {
	static Logger log = Logger.getLogger(DiskController.class);
	HttpSession session;
	@Resource
	LogInfoManage logInfoManage;
	@Resource
	SystemManage systemDao ;
	public void setSystemDao(SystemManage systemDao) {
		this.systemDao = systemDao;
	}

	Gson gson = new Gson();
	
	//查询所有磁盘的容量信息(已测)  改为读配置文件
	@RequestMapping(value="jsp/manage/getSystemInfo.do")
	@ResponseBody
	public SystemConfigInfo getSystemConfigInfo(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/getSystemInfo.do");
		SystemConfigInfo systemConfigInfo = systemDao.getSystemConfigInfo();
		log.info("****读配置文件=******"+gson.toJson(systemConfigInfo));
		return systemConfigInfo;
	}
	
	//修改系统指标参数
	@RequestMapping(value="jsp/manage/updateSystemInfo.do")
	@ResponseBody
	public boolean updateSystemConfigInfo(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/getSystemInfo.do");
		
		Double cpuOccupancy =Double.parseDouble(req.getParameter("cpuOccupancy")) ;
		Double flowRate =Double.parseDouble(req.getParameter("flowRate")) ;
		Double totalDiskIndex =Double.parseDouble(req.getParameter("totalDiskIndex")) ;
		Double perDiskIndex =Double.parseDouble(req.getParameter("perDiskIndex")) ;
		
		/*Double cpuOccupancy =33.1;
		Double flowRate =22.2 ;
		Double totalDiskIndex =8.2;
		Double perDiskIndex =2.2 ;*/
		
		SystemConfigInfo systemConfigInfo  = new SystemConfigInfo();
		systemConfigInfo.setCpuOccupancy(cpuOccupancy);
		systemConfigInfo.setFlowRate(flowRate);
		systemConfigInfo.setPerDiskIndex(perDiskIndex);
		systemConfigInfo.setTotalDiskIndex(totalDiskIndex);
		
		boolean updateSystemInfo = systemDao.updateSystemConfigInfo(systemConfigInfo);
		log.info(gson.toJson(systemConfigInfo));
		//记录日志
		 session = req.getSession();
			OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "更新", session.getAttribute("userAccount").toString()+"更新了系统指标参数", ToolUtils.formatDate(new Date()));
			logInfoManage.insertInfoToDataBase(operateLog);
		return updateSystemInfo;
	}
	
	
	
	
	
}
