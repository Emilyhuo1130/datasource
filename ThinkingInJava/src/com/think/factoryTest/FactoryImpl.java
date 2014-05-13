package com.think.factoryTest;

public class FactoryImpl implements Factiry{
	static{
		System.out.println("º”‘ÿ¿‡");
	}
	public Service getService() {
		return new ServiceImpl();
	}
	public User getUser(){
		return new UserImpl();
	}
	public String getString(){
		return "some";
	}

}
