package com.masterWorkertest;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class MainTest {
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		Master m=new Master(new PlusWorker(),5);
		for(int i=0;i<10000;i++)
			m.submit(i);
		m.execute();
		long re=0;
		Map<String,Object> resultMap=m.getResultMap();
		while(resultMap.size()>0||!m.isComplete()){
			//不需要等待所有的worker都执行完就可以进计算
			Set<String> keys = resultMap.keySet();
			String key=null;
			for(String k:keys){
				key=k;
				break;
			}
			Integer i=null;
			if(key!=null)
				i=(Integer)resultMap.get(key);
			if(i!=null)
				re+=i;//最终结果
			if(key!=null)
				resultMap.remove(key);//移除已经计算过的
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
		System.out.println(re);//400289378368
	}
	@Test
	public void testother(){
		long t1 = System.currentTimeMillis();
		long re=0l;
		for(int i=0;i<10000;i++)
			re+=(i*i*i);
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
		System.out.println(re);//400289378368
	}					
	
}
