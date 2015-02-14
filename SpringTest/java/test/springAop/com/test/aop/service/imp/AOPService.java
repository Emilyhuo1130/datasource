package com.test.aop.service.imp;

import org.springframework.stereotype.Service;

import com.test.aop.bean.UserInfo;
import com.test.aop.service.IAOPTest;
@Service
public class AOPService implements IAOPTest{

	public void AopTest1() {
		
		
	}

	public UserInfo AopTest2() {
		UserInfo info=new UserInfo();
		info.setAge(22);
		return info;
	}

	@Override
	public void AopTest3() throws Exception {
		try{
			
		}catch(Exception e){
			throw new Exception("一個測試的異常");
		}
		
	}

	@Override
	public UserInfo AopTest2(UserInfo info, String string) {
		// TODO Auto-generated method stub
		return null;
	}


}
