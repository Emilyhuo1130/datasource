package com.zhang.javaTest.guavaTest;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ListsAndMaps {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list=Lists.newArrayList();
		
		List<String> list2=Lists.newArrayList("22","33","44");//创建一个list 
		
		Map<String,Object> map=Maps.newHashMap();
		
		/*** ImmutableList和ImmutableMap 
		 * 不可被改变的集合***/
		ImmutableList<Integer> imList=ImmutableList.of(1,2,3);
		
		ImmutableMap<String,Integer> immap=ImmutableMap.of("1",1,"2",2,"3",3);
		
		
		
	}

}
