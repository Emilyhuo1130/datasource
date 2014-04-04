package com.cmd;


/**linux*
 * 多线程保存多个录像机的视频到本地存储器上，保存录像的核心程序。
 * 随服务器启动后将不再停止。
 * 
 * */
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

public class VLC_Main {
	private ExecutorService threadpool=Executors.newFixedThreadPool(500);
	private static String user_home;
	public  VLC_Main(){
		Properties p=System.getProperties();
		user_home=p.getProperty("user.home");
	}
	
	public void start(String ip) {
		Handler hand=new Handler(ip);
		threadpool.execute(hand);
	}
	//多线程
	class Handler implements Runnable{
		private Process ps;
		private Timer  timer;
		private String ip;
		private Handler(String ip){
			this.ip=ip;
		}
		
		public void  run(){
			timer =new Timer();
			final SimpleDateFormat sdf=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			boolean create=findFloder(ip);//查找是否存在文件夹
			if(create){
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						try {
							if(ps!=null){
								System.out.println("ps is not null");
								ps.destroy();
							}else{
								System.out.println("ps is null");
							}
							Thread.sleep(1);
							String fileName=ip+":"+sdf.format(new Date())+".mp4";
							String file_Path=user_home+"/all_file_pack/"+ip+"/"+fileName;
							ps=Runtime.getRuntime().exec("vlc rtsp://INFINOVA:INFINOVA@"+ip+"/1/h264major :sout=#file{dst="+file_Path+"} :sout-keep");
							System.out.println("vlc rtsp://INFINOVA:INFINOVA@"+ip+"/1/h264major :sout=#file{dst="+file_Path+"} :sout-keep");
						} catch (IOException e) {
							e.printStackTrace();
							timer.cancel();
						} catch (InterruptedException e) {
							e.printStackTrace();
							timer.cancel();
						}
						
					}
				}, 0,15000);
			
			}else{
				System.out.println("创建文件夹失败，可能是因为没有权限！");
			}
				
			
		}
		
		
	}
	//检测有没有文件夹，没有文件夹就创建新的文件夹/userhome/all_file_pack/192.168.2.147/*.mp4
	public static boolean findFloder(String ip){
		File f=new File(user_home+"/all_file_pack");
		boolean b=false;
		for(File f2:f.listFiles()){
			if(f2.getName().equals(ip)){
				b=true;
				break;
			}else{
				File mknewFlder=new File(user_home+"/all_file_pack/"+ip);
				if(mknewFlder.mkdirs()){
					b=true;
				}else{
					b=false;
				}
			}
		}
		return b;
		
	}
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		VLC_Main server=new VLC_Main();
		server.start("192.168.2.126");
		/*Thread.sleep(5000);
		server.start();*/
	}

}

