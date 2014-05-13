package com.think.arraysTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RunnableTest {
	private ExecutorService threadpool=Executors.newFixedThreadPool(2);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new RunnableTest().test();
	}
	
	public void test(){
		
		threadpool.execute(new RunTest());
		
		/*threadpool.execute(new RunTest());
		threadpool.execute(new RunTest());
		
		threadpool.shutdown();*/
		Future<String> submit = threadpool.submit(new CallTest());
		try {
			System.out.println(submit.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			threadpool.shutdown();
		}
		
		
	}
	class RunTest implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<4;i++){
				try {
					Thread.sleep(1000);
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("0000000000000");
				System.out.println(Thread.currentThread().getName());
			}
		}
		
	}
	
	class CallTest implements Callable<String>{

		@Override
		public String call() throws Exception {
			// TODO Auto-generated method stub
			
			return "4444444444444";
		}
		
	}

}
