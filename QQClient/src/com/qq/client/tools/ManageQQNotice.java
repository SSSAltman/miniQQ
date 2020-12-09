/*
 * 这是管理用户公告类
 * */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.view.QQChat;
import com.qq.client.view.QQNotice;
public class ManageQQNotice {

	private static HashMap im=new HashMap<String, QQNotice>();
	
	//加入QQNotice对象
	public static void addQQNotice(String LoginIdFriendId,QQNotice qqNotice) {
		im.put(LoginIdFriendId, qqNotice);
	}
	
	//取出QQChat对象
		public static QQNotice getQQNotice(String LoginIdFriendId) {
			return (QQNotice)im.get(LoginIdFriendId);
		}
}
