package com.think.factoryTest;

import java.util.List;

public interface Service {
	public void getName();
	public void getPassWord();
	public <E> void get();
	public <E> List<E> getList(E...es);
}
