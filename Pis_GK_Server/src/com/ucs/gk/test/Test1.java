package com.ucs.gk.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.google.gson.Gson;
import com.ucs.gk.bo.Config_Pis;
import com.ucs.gk.bo.client.LinkageInfo_Single;
import com.ucs.gk.bo.client.Pic_Linkage;
import com.ucs.gk.bo.client.Pic_Multi;
import com.ucs.gk.bo.client.Pic_Single;
import com.ucs.gk.bo.client.VideoConfig;
import com.ucs.gk.bo.effect.Effects;
import com.ucs.gk.bo.effect.Pic_info;
import com.ucs.gk.daoImpl.UserControllerDaoImpl;
import com.ucs.gk.request.Config_client_Req;
import com.ucs.gk.response.ResConfig_Pis;

public class Test1 {

	/**
	 * @param args
	 */
	private static String user_url=System.getProperty("user.dir").replace("bin", "webapps/GK");
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Properties p = new Properties();
		try{
			FileInputStream fis=new FileInputStream(user_url+"/effects.properties");
			BufferedReader buffer = new BufferedReader(   
				       new InputStreamReader(fis,"utf-8"));  
			p.load(buffer);
			String login_admin=p.getProperty("effect.info");
			System.out.println(login_admin);
			Gson gson=new Gson();
			Effects effect=new Effects();
			effect=gson.fromJson(login_admin,Effects.class );
			System.out.println(effect.getMulti_Effects().size());
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	 @Test
	public void Pic_Linkagetojson(){
		List<Pic_Linkage> Pic_LinkageList=new ArrayList<Pic_Linkage>();
		Pic_Linkage Linkage=new Pic_Linkage();
		Linkage.setMultiScreenEffect("1");
		Linkage.setVideoName("universe.mp4");
		Linkage.setSynchronizationPlay("0");
		Linkage.setTime(60);
		List<LinkageInfo_Single> LinkInfoList=new ArrayList<LinkageInfo_Single>();
		LinkageInfo_Single  Single=new LinkageInfo_Single();
		Single.setLocal_ip("192.168.2.23");
		Single.setPlayOrder(3);
		LinkageInfo_Single  Single2=new LinkageInfo_Single();
		Single2.setLocal_ip("192.168.2.25");
		Single2.setPlayOrder(6);
		LinkInfoList.add(Single);
		LinkInfoList.add(Single2);
		Linkage.setLinkInfoList(LinkInfoList);
		Pic_LinkageList.add(Linkage);
		Gson gson=new Gson();
		String str=gson.toJson(Pic_LinkageList);
		System.out.println(str);
	}
	
	 @Test
	 public void list(){
		/* List<ResConfig_Pis> config_Pis=new ArrayList<ResConfig_Pis>();
		 Gson gson=new Gson();
			String str=gson.toJson(config_Pis);
			System.out.println(str);*/
		 System.out.println((new Date().getTime()));
	 }
	 @Test
	 public void stri(){
		 Config_client_Req req=new Config_client_Req(); 
		 req.setClient_Id("1");
		 req.setLocal_ip("192.168.2.122");
		 req.setOld_local_ip("");
		 Pic_Multi pic=new Pic_Multi();
		 pic.setSequence_number(2);
		 pic.setPicShow("2");
		 pic.setResFolder("Resources/MultiImages/Animal");
		 pic.setRandom(false);
		 pic.setTime(60);
	
		 List<Pic_Multi> list=new ArrayList<Pic_Multi>();
		 list.add(pic);
		 req.setPic_m(list);
		 
		 VideoConfig video=new VideoConfig();
		 video.setSequence_number(3);
		 video.setVideoRelativePath("Resources/Videos/rat.wmv");
		 List<VideoConfig> vlist=new ArrayList<VideoConfig>();
		 vlist.add(video);
		 req.setListVideoConfig(vlist);
		 
		 Pic_Single s= new Pic_Single();
		 s.setSequence_number(0);
		 s.setResFolder("Resources/SingleImages/DefaultTheme");
		 Pic_Single s2= new Pic_Single();
		 s2.setSequence_number(1);
		 s2.setResFolder("Resources/SingleImages/HongKouAD");
		 List<Pic_Single> list2=new ArrayList<Pic_Single>();
		 list2.add(s);
		 list2.add(s2);
		 req.setPic_s(list2);
		 Gson gson=new Gson();
		 
		 System.out.println(gson.toJson(req));
		// UserControllerDaoImpl dao =new UserControllerDaoImpl();
		 //dao.saveOneInfo(req);
	 }
	 

	
}
