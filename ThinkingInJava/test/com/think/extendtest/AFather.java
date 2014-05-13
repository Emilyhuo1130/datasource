package com.think.extendtest;

public  class AFather {
	static {
		System.out.println("a AFather static ");
	}
	public AFather(){
		System.out.println("a father gou zao qi ");
	}
	public AFather(String name){
		System.out.print(name);
		System.out.println("a father gou zao qi zhang");
	}
	protected void test(){System.out.println("father test");};
	public final void getName(){System.out.println("final test");}
	private void getPassWord(){System.out.println("father private getPassWord");}
}
