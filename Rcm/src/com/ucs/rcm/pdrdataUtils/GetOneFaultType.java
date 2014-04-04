
package com.ucs.rcm.pdrdataUtils;

import java.util.List;

public class GetOneFaultType {

	public static String get_08_01_faultType(List<KSKPointInfo> pointInfoList) {
		// TODO Auto-generated method stub
		String type=null;
		KSKPointInfo dbzl_vol=pointInfoList.get(0);//定位直流电压
		KSKPointInfo dbjl_vol=pointInfoList.get(1);//定位交流电压
		KSKPointInfo fbzl_vol=pointInfoList.get(2);//反位直流电压
		KSKPointInfo fbjl_vol=pointInfoList.get(3);//反位交流电压
		KSKPointInfo dbbs_stat=pointInfoList.get(4);//定位表示状态
		KSKPointInfo fbbs_stat=pointInfoList.get(5);//反位表示状态
		
		if(((dbbs_stat.getValues()[0])==1)&&((fbbs_stat.getValues()[0])==0)){
			type="定位";
		}else  if((dbbs_stat.getValues()[0]==0)&&(fbbs_stat.getValues()[0]==1)){
			type="反位";
		}
		boolean bAllDC10=false;
		boolean bAllDC25=false;
		boolean bAllDC45=false;
		
		boolean bAllAC30=false;
		boolean bAllAC65=false;
		boolean bAllAC85=false;
		boolean bAllAC90=false;
		
		if(type.equals("定位")){
			bAllDC10=GetOne_08_01_Util.AllofPdrData_10(dbzl_vol,"<=",6,10);
			bAllDC25=GetOne_08_01_Util.AllofPdrData_25(dbzl_vol,">=",6,10);
			bAllDC45=GetOne_08_01_Util.AllofPdrData_45(dbzl_vol,"<=",6,10);
		
			bAllAC30=GetOne_08_01_Util.AllofPdrData_30(dbjl_vol,"<=",6,10);
			bAllAC65=GetOne_08_01_Util.AllofPdrData_65(dbjl_vol,">=",6,10);
			bAllAC85=GetOne_08_01_Util.AllofPdrData_85(dbjl_vol,"<=",6,10);
			bAllAC90=GetOne_08_01_Util.AllofPdrData_90(dbjl_vol,">=",6,10);
		}else if(type.equals("反位")){
			bAllDC10=GetOne_08_01_Util.AllofPdrData_10(fbzl_vol,"<=",6,10);
			bAllDC25=GetOne_08_01_Util.AllofPdrData_25(fbzl_vol,">=",6,10);
			bAllDC45=GetOne_08_01_Util.AllofPdrData_45(fbzl_vol,"<=",6,10);
		
			bAllAC30=GetOne_08_01_Util.AllofPdrData_30(fbjl_vol,"<=",6,10);
			bAllAC65=GetOne_08_01_Util.AllofPdrData_65(fbjl_vol,">=",6,10);
			bAllAC85=GetOne_08_01_Util.AllofPdrData_85(fbjl_vol,"<=",6,10);
			bAllAC90=GetOne_08_01_Util.AllofPdrData_90(fbjl_vol,">=",6,10);
		}
		if((bAllDC10)&&(bAllAC30)){
			type=type+"表示回路故障";
		}else if(bAllDC10&&bAllAC90){
			type=type+"表示回路室外开路";
		}else if(bAllDC25&&bAllDC45&&bAllAC65&&bAllAC85){
			type=type+"表示回路线圈支路室内开路";
		}else{
			type=type+"数据缺失未能匹配到故障原因";
		}
		return type;
	}
	
	
	
	
/***隧道风机通风系统
 * @param powinfos **/
	public static String get_03_36_faultType(List<PointInfo> pointInfoList, List<PointInfo> powinfos) {
		// TODO Auto-generated method stub
		String type=null;
		
		PointInfo ztl_em_di2580 =pointInfoList.get(0);
		PointInfo ztl_em_di2581 =pointInfoList.get(1);
		PointInfo ztl_em_di2582 =pointInfoList.get(2);
		PointInfo ztl_em_di2583 =pointInfoList.get(3);
		
		boolean power=false;
		boolean em_di2580=false;
		boolean em_di2581=false;
		boolean em_di2582=false;
		boolean em_di2583=false;
		
		power=GetOne_03_36_Util.getbooleanztl_em_di2579(powinfos);
		em_di2580=GetOne_03_36_Util.getbooleanztl_em_di2580(ztl_em_di2580);
		em_di2581=GetOne_03_36_Util.getbooleanztl_em_di2581(ztl_em_di2581);
		em_di2582=GetOne_03_36_Util.getbooleanztl_em_di2582(ztl_em_di2582);
		em_di2583=GetOne_03_36_Util.getbooleanztl_em_di2583(ztl_em_di2583);
		
		if(power){
			type="供电电源失电";
		}else{
			if(em_di2581){
				type="过热报警";
			}else if(em_di2582){
				type="过载";
			}else if(em_di2583){
				type="过热跳闸";
			}else if(em_di2580){
				type="设备故障";
			}
				 
		}
		
		
		return type;
	}



	/****区间水泵**/
	public static String get_03_04_faultType(List<PointInfo> pointInfoList,
			List<PointInfo> powinfos) {
		// TODO Auto-generated method stub
		String type=null;
		
		PointInfo point1 =pointInfoList.get(0);
		PointInfo point2 =pointInfoList.get(1);
		PointInfo point3 =pointInfoList.get(2);
		PointInfo point4 =pointInfoList.get(3);
		
		boolean power=false;
		boolean em_di3040=false;
		boolean em_di3043=false;
		boolean em_di3044=false;
		boolean em_di3045=false;
		
		power=GetOne_03_04_Util.getpower(powinfos);
		em_di3040=GetOne_03_04_Util.getbooleanem_di3040(point1);
		em_di3043=GetOne_03_04_Util.getbooleanem_di3043(point2);
		em_di3044=GetOne_03_04_Util.getbooleanem_di3044(point3);
		em_di3045=GetOne_03_04_Util.getbooleanem_di3045(point4);
		
		if(power){
			type="供电电源失电";
		}else{
			if(em_di3043){
				type="设备故障";
			}else if(em_di3044){
				type="设备故障";
			}else if(em_di3045){
				type="高水位报警报警";
			}else{
				type="过载";
			}
				 
		}
		
		
		return type;
	}



/***信号机**/
	public static String get_08_02_faultType(List<PointInfo> pointInfoList) {
		
		String type=null;
		PointInfo point1 =pointInfoList.get(0);
		PointInfo point2 =pointInfoList.get(1);
		PointInfo point3 =pointInfoList.get(2);
		boolean S11A_H=false;
		boolean T05A=false;
		boolean T07A=false;
		S11A_H=GetOne_08_02_Util.getbooleanem_S11A_H(point1);
		T05A=GetOne_08_02_Util.getbooleanem_T05A(point2);
		T07A=GetOne_08_02_Util.getbooleanem_T07A(point3);
		
		if(S11A_H){
			if(T05A||T07A){
				type="列车信号非正常关闭报警";
			}
		}else{
			type="数据异常";
		}
		
		
		return type;
	}



	/***触网**/
	public static String get_14_101_faultType(List<PointInfo> pointInfoList) {
	// TODO Auto-generated method stub
		String type=null;
		String value=pointInfoList.get(0).getValue1();
		if(value.equals("0")){
			type="安全";
		}else if(value.equals("1")){//01
			type="拉出值超限";
		}else if(value.equals("2")){//02
			type="导高超限";
		}else if(value.equals("3")){//03
			type="磨耗异常";
		}else if(value.equals("4")){//04
			type="水平距离异常";
		}else if(value.equals("5")){//05
			type="接触线坡度异常";
		}else if(value.equals("6")){//06
			type="定位器坡度异常";
		}else if(value.equals("7")){//07
			type="槽钢水平度异常";
		}else{
			type="触网：超出判断范围";
		}
		return type;
	}
	/***轨道***/
	public static String get_13_100_faultType(List<PointInfo> pointInfoList) {
		String type=null;
		PointInfo point1 =pointInfoList.get(0);
		PointInfo point2 =pointInfoList.get(1);
		PointInfo point3 =pointInfoList.get(2);
		PointInfo point4 =pointInfoList.get(3);
		PointInfo point5 =pointInfoList.get(4);
		boolean p1=GetOne_13_10_Util.getboolean(point1,point2);
		boolean p2=GetOne_13_10_Util.getboolean(point3,point4);
		boolean p3=GetOne_13_10_Util.getboolean(point5,point5);
		if(p1){
			type="轨向不平顺";
		}else if(p2){
			type="高低不平顺";
		}else if(p3){
			type="水平不平顺";
		}
		return type;
	}

	
	/****桥梁*/
	public static String get_16_300_faultType(List<PointInfo> pointInfoList) {
		String type=null;
		String redORyellor=pointInfoList.get(0).getValue1();
		if(redORyellor.equals("0")){
			type="正常";
		}else if(redORyellor.equals("1")){
			type="黄色预警";
		}else if(redORyellor.equals("2")){
			type="红色预警";
		}else {
			type="超出界值";
		}
		return type;
	}
	
	/***隧道**/
	public static String get_15_20_faultType(List<PointInfo> pointInfoList) {
		String type=null;
		String level=pointInfoList.get(0).getValue1();
		if(level.equals("0")){
			type="正常";
		}else if(level.equals("1")){
			type="一级预警";
		}else if(level.equals("2")){
			type="二级预警";
		}else if(level.equals("2")){
			type="三级预警";
		}else {
			type="超出界值";
		}
		return type;
	}



	/**高/低  压开 馈线 关柜*/
	public static String get_02_24_faultType(List<PointInfo> pointInfoList) {
		// TODO Auto-generated method stub
		String p1=pointInfoList.get(2).getValue1();//开关跳闸回路断线
		String p2=pointInfoList.get(8).getValue1();//开关过电流
		String p3=pointInfoList.get(10).getValue1();//开关差动保护跳闸
		String type=null;
		if(p1.equals("1")){
			type="开关跳闸回路断线";
		}
		if(p2.equals("1")){
			type="开关过电流";
		}
		if(p3.equals("1")){
			type="开关差动保护跳闸";
		}
		
		return type;
	}
}
