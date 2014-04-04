package com.ucs.connect.date;
import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


@SuppressWarnings("deprecation")
public class RequestInfo {
	private static HttpClient httpclient = new DefaultHttpClient();
	private static Logger loger = Logger.getLogger(RequestInfo.class.getName());
	public String requestinfo(List<NameValuePair> params,String url) {
		String body="";
		//post请求
		HttpPost httppost = new HttpPost(url);
		try{
			httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
            body=httpclient.execute(httppost,new ResponseHandler<String>() { 
                public String handleResponse( 
                     HttpResponse response) throws ClientProtocolException, IOException { 
                	 HttpEntity entity = response.getEntity();
                	 loger.info("--------------------httpclient-----------------------");
                	 loger.info(response.getStatusLine());
                	 if(entity != null) {  	  
                         if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                             return new String(EntityUtils.toString(entity));  
                         }  
                     }  
                return "";
                }
              });
           } catch (Exception e) {  
        	   e.printStackTrace();  
        }finally{
        	httppost.releaseConnection();
        }
     return body;
     }
}