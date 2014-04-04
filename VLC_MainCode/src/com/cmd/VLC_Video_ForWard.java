package com.cmd;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;



public class VLC_Video_ForWard {

	/**
	 * @param args
	 * @throws IOException 
	 */
	private static Process p=null;
	private String localIP="192.168.2.146";
	private static String recoderIP=null;
	
	
	public  VLC_Video_ForWard(){
		Properties p=new Properties();
		try {
			p.load(new FileInputStream(System.getProperty("user.home")+"/server.properties"));
			localIP=p.getProperty("localhost.ip");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public boolean setForWardUrl(String ip){
		try{
			p.destroy();
		}finally{
			recoderIP=ip;
			//转发到这个地址，可以在192.168.2.146:8554/video获取rstp流
			try {
				p = Runtime.getRuntime().exec("vlc rtsp://INFINOVA:INFINOVA@"+recoderIP+"/1/h264major :sout=#rtp{sdp=rtsp://"+localIP+":8554/video} :sout-all :sout-keep");
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	//关闭转发
	public boolean endForWard(){
		if(recoderIP!=null){
			p.destroy();
			p=null;
			recoderIP=null;
		}
		return true;
	}
	
	
	
	

}
