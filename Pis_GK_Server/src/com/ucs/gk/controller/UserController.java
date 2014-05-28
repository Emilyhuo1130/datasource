package com.ucs.gk.controller;




import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucs.gk.bo.*;
import com.ucs.gk.bo.client.Config_client;
import com.ucs.gk.bo.client.Pic_Linkage;
import com.ucs.gk.bo.client.Pic_Multi;
import com.ucs.gk.bo.client.Pic_Single;
import com.ucs.gk.bo.effect.Effects;
import com.ucs.gk.bo.effect.OneClientInfo;
import com.ucs.gk.bo.effect.OneEffectImagesAddress;
import com.ucs.gk.bo.effect.Pic_info;
import com.ucs.gk.daoImpl.UserControllerDaoImpl;
import com.ucs.gk.interFace.UserControllerInterFace;
import com.ucs.gk.request.Config_client_Req;
import com.ucs.gk.response.ResConfig_Pis;
import com.ucs.gk.response.ResInfo;
import com.ucs.gk.response.Res_Pis;

@Controller
public class UserController {
	static Logger log = Logger.getLogger(UserController.class);
	@Resource
	private UserControllerInterFace usercontrollerInterface;
	public void setUsercontrollerInterface(
			UserControllerInterFace usercontrollerInterface) {
		this.usercontrollerInterface = usercontrollerInterface;
	}


	/**用户登录
	 * @return ***/
	@RequestMapping(value = "/jsp/login")
	public ModelAndView login(HttpServletRequest req,
			HttpServletResponse res) {
		boolean b=usercontrollerInterface.login(req,res);
		
		log.warn("获取tomcat的根路径："+System.getProperty("catalina.base"));
		String str="";
		if(b){
			return new ModelAndView("index");
		}else{
			str="用户名或密码错误！";
			return new ModelAndView("login","errorInfo",str);
		}
	}
	
	
	/**显示设备信息**/
	@RequestMapping(value = "/jsp/getinfoList")
	public ModelAndView  getinfo(HttpServletRequest req,
			HttpServletResponse res) {
		List<ResConfig_Pis> config_Pis =usercontrollerInterface.getinfoListNumTOName();
		Res_Pis resinfo=new Res_Pis();
		resinfo.setConfigList(config_Pis);
		return new ModelAndView("config_info", "config_Pis", resinfo);
		//return resinfo;
		
	}
	
	/**显示服务器配置信息****/
	@RequestMapping(value = "/jsp/getserverinfo")
	public ModelAndView  getserverconfig(HttpServletRequest req,
			HttpServletResponse res) {
		
		Config_Pis config_Pis =usercontrollerInterface.getinfoList();
		ResServerSettingInfo info=UserControllerDaoImpl.getServerSettingInfo(config_Pis);
		return new ModelAndView("config", "config_Pis", info);
	}
	
	
	
	/**填写或修改服务器信息**/
	@RequestMapping(value = "/jsp/setServerInfo")
	public void   setServerinfo(HttpServletRequest req,
			HttpServletResponse res) {
		//配置服务器端
		usercontrollerInterface.setServerinfo(req,res);
	}
	
	
	/***修改某个ip的配置信息 找出这条单独的记录 填写上去等待修改**/
	@RequestMapping(value = "/jsp/modification")
	@ResponseBody
	public OneClientInfo  modification(HttpServletRequest req,
			HttpServletResponse res) {
		OneClientInfo info=new OneClientInfo();
		String local_ip=req.getParameter("Local_ip").trim();
		Config_client config_client =usercontrollerInterface.modification(local_ip);
		Effects effects=usercontrollerInterface.geteffects();
		info.setClient(config_client);
		info.setEffects(effects);
		return info;
		
	}
	
	/***单击客户端配置时**/
	@RequestMapping(value = "/jsp/toAdd")
	public ModelAndView  toAdd(HttpServletRequest req,
			HttpServletResponse res) {
		OneClientInfo info=new OneClientInfo();
		Config_client config_client =new Config_client();
		config_client.setPic_m(null);
		config_client.setPic_s(null);
		Effects effects=usercontrollerInterface.geteffects();
		info.setClient(config_client);
		info.setEffects(effects);
		Gson gson=new Gson();
		log.info(gson.toJson(info));
		return new ModelAndView("c_config", "oneInfo", info);
		
	}
	
	/**读取对应的区位的ip**/
	@RequestMapping(value = "/jsp/getIPByClientId")
	@ResponseBody
	public String  getIPByClientId(HttpServletRequest req,
			HttpServletResponse res) {
		String ip =usercontrollerInterface.getIPByClientId(req.getParameter("cliendId"));
		return ip;
	}
	
	/**修改或增加单条配置信息**/
	@RequestMapping(value = "/jsp/AddorEditOneInfo")
	@ResponseBody
	public boolean  saveOneInfo(HttpServletRequest req,
			HttpServletResponse res) {
		res.setHeader("Access-Control-Allow-Origin","*");
		String  one_info=null;
		try {
			StringBuffer inputString = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					req.getInputStream(), "UTF-8"));
			String s = reader.readLine();
			while (s != null) {
				inputString.append(s);
				s = reader.readLine();
			}
			one_info=inputString.toString();
			log.info("得到的配置文件: " + one_info);
			
		}catch(Exception e){
			e.printStackTrace();
			log.info(e+"从客户端获取信息失败");
		}
		Gson gson=new Gson();
		Config_client_Req pis=gson.fromJson(one_info, Config_client_Req.class);
		//TODO 可能需要给单幅和多幅的根据图片文件夹的名字进行排序。
		//单幅排序
		for(Pic_Single pic_s:pis.getPic_s()){
			if(pic_s.getResFolder().equals("国客心空介绍的图片路径")){
				pic_s.setSequence_number(0);
			}else if(pic_s.getResFolder().equals("十二星座的图片路径")){
				pic_s.setSequence_number(1);
			}else if(pic_s.getResFolder().equals("公益广告的图片路径")){
				if(pic_s.getSequence_number()==0){
					pic_s.setSequence_number(2);
				}
			}
		}
		//多幅排序
		for(Pic_Multi pic_m:pis.getPic_m()){
			if(pic_m.getResFolder().equals("最后的的图片路径")){
				if(pic_m.getSequence_number()==0){
					pic_m.setSequence_number(3);
				}
			}
		}
		boolean flag=usercontrollerInterface.getFlag(pis);
		String oldLocal_IP=pis.getOld_local_ip().trim();
		ResInfo resinfo=null;
		if(flag){
			//客户端配置已经存在
			if(oldLocal_IP==null||oldLocal_IP.equals("")){
				/**对于新增的，并且已经存在的客户端信息不做修改**/
				
			}else{
				log.warn("记录存在，进行修改：");
				resinfo=usercontrollerInterface.editOneInfo( pis);
			}
		}else{
			//不存在记录
			log.warn("记录不存在，进行增加：");
			resinfo=usercontrollerInterface.saveOneInfo( pis);
		}
		
		if(resinfo.getInfo().equals("success")){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**对于新增的，并且已经存在的客户端信息不做修改   返回提示信息**/
	@RequestMapping(value = "/jsp/veriftyIP")
	@ResponseBody
	public ResInfo  veriftyIP(@RequestParam String Local_ip,@RequestParam String cliend_id) {
		ResInfo resinfo=new ResInfo();
		boolean flag=usercontrollerInterface.veriftyIP(Local_ip,cliend_id);
		if(flag){
			/***存在该IP配置*/
			resinfo.setInfo("false");
		}else{
			resinfo.setInfo("success");
		}
		return resinfo;
	}
	
	/***删除某条记录***/
	@RequestMapping(value = "/jsp/delete")
	public ModelAndView  deleteOneInfo(HttpServletRequest req,
			HttpServletResponse res) {
		String local_ip=req.getParameter("Local_ip").trim();
		 List<ResConfig_Pis> list=usercontrollerInterface.deleteOneInfo(local_ip);
		Res_Pis resinfo=new Res_Pis();
		resinfo.setConfigList(list);
		return new ModelAndView("config_info","config_Pis",resinfo);
		
	}
	/***特效文件选择
	 * @throws UnsupportedEncodingException ***/
	@RequestMapping(value="/jsp/changeImagesAddress")
	@ResponseBody
	public OneEffectImagesAddress validataUser(@RequestParam String effectname) throws UnsupportedEncodingException{
		OneEffectImagesAddress infoList=null;
		infoList=usercontrollerInterface.getOneEffectImagesAddress(effectname);
		return infoList;
	}
	
	
	
	/***********************************888联动效果配置区域**********************8**/
	/**通过区位显示ip***/
	@RequestMapping(value="/jsp/getipByclient_Id")
	@ResponseBody
	public String getipByclient_Id(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException{
		response.setHeader("Access-Control-Allow-Origin","*");//允许ajax跨域请求
		String client_Id=request.getParameter("client_Id");
		String linkageCode=request.getParameter("linkageCode");
		log.info("查找"+client_Id+"    code"+linkageCode+"的IP");
		if(client_Id!=null){
			String ip=usercontrollerInterface.getipByclient_Id(client_Id,linkageCode);
			log.info("通过区位显示ip:"+client_Id+"     :"+client_Id);
			return ip;
		}else{
			return null;
		}
	}
	/***添加或者更新某个联动效果配置**/
	@RequestMapping(value="/jsp/addPic_Linkage")
	@ResponseBody
	public boolean addPic_Linkage(HttpServletRequest request,HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin","*");
		
		
		
		
		
		
		String  linkageStr=null;
		try {
			StringBuffer inputString = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String s = reader.readLine();
			while (s != null) {
				inputString.append(s);
				s = reader.readLine();
			}
			linkageStr=inputString.toString();
			log.info("得到的配置文件: " + linkageStr);
			
		}catch(Exception e){
			e.printStackTrace();
			log.info(e+"从客户端获取信息失败");
		}
		Gson gson=new Gson();
		List<Pic_Linkage>  linkageList=gson.fromJson(linkageStr,  
				new TypeToken<List<Pic_Linkage>>() {  
		}.getType());  
		boolean b=false;
		if(linkageList.get(0).getLinkInfoList().size()>0){
			b=usercontrollerInterface.addPic_Linkage(linkageList);
			return b;
		}else{
			return false;
			
		}
	}
	/****显示所有已经配置的联动效果**/
	@RequestMapping(value = "/jsp/showPic_Linkage")
	public ModelAndView  showPic_Linkage(HttpServletRequest req,
			HttpServletResponse res) {
		List<Pic_Linkage> Pic_LinkageList =usercontrollerInterface.showPic_Linkage();
		Gson gson=new Gson();
		log.info("显示所有已经配置的联动效果"+gson.toJson(Pic_LinkageList));
		return new ModelAndView("linkage_config_info","Pic_LinkageList",Pic_LinkageList);
		
	}
	/***读取某个联动配置信息**/
	@RequestMapping(value = "/jsp/getOnePic_Linkage")
	@ResponseBody
	public Pic_Linkage  getOnePic_Linkage(@RequestParam String linkageCode) {
		Pic_Linkage Pic_Linkageinfo =usercontrollerInterface.getOnePic_Linkage(linkageCode);
		Gson gson=new Gson();
		log.info("读取某个联动配置信息"+gson.toJson(Pic_Linkageinfo));
		return Pic_Linkageinfo;
	}
	
	/****删除某个联动效果**/
	@RequestMapping(value="/jsp/deleteOnePic_LinkageByCode")
	@ResponseBody
	public boolean deleteOnePic_LinkageByCode(@RequestParam String linkageCode) throws UnsupportedEncodingException{
		log.info("删除某个联动效果 :"+linkageCode);
		boolean b=usercontrollerInterface.deleteOnePic_LinkageByCode(linkageCode);
		return b;
	}
	/***联动效果 0,1,2**/
	@RequestMapping(value="/jsp/getmultiScreenEffect")
	@ResponseBody
	public List<Pic_info> getmultiScreenEffect(HttpServletRequest req,
			HttpServletResponse response) throws UnsupportedEncodingException{
		response.setHeader("Access-Control-Allow-Origin","*");
		List<Pic_info> list=usercontrollerInterface.getmultiScreenEffect();
		Gson gson=new Gson();
		log.info("读取联动效果"+gson.toJson(list));
		return list;
	}
	@RequestMapping(value="/jsp/*.jsp")
	@ResponseBody
	public void jsp(HttpServletRequest req,
			HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin","*");
		
	}
	/***读取视频文件名**/
	@RequestMapping(value="/jsp/getvideosName")
	@ResponseBody
	public List<String> getvideosName(HttpServletRequest req,
			HttpServletResponse response) throws UnsupportedEncodingException{
		response.setHeader("Access-Control-Allow-Origin","*");
		List<String> list=usercontrollerInterface.getvideosName();
		Gson gson=new Gson();
		log.info("读取视频文件名"+gson.toJson(list));
		return list;
	}
	
	/**网页访问日志文件*/
	@RequestMapping(value="/jsp/getLog")
	public ModelAndView getLogInfo(){
		java.util.List<String> loginfo=usercontrollerInterface.getlogInfo();
		return new ModelAndView("log","loginfo",loginfo);
	}
	
	
}