package com.cctv.sendSOAP.onvif.sendMessage;


import javax.xml.soap.SOAPMessage;

public class Media {
	private static SOAPMessage response=null;
	/**获取配置文件**/
	public static SOAPMessage getProfiles(String userName,String password,String nonce,String ip){
		String soapReuqest="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		soapReuqest+="<soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope' xmlns:trt='http://www.onvif.org/ver10/media/wsdl' xmlns:tt='http://www.onvif.org/ver10/schema'>";
		soapReuqest+="<s:Header xmlns:s='http://www.w3.org/2003/05/soap-envelope'>";
		soapReuqest+="<wsse:Security xmlns:wsse='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' xmlns:wsu='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd'>";
		soapReuqest+="<wsse:UsernameToken>";
		soapReuqest+="<wsse:Username>"+userName+"</wsse:Username>";
		soapReuqest+="<wsse:Password Type='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest'>"+password+"</wsse:Password>";
		soapReuqest+="<wsse:Nonce>"+nonce+"</wsse:Nonce>";
		soapReuqest+="<wsu:Created>"+SendMessageUtils.getSendTime()+"</wsu:Created>";//2014-03-24T08:28:17Z
		soapReuqest+="</wsse:UsernameToken>";
		soapReuqest+="</wsse:Security>";
		soapReuqest+="</s:Header>";
		soapReuqest+="<soap:Body>";
		soapReuqest+="<trt:GetProfiles />";
		soapReuqest+="</soap:Body>";
		soapReuqest+="</soap:Envelope>";
		response=SendMessageUtils.sendMessage(soapReuqest,"http://"+ip+":8000/onvif/ptz_service");
    	return response;
	}
	
	
	
}
