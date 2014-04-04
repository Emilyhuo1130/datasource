package com.ucs.gk.bo;

public class ServerSetting {
	private String startUpTime="04:00:00";//客户端唤醒时间
	private String shutDownTime="24:00:00";//客户端关闭时间
	private String synchronizationPlay="1";//同步播放时间   1：整点    2：半点    3：一刻钟
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
	public String getSynchronizationPlay() {
		return synchronizationPlay;
	}
	public void setSynchronizationPlay(String synchronizationPlay) {
		this.synchronizationPlay = synchronizationPlay;
	}
	
}
