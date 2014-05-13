package com.think.other;

import com.sun.xml.internal.bind.util.Which;

public class StaticTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestPublicPrivate t1=new TestPublicPrivate();
		TestPublicPrivate t2=new TestPublicPrivate();
		TestPublicPrivate.i++;
		t1.j++;
		System.out.println(t1.i+"    :"+t1.j);
		System.out.println(t2.i+"    :"+t2.j);
		System.getProperties().list(System.out);
		System.out.println(1<2?"000":4);
		int a=1;
		switch (a) {
		case 0:
			System.out.println("000000000");
			break;
		case 1:
			System.out.println("11111111");
			break;
		default:
			System.out.println("other");
			break;
		}
		f1(2L);
	}
	public static void f1(int a){System.out.println("int a");}
	public static void f1(long a){System.out.println("long a");}

}
