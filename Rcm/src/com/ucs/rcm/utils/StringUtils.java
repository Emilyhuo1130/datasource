package com.ucs.rcm.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.junit.Test;

public class StringUtils {
	Logger log = Logger.getLogger(StringUtils.class);
	
	  public static String replace(String strSource, String strFrom, String strTo) {   
		    if (strSource == null) {       
		      return null;   
		    } 
		    int i = 0;
		    if ((i = strSource.indexOf(strFrom, i)) >= 0) {
		      char[] cSrc = strSource.toCharArray();
		      char[] cTo = strTo.toCharArray();
		      int len = strFrom.length(); 
		      StringBuffer buf = new StringBuffer(cSrc.length); 
		      buf.append(cSrc, 0, i).append(cTo);
		      i += len;   
		      int j = i;      
		      while ((i = strSource.indexOf(strFrom, i)) > 0) { 
		        buf.append(cSrc, j, i - j).append(cTo);  
		        i += len; 
		        j = i;       
		      }       
		      buf.append(cSrc, j, cSrc.length - j);
		      return buf.toString();
		    }
		    return strSource;
		  }
	  
	  //测试一下
	  //@Test
	  public void testReplace(){
		  String testStr = "and  and";
		  testStr =   StringUtils.replace(testStr , "a",  "2");
		  log.info(testStr);
	  }
	  
	  //通过使用正则表达式替换字符串中制定的字符串   考虑要严谨 如果没有匹配的则返回原字符串
	  public static String replaceByRegExp(String regExp , String oldStr , String desStr){
		  System.out.println("*******************正则替换****************");
		  Pattern pattern = Pattern.compile(regExp);
			Matcher matcher = pattern.matcher(oldStr);
			while(matcher.find()){
				oldStr = oldStr.replaceAll(regExp, desStr);
				System.out.println("**看看替换没*******"+oldStr+"*******");
			}
		  return oldStr;
	  }
	  
	  
}
