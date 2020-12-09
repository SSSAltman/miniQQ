package com.qq.server.view;
/*
 * 服务器的控制界面，可以启动服务器，关闭服务器
 * 可以管理和监控用户
 * */

import java.awt.*;
import javax.swing.*;
import com.qq.server.model.MyQQServer;
import java.awt.event.*;

public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1,jb2;
	
	public static void main(String[]args) {
		new MyServerFrame();
	}
	
	public MyServerFrame() {
		try { // 使用Windows的界面风格
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
		jp1=new JPanel();
		jb1=new JButton("启动服务器");
		jb2=new JButton("关闭服务器");
		
		jp1.add(jb1);
		jp1.add(jb2);
		//添加监听器
		jb1.addActionListener(this);
		
		this.setLocation(800, 400);
		this.add(jp1);
		this.setForeground(Color.RED);
		this.setTitle("终极的开关");
		this.setSize(400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==jb1) {
			new MyQQServer();
		}
		
	}
	
}
