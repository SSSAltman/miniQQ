/*
 * QQ�����������ڼ������ȴ�ĳ��qq�ͻ�������
 * */
package com.qq.server.model;

import java.util.*;
import java.net.*;
import java.nio.Buffer;
import java.sql.*;
import javax.swing.*;
import javax.xml.ws.handler.MessageContext;

import com.qq.common.Message;
import com.qq.common.User;

import java.io.*;

public class MyQQServer {
    
	public static void main(String[]args) {
		
	}
	
	public MyQQServer() {
		
		try {
			System.out.println("��������9999�Ŷ˿ڽ��м���");
			//��9999�ż���
			ServerSocket ss=new ServerSocket(9999);
			ServerSocket sss=new ServerSocket(3456);
			//�����ȴ�����
			while(true) {
				
				Socket s=ss.accept();
				
			//���տͻ��˷����ĵ�½��֤��Ϣ
				
					ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
					User u=(User)ois.readObject();
			
			
			//System.out.println("���������յ��û���  "+u.getUseId()+"\n"+"���������յ�����"+u.getPasswd());
			
			
			//�������������ݿ����֤
			String account1 =u.getUseId();
			//��������˺�
			String passwords =u.getPasswd();
			String account2 = " ", account3 = " ";//account2�����ݿ��������
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/liangshanheros2?useSSL=true&serverTimezone=GMT";
			String user = "root";
			String password = "Lyshangqhua000";
			String sqlStr = "select passward from 7c where number = '"+account1+"'";
				PreparedStatement ps = null;
				Connection ct = null;
				ResultSet rs = null;
				try {
					Class.forName(driver);
				    ct = DriverManager.getConnection(url, user, password);
				    ps = ct.prepareStatement(sqlStr);
				    rs = ps.executeQuery();
				    while(rs.next()) {
				    	//�����ݿ���ȡ������������
				    	account2 = rs.getString(1);
				    }
				}catch(Exception e1){
					e1.printStackTrace();
				}finally {
					try {
						if(rs != null) rs.close();
						if(ps != null) ps.close();
						if(ct != null) ct.close();
						
					}catch(Exception e1) {
						e1.printStackTrace();
					}finally {	
						
					}
				}
			
			
			//��Ϊ���صĶ���
			Message m = new Message();
			//���ض���
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			
			//�����ж��˺ź������Ƿ���ȷ
			if(u.getPasswd().equals(account2)) {
				
				//���سɹ���½����Ϣ
				m.setMesType("1");
				m.setSender(u.getUseId());
				oos.writeObject(m);
				
				
				//����һ���̣߳��ø��߳���ͻ��˱���ͨѶ,���Ұ��̼߳��뵽��������
				SerConClientThread sect=new SerConClientThread(s);
				ManageClientThread.addClientThread(u.getUseId(), sect);
				//�����Ϳͻ��˽������߳�
				sect.start();
				
				
				//����һ���߳�,�ø��߳��������жϿͻ��˶��ļ��Ĳ�ͬ����
				JudgeFileThread jge = new JudgeFileThread(sss, u);
				jge.start();
				System.out.println("�ж��߳��Ѿ�����");
				//System.out.print("�Ѿ�����ȷ��ֵ");
			}else {
				//����ʧ�ܵ�½����Ϣ
				m.setMesType("2");
				oos.writeObject(m);
				//System.out.print("�Ѿ�����ȷ��ֵ");
				//�ر�����
				s.close();
				
			}
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
