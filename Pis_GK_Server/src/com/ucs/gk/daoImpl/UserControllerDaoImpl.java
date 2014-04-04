package com.ucs.gk.daoImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucs.gk.bo.Config_Pis;
import com.ucs.gk.bo.ResServerSettingInfo;
import com.ucs.gk.bo.ServerSetting;
import com.ucs.gk.bo.client.Config_client;
import com.ucs.gk.bo.client.LinkageInfo_Single;
import com.ucs.gk.bo.client.Pic_Linkage;
import com.ucs.gk.bo.client.Pic_Multi;
import com.ucs.gk.bo.client.Pic_Single;
import com.ucs.gk.bo.client.VideoConfig;
import com.ucs.gk.bo.effect.Effects;
import com.ucs.gk.bo.effect.OneEffectImagesAddress;
import com.ucs.gk.bo.effect.Pic_info;
import com.ucs.gk.interFace.UserControllerInterFace;
import com.ucs.gk.request.Config_client_Req;
import com.ucs.gk.response.ResConfig_Pis;
import com.ucs.gk.response.ResInfo;
import com.ucs.gk.utils.ExceptionToString;


@Repository("UserControllerDaoImpl")
public class UserControllerDaoImpl implements UserControllerInterFace {
	static Logger log = Logger.getLogger(UserControllerDaoImpl.class);

	private static String user_url=System.getProperty("user.dir")+"/WEB-INF/config/share";
	private static String config_properties=System.getProperty("user.dir")+"/WEB-INF";
	private static String shareUrl=System.getProperty("catalina.base")+"/Pis_GK_client";
	//private static String shareUrl="E:/apache-tomcat-7.0.2"+"/Pis_GK_client";
	private static Timer timer=new Timer();
	
	/***获取json文件**/
	public  Config_Pis  getinfoList() {
		Gson  gson =  new Gson();
		Config_Pis config_Pis =  new Config_Pis();
		try{
			File file = new File(shareUrl+"/json.xml");
			log.warn(shareUrl+"/json.xml");
			BufferedReader br = new BufferedReader(new FileReader(file));	
			String str=null;
			String config="";
			while((str = br.readLine())!=null){
				config=config+str;
            }
            br.close();
            if(config.trim().length()>0){
            	config_Pis = gson.fromJson(config, Config_Pis.class);
            }else{
            	config_Pis=gson.fromJson("{'clients':[],'server_IP':'','version':'','update_Time':'','update_Time_now':'','synchronizationPlay':'1'}", Config_Pis.class);
            }
            String str2=gson.toJson(config_Pis);
           log.info("方法getinfoList的返回值"+str2);
		}catch(Exception e){
			log.error("读取getinfoList失败"+ExceptionToString.getTrace(e));
		}
		
		return config_Pis; 
		
	}
	
	/***保存json文件**/
	public  void   saveTempinfo(String str) {
		File file = new File(shareUrl+"/json.xml");
		log.warn(shareUrl+"/json.xml");
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(str);
			bw.close();
			log.info("保存json.xml成功");
		}catch(Exception e){
			e.printStackTrace();
			log.error("保存json.xml失败"+e);
		}
		
	}


	public  ResInfo saveOneInfo(Config_client_Req pis) {
		Gson gson=new Gson();
		Config_Pis config =getinfoList();
		List<Config_client> clients=config.getClients();
		
		Config_client client =  new Config_client();
		
		ResInfo resinfo=new ResInfo();
	
			//配置客户端
			client.setLocal_ip(pis.getLocal_ip());
			client.setClient_Id(pis.getClient_Id());
			client.setPic_m(pis.getPic_m());
			client.setPic_s(pis.getPic_s());
			client.setListVideoConfig(pis.getListVideoConfig());
			clients.add(client);
			config.setClients(clients);
			
			String result;
			result=gson.toJson(config);
			log.info("新增一条配置信息："+result);
			System.out.println(result);
			
			/**保存json字符**/
			
			saveTempinfo(result);
			
			resinfo.setInfo("success");
		return resinfo;
	}


	public  boolean login(HttpServletRequest req, HttpServletResponse res) {
		Properties p = new Properties();
		boolean b=false;
		String name=req.getParameter("username");
		String pw=req.getParameter("password");
		HttpSession session =req.getSession();
		session.setAttribute("username", name);
		session.setAttribute("password", pw);
		log.info(System.getProperty("user.dir")+"登录账号："+name+"   登录密码："+pw);
		String login_admin=null;
		String login_pw=null;
		try{
			p.load(new FileInputStream(config_properties+"/admin_login.properties"));
			login_admin=p.getProperty("login.username");
			login_pw=p.getProperty("login.password");
			
			if((login_admin.equals(name))&&(login_pw.equals(pw))){
				b=true;
			}else{
				b=false;
				log.warn(req.getRemoteHost()+"访问登录失败     ------"+"登录账号："+name+"   登录密码："+pw);
			}
		}catch(Exception e){
			log.error("读取账号密码配置文件失败"+ExceptionToString.getTrace(e));
		}
		
		/**文件不存在测则创建文件**/
		File file=new File(shareUrl+"/json.xml");
		if(file.exists()){
		}else{
			File newfile=new File(shareUrl+"/json.xml");
			log.info("json.xml文件不存在，创建新的文件");
			try{
				newfile.createNewFile();
			}catch(Exception  e){
				log.error("创建json.xml失败"+ExceptionToString.getTrace(e));
			}
		}
		return b;
	}


	public  void setServerinfo(HttpServletRequest req,
			HttpServletResponse res) {
		Config_Pis config_Pis =getinfoList();
		config_Pis.setServer_IP(req.getParameter("server_IP"));
		config_Pis.setVersion(req.getParameter("version"));
		config_Pis.setUpdate_Time(req.getParameter("update_Time").substring(0,5));
		boolean bisimmediateshutdown=Boolean.valueOf(req.getParameter("bisimmediateshutdown")).booleanValue();
		config_Pis.setBisimmediateshutdown(bisimmediateshutdown);//是否立即关机
		config_Pis.setShutDown_Time(req.getParameter("shutDownTime").substring(0,5));
		boolean flag=Boolean.valueOf(req.getParameter("flag")).booleanValue();
		
		/***服务器 唤醒 结束  同步更新设置**/
		ServerSetting settinginfo=new ServerSetting();
		settinginfo.setStartUpTime(req.getParameter("startUpTime"));
		settinginfo.setShutDownTime(req.getParameter("shutDownTime"));
		
		settinginfo.setSynchronizationPlay(req.getParameter("synchronizationPlay"));
		//System.out.println(settinginfo.getStartUpTime()+"    "+settinginfo.getShutDownTime());
		setServerSetting(settinginfo);
		
		
		if(flag){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			config_Pis.setUpdate_Time_now(sdf.format(new Date()));
		}else{
			
		}
		log.info("是否立即更新："+flag+"更新标志时间："+config_Pis.getUpdate_Time_now());
		Gson  gson =  new Gson();
		String r = gson.toJson(config_Pis);
		File file = new File(shareUrl+"/json.xml");
		
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(r);
			bw.close();
			res.sendRedirect("getinfoList.do");
		}catch(Exception e){
			log.error("更新服务器配置失败"+ExceptionToString.getTrace(e));
		}
		timer.schedule(new TimerTask() {
			public void run() {
				//60秒后修改是否立即关机为false
				Config_Pis config_Pis =getinfoList();
				config_Pis.setBisimmediateshutdown(false);
				Gson  gson =  new Gson();
				String r = gson.toJson(config_Pis);
				saveTempinfo(r);
				timer.cancel();
			}
		}, 60*1000);
		
	}


	public  ResInfo editOneInfo(Config_client_Req pis) {
		ResInfo resinfo=new ResInfo();
		Config_Pis config_Pis =getinfoList();
		List<Config_client> listinfo=config_Pis.getClients();
		String local_ip=pis.getLocal_ip().trim();
		String oldlocal_ip=pis.getOld_local_ip().trim();
		
		Gson gson=new Gson();
		for(Config_client info :listinfo){
			String str=info.getLocal_ip().trim();
			if(str.equals(oldlocal_ip)){
				info.setLocal_ip(local_ip);
				info.setClient_Id(pis.getClient_Id());
				info.setPic_m(pis.getPic_m());
				info.setPic_s(pis.getPic_s());
			}
		}
		//修改联动配置里面的信息
		List<Pic_Linkage> LinkageList=config_Pis.getPic_Linkage();
		for(Pic_Linkage Linkageifno:LinkageList){
			List<LinkageInfo_Single> SingleList=Linkageifno.getLinkInfoList();
			for(LinkageInfo_Single singleInfo:SingleList){
				if(singleInfo.getLocal_ip().equals(oldlocal_ip)){
					singleInfo.setLocal_ip(local_ip);
					log.info("修改联动配置里面的信息"+local_ip);
				}
			}
		}
		
		int count =0;
		for(Config_client info :listinfo){
			if(local_ip.equals(info.getLocal_ip())){
				count++;
			}
		}
		if(count==1){
			String json=gson.toJson(config_Pis);
			log.info("修改后的信息："+json);
			saveTempinfo(json);
			resinfo.setInfo("success");
		}else{
			resinfo.setInfo("false");
			log.warn("修改单条记录失败！");
		}
		return resinfo;
	}

	/**判断是否已存在这个ip的配置*/
	public  boolean getFlag(Config_client_Req one_info) {
		boolean falg=false;
		Config_Pis config_Pis =getinfoList();
		List<Config_client> listinfo=config_Pis.getClients();
		String local_ip=one_info.getOld_local_ip().trim();
		String newlocal_ip=one_info.getLocal_ip().trim();
		if(local_ip.equals("")){
			local_ip=newlocal_ip;
		}
		for(Config_client info :listinfo){
			String str=info.getLocal_ip().trim();
			if(str.equals(local_ip)){
				falg=true;
				break;
			}
		}
		return falg;
	}

	/**IP是否存在异步验证*/
	public  boolean veriftyIP(String Local_IP,String cliend_id) {
		boolean falg=false;
		Config_Pis config_Pis =getinfoList();
		List<Config_client> listinfo=config_Pis.getClients();
		for(Config_client info :listinfo){
			String str=info.getLocal_ip().trim();
			String str2=info.getClient_Id().trim();
			if((str.equals(Local_IP))||(str2.equals(cliend_id))){
				falg=true;
				break;
			}
		}
		return falg;
	}

	

	public  Config_client modification(String local_ip) {
		Config_client c_info=new Config_client();
		Config_Pis config_Pis =getinfoList();
		List<Config_client> listinfo=config_Pis.getClients();
		log.info("等待修改的IP："+local_ip);
		for(Config_client info :listinfo){
			String str=info.getLocal_ip().trim();
			if(str.equals(local_ip)){
				c_info= info;
			}
		}
	Gson gson=new Gson();
	String str=gson.toJson(c_info);
	log.info("得到要修该的信息"+str);
		return c_info;
	}

	/**获取特效**/
	public  Effects geteffects() {
		
		Effects effect=new Effects();
		Properties p = new Properties();
		try{
			FileInputStream fis=new FileInputStream(config_properties+"/effects.properties");
			BufferedReader buffer = new BufferedReader(   
				       new InputStreamReader(fis,"utf-8"));  
			p.load(buffer);
			String info=p.getProperty("effect.info");
			log.info("获取特效："+info);
			Gson gson=new Gson();
			effect=gson.fromJson(info,Effects.class );
			effect=getFileName(effect);
		}catch(Exception e){
			log.error("特效获取失败"+ExceptionToString.getTrace(e));
		}
		return effect;
	}

	/***列表信息将数字转为字符**/
	public  List<ResConfig_Pis> getinfoListNumTOName() {
		List<ResConfig_Pis> resinfoList=new ArrayList<ResConfig_Pis>();
		Properties p = new Properties();
		Gson  gson =  new Gson();
		Config_Pis config_Pis =  new Config_Pis();
		Effects effect=new Effects();
		try{
			FileInputStream fis=new FileInputStream(config_properties+"/effects.properties");
			BufferedReader buffer = new BufferedReader(   
				       new InputStreamReader(fis,"utf-8"));  
			p.load(buffer);
			String login_admin=p.getProperty("effect.info");
			effect=gson.fromJson(login_admin,Effects.class );
		}catch(Exception e){
			e.printStackTrace();
		}
		List<Pic_info> multi_Effects_List=effect.getMulti_Effects();
		List<Pic_info> single_picIn_Effects_List=effect.getSingle_picIn_Effects();
		List<Pic_info> single_picout_Effects_List=effect.getSingle_picout_Effects();
		
		 
		try{
			
			File file = new File(shareUrl+"/json.xml");
			log.info(shareUrl+"/json.xml");
			BufferedReader br = new BufferedReader(new FileReader(file));	
			String str=null;
			String config="";
			while((str = br.readLine())!=null){
				config=config+str;
            }
            br.close();
          
            
            if(config.trim().length()>0){
            	config_Pis = gson.fromJson(config, Config_Pis.class);
            	List<Config_client> clients=config_Pis.getClients();
            	for(Config_client client:clients){
            		ResConfig_Pis pis=new ResConfig_Pis();
            		pis.setClient_Id(client.getClient_Id());
            		pis.setLocal_ip(client.getLocal_ip());
            		List<String> Pic_Multi_image_picShow=new ArrayList<String>();
            		List<String> Pic_Multi_image_urls=new ArrayList<String>();
            		for(Pic_Multi pic:client.getPic_m()){
            			Pic_Multi_image_picShow.add(pic.getPicShow());
            			Pic_Multi_image_urls.add(pic.getResFolder());
            			
            		}
            		pis.setPic_m_image_urls(Pic_Multi_image_urls);//多幅图片路径
            		pis.setPic_mNames(Pic_Multi_image_picShow);//多幅图片的名称
            		pis.setPic_s_image_urls(null);//单幅图片路径不显示
            		pis.setPic_sNames(null);//单幅名称不显示
            		
            		for(int i=0;i<Pic_Multi_image_picShow.size();i++){
            			for(Pic_info info:multi_Effects_List){
            				if(info.getValue().equals(Pic_Multi_image_picShow.get(i))){
            					Pic_Multi_image_picShow.set(i, info.getName());
            					break;
            				}
            			}
            			
            		}
            		//视屏名字
            		List<VideoConfig> ListVideoConfig=client.getListVideoConfig();
            		List<String> videoNames=new ArrayList<String>();
            		for(VideoConfig v:ListVideoConfig){// Resources/Videos/rat.wmv
            			videoNames.add(v.getVideoRelativePath().substring(17));//获取视频名字
            		}
            		pis.setViedoNames(videoNames);
            		
            		resinfoList.add(pis);
            		
            		
            	}
            	
            }
            
            String str2=gson.toJson(resinfoList);
            log.info("/jsp/getinfoList.do的返回值："+str2);
		}catch(Exception e){
			log.error("列表信息数字转化为字符操作失败"+ExceptionToString.getTrace(e));
		}
		
		/**对集合按照区位号大小进行排序***/
		
		Collections.sort(resinfoList,new Comparator<ResConfig_Pis>(){
			public int compare(ResConfig_Pis p1,ResConfig_Pis p2){
				return Integer.parseInt(p1.getClient_Id().trim())-Integer.parseInt(p2.getClient_Id().trim());
			}
		});
		return resinfoList; 
	}


	public  List<ResConfig_Pis>  deleteOneInfo(String local_ip) {
		Config_Pis config_Pis;
		config_Pis=getinfoList();
		
		
		List<Config_client> clients=config_Pis.getClients();
		
		for(int i=0;i<clients.size();i++){
			if(clients.get(i).getLocal_ip().equals(local_ip)){
				clients.remove(i);
			}
		}
		log.info("删除单条记录操作成功");
		List<Pic_Linkage> pic_LinkageList=config_Pis.getPic_Linkage();
		for(int i=0;i<pic_LinkageList.size();i++){
			List<LinkageInfo_Single> SingleList=pic_LinkageList.get(i).getLinkInfoList();
			for(int j=0;j<SingleList.size();j++){
				log.info("去删除联动配置中对应的ip"+local_ip);
				if(SingleList.get(j).getLocal_ip().equals(local_ip)){
					SingleList.remove(j);
				}
			}
		}
		Gson gson=new Gson();
		String result=gson.toJson(config_Pis);
		saveTempinfo(result);
		List<ResConfig_Pis> info=getinfoListNumTOName();
		
		return info;
	}


	/***读取文件夹名
	 * @param effect 
	 * @return */
	
	public  static    Effects getFileName(Effects effect){
		
		List<Pic_info> pic_Mulit_url=new ArrayList<Pic_info>();//多图特效文件路径
		List<Pic_info> pic_Single_url=new ArrayList<Pic_info>();//单图特效文件路径
		List<Pic_info> video_url=new ArrayList<Pic_info>();//视频
		File file=new File(shareUrl+"/Resources");
		log.info("读取图片文件夹名"+shareUrl+"/Resources");
		
		File[] files=file.listFiles();
		for(File f:files){
			boolean f1=false;
			if(f.isDirectory()){
				f1=true;
			}
			
			File file2=new File(shareUrl+"/Resources/"+f.getName());
			log.debug(shareUrl+"/Resources/"+f.getName());
			if(file2.isDirectory()){
				File[] files2=file2.listFiles();
				for(File f2:files2){
					if(f2.isDirectory()){
						//放单幅和多幅的路径
						f1=false;
						if(f.getName().equals("MultiImages")){//多幅
							Pic_info info_s=new Pic_info();
							info_s.setValue("Resources/"+f.getName()+"/"+f2.getName());
							info_s.setName("Resources/"+f.getName()+"/"+f2.getName());
							pic_Mulit_url.add(info_s);
						}else if(f.getName().equals("SingleImages")){//单幅
							Pic_info info_s=new Pic_info();
							info_s.setValue("Resources/"+f.getName()+"/"+f2.getName());
							info_s.setName("Resources/"+f.getName()+"/"+f2.getName());
							pic_Single_url.add(info_s);
						}
					}
				}
			}
			if(f1){
				//放视频路径
				File[] videoNames= f.listFiles();
				for(File f2:videoNames){
					Pic_info info_v=new Pic_info();
					info_v.setName("Resources/Videos/"+f2.getName());
					info_v.setValue("Resources/Videos/"+f2.getName());
					video_url.add(info_v);
				}
				
			}
		}
		effect.setPic_Single_url(pic_Single_url);
		effect.setPic_Mulit_url(pic_Mulit_url);
		effect.setVideo_url(video_url);
		log.info("图片文件夹读取成功");
		return effect;
	}



	/**指定效果指定图片目录***/
	public  OneEffectImagesAddress getOneEffectImagesAddress(String name) {
		log.info("读取指定效果对应的图片文件目录");
		OneEffectImagesAddress infoList=new OneEffectImagesAddress();
		List<Pic_info> list=new ArrayList<Pic_info>();
		 if(name.trim().equals("水墨画")){
			Pic_info info=new Pic_info();
			info.setValue("Images/InkDraw");
			info.setName("Images/InkDraw");
			list.add(info);
			infoList.setInfoList(list);
		}else if(name.trim().equals("路径移动")){
			Pic_info info=new Pic_info();
			info.setValue("Images/BorderUniversity");
			info.setName("Images/BorderUniversity");
			list.add(info);
			infoList.setInfoList(list);
		}else{
			Effects effect =new Effects();
			effect=getFileName(effect);
			List<Pic_info> pic_Mulit_url=effect.getPic_Mulit_url();
			for(int i=0;i<pic_Mulit_url.size();i++){
				String urlAddress=pic_Mulit_url.get(i).getValue();
				if((urlAddress.equals("Images/InkDraw"))||(urlAddress.equals("Images/BorderUniversity"))){
					pic_Mulit_url.remove(i);
				}
			}
			infoList.setInfoList(pic_Mulit_url);
		}
		log.info("成功读取指定效果对应的图片文件目录");
		return infoList;
	}
	
	
	/***配置服务器的远程唤醒时间，关闭时间设置完成**/
	public  void setServerSetting(ServerSetting info) {
		Properties p = new Properties();
		try{
			FileInputStream fis=new FileInputStream(config_properties+"/effects.properties");
			BufferedReader buffer = new BufferedReader(   
				       new InputStreamReader(fis,"utf-8"));  
			p.load(buffer);
			
			p.setProperty("Servr.startUpTime", info.getStartUpTime());
			if(info.getShutDownTime().equals("00:00:00")){
				info.setShutDownTime("24:00:00");
			}
			p.setProperty("Servr.shutDownTime", info.getShutDownTime());
			//p.setProperty("Servr.synchronizationPlay", info.getSynchronizationPlay());
			FileOutputStream fos=new FileOutputStream(config_properties+"/effects.properties");
			BufferedWriter bufwri=new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
			p.store(bufwri, null);
			log.info("远程唤醒时间，关闭时间设置完成");
			
		}catch(Exception e){
			log.error("远程唤醒时间，关闭时间设置失败"+ExceptionToString.getTrace(e));
		}
		
	}
	
	
	
	


	/****返回服务器配置信息**/
	public static ResServerSettingInfo getServerSettingInfo(
			Config_Pis config_Pis) {
		ResServerSettingInfo info=new ResServerSettingInfo();
		Properties p = new Properties();
		String startuptimes=null;
		String shutdowntimes=null;
		try{
			FileInputStream fis=new FileInputStream(config_properties+"/effects.properties");
			BufferedReader buffer = new BufferedReader(   
				       new InputStreamReader(fis,"utf-8"));  
			p.load(buffer);
			startuptimes=p.getProperty("Servr.startUpTime");
			shutdowntimes=p.getProperty("Servr.shutDownTime");
			
		}catch(Exception e){
			log.error("getServerSettingInfo:读取服务器配置信息失败"+ExceptionToString.getTrace(e));
		}
		
		info.setServer_IP(config_Pis.getServer_IP());
		info.setVersion(config_Pis.getVersion());
		info.setUpdate_Time(config_Pis.getUpdate_Time());
		info.setStartUpTime(startuptimes);
		info.setShutDownTime(config_Pis.getShutDown_Time());
		info.setUpdate_Time_now(config_Pis.getUpdate_Time_now());
		
		log.info(info.getStartUpTime()+"    "+config_Pis.getShutDown_Time());
		return info;
	}

	/***  开发人员 调试  日志查看**/
	
	public List<String> getlogInfo() {
		List<String> infoList=new ArrayList<String>();
		
		try{
			FileInputStream fis=new FileInputStream(config_properties+"/logs/log.txt");
			InputStreamReader isr=new InputStreamReader(fis,"utf-8");
			BufferedReader br=new BufferedReader(isr);
			
			String str=null;
			while ((str=br.readLine())!=null){
				infoList.add(str);
			} 
			isr.close();
		}catch(Exception e){
			log.error("读取日志文件操作失败"+ExceptionToString.getTrace(e));
		}
		return infoList;
	}

	/***********************************888联动效果配置区域**********************8**/
	/**通过区位显示ip***/
	public String getipByclient_Id(String client_Id,String linkageCode) {
		Config_Pis config_pis=getinfoList();
		List<Config_client> clients=config_pis.getClients();
		String ip=null;
		for(Config_client info:clients){
			System.out.println(info.getClient_Id()+"-------------"+client_Id);
			if(info.getClient_Id().equals(client_Id)){
				ip=info.getLocal_ip();
			}
		}
		if(ip==null){
			ip="此区位尚未配置！";
		}else{//存在  检查这个ip是否已经配置到联动效果
			int count =0;
			List<Pic_Linkage> pic_LinkageList=config_pis.getPic_Linkage();
			if(linkageCode==null){
				for(Pic_Linkage Linkageinfo:pic_LinkageList){
					List<LinkageInfo_Single> SingleList=Linkageinfo.getLinkInfoList();
					for(LinkageInfo_Single Singleinfo: SingleList){
						if(Singleinfo.getLocal_ip().equals(ip)){
							count++;
						}
					}
				}
			}else{
				for(Pic_Linkage Linkageinfo:pic_LinkageList){
					if(!Linkageinfo.getLinkageCode().equals(linkageCode)){
						log.info(Linkageinfo.getLinkageCode()+"------"+linkageCode);
						List<LinkageInfo_Single> SingleList=Linkageinfo.getLinkInfoList();
						for(LinkageInfo_Single Singleinfo: SingleList){
							if(Singleinfo.getLocal_ip().equals(ip)){
								count++;
							}
						}
					}
					
				}
			}
			if(count>0){
				ip="此区位已在其他联动配置!";
			}
			
		}
		
		return ip;
	}

	/***添加联动或更新配置***/
	public boolean addPic_Linkage(List<Pic_Linkage> linkageList) {
		Gson gson=new Gson();
		log.info("获取到的配置文件"+gson.toJson(linkageList.get(0)));
		Config_Pis config_pis=getinfoList();
		List<Pic_Linkage> Pic_LinkageList=config_pis.getPic_Linkage();
		
		Date date =new Date();
		Long l=date.getTime();
		String LinkageCode=linkageList.get(0).getLinkageCode();
		if(LinkageCode==null){//不存在  则添加
			if(Pic_LinkageList==null){//首次配置 创建集合
				 Pic_LinkageList=new ArrayList<Pic_Linkage>();
			}
			linkageList.get(0).setLinkageCode(l+"");
			Pic_LinkageList.add(linkageList.get(0));
			
		}else{//存在就把原来的删了
			for(int i=0;i<Pic_LinkageList.size();i++){
				Pic_Linkage info=Pic_LinkageList.get(i);
				if(info.getLinkageCode().equals(LinkageCode)){
					Pic_LinkageList.remove(i);
				}
			}
			log.info("修改后的文件"+gson.toJson(Pic_LinkageList));
			 List<LinkageInfo_Single> SingleList=linkageList.get(0).getLinkInfoList();
			 for(LinkageInfo_Single info:SingleList){
				 String ip=info.getLocal_ip().split(":")[0];
				 info.setLocal_ip(ip);
			 }
			Pic_LinkageList.add(linkageList.get(0));
			log.info("新增后后的文件"+gson.toJson(Pic_LinkageList));
		}
		config_pis.setPic_Linkage(Pic_LinkageList);
		
		String str=gson.toJson(config_pis);
		log.info("添加联动或更新配置      增加或者修改后的文件"+str);
		saveTempinfo(str);
		return true;
	}
	/****显示所有已经配置的联动效果**/
	public List<Pic_Linkage> showPic_Linkage() {
		Config_Pis config_pis=getinfoList();
		List<Pic_Linkage> infoList=config_pis.getPic_Linkage();
		if(infoList!=null){
			for(int i=0;i<infoList.size();i++){
				List<LinkageInfo_Single> linkInfoList=infoList.get(i).getLinkInfoList();
				for(int j=0;j<linkInfoList.size();j++){
					String local_ip=linkInfoList.get(j).getLocal_ip();
					String client_id=getClient_IdByIP(local_ip);
					if(client_id!=null){
						linkInfoList.get(j).setPlayOrder(Integer.parseInt(client_id));
					}
				}
				/**按照区位号排序***/
				Collections.sort(linkInfoList,new Comparator<LinkageInfo_Single>(){
					public int compare(LinkageInfo_Single L1,
							LinkageInfo_Single L2) {
						return L1.getPlayOrder()-L2.getPlayOrder();
					}
				});
				
			}
			
			 List<Pic_info> EffectList= getmultiScreenEffect();
			 Map<String,String> map=new HashMap<String,String>();
			 map.put("1", "整点");
			 map.put("2", "半点");
			 map.put("3", "一刻钟");
			 for(Pic_Linkage info:infoList){
				 String Effectid=info.getMultiScreenEffect();
				 for(Pic_info effectinfo:EffectList){
					 if(Effectid.equals(effectinfo.getValue())){
						 info.setMultiScreenEffect(effectinfo.getName());
						 info.setSynchronizationPlay(map.get(info.getSynchronizationPlay()));
						
					 }
				 }
			 }
			return infoList;
		}else{
			return null;
		}
	}
	/**用ip匹配区位号***/
	private String getClient_IdByIP(String local_ip) {
		Config_Pis config_pis=getinfoList();
		List<Config_client> clients=config_pis.getClients();
		String client_id=null;
		for(Config_client info:clients){
			if(info.getLocal_ip().equals(local_ip)){
				client_id=info.getClient_Id();
			}
		}
		return client_id;
	}

	
	/***读取某个联动配置信息**/

	public Pic_Linkage getOnePic_Linkage(String linkageCode) {
		Pic_Linkage Linkage=null;
		Config_Pis config_pis=getinfoList();
		List<Pic_Linkage> infoList=config_pis.getPic_Linkage();
		for(Pic_Linkage info:infoList){
			if(info.getLinkageCode().equals(linkageCode)){
				Linkage=info;
			}
		}
		List<LinkageInfo_Single> SingleList=Linkage.getLinkInfoList();
		Collections.sort(SingleList, new Comparator<LinkageInfo_Single>() {
			public int compare(LinkageInfo_Single L1, LinkageInfo_Single L2) {
				return L1.getPlayOrder()-L2.getPlayOrder();
			}
			
		});
		for(LinkageInfo_Single info:SingleList){
			String Local_ip=info.getLocal_ip();
			String client_id=getClient_IdByIP(Local_ip);
			info.setLocal_ip(Local_ip+":"+client_id);
		}
		
		return Linkage;
	}
	/****删除某个联动效果**/
	public boolean deleteOnePic_LinkageByCode(String linkageCode) {
		Config_Pis config_pis=getinfoList();
		List<Pic_Linkage> infoList=config_pis.getPic_Linkage();
		for(int i=0;i<infoList.size();i++){
			if(infoList.get(i).getLinkageCode().equals(linkageCode)){
				infoList.remove(i);
				break;
			}
		}
		Config_Pis config_pis2=getinfoList();
		config_pis2.setPic_Linkage(infoList);
		Gson gson=new Gson();
		String str=gson.toJson(config_pis2);
		saveTempinfo(str);
		return true;
	}
	
	/***联动效果 0,1,2**/
	public List<Pic_info> getmultiScreenEffect() {
		Properties p = new Properties();
		String multiScreenEffect=null;
		
		try{
			FileInputStream fis=new FileInputStream(config_properties+"/linkageSetting.properties");
			BufferedReader buffer = new BufferedReader(   
				       new InputStreamReader(fis,"utf-8"));  
			p.load(buffer);
			multiScreenEffect=p.getProperty("Servr.tmultiScreenEffect");
		}catch(Exception e){
			log.error("multiScreenEffect:读取联动效果失败"+ExceptionToString.getTrace(e));
		}
		Gson gson=new Gson();
		List<Pic_info> list=gson.fromJson(multiScreenEffect, 
				new TypeToken<List<Pic_info>>(){
		}.getType());
		return list;
	}
	
	public List<String> getvideosName() {
		File file=new File(shareUrl+"/Resources/Videos");
		List<String> list=new ArrayList<String>();
		log.info("读取图片文件夹名"+shareUrl+"/Resources/Videos");
		
		File[] files=file.listFiles();
		for(File f:files){
			try{
				list.add(new String(f.getName().getBytes(),"utf-8"));
				System.out.println(f.getName());
			}catch(Exception e){
			}
		}
		return list;
	}

	public String getIPByClientId(String clientId) {
		Properties p = new Properties();
		String clients=null;
		String ip=null;
		
		try{
			FileInputStream fis=new FileInputStream(config_properties+"/linkageSetting.properties");
			BufferedReader buffer = new BufferedReader(   
				       new InputStreamReader(fis,"utf-8"));  
			p.load(buffer);
			clients=p.getProperty("clientid.ip");
		}catch(Exception e){
			log.error("multiScreenEffect:读取联动效果失败"+ExceptionToString.getTrace(e));
		}
		Gson gson=new Gson();
		List<Pic_info> list=gson.fromJson(clients, 
				new TypeToken<List<Pic_info>>(){
		}.getType());
		for(Pic_info pic:list){
			if(pic.getValue().equals(clientId)){
				ip=pic.getName();
				break;
			}
		}
		return ip;
	}

	
	
}
