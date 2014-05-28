package com.org.zhang.service.face;
import javax.annotation.security.RolesAllowed;
public interface SecurityServiceInterFace {
	@RolesAllowed("USER")
	boolean getinput();

}
