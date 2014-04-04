package com.ucs.security.server;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.ucs.security.dao.SecurityTestDao;
import com.ucs.security.face.SecurityTestInterface;
import com.ucs.security.pojo.Users;
/**
 * JAAS配置
 * */
public class DefaultLoginModel implements LoginModule{

	private Subject subject;
	private CallbackHandler callbackHandler;
	private String userName;
	private String passWord;
	@Resource
	private SecurityTestInterface dao;//无法加载到ioc容器中
	private Users findbyUsername;
	Logger log=Logger.getLogger(DefaultLoginModel.class);
	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
				this.subject = subject;
				this.callbackHandler = callbackHandler;
				NameCallback namecallback=new NameCallback("prompt");
				PasswordCallback passwordcallback=new PasswordCallback("prompt", false);
				try {
					callbackHandler.handle(new Callback[] {namecallback,passwordcallback});
					userName=namecallback.getName();
					passWord=new String(passwordcallback.getPassword());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (UnsupportedCallbackException e) {
					e.printStackTrace();
				}
		
	}

	@Override
	public boolean login() throws LoginException {
		//用户登录验证，
		log.info("username:"+userName+"   password:"+passWord);
		//findbyUsername = dao.findbyUsername(userName);
		log.info("数据库查到的用户信息"+findbyUsername.getName());
		if(findbyUsername.getName().equals(userName)&&findbyUsername.getPassword().equals(passWord)){
			UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(userName, passWord);
			subject.getPrincipals().add(upat);
			return true;
			
		}else{
			return false;
		}
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return true;
	}

}
