package com.ucs.cctv.Response;

import java.util.List;
import com.ucs.cctv.Pojo.OperateLog;

public class LogResponse {
	List<OperateLog> logInfo;
    int pages;
	public List<OperateLog> getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(List<OperateLog> logInfo) {
		this.logInfo = logInfo;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}

}
