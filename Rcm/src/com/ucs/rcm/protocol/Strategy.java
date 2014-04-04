package com.ucs.rcm.protocol;

import net.sf.json.JSONObject;

import com.ucs.rcm.business.bo.Strategybo;
import com.ucs.rcm.reqobj.HttpRequest;
import com.ucs.rcm.reqobj.RequpdateFaultInfo;

public class Strategy {
	public static void main(String[] args) {
		
		updateFaultInfo();
		
	}

	private static void updateFaultInfo() {
		// TODO Auto-generated method stub
		HttpRequest req = new HttpRequest();
		RequpdateFaultInfo info = new RequpdateFaultInfo();
		String result;
		info.setFultDescription("lalallla");
		info.setFaultCause("00000");
		info.setMaintenancePolicy("9987");
		info.setId(3);
		req.setType("StrategyManager");
		req.setMethod("updateFaultInfo");
		req.setReq(info);
		result = JSONObject.fromObject(req).toString();
		System.out.println(result);
		Strategybo strategybo = new Strategybo();
		result = JSONObject.fromObject(strategybo.updateFaultInfo(info)).toString();
		System.out.println(result);
	}
}
