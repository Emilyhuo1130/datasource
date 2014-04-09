package com.yihaomen.test;

import org.apache.ibatis.session.SqlSession;

import com.yihaomen.mybatis.inter.IUserInfoOperation;
import com.yihaomen.mybatis.model.UserInfo;

public class MyIbatisTest2 extends MyIbatisBase{

	/**
	 * @param args
	 * 面向接口的方式
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//sqlsessionFactory.getConfiguration().addMapper(IUserInfoOperation.class);
		SqlSession session = sqlsessionFactory.openSession();
		try{
			IUserInfoOperation useroperation = session.getMapper(IUserInfoOperation.class);
			UserInfo user = useroperation.selectUserByID(4);
			System.out.println(user.getName());
			UserInfo user2 = useroperation.selectUserByName("zhang");
			System.out.println(user2.getAge());
		}finally{
			session.close();
		}
	}

}
