package com.zhang.cmdTest;

import org.junit.Test;

public class CMDTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 new CMDTest().cmd();  
	}
	 public void execCommand(String[] arstringCommand) {  
	        for (int i = 0; i < arstringCommand.length; i++) {  
	            System.out.print(arstringCommand[i] + " ");  
	        }  
	        try {  
	            Runtime.getRuntime().exec(arstringCommand);  
	  
	        } catch (Exception e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }  
	    public void execCommand(String arstringCommand) {  
	        try {  
	            Runtime.getRuntime().exec(arstringCommand);  
	              
	        } catch (Exception e) {  
	            System.out.println(e.getMessage());  
	        }  
	    }  
	  
	    public void cmd(){  
	        //打开记算器  
	        String[] arstringCommand = new String[] {  
	                "cmd ",   
	                "/k",  
	                "start", // cmd Shell命令  
	                "calc"  
	        };  
	       // execCommand(arstringCommand);  
	     
	       String cmd = "cmd /k start D:/VideoLAN/VLC/vlc.exe rtsp://INFINOVA:INFINOVA@192.168.2.125/1/h264major :sout=#file{dst=C:/Users/kathy/Desktop/test.mp4,no-overwrite} :sout-keep";  
	      //  String cmd="cmd /k start D:/WOTBox/WOTBox.exe";
	        execCommand(cmd);  
	    } 
	    @Test
	    public void stop(){
	    	String stop="cmd /k start taskkill /IM vlc.exe";
	    	execCommand(stop);
	    }

}
