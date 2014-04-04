package com.daoTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.FieldFilter;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import org.nutz.trans.Atom;

import com.datasource.Dataource;
import com.pojo.LogInfo;
import com.pojo.Person;

public class InsertFaceTest extends Dataource {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	static Person p;
	static List<Person> list=new ArrayList<Person>();
	public static void main(String[] args) throws ClassNotFoundException {
		SimpleDataSource ds=getdateSource();
		Dao dao=new NutDao(ds);
		//dao.drop(Person.class);//删除一张表，建表前线删除
		//dao.create(Person.class, true);//创建一张表
		//dao.create(Person.class, false);//创建表，如果存在就忽略  一般都这么写
		
		/***Fetch 更具id  或者主键获取单条记录***/
		/*p=dao.fetch(Person.class,"ABC");
		System.out.println(p.getName());*/
		p=dao.fetch(Person.class, 4);
		System.out.println(p.getName());
		
		/***更新单个记录**/
		/*p.setAge(44);
		dao.update(p);*/
		
		/**更新多个记录****/
		//dao.update(Person.class,Chain.make("name", "lisi"), Cnd.where("age",">","21"));
		
		/***s删除记录**/
		//dao.delete(Person.class,"ABC");//@Name的主键找到这个记录 
		//dao.delete(Person.class,2);
		
		/**Query 查询****/
		//查询全部记录
		/*list=dao.query(Person.class, null);
		System.out.println(list.size());*/
		
		//按条件查询
		/*list=dao.query(Person.class, Cnd.where("name", "like","%z%"));
		System.out.println(list.size());*/
		
		//原生的sql查询   select * from Person where 1=1 and name like '%z%' order by age 
		/*list=dao.query(Person.class, Cnd.wrap("where 1=1 and name like '%z%' and age = 44 order by age "));
		System.out.println(list.size());*/
		
		
		
		/**Criteria***/
		//Criteria cr=Cnd.cri();
	//	cr.where().andIn("id", 2,3,5,6).andIn("name", "zhangsan");//in 语句
	//	cr.where().andLT("id", 5);// LT 小于   GTE 大于等于    LTE小于等于
		//cr.where().andLike("name", "%zhang%");
		
	
		
	/*	list=dao.query(Person.class, cr, null);
		System.out.println(list.size());*/
		
		/***分页查询***/
		/*list=dao.query(Person.class, Cnd.wrap("where 1=1"),dao.createPager(2, 4));//第2页  每页4条
		System.out.println(list.size());
		for(Person p:list){
			System.out.println(p.getId());
		}*/
		
		/***clear  清除**/
		//dao.clear(Person.class);//清除所有记录
		//dao.clear(Person.class,Cnd.wrap("id < 6"));


		ds.close();
	}	
	
	/***分页查询  返回分页信息 和总页数**/
	public QueryResult getPetList(Dao dao, int pageNumber, int pageSize){
		Pager pager = dao.createPager(pageNumber, pageSize);
		List<Person> list = dao.query(Person.class, null, pager);
		pager.setRecordCount(dao.count(Person.class));
		return new QueryResult(list, pager);
	}
	
	
	

}
