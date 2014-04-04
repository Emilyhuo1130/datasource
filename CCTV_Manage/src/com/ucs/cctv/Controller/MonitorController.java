/**监控组管理*/
package com.ucs.cctv.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import com.ucs.cctv.Interface_Dao.CameraManage;
import com.ucs.cctv.Interface_Dao.LogInfoManage;
import com.ucs.cctv.Interface_Dao.MonitorGroupManage;
import com.ucs.cctv.Interface_Dao.Utils;
import com.ucs.cctv.Pojo.CameraInfo;
import com.ucs.cctv.Pojo.Group;
import com.ucs.cctv.Pojo.MonitorGroup;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Response.GroupResponse;
import com.ucs.cctv.Response.MonitorGroupResponse;
import com.ucs.cctv.Trees.SectionCameraTrees;
import com.ucs.cctv.utils.ToolUtils;

@Controller
public class MonitorController{
	static Logger log = Logger.getLogger(MonitorController.class);
	HttpSession session;
	@Resource
	LogInfoManage logInfoManage;
	@Resource
	MonitorGroupManage monitorDao;
	public void setMonitorDao(MonitorGroupManage monitorDao) {
		this.monitorDao = monitorDao;
	}
	@Resource
	CameraManage cameraDao;
	public void setCameraDao(CameraManage cameraDao) {
		this.cameraDao = cameraDao;
	}
	@Resource
	Utils utilsDao;
	public void setUtilsDao(Utils utilsDao) {
		this.utilsDao = utilsDao;
	}


	Gson gson = new Gson();
	
	//查看所有监控组(已测***)
	@RequestMapping(value="jsp/manage/searchAllMonitorGroup.do")
	@ResponseBody
	public GroupResponse find_AllMonitorGroup(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/searchAllMonitorGroup.do");
		String monitorName=req.getParameter("monitorName");
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int page=Integer.parseInt(req.getParameter("page"));
		
		/*String monitorName="";
		int pageSize=5;
		int page=1;*/
		
		GroupResponse groupResponse = monitorDao.findAllMonitorGroup(monitorName,pageSize,page);
		for(MonitorGroup monitorGroup : groupResponse.getMonitorGroup()){
			monitorGroup.setOperators(null);
			for(CameraInfo cameraInfo :monitorGroup.getCameraMerbers() ){
				cameraInfo.setMonitorGroups(null);
			}
		}
		log.info("查看所有监控组返回数据="+gson.toJson(groupResponse));
		
		return groupResponse;
	}
	
	
	//更新 添加监控组  用流取数据"{'groupId'：1,'groupName':'groupOne','remark':'nothing',"ids":[1,2,3,4]}"
	@RequestMapping(value="jsp/manage/updateMonitorGroup.do")
	@ResponseBody
	public Map<String,Object > updateMonitorGroup(HttpServletRequest req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		Map<String,Object> map = new HashMap<String,Object>();
		log.info("请求:jsp/manage/updateMonitorGroup.do");
		MonitorGroup monitorGroup = new MonitorGroup();
		String groupStr=null;
		try {
			// get inputStream
		
			StringBuffer inputString = new StringBuffer();	
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					req.getInputStream(), "UTF-8"));
			String s = reader.readLine();
			while (s != null) {
				inputString.append(s);
				s = reader.readLine();
			}
			groupStr=inputString.toString();
			log.info("得到的流文件: " + groupStr);
			
		}catch(Exception e){
			e.printStackTrace();
			log.info(e+"获取流文件失败");
		}
		Gson gson=new Gson();
		Group group=gson.fromJson(groupStr, Group.class) ;
		
		int[] cameras = new int[ group.getIds().size()];
		for (int i = 0; i < group.getIds().size(); i++) {
			cameras[i]=group.getIds().get(i);
		}
		//解析摄像机组数据[1,2,3,4]
				log.info(Arrays.toString(cameras));
				Set<CameraInfo> cameraInfos = new HashSet<CameraInfo>();
				int id;
				for (int i = 0; i < cameras.length; i++) {
					 id = cameras[i];
					 CameraInfo cameraInfo = new CameraInfo();
						 cameraInfo = cameraDao.findCamerabyId(id);
					cameraInfos.add(cameraInfo);
					log.info("*******************************"+gson.toJson(cameraInfo));
				}
		//将摄像机组set进去
		monitorGroup.setCameraMerbers(cameraInfos);
		monitorGroup.setGroupName(group.getGroupName());
		monitorGroup.setOperators(null);
		monitorGroup.setRemark(group.getRemark());
		if((group.getGroupId()!=null)&&(group.getGroupId().length()!=0)){
			monitorGroup.setGroupId(Integer.parseInt(group.getGroupId()));
		}
		log.info("monitor="+monitorGroup);
		//验证此监控组是否存在
		boolean updateBackInfo=false;
		try {
			String groupId =monitorGroup.getGroupId()==null?"":monitorGroup.getGroupId().toString();
			if (groupId.isEmpty()||groupId==null||groupId=="0") {
				log.info("添加监控组入参="+monitorGroup.getGroupName()+","+monitorGroup.getRemark());
				updateBackInfo = monitorDao.addMonitorGroup(monitorGroup);//添加
				log.info("添加监控组"+updateBackInfo);
				//记录日志
				 session = req.getSession();
					log.info("检验session中是否有内容**************"+session.getAttribute("userAccount").toString());
					OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "添加", session.getAttribute("userAccount").toString()+"修改了id为"+groupId+"的监控组", ToolUtils.formatDate(new Date()));
					logInfoManage.insertInfoToDataBase(operateLog);
			
			}else{
				updateBackInfo = monitorDao.updateMonitorGroup(monitorGroup);//更新
				log.info("更新监控组"+updateBackInfo);
				//记录日志
				 session = req.getSession();
					log.info("检验session中是否有内容**************"+session.getAttribute("userAccount").toString());
					OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "更新", session.getAttribute("userAccount").toString()+"修改了id为"+groupId+"的监控组", ToolUtils.formatDate(new Date()));
					logInfoManage.insertInfoToDataBase(operateLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		log.info("更新 添加监控组失败");
		}
		map.put("updateMonitorGroup", updateBackInfo);
			
				return map;
	}

	
	//根据id查询监控组（已测*******）
	@RequestMapping(value="jsp/manage/searchMonitorGroupById.do")
	@ResponseBody
	public Map<String,Object> find_MonitorGroupById(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/manage/searchMonitorGroupById.do");
		String id =  req.getParameter("groupId")==null?"":req.getParameter("groupId");
		//String id="1";
		MonitorGroup monitorGroup = monitorDao.findMonitorbyId(Integer.parseInt(id));
		MonitorGroupResponse monitorGroupResponse = new MonitorGroupResponse();
		monitorGroupResponse.setGroupId(id);
		monitorGroupResponse.setGroupName(monitorGroup.getGroupName());
		monitorGroupResponse.setRemark(monitorGroup.getRemark());
		monitorGroupResponse.setGroupId(monitorGroup.getGroupId().toString());
		
		log.info("id="+id);
		SectionCameraTrees cameraTrees = utilsDao.showSectionCameraTreesById(Integer.parseInt(id));
		List<SectionCameraTrees> sectionCameraTrees = new ArrayList<SectionCameraTrees>();
		sectionCameraTrees.add(cameraTrees);
		monitorGroupResponse.setSectionCameraTrees(sectionCameraTrees);
		map.put("findMonitorById", monitorGroupResponse);
		log.info("根据id查监控组返回数据="+gson.toJson(monitorGroup.getGroupName()));
		return map; 
	}
	
	//删除监控组（已测***）
		@RequestMapping(value="jsp/manage/deleteMonitorGroupById.do")
		@ResponseBody
		public Map<String,Object> delete_MonitorGroupById(HttpServletRequest  req,HttpServletResponse res){
			Map<String,Object> map = new HashMap<String,Object>();
			res.setHeader("Access-Control-Allow-Origin","*");
			log.info("请求:jsp/manage/searchMonitorGroupById.do");
			String id = req.getParameter("groupId")==null?"10000":req.getParameter("groupId");
			//String id="4";
			Boolean deleteInfo = monitorDao.deleteMonitorGroup(Integer.parseInt(id));
			map.put("deleteMonitorById", deleteInfo);
			log.info("返回是否删除监控组信息="+gson.toJson(deleteInfo));
			 session = req.getSession();
				log.info("检验session中是否有内容**************"+session.getAttribute("userAccount").toString());
				OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "删除", session.getAttribute("userAccount").toString()+"删除了id为"+id+"的监控组", ToolUtils.formatDate(new Date()));
				logInfoManage.insertInfoToDataBase(operateLog);
			return map; 
		}
		
		//添加监控组是初始化返回一颗空的区位摄像机树(已测***)
		@RequestMapping(value="jsp/manage/getInitSectionCameraTree.do")
		@ResponseBody
		public SectionCameraTrees getInitSectionCameraTree(HttpServletRequest req,HttpServletResponse res){
			SectionCameraTrees sectionCameraTrees = utilsDao.showSectionCameraTrees();
			return sectionCameraTrees;
			
		}
		
		
		/**添加一个监控组时验证这个监控组名字是否已经存在***/
		@RequestMapping(value="jsp/manage/validateMonitorName.do")
		@ResponseBody
		public boolean validateMonitorName(HttpServletRequest req,HttpServletResponse res){
			//TODO
			//返回true表示这个监控组名字不存在是可以添加的，返回false表示这个监控组名字已经存在
			res.setHeader("Access-Control-Allow-Origin","*");
			String groupName=req.getParameter("groupName");
			System.out.println("----------------------"+groupName+"----------------");
			return true;
			
		}
		
		/**更新一个监控组时验证这个监控组名字是否已经在其他监控组中存在***/
		@RequestMapping(value="jsp/manage/validateotherMonitorName.do")
		@ResponseBody
		public boolean validateotherMonitorName(HttpServletRequest req,HttpServletResponse res){
			//TODO
			//返回true表示这个监控组名字在其他监控组中不存在是可以添加的，返回false表示这个监控组名字已经存在于其他监控组中。
			res.setHeader("Access-Control-Allow-Origin","*");
			String groupName=req.getParameter("groupName");
			String groupId=req.getParameter("groupId");
			System.out.println(groupName+"----------------"+groupId);
			return true;
			
		}
		


	
}
