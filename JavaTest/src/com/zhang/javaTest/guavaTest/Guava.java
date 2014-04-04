package com.zhang.javaTest.guavaTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class Guava {

	/**
	 * @param args
	 */
	public static void main(String[] args){
	Optional<ImmutableMultiset<Person>> optional=Optional.fromNullable(testPredict());

	if(optional.isPresent()){
		for(Person p:optional.get()){
			System.out.println("name："+p.getName());
		}
	}

		System.out.println(optional.isPresent());
	}

	private static ImmutableMultiset<Person> testPredict() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/***一般数字排序**/
	@Test
	public void sortnumber(){
		List<Integer> numbers=Lists.newArrayList(30, 20, 60, 80, 10);

		System.out.println(Ordering.natural().sortedCopy(numbers));; //10,20,30,60,80 升序

		Ordering.natural().reverse().sortedCopy(numbers); //80,60,30,20,10 降序

		Ordering.natural().min(numbers); //10 取到最小值

		Ordering.natural().max(numbers); //80  取到最大值

		List<Integer> numbers2=Lists.newArrayList(30, 20, 60, 80, null, 10);

		Ordering.natural().nullsLast().sortedCopy(numbers2); //10, 20,30,60,80,null null放在最后

		Ordering.natural().nullsFirst().sortedCopy(numbers2); //null,10,20,30,60,80  null放在最前面
	}
	/***对象按照某种属性排序**/
	@Test
	public void testOrdering(){
		List<Person> personList=Lists.newArrayList(
			new Person("abc",  25),
			new Person("ab",  30),
			new Person( "ade",27),
			new Person("a", 20)
		);

		Ordering<Person> byAge=new Ordering<Person>() {
		@Override
		public int compare( Person left, Person right) {
			return left.getAge()-right.getAge();
		}
		};
		for(Person p: byAge.immutableSortedCopy(personList)){//升序
			System.out.println(p.getAge());// 20 25 27 30
		}
		for(Person p: byAge.reverse().immutableSortedCopy(personList)){//降序
			System.out.println(p.getAge());// 30 27 25 20
		}
		personList=byAge.reverse().immutableSortedCopy(personList);
		
	}
	
	/****Objects 一般使用**/
	@Test
	public void ObjectsTest(){
		Objects.equal("q","q");//true
		Objects.equal(null, "3");//false
		System.out.println(Objects.hashCode("5"));;
		System.out.println(Objects.toStringHelper("zhang").add("yin", 1).toString());
	}
	
	/***Optional测试     避免没有实例化的东西报错**/
	@Test
	public void OptionalTest(){
		Optional<Integer> possible=Optional.of(6);
		if(possible.isPresent()){
			System.out.println("possible:"+possible.get());
		}
		List<String> info=null;//不存在实例
		info=new ArrayList<String>();//实例化
		Optional<List<String>> list=Optional.of(info);
		if(list.isPresent()){//是否存在实例 没有实例化就返回false 存在实例返回true
			System.out.println(list.get().size());
		}else{
			System.out.println(list.isPresent());
		}
	}
	
	/********Lists 测试******/
	@Test
	public void ListsTest(){
		List<Integer> numbers=Lists.newArrayList(1,2,3,4);
		
		Lists.newArrayListWithCapacity(6);//在知道集合大小的情况下  设置集合个数为6
		
		Lists.newArrayListWithExpectedSize(6);//不确定元素个数
		
		ImmutableList<Character> ss=Lists.charactersOf("zhang");//字符全部转为 [z, h, a, n, g]
		System.out.println(ss);
	}
	
	/****Strings***/
	@Test
	public void StringsTest(){
		
		System.out.println(Strings.padStart("zhang", 8, '0'));//字符串长度至少为8，不够在开头用0补齐
		
		System.out.println(Strings.padEnd("zhang", 8, '0'));//字符串长度至少为8，不够在结尾用0补齐
		
		System.out.println(Strings.repeat("zhang", 3));//将字符创复制3遍拼接起来	
		
	
	}
	
	/****Splitter***/
	@Test
	public void SplitterTest(){
		System.out.println(Splitter.fixedLength(2).split("zhang"));//将字符创每两个两个分开成数组   [zh, an, g]
		System.out.println(Splitter.on(",").split("33,44"));
		Iterable<String> ss=Splitter.fixedLength(2).split("zhang");
		for(String info:ss){
			System.out.println(info);
		}
		System.out.println(Splitter.on(":,").split("33:,44,55,77"));
		
		System.out.println(Splitter.on(",").omitEmptyStrings().split("2,,,,4,,.5,,..6"));//去除掉的 ”，“ [2, 4, .5, ..6]
		System.out.println(Splitter.on("5").omitEmptyStrings().split("123556655456"));// 去除掉”5“ [123, 66, 4, 6]
		
	}
}
