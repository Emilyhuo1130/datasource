package com.ucs.rcm;

import java.util.HashMap;
import java.io.*;

import jxl.*;
import jxl.write.*;

public class RcmUtils {
	public static HashMap stationmap = new HashMap<String, String>();
	
	
	/**
	 * @param args
	 */

	public static int getWarningLevels(String deviceName) {

		int result = 1;

		if (deviceName.contains("道岔") || deviceName.contains("隧道断面")
				|| deviceName.contains("桥梁") || deviceName.contains("轨道区段"))
			result = 1;
		if (deviceName.contains("信号机") || deviceName.contains("触网导线")
				|| deviceName.contains("断路器高压开关"))
			result = 2;
		if (deviceName.contains("油浸式变压器") || deviceName.contains("隧道风机"))
			result = 3;
		if (deviceName.contains("区间水泵"))
			result = 4;

		return result;

	}

	public static float getHealthLevel(int[] warningLevelCounts) {
		
/*		1	严重
		2	危险
		3	良好
		4	健康*/


		float result = 100;
		int r = 0;

		for (int i = 0; i < 3; i++) {
			if ((i + 1) == 1)
				result = result - warningLevelCounts[i] * 5;
			if ((i + 1) == 2)
				result = result - warningLevelCounts[i] * 3;
			if ((i + 1) == 3)
				result = result - warningLevelCounts[i] * 1;
			if ((i + 1) == 4)
				result = result - warningLevelCounts[i] * 0.5f;

		}
		
	//	System.out.println("result:"+result);

		
		if(result == 100f){
			r = 4;
		}else if((result < 100f) && (result >= 90f)){
			r = 3;
		}else if((result < 90f) && (result >= 70f)){
			r = 2;
		}else if( result <= 70f ){
			r = 1;
		}
		
		return r;

	}

	public static void getStationMap() {
	

		try {
		
		
			//Workbook book = Workbook.getWorkbook(new File("src/groups.xls"));
			Workbook book = Workbook.getWorkbook(new File( System.getProperty("user.dir")+"/WEB-INF/groups.xls"));
			
			
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 第2列 第三列，2-34
			for (int row = 1; row < 34; row++) {
				Cell cell1 = sheet.getCell(1,row);
				
				System.out.println(sheet.getCell(1,row).getContents()+"||"+sheet.getCell(2,row).getContents());
				stationmap.put(sheet.getCell(1,row).getContents().trim(), sheet.getCell(2,row).getContents().trim());
			}
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		 
	}
	/***log4j记录错误日志信息对象转字符串***/
	public static String getTrace(Exception t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }

	public static void main(String[] args) {
		getStationMap();
		System.out.println(stationmap.size());
		System.out.println(stationmap.get("HQ"));

	}

}
