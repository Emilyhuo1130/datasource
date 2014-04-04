package com.services;

import java.util.List;

import org.junit.Test;


import com.dao.AdminInfoDAO;
import com.pojo.AdminInfo;





public class AdminInfoControl {
	
	public static void main(String[] args) {
		AdminInfoDAO dao=new AdminInfoDAO();
		List list=dao.findAll(1,3);
		System.out.println(list.size());

	}
	
	@Test
	public void save(){
		AdminInfoDAO dao=new AdminInfoDAO();
		AdminInfo instance =new AdminInfo();
		instance.setUserName("000");
		instance.setUserPw("3333");
		dao.save(instance);
	}
	
}
