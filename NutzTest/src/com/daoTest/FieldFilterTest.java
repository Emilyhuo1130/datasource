package com.daoTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.trans.Atom;

import com.datasource.Dataource;
import com.pojo.LogInfo;
import com.pojo.Person;

public class FieldFilterTest  extends Dataource{
	/**忽略更新    过滤字段***/
	
	static Person p;
	static List<Person> list=new ArrayList<Person>();
	
	/***忽略少数字段  把userage忽略掉不更新*/
	@Test
	public void update1(){
		FieldFilter.locked(Person.class, "^userage$").run(new Atom() {
			public void run() {
				try{
					SimpleDataSource ds=getdateSource();
					Dao dao=new NutDao(ds);
					p=dao.fetch(Person.class,6);
					System.out.println(p.getId()+p.getName()+p.getUserage());//取值忽略了userage只取其他的值
					p.setName("lisi2");
					p.setUserage(25);
					System.out.println(p.getName()+p.getUserage());
					dao.update(p);//忽略掉了userage 只更新其他的
					ds.close();
				}catch(Exception e){
					
				}
				
			}
		});
		
	}
	
	/**忽略空的东西***/
	@Test
	public void update2(){
		FieldFilter.create(Person.class, true).run(new Atom() {
			public void run() {
				// TODO Auto-generated method stub
				try{
					SimpleDataSource ds=getdateSource();
					Dao dao=new NutDao(ds);
					p=dao.fetch(Person.class,6);
					System.out.println(p.getName()+p.getUserage());
					p.setName(null);
					p.setUserage(24);
					dao.update(p);
					ds.close();
				}catch(Exception e){
					
				}
			}
		});
	}
	
	/**忽略空的东西并且保留指定的字段***/
	@Test
	public void update3(){
		FieldFilter.create(Person.class,"^id|name$",true).run(new Atom() {
			public void run() {
				// TODO Auto-generated method stub
				try{
					SimpleDataSource ds=getdateSource();
					Dao dao=new NutDao(ds);
					p=dao.fetch(Person.class,6);
					System.out.println(p.getId()+p.getName()+p.getUserage());//保留了id和name的值，其他值都不要
					p.setName("444");
					p.setUserage(35);
					System.out.println(p.getName()+p.getUserage());
					dao.update(p);//忽略掉userage 只更新被保留的字段
					ds.close();
				}catch(Exception e){
					
				}
			}
		});
	}
	
	
	/**忽略空的东西并且忽略指定的字段***/
	@Test
	public void update4(){
		FieldFilter.create(Person.class,null,"^name$",true).run(new Atom() {
			public void run() {
				// TODO Auto-generated method stub
				try{
					SimpleDataSource ds=getdateSource();
					Dao dao=new NutDao(ds);
					p=dao.fetch(Person.class,6);
					System.out.println(p.getId()+p.getName()+p.getUserage());//6 null 24 被忽略掉 输出null
					p.setName("444");
					p.setUserage(35);
					System.out.println(p.getName()+p.getUserage());
					dao.update(p);//只更新未被忽略的字段
					ds.close();
				}catch(Exception e){
					
				}
			}
		});
	}
	
	/***多个实体对象的字段过滤***/
	@Test
	public void update5(){
		FieldFilter.create(Person.class,null,"^name$",true).set(LogInfo.class, "^userName$").run(new Atom() {
			public void run() {
				// TODO Auto-generated method stub
				try{
					SimpleDataSource ds=getdateSource();
					Dao dao=new NutDao(ds);
					p=dao.fetch(Person.class,6);
					System.out.println(p.getId()+p.getName()+p.getUserage());//6 null 24 被忽略掉 输出null
					p.setName("444");
					p.setUserage(35);
					System.out.println(p.getName()+p.getUserage());
					dao.update(p);//只更新未被忽略的字段
					ds.close();
				}catch(Exception e){
					
				}
			}
		});
	}
	
}
