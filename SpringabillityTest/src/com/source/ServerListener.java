package com.source;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.face.AbillityFace;
import com.pojo.AbillityPojo;

@Controller
public class ServerListener{
	@Resource
	private AbillityFace dao;
	Logger log=Logger.getLogger(ServerListener.class);
	
	@RequestMapping(value="Listener")
	@ResponseBody
	public boolean testAbillity5(HttpServletResponse res,HttpServletRequest req){
		Timer timer =new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				log.info("执行了一次定时器");
				List<AbillityPojo> infoList = SourceList.getInfoList();
				if(infoList.size()>0){
					log.info("执行了一次数据操作");
					dao.insert5(infoList);
				}
				
			}
		},0,10000);
		return true;
	}

}
