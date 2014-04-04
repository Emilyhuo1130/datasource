package com.ucs.cctv.Implement_Dao;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.google.gson.Gson;
import com.ucs.cctv.Interface_Dao.SystemManage;
import com.ucs.cctv.Pojo.SystemConfigInfo;

@Repository("SystemService")
public class SystemManageImpl extends HibernateDaoSupport implements SystemManage{
	
	static Logger log = Logger.getLogger(SystemManageImpl.class);
	Gson gson = new Gson();
	
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }
	
	//返回系统参数设置参数
	public SystemConfigInfo getSystemConfigInfo(){
    Properties props = new Properties();
    SystemConfigInfo systemConfigInfo = new SystemConfigInfo();
       try {
       	String path = POIFSReader.class.getResource("/").getPath();
       	String	 filePath = path.replace("/build/", "").replace("%20"," ").replace("WebRoot/WEB-INF/", "")+"systemPrameter.properties" ;
       	
        InputStream in = new BufferedInputStream (new FileInputStream(filePath));
        props.load(in);
        
	        systemConfigInfo.setCpuOccupancy(Double.parseDouble(props.getProperty("cpuOccupancy")));
	        systemConfigInfo.setFlowRate(Double.parseDouble(props.getProperty("flowRate")));
	        systemConfigInfo.setTotalDiskIndex(Double.parseDouble(props.getProperty("totalDiskIndex")));
	        systemConfigInfo.setPerDiskIndex(Double.parseDouble(props.getProperty("perDiskIndex")));
        
       } catch (Exception e) {
        e.printStackTrace();
       }
	return systemConfigInfo;
       
   }
		
    //****************************************************************************************************
	//更新系统配置参数信息
	public boolean updateSystemConfigInfo(SystemConfigInfo systemConfigInfo) {
		  Properties prop = new Properties();
		  	String path = POIFSReader.class.getResource("/").getPath();
		  	//String filePath = (path.replace("/build/", "").replace("%20"," ").replace("WebRoot/WEB-INF/", "").replaceFirst("/classes", "") )+"src/systemPrameter.properties";
	       	String	 filePath = path.replace("/build/", "").replace("%20"," ").replace("WebRoot/WEB-INF/", "")+"systemPrameter.properties" ;
	       	log.info(filePath);
		     try {
		      InputStream fis = new FileInputStream(filePath);
		            prop.load(fis);
		            OutputStream fos = new FileOutputStream(filePath);
		            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));
		            prop.setProperty("cpuOccupancy", String.valueOf(systemConfigInfo.getCpuOccupancy()));
		            prop.setProperty("flowRate", String.valueOf(systemConfigInfo.getFlowRate()));
		            prop.setProperty("totalDiskIndex", String.valueOf(systemConfigInfo.getTotalDiskIndex()));
		            prop.setProperty("perDiskIndex", String.valueOf(systemConfigInfo.getPerDiskIndex()));
		            prop.store(bw, null);
		            
		        } catch (IOException e) {
		        	e.printStackTrace();
		         System.err.println("***Visit "+filePath+" for updating "+"parameterName"+" value error***");
		        }
		return true;
	}
}
