package com.qq.client.view;

import java.util.*;
import java.awt.*;
import javax.swing.*;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQQChat;
import com.qq.common.Message;

import java.awt.event.*;
import java.net.*;
import java.security.acl.Owner;
import java.text.DecimalFormat;
import java.io.*;
import java.math.RoundingMode;

public class ManageFile extends JFrame implements ActionListener,MouseListener{

	JButton jb1;
	JLabel jb2;
	JScrollPane jsp1;
	JPanel jp1,jp2,jp3;
	String ownerID;
	private static ObjectOutputStream  oos;
	private FileInputStream fis;
	private DataOutputStream dos;
	private static FileOutputStream fos;  //�����յ����ļ�д�����
    private FileInputStream in;     //��ȡ���͹����������ļ�
    private static DataInputStream dis;
    //Socket socket;          //�׽��֣����������ļ�
    Socket s;               //�׽��֣����������ļ�
    private static DecimalFormat df = null; 
    //ServerSocket server;    //��һ���µķ���������
    static {  
        // �������ָ�ʽ������һλ��ЧС��  
        df = new DecimalFormat("#0.0");  
        df.setRoundingMode(RoundingMode.HALF_UP);  
        df.setMinimumFractionDigits(1);  
        df.setMaximumFractionDigits(1);  
    }  
	
	public static void main(String[]args) {
		
	} 
	
    public ManageFile(String owner) {
    	
    	try { // ʹ��Windows�Ľ�����
			   UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			   e.printStackTrace();
		}
    	
    	this.ownerID=owner;
    	
    	jb1=new JButton("�ļ��ϴ�");
    	jb2=new JLabel("�ļ�����˫���ļ���");
    	jp2=new JPanel(new GridLayout(15, 1,4,4));
    	jp3=new JPanel();
    	
    	//����һ�����飬���������ļ���
    	String arr[]= {"601048877.jpg","Lancer.png","����ʤ��.jpg",
    			"����.PNG","�ں��ֽ.jpg","�ֵ�.jpg",
    			"����1.jpg","����.jpg",
    			"ƽ��ʱ��.jpg","����.jpg",
    			"���.jpg","201800301058_������_305.docx",
    			"u=3269538440,1704947521&fm=26&gp=0.jpg","�������Թ�.PNG","UML1_1.png",
    			"201800301119.jpg"};               
    	
    	//��ʼ��һ���ļ��б�
    	JLabel []jbls=new JLabel[arr.length];
    	//��ÿһ����ǩ���ϼ�����
    	for(int i=0;i<jbls.length;i++) {
    		jbls[i]=new JLabel(arr[i]);
    		jbls[i].addMouseListener(this);
    		jp2.add(jbls[i]);
    	}
    
    	//��Ӽ�����
    	jb1.addActionListener(this);
    	
    	
    	jp3.add(jb1);
    	jp3.add(jb2);
    	
    	jsp1=new JScrollPane(jp2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	jp1=new JPanel(new BorderLayout());
    	
    	jp1.add(jsp1,"West");
    	jp1.add(jp3);
    	
    	this.add(jp1);
    	this.setTitle(this.ownerID+" ���ļ�����");
    	this.setVisible(true);
    	this.setSize(800,600);
    }

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb1) {
			
			try {
				//�½�һ��Socket����
				Socket s=new Socket ("127.0.0.1",3456);
				//�����ļ��ϴ�����
				PrintWriter pw=new PrintWriter(s.getOutputStream(),true);
				pw.println("�ϴ�");
				
				JFileChooser jfc=new JFileChooser("D:\\");
				jfc.showOpenDialog(null);
				File file=jfc.getSelectedFile();
				
				
				
				if(file.exists()) {
					fis = new FileInputStream(file);
					dos = new DataOutputStream(s.getOutputStream());
					
					
					//�����ļ����ͳ���
					dos.writeUTF(file.getName());
					dos.flush();
					dos.writeLong(file.length());
					dos.flush();
					
					// ��ʼ�����ļ�  
	                System.out.println("======== �ͻ��˿�ʼ�����ļ� ========");  
	                byte[] bytes = new byte[1024];  
	                int length = 0;  
	                long progress = 0;  
	                while((length = fis.read(bytes, 0, bytes.length)) != -1) {  
	                    dos.write(bytes, 0, length);  
	                    dos.flush();  
	                    progress += length;  
	                    System.out.print("| " + (100*progress/file.length()) + "% |");  
	                }  
	                System.out.println();  
	                System.out.println("======== �ͻ����ļ�����ɹ� ========");  
				}
				
				} catch (Exception e2) {
				e2.printStackTrace();
			}
			finally {  
	            try {  
	                if(fis != null)  
	                    fis.close();  
	                if(dos != null) {}
	                    dos.close();  
	                //s.close();  
	            } catch (Exception e3) {}  
	        } 
		}
		
	}

	//���˫����ǩ
	public void mouseClicked(MouseEvent arg0) {
		//�������ļ�Ҫ������
		if(arg0.getClickCount()==2) {
			//�õ��ú��ѵı��
			String FileName=((JLabel)arg0.getSource()).getText();
			System.out.println("����Ҫ�ӷ���������"+FileName);
			System.out.println("׼����������");
			try {
				
				//File directory;    //���յ����ļ������λ��
				//�½�һ��Socket����
				Socket d=new Socket ("127.0.0.1",3456);
				//�����ļ���������ֱ�Ӱ��ļ�������ȥ
				PrintWriter pw=new PrintWriter(d.getOutputStream(),true);
				pw.println(FileName);
				
				//׼������������ļ�

		    	  System.out.println("�µĶ˿��Ѿ���"); 
		    	  System.out.println("�µ������Ѿ��");
		    	  
		    	  //��dis��ȡ�ļ��Ļ�������
		    	  dis = new DataInputStream(d.getInputStream());
		    	  
		    	  //��ȡ�ļ��Ļ�������
		    	  String fileName = dis.readUTF();  
		          long fileLength = dis.readLong();  
		          
		    	  //ָ����д���ļ���λ�ã���fosд���ļ�����
//		    	  directory = new File("D:\\5556");
//		    	  if(!directory.exists()) {  
//		              directory.mkdir();  
//		          } 
		          JFileChooser jfc=new JFileChooser("D:\\");
					jfc.showOpenDialog(null);
					File file=jfc.getSelectedFile();
//		    	  File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);  
		          fos = new FileOutputStream(file);


		          //��������
		          byte[] bytes = new byte[1024];  
		          int length = 0; 
		          int count =0;
		          while((length = dis.read(bytes, 0, bytes.length)) != -1) {  
		              fos.write(bytes, 0, length);  
		              fos.flush();
		              count++;
		              if(count==fileLength/1024) {
		            	  break;
		              }
		          }  
		          System.out.println("======== �ͻ����ļ����ճɹ� [File Name��" + fileName + "] [Size��" + getFormatFileSize(fileLength) + "] ========");
				}
					
					catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	//������
	public void mouseEntered(MouseEvent arg0) {
		//������ʱ�͸ı���ɫ
		JLabel jl = (JLabel)arg0.getSource();
		jl.setForeground(Color.GREEN);
		
	}

	//����˳�
	public void mouseExited(MouseEvent arg0) {
		//����˳�ʱ�ͱ��ԭ������ɫ
		JLabel jl = (JLabel)arg0.getSource();
		jl.setForeground(Color.BLACK);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	 private String getFormatFileSize(long length) {  
	      double size = ((double) length) / (1 << 30);  
	      if(size >= 1) {  
	          return df.format(size) + "GB";  
	      }  
	      size = ((double) length) / (1 << 20);  
	      if(size >= 1) {  
	          return df.format(size) + "MB";  
	      }  
	      size = ((double) length) / (1 << 10);  
	      if(size >= 1) {  
	          return df.format(size) + "KB";  
	      }  
	      return length + "B";  
	  }  
}
