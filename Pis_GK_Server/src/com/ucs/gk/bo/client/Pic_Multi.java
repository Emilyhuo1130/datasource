package com.ucs.gk.bo.client;
//多幅特效
public class Pic_Multi {
		private int sequence_number=0;//播放序号
		private String picShow="";//特效类型
		private String resFolder="";//图片路径
		private boolean random=false;//随机播放
		private  int time=60;//演示时间
		
		
		public int getSequence_number() {
			return sequence_number;
		}
		public void setSequence_number(int sequence_number) {
			this.sequence_number = sequence_number;
		}
		public String getPicShow() {
			return picShow;
		}
		public void setPicShow(String picShow) {
			this.picShow = picShow;
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
		public int getTime() {
			return time;
		}
		public void setTime(int time) {
			this.time = time;
		}

	}

