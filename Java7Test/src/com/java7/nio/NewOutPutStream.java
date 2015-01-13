package com.java7.nio;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NewOutPutStream {

	public static void main(String[] args) throws IOException  {
		Path path = Paths.get("d:/test.xlsx");
		InputStream newInputStream = Files.newInputStream(path, StandardOpenOption.READ);
		Path path2 = Paths.get("d:/e.xlsx");
		Files.createFile(path2);
		OutputStream newOutputStream = Files.newOutputStream(path2, StandardOpenOption.WRITE);
		BufferedInputStream bis = new BufferedInputStream(newInputStream);
		BufferedOutputStream bos = new BufferedOutputStream(newOutputStream);
		int n = 0;
		while((n = bis.read()) != -1){
			bos.write(n);
		}
		bis.close();bos.close();
		
	}

}
