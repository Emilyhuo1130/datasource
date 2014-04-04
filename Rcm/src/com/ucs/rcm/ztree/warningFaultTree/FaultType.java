package com.ucs.rcm.ztree.warningFaultTree;

public class FaultType {
	//private String click;
	private String name;
	private String icon="../../assets/javascripts/dhtmlxTree/codebase/imgs/csh_books/leaf.gif";
	private Object font;
	private int value;
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Object getFont() {
		return font;
	}
	public void setFont(Object font) {
		this.font = font;
	}
	
}
