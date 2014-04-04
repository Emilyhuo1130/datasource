package com.ucs.cctv.Trees;

import java.util.List;

public class SectionTrees {
	private String name;
	private List<SectionChildren> children;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SectionChildren> getChildren() {
		return children;
	}
	public void setChildren(List<SectionChildren> children) {
		this.children = children;
	}

}
