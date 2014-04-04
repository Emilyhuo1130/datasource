package com.mvcTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;
import org.nutz.mvc.view.UTF8JsonView;
import org.nutz.mvc.view.ViewWrapper;

import com.datasource.Dataource;
import com.pojo.Person;
/***视图***/
public class LogServer extends Dataource{
	/**jsp视图返回**/
	@At("/Test/hello")
	@Ok("jsp:jsp.hello")//申明为jsp视图,返回的视图为WEB-INF/jsp/hello.jsp
	public Person login(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException{
		SimpleDataSource ds=getdateSource();
		Dao dao=new NutDao(ds);
		Person p=dao.fetch(Person.class,5);
		return p;
	}
	/***json视图返回***/
	@At("/Test/json")
	@Ok("json")//申明为json视图
	public Person json(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException{
		SimpleDataSource ds=getdateSource();
		Dao dao=new NutDao(ds);
		Person p=dao.fetch(Person.class,5);
		return p;
	}
	/**根据参数返回视图**/
	@At("/Test/myurl")//根据参数类型选择返回视图
	public View chose(@Param("type") int type){
		if(type==0)
			return new ViewWrapper(new UTF8JsonView(null), "It is zero!");
		
		if(type<10)
			return new ViewWrapper(new UTF8JsonView(null), "It less than 10!");
		
		return new ViewWrapper(new JspView("mypage.showNumber"), type);
	}
	
	/**不指向页面根据请求自动训寻返回页面*/
	@At("/jsp/hello")
	@Ok("jsp")//不指定返回页面，会自动根据请求地址去找WEB-INF/jsp/hello.jsp
	@Fail("jsp:jsp.error")
	public Person hello1(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException{
		SimpleDataSource ds=getdateSource();
		Dao dao=new NutDao(ds);
		Person p=dao.fetch(Person.class,5);
		return p;
	}
	
	/**返回页面不在WEB-INF下的，自定义返回路径**/
	@At("/jsp/hello2")
	@Ok("jsp:/jsp/hello")//这时候对应到/jsp/hello.jsp
	@Fail("jsp:jsp.error")
	public Person hello2(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException{
		SimpleDataSource ds=getdateSource();
		Dao dao=new NutDao(ds);
		Person p=dao.fetch(Person.class,5);
		return p;
	}
	
	/**视图重定向**/
	@At("/Test/redirect1")//没有参数的重定向
	@Ok("redirect:/index.jsp")
	@Fail("jsp:jsp.error")
	public Person redirect(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException{
		SimpleDataSource ds=getdateSource();
		Dao dao=new NutDao(ds);
		Person p=dao.fetch(Person.class,5);
		return p;
	}
	
	@At("/Test/redirect2")//带参数的重定向
	@Ok("redirect:/Test/myurl.nut?type=${obj}")//还有一种写法@Ok(">>:/Test/myurl.nut?type=${obj}")
	@Fail("jsp:jsp.error")
	public int redirect2(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException{
		
		return 2;
	}
	
	/**内部重定向**地址栏地址不会变，只在服务器内部做重定向*/
	@At("/Test/redirect3")
	@Ok("forward:/Test/myurl.nut?type=${obj}")//还有一种写法@Ok(">>:/Test/myurl.nut?type=${obj}")
	@Fail("jsp:jsp.error")
	public int redirect3(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException{
		
		return 2;
	}
	
	/**http返回码视图***/
	@At("/Test/http404")
	@Ok("http:404")
	public int http404(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException{
		return 2;
	}
	
	/**空白 视图***/
	@At("/Test/void")
	@Ok("void")
	public int none(HttpServletRequest req,HttpServletResponse res) throws ClassNotFoundException{
		return 2;
	}
}
