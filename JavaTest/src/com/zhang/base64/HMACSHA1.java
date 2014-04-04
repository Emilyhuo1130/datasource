package com.zhang.base64;



import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;  
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;  
  




import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.crypto.Mac;  
import javax.crypto.spec.SecretKeySpec;  


import sun.misc.BASE64Decoder;
  
  
public class HMACSHA1 {     
    
    private static final String HMAC_SHA1 = "HmacSHA1";    

    private static Random random = new Random();
    private static String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

     public static String getSignature(String data,String key) throws Exception{  
    	 byte[] keyBytes=key.getBytes();  
         SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);     
         Mac mac = Mac.getInstance(HMAC_SHA1);     
         mac.init(signingKey);     
         byte[] rawHmac = mac.doFinal(data.getBytes());  
         StringBuilder sb=new StringBuilder();  
         for(byte b:rawHmac){  
          sb.append(byteToHexString(b));  
         }  
         return sb.toString();     
     }  
       
     private static String byteToHexString(byte ib){  
      char[] Digit={  
        '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'  
      };  
      char[] ob=new char[2];  
      ob[0]=Digit[(ib>>>4)& 0X0f];  
      ob[1]=Digit[ib & 0X0F];  
      String s=new String(ob);  
      return s;           
     }     
     
     
     
     private static String GenerateNonce(int length)
     {
         StringBuffer nonceString = new StringBuffer();

         for (int i = 0; i < length; i++)
         {
        	 int j=random.nextInt(length);
             nonceString.append(validChars.substring(j,j+1));
         }

         return nonceString.toString();
     }

     	
     public static String getBASE64(byte[] b) {
		  String s = null;
		  if (b != null) {
		   s = new sun.misc.BASE64Encoder().encode(b);
		  }
		  return s;
		 }
		 
		 public static byte[] getFromBASE64(String s) {
		  byte[] b = null;
		  if (s != null) {
		   BASE64Decoder decoder = new BASE64Decoder();
		   try {
		    b = decoder.decodeBuffer(s);
		    return b;
		   } catch (Exception e) {
		    e.printStackTrace();
		   }
		  }
		  return b;
		 }
		 
     
     
     
     
     public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	 System.out.println(System.getProperty("file.encoding"));
    	 String defaultEncoding=System.getProperty("file.encoding");
    	 
    	 
    	 String noncestring =GenerateNonce(16);
    	 String createtime ="2014-02-02";
    	 String digestbuf= noncestring+createtime + "INFINOVA";
    	 System.out.println(digestbuf);
    	 MessageDigest md=MessageDigest.getInstance("SHA-1");
    	 md.update(digestbuf.getBytes());
    	 byte[] result=md.digest();
    	 System.out.println(Arrays.toString(result));
    	 StringBuffer sb = new StringBuffer();
    	 
	 	    for (byte b : result) {
	 	        int i = b & 0xff;
	 	        if (i < 0xf) {
	 	            sb.append(0);
	 	        }
	 	        sb.append(Integer.toHexString(i));
	 	        
	 	    }
	 	 System.out.println(sb.toString()+"   "+sb.toString().length());
	 	 System.out.println(getBASE64(sb.toString().toUpperCase().getBytes()));
	 	 
	 	 
	 	 
	 	
     }
 }   
