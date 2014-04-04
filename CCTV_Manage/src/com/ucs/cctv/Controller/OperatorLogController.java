/**操作日志管理*/
package com.ucs.cctv.Controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.ucs.cctv.Interface_Dao.LogInfoManage;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Response.LogResponse;


@Controller
public class OperatorLogController {
	@Resource
	LogInfoManage logInfoDao;
	public void setLogInfoDao(LogInfoManage logInfoDao) {
		this.logInfoDao = logInfoDao;
	}


	static Logger log = Logger.getLogger(OperatorLogController.class);
	Gson gson = new Gson();
	
	
	//查询日志记录  (已测)   
	@RequestMapping(value="jsp/manage/searchOperatorLogInfo.do")
	@ResponseBody
	public LogResponse  getAllOperatorLogInfo(HttpServletRequest req , HttpServletResponse res) throws UnsupportedEncodingException {
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/searchOperatorLogInfo.do");
		
		String operatorName = req.getParameter("operatorName");
		String operatorAccount = req.getParameter("operatorAccount");
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		String operateType = req.getParameter("operateType");
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		int page = Integer.parseInt(req.getParameter("page"));
		
		
		log.info("-----入参="+operatorName+","+operatorAccount+","+startTime+","+endTime+","+operateType+","+pageSize+","+page+"-------------");
		
		LogResponse  logResponse= logInfoDao.findAllLogInfo(operatorName, operatorAccount, startTime, endTime, operateType, pageSize, page);
		Iterator<OperateLog> iterator = logResponse.getLogInfo().iterator();
		while (iterator.hasNext()) {
			OperateLog operateLog = (OperateLog)iterator.next();
			operateLog.setOperatorFormatDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(operateLog.getOperateTime()));
			
		}
		log.info("查看所有日志信息返回数据="+gson.toJson(logResponse.getLogInfo()));
		log.info(gson.toJson(logResponse.getPages()));
		return logResponse;
		
		
	}
}
