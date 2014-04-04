package com.ucs.rcm.business.bo;
/**历史等级指数*/
import java.util.List;

public class LevelHistory {

	private String interval;
	private List<LevelSeqs> lines;

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public List<LevelSeqs> getLines() {
		return lines;
	}

	public void setLines(List<LevelSeqs> lines) {
		this.lines = lines;
	}

	 

}
