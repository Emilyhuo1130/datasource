package com.daoTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.TableName;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import com.datasource.Dataource;
import com.pojo.LogInfo;
import com.pojo.Person;

public class OneToOne extends Dataource {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	/**一对一映射***/
	static List<Person> list=new ArrayList<Person>();
	public static void main(String[] args) throws ClassNotFoundException {
		SimpleDataSource ds=getdateSource();
		final Dao dao=new NutDao(ds);
		final Person p=dao.fetch(Person.class,2);
		TableName.run(p.getId(), new Runnable() {
			public void run() {
				dao.fetchLinks(p, "info");
			}
		});
		System.out.println(p.getInfo().getUserName()+"   "+p.getInfo().getPassWord());
		p.setUserage(44);
		p.getInfo().setPassWord("111111");
		dao.update(p);//更新person但是不会跟新loginfo的内容
		dao.updateLinks(p, "info");//需要使用udateLinks才能更新loginfo的内容
		ds.close();
		
	}
	
	/***一对一映射 获取数据的简便写法
	 * @throws ClassNotFoundException ****/
	@Test
	public void onetoone() throws ClassNotFoundException{
		SimpleDataSource ds=getdateSource();
		final Dao dao=new NutDao(ds);
		//Person p=dao.fetchLinks(dao.fetch(Person.class,3), "info");
		//System.out.println(p.getInfo().getUserName());
		
		//p.getInfo().setPassWord("77");
		//p.setName("wangwu");
		//dao.update(p);//只更新person
		
		//dao.updateLinks(p, "info");//只跟新loginfo
		//dao.updateWith(p, "info");//person 和loginfo都更新
		
		//dao.delete(p);//仅删除这个id的person
		
		//dao.deleteLinks(p, "info");//仅删除person下的loginfo
		
		//dao.deleteWith(p, "info");//这个person和以下的loginfo都删除
		Person p2=new Person();
		p2.setInfoid(1);
		p2.setName("22");
		p2.setUserage(33);
		dao.insert(p2);
		ds.close();
		
	}

}
