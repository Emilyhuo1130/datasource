package com.utils;

import redis.clients.jedis.Jedis;

public class DButil {
	private final static String host="127.0.0.1";
	private final static int port = 6379;
	
	public static Jedis getConnection(){
		 Jedis jedis =new Jedis(host,port);
		 return jedis;
	}
}
