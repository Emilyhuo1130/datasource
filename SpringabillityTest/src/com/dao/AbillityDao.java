package com.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.face.AbillityFace;
import com.pojo.AbillityPojo;
import com.source.SourceList;
@Repository("AbillityDao")
public class AbillityDao implements AbillityFace{
	private ExecutorService threadpool=Executors.newFixedThreadPool(500);
	Logger log=Logger.getLogger(AbillityDao.class);
	@Resource
	private JdbcTemplate jdbc;
	
	@Override
	public boolean insert(List<AbillityPojo> list) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		log.info("准备开始插入数据："+sdf.format(new Date()));
		
			for(AbillityPojo a:list){
				jdbc.update("insert into abillity(name,age,info) values(?,?,?)", new Object[]{a.getName(),a.getAge(),a.getInfo()});
			}
		
		log.info("数据插入完成："+sdf.format(new Date()));
		return true;
	}
	@Override
	public boolean insert2(AbillityPojo a) {
		
		jdbc.update("insert into abillity(name,age,info) values(?,?,?)",new Object[]{a.getName(),a.getAge(),a.getInfo()});
		return true;
	}
	@Override
	public boolean insert3(List<AbillityPojo> list) {
		log.info("insert3开始计时"+new Date());
		for(AbillityPojo p:list){
			SQLInsert s=new SQLInsert(p);
			threadpool.execute(s);
		}
		return true;
	}
	
	class SQLInsert implements Runnable{
		private AbillityPojo a;
		private SQLInsert(AbillityPojo p){
			a=p;
		}
		@Override
		public void run() {
			jdbc.update("insert into abillity(name,age,info) values(?,?,?)",new Object[]{a.getName(),a.getAge(),a.getInfo()});
			log.info(new Date());
		}
		
	}

	@Override
	public boolean insert5(List<AbillityPojo> infoList) {
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		log.info("准备开始插入数据："+sdf.format(new Date()));
		
			for(AbillityPojo a:infoList){
				jdbc.update("insert into abillity(name,age,info) values(?,?,?)", new Object[]{a.getName(),a.getAge(),a.getInfo()});
			}
		
		log.info("数据插入完成："+sdf.format(new Date()));
		
		SourceList.clean();
		return true;
	}

}
