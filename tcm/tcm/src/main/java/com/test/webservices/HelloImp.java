package com.test.webservices;

import com.ces.tcm.bean.UserInfo;
import com.ces.tcm.buyer.service.IGoodService;
import com.ces.tcm.buyer.service.imp.GoodService;
import com.ces.tcm.webservices.ServicesSingleton;
public class HelloImp implements IHello {
	/***
	 * webservices接口可以使用spring容器中的
	 * **/
	public String hello() {
		ServicesSingleton single = ServicesSingleton.getInstance();
		IGoodService service =  (GoodService)single.getService(IGoodService.class);
		
		UserInfo info=new UserInfo("test",33,"332222");
		service.addAndUpdate(info);
		return "hello";
	}

	@Override
	public String say() {
		// TODO Auto-generated method stub
		return "hello--------";
	}
	
	
}
