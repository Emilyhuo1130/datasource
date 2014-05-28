package com.org.zhang.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.org.zhang.dao.face.PublicMethodDaoFace;
@Repository
public class PublicMethodDao implements PublicMethodDaoFace{
	Logger log=Logger.getLogger(PublicMethodDao.class);
	
	@Resource
	private JdbcTemplate jdbcTamplate;
	@Override
	public boolean CommitBug(String opinion) {
		int update = jdbcTamplate.update("insert into opinionandbug(message,commitdate) values(?,?)",
				new Object[]{opinion,new Date()});
		return update!=0?true:false;
	}

}
