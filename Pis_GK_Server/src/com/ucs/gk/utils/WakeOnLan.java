package com.ucs.gk.utils;



/*import java.net.*;

public class WakeOnLan {
    
    public static final int PORT = 9;    
    
    public static void main(String[] args) {
        
        if (args.length != 2) {
            System.out.println("Usage: java WakeOnLan <broadcast-ip> <mac-address>");
            System.out.println("Example: java WakeOnLan 192.168.0.255 00:0D:61:08:22:4A");
            System.out.println("Example: java WakeOnLan 192.168.0.255 00-0D-61-08-22-4A");
            System.exit(1);
        }
        
        String ipStr = args[0];
        String macStr = args[1];
        
        try {
            byte[] macBytes = getMacBytes(macStr);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }
            
            InetAddress address = InetAddress.getByName(ipStr);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
            
            System.out.println("Wake-on-LAN packet sent.");
        }
        catch (Exception e) {
            System.out.println("Failed to send Wake-on-LAN packet: + e");
            System.exit(1);
        }
        
    }
    
    private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
=======*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;


public class WakeOnLan {
	private static String config_properties=System.getProperty("user.dir")+"/WEB-INF";
    private static final int PORT = 9;    
    private static List<String> macs=null;
    static Logger log = Logger.getLogger(WakeOnLan.class);
    public static void main(String[] args) {
        
        String ipStr = "192.168.2.255";
        String macStr = "00-E0-66-55-10-29";
        //218.4.188.6
      sendIP_MAC(ipStr,macStr);
        
       // closeOS(ipStr,macStr);
    	
    }
    /**远程唤醒**/
    public static void  getIPAndMACstoWakeUp(){
    	Properties p = new Properties();
    	
		try{
			FileInputStream fis=new FileInputStream(config_properties+"/effects.properties");
			BufferedReader buffer = new BufferedReader(   
				       new InputStreamReader(fis,"utf-8"));  
			p.load(buffer);
			String networkIP=p.getProperty("network.IP");
			String macsStr=p.getProperty("network.macs");
			String[] str_macs=macsStr.split(",");
			macs=new ArrayList<String>();
			for(int i=0;i<str_macs.length;i++){
				String onemac=str_macs[i];
				macs.add(onemac);
			}
			/***循环叫醒**/
			for(int i=0;i<macs.size();i++){
				log.info("唤醒远程主机IP："+networkIP+"     MAC: "+macs.get(i));
				sendIP_MAC(networkIP,macs.get(i));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
   
    
   
    /***y远程开机*/
	private static void sendIP_MAC(String ipStr,String macStr ){
    	 try {
             byte[] macBytes = getMacBytes(macStr);
             byte[] bytes = new byte[6 + 16 * macBytes.length];
             for (int i = 0; i < 6; i++) {
                 bytes[i] = (byte) 0xff;
             }
             for (int i = 6; i < bytes.length; i += macBytes.length) {
                 System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
             }
            //System.out.println(Arrays.toString(bytes));
             InetAddress address = InetAddress.getByName(ipStr);
             DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
             DatagramSocket socket = new DatagramSocket();
             socket.send(packet);
             socket.close();
             
             //System.out.println("远程主机"+ipStr+"已经开启");
             log.info("远程主机"+ipStr+"已经开启");
         }
         catch (Exception e) {
             System.out.println("Failed to send Wake-on-LAN packet: + e");
             log.warn("Failed to send Wake-on-LAN packet: "+e);
             System.exit(1);
         }
    }
    
    
    private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        
        String[] hex = macStr.split("(\\:|\\-)");
        //System.out.println(Arrays.toString(hex));

        if (hex.length != 6) {
            throw new IllegalArgumentException("Invalid MAC address.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid hex digit in MAC address.");
        }
        return bytes;
    }
    
   
}