package com.org.zhang.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.org.zhang.dao.face.PublicMethodDaoFace;
import com.org.zhang.service.face.PublicMethodFace;
@Service
public class PublicMethodService implements PublicMethodFace{
	@Resource
	private PublicMethodDaoFace dao;
	@Override
	public boolean CommitBug(String opinion) {
		return dao.CommitBug(opinion);
	}

}
