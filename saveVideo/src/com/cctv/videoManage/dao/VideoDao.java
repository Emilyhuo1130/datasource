package com.cctv.videoManage.dao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.cctv.videoManage.face.VideoFace;


@Repository("VideoDao")
public class VideoDao implements VideoFace {
	private static Timer timer;
	private static String videoName=null;
	private static String saveAddress;
	private ExecutorService threadpool=Executors.newFixedThreadPool(300);;//线程池
	Logger log=Logger.getLogger(VideoDao.class);
	
	public boolean start(final String ip, String address) {
		Process ps=null;
		Handler hand=new Handler(ps,ip);
		threadpool.execute(hand);
		return true;
		
	}
	//多线程
	class Handler implements Runnable{
		final SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		private Process ps;
		private String ip;
		Handler (Process ps,String ip){
			this.ps=ps;
			this.ip=ip;
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
						Thread.sleep(1);
						videoName=sdf.format(new Date())+".mp4";
						saveAddress="dst=/home/zhang/upload/"+videoName;
						String start="cvlc rtsp://INFINOVA:INFINOVA@"+ip+"/1/h264major :sout=#file{"+saveAddress+"} :sout-keep";
						Runtime.getRuntime().exec(start);
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
				}
			}, 0, 10000);
		}
	}
	
	
	
	
	
	
	

	public void cmdTest(String cmd){
		try{
			Runtime.getRuntime().exec(cmd);
		}catch(Exception e){
			System.out.println("cmd 命令出错！");
			timer.cancel();
		}
	}


	@Override
	public boolean end() {
		// TODO Auto-generated method stub
		return false;
	}
	



	
}
