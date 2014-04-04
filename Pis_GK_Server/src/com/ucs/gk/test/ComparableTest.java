package com.ucs.gk.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.ucs.gk.bo.Config_Pis;
import com.ucs.gk.bo.client.Config_client;
import com.ucs.gk.daoImpl.UserControllerDaoImpl;

public class ComparableTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List <Point> list2=new ArrayList<Point>();
		list2.add(new Point(8,2));
		list2.add(new Point(5,3));
		list2.add(new Point(1,4));
		list2.add(new Point(7,5));
	
		/**sort(List list,Comparator c)*/
		/**用内部类的方法，按照给定的排序方法进行排序*/
		Collections.sort(list2,new Comparator<Point>(){
			public int compare(Point p1,Point p2){
				return p1.getX()-p2.getX();
			}
			
			
		});
		System.out.println(list2);
		
	/*	UserControllerDaoImpl dao =new UserControllerDaoImpl();
		Config_Pis config_Pis =dao.getinfoListNumTOName();
		List<Config_client> list=config_Pis.getClients();
		Collections.sort(list,new Comparator<Config_client>(){
			public int compare(Config_client p1,Config_client p2){
				return Integer.parseInt(p1.getClient_Id())-Integer.parseInt(p2.getClient_Id());
			}
			
			
		});
		for(Config_client info:list){
			System.out.println(info.getClient_Id()+ "  "+info.getLocal_ip());
		}*/
		

	}
	@Test
	public  void savejson(){
		Config_Pis pis=new Config_Pis();
		Gson gson=new Gson();
		String str=gson.toJson(pis);
		UserControllerDaoImpl dao =new UserControllerDaoImpl();
		dao.saveTempinfo(str);
	}
}
