package com.source;

import java.util.ArrayList;
import java.util.List;

import com.pojo.AbillityPojo;

public class SourceList {
	private static List<AbillityPojo> infoList =new ArrayList<AbillityPojo>();

	public static List<AbillityPojo> getInfoList() {
		return infoList;
	}

	public static void setInfoList(List<AbillityPojo> infoList) {
		SourceList.infoList = infoList;
	}
	public static void clean(){
		infoList=new ArrayList<AbillityPojo>();
	}
	
	
}
