package com.zhang.javaTest.guavaTest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

public class ConcurrentMapTest {

	/**
	 *它适合多线程操作, 高并发, 无阻塞, 无死锁, 无冲突, 无测漏, 无...  @param args
	 */
	public static void main(String[] args) {
		/*ConcurrentHashMap<String, Integer> cahtch=new MapMaker()
		.expiration(365, TimeUnit.DAYS)  
	    .makeComputingMap(new Function<String, Integer>() {  
	     
		@Override
		public Integer apply(String arg0) {
			// TODO Auto-generated method stub
			return null;
		}  
	    });  */
	}

}
