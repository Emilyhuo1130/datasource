package com.ucs.rcm.responseObj;


public class ResOperateindex {
	private int id;
	private String lineNo;
	private String healthindex;//健康指数
	private String leveloneCount;//一级报警数
	private String leveltwoCount;//二级报警数
	private String levelthreeCount;//三级报警数
	private String levelforeCount;//四级报警数
	private String time;//更新时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getHealthindex() {
		return healthindex;
	}
	public void setHealthindex(String healthindex) {
		this.healthindex = healthindex;
	}
	public String getLeveloneCount() {
		return leveloneCount;
	}
	public void setLeveloneCount(String leveloneCount) {
		this.leveloneCount = leveloneCount;
	}
	public String getLeveltwoCount() {
		return leveltwoCount;
	}
	public void setLeveltwoCount(String leveltwoCount) {
		this.leveltwoCount = leveltwoCount;
	}
	public String getLevelthreeCount() {
		return levelthreeCount;
	}
	public void setLevelthreeCount(String levelthreeCount) {
		this.levelthreeCount = levelthreeCount;
	}
	public String getLevelforeCount() {
		return levelforeCount;
	}
	public void setLevelforeCount(String levelforeCount) {
		this.levelforeCount = levelforeCount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
