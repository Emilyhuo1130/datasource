package com.ucs.gk.response;

import java.util.List;


public class ResConfig_Pis {
	private String local_ip="";//客户端IP
	private String client_Id="";//客户端id（区位号）
	private List<String> pic_mNames;//多幅
	private List<String> pic_m_image_urls;//多幅图片路径
	private List<String> pic_sNames;//单幅
	private List<String> pic_s_image_urls;//单幅图片路径
	private List<String> viedoNames;//视频名字
	
	public List<String> getViedoNames() {
		return viedoNames;
	}
	public void setViedoNames(List<String> viedoNames) {
		this.viedoNames = viedoNames;
	}

	public String getLocal_ip() {
		return local_ip;
	}
	public List<String> getPic_m_image_urls() {
		return pic_m_image_urls;
	}
	public void setPic_m_image_urls(List<String> pic_m_image_urls) {
		this.pic_m_image_urls = pic_m_image_urls;
	}
	public List<String> getPic_s_image_urls() {
		return pic_s_image_urls;
	}
	public void setPic_s_image_urls(List<String> pic_s_image_urls) {
		this.pic_s_image_urls = pic_s_image_urls;
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
	public List<String> getPic_mNames() {
		return pic_mNames;
	}
	public void setPic_mNames(List<String> pic_mNames) {
		this.pic_mNames = pic_mNames;
	}
	
	public List<String> getPic_sNames() {
		return pic_sNames;
	}
	public void setPic_sNames(List<String> pic_sNames) {
		this.pic_sNames = pic_sNames;
	}
	
}
