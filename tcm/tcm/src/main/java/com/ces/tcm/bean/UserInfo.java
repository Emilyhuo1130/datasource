package com.ces.tcm.bean;

public class UserInfo {
	private String name;
	private int age;
	private String infoid;
	public UserInfo(String name,int age,String infoid){
		this.name=name;this.age=age;
		this.infoid=infoid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getInfoid() {
		return infoid;
	}
	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}
	
}
