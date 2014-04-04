package com.ucs.tdc.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ucs.tdc.interFace.TestInterFace;
import com.ucs.tdc.pojo.AdminInfo;
import com.ucs.tdc.pojo.ProjectInfo;

@Controller
public class TestController {
	@Resource
	private TestInterFace interFace;
	public void setInterFace(TestInterFace interFace) {
		this.interFace = interFace;
	}
	static Logger log = Logger.getLogger(TestController.class);


	/***查询 所有信息  返回json**/
	@RequestMapping(value="jsp/admin/do_getAdminInfos")
	@ResponseBody
	public List<AdminInfo> do_getAdminInfos(@RequestParam String name) {
		List<AdminInfo> infoList;
		infoList=interFace.getAlladminInfo();
		return infoList;
	}
	
	
	/**按照某种条件查询    返回的是单个对象  也可以是一个集合***/
	@RequestMapping(value="jsp/admin/do_getAdminInfoById")
	@ResponseBody
	public List<AdminInfo> do_getAdminInfoById(@RequestParam int id) {
		List<AdminInfo> infoList;
		infoList=interFace.getAlladminInfoById(id);
		return infoList;
	}
	
	/****修改信息***/
	@RequestMapping(value="jsp/user/do_updateinfo")
	@ResponseBody
	public boolean do_updateinfo(@RequestParam int id,@RequestParam String name,@RequestParam String password) {
		boolean b=false;//是否修改成功
		AdminInfo info=new AdminInfo();
		info.setId(id);
		info.setUserName(name);
		info.setUserPw(password);
		b=interFace.updaeinfo(info);
		return b;
	}
	
	
	/**保存信息
	 * @throws IOException 
	 * @throws HttpException ***/
	@RequestMapping(value="jsp/user/do_adduser")
	@ResponseBody
	public boolean do_adduser(@RequestParam String name,@RequestParam String password) throws HttpException, IOException {
		boolean b=false;//是否保存成功
		
		/*****
		 * 测试请求地址 http://localhost:8080/UCS_TDC/jsp/user/do_adduser.do?name=zhang&&password=123456
		 * 
		 * 业务端给我发请求的时候请除了登录 列 login.do 前面不加do_   其他都在前面加 do_  项目完成后添加拦截器所用
		 * 
		 * 记录每个请求的地址 和传的参数 是做什么的 和需要返回的对象  便于我开发
		 * 
		 * 测试  需要修改applicationContext.xml文件的数据库密码
		 * 
		 * 架构采用spring mvc+hibernate 原来的spring mvc上的东西这个上面都可以用
		 */
		System.out.println("-------------------------------");
		List<AdminInfo> list=new ArrayList<AdminInfo>();
		AdminInfo info=new AdminInfo();
		info.setId(22);
		info.setUserName(name);
		info.setUserPw(password);
		interFace.adduser(info);
		list.add(info);
		Gson gson=new Gson();
		String str=gson.toJson(list);
		/**httpclient***/
		HttpClient client = new HttpClient( ); 
		 String url = "http://192.168.2.119:8080/UCS_TDC/jsp/user/do_test.do?";
		 PostMethod method = new UTF8PostMethod(url);
		 method.setParameter("valueinfo", str);
		client.executeMethod(method); 
		//打印服务器返回的状态  
        System.out.println(method.getStatusLine());  
        //打印返回的信息  
        System.out.println("接收到服务器返回的对象字符串"+method.getResponseBodyAsString());  
        
        String str2=method.getResponseBodyAsString();
        /***将从服务器收到的字符串进行gson转化*/
       List<AdminInfo>  adlist=gson.fromJson(str2,  
               new TypeToken<List<AdminInfo>>() {  
       }.getType());  
        System.out.println("经过修改的对象  账号："+adlist.get(0).getUserName()+"   密码："+adlist.get(0).getUserPw());
        //释放连接  
        method.releaseConnection();  
		return b;
	}
	
	@RequestMapping(value="jsp/user/do_test")
	@ResponseBody
	public List<AdminInfo>  do_test(@RequestParam String valueinfo) {
		System.out.println("===================="+valueinfo+"==================================");
		Gson gson=new Gson();
		List<AdminInfo> infolist=gson.fromJson(valueinfo,  
                new TypeToken<List<AdminInfo>>() {  
        }.getType());  
		AdminInfo info=infolist.get(0);
		System.out.println("000000"+info.getUserName());
		return infolist;
	}
	
	
	
	
	
	/****删除信息***/
	@RequestMapping(value="jsp/user/do_deleteuser")
	@ResponseBody
	public boolean do_deleteuser(@RequestParam int id,@RequestParam String name,@RequestParam String password) {
		boolean b=false;//是否删除成功
		AdminInfo info=new AdminInfo();
		info.setId(id);
		info.setUserName(name);
		info.setUserPw(password);
		b=interFace.deleteinfoByID(info);
		return b;
	}
	
	/***多条件查询测试*/
	@RequestMapping(value = "admin/do_find_Test_Projects")
	@ResponseBody
	public List<ProjectInfo> do_find_Test_Projects(@RequestParam 
			String projectName,String starTime,String endTime) {
		
		List<ProjectInfo> list=interFace.do_find_Test_Projects(projectName,starTime,endTime);
		Gson gson =new Gson();
		log.info("显示项目   :"+gson.toJson(list));
		return list;
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
