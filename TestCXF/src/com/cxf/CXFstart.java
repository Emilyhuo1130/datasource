package com.cxf;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.xml.ws.Endpoint;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;

import com.cxf.server.HelloDao;

public class CXFstart {
	 private static final long serialVersionUID = -5314312869027558456L;  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 	System.out.println("Server is starting...");  
		 	HelloDao readerService = new HelloDao();  
	        Endpoint.publish("http://localhost:8082/readerService",readerService);  
	        System.out.println("Server is started...");  
	}
	
	/*@Override  
    protected void loadBus(ServletConfig servletConfig) {  
		 super.loadBus(servletConfig);  
	        System.out.println("#####################");  
	        ServerFactoryBean svrFactory = new ServerFactoryBean();
	        svrFactory.setServiceClass(HelloDao.class);
	        svrFactory.setAddress("/passportService");
	        svrFactory.setInvoker(new BeanInvoker(new HelloDao()));
	        Server server = svrFactory.create();
	        Endpoint cxfEndpoint = server.getEndpoint();
	        Map<String, Object> inProps = new HashMap<String, Object>();
	        inProps.put(WSHandlerConstants.ACTION,
	                WSHandlerConstants.USERNAME_TOKEN);
	        inProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_DIGEST);
	        inProps.put(WSHandlerConstants.PW_CALLBACK_CLASS,
	                ServerPasswordHandler.class.getName());
	        cxfEndpoint.getInInterceptors().add(new WSS4JInInterceptor(inProps));
	}*/

}
