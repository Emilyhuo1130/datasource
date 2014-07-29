package com.java7.facedi;

public class 接口的测试 {

	public static void main(String[] args) {
		TestFace face=new TestImp();
		get(face);

	}
	public static void get(TestFace face){
		System.out.println(face.getName());
	}

}
