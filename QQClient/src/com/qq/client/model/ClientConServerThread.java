/*
 *���ǿͻ��˺ͷ���������ͨ�ŵ��߳�
 * */
package com.qq.client.model;

import java.util.*;

import com.qq.client.tools.ManageClientVote;
import com.qq.client.tools.ManageQQChat;
import com.qq.client.tools.ManageQQNotice;
import com.qq.client.view.*;
import com.qq.client.view.QQChat;
import com.qq.common.Message;

import java.io.ObjectInputStream;
import java.net.*;

public class ClientConServerThread extends Thread{

	int yes=0;
	int no=0;
	
	private Socket s;  
	private String owner;
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	//���캯��
	public ClientConServerThread(Socket s,String owner) {
		this.s=s;
		this.owner=owner;
	}
	
	public void run() 
	{
		while(true) 
		{
			//��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				//System.out.println("�������յ���Ϣ: "+m.getSender()+" �� "+m.getGetter()+" ������ "+m.getCon());
				if(m.getGetter().equals("�߰�Ⱥ��")) {
					//�Ѵӷ�������õ���Ϣ����ʾ��Ӧ����ʾ���������
					//System.out.println("�����owner��"+this.owner);
					QQChat qqChat=ManageQQChat.getQQChat(this.owner+" "+"�߰�Ⱥ��");
					if(qqChat==null) {
						System.out.println("null");
					}
					//��ʾ
					qqChat.showMessage(m);
				}else if(m.getGetter().equals("����")) {
					//�Ѵӷ�������õ���Ϣ,��ʾ���������
					System.out.println("���յ�������Ϣ��׼���򿪹������");
					QQNotice qqnotice=ManageQQNotice.getQQNotice("�Լ�");
					System.out.println("��������"+m.getSender());
					if(qqnotice==null) {
						System.out.println("null");
					}
					//��ʾ
					qqnotice.showMessage(m);
				}
				else if(m.getGetter().equals("ͶƱ")) {
					//ȡ��ͶƱ����
					ClientVote client=ManageClientVote.getClientVote("ͶƱ");
					this.yes=0;
					this.no=0;
					client.showMessage(m);
				}
				else if(m.getGetter().equals("�޳�")) {
					//ȡ��ͶƱ����
					System.out.println("���յ��޳�Ʊ");
					this.yes++;
					m.setSender(yes+"");
					ClientVote client=ManageClientVote.getClientVote("ͶƱ");
					client.showYesMen(m);
				}
				else if(m.getGetter().equals("����")) {
					//ȡ��ͶƱ����
					System.out.println("���յ�����Ʊ");
					this.no++;
					m.setSender(no+"");
					ClientVote client=ManageClientVote.getClientVote("ͶƱ");
					client.showNoMen(m);
				}
				else {
				
				//�Ѵӷ�������õ���Ϣ����ʾ��Ӧ����ʾ���������
				QQChat qqChat=ManageQQChat.getQQChat(m.getGetter()+" "+m.getSender());
				//��ʾ
				qqChat.showMessage(m);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
