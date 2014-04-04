package com.ucs.cctv.Trees;

import java.util.List;

public class SectionCameraTrees {
	private String name;
	private List<CameraTrees> children;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CameraTrees> getChildren() {
		return children;
	}
	public void setChildren(List<CameraTrees> children) {
		this.children = children;
	}

}
