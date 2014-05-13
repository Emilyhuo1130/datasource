package com.think.arraysTest;

public class BankTest implements Runnable{

	/**
	 * @param args
	 */
	private static int money = 3000;//ģ���˻����
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
				 System.out.format("%s ��ȡ���� %s Ǯ", name,m+"");
				 System.out.println("\n");
				 money=money-m;
			 }else{
				 System.out.println("��������㣡");
			 }
		 }
	}
	

}
