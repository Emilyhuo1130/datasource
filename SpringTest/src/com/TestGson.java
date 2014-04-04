package com;

import org.apache.log4j.Logger;

import com.bo.form.UserForm;
import com.google.gson.Gson;


public class TestGson {
	static Logger log = Logger.getLogger("");

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//业务对象
		UserForm user =  new UserForm();
		user.setName("gao");
		user.setPassword("1111");
		
		
		//序列化为JSON数据
		Gson  gson =  new Gson();
		String r = gson.toJson(user);
		System.out.println(r);
		//OFF、FATAL、ERROR、WARN、INFO、DEBUG/trace、ALL
		log.trace("This is userFrom Json string:"+r);
		log.info("This is userFrom Json string:"+r);
		log.error("This is userFrom Json string:"+r);
		
		//Json数据反序列化对象
		UserForm user1 =  new UserForm();
		user1 = gson.fromJson(r, UserForm.class);
		System.out.println(user1.getName());
		

	}

}
