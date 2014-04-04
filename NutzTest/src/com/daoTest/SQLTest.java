package com.daoTest;

import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.sql.Sql;

import com.datasource.Dataource;

public class SQLTest extends Dataource {

	/**
	 *自定义SQL
	 * @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		SimpleDataSource ds=getdateSource();
		Dao dao=new NutDao(ds);
		Sql sql=Sqls.create("select * from userinfo where name like @name");
		sql.params().set("name", "%zhang%");
		dao.execute(sql);
		
	}

}
