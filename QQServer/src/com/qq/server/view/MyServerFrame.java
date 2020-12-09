package com.qq.server.view;
/*
 * �������Ŀ��ƽ��棬�����������������رշ�����
 * ���Թ���ͼ���û�
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
		try { // ʹ��Windows�Ľ�����
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
		jp1=new JPanel();
		jb1=new JButton("����������");
		jb2=new JButton("�رշ�����");
		
		jp1.add(jb1);
		jp1.add(jb2);
		//��Ӽ�����
		jb1.addActionListener(this);
		
		this.setLocation(800, 400);
		this.add(jp1);
		this.setForeground(Color.RED);
		this.setTitle("�ռ��Ŀ���");
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
