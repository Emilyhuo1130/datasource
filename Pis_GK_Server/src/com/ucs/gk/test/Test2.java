package com.ucs.gk.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.ucs.gk.bo.effect.Effects;
import com.ucs.gk.bo.effect.Pic_info;

public class Test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] multi_Effects={"请选择特效","砖墙","冒泡","飘落","旋转","群星陨落","六棱柱旋转","网格移动","地球转动","图片滑动","扔照片","3D旋转","3D翻转","路径指引","椭圆平移"};
		String[] single_effect_picIn_json={"画卷展开","1","2","3"};
		String[] single_effect_picout_json={"画卷展开","1","2","3"};
		String[] pic_Single_url={
				"Images/Theme/Client1",
				"Images/Theme/Client2",
				"Images/Theme/Client3",
				"Images/Theme/Client4",
				"Images/Theme/Client5",
				"Images/Theme/Client6",
				"Images/Theme/Client7",
				"Images/Theme/Client8",
				"Images/Theme/Client9",
				"Images/Theme/Client10",
				"Images/Theme/Client11",
				"Images/Theme/Client12",
				"Images/Theme/Client13",
				"Images/Theme/Client14",
				"Images/Theme/Client15",
				"Images/Theme/Client16",
				"Images/Theme/Client17",
				"Images/Theme/Client18",
				"Images/Theme/Client19",
				"Images/Theme/Client20",
				"Images/Theme/Client21",
				"Images/Theme/Client22",
				"Images/Theme/Client23",
				"Images/Theme/Client24",
				"Images/Theme/Client25",
				"Images/Theme/Client26",
				"Images/Theme/Myth"};
		String[] pic_Mulit_url={ "Images/Animal",
				"Images/Background",
				"Images/Border",
				"Images/BorderImages",
				"Images/BorderUniversity",
				"Images/Build",
				"Images/Entertainment",
				"Images/Exp",
				"Images/Hal",
				"Images/Ico",
				"Images/Icon",
				"Images/Metr",
				"Images/Nigh",
				"Images/OlderCit",
				"Images/OldTow",
				"Images/Scener",
				"Images/University",
				"Images/Zodia"};
		Effects effect=new Effects();
		List<Pic_info> multi_Effects_List=new ArrayList<Pic_info>();
		List<Pic_info> single_picIn_Effects_List=new ArrayList<Pic_info>();
		 List<Pic_info> single_picout_Effects_List=new ArrayList<Pic_info>();
		 List<Pic_info> pic_Mulit_url_List=new ArrayList<Pic_info>();
		 List<Pic_info> pic_Single_url_List=new ArrayList<Pic_info>();
		 
		for(int i=0;i<multi_Effects.length;i++){
			Pic_info info=new Pic_info();
			info.setValue(i+"");
			info.setName(multi_Effects[i]);
			multi_Effects_List.add(info);
		}
		for(int i=0;i<single_effect_picIn_json.length;i++){
			Pic_info info=new Pic_info();
			info.setValue(i+"");
			info.setName(single_effect_picIn_json[i]);
			single_picIn_Effects_List.add(info);
		}
		for(int i=0;i<single_effect_picout_json.length;i++){
			Pic_info info=new Pic_info();
			info.setValue(i+"");
			info.setName(single_effect_picout_json[i]);
			single_picout_Effects_List.add(info);
		}
		for(int i=0;i<pic_Mulit_url.length;i++){
			Pic_info info=new Pic_info();
			info.setValue(pic_Mulit_url[i]);
			info.setName(pic_Mulit_url[i]);
			pic_Mulit_url_List.add(info);
		}
		
		for(int i=0;i<pic_Single_url.length;i++){
			Pic_info info=new Pic_info();
			info.setValue(pic_Single_url[i]);
			info.setName(pic_Single_url[i]);
			pic_Single_url_List.add(info);
		}
		
		
		
		effect.setMulti_Effects(multi_Effects_List);
		effect.setSingle_picIn_Effects(single_picIn_Effects_List);
		effect.setSingle_picout_Effects(single_picout_Effects_List);
		effect.setPic_Mulit_url(pic_Mulit_url_List);
		effect.setPic_Single_url(pic_Single_url_List);
		
		Gson gson=new Gson();
		String str=gson.toJson(effect);
		System.out.println(str);
	}
	@Test
	public void getmultiScreenEffect(){
		List<Pic_info> list=new ArrayList<Pic_info>();
		Pic_info info=new Pic_info();
		info.setValue("0");info.setName("类型一");
		Pic_info info2=new Pic_info();
		info2.setValue("1");info2.setName("类型二");
		Pic_info info3=new Pic_info();
		info3.setValue("2");info3.setName("类型三");
		
		list.add(info3);list.add(info2);list.add(info);
		Gson gson=new Gson();
		System.out.println(gson.toJson(list));
	}
}
