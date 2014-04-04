package com.ucs.gk.test;


import java.io.BufferedReader;  
import java.io.InputStreamReader;  
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
public class GetMacAddress  
{  
  
    public static String callCmd(String[] cmd) {    
        String result = "";    
        String line = "";    
        try {    
            Process proc = Runtime.getRuntime().exec(cmd);    
            InputStreamReader is = new InputStreamReader(proc.getInputStream());    
            BufferedReader br = new BufferedReader (is);    
            while ((line = br.readLine ()) != null) {    
            result += line;    
            }    
        }    
        catch(Exception e) {    
            e.printStackTrace();    
        }    
        return result;    
    }  
      
    /**  
     *  
     * @param cmd  第一个命令  
     * @param another 第二个命令  
     * @return   第二个命令的执行结果  
     */    
     public static String callCmd(String[] cmd,String[] another) {    
         String result = "";    
         String line = "";    
         try {    
             Runtime rt = Runtime.getRuntime();    
             Process proc = rt.exec(cmd);    
             proc.waitFor();  //已经执行完第一个命令，准备执行第二个命令    
             proc = rt.exec(another);    
             InputStreamReader is = new InputStreamReader(proc.getInputStream());    
             BufferedReader br = new BufferedReader (is);    
             while ((line = br.readLine ()) != null) {    
                 result += line;    
             }    
         }    
         catch(Exception e) {    
             e.printStackTrace();    
         }    
         return result;    
     }    
     /**  
     *  
     * @param ip  目标ip,一般在局域网内  
     * @param sourceString 命令处理的结果字符串  
     * @param macSeparator mac分隔符号  
     * @return  mac地址，用上面的分隔符号表示  
     * @throws UnsupportedEncodingException 
     */    
     public static String filterMacAddress(final String ip, final String sourceString,final String macSeparator) throws UnsupportedEncodingException {    
         String result = "";    
         String regExp = "((([0-9,A-F,a-f]{1,2}" + macSeparator + "){1,5})[0-9,A-F,a-f]{1,2})";    
         Pattern pattern = Pattern.compile(regExp);    
         Matcher matcher = pattern.matcher(sourceString);   
        
        System.out.println(sourceString);
         while(matcher.find()){    
             result = matcher.group(1);    
             if(sourceString.indexOf(ip) <= sourceString.lastIndexOf(matcher.group(1))) {    
                 break;  //如果有多个IP,只匹配本IP对应的Mac.    
             }    
         }  
     
         return result;    
     }  
       
     /**  
     *  
     * @param ip 目标ip  
     * @return   Mac Address  
     * @throws UnsupportedEncodingException 
     *  
     */    
     public static String getMacInWindows(final String ip) throws UnsupportedEncodingException{    
         String result = "";    
         String[] cmd = {    
                 "cmd",    
                 "/c",    
                 "ping " +  ip    
                 };    
         String[] another = {    
                 "cmd",    
                 "/c",    
                 "arp -a"    
                 };    
         String cmdResult = callCmd(cmd,another);    
         
         result = filterMacAddress(ip,cmdResult,"-");  
         return result;    
     }       
     /**  
    *  
    * @param ip 目标ip  
    * @return   Mac Address  
     * @throws UnsupportedEncodingException 
    *  
    */    
     public static String getMacInLinux(final String ip) throws UnsupportedEncodingException{    
         String result = "";    
         String[] cmd = {    
                 "/bin/sh",    
                 "-c",    
                 "ping " +  ip + " -c 2 && arp -a"    
                 };    
         String cmdResult = callCmd(cmd);    
         result = filterMacAddress(ip,cmdResult,":");    
     
         return result;    
     }    
       
     /** 
      * 获取MAC地址  
     * @return 返回MAC地址 
     * @throws UnsupportedEncodingException 
      */  
     public static String getMacAddress(String ip) throws UnsupportedEncodingException{  
         String macAddress = "";  
         macAddress = getMacInWindows(ip).trim();  
         if(macAddress==null || "".equals(macAddress)){  
             macAddress = getMacInLinux(ip).trim();  
         }  
         return macAddress;  
     }  
       
     /**  
    * 测试  
     * @throws UnsupportedEncodingException 
    */    
     public static void main(String[] args) throws UnsupportedEncodingException {             
       // System.out.println(getMacAddress("192.168.2.201"));  
        for(int i=1;i<10;i++){
        	String ip="192.168.2."+i;
        	System.out.println(ip+"："+getMacAddress(ip)); 
        }
        for(int i=10;i<20;i++){
        	String ip="192.168.2."+i;
        	System.out.println(ip+"："+getMacAddress(ip)); 
        }
        for(int i=20;i<30;i++){
        	String ip="192.168.2."+i;
        	System.out.println(ip+"："+getMacAddress(ip)); 
        }
        for(int i=3;i<40;i++){
        	String ip="192.168.2."+i;
        	System.out.println(ip+"："+getMacAddress(ip)); 
        }
        for(int i=4;i<50;i++){
        	String ip="192.168.2."+i;
        	System.out.println(ip+"："+getMacAddress(ip)); 
        }
        for(int i=5;i<60;i++){
        	String ip="192.168.2."+i;
        	System.out.println(ip+"："+getMacAddress(ip)); 
        }
        
     }     
}  
