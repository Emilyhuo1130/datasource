package com.proxytest;
/**利用代理模式的延迟加载*/
public class DBQueryProxy implements IDBQuery{
	private DBQuery real=null;
	@Override
	public String request() {
		if(null==real)
			real= new DBQuery();
		return real.request();
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if(real==null)
			real= new DBQuery();
		return real.getName();
	}

}
