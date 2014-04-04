package com.ucs.rcm.webservice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.Test;

import com.ucs.rcm.RcmUtils;
import com.ucs.rcm.business.bo.Chattelanalysebo;
import com.ucs.rcm.business.bo.Equipment;
import com.ucs.rcm.webservice.partner.EquipmentService;
import com.ucs.rcm.webservice.partner.EquipmentServiceClient;

public class TestDKS {
	static Logger log = Logger.getLogger(TestDKS.class);
	/**
	 * @param args
	 */

	/*
	 * EquipmentId = equipmentId TheSystem =systemName EquipmentName =
	 * equipmentname Details = equipmentDescription
	 */

	public static List<Equipment> xmlParser(String xmlString) {

		List<Equipment> equipmentList = new ArrayList<Equipment>();
		try {

			 xmlString = new String (xmlString.getBytes(),"UTF-8");

			//xmlString = xmlString.replaceAll("[^\\x20-\\x7e]", "");
			//System.out.println(xmlString);
			// System.out.println(xmlString);
			Document document = DocumentHelper.parseText(xmlString);
			Element root = document.getRootElement();
			for (Iterator i = root.elementIterator(); i.hasNext();) {
				Element el = (Element) i.next();
				if (el.getQName().getName().equalsIgnoreCase("All_Output")) {
					Equipment eq = new Equipment();
					for (Iterator ii = el.elementIterator(); ii.hasNext();) {
						Element els = (Element) ii.next();
						System.out.println(els.getQName().getName() + ":"+ els.getText());
						
						

					}
					equipmentList.add(eq);
				}
				

			}

			System.out.println(equipmentList.size() + "size");
		

		} catch (Exception e) {
			e.printStackTrace();

		}

		return equipmentList;

	}

public static void main(String[] args) {
//		// TODO Auto-generated method stub
		
		/*EquipmentServiceClient ec = new EquipmentServiceClient();
		  EquipmentService es = ec.getEquipmentSoapPort( );
		  String xml = es.getEquipment("10.29.08.02.007", "sgncom_signal_controller_inst");
		  System.out.println(xml);*/
		 

		File file = new File("d:/13.xml");
		String xmlString = "";

		try {

			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file));
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				// System.out.println(lineTxt);
				xmlString = xmlString + lineTxt;
			}
			read.close();

			// System.out.println(xmlString);

			TestDKS.xmlParser(xmlString);
			//TestDKS.xmlParser2(xmlString);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void xmlParser2(String xmlString) {
	// TODO Auto-generated method stub
		List<Equipment> equipmentList = new ArrayList<Equipment>();
		 try{
			 System.out.println("---"+xmlString);
			  Document document = DocumentHelper.parseText(xmlString);
			  System.out.println(document.getXMLEncoding());
			  
			  Element root=document.getRootElement();
			  Element viewe=root.element("Equipment");
			  Element viewe2=viewe.element("annotation");
			  Element viewe3 =viewe2.element("documentation");
			
		  }catch(Exception e){
			  //log.error(RcmUtils.getTrace(e));
		  }
			

		
	}

	

}
