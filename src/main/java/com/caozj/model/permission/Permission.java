package com.caozj.model.permission;

import java.io.Serializable;

public class Permission implements Serializable{

	private static final long serialVersionUID = 4619631216312892345L;
	
	private int id;
	
	private String text;
	
	/**
	 * 权限代表的url，多个用,隔开
	 */
	private String url;
	
	private int parentID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Permission [id=");
		builder.append(id);
		builder.append(", text=");
		builder.append(text);
		builder.append(", url=");
		builder.append(url);
		builder.append(", parentID=");
		builder.append(parentID);
		builder.append("]");
		return builder.toString();
	}
	
	

}
