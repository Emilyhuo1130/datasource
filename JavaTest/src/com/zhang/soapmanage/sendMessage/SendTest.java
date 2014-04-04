package com.zhang.soapmanage.sendMessage;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SendTest {

	public static void main(String[] args) throws SOAPException, TransformerConfigurationException, TransformerException, TransformerFactoryConfigurationError, DocumentException {
		// TODO Auto-generated method stub
		SOAPMessage message=sendMessage();
		//Iterator element=message.getSOAPPart().getEnvelope().getBody().getAllAttributes();
		Iterator iter=message.getSOAPPart().getEnvelope().getBody().getChildElements();
		// Iterator iter = message.getSOAPPart().getEnvelope().getNamespacePrefixes();
		 while(iter.hasNext()){
			 SOAPElement element=(SOAPElement) iter.next();
			 Iterator iter2= element.getChildElements();
			 SOAPElement element2=(SOAPElement) iter2.next();
			 Iterator iter3= element2.getChildElements();
			 //while(iter3.hasNext()){
				 SOAPElement element3=(SOAPElement) iter3.next();
				String e=element3.getTextContent();
				 System.out.println(e);
			// }
             
           }
		
		 
		Document doc = message.getSOAPPart().getEnvelope().getBody().getOwnerDocument();  
		StringWriter output = new StringWriter();  
		TransformerFactory.newInstance().newTransformer().transform( new DOMSource(doc), new StreamResult(output));  
		System.out.println(output.toString()); 
		
		
		
		
		/*Element root  =DocumentHelper.parseText(output.toString()).getRootElement();
		for (Iterator i = root.elementIterator(); i.hasNext();) {
			Element el = (Element) i.next();
			System.out.println(el.getQName().getName()+"---"+el.getText()+"---");
			if(el.getQName().getName().equals("Body")){
				Iterator i2 = el.elementIterator();
				Element el2 = (Element) i2.next();
				if(el2.getQName().getName().equals("GetProfilesResponse")){
					Iterator i3 =el2.elementIterator();
					Element el3 = (Element) i3.next();
					Iterator i4=el3.elementIterator();
					Element el4 = (Element) i4.next();
					System.out.println(el4.getQName().getName()+"---"+el4.getText()+"---");
				}
			}
		}*/
		
		
	
	}
	
	public static SOAPMessage  sendMessage(){
		SOAPMessage response=null;
		String soapReuqest="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		soapReuqest+="<soap:Envelope xmlns:soap='http://www.w3.org/2003/05/soap-envelope' xmlns:trt='http://www.onvif.org/ver10/media/wsdl' xmlns:tt='http://www.onvif.org/ver10/schema'>";
		soapReuqest+="<s:Header xmlns:s='http://www.w3.org/2003/05/soap-envelope'>";
		soapReuqest+="<wsse:Security xmlns:wsse='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd' xmlns:wsu='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd'>";
		soapReuqest+="<wsse:UsernameToken>";
		soapReuqest+="<wsse:Username>INFINOVA</wsse:Username>";
		soapReuqest+="<wsse:Password Type='http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest'>wNbYGG6U3qP8pQMLyHcvxMvZVbI=</wsse:Password>";
		soapReuqest+="<wsse:Nonce>8goMFNpr2kD1dvWWeVQ/ng==</wsse:Nonce>";
		soapReuqest+="<wsu:Created>2014-03-24T02:12:45Z</wsu:Created>";
		soapReuqest+="</wsse:UsernameToken>";
		soapReuqest+="</wsse:Security>";
		soapReuqest+="</s:Header>";
		soapReuqest+="<soap:Body>";
		soapReuqest+="<trt:GetProfiles />";
		soapReuqest+="</soap:Body>";
		soapReuqest+="</soap:Envelope>";
    	try {
    		MessageFactory mf = MessageFactory.newInstance();
    		SOAPConnection con = SOAPConnectionFactory.newInstance().createConnection();
    		MimeHeaders mineHeaders=new MimeHeaders();
    		/*mineHeaders.addHeader("Host", "webservice.webxml.com.cn");
    		mineHeaders.addHeader("Content-Type", "text/xml;charset=utf-8");
    		mineHeaders.addHeader("SOAPAction","http://WebXml.com.cn/getRegionCountry");*/
    		SOAPMessage reqMessage = mf.createMessage(mineHeaders, new ByteArrayInputStream(soapReuqest.getBytes()));
    		//SOAPPart soappart = reqMessage.getSOAPPart();

    		System.out.println("\n Soap Request:\n");
    		reqMessage.writeTo(System.out);
    		System.out.println();

    		URL endpoint = new URL("http://192.168.2.127:8000/onvif/ptz_service");
    		response = con.call(reqMessage, endpoint);

    		// Print response
    		System.out.println("\n Soap Response:\n");
    		response.writeTo(System.out);
    		System.out.println("\n");
    		} catch (SOAPException e) {
    		e.printStackTrace();
    		} catch (IOException e) {
    		e.printStackTrace();
    		}
    	return response;
    	
	}

}
