/*
 * ���ǹ����û�����������
 * ����ͬ�����촰��
 * */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.view.QQChat;
public class ManageQQChat {

	private static HashMap hm=new HashMap<String, QQChat>();
	
	//����QQChat����
	public static void addQQChat(String LoginIdFriendId,QQChat qqChat) {
		hm.put(LoginIdFriendId, qqChat);
	}
	
	//ȡ��QQChat����
		public static QQChat getQQChat(String LoginIdFriendId) {
			return (QQChat)hm.get(LoginIdFriendId);
		}
}
