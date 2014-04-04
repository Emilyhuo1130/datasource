package com.ucs.gk.bo.client;

import java.util.ArrayList;
import java.util.List;

//联动
public class Pic_Linkage {
	private String linkageCode;//联动编码
	private String multiScreenEffect;//联动效果 0,1,2
	private String videoName=null;
	private String synchronizationPlay;//何时同步播放
	private int time;//时长
	private  List<LinkageInfo_Single> linkInfoList=new ArrayList<LinkageInfo_Single>();//选择联动机器
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getLinkageCode() {
		return linkageCode;
	}
	public void setLinkageCode(String linkageCode) {
		this.linkageCode = linkageCode;
	}
	public String getMultiScreenEffect() {
		return multiScreenEffect;
	}
	public void setMultiScreenEffect(String multiScreenEffect) {
		this.multiScreenEffect = multiScreenEffect;
	}
	public List<LinkageInfo_Single> getLinkInfoList() {
		return linkInfoList;
	}
	public void setLinkInfoList(List<LinkageInfo_Single> linkInfoList) {
		this.linkInfoList = linkInfoList;
	}
	public String getSynchronizationPlay() {
		return synchronizationPlay;
	}
	public void setSynchronizationPlay(String synchronizationPlay) {
		this.synchronizationPlay = synchronizationPlay;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
	
}
