package com.testAop;

import java.util.List;

import javax.servlet.ServletResponseWrapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.filter.SysContent;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

@Component
@Aspect
public class TransactionBean {
	Logger log=Logger.getLogger(TransactionBean.class);
	@Around("within(com.dao.ManageDao)")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable{
		
		
		Gson gson=new Gson();
		
		Object obj2=pjp.getArgs();
		log.info("获取切入点参数"+gson.toJson(obj2));
		HttpServletRequest request=SysContent.getRequest();
		HttpServletResponse response=SysContent.getResponse();
		String userName=(String)request.getSession().getAttribute("Log_name");//登录人
		String url=request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/")+1);
		log.info(request.getRequestURL()+"请求资源方法："+url);
		
		//根据用户名字找到数据库里他的资源列表
		List<String> list=Lists.newArrayList("add.do","userMain.do","getAllUserInfo.do","getSelect.do");
		boolean find=list.contains(url);
		Object obj=null;
		if(find){
			obj=pjp.proceed();//前往方法去执行 回调函数
			log.info("执行结束后的返回值："+obj);
		}else{
			response.sendRedirect("/SpringAop/jsp/noPower.jsp");//权限不够可以转发到
		}
		
		
		
		String name =pjp.getTarget().getClass().getName();
		String method=pjp.getSignature().getName();
		log.info("处理类："+name+"     处理方法："+method);
		
		
		return obj;
	}
}
