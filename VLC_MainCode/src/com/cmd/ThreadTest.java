package com.cmd;



public class ThreadTest {

	public static void main(String[] args) {
		vlcstart v=new vlcstart();
		v.setDaemon(true);
		v.start();
		int i=0;
		while(true){
			if(i==500000){
				break;
			}
			i++;
		}
	}
	
}

class vlcstart extends Thread{
	public void run(){
		int i=0;
		while(true){
			System.out.println(i+"-------------");
			i++;
		}
	}
}