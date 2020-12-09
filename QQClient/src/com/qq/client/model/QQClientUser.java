package com.qq.client.model;

import com.qq.common.User;

public class QQClientUser {

	public boolean checkUser(User u) {
		return new QQClientConServer().sendLoginInfoToServer(u);
		}
	
}
