package com.yihaomen.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.google.common.collect.Lists;
import com.yihaomen.mybatis.inter.IUserInfoOperation;
import com.yihaomen.mybatis.model.UserInfo;

public class ForEachTest extends MyIbatisBase {

	/**
	 * @param args
	 * sql in ”Ôæ‰µƒ≤‚ ‘   π”√forEach£¨
	 */
	public static void main(String[] args) {
		SqlSession session = sqlsessionFactory.openSession();
		try{
			IUserInfoOperation operation = session.getMapper(IUserInfoOperation.class);
			List<Integer> ids=Lists.newArrayList(1,2,3,4,5);
			List<UserInfo> list = operation.getForEachTest(ids);
			for(UserInfo info:list){
				System.out.println(info.getName());
			}
		}finally{
			session.close();
		}
	}

}
