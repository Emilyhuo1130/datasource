package com.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.inter_face.TestSpringJdbcIInterface;

@Repository("TestSpringJdbcDao")
public class TestSpringJdbcDao implements TestSpringJdbcIInterface {
	private Logger log=Logger.getLogger(TestSpringJdbcDao.class);
	@Resource
	private JdbcTemplate jdbcTamplate;
	
	public Object testSpringJdbc() {
		Gson gson=new Gson();
		List list=jdbcTamplate.queryForList("select * from task_particulars");
		log.info(gson.toJson(list));
		
		jdbcTamplate.update("delete from task_particulars where id =?",new Object[]{28});
		
		jdbcTamplate.update("update task_particulars set userName=?,taskDetail=? where id=?",new Object[]{"44","55",29});
		
		return list;
	}


}
