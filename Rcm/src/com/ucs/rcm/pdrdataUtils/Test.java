package com.ucs.rcm.pdrdataUtils;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="10.31.03.36.001";
		System.out.println(str.substring(3,5));
		String st="10.xx.02.53.001";
		System.out.println(st.replace("xx", str.substring(3,5)));
	}

}
