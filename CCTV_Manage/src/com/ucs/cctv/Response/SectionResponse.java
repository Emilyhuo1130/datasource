package com.ucs.cctv.Response;

import java.util.List;

import com.ucs.cctv.Pojo.SectionInfo;

public class SectionResponse {
	private List<SectionInfo> sectionInfo;
	private int pages;
	public List<SectionInfo> getSectionInfo() {
		return sectionInfo;
	}
	public void setSectionInfo(List<SectionInfo> sectionInfo) {
		this.sectionInfo = sectionInfo;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

}
