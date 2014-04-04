
package com.ucs.rcm.readxelInfoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class ReadEquipmentTree {
	public static void main(String[] args) {
		read();
	}
	
	public static void read(){
		String temp=null;//用于合并单元格所用
		try {
			Workbook book = Workbook.getWorkbook(new File("e:/warning.xls"));
			//Workbook book = Workbook.getWorkbook(new File( System.getProperty("user.dir")+"/WEB-INF/groups.xls"));
			long l2=111031021*10000;
			
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 第2列 第三列，2-34
			List<EquipmentInfo> list =new ArrayList<EquipmentInfo>();
			for (int row = 126; row < 198; row++) {
				
				Cell cell1 = sheet.getCell(0,row);
				
					if((sheet.getCell(0,row).getContents()!=null)&&(sheet.getCell(0,row).getContents().trim().length()>0)){
						
						temp=sheet.getCell(0,row).getContents();
					}
				
					
					EquipmentInfo bo =new EquipmentInfo();
					bo.setStationName(sheet.getCell(0,row).getContents().trim());
					bo.setSystemName(sheet.getCell(1,row).getContents().trim());
					bo.setSubsystemName(sheet.getCell(2,row).getContents().trim());
					bo.setComponent(sheet.getCell(3,row).getContents().trim());
					bo.setSubComponent(sheet.getCell(4,row).getContents().trim());
					bo.setSubcomponentId(sheet.getCell(5,row).getContents().trim());
					bo.setLevel("4");
					list.add(bo);
				
				
				//stationmap.put(temp, sheet.getCell(1,row).getContents().trim());
			}
			SaveInfoToDb.saveequipmentInfo(list);
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
