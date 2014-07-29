package concurrent.test.sutdy;

/**
 * @author Administrator
 *
 */
public class ThreadTest extends Thread {
	private  static int count=1000;
	public static void main(String[] args) throws InterruptedException {
		ThreadTest test=new ThreadTest();
		test.start();
		test=new ThreadTest();
		test.start();
	}
	
	public void run(){
		getCount();
	}
	
	synchronized
	public static void getCount(){
		count =count-100;
		System.out.println("取了100");
		System.out.println("还剩"+count);
	}
	

}
