package com.think.arrayListTest;

import java.util.List;



public class Hello<T,E>{
	public final T a;
	public final E b;
	

	public T getA() {
		return a;
	}



	public E getB() {
		return b;
	}



	public Hello(T a,E b){
		this.a=a;
		this.b=b;
	}

	

	public static void main(String[] args){
		Hello<Person,String> h=new Hello<Person,String>(new Person(), "44442");
		h.getA().getName();
	}
	
	
	
}
