package com.ucs.rcm.ztree.strategyDesign;

import java.util.List;

public class Component {
	private boolean open=false;
	private List<Sub> children;
	private String name;
	private String iconOpen="../../assets/javascripts/dhtmlxTree/codebase/imgs/csh_books/folderOpen.gif";
	private String iconClose="../../assets/javascripts/dhtmlxTree/codebase/imgs/csh_books/folderClosed.gif";
	
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public List<Sub> getChildren() {
		return children;
	}
	public void setChildren(List<Sub> children) {
		this.children = children;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconOpen() {
		return iconOpen;
	}
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public String getIconClose() {
		return iconClose;
	}
	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}
	
	
	
	
	



	
}
