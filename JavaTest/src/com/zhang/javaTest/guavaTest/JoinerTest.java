package com.zhang.javaTest.guavaTest;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.primitives.Ints;

public class JoinerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] ss={"jfuei93yfjv99dsuus","99",null,"ihy"};
		//System.out.println(Joiner.on(":").join(ss));//将数组用“：”分割
		
		//要是需要对null做特殊处理, 比如打印"NAN": 
		//System.out.println(Joiner.on(":").useForNull("NAN").join(ss));
		
		//或者干脆把null滤掉: 
		//System.out.println(Joiner.on(";").skipNulls().join(ss));
		
		
		int[] intarrays={1,2,3,4,5};
		/*String s2=Ints.join(";", intarrays);
		System.out.println(s2);*/
		
		//splitter
		for (String word : Splitter.on(',').split("ajoo,so,handsome!")) {  
			  System.out.println(word);  
			}  
	}

}
