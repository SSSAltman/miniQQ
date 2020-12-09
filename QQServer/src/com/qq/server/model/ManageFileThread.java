/*
 * 作用:服务器用来管理客户端传输文件的线程类
 * */
package com.qq.server.model;

import java.util.*;

public class ManageFileThread {

	public static HashMap km=new HashMap<String,FileInPutStreamThread>();
	
	//向km中添加一个客户端传文件线程
	public static void addFileThread(String uid,FileInPutStreamThread bt) 
	{
		km.put(uid, bt);
	}
	
	public static FileInPutStreamThread getFileThread(String uid) {
		return (FileInPutStreamThread)km.get(uid);
	}
}
