package com.ucs.rcm.pdrdataUtils;

public class KSKPointInfo {
	

	private String pointCode;//点代码
	private String meastype;//数据类型
	private float[] values;//32个值
	public String getPointCode() {
		return pointCode;
	}
	public void setPointCode(String pointCode) {
		this.pointCode = pointCode;
	}
	public String getMeastype() {
		return meastype;
	}
	public void setMeastype(String meastype) {
		this.meastype = meastype;
	}
	public float[] getValues() {
		return values;
	}
	public void setValues(float[] values) {
		this.values = values;
	}
	

	
	
	
}
