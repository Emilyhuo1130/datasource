package com.mvcTest;

import java.io.File;

import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;

public class Upload {
	@AdaptBy(type = UploadAdaptor.class, args = { "ioc:myUpload" })
	public void uploadPhoto(@Param(value = "photo") File f){
		
	}
}
