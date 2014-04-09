package com.yihaomen.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.yihaomen.mybatis.model.UserInfo;

public class MyIbatisTest extends MyIbatisBase{

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SqlSession session = sqlsessionFactory.openSession();
		try{
			UserInfo user=(UserInfo)session.selectOne("com.yihaomen.mybatis.inter.IUserInfoOperation.selectUserByID",4);
			System.out.println(user.getName());
			UserInfo selectOne = (UserInfo)session.selectOne("com.yihaomen.mybatis.inter.IUserInfoOperation.selectUserByName","zhang");
			System.out.println(selectOne.getAge());
			
		}finally{
			session.close();
		}
	}

}
