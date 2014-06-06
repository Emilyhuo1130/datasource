package com.org.zhang.dao.face;


import javax.annotation.security.RolesAllowed;

import com.org.zhang.pojo.Users;

public interface SecurityTestInterface {
	//@RolesAllowed("ROLE_USER")
	Users findbyUsername(String name);

	boolean getinput();
	

}
