package com.ucs.cctv.Response;

import java.util.List;

import com.ucs.cctv.Pojo.CameraInfo;

public class CameraResponse{
	private List<CameraInfo> cameraInfo;
    private int pages;
	public List<CameraInfo> getCameraInfo() {
		return cameraInfo;
	}
	public void setCameraInfo(List<CameraInfo> cameraInfo) {
		this.cameraInfo = cameraInfo;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

}
