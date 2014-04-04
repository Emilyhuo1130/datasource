package com.zhang.javaTest.methodTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.json.JSONObject;

import com.google.gson.Gson;

public class MethodRun {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// TODO Auto-generated method stub
		Class classType=Class.forName("com.zhang.javaTest.methodTest.MethodUtils");
		Object actionManage=classType.newInstance();
		Point p=new Point("zhang","22");
		
		Gson gson=new Gson();
		String m=gson.toJson(p);
		
		Class reqClass = Class.forName("com.zhang.javaTest.methodTest.Point");
		Method method=classType.getMethod("outsomething", reqClass);
		
		Object ss=method.invoke(actionManage, m);
		String str=JSONObject.fromObject(ss).toString();
		System.out.println(str);
	}

}
