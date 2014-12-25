package com.proxytest;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/***
 * 	CGLIB实现的动态代理
 * 
 * */
public class CglibDBQueryInterceptor implements MethodInterceptor {
	IDBQuery real=null;
	@Override
	public Object intercept(Object arg0, Method method, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		System.out.println(method.getName());
		if(real==null)
			real=new DBQuery();
		return real.request();
	}

}
