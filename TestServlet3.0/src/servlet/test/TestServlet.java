package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void init(ServletConfig config){
		System.out.println("start init");
	}
	@Override
	public void doPost(HttpServletRequest requert,HttpServletResponse response){
		System.out.println("doPost........");
	}
	@Override
	public void doGet(HttpServletRequest requert,HttpServletResponse response) throws IOException{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.print("进入Servlet的时间"+new Date());
			out.flush();
			AsyncContext ctx=requert.startAsync();//servlet 的异步
			
			new Thread(new Executor(ctx)).start();
			out.print("Servlet结束"+new Date());
			out.flush();
		
	}
	public TestServlet(){
		super();
	}
	
}
