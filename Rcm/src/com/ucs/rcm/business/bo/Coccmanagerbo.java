package com.ucs.rcm.business.bo;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.google.gson.Gson;
import com.ucs.rcm.pojo.RateOfwas;
import com.ucs.rcm.pojo.Warning;
import com.ucs.rcm.pojo.WarningIndex;

public class Coccmanagerbo extends BaseHibernateDAO{
	Logger log = Logger.getLogger(Coccmanagerbo.class);
	Gson gson = new Gson();
	
	//获取状态为零的全部告警信息   还要返回告警比例
	//@Test
	public WarningIndex getNoFinishedWarning(Page page){
		String hql = "from Warning w where w.statments=0";
		//计算出四种等级的告警数量的比例
		String hql_0 = "select count(*) from Warning w where w.statments=0";
		String hql_1 = "select count(*) from Warning w where w.statments=1";
		String hql_2 = "select count(*) from Warning w where w.statments=2";
		String hql_3 = "select count(*) from Warning w where w.statments=3";
		Session session = getSession();
		session.clear();
		Query query = session.createQuery(hql).setFirstResult(page.getPageNo()).setMaxResults(page.getPageCount());
		@SuppressWarnings("unchecked")
		List<Warning> ws = query.list();
		Query query_0 = session.createQuery(hql_0);
		Query query_1 = session.createQuery(hql_1);
		Query query_2 = session.createQuery(hql_2);
		Query query_3 = session.createQuery(hql_3);
		int oneIndex = ((Long)query_0.list().get(0)).intValue();
		int twoIndex = ((Long)query_1.list().get(0)).intValue();
		int threeIndex = ((Long)query_2.list().get(0)).intValue();
		int fourIndex = ((Long)query_3.list().get(0)).intValue();
		int total = oneIndex+twoIndex+threeIndex+fourIndex;
		int onesc =(int)(100* oneIndex / (total*1.0));
		int twosc =(int)((100* twoIndex / (total*1.0)));
		int threesc =(int)(100* threeIndex / (total*1.0));
		int foursc =(int)(100* fourIndex / (total*1.0));
		session.close();
		WarningIndex warningIndex = new WarningIndex();
		warningIndex.setWarnings(ws);
		warningIndex.setOneIndex(onesc);
		warningIndex.setTwoIndex(twosc);
		warningIndex.setThreeIndex(threesc);
		warningIndex.setFourIndex(foursc);
		log.info("***warnIndex=**********"+gson.toJson(warningIndex));
		return warningIndex;
	}
	
	
	//获取未处理 处理中 处理完的数据的数量
	//@Test
	public RateOfwas getNumOfDifWars(){
		//未处理的数量
		String sql1 = "select count(*) from Warning w where w.statments=0";
		String sql2 = "select count(*) from Warning w where w.statments=1 or w.statments=2 or w.statments=3";
		String sql3 = "select count(*) from Warning w where w.statments=4";
		Session session = getSession();
		session.clear();
		Query query_1 = session.createQuery(sql1);
		Query query_2 = session.createQuery(sql2);
		Query query_3 = session.createQuery(sql3);
		int warnings_nodeals = ((Long)query_1.list().get(0)).intValue();
		int warnings_dealing = ((Long)query_2.list().get(0)).intValue();
		int warnings_dealed = ((Long)query_3.list().get(0)).intValue();
		RateOfwas r = new RateOfwas();
		r.setWarningsNoDeal(warnings_nodeals);
		r.setWarningDealing(warnings_dealing);
		r.setWaaringDealed(warnings_dealed);
		session.close();
		log.info("********告警数量分布=***********"+gson.toJson(r));
		return r;
	}
	
	
	
	
	
	
}
