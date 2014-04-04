package com.ucs.rcm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import net.sf.json.JSONObject;



import com.ucs.rcm.db.DBUtil;
import com.ucs.rcm.reqobj.HttpRequest;
import com.ucs.rcm.reqobj.ReqgetWarningCounting;

/**
 * Servlet implementation class Service
 */
//@WebServlet("/Service")

public class Service extends HttpServlet {
	static Logger log = Logger.getLogger(Service.class);
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		PropertyConfigurator.configure(System.getProperty("user.dir")+"/WEB-INF/log4j.properties");
	
		RcmUtils.getStationMap( );
		log.warn("db is connected:"+DBUtil.LoadProperties().getJdbcUrl());
	}



	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Service() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("testget");
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin","*");
		
		
		
		try {
			// get inputStream
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();

			log.warn("RemoteHost:----------------"+request.getRemoteHost());
			StringBuffer inputString = new StringBuffer();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					request.getInputStream(), "UTF-8"));
			String s = reader.readLine();
			while (s != null) {
				System.out.println("readLine:"+s);
				inputString.append(s);
				s = reader.readLine();
			}

			log.info("请求报文: " + inputString.toString());
			

			HttpRequest httpRequest = (HttpRequest) JSONObject.toBean(
					JSONObject.fromObject(inputString.toString()),
					HttpRequest.class);

			// HttpRequest httpRequest = (HttpRequest) JSONObject
			// .toBean(JSONObject.fromObject(ReqgetUserInfo.getUserInfoReq()),
			// HttpRequest.class);

			try {
				System.out.println("try");

				Class reqClass = Class
						.forName("com.ucs.rcm.reqobj."
								+ "Req" + httpRequest.getMethod());
				//System.out.println("----------------------Class reqClass： "+httpRequest.getMethod());
				
				Class businessClass = Class
						.forName("com.ucs.rcm.business."
								+ httpRequest.getType());
				//System.out.println("----------------------Class businessClass:  "+httpRequest.getType());
				
				//将json对象转化为bean对象
				Object m = JSONObject.toBean(
						JSONObject.fromObject(httpRequest.getReq()), reqClass);
				//创建此 Class 对象所表示的类的一个新实例
				Object actionManager = businessClass.newInstance();
				//设置一个方法的方法名和参数列表
				Method method = businessClass.getMethod(httpRequest.getMethod(), reqClass);
				//启动方法，（actionManager是这个方法所在的类，m是实体类，里面是参数），取得返回值  格式为Object
				Object o = (method.invoke(actionManager, m));
				log.info("*********object o="+o.getClass()+"*********");
				String result = JSONObject.fromObject(o).toString();
				log.info("***********json转化为字符串:******************"+result);

				// result = new String(result.getBytes(), "UTF-8");
				//以json对象输出到JS中的response
				/*if(httpRequest.getMethod().equals("getEquipmentTree")){
					int a=result.indexOf("[");
					int b=result.lastIndexOf("]");
					out.print(result.substring(a, b+1));
				}else{
					out.print(result);
				}*/
				out.print(result);
				
				log.info("返回报文: " + result);
				
				
			
				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("类或者方法没找到,请检查type 或者method参数 是否正确"+RcmUtils.getTrace(e));
				

			}

			out.flush();
			out.close();
		} catch (Exception e) {
			log.error("请求失败或josn对象异常：" + RcmUtils.getTrace(e));
		}
	}
	
	public static void main(String args[]){
		
		
		HttpRequest httpRequest =  new HttpRequest();
		httpRequest.setMethod("method:getWarningCounting");
		httpRequest.setType("WarningManager");
		ReqgetWarningCounting  req =  new ReqgetWarningCounting();
		req.setReqString("aa");
		httpRequest.setReq(req);
		String r = JSONObject.fromObject(httpRequest).toString();
		System.out.println(r);
		
		String reqString = "{method:getWarningCounting,req:{reqString:aa},type:WarningManager}";
		 httpRequest = (HttpRequest) JSONObject.toBean(
				JSONObject.fromObject(r),
				HttpRequest.class);
		
		System.out.println(httpRequest.getType()+"  :  "+httpRequest.getMethod());
		
		
	}

}
