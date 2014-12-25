package com.proxytest;

import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

public class ProxyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		代理模式的延迟加载
//		IDBQuery q=new DBQueryProxy();
//		System.out.println(q.request());
		
//		jdk自带的动态代理
//		IDBQuery q = createJdkProxy();
//		q.getName();
		
		
		
//		CGLIB的动态代理
		IDBQuery q = createCglibProxy();
		System.out.println(q.request());
	}
	
	/***jdk动态代理*/
	public static IDBQuery createJdkProxy(){
		IDBQuery jdkProxy=(IDBQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
				new Class[]{IDBQuery.class}, new JdkDBQueryHandler());
		return jdkProxy;
	}
	
	/***CGLIB的动态代理**/
	public static IDBQuery createCglibProxy(){
		Enhancer enhancer=new Enhancer();
		enhancer.setCallback(new CglibDBQueryInterceptor());
		enhancer.setInterfaces(new Class[]{IDBQuery.class});
		IDBQuery cglibProxy = (IDBQuery)enhancer.create();
		return cglibProxy;
		
	}
	
	
	

}
