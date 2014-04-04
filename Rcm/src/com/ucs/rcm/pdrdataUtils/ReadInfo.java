package com.ucs.rcm.pdrdataUtils;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ucs.rcm.RcmUtils;

public class ReadInfo {
	static Logger log = Logger.getLogger(ReadInfo.class);
/****获取模拟量**/
	public static List<PointInfo> getpointInfoList(ResultSet rs) {
		// TODO Auto-generated method stub
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		try{
			while(rs.next()){
				PointInfo info=new PointInfo();
				String[] vals=new String[39];
				
				info.setComponentId(rs.getString("componentId"));
				vals[0]=rs.getString("componentId");
				info.setPointCode(rs.getString("pointCode"));
				vals[1]=rs.getString("pointCode");
				info.setDateType(rs.getString("dateType"));
				vals[2]=rs.getString("dateType");
				info.setValue1(rs.getString("value1"));
				vals[3]=rs.getString("value1");
				info.setValue2(rs.getString("value2"));
				vals[4]=rs.getString("value2");
				info.setValue3(rs.getString("value3"));
				vals[5]=rs.getString("value3");
				info.setValue4(rs.getString("value4"));
				vals[6]=rs.getString("value4");
				info.setValue5(rs.getString("value5"));
				vals[7]=rs.getString("value5");
				info.setValue6(rs.getString("value6"));
				vals[8]=rs.getString("value6");
				info.setValue7(rs.getString("value7"));
				vals[9]=rs.getString("value7");
				info.setValue8(rs.getString("value8"));
				vals[10]=rs.getString("value8");
				info.setValue9(rs.getString("value9"));
				vals[11]=rs.getString("value9");
				info.setValue10(rs.getString("value10"));
				vals[12]=rs.getString("value10");
				info.setValue11(rs.getString("value11"));
				vals[13]=rs.getString("value11");
				info.setValue12(rs.getString("value12"));
				vals[14]=rs.getString("value12");
				info.setValue13(rs.getString("value13"));
				vals[15]=rs.getString("value13");
				info.setValue14(rs.getString("value14"));
				vals[16]=rs.getString("value14");
				info.setValue15(rs.getString("value15"));
				vals[17]=rs.getString("value15");
				info.setValue16(rs.getString("value16"));
				vals[18]=rs.getString("value16");
				info.setValue17(rs.getString("value17"));
				vals[19]=rs.getString("value17");
				info.setValue18(rs.getString("value18"));
				vals[20]=rs.getString("value18");
				info.setValue19(rs.getString("value19"));
				vals[21]=rs.getString("value19");
				info.setValue20(rs.getString("value20"));
				vals[22]=rs.getString("value20");
				info.setValue21(rs.getString("value21"));
				vals[23]=rs.getString("value21");
				info.setValue22(rs.getString("value22"));
				vals[24]=rs.getString("value22");
				info.setValue23(rs.getString("value23"));
				vals[25]=rs.getString("value23");
				info.setValue24(rs.getString("value24"));
				vals[26]=rs.getString("value24");
				info.setValue25(rs.getString("value25"));
				vals[27]=rs.getString("value25");
				info.setValue26(rs.getString("value26"));
				vals[28]=rs.getString("value26");
				info.setValue27(rs.getString("value27"));
				vals[29]=rs.getString("value27");
				info.setValue28(rs.getString("value28"));
				vals[30]=rs.getString("value28");
				info.setValue29(rs.getString("value29"));
				vals[31]=rs.getString("value29");
				info.setValue30(rs.getString("value30"));
				vals[32]=rs.getString("value30");
				info.setValue31(rs.getString("value31"));
				vals[33]=rs.getString("value31");
				info.setValue32(rs.getString("value32"));
				vals[34]=rs.getString("value32");
				info.setValue33(rs.getString("value33"));
				vals[35]=rs.getString("value33");
				info.setValue34(rs.getString("value34"));
				vals[36]=rs.getString("value34");
				info.setValue35(rs.getString("value35"));
				vals[37]=rs.getString("value35");
				info.setWarninginfo(rs.getString("warningType"));
				vals[38]=rs.getString("warningType");
				info.setValues(vals);
				pointInfoList.add(info);
				
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return pointInfoList;
	}
	
	
	
/***获取数字量*/
	public static List<PointInfo> getpointInfoList_D(ResultSet rs) {
		List<PointInfo> pointInfoList=new ArrayList<PointInfo>();
		try{
			while(rs.next()){
				PointInfo info=new PointInfo();
				String[] vals=new String[38];
				info.setComponentId(rs.getString("componentId"));
				vals[0]=rs.getString("componentId");
				info.setPointCode(rs.getString("pointCode"));
				vals[1]=rs.getString("pointCode");
				info.setDateType(rs.getString("dateType"));
				vals[2]=rs.getString("dateType");
				info.setValue1(rs.getString("value1"));
				vals[3]=rs.getString("value1");
				
				info.setValues(vals);
				pointInfoList.add(info);
				
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return pointInfoList;
	}


/***道岔卡斯柯追忆数据***/
	public static List<KSKPointInfo> getKSKpointInfoList(ResultSet rs) {
		
		List<KSKPointInfo> pointInfoList=new ArrayList<KSKPointInfo>();
		try{
			while(rs.next()){
				KSKPointInfo info=new KSKPointInfo();
				float[] vals=new float[39];
				info.setPointCode(rs.getString("pointCode"));
				info.setMeastype(rs.getString("meastype"));	
				vals[0]=rs.getFloat("val0");
				vals[1]=rs.getFloat("val1");
				vals[2]=rs.getFloat("val2");
				vals[3]=rs.getFloat("val3");
				vals[4]=rs.getFloat("val4");
				vals[5]=rs.getFloat("val5");
				vals[6]=rs.getFloat("val5");
				vals[7]=rs.getFloat("val6");
				vals[8]=rs.getFloat("val7");
				vals[9]=rs.getFloat("val8");
				vals[10]=rs.getFloat("val9");
				vals[11]=rs.getFloat("val10");
				vals[12]=rs.getFloat("val11");
				vals[13]=rs.getFloat("val12");
				vals[14]=rs.getFloat("val13");
				vals[15]=rs.getFloat("val14");
				vals[16]=rs.getFloat("val15");
				vals[17]=rs.getFloat("val16");
				vals[18]=rs.getFloat("val17");
				vals[19]=rs.getFloat("val18");
				vals[20]=rs.getFloat("val19");
				vals[21]=rs.getFloat("val20");
				vals[22]=rs.getFloat("val21");
				vals[23]=rs.getFloat("val22");
				vals[24]=rs.getFloat("val23");
				vals[25]=rs.getFloat("val24");
				vals[26]=rs.getFloat("val25");
				vals[27]=rs.getFloat("val26");
				vals[28]=rs.getFloat("val27");
				vals[29]=rs.getFloat("val28");
				vals[30]=rs.getFloat("val29");
				vals[31]=rs.getFloat("val30");
				vals[32]=rs.getFloat("val31");
				info.setValues(vals);
				pointInfoList.add(info);
				
			}
		}catch(Exception e){
			log.error(RcmUtils.getTrace(e));
		}
		return pointInfoList;
	}

}
