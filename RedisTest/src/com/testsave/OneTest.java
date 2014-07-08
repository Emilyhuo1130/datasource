package com.testsave;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import redis.clients.jedis.Jedis;

import com.bean.FirstProtobuf;
import com.bean.UserInfo;
import com.google.gson.Gson;
import com.utils.DButil;
import com.utils.SerializeUtil;

public class OneTest {

	public static void main(String[] args) {
		Gson gson= new Gson();
		Jedis connection = DButil.getConnection();
		long start;	long end ;
		/* start= (new Date()).getTime();
		//使用gson序列化对象 单条存储
		for(int i= 0;i<10000;i++){
			UserInfo info = new UserInfo();
			info.setAge(i);
			info.setName("zhang"+i);
			connection.set("my"+i,gson.toJson(info));
		}
		connection.save();
		end =(new Date()).getTime();
		System.out.println(end-start);
		
		//--------------使用Serialize存储序列化对象------------------------------------
		start= (new Date()).getTime();
		for(int i= 0;i<10000;i++){
			UserInfo info = new UserInfo();
			info.setAge(i);
			info.setName("zhang"+i);
			connection.set(("tt"+i).getBytes(),SerializeUtil.serialize(info));
		}
		end =(new Date()).getTime();
		System.out.println(end-start);
		
		
		
		//--------------Protobuf序列化对象存储--------------------------------------
		start= (new Date()).getTime();
		FirstProtobuf.testBuf.Builder builder=FirstProtobuf.testBuf.newBuilder();
		for(int i= 0;i<10000;i++){
			builder.setID(i);
			builder.setUrl("zhang"+i);
			FirstProtobuf.testBuf info=builder.build();
			connection.set(("ss"+i).getBytes(),SerializeUtil.serialize(info));
		}
		end =(new Date()).getTime();
		System.out.println(end-start);
		*/
		
		//--------------反序列 list集合 存储--------------------------------------
		start= (new Date()).getTime();
		List<UserInfo> list =new ArrayList<UserInfo>();
		for(int i= 0;i<1000000;i++){
			UserInfo info = new UserInfo();
			info.setAge(i);
			info.setName("zhang"+i);
			list.add(info);
			//connection.rpush("list".getBytes(), SerializeUtil.serialize(info));
		}
		
		connection.set("list".getBytes(), SerializeUtil.serialize(list));
		end =(new Date()).getTime();
		System.out.println(end-start);


	}

}
