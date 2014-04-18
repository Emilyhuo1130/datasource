package com.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.face.AbillityFace;
import com.pojo.AbillityPojo;
import com.source.SourceList;

@Controller
public class Abillity {
	private ExecutorService threadpool=Executors.newFixedThreadPool(1000);
	Logger log=Logger.getLogger(Abillity.class);
	
	private Abillity(){
		
	}
	@Resource
	private AbillityFace dao;
	@RequestMapping(value="abillity")
	@ResponseBody
	public boolean testAbillity(HttpServletResponse res,HttpServletRequest req){
		String n=req.getParameter("number");
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		log.info("开始时间"+sdf.format(new Date()));
		List<AbillityPojo> list=new ArrayList<AbillityPojo>();
		for(int i=0;i<Integer.parseInt(n);i++){
			AbillityPojo a=new AbillityPojo();
			a.setName("zhang");
			a.setAge(22);
			a.setInfo("---9uhahakjldsljakhdlu");
			list.add(a);
		}
		log.info("循环结束时间："+sdf.format(new Date()));
		return dao.insert(list);
	}
	@RequestMapping(value="abillity2")
	@ResponseBody
	public boolean testAbillity2(HttpServletResponse res,HttpServletRequest req){
		String n=req.getParameter("number");
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		List<AbillityPojo> list=new ArrayList<AbillityPojo>();
		for(int i=0;i<Integer.parseInt(n);i++){
			AbillityPojo a=new AbillityPojo();
			a.setName("zhang");
			a.setAge(22);
			a.setInfo("---9uhahakjldsljakhdlu");
			list.add(a);
		}
		log.info("开始时间"+sdf.format(new Date()));
		for(AbillityPojo a:list){
			dao.insert2(a);
		}
		log.info("循环结束时间："+sdf.format(new Date()));
		return true;
	}
	//多线程
	@RequestMapping(value="abillity3")
	@ResponseBody
	public boolean testAbillity3(HttpServletResponse res,HttpServletRequest req){
		String n=req.getParameter("number");
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		List<AbillityPojo> list=new ArrayList<AbillityPojo>();
		for(int i=0;i<Integer.parseInt(n);i++){
			AbillityPojo a=new AbillityPojo();
			a.setName("zhang");
			a.setAge(22);
			a.setInfo("---9uhahakjldsljakhdlu");
			list.add(a);
		}
		
		
		
		log.info("开始时间"+sdf.format(new Date()));
		for(AbillityPojo a:list){
			Hander h=new Hander(a);
			threadpool.execute(h);
		}
		log.info("循环结束时间："+sdf.format(new Date()));
		return flag;
	}
	
	
	@RequestMapping(value="abillity4")
	@ResponseBody
	public boolean testAbillity4(HttpServletResponse res,HttpServletRequest req){
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		String n=req.getParameter("number");
		List<AbillityPojo> list=new ArrayList<AbillityPojo>();
		for(int i=0;i<Integer.parseInt(n);i++){
			AbillityPojo a=new AbillityPojo();
			a.setName("zhang");
			a.setAge(22);
			a.setInfo("---9uhahakjldsljakhdlu");
			list.add(a);
		}
		
		return dao.insert3(list);
	}
	
	
	@RequestMapping(value="abillity5")
	@ResponseBody
	public boolean testAbillity5(HttpServletResponse res,HttpServletRequest req){
		boolean flag=false;
		AbillityPojo a=new AbillityPojo();
		a.setName("zhang");
		a.setAge(22);
		a.setInfo("---9uhahakjldsljakhdlu");
		SourceList.getInfoList().add(a);
		log.info(SourceList.getInfoList().size());
		if(SourceList.getInfoList().size()>=10){
			flag =dao.insert5(SourceList.getInfoList());
		}
		return flag;
	}
	
	
	
	
	
	
	
	
	//多线程
	class Hander implements Runnable{
		private AbillityPojo pojo;
		private Hander(AbillityPojo pojo2){
			pojo=pojo2;
		}
		@Override
		public void run() {
			flag=dao.insert2(pojo);
			log.info(new Date());
		}
		
	}
	private boolean flag;
	
	
}
