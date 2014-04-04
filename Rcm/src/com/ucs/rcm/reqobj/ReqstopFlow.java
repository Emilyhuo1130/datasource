package com.ucs.rcm.reqobj;

public class ReqstopFlow {
	private int id;
	private String statments;
	private String username;//用来判断是哪个用户操作，用户不一样操作不一样
	private String operator;						//user3 终止流程则必须删除副表的记录  必须由user2重新添加
							//参数选择  user1   user2   user3   user4   
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatments() {
		return statments;
	}

	public void setStatments(String statments) {
		this.statments = statments;
	}
	
	
}
