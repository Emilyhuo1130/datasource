package com.ucs.gk.interFace;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ucs.gk.bo.Config_Pis;
import com.ucs.gk.bo.client.Config_client;
import com.ucs.gk.bo.client.Pic_Linkage;
import com.ucs.gk.bo.effect.Effects;
import com.ucs.gk.bo.effect.OneEffectImagesAddress;
import com.ucs.gk.bo.effect.Pic_info;
import com.ucs.gk.request.Config_client_Req;
import com.ucs.gk.response.ResConfig_Pis;
import com.ucs.gk.response.ResInfo;

public interface UserControllerInterFace {
	public  boolean login(HttpServletRequest req, HttpServletResponse res);
	public  List<ResConfig_Pis> getinfoListNumTOName() ;
	public  Config_Pis  getinfoList();
	public  void setServerinfo(HttpServletRequest req,HttpServletResponse res);
	public  Config_client modification(String local_ip);
	public  Effects geteffects();
	public  boolean getFlag(Config_client_Req one_info);
	public  boolean veriftyIP(String Local_IP,String cliend_id);
	public   List<ResConfig_Pis> deleteOneInfo(String local_ip);
	public  OneEffectImagesAddress getOneEffectImagesAddress(String name);
	public  ResInfo saveOneInfo(Config_client_Req pis);
	public  ResInfo editOneInfo(Config_client_Req pis);
	public List<String> getlogInfo();
	public String getipByclient_Id(String client_Id, String linkageCode);
	public boolean addPic_Linkage(List<Pic_Linkage> linkageList);
	public List<Pic_Linkage> showPic_Linkage();
	public Pic_Linkage getOnePic_Linkage(String linkageCode);
	public boolean deleteOnePic_LinkageByCode(String linkageCode);
	public List<Pic_info> getmultiScreenEffect();
	public List<String> getvideosName();
	public String getIPByClientId(String parameter);
	
	
	
	
	
}
