package com.cmd;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

public class TTTT {

	/**
	 * @param args
	 */
	private static  Process ps=null;
	public static void main(String[] args) throws IOException {
		Properties p=System.getProperties();
		System.out.println(p.getProperty("user.home"));
		String user_home=p.getProperty("user.home");
		File f=new File(user_home+"/all_file_pack");
		for(String f2:f.list()){
			System.out.println(f2);
		}
	}

}
