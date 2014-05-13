package com.think.other;

public class Children extends PublicPrivate{
	public void ss(){
		//get2_private("");private 是无法被子类访问的
		String password2 = PublicPrivate.password;
	}
}
