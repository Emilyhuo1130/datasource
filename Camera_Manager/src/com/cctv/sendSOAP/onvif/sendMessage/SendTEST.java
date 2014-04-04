package com.cctv.sendSOAP.onvif.sendMessage;

import javax.xml.soap.SOAPMessage;

public class SendTEST {
	public static void main(String[] args){
		SOAPMessage message=Media.getProfiles("INFINOVA", "BkWpG8cz2FzjKVPz3/AX4dSjnJ0=", "ynf+ukQISFGDOApOAUhKug==", "192.168.2.127");
	}
}
