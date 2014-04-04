package com.ucs.gk.bo;

public class ResServerSettingInfo {
	private String server_IP="";//服务端IP
	private String version="";//版本号
	private String update_Time="";//定时跟新时间
	private String update_Time_now="";//是否立即跟新
	
	private String synchronizationPlay="1";//同步播放时间   1：整点    2：半点    3：一刻钟
	private String startUpTime="04:00";//客户端唤醒时间
	private String shutDownTime="24:00";//客户端关闭时间
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
	public String getUpdate_Time_now() {
		return update_Time_now;
	}
	public void setUpdate_Time_now(String update_Time_now) {
		this.update_Time_now = update_Time_now;
	}
	public String getSynchronizationPlay() {
		return synchronizationPlay;
	}
	public void setSynchronizationPlay(String synchronizationPlay) {
		this.synchronizationPlay = synchronizationPlay;
	}
	public String getStartUpTime() {
		return startUpTime;
	}
	public void setStartUpTime(String startUpTime) {
		this.startUpTime = startUpTime;
	}
	public String getShutDownTime() {
		return shutDownTime;
	}
	public void setShutDownTime(String shutDownTime) {
		this.shutDownTime = shutDownTime;
	}
	
	
}
