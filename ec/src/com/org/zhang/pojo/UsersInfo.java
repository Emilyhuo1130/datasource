package com.org.zhang.pojo;
/**
 * 对应userinfo表，
 * power password securitycode 不显示
 * 
 * */
public class UsersInfo extends BaseUserInfo{
	
	private int inComeFromJob;//工资收入/获取的生活费
	private int inComeFromOther;//其他收入
	private int oneDayinComeFromfrined;//某天从朋友拿得到的贺礼
	private int oneDayinComeFromLottery;//某天意外中彩票
	private int oneDayinComeFromFund;//基金/分红收入
	private int oneDayinComeFromOther;//其他收入
	private String userPassWord;
	
	public String getUserPassWord() {
		return userPassWord;
	}
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	public int getInComeFromJob() {
		return inComeFromJob;
	}
	public void setInComeFromJob(int inComeFromJob) {
		this.inComeFromJob = inComeFromJob;
	}
	public int getInComeFromOther() {
		return inComeFromOther;
	}
	public void setInComeFromOther(int inComeFromOther) {
		this.inComeFromOther = inComeFromOther;
	}
	public int getOneDayinComeFromfrined() {
		return oneDayinComeFromfrined;
	}
	public void setOneDayinComeFromfrined(int oneDayinComeFromfrined) {
		this.oneDayinComeFromfrined = oneDayinComeFromfrined;
	}
	public int getOneDayinComeFromLottery() {
		return oneDayinComeFromLottery;
	}
	public void setOneDayinComeFromLottery(int oneDayinComeFromLottery) {
		this.oneDayinComeFromLottery = oneDayinComeFromLottery;
	}
	public int getOneDayinComeFromFund() {
		return oneDayinComeFromFund;
	}
	public void setOneDayinComeFromFund(int oneDayinComeFromFund) {
		this.oneDayinComeFromFund = oneDayinComeFromFund;
	}
	public int getOneDayinComeFromOther() {
		return oneDayinComeFromOther;
	}
	public void setOneDayinComeFromOther(int oneDayinComeFromOther) {
		this.oneDayinComeFromOther = oneDayinComeFromOther;
	}
	
}
