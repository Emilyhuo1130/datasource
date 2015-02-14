package com.test.aop;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.test.filter.SysContent;

@Component
@Aspect
public class TransactionBean {
	Logger log=Logger.getLogger(TransactionBean.class);
	@Around("within(com.test.aop.service.imp.AOPService)")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable{
		Gson gson=new Gson();
		Object obj2=pjp.getArgs();
		log.debug("获取切入点参数:     "+gson.toJson(obj2));
		HttpServletRequest request=SysContent.getRequest();
		HttpServletResponse response=SysContent.getResponse();
//		String userName=(String)request.getSession().getAttribute("Log_name");//登录人
//		String url=request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1);
//		log.info(request.getRequestURL()+"请求资源方法："+url);
		//根据用户名字找到数据库里他的资源列表
		Object obj=null;
		try{
			log.debug("接受从拦截器获取的 request：URL    "+request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1));
			obj=pjp.proceed();//前往方法去执行 回调函数
			log.debug("执行结束后的返回值："+obj);
			
		}catch(Exception e){
//			response.sendRedirect("/SpringAop/jsp/noPower.jsp");//权限不够可以转发到
			
		}
		
		
		
		String name =pjp.getTarget().getClass().getName();
		String method=pjp.getSignature().getName();
		log.debug("处理类："+name+"     处理方法："+method);
		
		
		return obj;
	}
}
