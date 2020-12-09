/*
 * 这是与好友聊天的界面
 *  因为客户端要处于读取状态，所以我们将其做成一个线程
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

public class QQChat extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp1;
	BackImage jp2;
	String ownerId;
	String friendId;
	
	public static void main(String[]args) {
		
	}
	
	public QQChat(String ownerid,String friend) {
		if(friend.equals("七班群聊")) {
		new BackImage("Image/上吊课设.png");
		}
		else {
		new BackImage("Image/酷安.jpg");
		}
		try { // 使用Windows的界面风格
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
		this.ownerId=ownerid;
		this.friendId=friend;
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("发送");
		jb.addActionListener(this);
		jp1=new JPanel();
		if(friend.equals("七班群聊")) {
		jp2=new BackImage("Image/上吊课设.png");}
		else {
		jp2=new BackImage("Image/合影.png");
		}
		jp1.add(jtf);
		jp1.add(jb);
		jp2.add(jta);
		
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
		this.setTitle(ownerid+" 和 "+friend+" 聊天");
		this.add(jp1, "South");
		this.add(jp2,"Center");
		this.setIconImage((new ImageIcon("Image/picture1.jpg").getImage()));
		this.setSize(500,300);
		this.setVisible(true);
		
	}

	
	//显示出消息的方法
	public void showMessage(Message m){
		//显示信息
		//String info=m.getSender()+" 对 "+m.getGetter()+" 说 "+m.getCon()+"\r\n";
		String info=m.getSender()+" : "+m.getCon()+"\r\n";
		this.jta.append(info);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==jb) {
			//如果用户点击了，发送消息
			Message m=new Message();
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			//System.out.println("对象封装完毕，准备发送");
			//发送给服务器
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

