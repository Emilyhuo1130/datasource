package com.ucs.rcm.readxelInfoUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import jxl.Sheet;
import jxl.Workbook;

public class Readpdrdata {
	private String componentId;
	private String pointCode;
	private String dateType;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String value5;
	private String value6;
	private String value7;
	private String value8;
	private String value9;
	private String value10;
	private String value11;
	private String value12;
	private String value13;
	private String value14;
	private String value15;
	private String value16;
	private String value17;
	private String value18;
	private String value19;
	private String value20;
	private String value21;
	private String value22;
	private String value23;
	private String value24;
	private String value25;
	private String value26;
	private String value27;
	private String value28;
	private String value29;
	private String value30;
	private String value31;
	private String value32;
	private String value33;
	private String value34;
	private String value35;
	
	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getPointCode() {
		return pointCode;
	}

	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	public String getValue6() {
		return value6;
	}

	public void setValue6(String value6) {
		this.value6 = value6;
	}

	public String getValue7() {
		return value7;
	}

	public void setValue7(String value7) {
		this.value7 = value7;
	}

	public String getValue8() {
		return value8;
	}

	public void setValue8(String value8) {
		this.value8 = value8;
	}

	public String getValue9() {
		return value9;
	}

	public void setValue9(String value9) {
		this.value9 = value9;
	}

	public String getValue10() {
		return value10;
	}

	public void setValue10(String value10) {
		this.value10 = value10;
	}

	public String getValue11() {
		return value11;
	}

	public void setValue11(String value11) {
		this.value11 = value11;
	}

	public String getValue12() {
		return value12;
	}

	public void setValue12(String value12) {
		this.value12 = value12;
	}

	public String getValue13() {
		return value13;
	}

	public void setValue13(String value13) {
		this.value13 = value13;
	}

	public String getValue14() {
		return value14;
	}

	public void setValue14(String value14) {
		this.value14 = value14;
	}

	public String getValue15() {
		return value15;
	}

	public void setValue15(String value15) {
		this.value15 = value15;
	}

	public String getValue16() {
		return value16;
	}

	public void setValue16(String value16) {
		this.value16 = value16;
	}

	public String getValue17() {
		return value17;
	}

	public void setValue17(String value17) {
		this.value17 = value17;
	}

	public String getValue18() {
		return value18;
	}

	public void setValue18(String value18) {
		this.value18 = value18;
	}

	public String getValue19() {
		return value19;
	}

	public void setValue19(String value19) {
		this.value19 = value19;
	}

	public String getValue20() {
		return value20;
	}

	public void setValue20(String value20) {
		this.value20 = value20;
	}

	public String getValue21() {
		return value21;
	}

	public void setValue21(String value21) {
		this.value21 = value21;
	}

	public String getValue22() {
		return value22;
	}

	public void setValue22(String value22) {
		this.value22 = value22;
	}

	public String getValue23() {
		return value23;
	}

	public void setValue23(String value23) {
		this.value23 = value23;
	}

	public String getValue24() {
		return value24;
	}

	public void setValue24(String value24) {
		this.value24 = value24;
	}

	public String getValue25() {
		return value25;
	}

	public void setValue25(String value25) {
		this.value25 = value25;
	}

	public String getValue26() {
		return value26;
	}

	public void setValue26(String value26) {
		this.value26 = value26;
	}

	public String getValue27() {
		return value27;
	}

	public void setValue27(String value27) {
		this.value27 = value27;
	}

	public String getValue28() {
		return value28;
	}

	public void setValue28(String value28) {
		this.value28 = value28;
	}

	public String getValue29() {
		return value29;
	}

	public void setValue29(String value29) {
		this.value29 = value29;
	}

	public String getValue30() {
		return value30;
	}

	public void setValue30(String value30) {
		this.value30 = value30;
	}

	public String getValue31() {
		return value31;
	}

	public void setValue31(String value31) {
		this.value31 = value31;
	}

	public String getValue32() {
		return value32;
	}

	public void setValue32(String value32) {
		this.value32 = value32;
	}

	public String getValue33() {
		return value33;
	}

	public void setValue33(String value33) {
		this.value33 = value33;
	}

	public String getValue34() {
		return value34;
	}

	public void setValue34(String value34) {
		this.value34 = value34;
	}

	public String getValue35() {
		return value35;
	}

	public void setValue35(String value35) {
		this.value35 = value35;
	}
	@Test
	public  void read(){
		
		try {
			Workbook book = Workbook.getWorkbook(new File("e:/pdrdata.xls"));
			//Workbook book = Workbook.getWorkbook(new File( System.getProperty("user.dir")+"/WEB-INF/groups.xls"));
			
			
			// 获得第一个工作表对象
			Sheet sheet = book.getSheet(0);
			// 第2列 第三列，2-34
			List<Readpdrdata> list =new ArrayList<Readpdrdata>();
			for (int row = 1; row < 8; row++) {
				
					Readpdrdata bo =new Readpdrdata();
					bo.setComponentId("10.29.08.01.001");
					bo.setPointCode(sheet.getCell(0,row).getContents());
					bo.setDateType(sheet.getCell(1,row).getContents());
					bo.setValue1(sheet.getCell(2,row).getContents());
					bo.setValue2(sheet.getCell(3,row).getContents());
					bo.setValue3(sheet.getCell(4,row).getContents());
					bo.setValue4(sheet.getCell(5,row).getContents());
					bo.setValue5(sheet.getCell(6,row).getContents());
					bo.setValue6(sheet.getCell(7,row).getContents());
					bo.setValue7(sheet.getCell(8,row).getContents());
					bo.setValue8(sheet.getCell(9,row).getContents());
					bo.setValue9(sheet.getCell(10,row).getContents());
					bo.setValue10(sheet.getCell(11,row).getContents());
					bo.setValue11(sheet.getCell(12,row).getContents());
					bo.setValue12(sheet.getCell(13,row).getContents());
					bo.setValue13(sheet.getCell(14,row).getContents());
					bo.setValue14(sheet.getCell(15,row).getContents());
					bo.setValue15(sheet.getCell(16,row).getContents());
					bo.setValue16(sheet.getCell(17,row).getContents());
					bo.setValue17(sheet.getCell(18,row).getContents());
					bo.setValue18(sheet.getCell(19,row).getContents());
					bo.setValue19(sheet.getCell(20,row).getContents());
					bo.setValue20(sheet.getCell(21,row).getContents());
					bo.setValue21(sheet.getCell(22,row).getContents());
					bo.setValue22(sheet.getCell(23,row).getContents());
					bo.setValue23(sheet.getCell(24,row).getContents());
					bo.setValue24(sheet.getCell(25,row).getContents());
					bo.setValue25(sheet.getCell(26,row).getContents());
					bo.setValue26(sheet.getCell(27,row).getContents());
					bo.setValue27(sheet.getCell(28,row).getContents());
					bo.setValue28(sheet.getCell(29,row).getContents());
					bo.setValue29(sheet.getCell(30,row).getContents());
					bo.setValue30(sheet.getCell(31,row).getContents());
					bo.setValue31(sheet.getCell(32,row).getContents());
					bo.setValue32(sheet.getCell(33,row).getContents());
					bo.setValue33(sheet.getCell(34,row).getContents());
					bo.setValue34(sheet.getCell(35,row).getContents());
					bo.setValue35(sheet.getCell(36,row).getContents());
					list.add(bo);
				
				
				//stationmap.put(temp, sheet.getCell(1,row).getContents().trim());
			}
			SaveInfoToDb.savespdrdata(list);
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
