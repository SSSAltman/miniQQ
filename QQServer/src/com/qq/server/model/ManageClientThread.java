/*
 * 作用:服务器用来管理客户端聊天的线程类
 * */
package com.qq.server.model;

import java.util.*;

public class ManageClientThread {

	public static HashMap hm=new HashMap<String,SerConClientThread>();
	
	//向hm中添加一个客户端通信线程
	public static void addClientThread(String uid,SerConClientThread ct) 
	{
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid) 
	{
	return (SerConClientThread)hm.get(uid);	
	}
}
