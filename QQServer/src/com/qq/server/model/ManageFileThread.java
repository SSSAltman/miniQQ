/*
 * ����:��������������ͻ��˴����ļ����߳���
 * */
package com.qq.server.model;

import java.util.*;

public class ManageFileThread {

	public static HashMap km=new HashMap<String,FileInPutStreamThread>();
	
	//��km�����һ���ͻ��˴��ļ��߳�
	public static void addFileThread(String uid,FileInPutStreamThread bt) 
	{
		km.put(uid, bt);
	}
	
	public static FileInPutStreamThread getFileThread(String uid) {
		return (FileInPutStreamThread)km.get(uid);
	}
}
