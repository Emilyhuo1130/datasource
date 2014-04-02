package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jws.WebService;
import javax.servlet.ServletConfig;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns ="/demo" ,asyncSupported=true,loadOnStartup=-1,
		initParams={@WebInitParam(name="username",value="tom")})
@MultipartConfig(location="/test.text",maxFileSize=-1,maxRequestSize=-1)//文件上传支持
public class ServletTest2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void init(ServletConfig config){
		System.out.println("init demo");
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		
	}
	@Override
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out=res.getWriter();
		out.print("测试注解的servlet");
	}

}
