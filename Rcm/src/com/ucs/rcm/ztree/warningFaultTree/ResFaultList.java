package com.ucs.rcm.ztree.warningFaultTree;

import java.util.List;

public class ResFaultList {
	private List<SystemName> name;
	private boolean nocheck=true;

	public List<SystemName> getName() {
		return name;
	}

	public void setName(List<SystemName> name) {
		this.name = name;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}
	
	
}
