package com.dao;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.face.ServiceTestdaoFace;
import com.pojo.UserInfo;
@Repository("ServiceTestDao")
public class ServiceTestDao implements ServiceTestdaoFace{
	Logger log=Logger.getLogger(ServiceTestDao.class);
	@Resource
	private JdbcTemplate template;
	@Override
	public boolean add(UserInfo info) throws Exception {
		try{
			template.update("insert into userinfo(name,age,infoid) values(?,?,?)",new Object[]{info.getName()
					,info.getAge(),info.getInfoid()});
			log.info("insert....... 执行Add操作");
			return true;
			
		}catch(Exception e){
			throw e;
		}
	}

	@Override
	public boolean update(UserInfo info) throws Exception{
		try{
			template.update("update userinfo set name=? where id =?",new Object[]{info.getName(),1});
			log.info("update....... 执行update操作");
			return true;
		}catch(Exception e){
			throw e;
		}
	}

}
