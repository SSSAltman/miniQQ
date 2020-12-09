/*
 * �°��ҵĺ����б������ҵĺ��ѣ�Ⱥ�ģ���ͬ�İ�ť��������ת
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
	
	//�����һ�ſ�Ƭ
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_jb4,jphy_jb5;
	JScrollPane jsp1;
	
	public static void main(String[]args) {
		
	}
	
	public NewQQFriendList(String ownerid) {
		try { // ʹ��Windows�Ľ�����
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
		this.owner=ownerid;
		
		//�����һ�ſ�Ƭ����ʾ�����б�
        jphy_jb1=new JButton("�ҵĺ���");
		jphy_jb2=new JButton("����");
		jphy_jb4=new JButton("ͶƱ");
		jphy_jb5=new JButton("��ʱ��ͼ");
		//���һ��������
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("�ļ�");
		//���һ��������
		jphy_jb3.addActionListener(this);
		jphy1=new JPanel(new BorderLayout());
		//���һ��������
		jphy_jb4.addActionListener(this);
		//���һ��������
		jphy_jb5.addActionListener(this);
		//�ٶ���50������
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		
		//��jphy2��ʼ��һ��Ⱥ��
		JLabel []jbls=new JLabel[51]; 
		jbls[0]=new JLabel("�߰�Ⱥ��",new ImageIcon("Image/ƽ��ʱ��.jpg"),JLabel.LEFT);
		jbls[0].addMouseListener(this);
		
		//��jphy2��ʼ��50������
		for(int i=0;i<50;i++) {
			jbls[i+1]=new JLabel(i+1+"",new ImageIcon("Image/Saber.png"),JLabel.LEFT);
			jbls[i+1].addMouseListener(this);
			jphy2.add(jbls[i]);
		}
		
		jphy3=new JPanel(new GridLayout(2, 1));
		//���ĸ���ť���뵽jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jphy3.add(jphy_jb4);
		jphy3.add(jphy_jb5);
		
		
		
		jsp1=new JScrollPane(jphy2);
		
		//��jphy��ʼ��
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		//��������
		this.add(jphy1);
		
		//�ڴ�������ʾ�Լ����û���
		this.setTitle(ownerid);
		this.setSize(250,450);
		this.setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		//�������˲�ͬ��ť���ͽ�����ת���������
		if(e.getSource()==jphy_jb2) {
			QQNotice qqNotice= new QQNotice(this.owner);
			//�ѹ��洰�ڼ��뵽��������
			ManageQQNotice.addQQNotice("�Լ�", qqNotice);
		}
		else if(e.getSource()==jphy_jb3) {
			new ManageFile(this.owner);
		}
		else if(e.getSource()==jphy_jb4) {
			ClientVote client=new ClientVote(this.owner);
			//��ͶƱ������뵽��������
			ManageClientVote.addClientVote("ͶƱ", client);
			System.out.println("ͶƱ�����Ѿ������������");
		}
		else if(e.getSource()==jphy_jb5) {
			new DrawPad(this.owner+"�Ļ�ͼ��");
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		//��Ӧ�û�˫�����¼������ҵõ��û����
		if(e.getClickCount()==2) {
			//�õ��ú��ѵı��
			String friendNo=((JLabel)e.getSource()).getText();
			QQChat qqChat= new QQChat(this.owner,friendNo);
			
			//�����������뵽��������
			System.out.println("�Ҵ���ȥ��owner��"+this.owner);
			ManageQQChat.addQQChat(this.owner+" "+friendNo,qqChat);
			
			
			
		}
	}

	//������
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.RED);
		}
	//����˳�
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
