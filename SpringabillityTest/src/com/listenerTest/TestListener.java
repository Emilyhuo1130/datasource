package com.listenerTest;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;


@WebListener
public class TestListener implements ServletContextListener{
	Logger log=Logger.getLogger(TestListener.class);
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Timer timer =new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				log.info("延迟启动定时器开始");
				
				HttpClient client = new HttpClient(); 
				String url = "http://localhost:8080/SpringabillityTest/Listener.do?";
				PostMethod method = new UTF8PostMethod(url);
				//method.setParameter("accountName", "chenhao");
				try {
					client.executeMethod(method);
				} catch (HttpException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(method.getStatusLine());  
		        //打印返回的信息  
		       // System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
		        method.releaseConnection(); 
				
			}
		}, 15000);
		
	}
	/***重写编码设置**/
	public static class UTF8PostMethod extends PostMethod{
        public UTF8PostMethod(String url){
            super(url);
        }
        @Override
        public String getRequestCharSet() {
            //return super.getRequestCharSet();
            return "UTF-8";
        }
    }

}
