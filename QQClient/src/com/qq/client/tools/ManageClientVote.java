/*
 * 这是管理投票界面对象的类的
 * */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.view.ClientVote;

public class ManageClientVote {

private static HashMap lm=new HashMap<String,ClientVote >();
	
	//加入ClientVote对象
	public static void addClientVote(String LoginIdFriendId,ClientVote client) {
		lm.put(LoginIdFriendId, client);
	}
	
	//取出ClientVote对象
		public static ClientVote getClientVote(String LoginIdFriendId) {
			return (ClientVote)lm.get(LoginIdFriendId);
		}
	
}
