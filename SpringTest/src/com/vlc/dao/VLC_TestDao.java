package com.vlc.dao;

import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vlc.face.VLC_Testface;
@Repository("VLC_TestDao")
public class VLC_TestDao implements VLC_Testface{

	private static Process exec=null;

	@Override
	public boolean start() {
		try {
			exec = Runtime.getRuntime().exec("cmd /c start D:/VideoLAN/VLC/vlc.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean end() {
		if(exec!=null){
			exec.destroy();
			return true;
		}else{
			return false;
		}
	}

}
