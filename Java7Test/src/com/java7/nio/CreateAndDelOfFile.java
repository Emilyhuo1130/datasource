package com.java7.nio;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 创建和删除文件
 * */
public class CreateAndDelOfFile {

	public static void main(String[] args) throws IOException {
		
		Path target=Paths.get("mt.txt");
		Path toCopy=Paths.get("copy.txt");
		Path toMove=Paths.get("move.txt");
		//Path file=Files.createFile(target);//创建文件
		//Files.delete(target);//删除文件
		
		//Files.copy(target, toCopy);//文件复制
		//Files.move(target, toMove);//文件移动
		
		//文件属性
		System.out.println(Files.getLastModifiedTime(target));//最后修改时间
		System.out.println(Files.size(target));//大小
		System.out.println(Files.isSymbolicLink(target));//返回是否是真实的路径，绝对路径
		System.out.println(Files.isDirectory(target));//是否为文件夹
		System.out.println(Files.readAttributes(target,"*"));//读取所有属性
		System.out.println(Files.readAllLines(target,StandardCharsets.UTF_8));//读取文本
	}

}
