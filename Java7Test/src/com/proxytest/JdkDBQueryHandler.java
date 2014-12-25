package com.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkDBQueryHandler implements InvocationHandler {
	IDBQuery real= null;
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println(method.getName());
		
//		可以使用映射原理进行方法的调用
		if(real==null)
			real=new DBQuery();
		return real.request();
	}

}
