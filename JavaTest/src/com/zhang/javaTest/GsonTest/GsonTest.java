package com.zhang.javaTest.GsonTest;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class GsonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson=new Gson();
		/**字符转list**/
		 List<Person>  adlist=gson.fromJson("List to json String",  
	               new TypeToken<List<Person>>() {  
	       }.getType());  
		 
		 Person p=gson.fromJson("json String", Person.class);
		 gson.toJson(p);
		 
		 
	}

}
