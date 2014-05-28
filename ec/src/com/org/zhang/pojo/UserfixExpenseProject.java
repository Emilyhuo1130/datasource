package com.org.zhang.pojo;
/**
 * 
 *用户每月固定消费项目
 *
 * @author Administrator
 *
 */
public class UserfixExpenseProject {
	private int id;
	private String userName;
	private int userAge;
	private String sex;
	private String inProvince;
	private String inCity;
	private int rent;//房租
	private int loan;//贷款
	private int utilities;//水电天然气费
	private int theBusFee;//上下班公交费/汽油费
	private int boardWages;//月固定伙食费
	private int other;//其他费用
	
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
	public int getRent() {
		return rent;
	}
	public void setRent(int rent) {
		this.rent = rent;
	}
	public int getLoan() {
		return loan;
	}
	public void setLoan(int loan) {
		this.loan = loan;
	}
	public int getUtilities() {
		return utilities;
	}
	public void setUtilities(int utilities) {
		this.utilities = utilities;
	}
	public int getTheBusFee() {
		return theBusFee;
	}
	public void setTheBusFee(int theBusFee) {
		this.theBusFee = theBusFee;
	}
	public int getBoardWages() {
		return boardWages;
	}
	public void setBoardWages(int boardWages) {
		this.boardWages = boardWages;
	}
	public int getOther() {
		return other;
	}
	public void setOther(int other) {
		this.other = other;
	}
	
	
}
