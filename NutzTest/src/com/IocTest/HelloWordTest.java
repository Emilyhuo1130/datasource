package com.IocTest;

import org.junit.Test;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.pojo.Pet;

public class HelloWordTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Ioc ioc=new NutIoc(new JsonLoader("com/IocTest/Helloword.js"));
		Pet pet=ioc.get(Pet.class,"xiaoming");
		System.out.printf("%s - [%s]\n", pet.getName(), pet.getBirthday().getTimeZone().getID());
		
		Pet xh = ioc.get(null, "xioahei");
		System.out.printf("%s's friend is %s\n", xh.getName(), xh.getFriend().getName());
		
		/**声明了 singleton: false，那么它每次获取，都会生成一个新的实例**/
		Pet p1 = ioc.get(null, "xioahei");
		Pet p2 = ioc.get(null, "xioahei");
		System.out.println(p1==p2);
	}
	
	/***匿名方法**/
	@Test
	public void test1(){
		Ioc ioc =new NutIoc(new JsonLoader("com/IocTest/inner.js"));
		Pet pet=ioc.get(Pet.class,"xiaoming");
		System.out.println(pet.getFriend().getName());
		
		
	}
	
}
