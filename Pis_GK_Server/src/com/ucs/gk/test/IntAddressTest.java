package com.ucs.gk.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IntAddressTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws UnknownHostException, SocketException {
		// TODO Auto-generated method stub
		/*InetAddress host=InetAddress.getLocalHost();
		System.out.println(host);
		System.out.println(host.getHostAddress());
		System.out.println(host.getHostName());
		*/
		//getAllMacAdress();
		getMACbyIP(null);
		
		/*for(int i=0;i<255;i++){
			String ip="192.168.2.";
			ip=ip+i;
			getMACbyIP(ip);
			
		}*/
	}
	
	public static void getMACbyIP(String ip) throws SocketException, UnknownHostException{
		System.out.println(ip+"对应网卡的MAC是:");
		NetworkInterface ne=NetworkInterface.getByInetAddress(InetAddress.getByName("192.168.2.201"));
		if(ne!=null){
			byte[]macs=ne.getHardwareAddress();
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < macs.length; i++) {  
				String mac = Integer.toHexString(macs[i] & 0xFF);  
				if (mac.length() == 1) {  
					mac = '0' + mac;  
				}  
				if(i<macs.length-1){
					sb.append(mac + "-");  
				}else if(i==macs.length-1){
					sb.append(mac);  
				}
			}  
			
			System.out.println(sb.toString());   
		}
			
	}
	
	
	
	
	public static String getWindowsMACAddress() {   
        String mac = null;   
        BufferedReader bufferedReader = null;   
        Process process = null;   
        try {   
            process = Runtime.getRuntime().exec("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息  
            bufferedReader = new BufferedReader(new InputStreamReader(process   
                    .getInputStream()));   
            
            	System.out.println(bufferedReader.readLine());
            
            String line = null;   
            int index = -1;   
            while ((line = bufferedReader.readLine()) != null) {   
                index = line.toLowerCase().indexOf("physical address");// 寻找标示字符串[physical address]  
                if (index >= 0) {// 找到了  
                    index = line.indexOf(":");// 寻找":"的位置  
                    if (index>=0) {   
                        mac = line.substring(index + 1).trim();//  取出mac地址并去除2边空格  
                    }   
                    break;   
                }   
            }   
        } catch (IOException e) {   
            e.printStackTrace();   
        } finally {   
            try {   
                if (bufferedReader != null) {   
                    bufferedReader.close();   
                }   
            } catch (IOException e1) {   
                e1.printStackTrace();   
            }   
            bufferedReader = null;   
            process = null;   
        }   
  
        return mac;   
    }   
  
	
	
	public static void getAllMacAdress() {  
        Enumeration<NetworkInterface> netInterfaces = null;  
   
        try {  
            // 获得所有网络接口  
            netInterfaces = NetworkInterface.getNetworkInterfaces();  
            while (netInterfaces.hasMoreElements()) {  
                System.out  
                        .println("==============================================");  
                String mac = "";  
                StringBuffer sb = new StringBuffer();  
                NetworkInterface ni = netInterfaces.nextElement();  
                System.out.println("DisplayName: " + ni.getDisplayName());  
                System.out.println("Name: " + ni.getName());  
   
                byte[] macs = ni.getHardwareAddress();  
                // 该interface不存在HardwareAddress，继续下一次循环  
                if (macs == null) {  
                    continue;  
                }  
   
                for (int i = 0; i < macs.length; i++) {  
                    mac = Integer.toHexString(macs[i] & 0xFF);  
                    if (mac.length() == 1) {  
                        mac = '0' + mac;  
                    }  
                    if(i<macs.length-1){
                    	sb.append(mac + "-");  
                    }else if(i==macs.length-1){
                    	sb.append(mac);  
                    }
                }  
                mac = sb.toString();  
                System.out.println(mac);  
   
                Enumeration<InetAddress> ips = ni.getInetAddresses();  
                while (ips.hasMoreElements()) {  
                    System.out.println("IP: "  
                            + ips.nextElement().getHostAddress());  
                }  
            }  
        } catch (SocketException e) {  
            e.printStackTrace();  
        }  
    }  

}
