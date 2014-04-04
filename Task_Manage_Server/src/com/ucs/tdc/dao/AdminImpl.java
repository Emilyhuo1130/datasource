package com.ucs.tdc.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.ucs.tdc.interFace.Admin;
import com.ucs.tdc.pojo.AdminInfo;
import com.ucs.tdc.pojo.UserInfo;

@Repository("AdminImpl")
public class AdminImpl extends HibernateDaoSupport implements Admin{
	static Logger log = Logger.getLogger(AdminImpl.class);
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
	/****普通用户登录验证**/
	public Boolean verifyuser(UserInfo info) {
		log.info("普通用户登录验证: 用户名："+info.getUserAccount()+"   密码:"+info.getUserPw());
		boolean flag=false;
		String hql="from UserInfo where userAccount=?";
		 Object[] params={info.getUserAccount()};
		 List<UserInfo> list =this.getHibernateTemplate().find(hql,params);
		 System.out.println(list.size());
		 UserInfo var_info;
		 if(list.size()!=0){
			 var_info =list.get(0);
			 if(var_info.getUserPw().equals(info.getUserPw())){
				 flag=true;
			 }else{
				 flag=false;
			 }
		 }else{
			 flag=false;
		 }
		 log.info("普通用户登录验证: 是否通过验证"+flag);
		return flag;
	}
	
	/**管理员登录验证***/
	public Boolean verifyAdmin(AdminInfo info) {
		log.info("管理员登录验证: 用户名："+info.getUserName()+"   密码:"+info.getUserPw());
		boolean flag=false;
		String hql="from AdminInfo where userName=?";
		 Object[] params={info.getUserName()};
		 List<AdminInfo> list =this.getHibernateTemplate().find(hql,params);
		 AdminInfo var_info;
		 if(list.size()!=0){
			 var_info =list.get(0);
			 if(var_info.getUserPw().equals(info.getUserPw())){
				 flag=true;
			 }else{
				 flag=false;
			 }
		 }else{
			 flag=false;
		 }
		 log.info("管理员用户登录验证: 是否通过验证"+flag);
		return flag;
	}
	/**管理员修改密码前的密码验证***/
	public Boolean do_verifyAdminPW(String adminName, String password) {
		// TODO Auto-generated method stub
		String hql="from AdminInfo where userName= '"+adminName+"' ";
		List<AdminInfo> list=getHibernateTemplate().find(hql);
		AdminInfo info=list.get(0);
		if(info.getUserPw().equals(password)){
			return true;
		}else{
			return false;
		}
	}
	/**通过账户名获取管理员账户信息***/
	public AdminInfo do_findAdminInfoByAccount(String adminName) {
		// TODO Auto-generated method stub
		String hql="from AdminInfo where userName= '"+adminName+"' ";
		List<AdminInfo> list=getHibernateTemplate().find(hql);
		AdminInfo info=list.get(0);
		return info;
	}
	/**管理员修改自己密码**/
	public Boolean do_modifyAdminInfo(AdminInfo info) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(info);
		return true;
	}

	/***管理员添加用户**/
	public Boolean do_addUser(UserInfo info) {
		getHibernateTemplate().save(info);
		return true;
	}
	/***管理员-更新用户**/
	public Boolean do_updateUser(UserInfo info) {
		getHibernateTemplate().update(info);
		return true;
	}
	/***管理员 删除普通用户**/
	public Boolean do_deleteUser(UserInfo info) {
		if(info.getId()!=0){
			getHibernateTemplate().delete(info);
		}
		return true;
	}
	
	
	/**查看用户***/
	public List<UserInfo> do_findAllUser(String userName, String userAccount,
			final int pageSize, final int page) {
		StringBuffer buf=new StringBuffer();
		buf.append(" from UserInfo where 1=1 ");
		if((userName!=null)&&(userName.length()>0)){
			buf.append(" and userName= '"+userName +"' ");
		}
		if((userAccount!=null)&&(userAccount.length()>0)){
			buf.append(" and userAccount= '"+userAccount +"' ");
			
		}
		final String hql=buf.toString();
		
		List list=(List) this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Query query=session.createQuery(hql);
				int count=(page-1)*pageSize+1;
				query.setFirstResult((page-1)*pageSize);
				query.setMaxResults(pageSize);
				List<UserInfo> list=query.list();
				return list;
			}
		});
		return list;
	}
	/***返回共几页**/
	public int do_userspages(int pageSize, String userName, String userAccount) {
		// TODO Auto-generated method stub
		String hql="select count(*) from UserInfo where 1=1 ";
		if((userName!=null)&&(userName.length()>0)){
			hql=hql+" and userName like '%"+userName+"%' ";
		}
		if((userAccount!=null)&&(userAccount.length()>0)){
			hql=hql+" and userAccount like '%"+userAccount+"%' ";
		}
		List<Object> list=this.getHibernateTemplate().find(hql);
		 Object obj=(Object)list.get(0);
		 int totalRows=Integer.parseInt(obj.toString());
		 if (totalRows % pageSize == 0) {
            return totalRows / pageSize;
        } else {
            return totalRows / pageSize + 1;
        }
	}
	/***管理员要修改某个人的信息  返回对应id的信息**/
	public UserInfo do_findAllUserbyId(int id) {
		// TODO Auto-generated method stub
		UserInfo info=getHibernateTemplate().get(UserInfo.class, id);
		return info;
	}
	
	/****根据用户名查询姓名*/
	public String do_findUserName(String accountName) {
		// TODO Auto-generated method stub
		String hql="select userName from UserInfo where userAccount= '"+accountName+"' ";
		List<String> list=getHibernateTemplate().find(hql);
		String userName=list.get(0);
		return userName;
	}

	

	
	
	
	
}
