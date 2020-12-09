package com.qq.client.view;
/*
 * 我的好友列表，包括黑名单，陌生人
 * */
import javax.swing.*;

import com.qq.client.tools.ManageQQChat;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class QQFriendList extends JFrame implements ActionListener,MouseListener{

	String owner;
	
	//处理第一张卡片
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	
	//处理第二张卡片
		JPanel jpmsr1,jpmsr2,jpmsr3;
		JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
		JScrollPane jsp2;
	//处理第三张卡片
		JPanel jphmd1,jphmd2,jphmd3;
		JButton jphmd_jb1,jphmd_jb2,jphmd_jb3;
		JScrollPane jsp3;
		
	//把整个JFrame设置为GardLayout布局
		CardLayout cl;
	public static void main(String[]args) {
		new QQFriendList("YES");
	}
	
	public QQFriendList(String ownerid) {
		
		this.owner=ownerid;
		
		//处理第一张卡片（显示好友列表）
        jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		//添加一个监听器
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("黑名单");
		//添加一个监听器
		jphy_jb3.addActionListener(this);
		jphy1=new JPanel(new BorderLayout());
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
		//把两个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		
		jsp1=new JScrollPane(jphy2);
		
		//对jphy初始化
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		//处理第二张卡片（显示陌生人列表）
        jpmsr_jb1=new JButton("我的好友");
        jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("黑名单");
		jpmsr_jb3.addActionListener(this);
		jpmsr1=new JPanel(new BorderLayout());
		//假定有20个陌生人
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		//给jpmsr2初始化50个陌生人
		JLabel []jbls2=new JLabel[20]; 
		
		for(int i=0;i<jbls2.length;i++) {
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("Image/蜡笔小新2.0.png"),JLabel.LEFT);
			jbls2[i].addMouseListener(this);
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2, 1));
		//把两个按钮加入到jpmsr3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		
		jsp2=new JScrollPane(jpmsr2);
		
		//对jpmsr初始化
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		//处理第三张卡片（显示黑名单列表）
        jphmd_jb1=new JButton("我的好友");
        jphmd_jb1.addActionListener(this);
		jphmd_jb2=new JButton("陌生人");
		jphmd_jb2.addActionListener(this);
		jphmd_jb3=new JButton("黑名单");
		jphmd1=new JPanel(new BorderLayout());
		//假定有10个黑名单
		jphmd2=new JPanel(new GridLayout(10,1,4,4));
		
		//给jphmd2初始化10个仇人
		JLabel []jbls3=new JLabel[10]; 
		
		for(int i=0;i<jbls3.length;i++) {
			jbls3[i]=new JLabel(i+1+"",new ImageIcon("Image/爆豪胜己1.0.png"),JLabel.LEFT);
			jbls3[i].addMouseListener(this);
			jphmd2.add(jbls3[i]);
		}
		
		jphmd3=new JPanel(new GridLayout(2, 1));
		//把两个按钮加入到jphmd3
		jphmd3.add(jphmd_jb1);
		jphmd3.add(jphmd_jb2);
		
		
		jsp3=new JScrollPane(jphmd2);
		
		//对jphmd初始化
		jphmd1.add(jphmd3,"North");
		jphmd1.add(jsp3,"Center");
		jphmd1.add(jphmd_jb3,"South");
		
		
		//添加总组件
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1");
		this.add(jpmsr1, "2");
		this.add(jphmd1, "3"); 
		//在窗口栏显示自己的用户名
		this.setTitle(ownerid);
		this.setSize(250,450);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//如果点击了不同卡片里的不同按钮，就进行跳转
		if(e.getSource()==jphy_jb2) {
			cl.show(this.getContentPane(),"2");
		}
		else if(e.getSource()==jphy_jb3) {
			cl.show(this.getContentPane(), "3");
		}
		else if(e.getSource()==jpmsr_jb1) {
			cl.show(this.getContentPane(),"1" );
		}
		else if(e.getSource()==jpmsr_jb3) {
			cl.show(this.getContentPane(),"3" );
		}
		else if(e.getSource()==jphmd_jb1) {
			cl.show(this.getContentPane(), "1");
		}else if(e.getSource()==jphmd_jb2) {
			cl.show(this.getContentPane(), "2");
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
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.RED);
		}

	@Override
	//鼠标退出
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
