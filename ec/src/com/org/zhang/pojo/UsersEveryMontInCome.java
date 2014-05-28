package com.org.zhang.pojo;
/**
 * 对应数据库 usersEveryMontInCome表
 * 某人每个月收入信息
 * */
public class UsersEveryMontInCome {
	 private int id ;
	 private String userName;
	 private int userAge;
	 private String sex;
	 private String inProvince;//省份
	 private String inCity;//城市
	 private String nowJob;//学生党，上班族，自由职业者，不上班
	 private int inOneYear;//年份
	 private int inOneMonth;//月份
	 private int thisMonthInCome;//这个月的收入统计和
	 
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
	public int getInOneYear() {
		return inOneYear;
	}
	public void setInOneYear(int inOneYear) {
		this.inOneYear = inOneYear;
	}
	public int getInOneMonth() {
		return inOneMonth;
	}
	public void setInOneMonth(int inOneMonth) {
		this.inOneMonth = inOneMonth;
	}
	public int getThisMonthInCome() {
		return thisMonthInCome;
	}
	public void setThisMonthInCome(int thisMonthInCome) {
		this.thisMonthInCome = thisMonthInCome;
	}
	
	
}
