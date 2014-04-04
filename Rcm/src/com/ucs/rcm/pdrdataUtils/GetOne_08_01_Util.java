package com.ucs.rcm.pdrdataUtils;

public class GetOne_08_01_Util {

	public static boolean AllofPdrData_10(KSKPointInfo dbzl_vol, String string,
			int i, int j) {
		float[] values=dbzl_vol.getValues();
		boolean flag=true;
		for(int a=6;a<=22;a++){
			if(values[a]>10.0){
				flag= false;
				break;
			}
		}
		return flag;
	}

	public static boolean AllofPdrData_25(KSKPointInfo dbzl_vol, String string,
			int i, int j) {
		float[] values=dbzl_vol.getValues();
		boolean flag=true;
		for(int a=6;a<=22;a++){
			if(values[a]<25.0){
				flag= false;
				break;
			}
		}
		return flag;
	}

	public static boolean AllofPdrData_45(KSKPointInfo dbzl_vol, String string,
			int i, int j) {
		float[] values=dbzl_vol.getValues();
		boolean flag=true;
		for(int a=6;a<=22;a++){
			if(values[a]>45.0){
				flag= false;
				break;
			}
		}
		return flag;
	}

	public static boolean AllofPdrData_30(KSKPointInfo dbjl_vol, String string,
			int i, int j) {
		float[] values=dbjl_vol.getValues();
		boolean flag=true;
		for(int a=6;a<=22;a++){
			if(values[a]>30.0){
				flag= false;
				break;
			}
		}
		return flag;
	}

	public static boolean AllofPdrData_65(KSKPointInfo dbjl_vol, String string,
			int i, int j) {
		float[] values=dbjl_vol.getValues();
		boolean flag=true;
		for(int a=6;a<=22;a++){
			if(values[a]<65.0){
				flag= false;
				break;
			}
		}
		return flag;
	}

	public static boolean AllofPdrData_85(KSKPointInfo dbjl_vol, String string,
			int i, int j) {
		float[] values=dbjl_vol.getValues();
		boolean flag=true;
		for(int a=6;a<=22;a++){
			if(values[a]>85.0){
				flag= false;
				break;
			}
		}
		return flag;
	}

	public static boolean AllofPdrData_90(KSKPointInfo dbjl_vol, String string,
			int i, int j) {
		float[] values=dbjl_vol.getValues();
		boolean flag=true;
		for(int a=6;a<=22;a++){
			if(values[a]<90.0){
				flag= false;
				break;
			}
		}
		return flag;
	}

}
