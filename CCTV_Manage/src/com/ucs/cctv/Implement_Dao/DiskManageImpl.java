package com.ucs.cctv.Implement_Dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.ucs.cctv.Pojo.DiskInfo;
import com.ucs.cctv.Response.DiskSizeResponse;
import com.ucs.cctv.Interface_Dao.DiskManage;
@Repository("DiskManageImpl")
public class DiskManageImpl extends HibernateDaoSupport implements DiskManage{
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
		static Logger log = Logger.getLogger(DiskManageImpl.class);
		Gson gson = new Gson();
		//磁盘信息初始化写入配置文件   之后从配置文件读取
		public void readDiskInfo(){
			 //读取properties的全部信息
		     Properties props = new Properties();
		        try {
		        	String path = POIFSReader.class.getResource("/").getPath();
		        	String	 filePath = path.replace("/build/", "").replace("%20"," ").replace("WebRoot/WEB-INF/", "").replaceFirst("/classes", "")+"src/disk.properties" ;
		        	
		         InputStream in = new BufferedInputStream (new FileInputStream(filePath));
		         props.load(in);
		            @SuppressWarnings("rawtypes")
					Enumeration en = props.propertyNames();
		             while (en.hasMoreElements()) {
		              String key = (String) en.nextElement();
		                    String Property = props.getProperty (key);
		                    System.out.println(key+"="+Property);
		                    
		                   DiskInfo diskInfo =  gson.fromJson(Property,DiskInfo.class);
		                   log.info(gson.toJson(diskInfo));
		                }
		        } catch (Exception e) {
		         e.printStackTrace();
		        }
		        
		    }
		
		//读取properties的全部信息返回Response对象
		public DiskSizeResponse getDiskInfo(){
	    Properties props = new Properties();
	    DiskSizeResponse diskSizeResponse = new DiskSizeResponse();
	    List<DiskInfo> diskInfos = new ArrayList<DiskInfo>();
	       try {
	       	String path = POIFSReader.class.getResource("/").getPath();
	       	String	 filePath = path.replace("/build/", "").replace("%20"," ").replace("WebRoot/WEB-INF/", "")+"disk.properties" ;
	       	
	        InputStream in = new BufferedInputStream (new FileInputStream(filePath));
	        props.load(in);
	          diskSizeResponse.setTotalSurplusCapacity(Double.parseDouble(props.getProperty("totalSurplusCapacity")));
	          diskSizeResponse.setPerDiskCapacityLess(Double.parseDouble(props.getProperty("perDiskCapacityLess")));
	          String[] diskInfoString = {props.getProperty("disk1"),props.getProperty("disk2"),props.getProperty("disk3"),props.getProperty("disk4"),};
	          for (int i = 0; i < diskInfoString.length; i++) {
				DiskInfo diskInfo = gson.fromJson(diskInfoString[i], DiskInfo.class);
				diskInfos.add(diskInfo);
				
			}
	          diskSizeResponse.setDiskList(diskInfos);
	        
	       } catch (Exception e) {
	        e.printStackTrace();
	       }
		return diskSizeResponse;
	       
	   }

		public boolean updateDiskInfo(DiskSizeResponse diskSizeResponse) {
				  Properties prop = new Properties();
				  	String path = POIFSReader.class.getResource("/").getPath();
				  	boolean udateBackInfo = false;
				  	//String filePath = (path.replace("/build/", "").replace("%20"," ").replace("WebRoot/WEB-INF/", "").replaceFirst("/classes", "") )+"src/disk.properties";
			      	String	 filePath = path.replace("/build/", "").replace("%20"," ").replace("WebRoot/WEB-INF/", "")+"disk.properties" ;
			       	log.info(filePath);
				     try {
				      InputStream fis = new FileInputStream(filePath);
				            prop.load(fis);
				            OutputStream fos = new FileOutputStream(filePath);
				            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
				            prop.setProperty("perDiskCapacityLess", String.valueOf(diskSizeResponse.getPerDiskCapacityLess()));
				            prop.store(bw, null);
				            udateBackInfo=true;
				            
				        } catch (IOException e) {
				        	e.printStackTrace();
				         System.err.println("***Visit "+filePath+" for updating "+"parameterName"+" value error***");
				        }
			return udateBackInfo;
		}
	}
		
		

