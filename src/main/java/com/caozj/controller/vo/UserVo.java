package com.caozj.controller.vo;

import com.caozj.model.permission.User;
import com.caozj.model.permission.UserExt;

public class UserVo {

	private User user;

	private UserExt userExt;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserExt getUserExt() {
		return userExt;
	}

	public void setUserExt(UserExt userExt) {
		this.userExt = userExt;
	}

}
