package com.ucs.rcm.pdrdataUtils;

public class GetOne_08_02_Util {

	public static boolean getbooleanem_S11A_H(PointInfo point1) {
		// TODO Auto-generated method stub
		String[] values=point1.getValues();
		boolean flag=true;
		for(int a=9;a<=11;a++){
			if(Integer.parseInt(values[a])!=1){
				flag= false;
				break;
			}
		}
		return flag;
	}

	public static boolean getbooleanem_T05A(PointInfo point2) {
		// TODO Auto-generated method stub
		String[] values=point2.getValues();
		boolean flag=true;
		for(int a=3;a<=8;a++){
			if(Integer.parseInt(values[a])!=0){
				flag= false;
				break;
			}
		}
		return flag;
	}

	public static boolean getbooleanem_T07A(PointInfo point3) {
		// TODO Auto-generated method stub
		String[] values=point3.getValues();
		boolean flag=true;
		for(int a=8;a<=11;a++){
			if(Integer.parseInt(values[a])!=0){
				flag= false;
				break;
			}
		}
		return flag;
	}

}
