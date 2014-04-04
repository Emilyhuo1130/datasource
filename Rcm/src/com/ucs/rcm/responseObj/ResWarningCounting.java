package com.ucs.rcm.responseObj;

import java.util.List;



import com.ucs.rcm.business.bo.LevelCount;
import com.ucs.rcm.business.bo.LevelHistory;
import com.ucs.rcm.business.bo.LineCount;

public class ResWarningCounting {

	private List<LineCount> lineCounts;//所有的线号
	private List<LevelCount> levelCounts;//所有的警告等级
	private LevelHistory levelHistory;//所有历史警告等级

	

	public LevelHistory getLevelHistory() {
		return levelHistory;
	}

	public void setLevelHistory(LevelHistory levelHistory) {
		this.levelHistory = levelHistory;
	}


	public List<LevelCount> getLevelCounts() {
		return levelCounts;
	}

	public List<LineCount> getLineCounts() {
		return lineCounts;
	}

	public void setLineCounts(List<LineCount> lineCounts) {
		this.lineCounts = lineCounts;
	}

	public void setLevelCounts(List<LevelCount> levelCounts) {
		this.levelCounts = levelCounts;
	}

}
