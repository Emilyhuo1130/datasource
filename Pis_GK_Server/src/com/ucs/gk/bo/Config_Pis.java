package com.ucs.gk.bo;

import java.util.ArrayList;
import java.util.List;

import com.ucs.gk.bo.client.Config_client;
import com.ucs.gk.bo.client.Pic_Linkage;
//  大对象
public class Config_Pis {

	private String server_IP="192.168.2.1";//服务端IP
	private String version="0.0.1";//版本号
	private String update_Time="02:00";//定时跟新时间
	private String update_Time_now="false";//是否立即跟新
	private boolean bisimmediateshutdown=false;//是否立即关机
	private String shutDown_Time="24:00";//定时关机时间

	private List<Config_client> clients=new ArrayList<Config_client>();
	private List<Pic_Linkage> pic_Linkage=new ArrayList<Pic_Linkage>();//联动实体类
	
	
	
	
	public boolean isBisimmediateshutdown() {
		return bisimmediateshutdown;
	}
	public void setBisimmediateshutdown(boolean bisimmediateshutdown) {
		this.bisimmediateshutdown = bisimmediateshutdown;
	}
	public String getShutDown_Time() {
		return shutDown_Time;
	}
	public void setShutDown_Time(String shutDown_Time) {
		this.shutDown_Time = shutDown_Time;
	}
	public List<Pic_Linkage> getPic_Linkage() {
		return pic_Linkage;
	}
	public void setPic_Linkage(List<Pic_Linkage> pic_Linkage) {
		this.pic_Linkage = pic_Linkage;
	}
	
	public String getUpdate_Time_now() {
		return update_Time_now;
	}
	public void setUpdate_Time_now(String update_Time_now) {
		this.update_Time_now = update_Time_now;
	}
	public List<Config_client> getClients() {
		return clients;
	}
	public void setClients(List<Config_client> clients) {
		this.clients = clients;
	}
	public String getServer_IP() {
		return server_IP;
	}
	public void setServer_IP(String server_IP) {
		this.server_IP = server_IP;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUpdate_Time() {
		return update_Time;
	}
	public void setUpdate_Time(String update_Time) {
		this.update_Time = update_Time;
	}
	

	
	
}
