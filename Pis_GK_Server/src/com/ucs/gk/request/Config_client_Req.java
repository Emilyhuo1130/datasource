package com.ucs.gk.request;

import java.util.ArrayList;
import java.util.List;

import com.ucs.gk.bo.client.Pic_Multi;
import com.ucs.gk.bo.client.Pic_Single;
import com.ucs.gk.bo.client.VideoConfig;


//请求类
public class Config_client_Req {
	private String old_local_ip;//原来的ip地址
	private String local_ip="";//客户端IP
	private String client_Id="";//客户端id（区位号）
	private List<Pic_Multi> pic_m=new ArrayList<Pic_Multi>();//多幅
	private List<Pic_Single> pic_s=new ArrayList<Pic_Single>();//单幅
	private List<VideoConfig> ListVideoConfig=new ArrayList<VideoConfig>();//视频
	
	public List<VideoConfig> getListVideoConfig() {
		return ListVideoConfig;
	}
	public void setListVideoConfig(List<VideoConfig> listVideoConfig) {
		ListVideoConfig = listVideoConfig;
	}
	public String getOld_local_ip() {
		return old_local_ip;
	}
	public void setOld_local_ip(String old_local_ip) {
		this.old_local_ip = old_local_ip;
	}
	public String getLocal_ip() {
		return local_ip;
	}
	public void setLocal_ip(String local_ip) {
		this.local_ip = local_ip;
	}
	public String getClient_Id() {
		return client_Id;
	}
	public void setClient_Id(String client_Id) {
		this.client_Id = client_Id;
	}
	public List<Pic_Multi> getPic_m() {
		return pic_m;
	}
	public void setPic_m(List<Pic_Multi> pic_m) {
		this.pic_m = pic_m;
	}
	public List<Pic_Single> getPic_s() {
		return pic_s;
	}
	public void setPic_s(List<Pic_Single> pic_s) {
		this.pic_s = pic_s;
	}
	
	
	
	


	

}