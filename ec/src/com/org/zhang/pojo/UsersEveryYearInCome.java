package com.org.zhang.pojo;

/**对应数据库UsersEveryYearInCome
 * 某人每年收入信息
 * 
 * */
public class UsersEveryYearInCome {
	private int id;
	private String userName;
	private int userAge;
	private String sex;
	private String inProvince;
	private String inCity;
	private int inOneYear;//在某一年
	private int thisYearInCome;//这一年的手如统计，统计来自每月收入
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public int getInOneYear() {
		return inOneYear;
	}
	public void setInOneYear(int inOneYear) {
		this.inOneYear = inOneYear;
	}
	public int getThisYearInCome() {
		return thisYearInCome;
	}
	public void setThisYearInCome(int thisYearInCome) {
		this.thisYearInCome = thisYearInCome;
	}
	
	
	
	
	
}
