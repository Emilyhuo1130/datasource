package com.zhang.cmdTest;


/**
 * 资源池的好处：
 * 1.资源数目可以得到控制，增加伸缩性
 * 2.资源都是readly状态，减小开销
 * 线程池是资源池的一种
 * Server端一定有：1线程池;2；出错重传，3；缓冲队列*/
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VLC_Thread {
	private ExecutorService threadpool;
	
	public void start() throws IOException{
		threadpool=Executors.newFixedThreadPool(100);//线程池
		//while(true){
			Process ps=null;
			Handler hand=new Handler(ps);
			threadpool.execute(hand);
			
		//}
	}
	//多线程
	class Handler implements Runnable{
		private Process ps;
		Handler (Process ps){
			this.ps=ps;
		}
		public void  run(){
			Timer timer =new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					if(ps!=null){
						ps.destroy();
						System.out.println("ps is not null");
					}else{
						System.out.println("ps is null");
					}
					try {
						ps=Runtime.getRuntime().exec("cmd /k start D:/VideoLAN/VLC/vlc.exe");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
				}
			}, 0, 10000);
		}
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		VLC_Thread server=new VLC_Thread();
		server.start();
		/*Thread.sleep(5000);
		server.start();*/
	}
	
	
	

}
