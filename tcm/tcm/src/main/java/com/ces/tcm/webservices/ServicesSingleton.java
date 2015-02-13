package com.ces.tcm.webservices;

import org.springframework.web.context.WebApplicationContext;

public class ServicesSingleton {
	  private WebApplicationContext servletContext;

	  public WebApplicationContext getServletContext() {
	    return servletContext;
	  }

	  public void setServletContext(WebApplicationContext servletContext) {
	    this.servletContext = servletContext;
	  }

	  private volatile static ServicesSingleton singleton;

	  private ServicesSingleton() {
	  }

	  public static ServicesSingleton getInstance() {
	    if (singleton == null) {
	      synchronized (ServicesSingleton.class) {
	        if (singleton == null) {
	          singleton = new ServicesSingleton();
	        }
	      }
	    }
	    return singleton;
	  }
	  
	  public Object getService(Class<?> classNameOfDAO) {
	    String implName = classNameOfDAO.getSimpleName();
	    String firstChar = implName.substring(1);
	    implName=firstChar;
	    return singleton.getServletContext().getBeansOfType(classNameOfDAO).get(implName);
	  }
}