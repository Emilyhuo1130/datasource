package com.ucs.rcm.pdrdataUtils;

public class GetOne_13_10_Util {

	public static boolean getboolean(PointInfo p1, PointInfo p2) {
		// TODO Auto-generated method stub
		if((Integer.parseInt(p1.getValue1())+Integer.parseInt(p2.getValue1()))>0){
			return false;
		}else{
			return true;
		}
	}
	
}
