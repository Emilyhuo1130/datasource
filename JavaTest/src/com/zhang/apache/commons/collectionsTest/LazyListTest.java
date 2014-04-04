package com.zhang.apache.commons.collectionsTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.list.TreeList;

import com.google.common.collect.Lists;

public class LazyListTest {

	/**
	 * 懒List，当取值超出界限，会把factory中的值加载到list中，同时list的大小也发生了变化。
	 * 超出的值全部由factory中的值代替
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Factory factory2=new Factory(){
			public Object create() {
				return null;
			}
		 };
		 List<String> list=Lists.newArrayList();
		 list.add("333");
		 list.add("444");
		 list.add("555");
		 List<String> lazy = LazyList.decorate(list, factory2);//创建list
		 Object obj = lazy.get(10);
		 if(obj==null){
			 System.out.println(obj);
		 }
		 lazy.add("666");
		 System.out.println(lazy.size());
		 for(String ss:lazy){
			 System.out.println(ss);
		 }
		
		 
		 
		 List list2=Lists.newArrayList(new User("zhang","123456"),new User("zz","zz"));
		 List<User> lazy2=LazyList.decorate(list2, factory2);
		 System.out.println(lazy2.contains(new User("zz","zz")));
		 
		 String sst="999999";
		 sst.substring(0, 2);
	}

}


class User{
	private String name;
	private String password;
	public User(String name,String password){
		this.name=name;this.password=password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}