/*
 * ���ǹ����û�������
 * */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.view.QQChat;
import com.qq.client.view.QQNotice;
public class ManageQQNotice {

	private static HashMap im=new HashMap<String, QQNotice>();
	
	//����QQNotice����
	public static void addQQNotice(String LoginIdFriendId,QQNotice qqNotice) {
		im.put(LoginIdFriendId, qqNotice);
	}
	
	//ȡ��QQChat����
		public static QQNotice getQQNotice(String LoginIdFriendId) {
			return (QQNotice)im.get(LoginIdFriendId);
		}
}
