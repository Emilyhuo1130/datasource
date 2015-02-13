package com.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;

import com.bean.UserInfo;
import com.google.gson.Gson;
import com.utils.DButil;
import com.utils.SerializeUtil;

public class RedisTest1 {

	public static void main(String[] args) {
		Jedis jedis = DButil.getConnection();
		long t1 = System.currentTimeMillis();
		List list=new ArrayList();
		for(int i=0;i<100000;i++){
			UserInfo info  = new UserInfo();
			info.setName("zhang");
			info.setAge(22);
			list.add(info);
			jedis.set(("mykey"+i).getBytes(), SerializeUtil.serialize(info));
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1); //22833
	}
	
	
	@Test
	public void testSerialize(){
		List list=new ArrayList();
		for(int i=0;i<100000;i++){
			UserInfo info  = new UserInfo();
			info.setName("zhang");
			info.setAge(22);
			list.add(info);
		}
		byte[] serialize = SerializeUtil.serialize(list);
		System.out.println(serialize.length);//84  1500123
		Gson gson=new Gson();
		byte[] bytes = gson.toJson(list).getBytes();
		System.out.println(bytes.length);//25  2600001
		byte[] serialize2 = SerializeUtil.serialize(gson.toJson(list));
		System.out.println(serialize2.length);//32  2600014
	}

}
