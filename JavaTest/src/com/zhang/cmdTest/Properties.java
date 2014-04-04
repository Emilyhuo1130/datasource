package com.zhang.cmdTest;

public class Properties {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		java.util.Properties pro=System.getProperties();
		System.out.println(pro.getProperty("os.arch")+"   "+pro.getProperty("os.name"));
	}

}
