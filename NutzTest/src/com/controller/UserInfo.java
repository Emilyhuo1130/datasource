package com.controller;



import java.util.ArrayList;
import java.util.List;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;


import com.datasource.Dataource;
import com.pojo.Person;

public class UserInfo {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		SimpleDataSource ds=Dataource.getdateSource();
		Dao dao = new NutDao(ds);
		List<Person> list=new ArrayList<Person>();
		Person p = new Person();
		p.setName("ABC");
		p.setUserage(20);
		list.add(p);
		dao.insert(list);//集合式的插入
		ds.close();//关闭连接
		
	}
	

}
