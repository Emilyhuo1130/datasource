package com.think.other;

import com.think.other.inter.PublicPrivateInterface;

public class PublicPrivate implements PublicPrivateInterface{
	public String name;
	public static String password;
	private String user;
	protected String pw;
	
	
	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		PublicPrivate.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void get(int i){
		
	}
	
	@SuppressWarnings("unused")
	private void get(String i){
		
	}
	protected void get(boolean b){
		
	}
	/**static **/
	public static void get2_public(int i){
		
	}
	
	
	private static  void get2_private(String i){
		
	}
	protected static void get2_protected(boolean b){
		
	}
	public static void main(String[] args) {
		get2_private("");
		PublicPrivate.get2_private("");
	}

	
}
