package com.think.arraysTest;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ArraysTest2 implements Runnable{
	private ExecutorService threadpool=Executors.newFixedThreadPool(5);
	 String name="zhang";
	public static void main(String[] args) {
		ArraysTest2 a=new ArraysTest2();
		Thread ta=new Thread(a,"thread-1");
		Thread tb=new Thread(a,"thread-2");
		ta.start();
		
		tb.start();
		
		
		
		 
		
		
	}
	
	synchronized
	public void sss(){
		System.out.println("something");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0;i<3;i++){
			System.out.println(Thread.currentThread().getName());
			sss();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
}
