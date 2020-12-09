/*
 * 新版我的好友列表，包括我的好友，群聊，不同的按钮来进行跳转
 * */
package com.qq.client.view;
import javax.swing.*;

import com.qq.client.tools.ManageClientVote;
import com.qq.client.tools.ManageQQChat;
import com.qq.client.tools.ManageQQNotice;

import minidrawpad.DrawPad;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class NewQQFriendList extends JFrame implements ActionListener,MouseListener{

	String owner;
	
	//处理第一张卡片
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_jb4,jphy_jb5;
	JScrollPane jsp1;
	
	public static void main(String[]args) {
		
	}
	
	public NewQQFriendList(String ownerid) {
		try { // 使用Windows的界面风格
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
		this.owner=ownerid;
		
		//处理第一张卡片（显示好友列表）
        jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("公告");
		jphy_jb4=new JButton("投票");
		jphy_jb5=new JButton("即时绘图");
		//添加一个监听器
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("文件");
		//添加一个监听器
		jphy_jb3.addActionListener(this);
		jphy1=new JPanel(new BorderLayout());
		//添加一个监听器
		jphy_jb4.addActionListener(this);
		//添加一个监听器
		jphy_jb5.addActionListener(this);
		//假定有50个好友
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		
		//给jphy2初始化一个群聊
		JLabel []jbls=new JLabel[51]; 
		jbls[0]=new JLabel("七班群聊",new ImageIcon("Image/平成时代.jpg"),JLabel.LEFT);
		jbls[0].addMouseListener(this);
		
		//给jphy2初始化50个好友
		for(int i=0;i<50;i++) {
			jbls[i+1]=new JLabel(i+1+"",new ImageIcon("Image/Saber.png"),JLabel.LEFT);
			jbls[i+1].addMouseListener(this);
			jphy2.add(jbls[i]);
		}
		
		jphy3=new JPanel(new GridLayout(2, 1));
		//把四个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jphy3.add(jphy_jb4);
		jphy3.add(jphy_jb5);
		
		
		
		jsp1=new JScrollPane(jphy2);
		
		//对jphy初始化
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		//添加总组件
		this.add(jphy1);
		
		//在窗口栏显示自己的用户名
		this.setTitle(ownerid);
		this.setSize(250,450);
		this.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		//如果点击了不同按钮，就进行跳转到公告界面
		if(e.getSource()==jphy_jb2) {
			QQNotice qqNotice= new QQNotice(this.owner);
			//把公告窗口加入到管理类中
			ManageQQNotice.addQQNotice("自己", qqNotice);
		}
		else if(e.getSource()==jphy_jb3) {
			new ManageFile(this.owner);
		}
		else if(e.getSource()==jphy_jb4) {
			ClientVote client=new ClientVote(this.owner);
			//把投票界面加入到管理类中
			ManageClientVote.addClientVote("投票", client);
			System.out.println("投票界面已经加入管理类中");
		}
		else if(e.getSource()==jphy_jb5) {
			new DrawPad(this.owner+"的绘图板");
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		//响应用户双击的事件，并且得到用户编号
		if(e.getClickCount()==2) {
			//得到该好友的编号
			String friendNo=((JLabel)e.getSource()).getText();
			QQChat qqChat= new QQChat(this.owner,friendNo);
			
			//把聊天界面加入到管理类中
			System.out.println("我传过去的owner是"+this.owner);
			ManageQQChat.addQQChat(this.owner+" "+friendNo,qqChat);
			
			
			
		}
	}

	//鼠标进入
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.RED);
		}
	//鼠标退出
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.BLACK);
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
