package com.ces.tcm.buyer.dao.imp;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ces.tcm.bean.UserInfo;
import com.ces.tcm.buyer.dao.IGoodsDao;
import com.ces.tcm.core.baseDao.JdbcDao;
@Repository("GoodsDao")
public class GoodsDao implements IGoodsDao{
	Logger log=Logger.getLogger(GoodsDao.class);
	@Resource
	private JdbcDao<UserInfo> jdbcDao;
	public boolean addAndUpdate(UserInfo info){
		try{
			jdbcDao.update("insert into userinfo(name,age,infoid) values(?,?,?)",new Object[]{info.getName()
					,info.getAge(),info.getInfoid()});
			log.info("insert....... 执行Add操作");
			return true;
			
		}catch(Exception e){
			throw e;
		}
	}
}
