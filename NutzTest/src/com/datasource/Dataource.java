package com.datasource;

import org.nutz.dao.impl.SimpleDataSource;

public class Dataource {
	public static SimpleDataSource  getdateSource() throws ClassNotFoundException{
		SimpleDataSource ds = new SimpleDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver"); //默认加载了大部分数据库的驱动!!
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
		ds.setUsername("root");
		ds.setPassword("516725");
		
		return ds;
	}
}
