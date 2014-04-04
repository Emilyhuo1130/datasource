package com.ucs.rcm.pdrdataUtils;

import java.util.List;

public class GetOne_03_36_Util {

	public static boolean getbooleanztl_em_di2579(List<PointInfo> powinfos) {
		// TODO Auto-generated method stub
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

	public static boolean getbooleanztl_em_di2580(PointInfo ztl_em_di2579) {
		// TODO Auto-generated method stub
		String flag=ztl_em_di2579.getValue1();
		boolean b=false;
		if(flag.equals("1")){
			b=true;
		}else if(flag.equals("0")){
			b=false;
		}
		return b;
	}

	public static boolean getbooleanztl_em_di2581(PointInfo ztl_em_di2579) {
		// TODO Auto-generated method stub
		String flag=ztl_em_di2579.getValue1();
		boolean b=false;
		if(flag.equals("1")){
			b=true;
		}else if(flag.equals("0")){
			b=false;
		}
		return b;
	}

	public static boolean getbooleanztl_em_di2582(PointInfo ztl_em_di2579) {
		// TODO Auto-generated method stub
		String flag=ztl_em_di2579.getValue1();
		boolean b=false;
		if(flag.equals("1")){
			b=true;
		}else if(flag.equals("0")){
			b=false;
		}
		return b;
	}

	public static boolean getbooleanztl_em_di2583(PointInfo ztl_em_di2579) {
		// TODO Auto-generated method stub
		String flag=ztl_em_di2579.getValue1();
		boolean b=false;
		if(flag.equals("1")){
			b=true;
		}else if(flag.equals("0")){
			b=false;
		}
		return b;
	}

}
