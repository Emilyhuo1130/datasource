package com.daoTest;

import org.junit.Test;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;

import com.datasource.Dataource;
import com.pojo.Person;

public class AffiirsTest extends Dataource {

	/**
	 *事务模板 @param args
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		SimpleDataSource sd=getdateSource();
		 Dao dao=new NutDao(sd);
		
		Person p=dao.fetch(Person.class,4);
		Person p2=dao.fetch(Person.class,5);
		p.setName("zhang");
		p2.setName("user");
		
		save(dao,p,p2);
	}
	
	/*事务原子性**/
	private static void save(final Dao dao, final Person p, final Person p2) {
		Trans.exec(new Atom(){
			public void run() {
				dao.update(p);
				dao.update(p2);
			}
			
		});
	}


	
}
