package com.test.webservices;

import javax.jws.WebService;

@WebService
public interface IHello {
	public String hello();
	public String say();
}
