package com.think.extendtest;

public class AChildren extends AFather{
	private final int i;
	static {
		System.out.println("a Children static ");
		new AFather("zhang");
		
	}
	public AChildren(){
		i=3;
		System.out.println("a children gou zao qi ");
		test();
		System.out.println(i);
		new Test2();
		
	}
	protected  void test(){System.out.println("children test-------------");};
	
	public void getPassWord(){System.out.println("children private getPassWord");}
	class Test2{
		public Test2(){System.out.println("children test00000000000");};
	}
}
