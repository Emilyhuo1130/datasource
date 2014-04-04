package com.ucs.cctv.Trees;

import java.util.List;

public class CameraTrees {
	private String name;
	private List<CameraChildren> children;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CameraChildren> getChildren() {
		return children;
	}
	public void setChildren(List<CameraChildren> children) {
		this.children = children;
	}
}
