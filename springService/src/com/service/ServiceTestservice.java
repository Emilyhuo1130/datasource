package com.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dao.face.ServiceTestdaoFace;
import com.pojo.UserInfo;
import com.service.face.ServiceTestFace;
@Service
public class ServiceTestservice implements ServiceTestFace{

	Logger log=Logger.getLogger(ServiceTestservice.class);
	@Resource
	private ServiceTestdaoFace dao;
	public boolean addAndUpdate(UserInfo info) throws Exception {
		log.info("ServiceTestservice.......执行service操作");
		try{
			boolean add=dao.add(info);
			boolean update=dao.update(info);
			if(add&&update){
				return true;
			}else{
				return false;
			}
			
		}catch(Exception e){
			throw e;
		}
	}

}
