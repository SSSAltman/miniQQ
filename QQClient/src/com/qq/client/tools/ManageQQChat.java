/*
 * 这是管理用户聊天界面的类
 * 管理不同的聊天窗口
 * */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.view.QQChat;
public class ManageQQChat {

	private static HashMap hm=new HashMap<String, QQChat>();
	
	//加入QQChat对象
	public static void addQQChat(String LoginIdFriendId,QQChat qqChat) {
		hm.put(LoginIdFriendId, qqChat);
	}
	
	//取出QQChat对象
		public static QQChat getQQChat(String LoginIdFriendId) {
			return (QQChat)hm.get(LoginIdFriendId);
		}
}
