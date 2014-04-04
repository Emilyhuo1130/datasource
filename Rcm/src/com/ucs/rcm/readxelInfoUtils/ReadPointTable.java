
package com.ucs.rcm.readxelInfoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import jxl.Sheet;
import jxl.Workbook;

public class ReadPointTable {

	/**
	 * @param args
	 */
	private String pointCode;
	private String pointDesc;
	private String groupCode;
	private String deviceCode;
	private String appSysID;
	
	
	public String getPointCode() {
		return pointCode;
	}


	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}


	public String getPointDesc() {
		return pointDesc;
	}


	public void setPointDesc(String pointDesc) {
		this.pointDesc = pointDesc;
	}


	public String getGroupCode() {
		return groupCode;
	}


	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}


	public String getDeviceCode() {
		return deviceCode;
	}


	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}


	public String getAppSysID() {
		return appSysID;
	}


	public void setAppSysID(String appSysID) {
		this.appSysID = appSysID;
	}


	public static void main(String[] args) {
		read();
	}
	
	public static void read(){
		
		try {
			Workbook book = Workbook.getWorkbook(new File("E:/专业数据/趋势图表.xls"));
			
			
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(1);//分数字量模拟量
			// 第2列 第三列，2-34
			List<ReadPointTable> list =new ArrayList<ReadPointTable>();
			for (int row = 35; row <58; row++) {
			
				ReadPointTable bo =new ReadPointTable();
					bo.setPointCode(sheet.getCell(0,row).getContents().trim());
					bo.setPointDesc(sheet.getCell(1,row).getContents().trim());
					bo.setGroupCode(sheet.getCell(2,row).getContents().trim());
					bo.setDeviceCode(sheet.getCell(3,row).getContents().trim());
					bo.setAppSysID(sheet.getCell(4,row).getContents().trim());
					
					list.add(bo);
				
				
				//stationmap.put(temp, sheet.getCell(1,row).getContents().trim());
			}
			SaveInfoToDb.savepointtableinfo(list);
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	

}

