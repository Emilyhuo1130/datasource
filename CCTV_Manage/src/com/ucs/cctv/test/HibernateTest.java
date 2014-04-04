package com.ucs.cctv.test;


import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.ucs.cctv.utils.ToolUtils;

public class HibernateTest extends HibernateDaoSupport{


	Gson gson = new Gson();



	Logger log = Logger.getLogger(HibernateTest.class);
	
	
	
public void testGetOperatorNameByAccount(){
	ApplicationContext context = ToolUtils.getPathOfapplicationXML();
	
	log.info(getHibernateTemplate());
	}




}
