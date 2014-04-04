package com.ucs.rcm.business.bo;
/**列车健康状况指标*/
public class Health {

	private String lineNo;//线号
	private String stats;//状态
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getStats() {
		return stats;
	}
	public void setStats(String stats) {
		this.stats = stats;
	}
	
}
