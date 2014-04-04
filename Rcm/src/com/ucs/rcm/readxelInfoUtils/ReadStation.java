package com.ucs.rcm.readxelInfoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ReadStation {

	private String lineNo;
	private String stationId;
	private String stationName;
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	@Test
	public  void read(){
		String temp=null;//用于合并单元格所用
		try {
			Workbook book = Workbook.getWorkbook(new File("e:/station.xls"));
			//Workbook book = Workbook.getWorkbook(new File( System.getProperty("user.dir")+"/WEB-INF/groups.xls"));
			
			
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 第2列 第三列，2-34
			List<ReadStation> list =new ArrayList<ReadStation>();
			for (int row = 1; row < 34; row++) {
				Cell cell1 = sheet.getCell(0,row);
				
					if((sheet.getCell(0,row).getContents()!=null)&&(sheet.getCell(0,row).getContents().trim().length()>0)){
						
						temp=sheet.getCell(0,row).getContents();
					}
				
					
					ReadStation bo =new ReadStation();
					bo.setStationId(sheet.getCell(1,row).getContents().trim());
					bo.setStationName(sheet.getCell(2,row).getContents().trim());
					bo.setLineNo("10");
					list.add(bo);
				
				
				//stationmap.put(temp, sheet.getCell(1,row).getContents().trim());
			}
			SaveInfoToDb.savestations(list);
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
