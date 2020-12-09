/*
 * QQ用来发送公告的类
 * 管理员权限发送过来的公告消息会在此显示
 * 具有发送公告按钮
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
		try { // 使用Windows的界面风格
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
		this.ownerId=ownerid;
		
		jp1=new BackImage("Image/公告.png");
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("发送公告");
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
		
		//添加键盘监听，实现在密码框里按下Enter之后可以发送
				jtf.addKeyListener(new KeyAdapter() {
			      	public void keyPressed(KeyEvent e) {
			      		if(e.getKeyChar()==KeyEvent.VK_ENTER) {
			      			jb.doClick();
			      		}
			      	}
			      });
		this.setTitle("七班班级公告");
		this.add(jp1,"Center");
		this.add(jp, "South");
		this.setIconImage((new ImageIcon("Image/picture1.jpg").getImage()));
		this.setSize(1000,600);
		this.setVisible(true);
		
	}

	
	//显示出消息的方法
	public void showMessage(Message m){
		//显示信息
		//String info=m.getSender()+" 对 "+m.getGetter()+" 说 "+m.getCon()+"\r\n";
		String info=m.getSender()+" 发送了公告 "+m.getCon()+"\r\n";
		this.jta.append(info);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==jb) {
			//如果用户点击了，发送公告
			Message m=new Message();
			m.setSender(this.ownerId);
			m.setGetter("公告");
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			//System.out.println("对象封装完毕，准备发送");
			//发送给服务器,启动线程
			try {
				this.showMessage(m);
				ObjectOutputStream oos=new ObjectOutputStream
						(ManageClientConServerThread.getClientConServer(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				//System.out.println("消息对象发送完毕");
			} catch (Exception e) {
				e.printStackTrace();
			}
			//自己的消息栏中消息清空
			this.jtf.setText("");
			
		}
	}

//	@Override
//	public void run() {
//		
//		while(true) 
//		{
//			//读取，如果读不到就等待
//			try {
//				//读取
//				ObjectInputStream ois = new ObjectInputStream(QQClientConServer.s.getInputStream());
//				Message m=(Message)ois.readObject();
//				
//				//显示信息
//				String info=m.getSender()+" 对 "+m.getGetter()+" 说 "+m.getCon()+"\r\n";
//				this.jta.append(info);
//				
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//	}
	//能够在底层显示图片的一个内部类JPanel
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
