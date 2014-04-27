package com;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
public class FileUploadController {
	/****多文件上传***/
	@RequestMapping(value="/jsp/testUpload1")
	@ResponseBody
	public boolean testUpload1(@RequestParam MultipartFile[] file){
		for(MultipartFile f:file){
			System.out.println(f.getOriginalFilename());
		}
		
		return true;
	}
	
	/***单文件上传**/
	@RequestMapping(value="/jsp/testUpload2")
	@ResponseBody
	public boolean testUpload2(@RequestParam MultipartFile file,HttpServletRequest req) throws IOException{
		
		InputStream is = file.getInputStream();
		String uploadFilePath=req.getSession().getServletContext().getRealPath("/")+"upload/";
		String filename = file.getOriginalFilename();
		//File tempfile=new File(uploadFilePath+filename);
		FileOutputStream fos=new FileOutputStream(uploadFilePath+filename);
		byte[] b=new byte[8192];
		int count =0;
		while((count=is.read(b))>0){
			fos.write(b,0,count);
		}
		fos.close();
		is.close();
		
		return true;
	}
	
	/***基于ajax的单文件上传**/
	@RequestMapping(value="/jsp/ajaxUploadTest")
	@ResponseBody
	public boolean ajaxUploadTest(@RequestParam MultipartFile file,HttpServletRequest req) throws IOException{
		
		InputStream is = file.getInputStream();
		String uploadFilePath=req.getSession().getServletContext().getRealPath("/")+"upload/";
		String filename = file.getOriginalFilename();
		//File tempfile=new File(uploadFilePath+filename);
		FileOutputStream fos=new FileOutputStream(uploadFilePath+filename);
		byte[] b=new byte[8192];
		int count =0;
		while((count=is.read(b))>0){
			fos.write(b,0,count);
		}
		fos.close();
		is.close();
		
		return true;
	}
	
	
	@RequestMapping(value="/jsp/testUpload3")
	@ResponseBody
	public boolean testUpload3(HttpServletRequest req,HttpServletResponse res){
		MultipartHttpServletRequest multi=(MultipartHttpServletRequest)req;
		 MultipartFile file = multi.getFile("file");  
		
		System.out.println(req.getSession().getServletContext().getRealPath("/")+"upload/");
		return true;
	}
	
	
	
	
}
