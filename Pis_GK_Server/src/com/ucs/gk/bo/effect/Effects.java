package com.ucs.gk.bo.effect;

import java.util.List;

public class Effects {
	private List<Pic_info> multi_Effects;
	private List<Pic_info> single_picIn_Effects;
	private List<Pic_info> single_picout_Effects;
	private List<Pic_info> pic_Mulit_url;//多图特效文件路径
	private List<Pic_info> pic_Single_url;//单图特效文件路径
	private List<Pic_info> video_url;//视屏文件路径
	
	public List<Pic_info> getVideo_url() {
		return video_url;
	}
	public void setVideo_url(List<Pic_info> video_url) {
		this.video_url = video_url;
	}
	public List<Pic_info> getPic_Mulit_url() {
		return pic_Mulit_url;
	}
	public void setPic_Mulit_url(List<Pic_info> pic_Mulit_url) {
		this.pic_Mulit_url = pic_Mulit_url;
	}
	public List<Pic_info> getPic_Single_url() {
		return pic_Single_url;
	}
	public void setPic_Single_url(List<Pic_info> pic_Single_url) {
		this.pic_Single_url = pic_Single_url;
	}
	public List<Pic_info> getMulti_Effects() {
		return multi_Effects;
	}
	public void setMulti_Effects(List<Pic_info> multi_Effects) {
		this.multi_Effects = multi_Effects;
	}
	public List<Pic_info> getSingle_picIn_Effects() {
		return single_picIn_Effects;
	}
	public void setSingle_picIn_Effects(List<Pic_info> single_picIn_Effects) {
		this.single_picIn_Effects = single_picIn_Effects;
	}
	public List<Pic_info> getSingle_picout_Effects() {
		return single_picout_Effects;
	}
	public void setSingle_picout_Effects(List<Pic_info> single_picout_Effects) {
		this.single_picout_Effects = single_picout_Effects;
	}
	
	
	
	
}
