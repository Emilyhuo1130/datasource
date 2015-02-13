package com.testsave;

import java.util.Date;
import java.util.List;

import redis.clients.jedis.Jedis;

import com.bean.UserInfo;
import com.utils.DButil;
import com.utils.SerializeUtil;

public class GetData {

	public static void main(String[] args) {
		long start =new Date().getTime();
		Jedis connection = DButil.getConnection();
		@SuppressWarnings("unchecked")
		List<UserInfo> list = (List<UserInfo>)SerializeUtil.unserialize(connection.get("list".getBytes()));
		int j=0;
		for(int i=0;i<list.size();i++){
			j++;
		}
		System.out.println(j);
		System.out.println(new Date().getTime()-start);
	}

}
