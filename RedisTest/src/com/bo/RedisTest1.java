package com.bo;

import redis.clients.jedis.Jedis;

import com.bean.UserInfo;
import com.utils.DButil;
import com.utils.SerializeUtil;

public class RedisTest1 {

	public static void main(String[] args) {
		Jedis jedis = DButil.getConnection();
		UserInfo info  = new UserInfo();
		info.setName("zhang");
		info.setAge(22);
		
		jedis.set("mykey".getBytes(), SerializeUtil.serialize(info));
		
		
		byte[] bs = jedis.get("mykey".getBytes());
		UserInfo user =(UserInfo) SerializeUtil.unserialize(bs);
		System.out.println(user.getAge());
		
	}

}
