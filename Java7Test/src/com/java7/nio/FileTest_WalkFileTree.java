package com.java7.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTest_WalkFileTree {
/**
 * 遍历目录中的文件
 * @throws IOException 
 * */
	public static void main(String[] args) throws IOException {
		Path startstringDir =Paths.get("E:/apache-tomcat-7.0.53/");
		Files.walkFileTree(startstringDir, new FindJavaVisitor());
	}
	private static class FindJavaVisitor extends SimpleFileVisitor<Path>{
		public FileVisitResult visitFile(Path file,BasicFileAttributes attrs){
			if(file.toString().endsWith(".properties")){
				System.out.println(file.getFileName());
			}
			return FileVisitResult.CONTINUE;
		}
	}

}
