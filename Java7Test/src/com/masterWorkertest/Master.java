package com.masterWorkertest;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/***
 * 一个任务分成若干个小任务，Master负责接收和分配任务
 * */
public class Master {
	//任务列队
	protected Queue<Object> workerQueue=new ConcurrentLinkedQueue<Object>();
	//worker进程列队
	protected Map<String,Thread> threadMap=new HashMap<String,Thread>();
	//子任务处理结果集
	protected Map<String,Object> resultMap=new ConcurrentHashMap<String,Object>();
	//构造函数，需要一个Worker进程逻辑，需要Worker进程数量
	public Master(Worker worker,int countWorker){
		worker.setWorkerQueue(workerQueue);
		worker.setResultMap(resultMap);
		for(int i=0;i<countWorker;i++)
			threadMap.put(Integer.toString(i), new Thread(worker,Integer.toString(i)));
	}
	
	//是否所有子任务都结束了
	public boolean isComplete(){
		for(Map.Entry<String, Thread> entry:threadMap.entrySet()){
			if(entry.getValue().getState()!=Thread.State.TERMINATED)
				return false;
		}
		return true;
	}
	//提交任务
	public void submit(Object job){
		workerQueue.add(job);
	}
	
	//返回子任务结果集
	public Map<String,Object> getResultMap(){
		return resultMap;
	}
	
	//开始运行所有的Woker进程进行处理
	public void execute(){
		for(Map.Entry<String, Thread> entry:threadMap.entrySet())
			entry.getValue().start();
	}
	
	
	
	
	
	
	
	
		
		

	
	
	

}
