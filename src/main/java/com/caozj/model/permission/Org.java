package com.caozj.model.permission;

import java.io.Serializable;

/**
 * 机构
 * 
 * @author caozj
 *
 */
public class Org implements Serializable {

	private static final long serialVersionUID = 5050560047226916827L;

	private int id;

	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Org [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
