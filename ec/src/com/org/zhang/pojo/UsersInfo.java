package com.org.zhang.pojo;
/**
 * 对应userinfo表，
 * power password securitycode 不显示
 * 
 * */
public class UsersInfo {
	private int id;
	private String userName;
	private String userPassWord;
	private int userAge;//用户年龄
	private String sex;
	private String inProvince;//所在省份
	private String inCity;//所在城市
	private String nowJob;//学生党，上班族，自由职业者，不上班
	private int inComeFromJob;//工资收入/获取的生活费
	private int inComeFromOther;//其他收入
	private int oneDayinComeFromfrined;//某天从朋友拿得到的贺礼
	private int oneDayinComeFromLottery;//某天意外中彩票
	private int oneDayinComeFromFund;//基金/分红收入
	private int oneDayinComeFromOther;//其他收入
	
	public String getUserPassWord() {
		return userPassWord;
	}
	public void setUserPassWord(String userPassWord) {
		this.userPassWord = userPassWord;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getInProvince() {
		return inProvince;
	}
	public void setInProvince(String inProvince) {
		this.inProvince = inProvince;
	}
	public String getInCity() {
		return inCity;
	}
	public void setInCity(String inCity) {
		this.inCity = inCity;
	}
	public String getNowJob() {
		return nowJob;
	}
	public void setNowJob(String nowJob) {
		this.nowJob = nowJob;
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
