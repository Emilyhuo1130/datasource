package camera_manager;

import java.util.List;

import javax.xml.ws.Endpoint;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class CameraManagerServiceImpl implements CameraManagerService {

	public int AddCameras(List<CameraRecordingSet> cameras) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Server start ......");
		CameraManagerService cameraManagerService = new CameraManagerServiceImpl();  
		Endpoint.publish("http://localhost:8085/CameraManagerService", cameraManagerService);
        System.out.println("Server ready...");    
        
        while (true) {
        	try {  
                Thread.sleep(1000*300); //休眠五分分钟，便于测试     
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }
        }
	}

}
