package com.qq.server.model;

import java.util.*;

import com.qq.common.User;

import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.io.*;


public class JudgeFileThread extends Thread {

	ServerSocket server;//��һ���µķ���������
	Socket s;
	User u;
	public JudgeFileThread(ServerSocket sss,User u) {
		//���ӿͻ���
	  	  try {
			this.server = sss;
			this.u=u;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//�ڸ��߳�����ѭ���ж�
	public void run() {
		try {
			while(true) {
				s=this.server.accept();
				//�������Կͻ��˵�������Ϣ
				InputStreamReader isr=new InputStreamReader(s.getInputStream());
				BufferedReader br=new BufferedReader(isr);
				String info = br.readLine();
				
				System.out.println("���ж��߳������յ�"+info);
				
				if(info.equals("�ϴ�")) {
					System.out.println("���յ�һ���ϴ�����");
					
					FileInPutStreamThread cect=new FileInPutStreamThread(s);
					ManageFileThread.addFileThread(this.u.getUseId(), cect);
					
					//�ļ������̴߳�
					cect.start();
					System.out.println("�ļ������̴߳�");
				}
				else {
					System.out.println("���ܵ���������"+info);
					FileOutPutStreamThread dect=new FileOutPutStreamThread(s, info);
					
					//�ļ�����ȥ�̴߳�
					dect.start();
					System.out.println("�ļ�����ȥ�̴߳�");
					
					
				}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
