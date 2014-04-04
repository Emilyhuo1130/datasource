package com.cxf.interFace;

import javax.jws.WebService;

@WebService
public interface Hello {
	public String sayHello(String name);
}
