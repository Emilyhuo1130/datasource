package com.ucs.rcm.responseObj;

import java.util.List;

public class ResOneSysmaintaiList {
	private List<OneLineSysmaintai> infoList;
	private long[] updateTime;

	public List<OneLineSysmaintai> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<OneLineSysmaintai> infoList) {
		this.infoList = infoList;
	}

	public long[] getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long[] updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
