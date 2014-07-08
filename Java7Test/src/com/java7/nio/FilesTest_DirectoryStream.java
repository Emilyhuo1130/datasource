package com.java7.nio;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
/**
 * 
查找目录中的文件
 * */
public class FilesTest_DirectoryStream {

	public static void main(String[] args) {
		String path="E:/apache-tomcat-7.0.53/conf";
		Path dir = Paths.get(path);
		try(DirectoryStream<Path> stream=
				Files.newDirectoryStream(dir,"*.properties");){//过滤出后缀名是properties的文件
			for(Path entry:stream){
				System.out.println(entry.getFileName());
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
