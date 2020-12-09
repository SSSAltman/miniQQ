package com.qq.client.view;

import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.view.QQChat.BackImage;
import com.qq.common.Message;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class ClientVote extends JFrame implements ActionListener{

	
	JPanel jp1;
	BackImage jp2;
	
	JTextArea jta;
	JTextField jtf1;
	JCheckBox jc1;
	JCheckBox jc2;
	JButton jb;
	String ownerid;
	
	public static void main(String[]args) {
	}
	
	public ClientVote(String ownerid) {
		try { // 使用Windows的界面风格
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
		this.ownerid=ownerid;
		
		//初始化组件
		jp1=new JPanel();
		jp2=new BackImage("Image/赛文.png");
		jc1=new JCheckBox("是");
		jc2=new JCheckBox("否");
		jta=new JTextArea();
		jtf1=new JTextField(15);
		jb=new JButton("发送投票");
		
		//添加监听器
		jb.addActionListener(this);
		jc1.addActionListener(this);
		jc2.addActionListener(this);
		//添加键盘监听，实现在密码框里按下Enter之后可以发送
		jtf1.addKeyListener(new KeyAdapter() {
	      	public void keyPressed(KeyEvent e) {
	      		if(e.getKeyChar()==KeyEvent.VK_ENTER) {
	      			jb.doClick();
	      		}
	      	}
	      });
		
		//添加组件
		jp1.add(jtf1);
		jp1.add(jb);
		jp1.add(jc1);
		jp1.add(jc2);
		jp2.add(jta);
		
		this.addWindowListener( new WindowAdapter() {
		    public void windowOpened( WindowEvent e ){
		        jtf1.requestFocus();
		    }
		}); 
		
		this.setTitle(ownerid+" 七班班级的投票界面");
		this.add(jp2,"Center");
		this.add(jp1, "South");
		this.setIconImage((new ImageIcon("Image/picture1.jpg").getImage()));
		this.setSize(500,300);
		this.setVisible(true);
		
		}

	//显示出投票的方法
		public void showMessage(Message m){
			//显示信息
			//String info=m.getSender()+" 对 "+m.getGetter()+" 说 "+m.getCon()+"\r\n";
			String info=m.getSender()+" 发送了投票 "+" : "+m.getCon()+"?"+"\r\n";
			this.jta.append(info);
			this.jtf1.setText("");
		}
		
		//显示出投赞成票人数的方法
				public void showYesMen(Message m){
					//显示信息
					//String info=m.getSender()+" 对 "+m.getGetter()+" 说 "+m.getCon()+"\r\n";
					String info="已经有 "+m.getSender()+" 个人匿名投了赞成票"+"\r\n";
					//String info="已经有1个人匿名投了赞成票"+"\r\n";
					this.jta.append(info);
				
				}
				
		//显示出投反对票人数的方法
				public void showNoMen(Message m){
					//显示信息
					String info="已经有 "+m.getSender()+" 个人匿名投了反对票"+"\r\n";
					//String info="已经有1个人匿名投了反对票"+"\r\n";
					this.jta.append(info);
					
				}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//如果用户点击了发送消息，就发送投票过去
		if(arg0.getSource()==jb) {
//			System.out.println("你点击了发送投票按钮");
			//如果用户点击了，发送消息
			Message m=new Message();
			m.setSender(this.ownerid);
			m.setGetter("投票");
			m.setCon(jtf1.getText());
			m.setSendTime(new java.util.Date().toString());
			
			//发送给服务器
			try {
				this.showMessage(m);
				ObjectOutputStream oos=new ObjectOutputStream
						(ManageClientConServerThread.getClientConServer(ownerid).getS().getOutputStream());
				oos.writeObject(m);
				//System.out.println("消息对象发送完毕");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(arg0.getSource()==jc1) {
			if(jc1.isSelected()) {
				System.out.println("你投了赞成票");
				//如果用户投了赞成票
				Message k=new Message();
				k.setSender(this.ownerid);
				k.setGetter("赞成");
				k.setCon("赞成");
				k.setSendTime(new java.util.Date().toString());
				//发送给服务器
				try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServer(ownerid).getS().getOutputStream());
					oos.writeObject(k);
					System.out.println("赞成消息对象发送完毕");
				} catch (Exception e) {
					e.printStackTrace();
				}
				jc1.setSelected(false); 
				JOptionPane.showMessageDialog(this,"您已经投票");
			}
		}
		else if(arg0.getSource()==jc2) {
			if(jc2.isSelected()) {
				System.out.println("你投了反对票");
				//如果用户投了反对票
				Message k=new Message();
				k.setSender(this.ownerid);
				k.setGetter("反对");
				k.setCon("反对");
				k.setSendTime(new java.util.Date().toString());
				//发送给服务器
				try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServer(ownerid).getS().getOutputStream());
					oos.writeObject(k);
					System.out.println("反对消息对象发送完毕");
				} catch (Exception e) {
					e.printStackTrace();
				}
				jc2.setSelected(false); 
				JOptionPane.showMessageDialog(this,"您已经投票");
			}
		}
		
	}
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
