/**系统配置*/
package com.ucs.cctv.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.ucs.cctv.Interface_Dao.DiskManage;
import com.ucs.cctv.Interface_Dao.LogInfoManage;
import com.ucs.cctv.Interface_Dao.RecorderManage;
import com.ucs.cctv.Pojo.DiskInfo;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Response.DiskSizeResponse;
import com.ucs.cctv.utils.ToolUtils;

@Controller
public class DiskController {
	static Logger log = Logger.getLogger(DiskController.class);
	HttpSession session;
	@Resource
	LogInfoManage logInfoManage;
	@Resource
	RecorderManage recorderDao;
	public void setRecorderDao(RecorderManage recorderDao) {
		this.recorderDao = recorderDao;
	}
	
	@Resource
	DiskManage diskDao;
	public void setDiskDao(DiskManage diskDao) {
		this.diskDao = diskDao;
	}

	
	Gson gson = new Gson();
	
	//查询所有磁盘的容量信息(已测***)  改为读配置文件***
	@RequestMapping(value="jsp/manage/searchAllDiskInfo.do")
	@ResponseBody
	public DiskSizeResponse find_AllDiskSizeResponse(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/searchAllDiskInfo.do");
		DiskSizeResponse diskSizeResponse = new DiskSizeResponse();
		diskSizeResponse = diskDao.getDiskInfo();
		log.info("查看所有磁盘返回数据="+gson.toJson(diskSizeResponse));
		return diskSizeResponse;
	}
	
	//更新配置信息(已测*** 文件在tomcat路径下)
	@RequestMapping(value="jsp/manage/updateDiskInfo.do")
	@ResponseBody
	public boolean updateDiskInfo(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/getSystemInfo.do");
		Double perDiskCapacityLess =Double.parseDouble(req.getParameter("perDiskCapacityLess")) ;
		//Double perDiskCapacityLess=99.55;
		DiskSizeResponse diskSizeResponse  = new DiskSizeResponse();
		diskSizeResponse.setPerDiskCapacityLess(perDiskCapacityLess);
		boolean updateDiskInfo = diskDao.updateDiskInfo(diskSizeResponse);
		log.info(gson.toJson(updateDiskInfo));
		//记录日志
		session = req.getSession();
		OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "更新", session.getAttribute("userAccount").toString()+"更新了磁盘信息", ToolUtils.formatDate(new Date()));
		logInfoManage.insertInfoToDataBase(operateLog);
		return true;
	
	}
	//磁盘剩余容量低于某个值时自动删除30天前的数据
	@RequestMapping(value="jsp/manage/deleteDataWhenCapacityLessThanthis.do")
	@ResponseBody
	public Boolean deleteDataWhenCapacityLessThanthis(){
		//遍历所有的磁盘容量
		List<DiskInfo> list = new ArrayList<DiskInfo>();
		for (int i = 0; i < list.size(); i++) {
			DiskInfo diskInfo=list.get(i);
			if(diskInfo.getDiskSurplusCapacity()<=0.3){
				//删除30天之前的数据...
			}
			
		}
		
		
		return false;
	}
	
}
