package com.test.aop.service;

import com.test.aop.bean.UserInfo;

public interface IAOPTest {

	void AopTest1();
	UserInfo AopTest2(UserInfo info, String string);
	void AopTest3() throws Exception;

}
