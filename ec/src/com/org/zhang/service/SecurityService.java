package com.org.zhang.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.org.zhang.dao.face.SecurityTestInterface;
import com.org.zhang.service.face.SecurityServiceInterFace;
@Service
public class SecurityService implements SecurityServiceInterFace{
	@Resource
	private SecurityTestInterface dao;
	@Override
	public boolean getinput() {
		// TODO Auto-generated method stub
		
		return dao.getinput();
	}

}
