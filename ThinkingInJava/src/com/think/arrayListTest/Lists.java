package com.think.arrayListTest;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

public  class Lists {
	private Object oj;
	public static <E> List<E> newArrayList(E...es ){
		for(int i=0;i<es.length;i++){
			System.out.println(es[i]);
			
		}
		ArrayList<E> list=new ArrayList<E>(es.length);
		Collections.addAll(list, es); 
		return list;
	}
}
