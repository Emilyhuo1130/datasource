package com.java7.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/***
 * 快速读写数据
 * */
public class ReadSource {
	public static void main(String[] args) {
		//读数据
		Path source = Paths.get("mt.txt");
		try(BufferedReader buf
				=Files.newBufferedReader(source, StandardCharsets.UTF_8)){
			String line =null;
			while((line = buf.readLine())!=null){
				System.out.println(line);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//写数据
		Path writein=Paths.get("move.txt");
		try(BufferedWriter write =Files.newBufferedWriter(writein, StandardCharsets.UTF_8,StandardOpenOption.WRITE)){
			write.write("hello world!");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
