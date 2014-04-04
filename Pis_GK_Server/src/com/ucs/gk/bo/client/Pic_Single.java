package com.ucs.gk.bo.client;
//单幅特效
public class Pic_Single {
	private int sequence_number=0;//播放序号
	private String picIn="";//切入
	private String picout="";//切出
	private String resFolder="";//图片路径
	private boolean random=false;//随机播放
	private  int time=10;//演示时间
	
	
	public int getSequence_number() {
		return sequence_number;
	}
	public void setSequence_number(int sequence_number) {
		this.sequence_number = sequence_number;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getPicIn() {
		return picIn;
	}
	public void setPicIn(String picIn) {
		this.picIn = picIn;
	}
	public String getPicout() {
		return picout;
	}
	public void setPicout(String picout) {
		this.picout = picout;
	}
	public String getResFolder() {
		return resFolder;
	}
	public void setResFolder(String resFolder) {
		this.resFolder = resFolder;
	}
	public boolean isRandom() {
		return random;
	}
	public void setRandom(boolean random) {
		this.random = random;
	}
	

}
