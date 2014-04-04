package com.ucs.rcm.webservice;


import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.gson.Gson;
import com.ucs.rcm.business.bo.Equipment;
import com.ucs.rcm.webservice.partner.EquipmentService;
import com.ucs.rcm.webservice.partner.EquipmentServiceClient;

public class Save_EquipmentInfo_fromDKS {
	static Logger log = Logger.getLogger(Save_EquipmentInfo_fromDKS.class);
	
	public static Equipment getEquipmentFromDKS(String equipmentId,String type){
		 EquipmentServiceClient ec = new EquipmentServiceClient();
		 EquipmentService es = ec.getEquipmentSoapPort( );
		
		  String xmlString = es.getEquipment(equipmentId, type);
		
		  Equipment eq = new Equipment();
		try {
			 xmlString = new String (xmlString.getBytes(),"UTF-8");
			Document document = DocumentHelper.parseText(xmlString);
			Element root = document.getRootElement();
			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element el = (Element) i.next();
				if (el.getQName().getName().equalsIgnoreCase("All_Output")) {
					for (Iterator ii = el.elementIterator(); ii.hasNext();) {
						Element els = (Element) ii.next();
						if(els.getQName().getName().equals("xlbh")){
							eq.setXlbh(els.getQName().getName());
						}else if (els.getQName().getName().equals("qj")){
							eq.setQj(els.getQName().getName());
						}else if (els.getQName().getName().equals("sbmc")){
							eq.setSbmc(els.getQName().getName());
						}else if (els.getQName().getName().equals("lc")){
							eq.setLc(els.getQName().getName());
						}else if (els.getQName().getName().equals("lcsz")){
							eq.setLcsz(els.getQName().getName());
						}else if (els.getQName().getName().equals("wzms")){
							eq.setWzms(els.getQName().getName());
						}else if (els.getQName().getName().equals("zcbm")){
							eq.setZcbm(els.getQName().getName());
						}else if (els.getQName().getName().equals("gdzcbm")){
							eq.setGdzcbm(els.getQName().getName());
						}else if (els.getQName().getName().equals("azrq")){
							eq.setAzrq(els.getQName().getName());
						}else if (els.getQName().getName().equals("scrq")){
							eq.setScrq(els.getQName().getName());
						}else if (els.getQName().getName().equals("bxq")){
							eq.setBxq(els.getQName().getName());
						}else if (els.getQName().getName().equals("sjsynx")){
							eq.setSjsynx(els.getQName().getName());
						}else if (els.getQName().getName().equals("yjsynx")){
							eq.setYjsynx(els.getQName().getName());
						}else if (els.getQName().getName().equals("dxpc")){
							eq.setDxpc(els.getQName().getName());
						}
						//System.out.println(els.getQName().getName() + ":"+ els.getText());
					}
					
				}

			}
		
			
				
			

			Gson gson=new Gson();
			log.info("获取"+equipmentId+"的设备信息"+gson.toJson(eq));
		} catch (Exception e) {
			e.printStackTrace();

		}

		return eq;
	}
	
	/***设备信息存数据库*/
	public void insertEquipment(List<Equipment> equipmentInfoList){
		
	}
	
	public static void main(String[] args ){
		getEquipmentFromDKS("10.29.08.01.001","sgncom_signal_controller_inst");
	}
	
}
