package com.ucs.gk.bo.client;

import java.util.ArrayList;
import java.util.List;


//客户端
public class Config_client {

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