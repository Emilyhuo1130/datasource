package com.think.other;

import com.think.other.inter.PublicPrivateInterface;
import com.think.other.inter.TestInterface;

public class  TestPublicPrivate {

	static {
		int b;
		
	}
	/**
	 * @param args
	 */
	
	public static int i;
	public int j=30;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PublicPrivate.get2_protected(false);
		PublicPrivate sun=new PublicPrivate();
		String pw = sun.pw;
		PublicPrivateInterface face=new PublicPrivate();
		face.get(9);
		
		
		Children chi=new Children();
		String name = chi.name;
	
		TestInterface face2=new PublicPrivateChildren();
		testreturn();
		
	}
	public static void testreturn(){
		int a=100;
		if(a==100){
			System.out.println("100");
			return;
		}
		System.out.println("-----------");
		//return;//½áÊø·½·¨
		
	}
	

}
