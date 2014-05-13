package com.think.arraysTest;

public class BankTest implements Runnable{

	/**
	 * @param args
	 */
	private static int money = 3000;//模拟账户余额
	public BankCard bc=new BankCard();
	public static void main(String[] args) {
		BankTest b=new BankTest();
		Thread wife=new Thread(b);
		Thread husband =new Thread(b);
		wife.setName("wife");
		husband.setName("husband");
		wife.start();
		husband.start();
		
	}

	
	public void run() {
		// TODO Auto-generated method stub
		bc.getMoney(Thread.currentThread().getName(), 2000);
	}
	
	static class BankCard{
		
		 public synchronized void getMoney(String name,int m){
			 try{
				 Thread.sleep(500);
			 }catch(Exception e){
				 
			 }
			 if(money>m){
				 System.out.format("%s 你取走了 %s 钱", name,m+"");
				 System.out.println("\n");
				 money=money-m;
			 }else{
				 System.out.println("你的与余额不足！");
			 }
		 }
	}
	

}
