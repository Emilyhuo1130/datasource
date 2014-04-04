package com.zhang.cmdTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerCmd {

	/**
	 * @param args
	 */
	private static Timer timer=new Timer();
	private static String videoName=null;
	private static String saveAddress;
	private static String ip;
	public static void main(String[] args) {
		final SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		ip="192.168.2.125";
		timer.schedule(new TimerTask(){
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String end="cmd /k start taskkill /IM vlc.exe";
				cmdTest(end);
				videoName=sdf.format(new Date())+".mp4";
				saveAddress="dst=C:/Users/kathy/Desktop/"+videoName+",no-overwrite";
				String start="cmd /k start D:/VideoLAN/VLC/vlc.exe rtsp://INFINOVA:INFINOVA@"+ip+"/1/h264major :sout=#file{"+saveAddress+"} :sout-keep";
				System.out.println(start);
				cmdTest(start);
			}
			
		}, 100,60000);

	}
	public static void cmdTest(String cmd){
		try{
			Runtime.getRuntime().exec(cmd);
		}catch(Exception e){
			System.out.println("cmd 命令出错！");
		}
	}

}
