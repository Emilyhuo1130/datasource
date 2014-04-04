package serverStart;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;

import camera_manager.CameraManagerServiceImpl;

public class StartThisServer implements ServletContextListener{
	Logger log=Logger.getLogger(StartThisServer.class);
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		log.info("CameraManagerServiceImpl start .....");
		CameraManagerServiceImpl server=new CameraManagerServiceImpl();
		Endpoint.publish("http://localhost:8085/CameraManagerService", server);
		log.info("CameraManage ready ......");
	}

}
