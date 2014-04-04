package com.FileTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.junit.Test;
import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
import org.nutz.lang.random.R;
import org.nutz.lang.random.StringGenerator;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public class Filetest {

	/**
	 * 读写的默认编码为utf8
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		/***读文本中的所有文本***/
		String ss=Files.read("test.txt");//读取src下的文件
		String ss2=Files.read("com/FileTest/test.txt");//读取指定包下的文件
		System.out.println(ss2);
		//或者
		/*String txt = Lang.readAll(Streams.fileInr("D:/test.txt"));
		System.out.println(txt);*/
		
		/**覆盖文本中的内容 ***/
		//文件不存在就创建
		//Files.write("d:/test.txt", "test张");
		//文件不存则什么都不做
		//Lang.writeAll(Streams.fileOutw("d:/test.txt"), "战三");
		
		
		/***获取Reader**/
		/*Reader reader=Streams.fileInr("d:/test.txt");
		
		*//***获取Writer**//*
		Writer writer =Streams.fileOutw("d:/test.txt");
		
		InputStream input=Streams.fileIn("d:/test.txt");
		
		OutputStream out=Streams.fileOut("d:/test.txt");
		//将文件移动到新的位置
		Files.move(new File("D:/demo/abc.txt"), new File("D:/demo/def.txt"));
		//将文件改名
		Files.rename(new File("D:/demo/abc.txt"), "def.txt");*/
		
		// 生成 10 个长度不超过20的字符串
		/*StringGenerator sg = new StringGenerator(20);
		for(int i=0;i<10;i++)
			System.out.println(sg.next());*/
		//闭区间随机整数
		/*for(int i=0;i<20;i++)
			System.out.println(R.random(1,5)); 
			System.out.println(R.UU64()); */
		
			
	}
	
	

}
