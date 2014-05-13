package com.think.extendtest;

public class NewTest {
	public static void main(String[] args) {
		new AChildren();
		new AChildren();
		System.out.println("------------------");
		new AFather();
		new AChildren().test();
		new AChildren().getName();
		int a=9;
		new NewTest().test(a);
	}
	public void test(int i){System.out.println("int i");};
	public void test(long i){System.out.println("long i");};
	public void test(Object j){System.out.println("Object i");};
	public void test(Integer j){System.out.println("integer i");};
	public void test(Long j){System.out.println("Long i");};
}
