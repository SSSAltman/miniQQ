package com.qq.client.view;
/*
 * �ҵĺ����б�������������İ����
 * */
import javax.swing.*;

import com.qq.client.tools.ManageQQChat;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class QQFriendList extends JFrame implements ActionListener,MouseListener{

	String owner;
	
	//�����һ�ſ�Ƭ
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	
	//����ڶ��ſ�Ƭ
		JPanel jpmsr1,jpmsr2,jpmsr3;
		JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
		JScrollPane jsp2;
	//��������ſ�Ƭ
		JPanel jphmd1,jphmd2,jphmd3;
		JButton jphmd_jb1,jphmd_jb2,jphmd_jb3;
		JScrollPane jsp3;
		
	//������JFrame����ΪGardLayout����
		CardLayout cl;
	public static void main(String[]args) {
		new QQFriendList("YES");
	}
	
	public QQFriendList(String ownerid) {
		
		this.owner=ownerid;
		
		//�����һ�ſ�Ƭ����ʾ�����б�
        jphy_jb1=new JButton("�ҵĺ���");
		jphy_jb2=new JButton("İ����");
		//���һ��������
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("������");
		//���һ��������
		jphy_jb3.addActionListener(this);
		jphy1=new JPanel(new BorderLayout());
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
		//��������ť���뵽jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		
		jsp1=new JScrollPane(jphy2);
		
		//��jphy��ʼ��
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		//����ڶ��ſ�Ƭ����ʾİ�����б�
        jpmsr_jb1=new JButton("�ҵĺ���");
        jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("İ����");
		jpmsr_jb3=new JButton("������");
		jpmsr_jb3.addActionListener(this);
		jpmsr1=new JPanel(new BorderLayout());
		//�ٶ���20��İ����
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		//��jpmsr2��ʼ��50��İ����
		JLabel []jbls2=new JLabel[20]; 
		
		for(int i=0;i<jbls2.length;i++) {
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("Image/����С��2.0.png"),JLabel.LEFT);
			jbls2[i].addMouseListener(this);
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2, 1));
		//��������ť���뵽jpmsr3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		
		jsp2=new JScrollPane(jpmsr2);
		
		//��jpmsr��ʼ��
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		//��������ſ�Ƭ����ʾ�������б�
        jphmd_jb1=new JButton("�ҵĺ���");
        jphmd_jb1.addActionListener(this);
		jphmd_jb2=new JButton("İ����");
		jphmd_jb2.addActionListener(this);
		jphmd_jb3=new JButton("������");
		jphmd1=new JPanel(new BorderLayout());
		//�ٶ���10��������
		jphmd2=new JPanel(new GridLayout(10,1,4,4));
		
		//��jphmd2��ʼ��10������
		JLabel []jbls3=new JLabel[10]; 
		
		for(int i=0;i<jbls3.length;i++) {
			jbls3[i]=new JLabel(i+1+"",new ImageIcon("Image/����ʤ��1.0.png"),JLabel.LEFT);
			jbls3[i].addMouseListener(this);
			jphmd2.add(jbls3[i]);
		}
		
		jphmd3=new JPanel(new GridLayout(2, 1));
		//��������ť���뵽jphmd3
		jphmd3.add(jphmd_jb1);
		jphmd3.add(jphmd_jb2);
		
		
		jsp3=new JScrollPane(jphmd2);
		
		//��jphmd��ʼ��
		jphmd1.add(jphmd3,"North");
		jphmd1.add(jsp3,"Center");
		jphmd1.add(jphmd_jb3,"South");
		
		
		//��������
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1");
		this.add(jpmsr1, "2");
		this.add(jphmd1, "3"); 
		//�ڴ�������ʾ�Լ����û���
		this.setTitle(ownerid);
		this.setSize(250,450);
		this.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//�������˲�ͬ��Ƭ��Ĳ�ͬ��ť���ͽ�����ת
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
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel)e.getSource();
		jl.setForeground(Color.RED);
		}

	@Override
	//����˳�
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
