package com.ucs.security.server;

import java.security.Principal;
import java.util.Collections;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.jaas.AuthorityGranter;

import com.ucs.security.face.SecurityTestInterface;
import com.ucs.security.pojo.Users;
/**
 * JAAS配置
 * */
public class DefaultAuthorityGrenter implements AuthorityGranter{
	
	@Resource
	private SecurityTestInterface dao;
	Logger log=Logger.getLogger(DefaultAuthorityGrenter.class);
	private Users findbyUsername;
	@Override
	public Set<String> grant(Principal arg0) {
		String name = arg0.getName();
		log.info("get login name:"+arg0.getName());
		//查找到用户拥有的权限
		findbyUsername = dao.findbyUsername(name);
		log.info("数据库查到的用户权限"+findbyUsername.getRole());
		return Collections.singleton(findbyUsername.getRole());
	}

}
