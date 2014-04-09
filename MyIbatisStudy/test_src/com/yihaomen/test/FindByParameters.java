package com.yihaomen.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yihaomen.mybatis.inter.IUserInfoOperation;
import com.yihaomen.mybatis.model.Page_;
import com.yihaomen.mybatis.model.UserInfo;
import com.yihaomen.mybatis.req.ReqGetUser;

public class FindByParameters extends MyIbatisBase{

	/**
	 * @param args
	 * ¶¯Ì¬²éÑ¯
	 */
	public static void main(String[] args) {
		SqlSession session = sqlsessionFactory.openSession();
		try{
			IUserInfoOperation operation = session.getMapper(IUserInfoOperation.class);
			int pageCount=1;
			int pageSize=2;
			Page_ page=new Page_();
			page.setOffset((pageCount-1)*pageSize);
			page.setPageSize(pageSize);
			UserInfo user =new UserInfo();
			user.setName("zhangsan");
			ReqGetUser req=new ReqGetUser();
			
			req.setPage(page);
			req.setUser(user);
			List<UserInfo> userlist = operation.getAllUserPertame(req);
			for(UserInfo info:userlist){
				System.out.println(info.getName());
			}
		}finally{
			session.close();
		}
	}

}
