package com.ucs.rcm.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.ucs.rcm.business.bo.Page;

import com.ucs.rcm.reqobj.HttpRequest;
import com.ucs.rcm.reqobj.ReqgetWarningCounting;
import com.ucs.rcm.reqobj.ReqgetWaringList;
import com.ucs.rcm.reqobj.WarningQueryObj;
import com.ucs.rcm.responseObj.DateShow;
import com.ucs.rcm.responseObj.ResDataShowList;

public class TestJson {
	
	public static void main(String args[]){
		
		
		ReqgetWaringList reqQuery =  new ReqgetWaringList() ;
		Page page = new Page();
	
		page.setPageCount(2);
		page.setPageNo(10);
		reqQuery.setPage(page);
		
		WarningQueryObj queryObj;
		queryObj =  new WarningQueryObj();
		queryObj.setEndDate("1990-01-01");
		queryObj.setEquipmentname("Equipmentname");
		reqQuery.setQuery(queryObj);
		
		
		String result = JSONObject.fromObject(reqQuery).toString();
		System.out.println(result);
		
		
		System.out.println("aa");
		HttpRequest req = new HttpRequest();
		req.setMethod("getWarningCounting");
		req.setType("WarningManager");
		
		ReqgetWarningCounting reqs =  new ReqgetWarningCounting();
		reqs.setReqString("aa");
		req.setReq(reqs);

		System.out.println(JSONObject.fromObject(req).toString());
		
		
		ReqgetWaringList reqQuery1 =   new ReqgetWaringList();
		reqQuery1 = (ReqgetWaringList)JSONObject.toBean(
				JSONObject.fromObject(result),
				ReqgetWaringList.class);
		System.out.println(reqQuery1.getPage().getPageCount());
		System.out.println(reqQuery1.getQuery().getEquipmentname());
		System.out.println(reqQuery1.getQuery().getStartDate()+"getStartDate");
		if(reqQuery1.getQuery().getStartDate().equals("")) 
			System.out.println("空字符串");
		
/*
		HttpRequest httpRequest = (HttpRequest) JSONObject.toBean(
				JSONObject.fromObject(inputString.toString()),
				HttpRequest.class);
		
		
		
		String result = JSONObject.fromObject(o).toString();*/
		
	}
	@Test
	public void dataShow(){
		float[] vals=new float[6];
		vals[0]=(float) 35.8;
		vals[1]=(float) 36.8;
		vals[2]=(float) 37.8;
		vals[3]=(float) 38.8;
		vals[4]=(float) 39.8;
		vals[5]=(float) 38.8;
		ResDataShowList info=new ResDataShowList();
		List<DateShow> list=new ArrayList<DateShow>();
		DateShow show1=new DateShow();
		show1.setName("定位直流电压");
		show1.setSource(vals);
		DateShow show2=new DateShow();
		show2.setName("定位交流电压");
		show2.setSource(vals);
		list.add(show1);list.add(show2);
		info.setListInfo(list);
		Gson gson=new Gson();
		System.out.println(gson.toJson(info));
	}

}
