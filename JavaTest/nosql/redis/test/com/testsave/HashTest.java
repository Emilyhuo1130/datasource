package com.testsave;

import java.util.HashMap;
import java.util.Map;

import redis.clients.jedis.Jedis;

import com.bean.UserInfo;
import com.utils.DButil;
import com.utils.SerializeUtil;

public class HashTest {
	/**
	 * redis hash map的测试
	 * **/
	public static void main(String[] args) {
		 Map<String, Object> pairs = new HashMap<String, Object>();  
		 UserInfo info=new UserInfo();
		 info.setAge(22);
		 info.setName("zhang");
		 pairs.put("zhang",info);
		 info.setAge(22);
		 info.setName("hao");
		 pairs.put("hao",info);
		 Jedis connection = DButil.getConnection();
		 connection.set("userList".getBytes(), SerializeUtil.serialize(pairs));
	}

}
