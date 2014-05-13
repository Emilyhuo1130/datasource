package com.think.arrayListTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.think.factoryTest.FactoryImpl;

public class TestList {

	private static Method method;

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		List<String> list=Lists.newArrayList("444","5555");
		list.add("444");
		
		List<Integer> intList =new LinkedList<Integer>();
		intList.add(1);
		intList.add(2);
		intList.remove(0);
		System.out.println(intList.get(0));;
		
		Iterator<Integer> iterator = intList.iterator();
		System.out.println(Arrays.toString("4djshjfhskjfhdi4".toCharArray()));
		
		get();
		
		System.out.format("select * from %s where name like %s", "userinfo","zhang\n");
		System.out.println("--------------------");
		Class forName =  Class.forName("com.think.factoryTest.FactoryImpl");
		Object newInstance = forName.newInstance();
		method = forName.getMethod("getString");
		Object invoke = method.invoke(newInstance);
		System.out.println(invoke);
	}
	
	public static void get(){
		try{
			System.out.println("get......");
			return;
		}catch(Exception e){
			
		}finally{
			System.out.println("finally");
		}
	}

}
