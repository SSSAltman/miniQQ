/*
 * ���ǿͻ������ӷ������ĺ�̨
 * */
package com.qq.client.model;

import java.awt.*;
import javax.swing.*;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.User;

import java.net.*;
import java.io.*;

public class QQClientConServer {

	public Socket s;
	
	//���͵�һ������
	public boolean sendLoginInfoToServer(Object o) {
		boolean b=false;
		try {
			//���ӿͻ���
			s=new Socket("192.168.124.1", 9999);
			//���Ͷ���
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			//���ܶ���
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			//���������֤�û���¼�ĵط�
			//System.out.println("���յ�����");
			//�ж��Ƿ��¼�ɹ�
			if(ms.getMesType().equals("1")) {
				//System.out.println("�ж���Ͽ��Դ��½���");
				//����һ����QQ�źͷ������������ӵ��߳�
				ClientConServerThread ccst =new ClientConServerThread(s,ms.getSender());
				//������ͨѶ�߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(((User)o).getUseId(), ccst);
				b=true;
				}
			else {
				s.close();
			}
			} catch (Exception e) {
			e.printStackTrace();
		}finally{
			}
		return b;
	}
	public void SendInfoToServer(Object o) {
		/*try {
			Socket s=new Socket("192.168.124.1", 9999);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}*/
	}
}
