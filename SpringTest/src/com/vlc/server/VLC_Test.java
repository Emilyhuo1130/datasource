package com.vlc.server;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vlc.face.VLC_Testface;

@Controller
public class VLC_Test {
	@Resource
	private VLC_Testface dao;
	
	@RequestMapping(value="/start.do")
	@ResponseBody
	public boolean start(){
		return dao.start();
	}
	
	@RequestMapping(value="/end.do")
	@ResponseBody
	public boolean end(){
		return dao.end();
	}
	
}
