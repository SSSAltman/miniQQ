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
		try { // ʹ��Windows�Ľ�����
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
		this.ownerid=ownerid;
		
		//��ʼ�����
		jp1=new JPanel();
		jp2=new BackImage("Image/����.png");
		jc1=new JCheckBox("��");
		jc2=new JCheckBox("��");
		jta=new JTextArea();
		jtf1=new JTextField(15);
		jb=new JButton("����ͶƱ");
		
		//��Ӽ�����
		jb.addActionListener(this);
		jc1.addActionListener(this);
		jc2.addActionListener(this);
		//��Ӽ��̼�����ʵ����������ﰴ��Enter֮����Է���
		jtf1.addKeyListener(new KeyAdapter() {
	      	public void keyPressed(KeyEvent e) {
	      		if(e.getKeyChar()==KeyEvent.VK_ENTER) {
	      			jb.doClick();
	      		}
	      	}
	      });
		
		//������
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
		
		this.setTitle(ownerid+" �߰�༶��ͶƱ����");
		this.add(jp2,"Center");
		this.add(jp1, "South");
		this.setIconImage((new ImageIcon("Image/picture1.jpg").getImage()));
		this.setSize(500,300);
		this.setVisible(true);
		
		}

	//��ʾ��ͶƱ�ķ���
		public void showMessage(Message m){
			//��ʾ��Ϣ
			//String info=m.getSender()+" �� "+m.getGetter()+" ˵ "+m.getCon()+"\r\n";
			String info=m.getSender()+" ������ͶƱ "+" : "+m.getCon()+"?"+"\r\n";
			this.jta.append(info);
			this.jtf1.setText("");
		}
		
		//��ʾ��Ͷ�޳�Ʊ�����ķ���
				public void showYesMen(Message m){
					//��ʾ��Ϣ
					//String info=m.getSender()+" �� "+m.getGetter()+" ˵ "+m.getCon()+"\r\n";
					String info="�Ѿ��� "+m.getSender()+" ��������Ͷ���޳�Ʊ"+"\r\n";
					//String info="�Ѿ���1��������Ͷ���޳�Ʊ"+"\r\n";
					this.jta.append(info);
				
				}
				
		//��ʾ��Ͷ����Ʊ�����ķ���
				public void showNoMen(Message m){
					//��ʾ��Ϣ
					String info="�Ѿ��� "+m.getSender()+" ��������Ͷ�˷���Ʊ"+"\r\n";
					//String info="�Ѿ���1��������Ͷ�˷���Ʊ"+"\r\n";
					this.jta.append(info);
					
				}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		//����û�����˷�����Ϣ���ͷ���ͶƱ��ȥ
		if(arg0.getSource()==jb) {
//			System.out.println("�����˷���ͶƱ��ť");
			//����û�����ˣ�������Ϣ
			Message m=new Message();
			m.setSender(this.ownerid);
			m.setGetter("ͶƱ");
			m.setCon(jtf1.getText());
			m.setSendTime(new java.util.Date().toString());
			
			//���͸�������
			try {
				this.showMessage(m);
				ObjectOutputStream oos=new ObjectOutputStream
						(ManageClientConServerThread.getClientConServer(ownerid).getS().getOutputStream());
				oos.writeObject(m);
				//System.out.println("��Ϣ���������");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(arg0.getSource()==jc1) {
			if(jc1.isSelected()) {
				System.out.println("��Ͷ���޳�Ʊ");
				//����û�Ͷ���޳�Ʊ
				Message k=new Message();
				k.setSender(this.ownerid);
				k.setGetter("�޳�");
				k.setCon("�޳�");
				k.setSendTime(new java.util.Date().toString());
				//���͸�������
				try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServer(ownerid).getS().getOutputStream());
					oos.writeObject(k);
					System.out.println("�޳���Ϣ���������");
				} catch (Exception e) {
					e.printStackTrace();
				}
				jc1.setSelected(false); 
				JOptionPane.showMessageDialog(this,"���Ѿ�ͶƱ");
			}
		}
		else if(arg0.getSource()==jc2) {
			if(jc2.isSelected()) {
				System.out.println("��Ͷ�˷���Ʊ");
				//����û�Ͷ�˷���Ʊ
				Message k=new Message();
				k.setSender(this.ownerid);
				k.setGetter("����");
				k.setCon("����");
				k.setSendTime(new java.util.Date().toString());
				//���͸�������
				try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServer(ownerid).getS().getOutputStream());
					oos.writeObject(k);
					System.out.println("������Ϣ���������");
				} catch (Exception e) {
					e.printStackTrace();
				}
				jc2.setSelected(false); 
				JOptionPane.showMessageDialog(this,"���Ѿ�ͶƱ");
			}
		}
		
	}
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
