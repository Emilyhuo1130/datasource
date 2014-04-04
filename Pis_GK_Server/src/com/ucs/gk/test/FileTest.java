package com.ucs.gk.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.ucs.gk.bo.effect.Pic_info;

public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String shareUrl="E:/apache-tomcat-7.0.2/Pis_GK_Client";
		File file=new File(shareUrl+"/Resources");
		
		File[] files=file.listFiles();
		for(File f:files){
			boolean f1=false;
			if(f.isDirectory()){
				f1=true;
			}
			
			File file2=new File(shareUrl+"/Resources/"+f.getName());
			if(file2.isDirectory()){
				File[] files2=file2.listFiles();
				for(File f2:files2){
					if(f2.isDirectory()){
						f1=false;
						/*Pic_info info_s=new Pic_info();
						info_s.setValue("Resources/"+f.getName()+"/"+f2.getName());
						info_s.setName("Resources/"+f.getName()+"/"+f2.getName());*/
						System.out.println("Resources/"+f.getName()+"/"+f2.getName());
					}
				}
			}
			if(f1){
				/*Pic_info info_m=new Pic_info();
				info_m.setValue("Images/"+f.getName());
				info_m.setName("Images/"+f.getName());*/
				//System.out.println("Resources/"+f.getName());
				File[] videoNames= f.listFiles();
				for(File f2:videoNames){
					System.out.println(f2.getName());
				}
			}
		}
	}
	
	@Test
	public void usbTest(){
		String s="Resources/Videos/rat.wmv";
		System.out.println(s.substring(17));
	}
	
	@Test
	public void clientidip(){
		List<Pic_info> list=new ArrayList<Pic_info>();
		String ss="192.168.2.";
		for(int i=1;i<=26;i++){
			Pic_info pic=new Pic_info();
			pic.setValue(i+"");
			pic.setName(ss+""+(i+149));
			list.add(pic);
		}
		Gson gson=new Gson();
		System.out.println(gson.toJson(list));
	}

}
