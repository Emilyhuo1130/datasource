package com.zhang.cxf;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.cxf.interFace.Hello;



public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();  
        factoryBean.setServiceClass(Hello.class);  
        factoryBean.setAddress("http://localhost:8082/readerService");  
        Hello readerService = (Hello)factoryBean.create();  
        String reader = readerService.sayHello("zhang");  
        System.out.println("Reader:"+reader);  
	}

}
