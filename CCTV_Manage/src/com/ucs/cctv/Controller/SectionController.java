/**区位管理*/
package com.ucs.cctv.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.ucs.cctv.Implement_Dao.UtilsImpl;
import com.ucs.cctv.Interface_Dao.LogInfoManage;
import com.ucs.cctv.Interface_Dao.SectionManage;
import com.ucs.cctv.Pojo.OperateLog;
import com.ucs.cctv.Pojo.SectionInfo;
import com.ucs.cctv.Response.SectionResponse;
import com.ucs.cctv.utils.ToolUtils;

@Controller
public class SectionController {
	static Logger log = Logger.getLogger(SectionController.class);
	HttpSession session;
	@Resource
	LogInfoManage logInfoManage;
	@Resource
	SectionManage sectionDao;
	public void setSectionDao(SectionManage sectionDao) {
		this.sectionDao = sectionDao;
	}
	UtilsImpl utilsImpl = new UtilsImpl();
	Gson gson = new Gson();
	
	//查找所有的区位信息记录（已测****）
	@RequestMapping(value="jsp/manage/searchSectionInfo.do")
	@ResponseBody
	public SectionResponse find_AllSections(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/searchSectionInfo.do");
		
		
		String sectionName=req.getParameter("sectionName");
		int pageSize=Integer.parseInt(req.getParameter("pageSize"));
		int page=Integer.parseInt(req.getParameter("page"));
		
	/*	String sectionName=""; 
		int pageSize=5;
		int page=1;*/
		
		SectionResponse sectionResponse = sectionDao.findAllSections(sectionName,pageSize,page);
		log.info("查看所有区位返回数据="+gson.toJson(sectionResponse.getSectionInfo()));
		return sectionResponse;
	}
	
	//初始化显示所有区位信息(已测***)
	@RequestMapping(value="jsp/manage/initAllSectionInfo.do")
	@ResponseBody
	public List<String> show_AllSections(HttpServletRequest  req,HttpServletResponse res){
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/initAllSectionInfo.do");
		List<String> sectionNames =  sectionDao.findAllSectionName();
		log.info("初始化所有区位返回数据="+gson.toJson(sectionNames));
		return sectionNames;
	}
	
	//根据id查找某个区位记录(已测**)
	@RequestMapping(value="jsp/manage/searchSectionInfoById.do")
	@ResponseBody
	public Map<String,Object> find_SesctionById(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/searchSectionInfoById.do");
		String id = req.getParameter("sectionId")==null?"":req.getParameter("sectionId");
		//String id = "1";
		log.info("******************入参id="+id);
		SectionInfo sectionInfo = sectionDao.findSectionById(Integer.parseInt(id==""?"0":id));
		log.info("根据id查找某个区位记录="+gson.toJson(sectionInfo));
		map.put("sectionInfo", sectionInfo);
		return map;
	}
	
	//删除一条区位记录(已测****)
	@RequestMapping(value="jsp/manage/deleteSectionInfoById.do")
	@ResponseBody
	public Map<String,Object> delete_SesctionById(HttpServletRequest  req,HttpServletResponse res){
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/deleteSectionInfoById.do");
		//先验证该区位上有没有绑定摄像机，如果有的话就提示不能删除
		String id=req.getParameter("sectionId")==""?"0":req.getParameter("sectionId");
		//String id="4";
		boolean deleteInfo = sectionDao.deleteSection(Integer.parseInt(id));
		log.info("删除某条区位记录返回="+gson.toJson(deleteInfo));
		//记录日志
		 session = req.getSession();
			OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "删除", session.getAttribute("userAccount").toString()+"删除了id为"+id+"的摄像机", ToolUtils.formatDate(new Date()));
			logInfoManage.insertInfoToDataBase(operateLog);
		map.put("deleteInfo", deleteInfo);
		return map;
	}
	
	//修改  添加一条区位记录(已测 ***)
	@RequestMapping(value="jsp/manage/updateSectionInfo.do")
	@ResponseBody
	public Map<String,Object> update_Sesction(HttpServletRequest  req,HttpServletResponse res){
		
		Map<String,Object> map = new HashMap<String,Object>();
		res.setHeader("Access-Control-Allow-Origin","*");
		log.info("请求:jsp/updateSectionInfo.do");
		//String id = req.getParameter("id");
		
		SectionInfo sectionInfo = new SectionInfo();
		String id = req.getParameter("sectionId")==""?"0":req.getParameter("sectionId");
		sectionInfo.setSectionId(Integer.parseInt(id));
		sectionInfo.setSectionName(req.getParameter("sectionName"));
		sectionInfo.setRemark(req.getParameter("remark"));
		sectionInfo.setCameraMember(null);//迭代查询摄像机再set进去
		
	/*	String id = "0";
		sectionInfo.setSectionId(Integer.parseInt(id));
		sectionInfo.setSectionName("uuu");
		sectionInfo.setRemark("uuu");
		sectionInfo.setCameraMember(null);//迭代查询摄像机再set进去
*/		
		//验证此记录是否存在
		SectionInfo section = sectionDao.findSectionById(sectionInfo.getSectionId());
				log.info("**********sectionInfo="+sectionInfo);
				boolean updateBackInfo = false;
		if(section==null){
			updateBackInfo= sectionDao.addSection(sectionInfo);
			log.info("添加某条区位记录返回="+gson.toJson(updateBackInfo));
			//记录日志
			 session = req.getSession();
				OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "添加", session.getAttribute("userAccount").toString()+"添加了一条区位信息", ToolUtils.formatDate(new Date()));
				logInfoManage.insertInfoToDataBase(operateLog);
		}else{
			updateBackInfo = sectionDao.updateSection(sectionInfo);
			log.info("修改某条区位记录返回="+gson.toJson(updateBackInfo));
			 session = req.getSession();
				OperateLog operateLog = ToolUtils.wrapSetMathod(session.getAttribute("userAccount").toString(), new Date(), "更新", session.getAttribute("userAccount").toString()+"更新了id为"+id+"的区位信息", ToolUtils.formatDate(new Date()));
				logInfoManage.insertInfoToDataBase(operateLog);
		}
		map.put("updateSectionInfo", updateBackInfo);
		return map;
		
	}
	
	/**添加一个区位时验证这个区位名字是否已经存在***/
	@RequestMapping(value="jsp/manage/validateSectionName.do")
	@ResponseBody
	public boolean validateSectionName(HttpServletRequest req,HttpServletResponse res){
		//TODO
		//返回true表示这个区位名字不存在是可以添加的，返回false表示这个区位名字已经存在
		res.setHeader("Access-Control-Allow-Origin","*");
		String sectionName=req.getParameter("sectionName");
		System.out.println(sectionName);
		return true;
		
	}
	
	/**更新一个区位时验证这个区位名字是否已经在其他区位中存在***/
	@RequestMapping(value="jsp/manage/validateotherSectionName.do")
	@ResponseBody
	public boolean validateotherSectionName(HttpServletRequest req,HttpServletResponse res){
		//TODO
		//返回true表示这个区位名字在其他区位中不存在是可以添加的，返回false表示这个区位名字已经存在于其他区位中。
		res.setHeader("Access-Control-Allow-Origin","*");
		String sectionName=req.getParameter("sectionName");
		String sectionId=req.getParameter("sectionId");
		System.out.println(sectionName+"-----------"+sectionId);
		return true;
		
	}
	
}
