package com.ucs.rcm.pdrdataUtils;

import java.util.List;

public class GetOne_03_04_Util {

	public static boolean getpower(List<PointInfo> powinfos) {
		String flag1=powinfos.get(0).getValue1();
		String flag2=powinfos.get(1).getValue1();
		boolean b=false;
		if((flag1.equals("0")&&(flag2.equals("0")))){
			b=true;
		}else {
			b=false;
		}
		return b;
	}

	public static boolean getbooleanem_di3040(PointInfo point1) {
		String flag=point1.getValue1();
		boolean b=false;
		if(flag.equals("1")){
			b=true;
		}else if(flag.equals("0")){
			b=false;
		}
		return b;
	}

	public static boolean getbooleanem_di3043(PointInfo point2) {
		String flag=point2.getValue1();
		boolean b=false;
		if(flag.equals("1")){
			b=true;
		}else if(flag.equals("0")){
			b=false;
		}
		return b;
	}

	public static boolean getbooleanem_di3044(PointInfo point3) {
		String flag=point3.getValue1();
		boolean b=false;
		if(flag.equals("1")){
			b=true;
		}else if(flag.equals("0")){
			b=false;
		}
		return b;
	}

	public static boolean getbooleanem_di3045(PointInfo point4) {
		String flag=point4.getValue1();
		boolean b=false;
		if(flag.equals("1")){
			b=true;
		}else if(flag.equals("0")){
			b=false;
		}
		return b;
	}

	

}
