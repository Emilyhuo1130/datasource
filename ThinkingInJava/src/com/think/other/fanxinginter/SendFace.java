package com.think.other.fanxinginter;

public interface SendFace<T,R> extends PublicInterFace<T,R> {
	public T get();
	public <E> void get2(E e);
	
}
