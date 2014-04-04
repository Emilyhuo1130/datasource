package com.ucs.cctv.Response;

import java.util.List;
import com.ucs.cctv.Pojo.RecorderInfo;

public class RecorderResponse {
	List<RecorderInfo> recorderInfo;
    int pages;
	public List<RecorderInfo> getRecorderInfo() {
		return recorderInfo;
	}
	public void setRecorderInfo(List<RecorderInfo> recorderInfo) {
		this.recorderInfo = recorderInfo;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

}
