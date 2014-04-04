package com.ucs.rcm.ztree.warningEquipmentTree;

import java.util.List;

public class SystemName {
	private boolean open=false;
	private String name;
	private String iconOpen="../../assets/javascripts/dhtmlxTree/codebase/imgs/csh_books/folderOpen.gif";
	private String iconClose="../../assets/javascripts/dhtmlxTree/codebase/imgs/csh_books/folderClosed.gif";
	private List<SubSystemName> children;
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
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
	public List<SubSystemName> getChildren() {
		return children;
	}
	public void setChildren(List<SubSystemName> children) {
		this.children = children;
	}
	
}
