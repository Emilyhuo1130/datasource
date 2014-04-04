
package com.ucs.rcm.readxelInfoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ucs.rcm.business.bo.WarningBo;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ReadWarning {
	
	public static HashMap<String,String> stationmap = new HashMap<String, String>();
	public static void main(String[] args) {
		read();
	}
	
	public static void read(){
		String temp=null;//用于合并单元格所用
		try {
			Workbook book = Workbook.getWorkbook(new File("e:/warning.xls"));
			//Workbook book = Workbook.getWorkbook(new File( System.getProperty("user.dir")+"/WEB-INF/groups.xls"));
			int rowNo=29;
			
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(3);
			// 第2列 第三列，2-34
			List<WarningBo> warningList =new ArrayList<WarningBo>();
			for (int row = 19; row < rowNo; row++) {
				Cell cell1 = sheet.getCell(0,row);
				
					if((sheet.getCell(0,row).getContents()!=null)&&(sheet.getCell(0,row).getContents().trim().length()>0)){
						
						temp=sheet.getCell(0,row).getContents();
					}
				
					String str=sheet.getCell(11,row).getContents();
					String time=str.replace("/","-");
					WarningBo bo =new WarningBo();
					bo.setId(sheet.getCell(0,row).getContents().trim());
					bo.setWarningId(sheet.getCell(1,row).getContents().trim());
					bo.setEquipmentId(sheet.getCell(2,row).getContents().trim());
					bo.setEquipmentname(sheet.getCell(3,row).getContents().trim());
					bo.setSystemName(sheet.getCell(4,row).getContents().trim());
					bo.setEquipmentDescription(sheet.getCell(5,row).getContents().trim());
					bo.setLineNo(sheet.getCell(6,row).getContents().trim());
					bo.setStationName(sheet.getCell(7,row).getContents().trim());
					bo.setWarningType(sheet.getCell(8,row).getContents().trim());
					bo.setWarningTypeLevel(sheet.getCell(9,row).getContents().trim());
					bo.setWarninginfo(sheet.getCell(10,row).getContents().trim());
					bo.setWarningDate(time);
					bo.setStatments("0");
					bo.setFromuser("0");
					warningList.add(bo);
				
				
				//stationmap.put(temp, sheet.getCell(1,row).getContents().trim());
			}
			SaveInfoToDb.saveWarning(warningList);
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
