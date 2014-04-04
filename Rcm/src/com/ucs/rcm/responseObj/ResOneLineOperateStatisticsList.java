package com.ucs.rcm.responseObj;

import java.util.List;

public class ResOneLineOperateStatisticsList {
	private List<OneLineOperateStatistics> infoList;
	private long[] updateTime;

	public long[] getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long[] updateTime) {
		this.updateTime = updateTime;
	}

	public List<OneLineOperateStatistics> getInfoList() {
		return infoList;
	}

	public void setInfoList(List<OneLineOperateStatistics> infoList) {
		this.infoList = infoList;
	}
	
}
