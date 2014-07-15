package com.java7.nio;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.*;
/**
 * WatchService 文件监控
 * */
public class WatchServicesTest {

	public static void main(String[] args) {
		try{
			WatchService watch=FileSystems.getDefault().newWatchService();
			Path file=FileSystems.getDefault().getPath("F:/GitHub/datasource/Java7Test");//监听文件夹中的文件，不监听子文件夹中的文件
			WatchKey key = file.register(watch, ENTRY_MODIFY);
			key=watch.take();
			while(true){
				for(WatchEvent<?> event:key.pollEvents()){
					if(event.kind()==ENTRY_MODIFY){
						System.out.println("文件被修改");
					}
				}
				key.reset();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
