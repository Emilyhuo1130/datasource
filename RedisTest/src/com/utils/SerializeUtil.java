package com.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;

import com.bean.FirstProtobuf;
import com.bean.UserInfo;
import com.google.protobuf.InvalidProtocolBufferException;

public class SerializeUtil {
	public static byte[] serialize(Object object) {
		try {
			//序列化
			ByteArrayOutputStream bos = new ByteArrayOutputStream();  
			ObjectOutputStream os = new ObjectOutputStream(bos);  
			os.writeObject(object);  
			os.flush();  
			os.close();  
			byte[] bytes = bos.toByteArray();  
			bos.close();  
			return bytes;
		} catch (Exception e) {
			 
		}
		return null;
	}
	
	public static Object unserialize(byte[] bytes) {
		ByteArrayInputStream bais = null;
		try {
			//反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			ois.close();
			bais.close();
			return ois.readObject();
		} catch (Exception e) {
		 
		}
		return null;
	}
	
	public static void main(String[] args) throws InvalidProtocolBufferException {
		//protoc.exe --java_out=./ test.proto
		//序列化
		FirstProtobuf.testBuf.Builder builder=FirstProtobuf.testBuf.newBuilder();
		builder.setID(777);
		builder.setUrl("shiqi");
		FirstProtobuf.testBuf info2=builder.build();
		byte[] serialize = SerializeUtil.serialize(info2);
		System.out.println(serialize.length);
		byte[] result = info2.toByteArray() ;
		System.out.println(result.length);
		
		//Jedis connection = DButil.getConnection();
		//connection.set("my".getBytes(), result);
		
	//	byte[] bs = connection.get("my".getBytes());
		
		
		//反序列化
		FirstProtobuf.testBuf testBuf = FirstProtobuf.testBuf.parseFrom(result);
		System.out.println(testBuf.getUrl());
	}
	
}
