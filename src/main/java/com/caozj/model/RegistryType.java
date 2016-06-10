package com.caozj.model;

import java.io.Serializable;

/**
 * 注册类型
 * 
 * @author caozj
 *
 */
public class RegistryType implements Serializable {

	private static final long serialVersionUID = 3417111265402798750L;

	private int id;

	private String text;

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

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistryType [id=");
		builder.append(id);
		builder.append(", text=");
		builder.append(text);
		builder.append(", parentID=");
		builder.append(parentID);
		builder.append("]");
		return builder.toString();
	}

}
