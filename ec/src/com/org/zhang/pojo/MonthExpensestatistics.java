package com.org.zhang.pojo;
/****
 * 用户每月消费统计
 * 
 * */
public class MonthExpensestatistics {
	private int id;
	private String userName;
	private  int userAge;
	private String sex;//man，woman
	private String inProvince;
	private String inCity;
	private String thisMonth;
	private  int rent;//房租
	private  int loan;//贷款
	private  int utilities;//水电费
	private  int theBusFee;//交通费
	private  int boardWages;//固定伙食费
	private  int other;//其他费用
	private  int everyDayBoardWages;//一个月的伙食费总和
	private  int giftCost;//一个月的礼品费用总和
	private  int travelCost;//一个月的旅游支出总和
	private  int otherTheBusFee;//一个月的额外的交通费总和
	private  int friendCost;//一个月的走亲访友支出总和
	private  int otherSocialCost;//一个月的其他社交费总和
	private  int cosmeticCost;//一个月的化妆品费总和
	private  int hygienicProductsCost;//一个月的个人卫生用品费总和
	private  int articlesOfDailyUseCost;//一个月的生活用品费总和
	private  int officeSuppliesCost;//一个月的办公用品费总和
	private  int otherDailySuppliesCost;//一个月的其他日常消费品费总和
	private  int electronCost;//一个月的电子消费品总和
	private  int otherCost;//一个月的其他费用总和
	
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
	public String getThisMonth() {
		return thisMonth;
	}
	public void setThisMonth(String thisMonth) {
		this.thisMonth = thisMonth;
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
	public int getEveryDayBoardWages() {
		return everyDayBoardWages;
	}
	public void setEveryDayBoardWages(int everyDayBoardWages) {
		this.everyDayBoardWages = everyDayBoardWages;
	}
	public int getGiftCost() {
		return giftCost;
	}
	public void setGiftCost(int giftCost) {
		this.giftCost = giftCost;
	}
	public int getTravelCost() {
		return travelCost;
	}
	public void setTravelCost(int travelCost) {
		this.travelCost = travelCost;
	}
	public int getOtherTheBusFee() {
		return otherTheBusFee;
	}
	public void setOtherTheBusFee(int otherTheBusFee) {
		this.otherTheBusFee = otherTheBusFee;
	}
	public int getFriendCost() {
		return friendCost;
	}
	public void setFriendCost(int friendCost) {
		this.friendCost = friendCost;
	}
	public int getOtherSocialCost() {
		return otherSocialCost;
	}
	public void setOtherSocialCost(int otherSocialCost) {
		this.otherSocialCost = otherSocialCost;
	}
	public int getCosmeticCost() {
		return cosmeticCost;
	}
	public void setCosmeticCost(int cosmeticCost) {
		this.cosmeticCost = cosmeticCost;
	}
	public int getHygienicProductsCost() {
		return hygienicProductsCost;
	}
	public void setHygienicProductsCost(int hygienicProductsCost) {
		this.hygienicProductsCost = hygienicProductsCost;
	}
	public int getArticlesOfDailyUseCost() {
		return articlesOfDailyUseCost;
	}
	public void setArticlesOfDailyUseCost(int articlesOfDailyUseCost) {
		this.articlesOfDailyUseCost = articlesOfDailyUseCost;
	}
	public int getOfficeSuppliesCost() {
		return officeSuppliesCost;
	}
	public void setOfficeSuppliesCost(int officeSuppliesCost) {
		this.officeSuppliesCost = officeSuppliesCost;
	}
	public int getOtherDailySuppliesCost() {
		return otherDailySuppliesCost;
	}
	public void setOtherDailySuppliesCost(int otherDailySuppliesCost) {
		this.otherDailySuppliesCost = otherDailySuppliesCost;
	}
	public int getElectronCost() {
		return electronCost;
	}
	public void setElectronCost(int electronCost) {
		this.electronCost = electronCost;
	}
	public int getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(int otherCost) {
		this.otherCost = otherCost;
	}
	
}
