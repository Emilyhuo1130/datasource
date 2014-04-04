package com.cctv.videoManage.server;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cctv.videoManage.face.VideoFace;

@Controller
public class Video {
	@Resource
	private VideoFace dao;
	
	@RequestMapping(value="/jsp/start")
	@ResponseBody
	public boolean start(HttpServletRequest req,HttpServletResponse res){
		String ip=req.getParameter("ip");
		String address=req.getParameter("address");
		System.out.println(ip+"   "+address);
		return dao.start(ip,address);
	}
	
	@RequestMapping(value="/jsp/end")
	@ResponseBody
	public boolean end(HttpServletRequest req,HttpServletResponse res){
		return dao.end();
	}
	
	@RequestMapping(value="/jsp/linux")
	@ResponseBody
	public boolean linux(HttpServletRequest req,HttpServletResponse res){
		try{
			Runtime.getRuntime().exec("vlc");
		}catch(Exception  e){
			
		}
		return true;
	}
	
	

}
