package com.zhang.javaTest.guavaTest;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class ResourceAndFiles {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		URL url=Resources.getResource("com/zhang/javaTest/guavaTest/test.txt");//src目录下
			System.out.println(Resources.toString(url,Charsets.UTF_8));//d读出 所有文本
			
			List<String> lines = Resources.readLines(url, Charsets.UTF_8);  //按照行读出
			//二进制流读取
			byte[] content = Resources.toByteArray(url);  
	}

}
