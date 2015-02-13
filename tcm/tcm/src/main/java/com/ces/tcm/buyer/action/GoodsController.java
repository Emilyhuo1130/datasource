package com.ces.tcm.buyer.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ces.tcm.bean.UserInfo;
import com.ces.tcm.buyer.service.IGoodService;

@Controller
public class GoodsController {
	@Resource
	private IGoodService service;
	Logger log=Logger.getLogger(GoodsController.class);
	@RequestMapping(value="/test",method=RequestMethod.GET)
	@ResponseBody
	public boolean test() {
		log.info("进入controller");
		UserInfo info=new UserInfo("test",33,"555555");
		try {
			return service.addAndUpdate(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
