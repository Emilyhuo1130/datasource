package com.zhang.cmdTest;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class VlcTest {
	private static Process ps=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timer timer =new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(ps!=null){
					System.out.println("ps is not null");
					ps.destroy();
				}else{
					System.out.println("ps is null");
				}
				try {
					Thread.sleep(1);
					ps=Runtime.getRuntime().exec("cmd /k start D:/VideoLAN/VLC/vlc.exe rtsp://INFINOVA:INFINOVA@192.168.2.127/1/h264major :sout=#file{dst=C:/Users/kathy/Desktop/test.mp4,30,no-overwrite} :sout-keep");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		}, 0, 15000);
	}

}
