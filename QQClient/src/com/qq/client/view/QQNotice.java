/*
 * QQ�������͹������
 * ����ԱȨ�޷��͹����Ĺ�����Ϣ���ڴ���ʾ
 * ���з��͹��水ť
 * */
package com.qq.client.view;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.net.*;

import com.qq.client.model.QQClientConServer;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;

public class QQNotice extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	BackImage jp1;
	String ownerId;
	String friendId; 
	public static void main(String[]args) {
	
	}
	
	public QQNotice(String ownerid) {
		try { // ʹ��Windows�Ľ�����
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
		this.ownerId=ownerid;
		
		jp1=new BackImage("Image/����.png");
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("���͹���");
		jb.addActionListener(this);
		jp=new JPanel();
		jp1.add(jta);
		jp.add(jtf);
		jp.add(jb);
		
		this.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        jtf.requestFocus();
		    }
		}); 
		
		//��Ӽ��̼�����ʵ����������ﰴ��Enter֮����Է���
				jtf.addKeyListener(new KeyAdapter() {
			      	public void keyPressed(KeyEvent e) {
			      		if(e.getKeyChar()==KeyEvent.VK_ENTER) {
			      			jb.doClick();
			      		}
			      	}
			      });
		this.setTitle("�߰�༶����");
		this.add(jp1,"Center");
		this.add(jp, "South");
		this.setIconImage((new ImageIcon("Image/picture1.jpg").getImage()));
		this.setSize(1000,600);
		this.setVisible(true);
		
	}

	
	//��ʾ����Ϣ�ķ���
	public void showMessage(Message m){
		//��ʾ��Ϣ
		//String info=m.getSender()+" �� "+m.getGetter()+" ˵ "+m.getCon()+"\r\n";
		String info=m.getSender()+" �����˹��� "+m.getCon()+"\r\n";
		this.jta.append(info);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==jb) {
			//����û�����ˣ����͹���
			Message m=new Message();
			m.setSender(this.ownerId);
			m.setGetter("����");
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			//System.out.println("�����װ��ϣ�׼������");
			//���͸�������,�����߳�
			try {
				this.showMessage(m);
				ObjectOutputStream oos=new ObjectOutputStream
						(ManageClientConServerThread.getClientConServer(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				//System.out.println("��Ϣ���������");
			} catch (Exception e) {
				e.printStackTrace();
			}
			//�Լ�����Ϣ������Ϣ���
			this.jtf.setText("");
			
		}
	}

//	@Override
//	public void run() {
//		
//		while(true) 
//		{
//			//��ȡ������������͵ȴ�
//			try {
//				//��ȡ
//				ObjectInputStream ois = new ObjectInputStream(QQClientConServer.s.getInputStream());
//				Message m=(Message)ois.readObject();
//				
//				//��ʾ��Ϣ
//				String info=m.getSender()+" �� "+m.getGetter()+" ˵ "+m.getCon()+"\r\n";
//				this.jta.append(info);
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
	//�ܹ��ڵײ���ʾͼƬ��һ���ڲ���JPanel
	class BackImage extends JPanel
	{
		Image im;
		public BackImage(String path){
			try
			{
				im=ImageIO.read(new File(path));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void paintComponent(Graphics g) {
			g.drawImage(im,0,0,this);
		}
		
	}
}
