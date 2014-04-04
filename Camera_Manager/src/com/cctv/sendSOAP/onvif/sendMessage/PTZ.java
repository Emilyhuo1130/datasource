package com.cctv.sendSOAP.onvif.sendMessage;

import java.util.Iterator;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

public class PTZ {
	private	static SOAPMessage response=null;
	public static SOAPMessage ContinuousMove(String userName,String password,String nonce,int x,int y,int z,String ip){
		
		SOAPMessage message=Media.getProfiles(userName, password, nonce, ip);
		Iterator iter = null;
		try {
			iter = message.getSOAPPart().getEnvelope().getBody().getChildElements();
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Iterator iter = message.getSOAPPart().getEnvelope().getNamespacePrefixes();
		String took =null;
		 while(iter.hasNext()){
			 SOAPElement element=(SOAPElement) iter.next();
			 Iterator iter2= element.getChildElements();
			 SOAPElement element2=(SOAPElement) iter2.next();
			 Iterator iter3= element2.getChildElements();
			 //while(iter3.hasNext()){
				 SOAPElement element3=(SOAPElement) iter3.next();
				took=element3.getTextContent();
				 
			// }
             
           }
		
		
		String soapReuqest="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		soapReuqest+="<s:Envelope xmlns:s='http://www.w3.org/2003/05/soap-envelope' xmlns:tptz='http://www.onvif.org/ver20/ptz/wsdl' xmlns:tt='http://www.onvif.org/ver10/schema'>";
		soapReuqest+="<s:Header>";
		soapReuqest+="<wsse:Security xmlns:wsse='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' xmlns:wsu='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd'>";
		soapReuqest+="<wsse:UsernameToken>";
		soapReuqest+="<wsse:Username>"+userName+"</wsse:Username>";
		soapReuqest+="<wsse:Password Type='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest'>"+password+"</wsse:Password>";
		soapReuqest+="<wsse:Nonce>"+nonce+"</wsse:Nonce>";
		soapReuqest+="<wsu:Created>"+SendMessageUtils.getSendTime()+"</wsu:Created>";
		soapReuqest+="</wsse:UsernameToken>";
		soapReuqest+="</wsse:Security>";
		soapReuqest+="</s:Header>";
		soapReuqest+="<s:Body>";
		soapReuqest+="<tptz:ContinuousMove>";
		soapReuqest+="<tptz:ProfileToken>"+took+"</tptz:ProfileToken>";
		soapReuqest+="<tptz:Velocity>";
		soapReuqest+="<tt:PanTilt x='"+x+"' y='"+y+"' />";
		soapReuqest+="<tt:Zoom x='"+z+"' />";
		soapReuqest+="</tptz:Velocity>";
		soapReuqest+="</tptz:ContinuousMove>";
		soapReuqest+="</s:Body>";
		soapReuqest+="</s:Envelope>";
		response=SendMessageUtils.sendMessage(soapReuqest,"http://"+ip+":8000/onvif/ptz_service");
    	return response;
	}
}
