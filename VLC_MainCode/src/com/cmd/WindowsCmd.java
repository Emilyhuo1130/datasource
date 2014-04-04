package com.cmd;

import java.io.IOException;

public class WindowsCmd {

	private static Process exec;

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		exec = Runtime.getRuntime().exec("cmd /c start D:/VideoLAN/VLC/vlc.exe");
		Thread.sleep(10000);
		System.out.println("------");
		exec.destroy();
	}

}
