/*
 * ���ǹ���ͶƱ�����������
 * */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.view.ClientVote;

public class ManageClientVote {

private static HashMap lm=new HashMap<String,ClientVote >();
	
	//����ClientVote����
	public static void addClientVote(String LoginIdFriendId,ClientVote client) {
		lm.put(LoginIdFriendId, client);
	}
	
	//ȡ��ClientVote����
		public static ClientVote getClientVote(String LoginIdFriendId) {
			return (ClientVote)lm.get(LoginIdFriendId);
		}
	
}
