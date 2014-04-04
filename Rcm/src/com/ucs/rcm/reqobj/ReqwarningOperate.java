package com.ucs.rcm.reqobj;

public class ReqwarningOperate {
	private int[] ids;//要操作的id
	private String action;//要更新的状态
	private String operator;//记录当前操作人员
	
	
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	


	
}
