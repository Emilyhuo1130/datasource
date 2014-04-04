package com.daoTest;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.TableName;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import com.datasource.Dataource;
import com.pojo.LogInfo;
import com.pojo.Person;

public class OneToMany extends Dataource  {

	/**
	 * 一对多映射@param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		SimpleDataSource ds=getdateSource();
		final Dao dao=new NutDao(ds);
		final LogInfo log=dao.fetch(LogInfo.class,1);
		TableName.run(log.getId(), new Runnable() {
			public void run() {
				dao.fetchLinks(log, "users");
			}
		});
		for(Person p:log.getUsers()){
			System.out.println(p.getName());
			p.setUserage(34);
		}
		log.setPassWord("666666");
		
		dao.update(log);
		
		dao.updateLinks(log, "users");//更新一对多的对象必须使用updateLikns 
		ds.close();
	}
	
	
	/***一对多的简便写法
	 * @throws ClassNotFoundException **/
	@Test
	public void onetomany() throws ClassNotFoundException{
		SimpleDataSource ds=getdateSource();
		final Dao dao=new NutDao(ds);
		LogInfo l=dao.fetchLinks(dao.fetch(LogInfo.class,1), "users");
		for(Person p:l.getUsers()){
			System.out.println(p.getName());
			p.setUserage(42);
		}
		//dao.updateLinks(l, "users");//z值更新users
		//dao.update(l);//只更新loginfo
		//dao.updateWith(l, "users");//users和loginfo都更新
		
		//dao.delete(l);//只删除Loginfo
		//dao.deleteLinks(l, "users"); //只删除users
		//dao.deleteWith(l, "users");//loginfo和users都删除
		
		
		ds.close();
		
		
	}

}
