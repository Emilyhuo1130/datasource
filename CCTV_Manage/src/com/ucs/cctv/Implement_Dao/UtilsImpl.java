package com.ucs.cctv.Implement_Dao;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.ucs.cctv.Interface_Dao.Utils;
import com.ucs.cctv.Trees.CameraChildren;
import com.ucs.cctv.Trees.CameraTrees;
import com.ucs.cctv.Trees.SectionCameraTrees;

@Repository("UtilsImpl")
public class UtilsImpl extends HibernateDaoSupport implements Utils{
	static Logger log = Logger.getLogger(UtilsImpl.class);
	@Resource
	public void setMySessionFactory(SessionFactory sf){
        super.setSessionFactory(sf);
    }


	public List<String> showAllSection() {	
		String hql="select distinct sectionName from SectionInfo";
		List<String> sectionName = getHibernateTemplate().find(hql);
		return sectionName;
	}

	public SectionCameraTrees showSectionCameraTrees() {
		SectionCameraTrees sectionCameraTrees = new SectionCameraTrees();
		List<CameraTrees> cameraList = new ArrayList<CameraTrees>();
		String hql="select distinct sectionName from SectionInfo";
		List<String> sectionName = getHibernateTemplate().find(hql);
		for(int i=0;i<sectionName.size();i++){
			List<CameraChildren> list = new ArrayList<CameraChildren>();
			CameraTrees cameraTrees = new CameraTrees();
		    cameraTrees.setName(sectionName.get(i));
		    hql = "select cameraName from CameraInfo where section=?";
		    List<String> cameraName = getHibernateTemplate().find(hql,sectionName.get(i));
		    for(String name : cameraName){
		    	CameraChildren camerachildren = new CameraChildren();
		    	camerachildren.setName(name);
		    	hql = "select cameraId from CameraInfo where cameraName=?";
		    	List<Integer> cameraId = getHibernateTemplate().find(hql,name);
		    	if(cameraId!=null){
		    		camerachildren.setValue(cameraId.get(0));
		    	}
		    	camerachildren.setChecked(false);
		    	list.add(camerachildren);
		    }
			cameraTrees.setChildren(list);
			cameraList.add(cameraTrees);
		}
		sectionCameraTrees.setName("区位");
		sectionCameraTrees.setChildren(cameraList);
		return sectionCameraTrees;
	}

	public List<String> showMonitorGroup() {
		String hql="select distinct groupName from MonitorGroup";	
		List<String> groupName = getHibernateTemplate().find(hql);
		return groupName;
	}

	public SectionCameraTrees showSectionCameraTreesById(int id) {
		log.info("入参id="+id);
		SectionCameraTrees sectionCameraTrees = new SectionCameraTrees();
		List<CameraTrees> cameraList = new ArrayList<CameraTrees>();
		String hql="select distinct sectionName from SectionInfo";
		log.info("*********getHibernateTemplate2()=*********"+getHibernateTemplate());
		log.info("*********1*******");
		List<String> sectionName = getHibernateTemplate().find(hql);
		log.info("*********1*******"+sectionName);
		String newHql = "select distinct g.cameraId from MonitorGroup c join c.cameraMerbers g where c.groupId=?";
		log.info("*********2*******");
		List<String> cameraId = getHibernateTemplate().find(newHql,id);
		log.info("*********2*******"+cameraId);
		for(int i=0;i<sectionName.size();i++){
			List<CameraChildren> list = new ArrayList<CameraChildren>();
			CameraTrees cameraTrees = new CameraTrees();
		    cameraTrees.setName(sectionName.get(i));
		    hql = "select cameraName from CameraInfo where section=?";
		    log.info("*********3*******");
		    List<String> cameraName = getHibernateTemplate().find(hql,sectionName.get(i));
		    log.info("*********3*******"+cameraName);
		    for(String name : cameraName){
		    	CameraChildren camerachildren = new CameraChildren();
		    	camerachildren.setName(name);
		    	hql = "select cameraId from CameraInfo where cameraName=?";
		    	List<Integer> cameraid = getHibernateTemplate().find(hql,name);
		    	if(cameraid!=null){
		    		camerachildren.setValue(cameraid.get(0));
		    	}
		    	if(cameraId.contains(cameraid.get(0))){
		    		camerachildren.setChecked(true);
		    	}
		    	else{
		    		camerachildren.setChecked(false);
		    		}
		    	list.add(camerachildren);
		    }
		    log.info(list.size());
			cameraTrees.setChildren(list);
			cameraList.add(cameraTrees);
		}
		sectionCameraTrees.setName("区位");
		sectionCameraTrees.setChildren(cameraList);
		log.info("****************************"+sectionCameraTrees);
		return sectionCameraTrees;
	}
	

}
