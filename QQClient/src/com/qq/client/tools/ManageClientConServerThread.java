/*
 * ����һ������ͻ��˺ͷ���������ͨѶ���߳���
 * */
package com.qq.client.tools;

import java.util.*;

import com.qq.client.model.ClientConServerThread;
public class ManageClientConServerThread {

	private static HashMap hm=new HashMap<String, ClientConServerThread>();
	
	//�Ѵ����õ�ClientConServerThread���뵽hm
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst) {
		hm.put(qqId, ccst);
	}
	
	//����ͨ��qqIdȡ�ø��߳�
	public static ClientConServerThread getClientConServer(String qqId) {
		return (ClientConServerThread)hm.get(qqId);
	}
	
}
