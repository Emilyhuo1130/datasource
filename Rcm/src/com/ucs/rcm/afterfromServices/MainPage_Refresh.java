package com.ucs.rcm.afterfromServices;




import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.reqobj.ReqgetInfoFromFlag;
import com.ucs.rcm.reqobj.ReqsetInfoFromFlag;
import com.ucs.rcm.responseObj.ResInfo;

public class MainPage_Refresh {
	static Logger log = Logger.getLogger(MainPage_Refresh.class);
	
		@Test
	public  static void infoistoDb()  {
		log.warn("*****************添加标志*********************");
		try{
			FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/WEB-INF/oos.dat");//System.getProperty("user.dir")+"/WEB-INF/oos.dat"
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject("success");
		}catch(Exception e){
			log.error("收到入库信息后，添加标志位添加失败"+RcmUtils.getTrace(e));
		}
	}

	public ResInfo getInfoRromFlag(ReqgetInfoFromFlag req)  {
		// TODO Auto-generated method stub
		ResInfo res=new ResInfo();
		try{
			//"E:/oos.dat"
			FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"/WEB-INF/oos.dat");//System.getProperty("user.dir")+"/WEB-INF/oos.dat"
			ObjectInputStream ois=new ObjectInputStream(fis);
			String flag=(String)ois.readObject();
			
				if(flag.equals("success")){
					res.setInfo("success");
				}else if(flag.equals("false")){
					res.setInfo("false");
				}
		}catch(Exception e){
			log.error("获取标志位信息失败"+RcmUtils.getTrace(e));
		}
		return res;
	}

	public ResInfo setInfoRromFlag(ReqsetInfoFromFlag req)  {
		ResInfo res=new ResInfo();
		try{
			FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"/WEB-INF/oos.dat");
			//FileOutputStream fos=new FileOutputStream("E:/oos.dat");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject("false");
		}catch(Exception e){
			log.error("设置标志位信息失败"+RcmUtils.getTrace(e));
		}
		res.setInfo("success");
		return res;
	}
	
	
}
