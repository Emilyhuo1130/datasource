package com.ucs.cctv.Implement_Dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.ucs.cctv.Interface_Dao.LoginManage;
import com.ucs.cctv.Pojo.AdminInfo;
import com.ucs.cctv.Pojo.OperatorInfo;
@Repository("LoginManageImpl")
public class LoginManageImpl extends HibernateDaoSupport implements LoginManage{
	static Logger log = Logger.getLogger(LoginManageImpl.class);
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	Gson gson = new Gson();
	
	public Boolean addAdmin(AdminInfo admin) {
		getHibernateTemplate().save(admin);
		return true;
	}

	public Boolean adminLoginVerify(String adminAccount, String adminPw) {
		List<AdminInfo> info = getHibernateTemplate().find("from AdminInfo where adminAccount=?",adminAccount);
		log.info(adminAccount);
		log.info(adminPw);
		log.info("info.size="+info.size());
		log.info("pw="+adminPw+","+info.get(0).getAdminPw());
		if(info.size()==0){
			return false;
		}else if(info.get(0).getAdminPw().equals(adminPw))
		{
			return true;
		}else{
			return false;
		}
	}

	public Boolean updateAdminInfo(AdminInfo admin) {
		getHibernateTemplate().update(admin);
		return true;
	}

	public Boolean operatorLoginVerify(String operatorAccount, String operatorPw) {
		List<OperatorInfo> info = getHibernateTemplate().find("from OperatorInfo where operatorAccount=?",operatorAccount);
		if(info.size()==0){
			return false;
		}else if(info.get(0).getOperatorPw().equals(operatorPw))
		{
			return true;
		}else{
			return false;
		}
	}

}
