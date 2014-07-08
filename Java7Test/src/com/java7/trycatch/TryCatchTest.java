package com.java7.trycatch;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TryCatchTest {

	public static void main(String[] args) {
		try(FileInputStream fis = new FileInputStream("d:/33.txt");
			FileOutputStream fos = new FileOutputStream("d:/44.txt");
			){
			@SuppressWarnings("unused")
			int n=-1;
			byte[] b=new byte[1*1024*1024];
			while((n=fis.read(b))!=-1){
				fos.write(b);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
